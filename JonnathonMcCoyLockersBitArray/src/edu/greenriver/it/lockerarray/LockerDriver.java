package edu.greenriver.it.lockerarray;

public class LockerDriver 
{
	private static final byte LOCKERS = 30;
	private static final byte STUDENTS = 30;
	
	public static void main(String[] args) 
	{
		LockerArray lockerArray = new LockerArray(0);
		
		solve(lockerArray);
	}
	
	private static void solve(LockerArray lockerArray)
	{
		for(int student = 1; student <= STUDENTS; student++)
		{
			for(int locker = 1; locker <= LOCKERS; locker++)
			{
				if(locker%student == 0)
				{
					if(lockerArray.getBit(locker-1))
					{
						lockerArray.setBit(locker-1, false);;
					}
					else lockerArray.setBit(locker-1, true);
					
					printToConsole(lockerArray, locker, student);
				}
			}
		}
	}
	
	private static void printToConsole(LockerArray lockerArray, int locker, int student)
	{
		switch (student)
		{
			case 1: System.out.println("Bit set on Locker " + locker + " by the " + student + "st student.");
					break;
			case 2: System.out.println("Bit set on Locker " + locker + " by the " + student + "nd student.");
					break;
			case 3: System.out.println("Bit set on Locker " + locker + " by the " + student + "rd student.");
					break;
			default: System.out.println("Bit set on Locker " + locker + " by the " + student + "th student.");
					 break;
		}
		
		System.out.print("Binary representation: ");
		printBinary(lockerArray.getData());
		System.out.println("Current value of lockerArray.data: " + lockerArray.getData());
		System.out.println();
	}
	
	private static void printBinary(int data)
	{
		String rawBinary = "";
		
		while(data != 0)
		{
			if(data%2 == 0)
			{
				rawBinary += "0";
			}
			else 
			{
				rawBinary += "1";
			}
			
			data = data/2;
		}
		
		String addZeroes = "";
		if(rawBinary.length() != 30)
		{
			for(int i = 0; i < (30 - rawBinary.length()); i++)
			{
				addZeroes += "0";
			}
			
			rawBinary += addZeroes;
		}
		
		String binary = "";
		for(int i = 0; i < rawBinary.length(); i++)
		{
			if(i%4 == 0)
			{
				binary += " " + rawBinary.charAt(i);
			}
			else
			{
				binary += rawBinary.charAt(i);
			}
		}
		
		//flip the binary
		String binaryFlip = "";
		for(int i = binary.length()-1; i >= 0; i--)
		{
			binaryFlip += binary.charAt(i);
		}
		
		System.out.println(binaryFlip);
	}
}
