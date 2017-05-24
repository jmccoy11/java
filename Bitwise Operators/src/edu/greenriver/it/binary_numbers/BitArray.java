package edu.greenriver.it.binary_numbers;

public class BitArray {
	private int data;
	
	public void setBit(int index, boolean value){
		if(value){
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
		else{ 
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
	}
	
	public boolean getBit(int index){
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
	}
	
	public int getData(){
		return data;
	}
	
	public void setData(int data){
		this.data = data;
	}
	
	public boolean isSet(int number, int index){
		int mask = (int)Math.pow(2, index);
		
		if((number & mask) == mask){
			return true;
		}
		else return false;
	}
}
