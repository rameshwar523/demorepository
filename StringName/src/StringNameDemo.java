
public class StringNameDemo {

	public String getStudentName(String name)
	{
		return name;
	}
	public static void main(String[] args) {
	StringNameDemo obj=new StringNameDemo();
	String s=obj.getStudentName("Rameshwar");
	System.out.println("My Name is "+s);
	

	}

}
