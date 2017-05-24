
public class Deck 
{
	public static void main(String[] args) 
	{
		int[] numbers = {6,13,2,4,1,12,10,11,5,9,8,3,7};
		
		for(int number : numbers) {
			System.out.print(number + "  ");
		}
		
		System.out.println("\n\n");
		
		sort(numbers);
		
		for(int number : numbers) {
			System.out.print(number + "  ");
		}
	}
	
	public static void sort(int[] input)
	{
		for(int i = 1; i < input.length; i++)
		{
			if(input[i] < input[i-1])
			{
				int temp = input[i];
				input[i] = input[i-1];
				input[i-1] = temp;
				
				for(int j = i-1; j >= 1; j--)
				{
					if(input[j] < input[j-1])
					{
						int temp2 = input[j];
						input[j] = input[j-1];
						input[j-1] = temp2;
					}
				}
			}
		}
	}
}
