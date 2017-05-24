package edu.greenriver.it.binary_numbers;

/*
 *     Red | Green |  Blue
 *      0      0       0
 *      0	   0	   1
 *      0      1       0
 *      0      1       1
 *      1      0       0
 *      1      0       1
 *      1      1       0
 *      1      1       1
 */

public class SubSets {

	public static void main(String[] args) {
		String[] balls = {"red", "green", "blue", "pink"}; //, "teal", "yellow", "orange", "purple", "burgundy"};
		
		for(int i = 0; i < (int)Math.pow(2, balls.length); i++){
			for(int j = 0; j < balls.length; j++){
				int mask = (int)Math.pow(2, j);
				if ((i & mask) == mask){
					System.out.print(balls[j] + " ");
				}
			}
			
			System.out.println();
			
//			//ask whether first, second, or third bit is ON
//			
//			int mask = 1;
//			if ((i & mask) == mask){
//				System.out.print("blue ");
//			}
//			
//			mask = 2;
//			if ((i & mask) == mask){
//				System.out.print("green ");
//			}
//			
//			mask = 4;
//			if ((i & mask) == mask){
//				System.out.print("red ");
//			}
		}
	}
}
