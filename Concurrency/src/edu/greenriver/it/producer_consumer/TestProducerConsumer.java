package edu.greenriver.it.producer_consumer;

public class TestProducerConsumer 
{

	public static void main(String[] args) 
	{
		FileReader producer = new FileReader("dictionary.txt");
		ConsolePrinter consumer = new ConsolePrinter();
		
		producer.start();
		consumer.start();
	}
}
