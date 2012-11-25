package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.DrawingNewsResponse;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse.Type;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class PushServiceImpl implements PushService {

	private final List<AsynchronousResponse> pendingNewsResponses = new ArrayList<>();

	private final List<AsynchronousResponse> pendingLoginResponses = new ArrayList<>();

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
			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
				pushNews(pBox, pDrawing);
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
			}

			@Override
			public void onLogout(Box pBox, User pUser) {
				pushLogin(pUser, Type.LOGOUT);
			}
		};
	}

	private void pushNews(Box pBox, Drawing pDrawing) {
		DrawingNewsResponse news = new DrawingNewsResponse();
		news.setAmount(0.5);
		news.setBrand("news-brand");
		news.setDate(new Date());
		news.setImage("foo.png");
		news.setKegId(123);
		news.setLocation("foo-lcation");
		news.setUserId(100);
		news.setUserName("foo-user");

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
