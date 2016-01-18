package HW113;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client {
	static public String s1 = ("==========================================================="+"\n"
			+ "                       GAME BEGINS NOW                      " +"\n"
			+ "===========================================================" +"\n"
			+"                Here is the Board:                     " + "\n"
			+"    -------------------------------------------        "+"\n");
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("localhost", 1111);
		Scanner s = new Scanner(System.in);
		
		
		OutputStream out = sock.getOutputStream();
		InputStream in = sock.getInputStream();
		//out.write("Hi there!".getBytes());
		out.flush();
		byte[] buffer = new byte[1024];
		int n;
		
		if((n = in.read(buffer)) != -1) {
			System.out.println(new String(buffer, 0, n));
			String name = s.nextLine();
			out.write(name.getBytes());
		}
		System.out.println(s1);
		String index;
		while(true)
		{
			if((n = in.read(buffer)) != -1) 
			{
				
				if(new String(buffer, 0, n).equals("Won"))
				{
					System.out.println("===========================================================");
					System.out.println("             " + new String(buffer, 0, in.read(buffer)));
					System.out.println("===========================================================");
				}
				else
				{				
					System.out.println(new String(buffer, 0, n));	
					System.out.println(new String(buffer, 0, in.read(buffer)));
					//System.out.println(new String(buffer, 0, in.read(buffer)));
					System.out.println("Enter Column Number : ");
					index = s.nextLine();
					//System.out.println("index "+index);
					out.write(index.getBytes());
					//System.out.println("index "+index);
					System.out.println(new String(buffer, 0, in.read(buffer)));
					
				}
			}
		}
	}
}