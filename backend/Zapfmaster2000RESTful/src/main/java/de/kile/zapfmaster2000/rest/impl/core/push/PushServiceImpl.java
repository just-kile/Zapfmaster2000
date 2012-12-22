package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;

public class PushServiceImpl implements PushService {

	/** pending responsed for news pushs: AccountId -> PushQueue */
	private final Map<Long, PushQueue> pendingNewsResponses = new HashMap<>();

	public PushServiceImpl(NewsService pNewsService) {
		pNewsService.addListener(createNewsListener());
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

	private NewsServiceListener createNewsListener() {
		return new NewsServiceListener() {

			@Override
			public void onNewsPosted(News pNews) {
				pushNews(pNews);
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

}
