
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable
{
	public static int nOP=2;
	public static ServerSocket server;
	public static String name = "";
	public static Socket sock[] =  new Socket[nOP];
	public static Socket soc;
	public static Thread t[] = new Thread[nOP];
	
	public Server(Socket soc)
	{
		this.soc = soc;
	}
	
	public static void main(String[] args) throws Exception
	{				
		server = new ServerSocket(1111);
		for(int i=0; i<nOP; i++)
		{
			System.out.println("Waiting for connections with Client "+(i+1)); 
			Socket sock2 = server.accept();
			System.out.println("Connection estalished! Player "+(i+1)+" is now connected :-)");
			
			InputStream in = sock2.getInputStream();
			OutputStream out = sock2.getOutputStream();
			
			out.write("Enter your Name : ".getBytes());
			
			String name="";
			
			byte buffer[]= new byte[1024];
			name = new String(buffer, 0, in.read(buffer));
			System.out.println("Name : " + name);
			t[i] = new Thread(new Server(sock2));		// Creating Thread
			sock[i] = sock2;
		}
		System.out.println("ALL Players are now in sync");
		for(int i=0; i<nOP; i++)
		{
			t[i].start();	// Starting Thread
		}
		String answer="";
		int n;
		byte[] buffer=new byte[1024];
		OutputStream out;
		InputStream in;
		int f = 0;
		int whi=0;
		while(whi <8)
		{
			f=f%nOP;
			
			out = sock[f].getOutputStream();
			in = sock[f].getInputStream();
			out.write(("Do you want to send any data ?").getBytes());
	
			while((n = in.read(buffer)) == -1)
			{}
			
			answer = (new String(buffer, 0, n));
			if(answer.equals("YES"))
			{
				out.write(("DONE").getBytes());
			}
			else
			{
				out.write(("OK").getBytes());
			}
		f++;
		whi++;
		}

		System.out.println("FINALLY DONE WITH ALL...");
	}
	
	public void run()
	{
		InputStream in = null;
		OutputStream out=null;
		int i = 0;
		try 
		{
			in = soc.getInputStream();
			out= soc.getOutputStream();
		} catch (IOException e1) 
		{	
			e1.printStackTrace();
		}
		while(true)
		{
			while(i == 0)
			{		
			}
		}
	} 

}