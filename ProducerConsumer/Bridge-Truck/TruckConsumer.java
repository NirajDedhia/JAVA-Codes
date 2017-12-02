import java.util.LinkedList;


public class TruckConsumer implements Runnable {

	private static LinkedList <ThreadWeight> messageQueue = new LinkedList<ThreadWeight>();
	
	public TruckConsumer(LinkedList <ThreadWeight> messageQueue) 
	{
		this.messageQueue=messageQueue;
	}
	
	public void run()
	{
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		while(messageQueue.size()>=0)
		{
			synchronized(messageQueue)
			{	
				messageQueue.notify();
				ThreadWeight tw;
				tw=messageQueue.remove();
				System.out.println("Truck Leaving ==> "+ tw.getThreadName()+ " of Weight " + tw.getWeight());

				try {
					messageQueue.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		
	}
	
	
}
