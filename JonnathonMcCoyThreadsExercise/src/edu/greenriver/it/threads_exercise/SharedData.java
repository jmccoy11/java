/* 
 * Jonnathon McCoy
 * February 17, 2017
 * 
 * Package: edu.greenriver.it.threads_exercise
 * Class: SharedData.java
 * 
 * A Thread-safe static class that can be accessed anywhere in the program.
 */

package edu.greenriver.it.threads_exercise;

import java.util.ArrayList;

/**
 * A Thread-safe static class that can be accessed anywhere in the program.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class SharedData
{
    public static ArrayList<String> wordList = new ArrayList<String>();
    public static Object lock = new Object();

    /**
     * Thread-safe
     * Add a word through a synchronized lock to the ArrayList.add() method.
     * 
     * @param word - String - Word to add to the ArrayList.
     */
    public static void add(String word)
    {
    	synchronized (lock)
    	{
    		wordList.add(word);
    	}
    }//add()

    /**
     * Thread-safe
     * Access ArrayList.size() method through a synchronized lock.
     * 
     * @return - int - Size of the ArrayList.
     */
    public static int wordPhraseTotal()
    {
    	synchronized (lock)
    	{
    		return wordList.size();
    	}
    }//wordPhraseTotal()

    /**
     * Thread-safe
     * Access ArrayList.get(index) method through a synchronized lock.
     * 
     * @param position - int - index position within the ArrayList
     * @return - String - Returns the Word in the ArrayList at index(position).
     */
    public static String getWordPhrase(int position)
    {
    	synchronized (lock)
    	{
    		return wordList.get(position);
    	}
    }//getWordPhrase()
}//SharedData.java
