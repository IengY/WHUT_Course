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
		case "zykxk":return "רҵ��ѡ��";
		case "yytykxk":return "Ӣ��������ѡ��";
		case "tqxk":return "��ǰѡ��";
		case "gxxk":return "����ѡ��";
		case "gxkxk":return "��ѡ��ѡ��";
		case "cxkxk":return "���޿�ѡ��";
		case "bxkxk":return "���޿�ѡ��";
		}
		return null;
	}
	public static String getTable(String table)
	{
		switch(table)
		{
		case "רҵ��ѡ��":return "zykxk";
		case "Ӣ��������ѡ��":return "yytykxk";
		case "��ǰѡ��":return "tqxk";
		case "����ѡ��":return "gxxk";
		case "��ѡ��ѡ��":return "gxkxk";
		case "���޿�ѡ��":return "cxkxk";
		case "���޿�ѡ��":return "bxkxk";
		}
		return null;
	}
}
