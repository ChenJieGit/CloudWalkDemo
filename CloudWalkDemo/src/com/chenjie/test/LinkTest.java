package com.chenjie.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.chenjie.test.util.CloudWalkUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class LinkTest {
	public static void main(String[] args) throws Exception{
        //比较两张图片中人脸的相似度
    	DefaultHttpClient httpClient = new DefaultHttpClient();
        String url = "http://120.24.168.188:7000/face/tool/compare";
        HttpPost httpPost = new HttpPost(url);
        
        String app_id = "yqyan1992@163.com";
        String app_secret = "822758072893040060";
        HttpResponse response = null;
        
        
    	//1.获取一张合照
		url = "https://thumbnail0.baidupcs.com/thumbnail/eae3a682d1fd0a8df3c94ad693ac1608?fid=188508550-250528-569234710098760&time=1468494000&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-ZRDosIZY2D9Hpvbsc2n3hT00HMo%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=469335890&dp-callid=0&size=c850_u580&quality=100";
		//2.人脸检测，得到每一个人的img
		response = CloudWalkUtil.ToolDetect(app_id, app_secret, url, "true");//如果为false，则不会得到
		   
        //获取相应的实体，转换为字符串
		String strResult = EntityUtils.toString(response.getEntity());
		//字符串转为json
        JSONObject jsonObject = JSONObject.fromString(strResult);      
		JSONArray jsonArray = JSONArray.fromObject(jsonObject.get("faces"));//把String转换为json 		
		List<String> imgList = new LinkedList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
	         JSONObject jo = (JSONObject) jsonArray.get(i);
	         imgList.add((String) jo.get("img"));       
	    }
		
        
    }
}
