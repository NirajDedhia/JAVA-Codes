import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetAddress;

public class Client 
{
	public static void main(String[] args) throws Exception
	{
		byte[] buf = new byte[1024];
		DatagramSocket s = new DatagramSocket();		
		DatagramPacket p;
		// Connecting to server
		InetAddress address = InetAddress.getByName("localhost");
		
		// Sending
		buf = "Hi, Trying to Connect!!!".getBytes();
		p = new DatagramPacket (buf,buf.length,address,1445);
		s.send(p);
		
		//Receiving
		buf = new byte[1024];
		p = new DatagramPacket(buf, buf.length);
		s.receive(p);
		String message = new String(p.getData(),0,p.getLength());
		System.out.println("Received message from Client");
		System.out.println("Server : "+ message);
		
		//Closing DatagramSocket
		s.close();
	}
}
