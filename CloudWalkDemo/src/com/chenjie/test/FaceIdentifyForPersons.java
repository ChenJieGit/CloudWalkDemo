package com.chenjie.test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.chenjie.test.util.Base64EncoderUtil;
import com.chenjie.test.util.CloudWalkUtil;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import sun.misc.BASE64Encoder;

public class FaceIdentifyForPersons {


//1、/face/clustering/face/create 创建人脸
//2、/face/clustering/group/create 创建1个组
//3、/face/clustering/group/addFace 在组中添加人脸 
//4、/face/recog/group/identify 组内进行识别
	//单脸的识别
	public static void main(String[] args) throws ParseException, ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		String app_id = "yqyan1992@163.com";
        String app_secret = "822758072893040060";
        
		String groupId = "test";
		String faceId1 = "chenjie1";
		String faceId2 = "zhanghan";
		String faceId3 = "chenqiuping";
		String faceId4 = "chenqi";
		HttpResponse response = null;
		
		
		//1.创建多张人脸
        String url = "https://thumbnail0.baidupcs.com/thumbnail/1a1d1cf4fbf15aaa04f1793c891c0dc7?fid=188508550-250528-807351338821966&time=1468479600&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-eN64LOvqzQeaIVNKW6kmOj2DlZ8%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=3102935890&dp-callid=0&size=c850_u580&quality=100";
        response = CloudWalkUtil.FaceCreate(app_id, app_secret,faceId1, url, "陈捷1");
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	       
		url = "https://thumbnail0.baidupcs.com/thumbnail/ac67cd84f29106600991e02d0d1c209f?fid=188508550-250528-501695044447465&time=1468479600&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-KJU5mkFPqdFEMBtqmqNf4ae3pKU%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=3102935890&dp-callid=0&size=c850_u580&quality=100";
		response = CloudWalkUtil.FaceCreate(app_id, app_secret,faceId2, url, "张晗");
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        
        url = "https://thumbnail0.baidupcs.com/thumbnail/bf4bffaf32b29e4b16aa086e1fbbf566?fid=188508550-250528-949873880285517&time=1468479600&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-G5gnJzSaRlyrg%2BRiRAi8SkgCURI%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=3102935890&dp-callid=0&size=c850_u580&quality=100";
        response = CloudWalkUtil.FaceCreate(app_id, app_secret,faceId3, url, "陈秋萍");
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        
        url = "https://thumbnail0.baidupcs.com/thumbnail/06764517e7eecd9767132ea739eb9e98?fid=188508550-250528-802865624919926&time=1468483200&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-7ixrHEtsJOyVL%2ByzSR610bJya4w%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=3437147839&dp-callid=0&size=c850_u580&quality=100";
        response = CloudWalkUtil.FaceCreate(app_id, app_secret,faceId4, url, "陈琦");
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
        
        //2.创建一个组
        response = CloudWalkUtil.GroupCreate(app_id, app_secret,groupId, "测试");  
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	
		//3.往组里面添加人脸
        response = CloudWalkUtil.GroupAddFace(app_id, app_secret, faceId1, groupId);
        System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		response = CloudWalkUtil.GroupAddFace(app_id, app_secret, faceId2, groupId);
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		response = CloudWalkUtil.GroupAddFace(app_id, app_secret, faceId3, groupId);
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		response = CloudWalkUtil.GroupAddFace(app_id, app_secret, faceId4, groupId);
		System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
	
		//4.组内进行识别
		
		
		//获取一张合照1,3个人中有2个人
		url = "https://thumbnail0.baidupcs.com/thumbnail/eae3a682d1fd0a8df3c94ad693ac1608?fid=188508550-250528-569234710098760&time=1468494000&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-ZRDosIZY2D9Hpvbsc2n3hT00HMo%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=469335890&dp-callid=0&size=c850_u580&quality=100";
		//人脸检测，得到每一个人的img
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
		System.out.println("共识别出"+imgList.size()+"个人");
		//遍历得到的每一个img
		for (int i = 0; i < imgList.size(); i++) {
			System.out.println("Identify person-"+i);
			response = CloudWalkUtil.GroupIdentify(app_id, app_secret, groupId, imgList.get(i), "3");
			System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		}	
		
		
		//合照2，好多人中有一个人
		url = "https://thumbnail0.baidupcs.com/thumbnail/f902a629e35fea2f932e7bb7c82bd3d4?fid=188508550-250528-487596343239646&time=1468497600&rt=sh&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-pnFHMzCGF7v2vRbudtv9viyJ4NQ%3D&expires=2h&chkv=0&chkbd=0&chkpc=&dp-logid=637222450&dp-callid=0&size=c850_u580&quality=100";
		//人脸检测，得到每一个人的img
		response = CloudWalkUtil.ToolDetect(app_id, app_secret, url, "true");//如果为false，则不会得到
		 
		//获取相应的实体，转换为字符串
		strResult = EntityUtils.toString(response.getEntity());
		//字符串转为json
		jsonObject = JSONObject.fromString(strResult);      
		jsonArray = JSONArray.fromObject(jsonObject.get("faces"));//把String转换为json 		
		imgList = new LinkedList<>();
		for (int i = 0; i < jsonArray.length(); i++) {
			 JSONObject jo = (JSONObject) jsonArray.get(i);
			 imgList.add((String) jo.get("img"));       
		}
		System.out.println("共识别出"+imgList.size()+"个人");
		//遍历得到的每一个img
		for (int i = 0; i < imgList.size(); i++) {
			System.out.println("Identify person-"+i);
			response = CloudWalkUtil.GroupIdentify(app_id, app_secret, groupId, imgList.get(i), "3");
			System.out.println(EntityUtils.toString(response.getEntity(), "utf-8"));
		}	
		
		
	}

}
