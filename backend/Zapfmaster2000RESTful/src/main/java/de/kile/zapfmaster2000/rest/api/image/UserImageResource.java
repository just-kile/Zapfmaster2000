package de.kile.zapfmaster2000.rest.api.image;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
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
import org.imgscalr.Scalr;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import com.google.common.io.ByteStreams;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Image;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Factory;

@Path("image/user")
public class UserImageResource {

	private static final int IMAGE_SIZE = 48;

	private static final int IMAGE_SIZE_BIG = 160;
	
	private static final Logger LOG = Logger.getLogger(UserImageResource.class);

	
	@GET
	@Path("/{userId}")
	public Response retrieveImage(@PathParam("userId") long pUserId,
			@QueryParam("token") String pToken) {
		return retrieveImage(pUserId, pToken, "false");
	}
	@GET
	@Path("/{userId}")
	public Response retrieveImage(@PathParam("userId") long pUserId,
			@QueryParam("token") String pToken, @QueryParam("big") String pBig) {

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
				InputStream stream;
//				if ("true".equals(pBig)) {
				stream = image.getContentBig().getBinaryStream();
//				} else {
//					stream = image.getContent().getBinaryStream();
//				}
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

				// scale image
				BufferedImage bufferedImage = ImageIO.read(inputStream);
				double ratio = bufferedImage.getWidth()
						/ (double) bufferedImage.getHeight();
				if (ratio > 1) {
					int newWidth = bufferedImage.getHeight();
					int pixelsToCrop = (bufferedImage.getWidth() - newWidth) / 2;
					bufferedImage = Scalr.crop(bufferedImage, pixelsToCrop, 0,
							newWidth, bufferedImage.getHeight());
				} else if (ratio < 1) {
					int newHeight = bufferedImage.getWidth();
					int pixelsToCrop = (bufferedImage.getHeight() - newHeight) / 2;
					bufferedImage = Scalr.crop(bufferedImage, 0, pixelsToCrop,
							bufferedImage.getWidth(), newHeight);
				}

				BufferedImage imageSmall = Scalr.resize(bufferedImage,
						Scalr.Mode.FIT_EXACT, IMAGE_SIZE);
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ImageIO.write(imageSmall, "png", outputStream);
				byte[] bytesSmall = outputStream.toByteArray();
				
				BufferedImage imageBig = Scalr.resize(bufferedImage,
						Scalr.Mode.FIT_EXACT, IMAGE_SIZE_BIG);
				outputStream = new ByteArrayOutputStream();
				ImageIO.write(imageBig, "png", outputStream);
				byte[] bytesBig = outputStream.toByteArray();

				// write data to db
				String path = "rest/image/user/" + user.getId();
				// String contentType = image.getMediaType().toString();

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
				newImage.setContentType("image/png");
				newImage.setContent(Hibernate.getLobCreator(session)
						.createBlob(bytesSmall));
				newImage.setContentBig(Hibernate.getLobCreator(session)
						.createBlob(bytesBig));
				
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
