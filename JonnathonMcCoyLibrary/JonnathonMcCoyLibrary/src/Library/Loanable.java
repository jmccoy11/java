/*
 * Jonnathon McCoy
 * May 19, 2016
 * Loanable.java
 * 
 * Loanable interface for use with Item Class and child classes
 */

package Library;


/**
 * Loanable interface for use with Item Class and child classes
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public interface Loanable 
{	
	/**
	 * loan period to be defined for each class
	 * 
	 * @return int number of days to loan out for
	 */
	public int loanPeriod();
}//Loanable.java
