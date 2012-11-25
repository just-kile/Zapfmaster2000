package de.kile.zapfmaster2000.rest.core.push;

import de.kile.zapfmaster2000.rest.api.news.DrawingNewsResponse;

public class DrawingNewsUpdateResponse extends DrawingNewsResponse {

	private RefreshType refreshType;

	public RefreshType getRefreshType() {
		return refreshType;
	}

	public void setRefreshType(RefreshType refreshType) {
		this.refreshType = refreshType;
	}

	public enum RefreshType {
		NEW, REFRESH
	}

}
