package de.zapfmaster2000.webservice.challenge;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.httpclient.methods.PostMethod;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;

import de.zapfmaster2000.webservice.core.Zapfmaster2000Core;
import de.zapfmaster2000.webservice.db.SessionFactoryUtil;
import de.zapfmaster2000.webservice.model.db.Challenge;
import de.zapfmaster2000.webservice.model.db.Challenge1v1;
import de.zapfmaster2000.webservice.model.db.ChallengeParticipant;
import de.zapfmaster2000.webservice.model.db.News;
import de.zapfmaster2000.webservice.request.RequestExecutor;
import de.zapfmaster2000.webservice.request.RequestType;

public class ChallengeManagerImpl implements ChallengeManager {

	private final List<ChallengeManagerListener> listeners = new ArrayList<ChallengeManagerListener>();

	@Override
	public void createChallenge1v1(int pUser1, int pUser2, int pDuration) {
		SessionFactory factory = SessionFactoryUtil.getInstance();
		Session session = factory.openSession();
		Transaction transaction = session.beginTransaction();
		
		// 1st: create the challenge
		Challenge challenge = new Challenge();
		challenge.setType(ChallengeConstants.CHALLENGE_TYPE_1V1);
		session.save(challenge);
		
		// 2nd: Create participants
		ChallengeParticipant participant1 = new ChallengeParticipant();
		participant1.setUserId(pUser1);
		participant1.setTeam(0);
		participant1.setChallengeId(challenge.getChallengeId());
		session.save(participant1);
		
		ChallengeParticipant participant2 = new ChallengeParticipant();
		participant2.setUserId(pUser2);
		participant2.setTeam(1);
		participant2.setChallengeId(challenge.getChallengeId());	
		session.save(participant2);
		
		// 3rd: Create 1v1 challenge
		Challenge1v1 challenge1v1 = new Challenge1v1();
		challenge1v1.setChallengeId(challenge.getChallengeId());
		challenge1v1.setDuration(pDuration);
		challenge1v1.setStartTime(new Date());
		session.save(challenge1v1);
		
		// 4rd: Create News
		News news = new News();
		news.setNewsType("CHALLENGE_STARTED");
		news.setContents(String.valueOf(challenge.getChallengeId()));
		news.setImagePath("foo");
		session.merge(news);
		

		
		transaction.commit();
		session.close();
		
		
		
		// 5th: Send request (push update on web-site)
		RequestExecutor executor = Zapfmaster2000Core.getInstance()
				.getRequestExecutor();
		PostMethod method = executor.createPostMethod(RequestType.CHALLENGE_STARTED);
		method.setParameter("id", String.valueOf(challenge.getChallengeId()));
		executor.executePost(method);		
	}

	@Override
	public void declineChallenge(int pUser, String pText) {

	}

	@Override
	public void addChallangeManagerListener(ChallengeManagerListener pListener) {
		listeners.add(pListener);
	}

	@Override
	public void removeChallangeManagerListener(
			ChallengeManagerListener pListener) {
		listeners.remove(pListener);
	}

}
