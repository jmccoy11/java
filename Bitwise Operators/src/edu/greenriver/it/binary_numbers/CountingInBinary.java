package edu.greenriver.it.binary_numbers;

public class CountingInBinary {

	public static void main(String[] args) {
		
		printBinary(54, 8);
	}

	private static void printBinary(int number, int numBits) {
		// start by printing out the first digit at index 0
		
		//loop from left to right
		for(int i = numBits -1; i >=0; i--){
			
			int mask = (int)Math.pow(2, i);
			int result = number & mask;
			
			if(result == mask){
				System.out.print("1");
			}
			else{
				System.out.print("0");
			}
		}
		
		System.out.println();
	}
}
