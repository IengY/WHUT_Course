package requests;
import com.google.gson.Gson;

public class Course{
	public String 课程名称;
	public String 上课老师;
	public String 上课时间;
	public String 上课地点;
	public String 起止周;
	public int 容量;
	public int 选上;
	public int 本轮已选;
	public String 选课方式;
	public double 学分;
	public String 备注;
	public String 双语等级;
	public String add_id;
	public String addAction;
	public static int getC()
	{
		return 13;
	}
	public static void main(String args[])
	{
		String jsonStr="{\r\n" + 
				"		\"课程名称\": \"计算机组成原理D\", \r\n" + 
				"		\"上课老师\": \"田小华\", \r\n" + 
				"		\"上课时间\": \"周二第3-4节{第01-16周};周四第3-4节{第01-16单周}\",\r\n" + 
				"		 \"上课地点\": \"新1-409;新1-309\", \r\n" + 
				"		 \"起止周\": \"01-16\",\r\n" + 
				"		  \"容量\": \"130\", \r\n" + 
				"		  \"选上\": \"122\", \r\n" + 
				"		  \"本轮已选\": \"0\", \r\n" + 
				"		  \"选课方式\": \"推荐课程 \",\r\n" + 
				"		   \"学分\": \"4\", \r\n" + 
				"		   \"备注\": \"jz2-2\", \r\n" + 
				"		   \"双语等级\": \"0 \"\r\n" + 
				"	}";
		Gson gson = new Gson();
		Course course= new Course();
		course = gson .fromJson(jsonStr, Course.class);
		//System.out.println(course.上课地点);
	}
}