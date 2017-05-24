/*
 * Jonnathon McCoy
 * January 31, 2017
 * 
 * Package: edu.greenriver.it.arrays
 * Class: BitArray.java
 * 
 * This class stores a number and uses BitWise operators to
 * ask questions, specifically what bits are set, about the number 
 * stored in the data variable.
 */

package edu.greenriver.it.arrays;

/**
 * This class stores a number and uses BitWise operators to
 * ask questions, specifically what bits are set, about the number 
 * stored in the data variable.
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class BitArray 
{
	private int data;
	
	/**
	 * Sets the bit at index "index" to boolean "value".
	 * 
	 * @param index - binary index of data
	 * @param value - set value to true or false. True will use the OR
	 * BitWise operator, False will invert all the bits and use the AND 
	 * BitWise operator.
	 */
	public void setBit(int index, boolean value)
	{
		if(value)
		{
			//set the bit at index "index" to 1
			
			/*
			 *   ex: BitArray data = 11;  0000 1011 = 11
			 *   setBit(5, true)  //set bit at index 2 to 1
			 *   
			 *   data    0000 1011    = 11
			 *   mask  | 0010 0000    = 32
			 *   data =  0010 1011    = 43  //data now equals 43
			 */
			
			
			int mask = (int)Math.pow(2, index);
			data |= mask; // data = data | mask;
		}
		else
		{ 
			//invert the bit at index "index" if it is already set
			
			/*
			 * ex: BitArray data = 15;  0000 1111 = 15
			 * setBit(2, false)  //flip the bit at index 2
			 * 
			 * mask    =  0000 0100  = 4
			 * ~mask   =  1111 1011  = 251
			 * 
			 * data       0000 1111  = 15
			 * ~mask    & 1111 1011  = 251
			 *            0000 1011  = 11
			 *            
			 *            
			 *     // this example tries to flip a bit that is not actually set
			 *     
			 * ex: BitArray data = 11;  0000 1011 = 11
			 * setBit(4, false)  //flip the bit at index 4
			 * 
			 * mask    =  0001 0000  = 16
			 * ~mask   =  1110 1111  = 239
			 * 
			 * data       0000 1011  = 11
			 * ~mask    & 1110 1111  = 239
			 *            0000 1011  = 11
			 */
			
			int mask = (int)Math.pow(2, index);
			mask = ~mask; // invert all the bits
			data &= mask; // data = data & mask;
		}
	} //setBit()
	
	/**
	 * Retrieve the boolean value of the bit at index "index".
	 * 
	 * Create the mask to be a power of 2 at index "index" and AND it to data.
	 * If the result equals the mask, the bit is set.
	 * 
	 * @param index - binary index of data
	 * @return - boolean true or false
	 */
	public boolean getBit(int index)
	{
		int mask = (int)Math.pow(2, index);
		
		/*	 ex: BitArray data = 11;   // 0000 1011 = 11
		 *   getBit(3)  // get bit at index 3
		 *   
		 *   int mask = 0000 1000
		 *   
		 *     0000 1011  = 11  // data
		 *   & 0000 1000  = 8   // mask
		 *     0000 1000  = 8   // data & mask
		 *     
		 *     return boolean check (mask & data) ==   mask 
		 *                                8       ==    8     return true
		 *                                
		 *  getBit(2) // get bit at index 2
		 *  
		 *    0000 1011  = 11
		 *    0000 0100  = 4
		 *  & 0000 0000  = 0
		 *  
		 *    return boolean check (mask & data) ==   mask
		 *                               0       ==    4      return false
		 */
		
		return (mask & data) == mask; // if mask logical AND to data == mask return true
	} //getBit()
	
	/**
	 * Retrieve the boolean value of the bit at index "index".
	 * 
	 * Create the mask to be a power of 2 at index "index" and AND it to number.
	 * If the result equals the mask, the bit is set.
	 * 
	 * @param number - integer to compare the mask to
	 * @param index - binary index of number
	 * @return
	 */
	public boolean isSet(int number, int index)
	{
		int mask = (int)Math.pow(2, index);
		
		return ((number & mask) == mask);
	} //isSet()
	
	/**
	 * Getter for data.
	 * 
	 * @return - int from data variable
	 */
	public int getData()
	{
		return data;
	} //getData()
}//BitArray.java
