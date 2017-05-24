package edu.greenriver.it.hashing;

public class HashingPractice 
{

	public static void main(String[] args) 
	{

		MyHashTable<String> wordSet = new MyHashTable<String>();

		//add a few elements
		wordSet.add("red");
		wordSet.add("black");
		wordSet.add("blue");
		wordSet.add("green");
		wordSet.add("white");
		wordSet.add("pink");
		
		System.out.println("Before rehash(): " + wordSet.toString());
		
		wordSet.add("purple");
		
		//view the contents of the internal array
		System.out.println("After rehash(): " + wordSet.toString());
		
		//practice with remove() and contains()
		System.out.println("remove(red): " + wordSet.remove("red"));
		System.out.println("remove(brown): " + wordSet.remove("brown"));

		System.out.println("contains(white): " + wordSet.contains("white"));
		System.out.println("contains(cyan): " + wordSet.contains("cyan"));
		
		System.out.println("size(): " + wordSet.size());
		System.out.println("isEmpty(): " + wordSet.isEmpty());
		
		for(String word : wordSet)
		{
			System.out.println(word);
			wordSet.remove("green");
		}
	}

}
