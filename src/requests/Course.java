package requests;
import com.google.gson.Gson;

public class Course{
	public String �γ�����;
	public String �Ͽ���ʦ;
	public String �Ͽ�ʱ��;
	public String �Ͽεص�;
	public String ��ֹ��;
	public int ����;
	public int ѡ��;
	public int ������ѡ;
	public String ѡ�η�ʽ;
	public double ѧ��;
	public String ��ע;
	public String ˫��ȼ�;
	public String add_id;
	public String addAction;
	public static int getC()
	{
		return 13;
	}
	public static void main(String args[])
	{
		String jsonStr="{\r\n" + 
				"		\"�γ�����\": \"��������ԭ��D\", \r\n" + 
				"		\"�Ͽ���ʦ\": \"��С��\", \r\n" + 
				"		\"�Ͽ�ʱ��\": \"�ܶ���3-4��{��01-16��};���ĵ�3-4��{��01-16����}\",\r\n" + 
				"		 \"�Ͽεص�\": \"��1-409;��1-309\", \r\n" + 
				"		 \"��ֹ��\": \"01-16\",\r\n" + 
				"		  \"����\": \"130\", \r\n" + 
				"		  \"ѡ��\": \"122\", \r\n" + 
				"		  \"������ѡ\": \"0\", \r\n" + 
				"		  \"ѡ�η�ʽ\": \"�Ƽ��γ� \",\r\n" + 
				"		   \"ѧ��\": \"4\", \r\n" + 
				"		   \"��ע\": \"jz2-2\", \r\n" + 
				"		   \"˫��ȼ�\": \"0 \"\r\n" + 
				"	}";
		Gson gson = new Gson();
		Course course= new Course();
		course = gson .fromJson(jsonStr, Course.class);
		//System.out.println(course.�Ͽεص�);
	}
}