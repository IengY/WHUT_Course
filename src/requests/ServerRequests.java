package requests;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import sqliteDatabase.CourseSQLiteJDBC;
import start.staticValue;

public class ServerRequests {
	public static void getServerCourseToDB(String class_type) throws ClientProtocolException, IOException, SQLException
	{
		String sql="DELETE  FROM "+class_type;
		PreparedStatement stmt=CourseSQLiteJDBC.courseConnection.prepareStatement(sql);
		stmt.executeUpdate();
		RequestConfig config=RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(15000).build();
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(config).build();
		String POST_URL=staticValue.POST_GETCOURSE_API;
		List<NameValuePair>nameValuePairs=new ArrayList<NameValuePair>();
		HttpPost httpPost = new HttpPost(POST_URL);
		nameValuePairs.add(new BasicNameValuePair("class_type",class_type));
		nameValuePairs.add(new BasicNameValuePair("userName", staticValue.user.getUsername()));
		nameValuePairs.add(new BasicNameValuePair("password", staticValue.user.getPassword()));
		UrlEncodedFormEntity entity=null;
		entity=new UrlEncodedFormEntity(nameValuePairs,"UTF-8");
		httpPost.setEntity(entity);
		CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
		String json=null;
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
			json=str.toString();
		}
		ParseCourseJson.ParseJsonToDB(json, class_type);
	}
	public static void getCacheCourseToDB() throws ClientProtocolException, IOException, SQLException
	{
		getServerCourseToDB("gxxk");
		getServerCourseToDB("gxkxk");
	}
	public static void getJwcCourseToDB() throws ClientProtocolException, IOException, SQLException
	{
		for(String table:staticValue.class_type)
		{
			getServerCourseToDB(table);
		}
	}
	public static void main(String[] args) throws ClientProtocolException, IOException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
