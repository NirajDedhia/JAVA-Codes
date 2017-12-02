import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class ReadTenK implements Runnable {

	static byte[] bytes;
	private LinkedList <byte[]> messageQueue = new LinkedList<byte[]>();
	long startTime, endTime;
	public ReadTenK(LinkedList <byte[]> bt) {
	
		this.messageQueue = bt;
		this.bytes=new byte[1000];
	}
	
	static File file = new File("file.txt");
	
	public void run()
	{
		      try {
			    	InputStream is = new FileInputStream(file);
			    	while(is.read(bytes)>=0)
			    	{
						synchronized(messageQueue)
						{	
							endTime = System.currentTimeMillis();
							System.out.println("Time taken "+(endTime-startTime));
							messageQueue.notify();
							while(messageQueue.size()<10)
									messageQueue.add(bytes);
							
							try {
								startTime = System.currentTimeMillis();
								messageQueue.wait();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						 }
					  }
		      }
		      catch (IOException e1) 
		      {
				e1.printStackTrace();
		      } 
			
		}
}