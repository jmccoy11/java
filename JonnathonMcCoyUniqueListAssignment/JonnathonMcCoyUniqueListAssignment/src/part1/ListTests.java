package part1;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Runs several tests against a custom UniqueList class that
 * uses the List<T> interface.
 * 
 * @author Josh Archer
 * @version 1.0
 */
public class ListTests extends TestClassFacade
{
	//the list to test
	private UniqueList<String> list;
	
	//bogus data to put in the list
	private String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h",
			                    "i", "j", "k", "l", "m", "n", "o", "p",
			                    "q", "r", "s", "t", "u", "v", "w", "x",
			                    "y", "z"};
	
	/**
	 * This will run before each test being ran.
	 */
	@Before 
	public void setup()
	{
		list = new UniqueList<String>();
		
		addData(list);
	}
	
	//helper function that adds the letters from the array above to the list
	//we are testing
	private void addData(List<String> listToGrow)
	{
		for (String letter : letters)
		{
			listToGrow.add(letter);
		}
	}
	
	/**
	 * Verifies that add() works correctly and that all elements are unique.
	 */
	@Test
	public void testAdd()
	{
		//verify initial adding of elements
		equals("size() should match the number of elements added through add()", letters.length, list.size());
		
		//convert contents to object array (to be compatible with toArray() test below)
		Object[] contents = new Object[list.size()];
		for (int i = 0; i < list.size(); i++)
		{
			contents[i] = list.get(i);
		}
		
		//verify that elements are unique
		verifyUnique(contents);
	}
	
	//helper function that verifies all elements in the list are unique
	private void verifyUnique(Object[] contents)
	{
		Set<String> set = new HashSet<String>();
		
		for (int i = 0; i < contents.length; i++)
		{
			set.add(contents[i].toString());
		}
		
		equals("elements in list are not unique", list.size(), set.size());
	}

	/**
	 * Verifies the get() method.
	 */
	@Test
	public void testGet()
	{
		//check access to elements
		for (int i = 0; i < letters.length; i++)
		{
			equals("get(i) should return the element at index i", letters[i], list.get(i));
		}
		
		//check bounds
		try 
		{
			list.get(list.size()); //out of bounds!
			fail("List should have thrown an IndexOutOfBoundsException with get() given index == size()");
		}
		catch (IndexOutOfBoundsException ex) { /* do nothing */ }
		
		try 
		{
			list.get(-1); //out of bounds!
			fail("List should have thrown an IndexOutOfBoundsException with get() given negative index");
		}
		catch (IndexOutOfBoundsException ex) { /* do nothing */ }
	}

	/**
	 * Verifies the size() and isEmpty() methods.
	 */
	@Test
	public void testSizeAndIsEmpty()
	{
		//verify initial additions
		equals("isEmpty() should be false when elements are present", false, list.isEmpty());
		equals("size() should match the number of elements added through add()", letters.length, list.size());
		
		//verify empty list size
		list = new UniqueList<String>();
		equals("size() should be 0 when no elements are present", 0, list.size());
		
		//verify size increases
		list.add("a");
		equals("size() should be 1 after a single call to add()", 1, list.size());
	}

	/**
	 * Verifies the clear() method.
	 */
	@Test
	public void testClear()
	{
		list.clear();
		equals("size() should be 0 after calling clear()", 0, list.size());
		equals("isEmpty() should be false after calling clear()",  true, list.isEmpty());
	}

	/**
	 * Verifies the remove(element) method. This is not testing the remove(index) method.
	 */
	@Test
	public void testRemove()
	{
		//remove first
		list.remove("a");
		equals("first element is not removed through remove()", "b", list.get(0));
		isFalse("list contains removed element after calling remove() on first element", list.contains("a"));
		
		//remove last
		list.remove("z");
		//24 elements left, so last index is 23
		equals("first element is not removed through remove()", "y", list.get(23)); 
		isFalse("list contains removed element after calling remove() on last element", list.contains("z"));
		
		list.remove("l");
		isFalse("list contains removed element after calling remove()", list.contains("l"));
		
		//try to remove element that is not in the list
		isFalse("remove() returns true for element not in the list", list.remove("~"));
	}
	
	/**
	 * Verifies the contains() method.
	 */
	@Test
	public void testContains()
	{
		//check for a missing element
		isFalse("contains() is true for a missing element", list.contains("~"));
		
		//check edge cases and a value in the middle
		Assert.assertTrue("contains() returns false for the first element", list.contains("a"));
		Assert.assertTrue("contains() returns false for an element", list.contains("l"));
		Assert.assertTrue("contains() returns false for last element", list.contains("z"));
	}

	/**
	 * Verifies the toArray() method.
	 */
	@Test
	public void testToArray()
	{
		Object[] contents = list.toArray();
		
		arrayEquals("toArray() result does not match list contents", contents, letters);
		
		//verify that elements are unique
		verifyUnique(contents);
	}
}
