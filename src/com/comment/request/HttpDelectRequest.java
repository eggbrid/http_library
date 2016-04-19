package com.comment.request;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.comment.model.HttpDelectRequestModel;
import com.comment.model.HttpResultModel;
import com.comment.other.HttpHelperConstant;

public class HttpDelectRequest extends HttpRequestBase {
	public void executeGet(HttpDelectRequestModel requestModel, int flag,Map<String,String> attributes) {
		String result = null;
		BufferedReader reader = null;
		try {
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params,
					requestModel.getConnectionTimeout()); // 设置连接超时
			HttpConnectionParams.setSoTimeout(params,
					requestModel.getSoTimeout()); // 设置请求超时
			HttpClient client = new DefaultHttpClient(params);
			HttpDelete request = new HttpDelete(requestModel.getUrl()+requestModel.getDelectParams().toString());
			HttpResponse response = client.execute(request);
			int res = response.getStatusLine().getStatusCode();

			if (res == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					reader = new BufferedReader(new InputStreamReader(response
							.getEntity().getContent()));
					StringBuffer strBuffer = new StringBuffer("");
					String line = null;
					while ((line = reader.readLine()) != null) {
						strBuffer.append(line);
					}
					result = strBuffer.toString();
					if (result == null || result.equals("")) {
						setResultModel(new HttpResultModel(
								HttpHelperConstant.HTTP_BACK_ERROR, null,
								"返回数据为空", flag,requestModel.getClasss(),attributes));
						return;
					}

				} else {
					setResultModel(new HttpResultModel(
							HttpHelperConstant.HTTP_BACK_ERROR, null, "返回数据为空",
							flag,requestModel.getClasss(),attributes));
					return;
				}

			} else {
				setResultModel(new HttpResultModel(
						HttpHelperConstant.HTTP_BACK_ERROR, null, "请求错误，错误码："
								+ res, flag,requestModel.getClasss(),attributes));
				return;
			}
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_BACK_SUCCESS, result, "ok", flag,requestModel.getClasss(),attributes));

		} catch (SocketTimeoutException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_TIME_OUT, null, e.toString(),
					flag,requestModel.getClasss(),attributes));
		} catch (UnsupportedEncodingException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_ENCODING_ERROR, null,
					e.toString(), flag,requestModel.getClasss(),attributes));
		} catch (ClientProtocolException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_PROTOCOL_ERROR, null,
					e.toString(), flag,requestModel.getClasss(),attributes));
		} catch (IOException e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_IO_ERROR, null, e.toString(),
					flag,requestModel.getClasss(),attributes));
		} catch (Exception e) {
			setResultModel(new HttpResultModel(
					HttpHelperConstant.HTTP_BACK_ERROR, null, e.toString(),
					flag,requestModel.getClasss(),attributes));
		} finally {
			if (reader != null) {
				try {
					reader.close();
					reader = null;
				} catch (IOException e) {
					setResultModel(new HttpResultModel(
							HttpHelperConstant.HTTP_BACK_ERROR, null, e.toString(),
							flag,requestModel.getClasss(),attributes));
				}
			}
		}
	
	}
}
