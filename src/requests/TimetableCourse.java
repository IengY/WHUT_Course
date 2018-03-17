package requests;

public class TimetableCourse {
	public String name;
	public Id id;
	public int start_time;
	public int end_time;
	public String location;
	public String teacher;
	public int isDivide;
	public boolean isTempShow=false;
	public String toString()
	{
		String str=name+"("+start_time+"-"+end_time+"";
		if(isDivide==1)
		{
			str+="单周";
		}else if(isDivide==2)
		{
			str+="双周";
		}
		str+=")";
		if(isTempShow==true)
		{
			str+="预选课";
		}
		return str;
	}
	public int getR()
	{
		return this.id.row;
	}
	public int getC()
	{
		return this.id.col;
	}
}
class Id
{
	public int row;
	public int col;
}
