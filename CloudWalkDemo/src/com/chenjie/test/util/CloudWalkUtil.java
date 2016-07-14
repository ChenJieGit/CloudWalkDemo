package com.chenjie.test.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class CloudWalkUtil {

//	app_id 	app_id
//	app_secret 	app_secret
//	faceId 	face_1
//	url 	http://api.cloudwalk.cn:8081/resources/image/1.jpg
//	tag 	人员1
	/**
	 * 创建人脸
	 * @param app_id
	 * @param app_secret
	 * @param faceId
	 * @param url
	 * @param tag
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse FaceCreate(String app_id,
			String app_secret,
			String faceId,
			String url,
			String tag) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();	
		String FaceCreateUrl = "http://120.24.168.188:7000/face/clustering/face/create";
        HttpPost httpPost = new HttpPost(FaceCreateUrl);
      
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app_id", app_id));
        nvps.add(new BasicNameValuePair("app_secret", app_secret));
        nvps.add(new BasicNameValuePair("url", url));
        nvps.add(new BasicNameValuePair("faceId", faceId));   
        nvps.add(new BasicNameValuePair("tag", tag));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
		return httpClient.execute(httpPost);
	}
//	app_id 	APP账户
//  app_secret 	APP密钥
//  groupId 	应用层传入的标识人脸的唯一字符串(128字节限制)
//  tag 	额外信息(128字节限制)
	/**
	 * 创建组
	 * @param app_id
	 * @param app_secret
	 * @param groupId
	 * @param tag
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse GroupCreate(String app_id,
			String app_secret,
			String groupId,
			String tag) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();	
		String FaceCreateUrl = "http://120.24.168.188:7000/face/clustering/group/create";
        HttpPost httpPost = new HttpPost(FaceCreateUrl);
      
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app_id", app_id));
        nvps.add(new BasicNameValuePair("app_secret", app_secret));
        nvps.add(new BasicNameValuePair("groupId", groupId));
        nvps.add(new BasicNameValuePair("tag", tag));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        return httpClient.execute(httpPost);
	}
	
	
//	app_id 	APP账户
//  app_secret 	APP密钥
//  faceId 	应用层传入的标识人脸的唯一字符串
//  groupId 	应用层传入的标识人脸的唯一字符串
	/**
	 * 往组中添加人脸
	 * @param app_id
	 * @param app_secret
	 * @param faceId
	 * @param groupId
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse GroupAddFace(String app_id,
			String app_secret,
			String faceId,
			String groupId) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();	
		String FaceCreateUrl = "http://120.24.168.188:7000/face/clustering/group/addFace";
        HttpPost httpPost = new HttpPost(FaceCreateUrl);
      
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app_id", app_id));
        nvps.add(new BasicNameValuePair("app_secret", app_secret));
        nvps.add(new BasicNameValuePair("groupId", groupId));
        nvps.add(new BasicNameValuePair("faceId", faceId));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        return httpClient.execute(httpPost);
	}
//	app_id 	APP账户
//  app_secret 	APP密钥
//  groupId 	应用层传入的标识人脸的唯一字符串
//  img 	人脸图片数据(base64编码)
//  topN 	返回N个结果
	/**
	 * 从小组中识别出于img最相识的topN个人
	 * @param app_id
	 * @param app_secret
	 * @param groupId
	 * @param img
	 * @param topN
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static HttpResponse GroupIdentify(String app_id,
			String app_secret,
			String groupId,
			String img,
			String topN) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();	
		String FaceCreateUrl = "http://120.24.168.188:7000/face/recog/group/identify";
        HttpPost httpPost = new HttpPost(FaceCreateUrl);
      
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app_id", app_id));
        nvps.add(new BasicNameValuePair("app_secret", app_secret));
        nvps.add(new BasicNameValuePair("groupId", groupId));
        nvps.add(new BasicNameValuePair("img", img));
        nvps.add(new BasicNameValuePair("topN", topN));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        return httpClient.execute(httpPost);
	}
	
	
	
	
//	必须 	app_id 	APP账户
//  	app_secret 	APP密钥
//  	url 或 img[POST] 	通过POST方法上传的待检测图片的URL或者二进制数据的Base64编码，原始图片大小需要小于20M
//	可选 	mode 	是否返回每张人脸图片true表示返回，false表示不返回
	public static HttpResponse ToolDetect(String app_id,
			String app_secret,
			String url,
			String mode) throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();	
		String FaceCreateUrl = "http://120.24.168.188:7000/face/tool/detect";
        HttpPost httpPost = new HttpPost(FaceCreateUrl);
      
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
       
        List<NameValuePair> nvps = new ArrayList<NameValuePair>();
        nvps.add(new BasicNameValuePair("app_id", app_id));
        nvps.add(new BasicNameValuePair("app_secret", app_secret));
        nvps.add(new BasicNameValuePair("url", url));
        nvps.add(new BasicNameValuePair("mode", mode));
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        return httpClient.execute(httpPost);
	}
}
