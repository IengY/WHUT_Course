package requests;

import java.io.BufferedReader;
import java.io.DataInput;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class ParseMapFile {
	HashMap<String, String>hashMap=new HashMap();
	List<CourseTypeList>list=new ArrayList<CourseTypeList>();
	public ParseMapFile(String path) throws IOException {
		// TODO Auto-generated constructor stub
		File file=new File(path+"\\"+"map_file.json");
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
		hashMap=gson.fromJson(builder.toString(), hashMap.getClass());
		for(String key:hashMap.keySet())
		{
			list.add(new CourseTypeList(key,hashMap.get(key)));
		}
	}
	
	public List<CourseTypeList> getList()
	{
		return this.list;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
