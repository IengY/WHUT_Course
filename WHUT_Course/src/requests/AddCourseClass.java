package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddCourseClass {
	public String addclass;
	public ArrayList<Course> classes=new ArrayList<Course>();
	public static AddCourseClass ParseCourseClass(String Path) throws  Throwable
	{
		File file=new File(Path);
		FileInputStream fileInputStream = new FileInputStream(file);
		String str="";
		InputStreamReader in= new InputStreamReader(fileInputStream,"UTF-8");
		BufferedReader bufferedReader = new BufferedReader(in);
		StringBuilder builder = new StringBuilder();
		while((str=bufferedReader.readLine())!=null)
		{
			builder.append(str);
		}
		bufferedReader.close();
		fileInputStream.close();
		//
		System.out.println(builder);
		//
		Gson gson = new Gson();
		AddCourseClass addCourseClass=gson.fromJson(builder.toString(), AddCourseClass.class);
		return addCourseClass;
	}
	public static void main(String args[])
	{
		String jsonStr="{\r\n" + 
				"	\"addclass\": \"zykxkAdd.do?xnxq=2017-2018-2&kcdm=4120044110&jxjhh=20164123&addid={suid_obj}&keyinfo=63928434CC301593D7CC1667AA2EDEEF\", \r\n" + 
				"	\"classes\": \r\n" + 
				"	[\r\n" + 
				"		{\r\n" + 
				"			\"课程名称\": \"可视化编程\", \r\n" + 
				"			\"上课老师\": \"胡伦\",\r\n" + 
				"			 \"上课时间\": \"周四第1-2节{第01-15周}\", \r\n" + 
				"			\"上课地点\": \"新2-410\", \r\n" + 
				"			\"起止周\": \"01-15\", \r\n" + 
				"			\"容量\": \"90\", \r\n" + 
				"			\"选上\": \"76\", \r\n" + 
				"			\"本轮已选\": \"0\", \r\n" + 
				"			\"选课方式\": \"推荐课程 \", \r\n" + 
				"			\"学分\": \"2.5\", \r\n" + 
				"			\"备注\": \"jz1-2,NEW\", \r\n" + 
				"			\"双语等级\": \"0 \"\r\n" + 
				"		}, \r\n" + 
				"		{\r\n" + 
				"			\"课程名称\": \"可视化编程\", \r\n" + 
				"			\"上课老师\": \"胡伦\", \r\n" + 
				"			\"上课时间\": \"周二第1-2节{第01-15周}\",\r\n" + 
				"			 \"上课地点\": \"新3-313\", \r\n" + 
				"			\"起止周\": \"01-15\", \r\n" + 
				"			\"容量\": \"60\",\r\n" + 
				"			 \"选上\": \"48\", \r\n" + 
				"			\"本轮已选\": \"0\", \r\n" + 
				"			\"选课方式\": \"推荐课程 \", \r\n" + 
				"			\"学分\": \"2.5\", \r\n" + 
				"			\"备注\": \"jz1-2\", \r\n" + 
				"			\"双语等级\": \"0 \"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
			Gson gson = new Gson();
			AddCourseClass addCourseClass = new AddCourseClass();
			addCourseClass = gson.fromJson(jsonStr, AddCourseClass.class);
			//System.out.println(addCourseClass.classes.get(0).上课地点);
		
		
	}
}
