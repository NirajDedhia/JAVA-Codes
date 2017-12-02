



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
/**
 * The class Client will create a client and will display the board
 * and takes the column number from the client. 
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */

public class Client {
	static public String s1 = ("==========================================================="+"\n"
			+ "                       GAME BEGINS NOW                      " +"\n"
			+ "===========================================================" +"\n"
			+"                Here is the Board:                     " + "\n"
			+"    -------------------------------------------        "+"\n");
	
	public static void main(String[] args) throws UnknownHostException, IOException 
	{

		DatagramSocket server=new DatagramSocket();
		
		// Send message to Server
		byte[] buf = new byte[256];
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String naam = " HIIIIIIII ";
		buf = naam.getBytes();
		InetAddress address = InetAddress.getByName("localhost");
		DatagramPacket packet=new DatagramPacket(buf,buf.length,address,4445);
		server.send(packet);
		
		// Reading Player Name
		System.out.println("Enter Your Name: ");
		naam = in.readLine();
		System.out.println(s1);
		buf = naam.getBytes();
		packet=new DatagramPacket(buf,buf.length,address,4445);
		server.send(packet);
		
		while(true)
		{
			byte[] buf1=new byte[1024];
			DatagramPacket packet1=new DatagramPacket(buf1,buf1.length);			
			server.receive(packet1);
			String receive1=new String(packet1.getData(),0,packet1.getLength());

			// Checking that player has WON or NOT ?
			if(receive1.equals("Won"))
			{
				packet1=new DatagramPacket(buf1,buf1.length);
				server.receive(packet1);
				receive1=new String(packet1.getData(),0,packet1.getLength());
				System.out.println(receive1);
				break;
			}
			else
			{
				// Receiving CURRENT Board
				System.out.println(receive1);
				
				// Asking next move to player
				System.out.println("Enter Column Number : ");
				byte[] buf2 = new byte[256];
				String index=in.readLine();

				// Sending column number to Server
				buf2 = index.getBytes();
				packet=new DatagramPacket(buf2,buf2.length,address,4445);
				server.send(packet);
				
				// Receiving UPDATED Board
				packet1=new DatagramPacket(buf1,buf1.length);
				server.receive(packet1);
				receive1=new String(packet1.getData(),0,packet1.getLength());
				System.out.println(receive1);
			}
		}
	}
}
