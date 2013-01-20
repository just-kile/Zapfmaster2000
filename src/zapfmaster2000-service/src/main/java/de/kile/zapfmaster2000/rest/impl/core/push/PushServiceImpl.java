package de.kile.zapfmaster2000.rest.impl.core.push;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jboss.resteasy.spi.AsynchronousResponse;

import de.kile.zapfmaster2000.rest.api.news.AbstractNewsResponse;
import de.kile.zapfmaster2000.rest.api.push.DrawDraftKitResponse;
import de.kile.zapfmaster2000.rest.api.push.LoginDraftKitResponse;
import de.kile.zapfmaster2000.rest.api.push.LogoutDraftKitResponse;
import de.kile.zapfmaster2000.rest.core.Zapfmaster2000Core;
import de.kile.zapfmaster2000.rest.core.box.BoxService;
import de.kile.zapfmaster2000.rest.core.box.BoxServiceListener;
import de.kile.zapfmaster2000.rest.core.box.LoginFailureReason;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeService;
import de.kile.zapfmaster2000.rest.core.challenge.ChallengeServiceListener;
import de.kile.zapfmaster2000.rest.core.news.NewsService;
import de.kile.zapfmaster2000.rest.core.news.NewsServiceListener;
import de.kile.zapfmaster2000.rest.core.push.PushService;
import de.kile.zapfmaster2000.rest.core.util.NewsAdapter;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Account;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Box;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Challenge1v1;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Drawing;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.News;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.User;
import de.kile.zapfmaster2000.rest.model.zapfmaster2000.Zapfmaster2000Package;

public class PushServiceImpl implements PushService {

	/** pending responses for news pushs: AccountId -> PushQueue */
	private final Map<Long, PushQueue> pendingNewsResponses = new HashMap<>();

	/** pending responses for draft kit pushs: BoxId -> PushQueue */
	private final Map<Long, PushQueue> pendingDraftKitReponses = new HashMap<>();

	/** pending reponses for challenge pushs: UserId -> PushQueue */
	private final Map<Long, PushQueue> pendingChallengeResponses = new HashMap<>();
	
	/** pending responses for unknown rfid tag pushes: BoxId -> PushQueue */
	private final Map<Long, PushQueue> pendingUnkownRfidResponses = new HashMap<>();

	public PushServiceImpl(NewsService pNewsService, BoxService pBoxService,
			ChallengeService pChallengeService) {
		pNewsService.addListener(createNewsListener());
		pBoxService.addListener(createBoxServiceListener());
		pChallengeService.addListener(createChallengeServiceListener());
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
		PushQueue queue = pendingDraftKitReponses.get(pBox.getId());
		assert (queue != null);
		queue.addRequest(pResponse);
	}

	@Override
	public void addChallengeRequest(AsynchronousResponse pResponse, User pUser) {
		if (!pendingChallengeResponses.containsKey(pUser.getId())) {
			PushQueue queue = new PushQueue();
			pendingChallengeResponses.put(pUser.getId(), queue);
		}
		PushQueue queue = pendingChallengeResponses.get(pUser.getId());
		assert (queue != null);
		queue.addRequest(pResponse);
	}

	@Override
	public void addUnkownRfidRequest(AsynchronousResponse pResponse, Box pBox) {
		if (!pendingUnkownRfidResponses.containsKey(pBox.getId())){
			PushQueue queue = new PushQueue();
			pendingUnkownRfidResponses.put(pBox.getId(), queue);
		}
		PushQueue queue = pendingUnkownRfidResponses.get(pBox.getId());
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

			@Override
			public void onLoginFailed(Box pBox, LoginFailureReason pReason, long pTag) {
				if (pReason == LoginFailureReason.INVALID_RFID_TAG) {
					pushInvalidRfidTag(pBox, pTag);
				}
			}

		};
	}

	private ChallengeServiceListener createChallengeServiceListener() {
		return new ChallengeServiceListener() {

			@Override
			public void onPendingChallengeCreated(Challenge pChallenge) {
				pushPendingChallenge(pChallenge);
			}

			@Override
			public void onChallengeStarted(Challenge pChallenge) {
				pushChallengeStarted(pChallenge);
			}

			@Override
			public void onChallengeFinished(Challenge pChallenge) {
			}

			@Override
			public void onChallengeDeclined(Challenge pChallenge) {
				pushChallengeDeclined(pChallenge);
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

	private void pushPendingChallenge(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;
			User challengee = challenge1v1.getUser2();
			if (pendingChallengeResponses.containsKey(challengee.getId())) {
				PushQueue queue = pendingChallengeResponses.get(challengee
						.getId());

				ChallengeRequestResponse entity = new ChallengeRequestResponse();
				User challenger = challenge1v1.getUser1();
				entity.setChallengerUserName(challenger.getName());
				entity.setChallengerImagePath(challenger.getImagePath());
				entity.setChallengerUserId(challenger.getId());
				entity.setChallengeId(challenge1v1.getId());

				Response response = Response.ok(entity).build();
				queue.push(response, true);
			} // else nobody is interested in this
		}
	}

	private void pushChallengeStarted(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			pChallenge = (Challenge) session.load(
					Zapfmaster2000Package.eINSTANCE.getChallenge().getName(),
					pChallenge.getId());

			ChallengeAcceptedReponse entity = new ChallengeAcceptedReponse();
			entity.setUser1Id(challenge1v1.getUser1().getId());
			entity.setUser1Name(challenge1v1.getUser1().getName());
			entity.setUser2Id(challenge1v1.getUser2().getId());
			entity.setUser2Name(challenge1v1.getUser2().getName());

			tx.commit();

			Response response = Response.ok(entity).build();

			if (pendingChallengeResponses.containsKey(challenge1v1.getUser1()
					.getId())) {
				PushQueue queue = pendingChallengeResponses.get(challenge1v1
						.getUser1().getId());
				queue.push(response, true);
			}
			if (pendingChallengeResponses.containsKey(challenge1v1.getUser2()
					.getId())) {
				PushQueue queue = pendingChallengeResponses.get(challenge1v1
						.getUser2().getId());
				queue.push(response, true);
			}
		}
	}

	private void pushChallengeDeclined(Challenge pChallenge) {
		if (pChallenge instanceof Challenge1v1) {
			Challenge1v1 challenge1v1 = (Challenge1v1) pChallenge;

			Session session = Zapfmaster2000Core.INSTANCE
					.getTransactionService().getSessionFactory()
					.getCurrentSession();
			Transaction tx = session.beginTransaction();
			pChallenge = (Challenge) session.load(
					Zapfmaster2000Package.eINSTANCE.getChallenge().getName(),
					pChallenge.getId());

			ChallengeDeclinedReponse entity = new ChallengeDeclinedReponse();
			entity.setUser1Id(challenge1v1.getUser1().getId());
			entity.setUser1Name(challenge1v1.getUser1().getName());
			entity.setUser2Id(challenge1v1.getUser2().getId());
			entity.setUser2Name(challenge1v1.getUser2().getName());

			tx.commit();

			Response response = Response.ok(entity).build();

			if (pendingChallengeResponses.containsKey(challenge1v1.getUser1()
					.getId())) {
				PushQueue queue = pendingChallengeResponses.get(challenge1v1
						.getUser1().getId());
				queue.push(response, true);
			}
			if (pendingChallengeResponses.containsKey(challenge1v1.getUser2()
					.getId())) {
				PushQueue queue = pendingChallengeResponses.get(challenge1v1
						.getUser2().getId());
				queue.push(response, true);
			}
		}
	}
	
	private void pushInvalidRfidTag(Box pBox, long pTag) {
		if (pendingUnkownRfidResponses.containsKey(pBox.getId())) {
			PushQueue queue = pendingUnkownRfidResponses.get(pBox.getId());
			
			Response response = Response.ok(pTag).build();
			queue.push(response, false);
		} // else nobody cares
	}

}
