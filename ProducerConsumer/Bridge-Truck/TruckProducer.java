import java.util.*;

import javax.xml.ws.handler.MessageContext;


public class TruckProducer implements Runnable {

	private static LinkedList <ThreadWeight> messageQueue = new LinkedList<ThreadWeight>();
	private int truck=0;
	private static int w;
	Random r = new Random();
	private static int remainingWeight=0;
	private int count1=0;
	public TruckProducer(LinkedList <ThreadWeight> messageQueue) 
	{
		this.messageQueue=messageQueue;
	}

	public void run()
	{
		
		while(count1<50)
		{	
			synchronized(messageQueue)
			{
			messageQueue.notify();
			while(messageQueue.size()<=3 && bridgeCapacity()+(w=r.nextInt(100000-100)+100) < 200000)
			{	
				truck=truck+1;
				messageQueue.add(new ThreadWeight("Truck "+truck,w));
			}
			System.out.println("----------------------------------------------------------------");
			System.out.println("BRIDGE Current Condition : ");
			
			int we=0;
			for(int i=0;i<messageQueue.size();i++)
				we = we + messageQueue.get(i).getWeight();

			System.out.println("Total number of Trucks : "+messageQueue.size()+"; Total Weight on bridge : "+ we);
			System.out.println("----------------------------------------------------------------");
			for(int i=0;i<messageQueue.size();i++)
				System.out.println(messageQueue.get(i).getThreadName()+ " of Weight " + messageQueue.get(i).getWeight());

			try {
				messageQueue.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			}
			count1+=1;
		}
	}
	
	public int bridgeCapacity()
	{
		int sum=0;
		for(int i =0;i<messageQueue.size();i++)
			sum=sum+(messageQueue.get(i).getWeight());
		return sum;
	}
}
