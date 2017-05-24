/*
 * Jonnathon McCoy
 * AssassinManager.java
 * 06/07/2016
 * 
 * Class that handles the Linked Lists assassinsHead and graveHead
 */

package Assassins;

import java.util.List;

/**
 * Class that handles the Linked Lists assassinsHead and graveHead
 * 
 * @author Jonnathon McCoy
 * @version 1.0
 */
public class AssassinManager 
{
	AssassinNode assassinsHead;  //head of the kill ring L-List
	AssassinNode graveHead;  //head of the graveyard L-List

	/**
	 * Constructor for the Assassin Manager class
	 * 
	 * @param nameList ArrayList of names from names.txt
	 */
	public AssassinManager(List<String> nameList) 
	{
		/*
		 * List of names
		 * 
		 * Iterate over the list of names and build the assassins
		 * linked list
		 * 
		 * assassinsHead should point to the first node in the 
		 * Linked list
		 */
		
		for(int i = 0; i < nameList.size(); i++)
		{
			//is my list empty
			if(assassinsHead == null)
			{
				//assign (point to) a new node object
				assassinsHead = new AssassinNode(nameList.get(i));
			}
			else
			{
				AssassinNode current = assassinsHead;
				
				//continue looping until I find a node with a null next pointer
				while(current.getNext() != null)
				{
					//move my current variable to the next Node
					current = current.getNext();
				}
				
				//add a new node at the end of the list
				current.setNext(new AssassinNode(nameList.get(i)));
				current.getNext().setKiller(nameList.get(i-1));
			}
		}
		
		//set assassinsHead killer
		assassinsHead.setKiller(nameList.get(nameList.size() - 1));
	}

	/**
	 * Prints the assassinsHead Linked List
	 */
	public void printKillRing()
	{
		AssassinNode current = assassinsHead;
		
		while(current.getNext() != null)
		{
			System.out.println(current.getNext().getKiller() + " is stalking "
					+ current.getNext().getName());
			
			if(current.getNext().getNext() == null)
			{
				System.out.println(assassinsHead.getKiller() 
						+ " is stalking " + assassinsHead.getName());
			}
			
			current = current.getNext();
		}
	}

	/**
	 * Prints the graveHead Linked List
	 */
	public void printGraveyard()
	{
		AssassinNode current = graveHead;
		
		if(graveHead == null)
		{
			//do nothing
		}
		else
		{
			while(current.getNext() != null)
			{
				System.out.println(current.getName() + " was killed by " 
						+ current.getKiller());
				
				current = current.getNext();
			}
			
			System.out.println(current.getName() + " was killed by " 
					+ current.getKiller());
		}
	}
	
	/**
	 * Search assassinsHead for parameter name
	 * 
	 * @param name String name to search for within the Linked List
	 * @return Return Boolean if search exists in assassinsHead.
	 */
	public boolean killRingContains(String name)
	{
		AssassinNode current = assassinsHead;
		
		boolean found = false;
		while(!found)
		{
			if(current.getName().equalsIgnoreCase(name))
			{
				found = true;
				return true;
			}
			
			if(current.getNext() == null)
			{
				return false;
			}
			current = current.getNext();
		}
		
		//else
		return false;
	}
	
	/**
	 * * Search assassinsHead for parameter name
	 * 
	 * @param name String name to search for within the Linked List
	 * @return Return Boolean if search exists in graveHead
	 */
	public boolean graveyardContains(String name)
	{
		AssassinNode current = graveHead;
		
		boolean found = false;
		while(!found)
		{
			if(current == null)
			{
				found = true;
				return false;
			}
			else if(current.getName().equalsIgnoreCase(name))
			{
				found = true;
				return true;
			}
//			else if(current.getNext() == null)
//			{
//				found = true;
//				return false;
//			}
			
			current = current.getNext();
		}
		
		//else
		return false;
	}
	
	/**
	 * If one person is left in assassinsHead. ie: next == null
	 * 
	 * @return return boolean true if assassinsHead.next == null
	 */
	public boolean isGameOver() 
	{
		if(assassinsHead.getNext() == null)
		{
			return true;
		}
		else return false;
	}
	
	/**
	 * String assassinsHead.name
	 * @return String assassinsHead.name!
	 */
	public String winner()
	{
		return assassinsHead.getName() + "!";
	}
	
	/**
	 * Find and remove the name passed into name parameter. Once object is
	 * found, check if graveHead is null, and send the assassinsNode attached
	 * to the name to the graveHead Linked List, and connect the killer
	 * assassinsNode to the next assassinsNode of the one assassinated.
	 * 
	 * @param name String name of the assassinNode that is to be removed from
	 * the assassinsHead Linked List.
	 */
	public void kill(String name)
	{
		AssassinNode current = assassinsHead;
		AssassinNode graveyardCurrent = graveHead;
		
		boolean found = false;
		while(!found)
		{
			if(assassinsHead.getName().equals(name)) //if the target is the assassinsHead
			{
				found = true;
				
				if(graveHead == null) //if graveHead is empty
				{
					//send assassinsHead to graveHead
					graveHead = current;
					
					// set assassinsHead.next to the new assassinsHead
					assassinsHead = current.getNext();
					
					//set the new killer to the old assassinsHead killer
					assassinsHead.setKiller(current.getKiller());
					
					graveHead.setNext(null);
				}
				else
				{
					while(graveyardCurrent.getNext() != null)
					{
						graveyardCurrent = graveyardCurrent.getNext();
					}
					
					graveyardCurrent.setNext(current);
					
					assassinsHead = current.getNext();
					
					assassinsHead.setKiller(current.getKiller());
					
					graveyardCurrent.getNext().setNext(null);
				}
			} 
			else if(current.getNext().getNext() == null) //check if it is the end of the list
			{
				found = true;
				
				if(graveHead == null)
				{
					//graveHead becomes the target node which already has a next == null
					graveHead = current.getNext();
					
					//set assassinsHead killer to the new Node at the end of the list
					assassinsHead.setKiller(current.getName());
					
					current.setNext(null);
				}
				else
				{
					while(graveyardCurrent.getNext() != null)
					{
						graveyardCurrent = graveyardCurrent.getNext();
					}
					
					graveyardCurrent.setNext(current.getNext());
					
					//set the current.next to the Node after the next node (null included)
					current.setNext(null);							
					
					//current.next is now the end of the list
					//change the assassinsHead killer to current.name
					assassinsHead.setKiller(current.getName());
					
					graveyardCurrent.getNext().setNext(null);
				}
			}
			else //everything else
			{
				if(current.getNext().getName().equals(name))
				{
					found = true;
					
					if(graveHead == null)
					{
						//graveHead becomes the target node
						graveHead = current.getNext();
							
						//set the current next Node to the Node after the next node
						current.setNext(current.getNext().getNext());
								
						//setKiller of the now current.next to the name of the current
						current.getNext().getNext().setKiller(current.getName());
								
						graveHead.setNext(null);
					}
					else
					{
						while(graveyardCurrent.getNext() != null)
						{
							graveyardCurrent = graveyardCurrent.getNext();
						}
						
						graveyardCurrent.setNext(current.getNext());
						
						current.setNext(current.getNext().getNext());
						current.getNext().setKiller(current.getName());
						
						graveyardCurrent.getNext().setNext(null);
					}
				}
			}
			
			current = current.getNext();
		}
	}
}
			