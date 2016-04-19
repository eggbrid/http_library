package com.comment.https;

import java.util.Map;

import com.comment.interfaces.HttpsCallBack;
import com.comment.model.HttpPostRequestModel;
import com.comment.model.HttpPutRequestModel;
import com.comment.request.HttpPostRequest;
import com.comment.request.HttpPutRequest;

public class HttpPutThread implements Runnable {
	private HttpPutRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private Map<String,String> attributes;
	private int flag;
	public HttpPutThread(HttpPutRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}
	@Override
	public void run() {
		HttpPutRequest postRequest = new HttpPutRequest();
		postRequest.executePost(httpRequestModel, flag,attributes);
		httpsCallBack.back(postRequest.getResultModel());
	}
	
}
