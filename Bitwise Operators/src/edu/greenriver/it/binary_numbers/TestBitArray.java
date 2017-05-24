package edu.greenriver.it.binary_numbers;

public class TestBitArray {

	public static void main(String[] args) {
		BitArray bitArray = new BitArray();
		
		System.out.println("Set bit 0:");
		bitArray.setBit(0, true);
		System.out.println(bitArray.getData());  // 0000 0001 = 1
		showBinary(bitArray);
		
		System.out.println("Set bit 1:");
		bitArray.setBit(1, true);
		System.out.println(bitArray.getData());  // 0000 0011 = 3
		showBinary(bitArray);
		
		System.out.println("Set bit 2:");
		bitArray.setBit(2, true);
		System.out.println(bitArray.getData());  // 0000 0111 = 7
		showBinary(bitArray);
		
		System.out.println("Set bit 3:");
		bitArray.setBit(3, true);
		System.out.println(bitArray.getData());  // 0000 1111 = 15
		showBinary(bitArray);
		
		System.out.println("Flip bit 2:");
		bitArray.setBit(2, false);
		System.out.println(bitArray.getData());  // 0000 1011 = 11
		showBinary(bitArray);
		
		System.out.println("Attempt to flip bit 4:");
		bitArray.setBit(4, false);
		System.out.println(bitArray.getData());
		showBinary(bitArray);
		
		System.out.println("Set bit 5:");
		bitArray.setBit(5, true);
		System.out.println(bitArray.getData());
		showBinary(bitArray);
		
		System.out.println("Testing isSet()");
		System.out.println();
		System.out.println("For the number 3, is bit 0 set?");
		System.out.println(isSet(3,0));
		
		System.out.println();
		System.out.println("For the number 64, is bit 6 set?");
		System.out.println(isSet(64,6));
		
		System.out.println();
		System.out.println("For the number 64, is bit 2 set?");
		System.out.println(isSet(64,2));
		
		System.out.println();
		System.out.println("Testing wordMask()");
		System.out.println("Vertical with the word Mask of 1100 0011 is: ");
		System.out.println(wordMask("vertical", 11000011));

		System.out.println();
		System.out.println("Vertical with the word Mask of 1000 1011 is: ");
		System.out.println(wordMask("vertical", 10001011));
		
		/*
		 * This code does not work. Sending 0000 1011 as an int to wordMask()
		 *   sets this parameter to 521 instead of 00001011
		 *   
		 *   I HAVE NO IDEA WHY!?!?
		 */
//		System.out.println();
//		System.out.println("Seven with the word Mask of 0000 1011 is: ");
//		System.out.println(wordMask("seven", 00001011));
		
		/*
		 * This code is not working as intended either. The mask is lining up
		 * to the end of the word instead of the beginning.
		 */
//		System.out.println();
//		System.out.println("Seven with the word Mask of 1000 1011 is: ");
//		System.out.println(wordMask("seven", 10001011));
	}
	
	public static void showBinary(BitArray bitArray){
		
		/*
		 * Prints the true/false version of the Binary
		 * 
		System.out.println();
		for(int i = 7; i >= 0; i--){
			if(i == 0){
				System.out.println(bitArray.getBit(i));
			}
			else{
				System.out.print(bitArray.getBit(i) + ", ");
			}
		}
		
		 */
		
		//Prints the 0 or 1 equivalent of the number.
		for(int i = 7; i >= 0; i--){
			if(i == 0){
				if(bitArray.getBit(i) == true) System.out.println("1");
				else System.out.println("0");
			}
			else{
				if((i+1)%4 == 0 && i != 7){
					if(bitArray.getBit(i) == true) System.out.print(" 1");
					else System.out.print(" 0");
				}
				else{
					if(bitArray.getBit(i) == true) System.out.print("1");
					else System.out.print("0");
				}
			}
		}
		
		System.out.println();
	}
	
	public static boolean isSet(int number, int index){
		int mask = (int)Math.pow(2, index);
		
		if((number & mask) == mask){
			return true;
		}
		else return false;
	}
	
	/**
	 * Using a binary mask, create a word by selecting only the characters
	 * that correspond at the same index that the bit mask is set to 1.
	 * 
	 * The steps of this method are as follows:
	 * 
	 * Convert the binaryMask from a base10 to a base2 number
	 * Flip the word to read backward so the indexes of the base2 will
	 *   line with the indexes of the String.
	 * Check if the bit at index is set and build a new word from the 
	 *   indexes that the bitMask have set to a 1.
	 * Return new word.
	 * 
	 * @param word - String - the word you wish to apply the mask
	 * @param binaryMask - int - the binary mask you would like to apply 
	 * @return - String - the new word created by combining the word with the
	 *   binary mask.
	 */
	public static String wordMask(String word, int binaryMask){
		
		/* 
		 * Convert the mask by taking the base 10 and multiplying it
		 * by 2 to the corresponding power (starting at 0: 2^0=1, 2^1=2, 2^2=4, etc.)
		 * and add it to the maskNum.
		 * 
		 * if mask%10 is 0, the maskNum += 0 (0*2^power = 0);
		 * if the mask%10 is 1, the maskNum is += (1)*2^power (1*1=1, 1*2=2, 1*4=4, etc.)
		 * 
		 * divide by 10 to reduce the number by one digit.
		 * 
		 * repeat until the mask == 0;
		 * 
		 * now maskNum will equal a non-base10 number that can be used
		 * with comparison to another number to see if bits are set.
		 */
		int numMask = 0;
		int power = 0;
		
		while(binaryMask != 0){
			int temp = binaryMask%10;
			numMask += temp*(int)Math.pow(2, power);
			binaryMask = binaryMask/10;
			power++;
		}
		
		/* Since a binary number is read from right to left and a word is read
		 * left to right, the indexes for both the binary mask and the number
		 * will be inverted.
		 * 
		 * This data will need to be flipped around to line up the indexes between
		 * the string and the mask.
		 * 
		 * ie: 1000 1011  index 7 of is a 1
		 *     |
		 *     V
		 *     Vert icle  matches index 0 which is a V
		 *     
		 * 		by reversing verticle (which seems easier than reversing a mask 
		 * 			at the moment)
		 * 
		 *     1000 1011   index 7 is a 1
		 *     |_______
		 *             |         
		 *     elci treV   will match with index 7 of elcitreV which is a V
		 */
		String flipWord = "";
		for(int i = word.length()-1; i >= 0; i--){
			flipWord += word.charAt(i);
		}
		
		/*
		 * For each bit that isSet() take the character at the current iteration
		 * of the 'for' loop from flipWord[i].
		 */
		String maskWord = "";
		for(int i = word.length()-1; i >= 0; i--){
			if(isSet(numMask, i)){
				maskWord += flipWord.charAt(i);
			}
		}
		
		return maskWord;
		
//       *****************************************
		
//		 * THE FOLLOWING CODE IS NOT FOR GRADING BUT FOR MY OWN REFERENCE
// 		 * 
//		 * (*yes it does work but it was the long way around the problem*)
		
//		String tempWord = "";
//		
//		int numMask = 0;
//		int power = 0;

//		 * Convert the mask by taking the base 10 and multiplying it
//		 * by the 2 to the corresponding power (starting at 0: 2^0=1, 2^1=2, 2^2=4, etc.)
//		 * and add it to the maskNum.
//		 * 
//		 * if mask%10 is 0, the maskNum += 0 (0*2^power = 0);
//		 * if the mask%10 is 1, the maskNum is += (1)*2^power (1*1=1, 1*2=2, 1*4=4, etc.)
//		 * 
//		 * divide by 10 to reduce the number by one digit.
//		 * 
//		 * repeat until the mask == 0;
//		 * 
//		 * now maskNum will equal a non-base10 number that can be used
//		 * with comparison to another number to see if bits are set.
		 
//		while(binaryMask != 0){
//			int temp = binaryMask%10;
//			numMask += temp*(int)Math.pow(2, power);
//			binaryMask = binaryMask/10;
//			power++;
//		}
		
		
//		 * taking the maskNum%2 will indicate if the remainder is a 0
//		 * or a 1. if the remainder is a 1, take the character from the
//		 * word at the index the 'for' loop is currently at and concatenate
//		 * it with the tempWord variable.
//		 * 
//		 * divide maskNum by 2 to get the next number to compare maskNum%2
		 
//		for(int i = 0; i < word.length(); i++){
//			if(numMask%2 == 1){
//				tempWord = tempWord + word.charAt(i);
//			}
//			
//			numMask = numMask/2;
//		}
		
		
//		 * Since a binary number is read from right to left and a word is read
//		 * left to right, the data retrieved in the last for loop will be 
//		 * backwards. ie: laev.
//		 * 
//		 * This data will need to be flipped around.  ie. laev = veal.
		 
//		String flipWord = "";
//		for(int i = tempWord.length()-1; i >= 0; i--){
//			flipWord += tempWord.charAt(i);
//		}
		
//		return flipWord;
	}
}
