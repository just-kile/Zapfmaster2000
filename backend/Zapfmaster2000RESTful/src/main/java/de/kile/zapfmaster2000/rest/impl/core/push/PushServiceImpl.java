package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.emf.common.util.EList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.push.DrawingNewsUpdateResponse;
import de.kile.zapfmaster2000.rest.core.push.DrawingNewsUpdateResponse.RefreshType;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse.Type;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Keg;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class PushServiceImpl implements PushService {

	private final List<AsynchronousResponse> pendingNewsResponses = new ArrayList<>();

	private final List<AsynchronousResponse> pendingLoginResponses = new ArrayList<>();

	private final Map<Box, Boolean> mapBox2FirstDrawEvent = new HashMap<>();

	public PushServiceImpl(BoxService pBoxService) {
		pBoxService.addListener(createServiceListener());
	}

	@Override
	public void addNewsRequest(AsynchronousResponse pResponse) {
		pendingNewsResponses.add(pResponse);
	}

	@Override
	public void addLoginRequest(AsynchronousResponse pResponse) {
		pendingLoginResponses.add(pResponse);
	}

	private BoxServiceListener createServiceListener() {
		return new BoxServiceListener() {

			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {
				pushLogin(pUser, Type.LOGIN);
				mapBox2FirstDrawEvent.put(pBox, true);
			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				pushNews(pBox, pDrawing.getUser(), pDrawing.getAmount());
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
				pushNews(pBox, pUser, pAmount);
				mapBox2FirstDrawEvent.put(pBox, false);
			}

			@Override
			public void onLogout(Box pBox, User pUser) {
				pushLogin(pUser, Type.LOGOUT);
			}
		};
	}

	private void pushNews(Box pBox, User pUser, double pAmount) {
		Session session = Zapfmaster2000Core.INSTANCE.getTransactionService().getSessionFactory()
				.getCurrentSession();
		Transaction tx = session.beginTransaction();
		session.update(pBox);

		EList<Keg> kegs = pBox.getKegs();
		Keg[] kegArray = kegs.toArray(new Keg[kegs.size()]);
		Arrays.sort(kegArray, new Comparator<Keg>() {
			@Override
			public int compare(Keg o1, Keg o2) {
				if (o1.getStartDate().before(o2.getStartDate())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		Keg currentKeg = kegArray[0];
		
		tx.commit();

		DrawingNewsUpdateResponse news = new DrawingNewsUpdateResponse();
		news.setAmount(pAmount);
		news.setBrand(currentKeg.getBrand());
		news.setDate(new Date());
		news.setImage(pUser.getImagePath());
		news.setKegId(currentKeg.getId());
		news.setLocation(pBox.getLocation());
		news.setUserId(pUser.getId());
		news.setUserName(pUser.getName());
		if (mapBox2FirstDrawEvent.get(pBox)) {
			news.setRefreshType(RefreshType.NEW);
		} else {
			news.setRefreshType(RefreshType.REFRESH);
		}

		for (AsynchronousResponse pendingResponse : pendingNewsResponses) {
			Response response = Response.ok(news)
					.type(MediaType.APPLICATION_JSON).build();
			pendingResponse.setResponse(response);
		}
		pendingNewsResponses.clear();
	}

	private void pushLogin(User pUser, Type pType) {
		UserLoginResponse loginResp = new UserLoginResponse();
		loginResp.setType(pType);
		loginResp.setUserName(pUser.getName());
		loginResp.setUserId(pUser.getId());

		for (AsynchronousResponse pendingResponse : pendingLoginResponses) {
			Response response = Response.ok(loginResp)
					.type(MediaType.APPLICATION_JSON).build();
			pendingResponse.setResponse(response);
		}
		pendingLoginResponses.clear();
	}
}
