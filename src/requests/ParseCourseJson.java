package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import sqliteDatabase.CourseSQLiteJDBC;

public class ParseCourseJson {
	public static List<CourseList> ParseJson(String json)
	{
		List<CourseList>ret=new ArrayList<>();
		JsonArray array=new JsonParser().parse(json).getAsJsonArray();
		Gson gson = new Gson();
		for (final JsonElement elem: array)
		{
			ret.add(gson.fromJson(elem, CourseList.class));
		}
		return ret;
	}
	public static void ParseJsonToDB(String json,String table) throws SQLException
	{
		List<CourseList>addCourseClasses=ParseJson(json);
		for(CourseList item:addCourseClasses)
		{
			CourseSQLiteJDBC.insert(item.classes, table);
		}
	}
	public static void main(String []args) throws IOException, SQLException
	{
		File file=new File("F:\\\\Workspaces\\\\WHUT_Course\\\\src\\\\cache\\\\temp.json");
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
		List<CourseList>addCourseClasses=ParseJson(builder.toString());
		for(CourseList item:addCourseClasses)
		{
			CourseSQLiteJDBC.insert(item.classes, "zykxk");
		}
	}
}
