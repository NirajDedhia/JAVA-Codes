import java.util.*;


public class BridgeTruckMain {

	private static LinkedList <ThreadWeight> messageQueue = new LinkedList<ThreadWeight>();
	public static void main(String[] args) 
	{
	
		TruckProducer rt10=new TruckProducer(messageQueue);
		Thread t1 = new Thread(rt10);
		TruckConsumer rt1=new TruckConsumer(messageQueue);
		Thread t2 = new Thread(rt1);

		t1.start();
		t2.start();


	}

}
