package edu.greenriver.it.producer_consumer;

import java.util.LinkedList;
import java.util.Queue;

public class SharedWordQueue 
{
	//shared fixed-sized que
	private static final int MAX_WORDS = 10;
	private static Queue<String> words = new LinkedList<String>();
	
	//producer will use this method...
	public static void addWord(String word) throws InterruptedException
	{
		synchronized (words)
		{
			while(words.size() == MAX_WORDS)
			{
				words.wait(); //wait until there is room on the queue
			}
			
			words.add(word);
			
			//pass the message that the queue is not empty
			words.notify();
		}
	}
	
	//consumer will use this method...
	public static String getWord() throws InterruptedException
	{
		synchronized (words)
		{
			while(words.isEmpty())
			{
				words.wait();
			}
			
			String word = words.poll(); //retrieve the head of the queue
			words.notify(); //pass the message that the queue has room
			
			return word;
		}
	}
}