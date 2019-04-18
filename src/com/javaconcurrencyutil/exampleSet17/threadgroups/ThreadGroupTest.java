/**
 * 
 */
package com.javaconcurrencyutil.exampleSet17.threadgroups;

/**
 * @author Anuj Sharma
 *         <ul>
 *         <li>
 *         A thread group represents a set of threads. In addition, a thread
 *         group can also include other thread groups. The thread groups form a
 *         tree in which every thread group except the initial thread group has
 *         a parent. A thread is allowed to access information about its own
 *         thread group, but not to access information about its thread group's
 *         parent thread group or any other thread groups.</li>
 *         <li>
 *         ThreadGroup's: destroy() Destroys this thread group and all of its
 *         subgroups. checkAccess() Determines if the currently running thread
 *         has permission to modify this thread group.</li>
 *         <li>
 *         You can use ThreadFactory to set the Thread Name,Thread Group Name
 *         Creating new threads: New threads are created using a ThreadFactory.
 *         If not otherwise specified, a Executors.defaultThreadFactory() is
 *         used, that creates threads to all be in the same ThreadGroup and with
 *         the same NORM_PRIORITY priority and non-daemon status. By supplying a
 *         different ThreadFactory, you can alter the thread's name, thread
 *         group, priority, daemon status, etc. If a ThreadFactory fails to
 *         create a thread when asked by returning null from newThread, the
 *         executor will continue, but might not be able to execute any tasks.</li>
 *         <li>
 *         ThreadGroup is a class which was intended to provide information
 *         about a thread group. ThreadGroup API is weak and it doesn’t have any
 *         functionality that is not provided by Thread. Two of the major
 *         feature it had are to get the list of active threads in a thread
 *         group and to set the uncaught exception handler for the thread. But
 *         Java 1.5 has added
 *         setUncaughtExceptionHandler(UncaughtExceptionHandler eh) method using
 *         which we can add uncaught exception handler to the thread. So
 *         ThreadGroup is obsolete and hence not advised to use anymore.
 * 
 *         t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler(){
 * @Override public void uncaughtException(Thread t, Throwable e) {
 *           System.out.println("exception occured:"+e.getMessage()); }
 * 
 *           }); </li> <li>
 *           Deadlock is a programming situation where two or more threads are
 *           blocked forever, this situation arises with at least two threads
 *           and two or more resources.
 * 
 *           To analyze a deadlock, we need to look at the java thread dump of
 *           the application, we need to look out for the threads with state as
 *           BLOCKED and then the resources it’s waiting to lock, every resource
 *           has a unique ID using which we can find which thread is already
 *           holding the lock on the object.
 * 
 *           Avoid Nested Locks, Lock Only What is Required and Avoid waiting
 *           indefinitely are common ways to avoid deadlock situation, read this
 *           post to learn how to analyze deadlock in java with sample program.</li>
 *           <li>
 *           Analyze Thread Dump: To detect deadlock see threads with BLOCKED
 *           state. Thread Name: Name of the Thread Thread Priority: Priority of
 *           the thread Thread ID: Represents the unique ID of the Thread Thread
 *           Status: Provides the current thread state, for example RUNNABLE,
 *           WAITING, BLOCKED. While analyzing deadlock look for the blocked
 *           threads and resources on which they are trying to acquire lock.
 *           Thread callstack: Provides the vital stack information for the
 *           thread. This is the place where we can see the locks obtained by
 *           Thread and if it’s waiting for any lock.</li> <li>
 *           ava.util.Timer is a utility class that can be used to schedule a
 *           thread to be executed at certain time in future. Java Timer class
 *           can be used to schedule a task to be run one-time or to be run at
 *           regular intervals.
 * 
 *           java.util.TimerTask is an abstract class that implements Runnable
 *           interface and we need to extend this class to create our own
 *           TimerTask that can be scheduled using java Timer class.</li>
 *           <li>
 *           To get thread dump:
 *           thread.dumpStack() 
          Prints a stack trace of the current thread to the standard error stream.
 *           </li>
 *           <li>
 *           getAllStackTraces() 
          Returns a map of stack traces for all live threads.
 *           </li>
 *           <li>
 *            Returns an array of stack trace elements representing the stack dump of this thread.
 *           </li>
 *           <li>
 *           holdsLock(Object obj) 
          Returns true if and only if the current thread holds the monitor lock on the specified object.
 *           </li>
 *           <li>
 *           setContextClassLoader(ClassLoader cl) 
          Sets the context ClassLoader for this Thread.
 *           </li>
 *           <li>
 *           activeCount() 
          Returns the number of active threads in the current thread's thread group.
 *           </li>
 */
public class ThreadGroupTest {
	
	public static void main(String[] args) {
		ThreadGroup threadGroup=new ThreadGroup("workerThreads");
		
		Thread t1=new Thread(threadGroup,"ThreadOne"){
			@Override
			public void run() {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" has run");
			}
		};
		
		Thread t2=new Thread(threadGroup,"ThreadTwo"){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" has run");
			}
		};
		
		Thread t3=new Thread(threadGroup,"ThreadThree"){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+" has run");
			}
		};
		
		threadGroup.uncaughtException(t1,new Exception(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String getMessage() {
				return "Error Occurred";
			}
		});
		
		t1.start();
		t2.start();
		t3.start();
		System.out.println("Thread Group is : "+threadGroup.getName()+" and active Count is : "+threadGroup.activeCount());
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("After Execution of tasks.Thread Group is : "+threadGroup.getName()+" and active Count is : "+threadGroup.activeCount());
	}
}
