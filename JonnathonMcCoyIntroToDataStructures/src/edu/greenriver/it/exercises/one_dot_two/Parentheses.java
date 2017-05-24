package edu.greenriver.it.exercises.one_dot_two;

import java.util.Scanner;
import java.util.Stack;

public class Parentheses 
{	
	private static final String OPEN_PARA = "(", CLOSED_PARA = ")", 
			OPEN_BRACKET = "[", CLOSED_BRACKET = "]",
			OPEN_CURLY = "{", CLOSED_CURLY = "}";
	private static Scanner console = new Scanner(System.in);
	private static Stack<String> myStack = new Stack<String>();
	
	public static void main(String[] args)
	{
		System.out.println("Enter a sequence of parentheses, brackets, and curly braces and this program"
				+ "will check if they are in the proper sequence.");
		String userInput = console.nextLine();
		
		System.out.println("The brackets sequence you entered was " + checkParentheses(userInput));
	}
	
	private static boolean checkParentheses(String userInput)
	{
		for(int i = 0; i < userInput.length(); i++)
		{
			myStack.push(Character.toString(userInput.charAt(i)));
		}
		
		if(myStack.peek().equals(CLOSED_BRACKET) 
				|| myStack.peek().equals(CLOSED_CURLY) 
				|| myStack.peek().equals(CLOSED_PARA))
		{
			if(myStack.peek().equals(CLOSED_BRACKET))
			{
				if(isClosed(myStack, OPEN_BRACKET))
				{
					return true;
				}
				else 
				{
					return false;
				}
			}
			else if(myStack.peek().equals(CLOSED_CURLY))
			{
				if(isClosed(myStack, OPEN_BRACKET))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else if(myStack.peek().equals(CLOSED_PARA))
			{
				if(isClosed(myStack, OPEN_PARA))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
		}
		
		return false;
	}
	
	private static boolean isClosed(Stack<String> stack, String lookingFor)
	{
		while(!stack.isEmpty())
		{	
			if(lookingFor.equals(OPEN_BRACKET))
			{	
				stack.pop();
				
				if(stack.peek().equals(OPEN_CURLY) || stack.peek().equals(OPEN_PARA))
				{
					return false;
				}
				else if(stack.peek().equals(OPEN_BRACKET))
				{
					return true;
				}
				
				return true;
			}
			else if(lookingFor.equals(OPEN_CURLY) )
			{
				stack.pop();
				
				if(stack.peek().equals(OPEN_BRACKET) || stack.peek().equals(OPEN_PARA))
				{
					return false;
				}
				else if(stack.peek().equals(OPEN_CURLY))
				{
					return true;
				}
				
				return false;
			}
			else if(lookingFor.equals(OPEN_PARA))
			{
				stack.pop();
				if(stack.peek().equals(OPEN_BRACKET) || stack.peek().equals(OPEN_CURLY))
				{
					return false;
				}
				else if(stack.peek().equals(CLOSED_PARA))
				{
					return true;
				}
				
				return true;
			}
		}
		return false;
	}
}
