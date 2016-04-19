package com.comment.https;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.comment.interfaces.HttpsCallBack;
import com.comment.model.HttpDelectRequestModel;
import com.comment.model.HttpGetRequestModel;
import com.comment.model.HttpPostRequestModel;
import com.comment.model.HttpPutRequestModel;
import com.comment.other.HttpSetting;

public class HttpService{
	private static int MAXTHREAD = HttpSetting.maxHttpThread;
	private static ExecutorService executorService = Executors
			.newFixedThreadPool(MAXTHREAD);
	public static void doPost(HttpPostRequestModel httpRequestModel,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		executorService.execute(new HttpPostThread(httpRequestModel, httpsCallBack, flag,attributes));
	}
	
	public static void doPut(HttpPutRequestModel httpRequestModel,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes) {
		executorService.execute(new HttpPutThread(httpRequestModel, httpsCallBack, flag,attributes));
	}
	public static void doGet(HttpGetRequestModel httpRequestModel,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes){
		executorService.execute(new HttpGetThread(httpRequestModel,httpsCallBack,flag,attributes));
	}
	public static void doDelect(HttpDelectRequestModel httpRequestModel,HttpsCallBack httpsCallBack,int flag,Map<String,String> attributes){
		executorService.execute(new HttpDelectThread(httpRequestModel,httpsCallBack,flag,attributes));
	}
	public void cancelAllTasks() {
		executorService.shutdownNow();
		executorService = Executors.newFixedThreadPool(MAXTHREAD);
	}
}
