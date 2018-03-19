package sqliteDatabase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import requests.AddCourseClass;
import requests.Course;
import start.staticValue;

public class CourseSQLiteJDBC {
	public static Connection courseConnection=null;
	static {
		 try {
			Class.forName("org.sqlite.JDBC");
			courseConnection=DriverManager.getConnection("jdbc:sqlite:"+staticValue.dataBasePath);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void insert(List<Course>list,String table) throws SQLException
	{
		String sql="INSERT INTO "+table+" (课程名称,上课老师,上课时间,上课地点,容量,选上,本轮已选,选课方式,备注,学分,双语等级,add_id,addclass) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement=courseConnection.prepareStatement(sql);
		for (Course course:list)
		{
			preparedStatement.setObject(1, course.课程名称);
			preparedStatement.setObject(2, course.上课老师);
			preparedStatement.setObject(3, course.上课时间);
			preparedStatement.setObject(4, course.上课地点);
			preparedStatement.setObject(5, course.容量);
			preparedStatement.setObject(6, course.选上);
			preparedStatement.setObject(7, course.本轮已选);
			preparedStatement.setObject(8, course.选课方式);
			preparedStatement.setObject(9, course.备注);
			preparedStatement.setObject(10, course.学分);
			preparedStatement.setObject(11, course.双语等级);
			preparedStatement.setObject(12, course.add_id);
			preparedStatement.setObject(13, course.addclass);
			preparedStatement.execute();
		}
		return ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AddCourseClass addCourseClass =AddCourseClass.ParseCourseClass("F:\\Workspaces\\WHUT_Course\\src\\cache\\course\\专业选课\\4120265140.json");
			insert(addCourseClass.classes, "zykxk");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
