package de.justkile.zapfmaster2000.services

import java.security.SecureRandom;

import org.bouncycastle.asn1.x509.UserNotice;

import de.justkile.zapfmaster2000.model.Account;
import de.justkile.zapfmaster2000.model.Admin;
import de.justkile.zapfmaster2000.model.Token;
import de.justkile.zapfmaster2000.model.User;
import grails.transaction.Transactional


@Transactional
class AuthService {

	def SecureRandom secureRandom = new SecureRandom()

	def String loginAccount(String accountName) {
		String token = null;
		def account = Account.findByName(accountName)
		if (account) {
			def rawToken = createNextToken();
			new Token(token: rawToken, account: account).save()
			return rawToken
		} else {
			throw new AuthException("There is no account with name $name")
		}
	}

	def String loginUser(String pUserName, String pPassword) {
		def user = User.where {
			name == pUserName && password == pPassword
		}.find()

		if (user) {
			def rawToken = createNextToken();
            def token = new Token(token: rawToken, user: user, account: user.account)
			token.save()
			return rawToken
		} else {
			throw new AuthException("No user with given credentials found.")
		}
	}

	def String loginAdmin(String adminName, String pPassword) {
		def admin = Admin.where {
			name == adminName && password == pPassword
		}.find()

		if (admin) {
			def rawToken = createNextToken();
			new Token(token: rawToken, admin: admin, account: admin.account).save()
			return rawToken
		} else {
			throw new AuthException("No admin with given credentials found.")
		}
	}

	def Account retrieveAccount(String pToken) {
		def token = Token.findByToken(pToken)
		if (token && token.account) {
			return token.account
		} else {
			throw new AuthException("No account associated to the given token.")
		}
	}

	def User retrieveUser(String pToken) {
		def token = Token.findByToken(pToken)
		if (token && token.user) {
			return token.user
		} else {
			throw new AuthException("No user associated to the given token.")
		}
	}

	def Admin retrieveAdmin(String pToken) {
		def token = Token.findByToken(pToken)
		if (token && token.admin) {
			return token.admin
		} else {
			throw new AuthException("No admin associated to the given token.")
		}
	}

	public String retrieveGoogleCloudMessagingToken(String pToken) {
		def token = Token.findByToken(pToken)
		if (token && token.googleCloudMessagingToken) {
			return token.googleCloudMessagingToken
		} else {
			throw new AuthException("No GoogleCouldMessagingToken associated to the given token.")
		}
	}

	public void setupGoogleCloudMessagingToken(String pToken,
			String googleCloudMessagingToken) throws IllegalArgumentException {
		def token = Token.findByToken(pToken)
		if (token) {
			token.googleCloudMessagingToken = googleCloudMessagingToken
			token.save()
		} else {
			throw new AuthException("Unknown token.")
		}
	}

	private def createNextToken() {
		return new BigInteger(130, secureRandom).toString(32);
	}
}
