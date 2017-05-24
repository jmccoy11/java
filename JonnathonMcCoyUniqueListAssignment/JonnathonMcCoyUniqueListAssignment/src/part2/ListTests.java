package part2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	
	/**
	 * Verifies the iterator() method and associated for-each loop.
	 */
	@Test
	public void testIterator()
	{
		//use next() & hasNext()
		Iterator<String> letterIterator = list.iterator();
		int count = 0;
		
		while (letterIterator.hasNext())
		{
			String letter = letterIterator.next();
			
			//verify ordering and all elements
			equals("Elements retrieved from iterator are out of order", letters[count], letter);
			
			count++;
		}
		
		equals("Number of elements retrieved from iteator is correct", letters.length, count);
		
		//can you use a for-each with no problem? (i.e. does this throw an exception?)
		Object[] contents = new Object[list.size()];
		int index = 0;
		for (String letter : list)
		{
			contents[index] = letter;
			index++;
		}
		
		//verify contents are still unique
		verifyUnique(contents);
	}

	/**
	 * Verifies the containsAll() set method.
	 */
	@Test
	public void testContainsAll()
	{
		Set<String> allContained = new HashSet<String>();
		Set<String> someContained = new HashSet<String>();
		
		allContained.add("a");
		allContained.add("z");
		allContained.add("d");
		allContained.add("q");
		allContained.add("r");
		
		someContained.add("a");
		someContained.add("~");
		someContained.add("d");
		someContained.add("^");
		someContained.add("*");
		
		isTrue("containsAll() returns false given a collection containing elements only in this list", 
				list.containsAll(allContained));
		isFalse("containsAll() returns true given a collection with only some of the elements in this list", 
				list.containsAll(someContained));
	}
	
	/**
	 * Verifies the addAll(collection) set method.
	 */
	@Test
	public void testAddAll()
	{
		list.clear();
		
		Set<String> newElements = new TreeSet<String>();
		
		String[] letterValues1 = {"a", "d", "q", "r", "z"};
		String[] letterValues2 = {"a", "b", "q", "e", "s"};
		String[] union = {"a", "d", "q", "r", "z", "b", "e", "s"};
		
		for (int i = 0; i < letterValues1.length; i++)
		{
			list.add(letterValues1[i]);
		}
		
		for (int i = 0; i < letterValues2.length; i++)
		{
			newElements.add(letterValues2[i]);
		}
		
		list.addAll(newElements);
		
		//size does not change
		equals("size() is not correct after calling addAll()", union.length, list.size());
		
		for (int i = 0; i < union.length; i++)
		{
			equals("elements cannot be found in the linked list after calling addAll()", union[i], list.get(i));
		}
		
		//verify that elements are unique
		verifyUnique(list.toArray());
		
		//size should stay the same for an empty collection
		list.clear();
		list.addAll(new UniqueList<String>());

		equals("size() is not correct after calling addAll()", 0, list.size());
	}
	
	/**
	 * Verifies the addAll(index, collection) set method.
	 */
	@Test
	public void testAddAllIndexed()
	{
		list.clear();
		
		Set<String> newElements = new TreeSet<String>();
		
		String[] letterValues1 = {"a", "d", "q", "r", "z"};
		String[] letterValues2 = {"a", "b", "q", "e", "s"};
		String[] union = {"a", "d", "q", "b", "e", "s", "r", "z"};
		
		for (int i = 0; i < letterValues1.length; i++)
		{
			list.add(letterValues1[i]);
		}
		
		for (int i = 0; i < letterValues2.length; i++)
		{
			newElements.add(letterValues2[i]);
		}
		
		//add at index 3 (location of element "d")
		list.addAll(3, newElements);
		
		//verify the new size (26 alphabetic characters plus the 5 above
		equals("size() is not correct after calling addAll(index)", union.length, list.size());
		
		for (int i = 0; i < union.length; i++)
		{
			equals("elements cannot be found in the linked list after calling addAll(index)", union[i], list.get(i));
		}
		
		//verify that elements are unique
		verifyUnique(list.toArray());
	}

	/**
	 * Verifies the removeAll() method.
	 */
	@Test
	public void testRemoveAll()
	{
		Set<String> consonantsSet = new HashSet<String>();
		String[] consonants = {"b", "c", "d", "f", "g", "h",
                               "j", "k", "l", "m", "n", "p",
                               "q", "r", "s", "t", "v", "w", "x",
                               "y", "z"};
		String[] vowels = {"a", "e", "i", "o", "u"};
		
		for (String consonant : consonants)
		{
			consonantsSet.add(consonant);
		}
		
		list.removeAll(consonantsSet);
		
		//we should only have 5 vowels left
		equals("size() is not correct after calling removeAll()", 5, list.size());
		
		for (String vowel : vowels)
		{
			isTrue("remaining elements in list after calling removeAll() is incorrect", list.contains(vowel));
		}
	}

	/**
	 * Verifies the retainAll() method.
	 */
	@Test
	public void testRetainAll()
	{
		Set<String> vowelsSet = new HashSet<String>();
		String[] vowels = {"a", "e", "i", "o", "u", "~"};
		
		for (String vowel : vowels)
		{
			vowelsSet.add(vowel);
		}
		
		list.retainAll(vowelsSet);
		
		//we should only have 5 vowels left
		equals("size() is not correct after calling retainAll()", 5, list.size());
		
		//ignore the ~ (which should not be in the list after this call)
		for (int i = 0; i < 5; i++)
		{
			isTrue("remaining elements in list after calling retainAll() is incorrect", list.contains(vowels[i]));
		}
	}

	/**
	 * Verifies the remove(index) method.
	 */
	@Test
	public void testRemoveIndexed()
	{
		//remove last
		list.remove(25);
		//24 elements left, so last index is 23
		notEquals("first element is not removed through remove()", "z", list.get(23)); 
		isFalse("list contains removed element after calling remove() on last element", list.contains("z"));
		
		list.remove(11);
		isFalse("list contains removed element after calling remove()", list.contains("l"));
		
		//remove first
		list.remove(0);
		notEquals("first element is not removed through remove()", "a", list.get(0));
		isFalse("list contains removed element after calling remove() on first element", list.contains("a"));
		
		list.clear();
		addData(list);
		
		//test bounds
		try 
		{
			list.remove(list.size()); //out of bounds!
			fail("List should have thrown an IndexOutOfBoundsException with remove() given index == size()");
		}
		catch (IndexOutOfBoundsException ex) { /* do nothing */ }
		
		try 
		{
			list.remove(-1); //out of bounds!
			fail("List should have thrown an IndexOutOfBoundsException with remove() given negative index");
		}
		catch (IndexOutOfBoundsException ex) { /* do nothing */ }
	}

	/**
	 * Verifies the indexOf() method.
	 */
	@Test
	public void testIndexOf()
	{
		//test bounds
		equals("indexOf() does not return index zero for the first element", 0, list.indexOf("a"));
		equals("indexOf() does not return last index for the first element", 25, list.indexOf("z"));
		equals("indexOf() does not return the correct index for an element in the list", 11, list.indexOf("l"));
		
		//test missing element
		equals("indexOf() does not return -1 for a missing element", -1, list.indexOf("~"));
	}

	/**
	 * Verifies the lastIndexOf() method.
	 */
	@Test
	public void lastIndexOf()
	{
		list.add("a");
		list.add("z");
		
		//test bounds (no duplicates)
		equals("lastIndexOf() does not return the last index for an element", 0, list.lastIndexOf("a"));
		equals("lastIndexOf() does not return the last index for an element", 25, list.lastIndexOf("z"));
		equals("lastIndexOf() does not return the last index for an element", 11, list.lastIndexOf("l"));
		
		//test missing element
		equals("lastIndexOf() does not return -1 for a missing element", -1, list.lastIndexOf("~"));
	}
}
