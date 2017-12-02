import java.io.BufferedReader;

/**
 * The class ServerController will create a server which will connect to all the clients and make them run
 * in sync.
 *
 * @ author	Niraj Dedhia
 * @ author	Shweta Thakkar
 */

import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServerController implements Runnable{
	

		public static int nOP=2;
		static public DatagramSocket server;

		static public DatagramPacket packet4;

		static private Player p[] = new Player[nOP];
		public Player player;
		static int i=0;
		public static char [][] board;
		public static int active = 0;
		public static String name = "";
		
		public ServerController(Player player, char[][] board) 
		{
			this.player = player;
			this.board = board;
		}
		
		
		public static void main(String[] args) throws Exception
		{
			
			char [][] board=new char[9][25];
			
			for(int i=0;i<9;i++)
				for(int j=8;j>=i;j--)
					board[i][j]='o';
			for(int i=0;i<9;i++)
				for(int j=9;j<=16;j++)
					board[i][j]='o';
			for(int i=0;i<9;i++)
				for(int j=17;j<25-i;j++)
					board[i][j]='o';
			
		Thread t[] = new Thread[nOP];
		server = new DatagramSocket(4445);
		DatagramPacket packet;
		char piece=' ';
		String boardString="";
		byte[] buf=new byte[256];
		packet=new DatagramPacket(buf,buf.length);
		InetAddress address[] = new InetAddress[nOP];
		int port [] = new int[nOP];
		
		for(int i=0; i<nOP; i++)
		{
			try
			{
			// Read from Client	
			System.out.println("waiting for connections with player "+(i+1));
			server.receive(packet);
			System.out.println("HIIII "+new String (packet.getData(),0,packet.getLength()));
			System.out.println("connection estalished! Player "+(i+1)+" is now connected :-)");	
			
			 // Receiving name from Player
			 server.receive(packet);
			 String receive=new String (packet.getData(),0,packet.getLength());
				System.out.println("Name from Player: "+receive);
				
			// Allocating 
				if(i==0)
					piece = '@';
				else if(i==1)
					piece = '#';
				else if(i==2)
					piece = '$';
				else if(i==3)
					piece = '*';
				
				p[i] = new Player(receive,piece,server);
				t[i] = new Thread(new ServerController(p[i],board)); 
				address[i] = packet.getAddress();
				port[i] = packet.getPort();
			}
			catch(Exception e)
			{}
			System.out.println("ALL Players are now in sync");		
		}
		
		for(int i =0;i<nOP;i++)
		{
			t[i].start();
		}

		int index=0;
		int f = 0;
		Connect4FieldController cc;
		while(true)
		{
			f=f%nOP;

			byte[] buf1=new byte[256];
			boardString = charToString(board);
			buf1=boardString.getBytes();
			
			packet4=new DatagramPacket(buf1,buf1.length,address[f],port[f]);
			server.send(packet4);
			
			byte[] buf2=new byte[1024];
			DatagramPacket packet1=new DatagramPacket(buf2,buf2.length);
			
			server.receive(packet1);
			String receive1=new String(packet1.getData(),0,packet1.getLength());
			//System.out.println(receive1);
			
			cc = new Connect4FieldController(board, Integer.parseInt(receive1), p[f]);
			index = cc.playTheGame();
			// Sending NEW Board
			boardString = charToString(board);
			buf1=boardString.getBytes();
			packet4=new DatagramPacket(buf1,buf1.length,address[f],port[f]);
			server.send(packet4);
			if(index==2)
			{
				System.out.println("Player "+ p[f].getName()+" Won");
				String s = "Won";
				packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[f],port[f]);
				server.send(packet4);
				s = "Congratulations !!! "+ p[f].getName() +" You won";
				packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[f],port[f]);
				server.send(packet4);
				break;
			}
			if(index==1)
			{
				System.out.println("GAME DRAW");
				break;
			}
			f++;	
		}
		
		String s = "Won";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+1)%nOP],port[(f+1)%nOP]);
		server.send(packet4);
		s = "Sorry You loose "+"\n"+ p[f].getName()+" Won!!";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+1)%nOP],port[(f+1)%nOP]);
		server.send(packet4);		
		
		/***
		s = "Won";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+2)%nOP],port[(f+2)%nOP]);
		server.send(packet4);
		s = "Sorry You loose "+"\n"+ p[f].getName()+" Won!!";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+2)%nOP],port[(f+2)%nOP]);
		server.send(packet4);		
		
		s = "Won";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+3)%nOP],port[(f+3)%nOP]);
		server.send(packet4);
		s = "Sorry You loose "+"\n"+ p[f].getName()+" Won!!";
		packet4=new DatagramPacket(s.getBytes(),s.getBytes().length,address[(f+3)%nOP],port[(f+3)%nOP]);
		server.send(packet4);		
		*/
		}
		
		public void run()
		{
			while(true)
			{
				while(i == 0)
				{		
				}
			}
		}
		public static String charToString(char[][] board)
		{
			String charToString="";
			for(int i=0;i<9;i++)
			{
			for(int j=0;j<25;j++)
			{
				if(board[i][j]!=0)
					charToString += " " + board[i][j];
				else
					charToString+=" "+" ";
			}
			charToString += "\n";
			}
			return charToString;
		}
}