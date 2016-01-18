/** 
 * Deadlock.java 
 *
 * Version:	v 1.7  
 *     
 * 
 * Revision: 
 *     Initial revision 1.0 11/2/2015 sst and njd
 */


/**
 * The class Deadlock extends thread and creates deadlock between two threads.
 * 
 * @ author	Shweta Thakkar 
 * @ author	Niraj Dedhia
 */
 
public class Deadlock extends Thread{
   public static Object o1 = new Object();
   public static Object o2 = new Object();
   private String name;
   
   public Deadlock(String name)
   {
	   this.name=name;
   }
   
   public static void main(String args[]) {
	  Deadlock t1 = new Deadlock("first");
	  Deadlock t2 = new Deadlock("second");
	  t1.start();
	  t2.start();
   }

/**
 * 'run()' : In this method we check the name of the thread and call function1 if the name of the thread is "first"
 * else we call the "second" thread.
 *
 * @param	null  
 * @return	null
 */
   public void run()
   {	
	   if(this.name.equals("first"))
	   {
	   function1();
	   }
	   else
	   {
	   function2();
	   }
	   
   }

/**
 * 'function1()' : In this method Thread 1 enters the function1 and holds the lock and waits for 100ms.
 * Then it tries to take the second lock.
 *
 * @param	null  
 * @return	null
 */  
   
   public void function1() {
      
         synchronized (o1) {
            System.out.println("Thread 1: Holding lock 1");
            
            try {
				o1.wait(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            System.out.println("Thread 1: Waiting for lock 2");
            synchronized (o2) {
            System.out.println("Thread 1: Done");
             }
            
            }
         }
 /**
  * 
  * 'function1()' : In this method Thread 2 enters the function2, holds lock 2 and waits for 100ms.
  * Then it tries to take the first lock which is not released by thread 1 and hence deadlock occurs.
  * @param	null  
  * @return	null
  */
   public void function2() {
      
         synchronized (o2) {
            System.out.println("Thread 2: Holding lock 2");
            try {
				o2.wait(100);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
            System.out.println("Thread 2: Waiting for lock 1");
            synchronized (o1) {
               System.out.println("Thread 2: Done");
            }
         }
      }
   } 
