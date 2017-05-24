package part1;

import org.junit.Assert;

/**
 * This creates an easier interface to interact with
 * JUnit 4. 
 * 
 * @author Josh Archer
 * @version 1.0
 */
public class TestClassFacade
{
	/**
	 * Hides the Assert.assertEquals() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to print out if the test fails
	 * @param expected the expected value
	 * @param actual the actual value
	 */
	public void equals(String message, Object expected, Object actual)
	{
		Assert.assertEquals(message, expected, actual);
	}

	/**
	 * Hides the Assert.assertNotEquals() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to print out if the test fails
	 * @param expected the expected value
	 * @param actual the actual value
	 */
	public void notEquals(String message, Object expected, Object actual)
	{
		Assert.assertNotEquals(message, expected, actual);
	}
	
	/**
	 * Hides the Assert.assertTrue() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to print out if the test fails
	 * @param expression the expression that should be true
	 */
	public void isTrue(String message, boolean expression)
	{
		Assert.assertTrue(message, expression);
	}

	/**
	 * Hides the Assert.assertFalse() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to print out if the test fails
	 * @param expression the expression that should be false
	 */
	public void isFalse(String message, boolean expression)
	{
		Assert.assertFalse(message, expression);
	}

	/**
	 * Hides the Assert.assertNotEquals() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to print out if the test fails
	 * @param expected the expected value
	 * @param actual the actual value
	 */
	public void arrayEquals(String message, Object[] expected, Object[] actual)
	{
		Assert.assertArrayEquals(message, expected, actual);
	}
	
	/**
	 * Hides the Assert.fail() method and follows the same rules
	 * as that method.
	 * 
	 * @param message the message to display with the test failure
	 */
	public void fail(String message)
	{
		Assert.fail(message);
	}
}
