package com.felipetcc.moodledroid.network;

public interface IRequestCallback<T> {

	public void onRequestResponse(T response);

	public void onRequestError(Exception error);

}
