/*
 * Jonnathon McCoy
 * Assignment ClassHierarchy
 * CreatorDriver.java
 * 04/14/2016
 */

package classHierarchy;

import java.util.Scanner;

/**
 * This class is a test class for the classes in
 * classHierarchy java project.
 * 
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class CreatorDriver
{
	//fields
	static Object userObject; //instantiated in order to
							  //change it later in program
	private static Scanner creatorScanner = 
			new Scanner(System.in);
	private static boolean creatorLoop = true, 
			menuLoop = true;
	
	//constructors
	
	//methods
	/**
	 * Entry point to CreatorDriver program.
	 * 
	 * 
	 * @param args Command-line arguments
	 */
	public static void main(String[] args)
	{
		System.out.println("Welcome to the Starcraft "
				+ "unit and building creator!");
		System.out.println("First I've created a few "
				+ "for you as a template, here they "
				+ "are:\n");
		
		prebuild(); //show prebuilt examples
		
		while(creatorLoop)
		{			
			creator(); //get input from user to create
			           //a class object
			menu(); //create a new class or exit
		}
		creatorScanner.close();
	}//run
	
	/**
	 * Gets information from the user in order to create
	 * a class object based on the answers given.
	 */
	public static void creator()
	{
		//fields
		String faction, name, type; 
		String buildingType, produces, unitType;
		String rangeType, groundOrAir, getTransport;
		int hitPoints;
		double speed, range;
		boolean transport;
		
		System.out.println("Please enter a faction.  "
				+ "(Terran, Zerg, or Protoss): ");
		faction = creatorScanner.nextLine();
		Type.verifyFaction(faction);
		
		System.out.println("Please enter a name: ");
		name = creatorScanner.nextLine();
		
		System.out.println("Please enter a type: "
				+ "Building or Unit. ");
		type = creatorScanner.nextLine();
		Type.verifyType(type);
		
		System.out.println("How many hitpoints does the "
				+ "object have? ");
		hitPoints = creatorScanner.nextInt();
		creatorScanner.nextLine(); //clear scanner buffer
		
		//if unit or building
		if(type.toLowerCase().equals("unit"))
		{
			System.out.println("Is the unit Infantry or "
					+ "a Vehicle?");
			unitType = creatorScanner.nextLine();
			Unit.verifyUnitType(unitType);
			
			System.out.println("What is the unit's speed?");
			speed = creatorScanner.nextDouble();
			creatorScanner.nextLine(); //clear scanner buffer
			
			//if infantry or vehicle
			if(unitType.toLowerCase().equals("infantry"))
			{
				System.out.println("Is the unit Melee or "
						+ "Ranged?");
				rangeType = creatorScanner.nextLine();
				Infantry.verifyRangeType(rangeType);
				
				//if melee or ranged
				if(rangeType.toLowerCase().equals("ranged"))
				{
					System.out.println("What is the unit's "
							+ "range?");
					range = creatorScanner.nextDouble();
					creatorScanner.nextLine(); //clear scanner buffer
					
					//create ranged Infantry class object
					userObject = new Infantry(faction,
							name,hitPoints, speed, 
							rangeType,range);
				}//ranged
				
				//used else if instead of else in case I
				//want to expand on this hierarchy.
				else if(rangeType.toLowerCase().equals("melee"))
				{
					range = 0;
					
					//create melee Infantry class object
					userObject = new Infantry(faction,
							name,hitPoints, speed, 
							rangeType,range);
				}//melee
			}//infantry
			
			else if(unitType.toLowerCase().equals("vehicle"))
			{
				System.out.println("Is the vehicle Ground or"
						+ " Air? ");
				groundOrAir = creatorScanner.nextLine();
				groundOrAir = Vehicle.verifyGroundOrAir(groundOrAir);
				
				System.out.println("Is this a transport unit?");
				getTransport = creatorScanner.nextLine();
				
				//transport capable?
				while(true)//verification loop
				{
					if(getTransport.toLowerCase().equals("yes"))
					{
						transport = true;
						break;
					}
					else if(getTransport.toLowerCase().equals("no"))
					{
						transport = false;
						break;
					}
					else
					{
						System.out.println("Please enter "
								+ "a Yes or No");
						getTransport = creatorScanner.nextLine();
					}
				}//transport
				
				//create Vehicle class object
				userObject = new Vehicle(faction, name, 
						hitPoints, speed, groundOrAir,
						transport);
			}//vehicle
		}//unit
		
		else
		{
			System.out.println("Please enter whether "
				+ "the building is a Static or "
				+ "a Production building. ");
			buildingType = creatorScanner.nextLine();
			Building.verifyBuildingType(buildingType);
			
			if(buildingType.toLowerCase().equals("production"))
			{
				System.out.println("What does this building "
						+ "produce? ");
				produces = creatorScanner.nextLine();
				
				//create production Building class object
				userObject = new Producing(faction, name, 
						hitPoints, produces);
			}//production
			else if(buildingType.toLowerCase().equals("static"))
			{
				//create static Building class object
				userObject = new Building(faction, name, 
						hitPoints, buildingType);
			}//static
		}//building
	}//creator
	
	/**
	 * Creates some pre-built examples for the user.
	 */
	public static void prebuild()
	{
		System.out.println("Producing Class");
		Producing custom5 = new Producing("Zerg", 
				"Hatchery",	1000, "All Units");
		System.out.println(custom5);
		System.out.println("");
		
		System.out.println("Infantry Class");
		Infantry custom6 = new Infantry("Protoss", 
				"Dragoon", 215, 80, "Ranged", 200);
		System.out.println(custom6);
		System.out.println("");
		
		System.out.println("Vehicle Class");
		Vehicle custom7 = new Vehicle("Terran", "Transport Ship", 
				225, 75, "Air", true);
		System.out.println(custom7);
		System.out.println("");
		
		/*
		 * Used this code to test my classes individually
		 * didn't want to delete it.
		 
		System.out.println("Faction Class");
		Faction custom1 = new Faction("Terran", "Ghost");
		System.out.println(custom1);
		System.out.println("");
		
		System.out.println("Type Class");
		Type custom2 = new Type("Zerg", "Zergling", "Unit",
				150, true);
		System.out.println(custom2);
		System.out.println("");
		
		System.out.println("Unit Class");
		Unit custom3 = new Unit("Protoss", "Zealot", 200,
				true, "Infantry", 50);
		System.out.println(custom3);
		System.out.println("");
		
		System.out.println("Building Class");
		Building custom4 = new Building("Terran", 
				"Supply Depot",	300, "Static");
		System.out.println(custom4);
		System.out.println("");
		
		System.out.println("Producing Class");
		Producing custom5 = new Producing("Zerg", 
				"Hatchery",	1000, "All Units");
		System.out.println(custom5);
		System.out.println("");
		
		System.out.println("Infantry Class");
		Infantry custom6 = new Infantry("Protoss", 
				"Dragoon", 215, 80, "Ranged", 200);
		System.out.println(custom6);
		System.out.println("");
		
		System.out.println("Vehicle Class");
		Vehicle custom7 = new Vehicle("Zerg", "Overlord", 
				225, 75, "Air", true);
		System.out.println(custom7);
		System.out.println("");
		*/
	}//prebuild
	
	/**
	 * Method for creating and implementing a menu.
	 */
	public static void menu()
	{
		System.out.println("");
		System.out.println("Currently, this program does "
				+ "not have the capability of creating "
				+ "multiple \n objects as the programmer "
				+ "does not yet have that knowledge. \n");
		
		menuLoop = true;
		while(menuLoop)
		{
			System.out.println("This is what you have.");
			System.out.println("");
			System.out.println(userObject);
			System.out.println("");
			
			System.out.println("What would you like to do?");
			System.out.println("1. Create another "
					+ "(This will override your last "
					+ "creation)");
			System.out.println("2. Exit\n");
		
			String menu = creatorScanner.nextLine();
		
			if(menu.equals("1"))
			{
				menuLoop = false;
			}
			else if(menu.equals("2"))
			{
				creatorLoop = false;
				menuLoop = false;
			}
			else
			{
				System.out.println("Please choose a "
						+ "valid option from above.\n");
			}
		}//menuLoop
	}//menu
}//CreatorDriver.java

