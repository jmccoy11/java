package greenriver.it.edu.synchronization;

public class TestSynchronization 
{
	private static final int NUM_THREADS = 10;
	
	public static void main(String[] args) 
	{
		SharedData data = new SharedData(0);
		
		try
		{
			WorkerThread[] threads = new WorkerThread[NUM_THREADS];
			
			//create our threads
			for(int i = 0; i < NUM_THREADS; i++)
			{
				threads[i] = new WorkerThread(data);
			}
			
			//start our threads
			for(int i = 0; i < NUM_THREADS; i++)
			{
				threads[i].start();
			}
			
			//wait until they complete
			for(int i = 0; i < NUM_THREADS; i++)
			{
				threads[i].join();
			}
		}
				
		catch (InterruptedException e) 
		{
			//do nothing
		}
		
		//print out our data (should be 150,000)
		System.out.println(data.getData());
	}

}
