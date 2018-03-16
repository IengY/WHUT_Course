package requests;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import com.google.gson.Gson;
public class Requests implements Runnable
{
	int sleepTime=2000;//2s请求一次
	public  boolean loop=false;
	private String username=null;
	private String password=null;
	private int loopCount=0;
	private SSOResponse ssoResponse=new SSOResponse();
	public static String loginUrl="http://sso.jwc.whut.edu.cn/Certification/login.do";
	private  CookieStore cookieStore = new BasicCookieStore();
	private static String UA="Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:58.0) Gecko/20100101 Firefox/58.0";
	private String keyinfo;
	public static List<SelectedCourse>list;
	public Requests(User user,List<SelectedCourse>list) throws ClientProtocolException, IOException {
		// TODO Auto-generated constructor stub
		init(user.getUsername(),user.getPassword());
		this.list=list;
	}
	public void reInit() throws ClientProtocolException, IOException
	{
		String type="xs";
		HttpHost proxy = new HttpHost("localhost",8888);
		RequestConfig config=RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
		HttpPost httpPost=new HttpPost(loginUrl);
		httpPost.setHeader("user-Agent",UA);
		List<NameValuePair>PostData=new ArrayList<NameValuePair>();
		PostData.add(new BasicNameValuePair("userName", username));
		PostData.add(new BasicNameValuePair("password", password));
		PostData.add(new BasicNameValuePair("type", type));
		UrlEncodedFormEntity entity=null;
		String courseLogin="http://202.114.90.180/Course/";
		HttpGet httpget=new HttpGet(courseLogin);
		httpget.setHeader("user-Agent",UA);
		httpClient.execute(httpget);
		try {
			entity=new UrlEncodedFormEntity(PostData,"UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
			if(httpResponse.getStatusLine().getStatusCode()==200)
			{
				HttpEntity httpEntity=httpResponse.getEntity();
				BufferedReader reader=new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
				StringBuilder str=new StringBuilder();
				String line=null;
				while((line=reader.readLine())!=null)
				{
					str.append(line);
				}
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void init(String username,String password) throws ClientProtocolException, IOException
	{
		this.username=username;
		this.password=password;
		String type="xs";
		HttpHost proxy = new HttpHost("localhost",8888);
		RequestConfig config=RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
		HttpPost httpPost=new HttpPost(loginUrl);
		httpPost.setHeader("user-Agent",UA);
		List<NameValuePair>PostData=new ArrayList<NameValuePair>();
		PostData.add(new BasicNameValuePair("userName", username));
		PostData.add(new BasicNameValuePair("password", password));
		PostData.add(new BasicNameValuePair("type", type));
		UrlEncodedFormEntity entity=null;
		String courseLogin="http://202.114.90.180/Course/";
	//	RequestConfig config=RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(15000).build();
	//	CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
		HttpGet httpget=new HttpGet(courseLogin);
		try {
			entity=new UrlEncodedFormEntity(PostData,"UTF-8");
			httpPost.setEntity(entity);
			CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
			httpClient.execute(httpget);
			if(httpResponse.getStatusLine().getStatusCode()==200)
			{
				HttpEntity httpEntity=httpResponse.getEntity();
				BufferedReader reader=new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
				StringBuilder str=new StringBuilder();
				String line=null;
				while((line=reader.readLine())!=null)
				{
					str.append(line);
				}
				System.out.println(line);
			}
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/***
	 *
	 * String addUrl   
	 * Boolean loop : is loop?
	 * @throws InterruptedException 
	 *
	 ***/
	private void addCourse() throws InterruptedException
	{
		HttpHost proxy = new HttpHost("localhost",8888);
		Thread.sleep(sleepTime);
		RequestConfig config=RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).setDefaultRequestConfig(config).build();
		for(int i=0;i<list.size();++i)
		{
			System.out.println(list.get(i).course.课程名称);
			String addUrl="http://202.114.90.180/Course/"+list.get(i).addUrl;
			try 
			{
					HttpGet httpAdd=new HttpGet(addUrl);
					httpAdd.setHeader("user-Agent",UA);
					loopCount++;
					CloseableHttpResponse response=httpClient.execute(httpAdd);
					if(response.getStatusLine().getStatusCode()==200)
					{
						HttpEntity httpEntity=response.getEntity();
						BufferedReader reader=new BufferedReader(new InputStreamReader(httpEntity.getContent(), "UTF-8"));
						StringBuilder str=new StringBuilder();
						String line=null;
						while((line=reader.readLine())!=null)
						{
							str.append(line);
						}
						Gson gson = new Gson();
						ssoResponse = gson.fromJson(str.toString(), SSOResponse.class);
						System.out.println(ssoResponse.message);
						list.get(i).message=ssoResponse.message;
						list.get(i).loop++;
					}
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}
	}
	public static void main(String[] args) {

	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while(true)
			{
				addCourse();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
