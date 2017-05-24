package greenriver.it.edu.synchronization;

public class SharedData 
{
	private Object lock = new Object();
	private int data;
	
	public SharedData(int data)
	{
		this.data = data;
	}
	
	public void increment()
	{
		synchronized (lock)
		{
			data++;
		}
		
//		synchronized (this)
//		{
//			data++;
//		}
		
	}
	
	//Not recommended
/*	public synchronized void increments()
	{
		data++;
	}
*/
	
	public int getData()
	{
		return data;
	}
}
