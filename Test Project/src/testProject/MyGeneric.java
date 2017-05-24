package testProject;

public class MyGeneric<T>
{
	private T data;
	
	public MyGeneric(T data)
	{
		this.data = data;
	}
	
	public T getData()
	{
		return data;
	}
	
	public void setData(T data)
	{
		this.data = data;
	}
}
