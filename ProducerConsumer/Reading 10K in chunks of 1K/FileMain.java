import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


public class FileMain 
{
	
	private static LinkedList <byte[]> messageQueue = new LinkedList<byte[]>();

	public static void main(String[] args) 
	{

	ReadTenK rt10=new ReadTenK(messageQueue);
	Thread t1 = new Thread(rt10);
	ReadOneK rt1=new ReadOneK(messageQueue);
	Thread t2 = new Thread(rt1);

	t1.start();
	t2.start();
	
	}
}
