package requests;

public class CourseTypeList
{
	public String name;
	public String key;
	public CourseTypeList(String name,String key) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.key=key;
	}
	public String toString()
	{
		return this.name;
	}
}