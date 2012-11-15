package de.beeromat.core.request;

import org.apache.commons.httpclient.methods.PostMethod;

/**
 * Used to set post request to the beeromat website.
 * 
 * @author thomas
 */
public interface RequestExecutor {

	public PostMethod createPostMethod(RequestType pType);

	public void executePost(PostMethod pPostMethod);

}
