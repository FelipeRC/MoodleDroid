package com.felipetcc.moodledroid.network;

import java.util.HashMap;
import java.util.Map;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class NetworkQueue extends Application {

	public static final String TAG = NetworkQueue.class.getSimpleName();

	private static NetworkQueue mInstance = null;
	private static Context mCtx;

	// Fila de execução
	private RequestQueue mRequestQueue;

	private NetworkQueue(Context context) {
		mCtx = context;
		mRequestQueue = getRequestQueue();
	}

	public static synchronized NetworkQueue getInstance(Context context) {

		if (mInstance == null) {
			mInstance = new NetworkQueue(context);
		}
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley
					.newRequestQueue(mCtx.getApplicationContext());
		}
		return mRequestQueue;
	}

	private <T> void addToRequestQueue(Request<T> request, String tag) {
		request.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(request);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}

	public void doStringRequestByPOST(String url, String tag,
			final IRequestCallback<String> requestCallback, final Map<String, String> params) {

		StringRequest strReq = new StringRequest(Method.POST, url,
				new Response.Listener<String>() {

					@Override
					public void onResponse(String response) {
						Log.d(TAG, response.toString());
						notifyOnResponse(response, requestCallback);
					}
				}, new Response.ErrorListener() {

					@Override
					public void onErrorResponse(VolleyError error) {
						VolleyLog.d(TAG, "Error: " + error.getMessage());
						notifyOnErrorResponse(error, requestCallback);
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				return params;
			}

		};

		// Adding request to request queue
		addToRequestQueue(strReq, tag);
	}

	private <T> void notifyOnResponse(T response,
			IRequestCallback<T> networkRequestCallback) {
		if (networkRequestCallback != null) {
			networkRequestCallback.onRequestResponse(response);
		}
	}

	private <T> void notifyOnErrorResponse(Exception error,
			IRequestCallback<T> networkRequestCallback) {
		if (networkRequestCallback != null) {
			networkRequestCallback.onRequestError(error);
		}
	}

}
