package HW132;
import java.rmi.Remote;


public interface ClientIF extends Remote 
{
	void retrieveMessage (String msg) throws Exception; 
	String getName() throws Exception;
	int getIndex() throws Exception;
}
