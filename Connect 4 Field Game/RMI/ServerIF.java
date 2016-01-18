package HW132;
import java.rmi.Remote;

public interface ServerIF extends Remote 
{
	void registerChatClient(String name, ClientIF chatClient) throws Exception;
	void BroadcastMessage () throws Exception;
}
