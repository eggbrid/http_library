package com.comment.https;

import java.util.Map;

import com.comment.interfaces.HttpsCallBack;
import com.comment.model.HttpPostRequestModel;
import com.comment.request.HttpPostFileRequest;
import com.comment.request.HttpPostRequest;

public class HttpPostThread implements Runnable {
	private HttpPostRequestModel httpRequestModel;
	private HttpsCallBack httpsCallBack;
	private int flag;
	private Map<String,String> attributes;
	public HttpPostThread(HttpPostRequestModel requestModel ,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		this.httpRequestModel = requestModel;
		this.httpsCallBack=httpsCallBack;
		this.flag=flag;
		this.attributes=attributes;
	}
	@Override
	public void run() {
		if (httpRequestModel.getPostPrams().isHasFile()) {
			HttpPostFileRequest postRequest = new HttpPostFileRequest();
			postRequest.executePost(httpRequestModel, flag,attributes);
			httpsCallBack.back(postRequest.getResultModel());
		}else{
			HttpPostRequest postRequest = new HttpPostRequest();
			postRequest.executePost(httpRequestModel, flag,attributes);
			httpsCallBack.back(postRequest.getResultModel());
		}

	}
	
}
