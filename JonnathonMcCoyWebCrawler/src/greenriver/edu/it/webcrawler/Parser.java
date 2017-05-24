/*
 * Jonnathon McCoy
 * March 3, 2017
 * 
 * package: edu.greenriver.it.webcrawler
 * class: Parser.java
 * 
 * The Parser class takes a downloaded page from the critical region SharedPage ArrayList and searches
 * the page for specified keywords.
 */
package greenriver.edu.it.webcrawler;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class takes a downloaded page from the critical region SharedPage ArrayList and searches
 * the page for specified keywords.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class Parser extends Thread
{
	//fields
	private String pageText;
	
	//methods
	/**
	 * Pull a page from the SharedPage queue, search for any links on the page and add them to the
	 * SharedLink critical region to be used the the Fetcher class. Additionally search for any keywords set by the
	 * user and increment the keywordHits.
	 */
	@Override
	public void run()
	{
		while(true)
		{
			//System.out.println("beginning of Parser code");
			
			//pull a page from the page que
			try 
			{
				pageText = SharedPage.getNextPage();
				//System.out.println("Parser Page Pulled");
			} 
			catch (InterruptedException e) 
			{
				//do nothing
			}
			
			//search the page for all links in anchor (<a href="") elements
			
			//Regex
			//loop will execute for each link found
			Pattern pattern = Pattern.compile("href=\"(http:.*?)\"");
			Matcher matcher = null;
			
			//Some pages are returning null values
			if(pageText != null)
			{
				matcher = pattern.matcher(pageText);
			}
			
			//add each link found to the link queue
			
			//sometimes matcher is returning null values
			while(matcher != null && matcher.find())
			{
				String link = matcher.group(1);
			
				try 
				{
					SharedLink.addLink(link);
				} 
				catch (InterruptedException e) 
				{
					//do nothing
				}
			}
			
			//search the page for keywords specified by the user of the webcrawler
			ArrayList<String> keywords = WebCrawlerConsole.getKeywords();
			
				for(String word : keywords)
				{
					//cannot split pageText if it is null
					if(pageText != null)
					{
						String[] keywordSearch = pageText.split(word);
						for(int i=0; i < keywordSearch.length; i++)
						{
							WebCrawlerConsole.keywordHit();
						}
					}
				}
			
			//System.out.println("end of Parser code");
		}
	}
}
