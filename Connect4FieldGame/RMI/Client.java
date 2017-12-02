package HW132;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;


public class Client extends UnicastRemoteObject implements ClientIF, Runnable
{
	private static final long serialVersionUID = 1L;
	public ServerIF chatServer;
	public String name;
	public static Scanner s = new Scanner (System.in);
	public Client(String name, ServerIF chatServer) throws Exception
	{
		setName(name);
		this.chatServer = chatServer;
		chatServer.registerChatClient(name,this);
	}
	public void retrieveMessage (String msg) throws Exception
	{
		System.out.println(msg);
	}
	public int getIndex () throws Exception
	{	
		System.out.println("Enter Index: ");
		return (s.nextInt());
	}
	public String getName() throws Exception 
	{
		return this.name;
	}
	public void setName(String name) throws Exception 
	{
		this.name = name;
	}
	public void run() 
	{
		try 
		{
			chatServer.BroadcastMessage();
		} 
		catch (Exception e){}
		while(true){}
	}
	public static void main (String [] args) throws Exception
	{
		String url = "rmi://localhost/Server";
		ServerIF chatServer = (ServerIF)Naming.lookup(url);
		System.out.println("Enter your name : ");
		String name = s.nextLine();
		new Thread (new Client(name, chatServer)).start();
	}
}