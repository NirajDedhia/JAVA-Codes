import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Server 
{
	public static void main(String[] args) throws Exception 
	{
		byte[] buf = new byte[1024];
		DatagramSocket s = new DatagramSocket(1445);
		DatagramPacket p = new DatagramPacket(buf, buf.length);
		
		System.out.println("Waiting for connection");
		
		//Receiving
		s.receive(p);
		String message = new String(p.getData(),0,p.getLength());
		System.out.println("Received message from Client");
		System.out.println("Client : "+ message);
		
		//Sending
		buf = new byte[1024];
		buf = "Hi Client you are now connected :-)".getBytes();
		DatagramPacket p1 = new DatagramPacket(buf, buf.length, p.getAddress(), p.getPort());
		s.send(p1);
		
		//Closing DatagramSocket
		s.close();	
	}
}
