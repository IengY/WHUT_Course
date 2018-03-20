package start;
import java.util.List;

import client.*;
import requests.TimetableCourse;
import requests.User;
public class staticValue {
	public static User user;
	public static List<TimetableCourse>timetableCoursesList;
	public static String basePath="src\\cache";
	public static String selectCourseBasePath="src\\cache\\course";
	public static String timetableJsonPath=basePath+"\\timetable.json";
	public static String dataBasePath="src\\database\\Course.db";
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
}
