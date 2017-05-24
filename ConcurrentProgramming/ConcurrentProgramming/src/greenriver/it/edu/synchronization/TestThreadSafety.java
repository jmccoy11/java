package greenriver.it.edu.synchronization;

//import java.util.ArrayList;
//import java.util.Collections;
//import java.util.List;

public class TestThreadSafety {

	public static void main(String[] args) throws InterruptedException
	{
		ThreadSafeList<String> words = new ThreadSafeList<String>();
		//List<String> words1 = Collections.synchronizedList(new ArrayList<String>());
		
		final int NUM_THREADS = 10;
		
		Thread[] threads = new Thread[NUM_THREADS];
		
		//create threads
		for(int i=0; i < threads.length; i++)
		{
			threads[i] = new Thread(new Runnable() 
			{
				public void run()
				{
					for(int i = 0; i <= 1000; i++)
					{
						words.add("dog");

					}
				}
			});
		}
		
		//start them
		for(int i=0; i < threads.length; i++) {threads[i].start();}
		for(int i=0; i < threads.length; i++) {threads[i].run();}
		
		//print my results
		System.out.println(words.size());
	}

}
