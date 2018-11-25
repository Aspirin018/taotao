package com.taotao.httpclient;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {
	
	@Test
	public void doGet() throws ClientProtocolException, IOException{
		//创建httpclient对象
		CloseableHttpClient httpClient = HttpClients.createDefault();
		//创建get对象
		HttpGet get = new HttpGet("http://www.sogou.com"); 
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//获取响应结果
		int statusCode = response.getStatusLine().getStatusCode();//响应状态码
		HttpEntity entity = response.getEntity();//响应的实体
		String string = EntityUtils.toString(entity, "utf-8");//用apache提供的工具类转换实体为字符串
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doGetWithParam() throws Exception{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		URIBuilder builder = new URIBuilder("http://www.sogou.com/web");
		builder.addParameter("query", "花");
		HttpGet get = new HttpGet(builder.build()); 
		//执行请求
		CloseableHttpResponse response = httpClient.execute(get);
		//获取响应结果
		int statusCode = response.getStatusLine().getStatusCode();//响应状态码
		HttpEntity entity = response.getEntity();//响应的实体
		String string = EntityUtils.toString(entity, "utf-8");//用apache提供的工具类转换实体为字符串
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPost() throws Exception{    
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
		CloseableHttpResponse response = httpClient.execute(post);
		HttpEntity entity = response.getEntity();//响应的实体
		String string = EntityUtils.toString(entity, "utf-8");//用apache提供的工具类转换实体为字符串
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}
	
	@Test
	public void doPostWithParam() throws Exception{       
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://localhost:8082/httpclient/post.action");
		List<NameValuePair> kvList = new ArrayList();
		kvList.add(new BasicNameValuePair("username", "章三"));
		kvList.add(new BasicNameValuePair("password", "123"));
		StringEntity entity = new UrlEncodedFormEntity(kvList, "utf-8");
		post.setEntity(entity);
		CloseableHttpResponse response = httpClient.execute(post);
		String string = EntityUtils.toString(response.getEntity(), "utf-8");//用apache提供的工具类转换实体为字符串
		System.out.println(string);
		//关闭httpclient
		response.close();
		httpClient.close();
	}

}
