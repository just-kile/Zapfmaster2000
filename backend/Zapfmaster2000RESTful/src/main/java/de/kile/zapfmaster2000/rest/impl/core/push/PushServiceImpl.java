package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse;
import de.kile.zapfmaster2000.rest.core.push.UserLoginResponse.Type;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class PushServiceImpl implements PushService {

	private final List<AsynchronousResponse> pendingNewsResponses = new ArrayList<>();

	private final List<AsynchronousResponse> pendingLoginResponses = new ArrayList<>();

	public PushServiceImpl(NewsService pNewsService) {
		pNewsService.addListener(createNewsListener());
	}

	@Override
	public void addNewsRequest(AsynchronousResponse pResponse) {
		pendingNewsResponses.add(pResponse);
	}

	@Override
	public void addLoginRequest(AsynchronousResponse pResponse) {
		pendingLoginResponses.add(pResponse);
	}

	private NewsServiceListener createNewsListener() {
		return new NewsServiceListener() {
			
			@Override
			public void onNewsPosted(News pNews) {
				pushNews(pNews);			
			}
		};
	}

	private void pushNews(News pNews) {
		AbstractNewsResponse news = new NewsAdapter().adapt(pNews);
		
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
