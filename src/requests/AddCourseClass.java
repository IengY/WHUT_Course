package requests;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

import com.google.gson.Gson;

public class AddCourseClass {
	public String addclass;
	public ArrayList<Course> classes=new ArrayList<Course>();
	public static AddCourseClass ParseCourseClass(String Path) throws  Throwable
	{
		File file=new File(Path);
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
		//
		Gson gson = new Gson();
		AddCourseClass addCourseClass=gson.fromJson(builder.toString(), AddCourseClass.class);
		return addCourseClass;
	}
	public static void main(String args[])
	{
		String jsonStr="{\r\n" + 
				"	\"addclass\": \"zykxkAdd.do?xnxq=2017-2018-2&kcdm=4120044110&jxjhh=20164123&addid={suid_obj}&keyinfo=63928434CC301593D7CC1667AA2EDEEF\", \r\n" + 
				"	\"classes\": \r\n" + 
				"	[\r\n" + 
				"		{\r\n" + 
				"			\"�γ�����\": \"���ӻ����\", \r\n" + 
				"			\"�Ͽ���ʦ\": \"����\",\r\n" + 
				"			 \"�Ͽ�ʱ��\": \"���ĵ�1-2��{��01-15��}\", \r\n" + 
				"			\"�Ͽεص�\": \"��2-410\", \r\n" + 
				"			\"��ֹ��\": \"01-15\", \r\n" + 
				"			\"����\": \"90\", \r\n" + 
				"			\"ѡ��\": \"76\", \r\n" + 
				"			\"������ѡ\": \"0\", \r\n" + 
				"			\"ѡ�η�ʽ\": \"�Ƽ��γ� \", \r\n" + 
				"			\"ѧ��\": \"2.5\", \r\n" + 
				"			\"��ע\": \"jz1-2,NEW\", \r\n" + 
				"			\"˫��ȼ�\": \"0 \"\r\n" + 
				"		}, \r\n" + 
				"		{\r\n" + 
				"			\"�γ�����\": \"���ӻ����\", \r\n" + 
				"			\"�Ͽ���ʦ\": \"����\", \r\n" + 
				"			\"�Ͽ�ʱ��\": \"�ܶ���1-2��{��01-15��}\",\r\n" + 
				"			 \"�Ͽεص�\": \"��3-313\", \r\n" + 
				"			\"��ֹ��\": \"01-15\", \r\n" + 
				"			\"����\": \"60\",\r\n" + 
				"			 \"ѡ��\": \"48\", \r\n" + 
				"			\"������ѡ\": \"0\", \r\n" + 
				"			\"ѡ�η�ʽ\": \"�Ƽ��γ� \", \r\n" + 
				"			\"ѧ��\": \"2.5\", \r\n" + 
				"			\"��ע\": \"jz1-2\", \r\n" + 
				"			\"˫��ȼ�\": \"0 \"\r\n" + 
				"		}\r\n" + 
				"	]\r\n" + 
				"}";
			Gson gson = new Gson();
			AddCourseClass addCourseClass = new AddCourseClass();
			addCourseClass = gson.fromJson(jsonStr, AddCourseClass.class);
			//System.out.println(addCourseClass.classes.get(0).�Ͽεص�);
		
		
	}
}
