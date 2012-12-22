package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.api.push.DrawDraftKitResponse;
import de.kile.zapfmaster2000.rest.api.push.LoginDraftKitResponse;
import de.kile.zapfmaster2000.rest.api.push.LogoutDraftKitResponse;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;

public class PushServiceImpl implements PushService {

	/** pending responsed for news pushs: AccountId -> PushQueue */
	private final Map<Long, PushQueue> pendingNewsResponses = new HashMap<>();

	/** pending responses for draft kit pushs: BoxId -> PushQueue */
	private final Map<Long, PushQueue> pendingDraftKitReponses = new HashMap<>();

	public PushServiceImpl(NewsService pNewsService, BoxService pBoxService) {
		pNewsService.addListener(createNewsListener());
		pBoxService.addListener(createBoxServiceListener());
	}

	@Override
	public void addNewsRequest(AsynchronousResponse pResponse,
			Account pAccount, String pToken) {
		if (!pendingNewsResponses.containsKey(pAccount.getId())) {
			PushQueue queue = new PushQueue();
			pendingNewsResponses.put(pAccount.getId(), queue);
		}
		PushQueue queue = pendingNewsResponses.get(pAccount.getId());
		assert (queue != null);
		queue.addRequest(pResponse);
	}

	@Override
	public void addDraftkitRequest(AsynchronousResponse pResponse, Box pBox,
			String pToken) {
		if (!pendingDraftKitReponses.containsKey(pBox.getId())) {
			PushQueue queue = new PushQueue();
			pendingDraftKitReponses.put(pBox.getId(), queue);
		}
		PushQueue request = pendingDraftKitReponses.get(pBox.getId());
		assert (request != null);
		request.addRequest(pResponse);
	}

	private NewsServiceListener createNewsListener() {
		return new NewsServiceListener() {

			@Override
			public void onNewsPosted(News pNews) {
				pushNews(pNews);
			}
		};
	}

	private BoxServiceListener createBoxServiceListener() {
		return new BoxServiceListener() {

			@Override
			public void onLogout(Box pBox, User pUser) {
				pushLogout(pBox);

			}

			@Override
			public void onLoginsuccessful(Box pBox, User pUser) {
				pushLogin(pBox, pUser);

			}

			@Override
			public void onEndDrawing(Box pBox, Drawing pDrawing) {
			}

			@Override
			public void onDrawing(Box pBox, User pUser, double pAmount) {
				pushDrawing(pBox, pUser, pAmount);
			}

		};
	}

	private void pushNews(News pNews) {
		Account account = pNews.getAccount();
		if (pendingNewsResponses.containsKey(account.getId())) {
			AbstractNewsResponse news = new NewsAdapter().adapt(pNews);
			Response response = Response.ok(news)
					.type(MediaType.APPLICATION_JSON).build();

			PushQueue queue = pendingNewsResponses.get(account.getId());
			queue.push(response, true);
		}
	}

	private void pushLogin(Box pBox, User pUser) {
		if (pendingDraftKitReponses.containsKey(pBox.getId())) {
			LoginDraftKitResponse entity = new LoginDraftKitResponse();
			entity.setBoxId(pBox.getId());
			entity.setImagePath(pUser.getImagePath());
			entity.setUserId(pUser.getId());
			entity.setUserName(pUser.getName());
			
			Response response = Response.ok(entity)
					.type(MediaType.APPLICATION_JSON).build();
			
			PushQueue queue = pendingDraftKitReponses.get(pBox.getId());
			queue.push(response, true);
		}
	}

	private void pushDrawing(Box pBox, User pUser, double pAmount) {
		if (pendingDraftKitReponses.containsKey(pBox.getId())) {
			DrawDraftKitResponse entity = new DrawDraftKitResponse();
			entity.setBoxId(pBox.getId());
			entity.setImagePath(pUser.getImagePath());
			entity.setUserId(pUser.getId());
			entity.setUserName(pUser.getName());
			entity.setAmount(pAmount);
			
			Response response = Response.ok(entity)
					.type(MediaType.APPLICATION_JSON).build();
			
			PushQueue queue = pendingDraftKitReponses.get(pBox.getId());
			queue.push(response, false);
		}
	}

	private void pushLogout(Box pBox) {
		if (pendingDraftKitReponses.containsKey(pBox.getId())) {
			LogoutDraftKitResponse entity = new LogoutDraftKitResponse();
			entity.setBoxId(pBox.getId());
			Response response = Response.ok(entity)
					.type(MediaType.APPLICATION_JSON).build();
			
			PushQueue queue = pendingDraftKitReponses.get(pBox.getId());
			queue.push(response, true);
		}
	}
}
