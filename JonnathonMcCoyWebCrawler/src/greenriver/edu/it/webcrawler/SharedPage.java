/*
 * Jonnathon McCoy
 * March 3, 2017
 * 
 * package: edu.greenriver.it.webcrawler
 * class: SharedPage.java
 * 
 * The SharedPage class stores pages downloaded from URL links in the SharedLink class.
 * 
 * Also keeps track of how many pages failed to download.
 */
package greenriver.edu.it.webcrawler;

import java.util.LinkedList;
import java.util.Queue;

/**
 * The SharedPage class stores pages downloaded from URL links in the SharedLink class.
 * 
 * Also keeps track of how many pages failed to download.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class SharedPage 
{
	//fields
	private static final int MAX_QUEUE_SIZE = 50000;
	
	private static int pagesDownloaded = 0;
	private static int failedDownloads = 0;
	
	private static Queue<String> pages = new LinkedList<String>();
	
	//methods
	/**
	 * Adds a new page to the queue.
	 * Queue should have a maximum size of 50000
	 * if there is no room in the queue, the thread should call wait() on the queue
	 * After adding a new page to the queue, you should call notify();
	 * 
	 * @param pageText - String of entire downloaded page
	 * @throws InterruptedException - Checked exception in case the thread is interrupted.
	 */
	public static void addPage(String pageText) throws InterruptedException
	{
		synchronized(pages)
		{
			if(pages.size() == MAX_QUEUE_SIZE)
			{
				//System.out.println("SharedPage addPage() MAX SIZE reached ... waiting");
				pages.wait();
			}
			
			pages.add(pageText);
			pagesDownloaded++;
			
			//System.out.println("Shared Page added");
			pages.notify();
		}
	}
		
	/**
	 * Returns a page from the queue
	 * After removing a page from the queue, you should call notify() on the queue before
	 * returning the page text
		
	 * @return  - String of entire downloaded page
	 * @throws InterruptedException - Checked exception in case the thread is interrupted.
	 */
	public static String getNextPage() throws InterruptedException
	{
		synchronized(pages)
		{
			if(pages.isEmpty())
			{
				//System.out.println("SharedPage getNextPage() isEmpty ... waiting");
				pages.wait();
			}
			String nextPage = pages.poll();
			
			//remove the head
			pages.remove(pages.poll());
			
			pages.notify();
			
			//System.out.println("SharedPage page pulled");
			return nextPage;
		}
	}

	/**
	 * returns the total number of pages that have been added to the queue through the
	 * addPage() method
	 * 
	 * @return int - number of pages successfully downloaded
	 */
	public static int getPagesDownloaded()
	{
		synchronized(pages)
		{
			return pagesDownloaded;
		}
	}
	
	/**
	 * Increase the number of unsuccessful page downloads.
	 */
	public static void increaseFailedDownloads()
	{
		synchronized(pages)
		{
			failedDownloads++;
		}
	}
	
	/**
	 * Getter for failedDownloads.
	 * 
	 * @return - int - number of pages unsuccessfully downloaded
	 */
	public static int getFailedDownloads()
	{
		synchronized(pages)
		{
			return failedDownloads;
		}
	}
	
	/**
	 * For use in debugging the SharedLink class.
	 */
	public static void debugPages()
	{
		System.out.println("Page Data in pages: ");
		for(String pageData : pages)
		{
			try
			{
				System.out.println(pageData.substring(0, 250));
			}
			catch (Exception e)
			{
				//do nothing
			}
		}
	}
}
