/*
 * Jonnathon McCoy
 * March 3, 2017
 * 
 * package: edu.greenriver.it.webcrawler
 * class: SharedLink.java
 * 
 * The SharedLink class stores links parsed from pages and keeps track of all sites
 * that have already been visited.
 * 
 * Also keeps track of how many links have been found by the Parser.
 */

package greenriver.edu.it.webcrawler;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

/**
 * The SharedLink class stores links parsed from pages and keeps track of all sites
 * that have already been visited.
 * 
 * Also keeps track of how many links have been found by the Parser.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class SharedLink 
{
	//fields
	private static final int MAX_QUEUE_SIZE = 50000;
	
	private static int linksFound = 0;
	
	private static HashSet<String> visitedURLs = new HashSet<String>();
	private static Queue<String> links = new LinkedList<String>();
	
	
	//methods
	/**
	 * 
	 * Adds a link to the que if it has not yet been seen
	 * Store a HashSet<String> of string URLs that have been seen so far to ensure
	 * that a link has not been added more than once
	 * 
	 * The queue has a maximum size of 50000. If there is no room in the queue
	 * then the thread should call wait() on the queue
	 * 
	 * After adding a new link to the queue, notify() is called on the queue
	 * 
	 * @param url - String - URL to be added to the links Queue
	 * 
	 * @throws InterruptedException - Checked exception in case the thread is interrupted.
	 */
	public static void addLink(String url) throws InterruptedException
	{
		synchronized(links)
		{
			//if visitedURLs does not contain the current URL
			if(!visitedURLs.contains(url))
			{
				visitedURLs.add(url);
				linksFound++;
			}
			
			if(links.size() == MAX_QUEUE_SIZE)
			{
				//System.out.println("SharedLink addLink() MAX SIZE reached ... waiting");
				links.wait();
			}
			
			links.add(url);
			
			links.notify();
			//System.out.println("Link added");
		}
	}
		
	
	/**
	 * Returns a link from the queue. If the queue is empty, then the thread should call
	 * wait() on the queue.
	 * After removing a link from the queue, you should call notify() on the queue before
	 * returning the URL.
	 * 
	 * @return String - URL of the next website in the list to download
	 * @throws InterruptedException - Checked exception in case the thread is interrupted.
	 */
	public static String getNextLink() throws InterruptedException
	{
		synchronized(links)
		{
			if(links.isEmpty())
			{
				//System.out.println("SharedLink getNextLink() isEmpty ... waiting");
				links.wait();
			}
			
			//assign the head String url to the nextLink
			String nextLink = links.poll();
			
			//remove the head
			links.remove(links.poll());
			
			links.notify();
			
			return nextLink;
		}
	}
		
	/**
	 * returns the total number of unique links found so far through the addLink() method
	 * @return int - number of links found
	 */
	public static  int getLinksFound()
	{
		return linksFound;
	}
	
	/**
	 * For use in debugging the SharedLink class.
	 */
	public static void debugLinks()
	{
		System.out.println("Visited Links: ");
		for(String link : visitedURLs)
		{
			System.out.println(link);
		}
		
		System.out.println();
		
		System.out.println("Links in Queue: ");
		for(String link : links)
		{
			System.out.println(link);
		}
	}
}
