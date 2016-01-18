import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ReadOneK implements Runnable {

	static byte[] bytes;
	
	private LinkedList <byte[]> messageQueue = new LinkedList<byte[]>();
	
	public ReadOneK(LinkedList <byte[]> bt) {
		
		this.messageQueue = bt;
		this.bytes=new byte[1024];
	}
	
	public void run()
	{
		int count=0;
			try {
				Thread.sleep(500);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			while(true)
			{
				synchronized(messageQueue)
				{	
					messageQueue.notify();
					bytes=messageQueue.remove();
					System.out.print("Value ==> ");
					System.out.println(new String(bytes));
					count=count+1;
					System.out.println("-----------------------------------------");
					System.out.println("count : " +count);
					System.out.println("-----------------------------------------");
			    		try {
						messageQueue.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}