package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import start.staticValue;

public class ParseTimetable {
	public static List<TimetableCourse> parseTimetable(String json)
	{
		Gson gson =new Gson();
		JsonArray array = new JsonParser().parse(json).getAsJsonArray();
		List<TimetableCourse>list = new ArrayList();
		for (final JsonElement elem: array)
		{
			list.add(gson.fromJson(elem, TimetableCourse.class));
		}
		return list;
		
	}
	public static List<TimetableCourse> parseDefaultTimetable() throws IOException
	{
		StringBuilder jsonBuilder=new StringBuilder();
		File file=new File(staticValue.timetableJsonPath);
		FileInputStream fileInputStream=new FileInputStream(file);
		InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
		BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
		String strTemp="";
		while((strTemp=bufferedReader.readLine())!=null)
		{
			jsonBuilder.append(strTemp);
		}
		String json=jsonBuilder.toString();
		return parseTimetable(json);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String json="[\r\n" + 
				"		{\r\n" + 
				"			\"name\": \"\\u901a\\u7528\\u5b66\\u672f\\u82f1\\u8bed\", \r\n" + 
				"			\"id\": {\"row\": 1, \"col\": 1}, \r\n" + 
				"			\"start_time\": 1, \"end_time\": 16, \r\n" + 
				"			\"location\": \"\\u65b04-301\", \r\n" + 
				"			\"teacher\": \"\\u4e07\\u5b5c\\u8001\\u5e08\",\r\n" + 
				"			 \"isDivide\": 0\r\n" + 
				"		},\r\n" + 
				"		{\r\n" + 
				"			\"name\": \"\\u901a\\u7528\\u5b66\\u672f\\u82f1\\u8bed\", \r\n" + 
				"			\"id\": {\"row\": 1, \"col\": 1}, \r\n" + 
				"			\"start_time\": 1, \"end_time\": 16, \r\n" + 
				"			\"location\": \"\\u65b04-301\", \r\n" + 
				"			\"teacher\": \"\\u4e07\\u5b5c\\u8001\\u5e08\",\r\n" + 
				"			 \"isDivide\": 0\r\n" + 
				"		}\r\n" + 
				"]";
		parseTimetable(json);
	}

}
