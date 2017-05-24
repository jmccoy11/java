package greenriver.it.edu.synchronization;

import java.util.ArrayList;

public class ThreadSafeList<T> 
{
	//store internally an unsafe list
	private ArrayList<T> data; //this is not safe!!!
	
	public ThreadSafeList()
	{
		data = new ArrayList<T>();
	}
	
	public boolean add(T element)
	{
		synchronized (data)
		{
			return data.add(element);
		}
	}
	
	public boolean remove(T element)
	{
		synchronized (data)
		{
			return data.remove(element);
		}
	}
	
	public void clear()
	{
		synchronized (data)
		{
			data.clear();
		}
	}
	
	public int size()
	{
		synchronized (data)
		{
			return data.size();
		}
	}
	
	public T get(int index)
	{
		synchronized (data)
		{
			return data.get(index);
		}
	}
}
