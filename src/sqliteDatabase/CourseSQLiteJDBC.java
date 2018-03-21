package sqliteDatabase;

import java.beans.Statement;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.spi.DirStateFactory.Result;

import com.google.gson.Gson;

import requests.CourseList;
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
		String sql="INSERT INTO "+table+" (�γ�����,�Ͽ���ʦ,�Ͽ�ʱ��,�Ͽεص�,����,ѡ��,������ѡ,ѡ�η�ʽ,��ע,ѧ��,˫��ȼ�,add_id,addAction) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
			preparedStatement.setObject(13, course.addAction);
			preparedStatement.execute();
		}
		return ;
	}
	public static List<String> getCourseList(String table) throws SQLException
	{
		String sql="SELECT DISTINCT �γ����� FROM "+table;
		List<String>courseList = new ArrayList();
		System.out.println("DB:getCourseList table:"+table);
		PreparedStatement stmt=courseConnection.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery();
		while(rs.next())
		{
			courseList.add(rs.getString("�γ�����"));
		}
		return courseList;
	}
	public static List<String> getTypeList() throws SQLException
	{
		List<String>tableList=new ArrayList();
		DatabaseMetaData md = courseConnection.getMetaData();
		ResultSet rs = md.getTables(null, null, "%", null);
		while(rs.next())
		{
			tableList.add(rs.getString(3));
			System.out.println(rs.getString(3));
		}
		return tableList;
	}
	public static List<Course> getCourse(String courseName,String table) throws SQLException
	{
		String sql="SELECT * FROM "+table+" WHERE \"�γ�����\" = \""+courseName+"\"";
		List<Course>courseList=new ArrayList();
		PreparedStatement preparedStatement = courseConnection.prepareStatement(sql);
		ResultSet rs=preparedStatement.executeQuery();
		while(rs.next())
		{
			Course course = new Course();
			course.�γ�����=rs.getString("�γ�����");
			course.�Ͽ���ʦ=rs.getString("�Ͽ���ʦ");
			course.�Ͽ�ʱ��=rs.getString("�Ͽ�ʱ��");
			course.�Ͽεص�=rs.getString("�Ͽεص�");
			course.����=rs.getInt("����");
			course.ѡ��=rs.getInt("ѡ��");
			course.������ѡ=rs.getInt("������ѡ");
			course.ѡ�η�ʽ=rs.getString("ѡ�η�ʽ");
			course.��ע=rs.getString("��ע");
			course.ѧ��=rs.getDouble("ѧ��");
			course.˫��ȼ�=rs.getString("˫��ȼ�");
			course.add_id=rs.getString("add_id");
			course.addAction=rs.getString("addAction");
			courseList.add(course);
		}
		return courseList;
	}
	public static void main(String[] args) throws Throwable {
		//getTypeList();
	
	}

}
