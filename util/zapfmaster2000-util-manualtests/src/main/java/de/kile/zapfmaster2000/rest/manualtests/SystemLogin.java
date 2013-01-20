package de.kile.zapfmaster2000.rest.manualtests;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * Hello world!
 * 
 */
public class SystemLogin {
	public static void main(String[] args) throws ClientProtocolException,
			IOException {

		DefaultHttpClient httpclient = new DefaultHttpClient();
		// login via post
		HttpPost post = new HttpPost(
				"http://localhost:8080/Zapfmaster2000RESTful/login/box");
		post.addHeader("accept", "application/json");

		StringEntity input = new StringEntity(
				"{\"name\":\"zapfmaster-box-1\",\"passphrase\":\"Y4SYg95B40AEbCblc7T1eSKM2JEOgdZ1\"}");
		input.setContentType("application/json");
		post.setEntity(input);
		HttpResponse response = httpclient.execute(post);
		EntityUtils.consume(response.getEntity());
		
		
		//
		// // check if cookie was set
		// List<Cookie> cookies = httpclient.getCookieStore().getCookies();
		// System.out.println("Cookies: " + cookies.size());
		// for (Cookie cookie : cookies) {
		// System.out.println(cookie.getName());
		// }

		// check if logged in
//		HttpGet get = new HttpGet(
//		 "http://localhost:8080/Zapfmaster2000RESTful/login");
//		 get.addHeader("accept", "application/json");
//
//
//
//		 HttpResponse response = httpclient.execute(get);

		System.out.println("done");

	}
}
