package edu.greenriver.it.starting_threads;

import java.time.LocalDateTime;

public class ThreadNames {

	public static void main(String[] args) 
	{
		Thread thread = new Thread(new Runnable() {  //an anonymous class
			public void run()
			{
				//print out the time
				System.out.println(LocalDateTime.now());
				
				//print out the name of the thread
				System.out.println(Thread.currentThread().getName());
			}
		}, "time printer thread");
		thread.start();
		
		Thread.currentThread().setName("main thread");
		System.out.println(Thread.currentThread().getName());
	}

}
