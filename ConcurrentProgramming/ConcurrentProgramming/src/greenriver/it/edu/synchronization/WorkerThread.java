package greenriver.it.edu.synchronization;

public class WorkerThread extends Thread
{
	private SharedData data;
	
	public WorkerThread(SharedData data)
	{
		this.data = data;
	}
	
	@Override
	public void run()
	{
		for(int i = 1; i <= 50000; i++)
		{
			data.increment();
		}
	}
}
