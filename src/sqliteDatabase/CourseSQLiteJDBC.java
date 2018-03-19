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
		String sql="INSERT INTO "+table+" (�γ�����,�Ͽ���ʦ,�Ͽ�ʱ��,�Ͽεص�,����,ѡ��,������ѡ,ѡ�η�ʽ,��ע,ѧ��,˫��ȼ�,add_id,addclass) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement=courseConnection.prepareStatement(sql);
		for (Course course:list)
		{
			preparedStatement.setObject(1, course.�γ�����);
			preparedStatement.setObject(2, course.�Ͽ���ʦ);
			preparedStatement.setObject(3, course.�Ͽ�ʱ��);
			preparedStatement.setObject(4, course.�Ͽεص�);
			preparedStatement.setObject(5, course.����);
			preparedStatement.setObject(6, course.ѡ��);
			preparedStatement.setObject(7, course.������ѡ);
			preparedStatement.setObject(8, course.ѡ�η�ʽ);
			preparedStatement.setObject(9, course.��ע);
			preparedStatement.setObject(10, course.ѧ��);
			preparedStatement.setObject(11, course.˫��ȼ�);
			preparedStatement.setObject(12, course.add_id);
			preparedStatement.setObject(13, course.addclass);
			preparedStatement.execute();
		}
		return ;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			AddCourseClass addCourseClass =AddCourseClass.ParseCourseClass("F:\\Workspaces\\WHUT_Course\\src\\cache\\course\\רҵѡ��\\4120265140.json");
			insert(addCourseClass.classes, "zykxk");
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
