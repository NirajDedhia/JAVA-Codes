/**
 * 
 */

/**
 * @author Niraj
 *
 */
public class ThreadWeight {

	public String getThreadName() {
		return threadName;
	}
	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	/**
	 * 
	 */
	String threadName;
	int weight;
	public ThreadWeight(String tName, int w) 
	{
		this.threadName=tName;
		this.weight=w;
	}
	

}
