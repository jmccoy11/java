/*
 * Jonnathon McCoy
 * March 3, 2017
 * 
 * package: edu.greenriver.it.webcrawler
 * class: Fetcher.java
 * 
 * The Fetcher class takes a url from the critical region SharedLink ArrayList and downloads
 * the page from a specified website for use by the Parser class.
 */

package greenriver.edu.it.webcrawler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * The Fetcher class takes a url from the critical region SharedLink ArrayList and downloads
 * the page from a specified website for use by the Parser class.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Fetcher extends Thread
{
	//fields
	private String url;
	
	//methods
	
	/**
	 * Pull a link url from the SharedLink queue, download the page, and add the entire page html
	 * as a String to the SharedPage queue critical region.
	 */
	@Override
	public void run()
	{
		while(true)
		{
			//pull a link from the link que
			try 
			{
				url = SharedLink.getNextLink();
			} 
			catch (InterruptedException e) 
			{
				//do nothing
			}
			
			//download the (HTML) page text at the given url
			String pageData = "";
			
			try 
			{
				HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
				BufferedReader download = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				
				pageData = download.readLine();

				while(download.readLine() != null)
				{
					pageData += download.readLine();
				}
				
				//store the (HTML) page text on the page que as a String
				SharedPage.addPage(pageData);
			} 
			catch (MalformedURLException e) 
			{
				//do nothing
			} 
			catch (IOException e) 
			{
				SharedPage.increaseFailedDownloads();
			}
			catch (InterruptedException e) 
			{
				//do nothing
			}
			catch (Exception e)
			{
				System.out.println("Something went wrong." + e.getMessage());
			}	
		}
	}
}
