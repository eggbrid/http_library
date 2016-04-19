package com.comment.https;

import java.util.Map;

import com.comment.interfaces.HttpsCallBack;
import com.comment.model.HttpDelectRequestModel;
import com.comment.request.HttpDelectRequest;

public class HttpDelectThread implements Runnable {
	private HttpDelectRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String, String> attributes;

	public HttpDelectThread(HttpDelectRequestModel requestModel,
			HttpsCallBack httpsCallBack, int flag,
			Map<String, String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack = httpsCallBack;
		this.flag = flag;
	}

	@Override
	public void run() {
		HttpDelectRequest getRequest = new HttpDelectRequest();
		getRequest.executeGet(httpRequestModel, flag,attributes);
		httpsCallBack.back(getRequest.getResultModel());
	}

}
