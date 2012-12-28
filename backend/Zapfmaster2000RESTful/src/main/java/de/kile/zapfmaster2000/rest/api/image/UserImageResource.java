package de.kile.zapfmaster2000.rest.api.image;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.google.common.io.ByteStreams;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

@Path("image/user")
public class UserImageResource {

	private static final int MAX_FILE_SIZE = 1 * 1000 * 1000; // 1MB

	private static final Logger LOG = Logger.getLogger(UserImageResource.class);

	@GET
	@Path("/{userId}")
	public Response retrieveImage(@PathParam("userId") long pUserId,
			@QueryParam("token") String pToken) {

		// TODO: Check token
		String path = "rest/image/user/" + pUserId;

		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService()
				.getSessionFactory().getCurrentSession();
		Transaction tx = session.beginTransaction();

		try {
			@SuppressWarnings("unchecked")
			List<Image> result = session
					.createQuery("FROM Image i WHERE i.path = :path")
					.setString("path", path).list();
			if (!result.isEmpty()) {
				Image image = result.get(0);
				InputStream stream = image.getContent().getBinaryStream();
				byte[] bytes = ByteStreams.toByteArray(stream);
				return Response.ok().type(image.getContentType()).entity(bytes)
						.build();
			} else {
				return Response.status(Status.NOT_FOUND).build();
			}
		} catch (Exception e) {
			LOG.error("Could not return image");
			return Response.status(Status.INTERNAL_SERVER_ERROR).build();
		} finally {
			tx.commit();
		}
	}

	@POST
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response uploadUserImage(MultipartFormDataInput pInput) {

		try {
			Map<String, List<InputPart>> uploadForm = pInput.getFormDataMap();
			List<InputPart> tokens = uploadForm.get("token");
			assert (tokens.size() == 1);
			String token = tokens.get(0).getBodyAsString();

			User user = Zapfmaster2000Core.INSTANCE.getAuthService()
					.retrieveUser(token);
			if (user != null) {
				List<InputPart> images = uploadForm.get("image");
				assert (images.size() == 1);
				InputPart image = images.get(0);

				InputStream inputStream = image
						.getBody(InputStream.class, null);
				byte[] bytes = ByteStreams.toByteArray(inputStream);
				if (bytes.length > MAX_FILE_SIZE) {
					return Response.status(Status.BAD_REQUEST).build();
				}

				// write data to db
				String path = "rest/image/user/" + user.getId();
				String contentType = image.getMediaType().toString();

				Session session = Zapfmaster2000Core.INSTANCE
						.getTransactionService().getSessionFactory()
						.getCurrentSession();
				Transaction tx = session.beginTransaction();
				session.update(user);

				// delete old image(s)
				session.createQuery("DELETE FROM Image i WHERE i.path = :path")
						.setString("path", path).executeUpdate();

				// create new image
				Image newImage = Zapfmaster2000Factory.eINSTANCE.createImage();
				newImage.setPath(path);
				newImage.setContentType(contentType);
				newImage.setContent(Hibernate.getLobCreator(session)
						.createBlob(bytes));

				session.save(newImage);
				user.setImagePath(path);
				session.save(user);

				tx.commit();

				return Response.ok().build();
			} else {
				return Response.status(Status.FORBIDDEN).build();
			}

		} catch (IOException e) {
			LOG.error("Could not uplload image", e);
			return Response.status(Status.BAD_REQUEST).build();
		}

	}
}
