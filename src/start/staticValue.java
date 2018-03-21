package start;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import client.*;
import requests.SelectedCourse;
import requests.TimetableCourse;
import requests.User;
public class staticValue {
	public static User user;
	public static List<TimetableCourse>timetableCoursesList;
	public static String basePath="src\\cache";
	public static String selectCourseBasePath="src\\cache\\course";
	public static String timetableJsonPath=basePath+"\\timetable.json";
	public static String dataBasePath="src\\database\\Course.db";
	public static List<SelectedCourse>selectedList=new ArrayList();
	
	//Server
	public static String BASE_API="http://119.23.234.110:80//";
	public static String POST_GETCOURSE_API=BASE_API+"get_classes";
	public static String[] class_type= {
			"zykxk","gxkxk","cxkxk","gxxk","bxkxk","tqxk","yytykxk"
	};
	
	//end server
	public static String getTableName(String table)
	{
		switch(table)
		{
		case "zykxk":return "专业课选课";
		case "yytykxk":return "英语体育课选课";
		case "tqxk":return "提前选课";
		case "gxxk":return "个性选课";
		case "gxkxk":return "公选课选课";
		case "cxkxk":return "重修课选课";
		case "bxkxk":return "必修课选课";
		}
		return null;
	}
	public static String getTable(String table)
	{
		switch(table)
		{
		case "专业课选课":return "zykxk";
		case "英语体育课选课":return "yytykxk";
		case "提前选课":return "tqxk";
		case "个性选课":return "gxxk";
		case "公选课选课":return "gxkxk";
		case "重修课选课":return "cxkxk";
		case "必修课选课":return "bxkxk";
		}
		return null;
	}
}
