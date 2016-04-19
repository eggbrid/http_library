package com.comment.https;

import java.util.Map;

import com.comment.interfaces.HttpsCallBack;
import com.comment.model.HttpGetRequestModel;
import com.comment.request.HttpGetRequest;

public class HttpGetThread implements Runnable {
	private HttpGetRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String, String> attributes;

	public HttpGetThread(HttpGetRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String, String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}

	@Override
	public void run() {
		HttpGetRequest getRequest = new HttpGetRequest();
			getRequest.executeGet(httpRequestModel,flag,attributes);
			httpsCallBack.back(getRequest.getResultModel());
	}


	
}
