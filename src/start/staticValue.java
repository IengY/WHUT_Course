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
}
