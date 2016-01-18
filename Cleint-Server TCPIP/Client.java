
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;


public class Client 
{
	
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sock = new Socket("localhost", 1111);
		Scanner s = new Scanner(System.in);
		
		OutputStream out = sock.getOutputStream();
		InputStream in = sock.getInputStream();
		
		out.flush();
		byte[] buffer = new byte[1024];
		int n;
		String index="";
		
		if((n = in.read(buffer)) != -1) {
			System.out.println(new String(buffer, 0, n));
			String name = s.nextLine();
			out.write(name.getBytes());
		}
		while(true)
		{
			if((n = in.read(buffer)) != -1) 
			{
				
				if((new String(buffer, 0, n)).equals("DONE"))
				{
					System.out.println("File uploaded successfully");
				}
				else
				{				
					System.out.println(new String(buffer, 0, n));	
					index = s.nextLine();
					out.write(index.getBytes());
					System.out.println(new String(buffer, 0, in.read(buffer)));
					
				}
			}
		}
	}
}