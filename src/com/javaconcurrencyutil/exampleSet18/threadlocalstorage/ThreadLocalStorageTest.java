/**
 * 
 */
package com.javaconcurrencyutil.exampleSet18.threadlocalstorage;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Anuj Sharma
 *         ################################################################3
 *         Atomic Access
 * 
 *         In programming, an atomic action is one that effectively happens all
 *         at once. An atomic action cannot stop in the middle: it either
 *         happens completely, or it doesn't happen at all. No side effects of
 *         an atomic action are visible until the action is complete.
 * 
 *         We have already seen that an increment expression, such as c++, does
 *         not describe an atomic action. Even very simple expressions can
 *         define complex actions that can decompose into other actions.
 *         However, there are actions you can specify that are atomic:
 * 
 *         Reads and writes are atomic for reference variables and for most
 *         primitive variables (all types except long and double). Reads and
 *         writes are atomic for all variables declared volatile (including long
 *         and double variables). Atomic actions cannot be interleaved, so they
 *         can be used without fear of thread interference. However, this does
 *         not eliminate all need to synchronize atomic actions, because memory
 *         consistency errors are still possible. Using volatile variables
 *         reduces the risk of memory consistency errors, because any write to a
 *         volatile variable establishes a happens-before relationship with
 *         subsequent reads of that same variable. This means that changes to a
 *         volatile variable are always visible to other threads. What's more,
 *         it also means that when a thread reads a volatile variable, it sees
 *         not just the latest change to the volatile, but also the side effects
 *         of the code that led up the change.
 * 
 *         Using simple atomic variable access is more efficient than accessing
 *         these variables through synchronized code, but requires more care by
 *         the programmer to avoid memory consistency errors. Whether the extra
 *         effort is worthwhile depends on the size and complexity of the
 *         application.
 *         #########################################################
 *         ################# Good Article on ThreadLocal Use: One of the rarely
 *         known features in Java among developers is Thread-local storage. The
 *         idea is simple, and the need for it comes in scenarios where we need
 *         data that is, well, local for the thread. For example, if we have two
 *         threads that refer to the same global variablem but we want them to
 *         have separate values independently initialized of each other.
 * 
 *         Most major programming languages have implementations of the concept.
 *         For example C++11 has the thread local keyword, and Ruby has chosen
 *         an API approach. Java has also an implementation of the concept with
 *         java.lang.ThreadLocal<T> and with subclass
 *         java.lang.InheritableThreadLocal<T> since version 1.2, so nothing new
 *         and shiny here. Let's say that for some reason we need to have an
 *         Long specific for our thread. Using Thread local that would simply
 *         be: public class ThreadLocalExample {
 * 
 *         public static class SomethingToRun implements Runnable {
 * 
 *         private ThreadLocal threadLocal = new ThreadLocal();
 * @Override public void run() {
 *           System.out.println(Thread.currentThread().getName() + " " +
 *           threadLocal.get());
 * 
 *           try { Thread.sleep(2000); } catch (InterruptedException e) { }
 * 
 *           threadLocal.set(System.nanoTime());
 *           System.out.println(Thread.currentThread().getName() + " " +
 *           threadLocal.get()); } }
 * 
 *           public static void main(String[] args) { SomethingToRun
 *           sharedRunnableInstance = new SomethingToRun();
 * 
 *           Thread thread1 = new Thread(sharedRunnableInstance); Thread thread2
 *           = new Thread(sharedRunnableInstance);
 * 
 *           thread1.start(); thread2.start(); }
 * 
 *           } One possible sample run of the following code will result in:
 * 
 *           Thread-0 null
 * 
 *           Thread-0 132466384576241
 * 
 *           Thread-1 null
 * 
 *           Thread-1 132466394296347 At the beginning the value is set to null
 *           to both threads, obviously each of them works with separate values
 *           since after setting the value to System.nanoTime() on Thread-0 it
 *           will not have any effect on the value of Thread-1 exactly as we
 *           wanted, a thread scoped long variable. One nice side effect is a
 *           case where the thread calls multiple methods from various classes.
 *           They will all be able to use the same thread scoped variable
 *           without major API changes. Since the value is not explicitly passed
 *           through one might argue it difficult to test and bad for design,
 *           but that is a separate topic altogether.
 * 
 * 
 *           In what areas are popular frameworks using Thread Locals? Spring,
 *           being one of the most popular frameworks in Java, uses ThreadLocals
 *           internally for many parts - easily demonstrated by a simple github
 *           search. Most of the usages are related to the current user's
 *           actions or information. This is actually one of the main uses for
 *           ThreadLocals in JavaEE world, storing information for the current
 *           request like in RequestContextHolder :
 * 
 *           private static final ThreadLocal<RequestAttributes>
 *           requestAttributesHolder = new
 *           NamedThreadLocal<RequestAttributes>("Request attributes");
 * 
 * 
 *           Or the current JDBC connection user credentials in
 *           UserCredentialsDataSourceAdapter. If we get back on
 *           RequestContextHolder, we can use this class to access all of the
 *           current request information from anywhere in our code. A common use
 *           case for this is LocaleContextHolder, which helps us store the
 *           current user's locale. Mockito uses it to store the current
 *           "global" configuration and if we take a look at any framework out
 *           there there is a high chance we'll find it as well. Thread Locals
 *           and Memory Leaks Now that we've learned about this awesome little
 *           feature, let's use it all over the place! Well, we can do that, but
 *           if you try a few Google searches, you'll find that most posts out
 *           there claim that ThreadLocal is evil. That's not exactly true. It's
 *           a nice utility, but in some contexts, you could easily accidentally
 *           create a memory leak. “Can you cause unintended object retention
 *           with thread locals? Sure you can. But you can do this with arrays
 *           too. That doesn’t mean that thread locals (or arrays) are bad
 *           things. Merely that you have to use them with some care. The use of
 *           thread pools demands extreme care. Sloppy use of thread pools in
 *           combination with sloppy use of thread locals can cause unintended
 *           object retention, as has been noted in many places. But placing the
 *           blame on thread locals is unwarranted.” - Joshua Bloch It is very
 *           easy to create a memory leak in your server code using ThreadLocal
 *           if it runs on an application server. ThreadLocal context is
 *           associated to the thread where it runs, and will be garbaged once
 *           the thread is dead. Modern app servers use pool of threads instead
 *           of creating new ones on each request, meaning you can end up
 *           holding large objects indefinitely in your application. Since the
 *           thread pool is from the app server, our memory leak could remain
 *           even after we unload our application. The fix for this is simple -
 *           free up resources you do not need. One other ThreadLocal misuse is
 *           API design. Often I have seen use of RequestContextHolder(that
 *           holds ThreadLocal) all over the place, like the DAO layer, for
 *           example. Later on, if one were to call the same DAO methods outside
 *           a request, for instance and scheduler, he would get a very bad
 *           surprise. Even though the variables in ThreadLocal are local to the
 *           thread they are very much global in your code. So, if you want to
 *           avoid maintenance developers hunting you down and taking their
 *           revenge, make sure you really need this thread scope before you use
 *           it.
 *           ################################################################
 *           ###################################
 * 
 *           "Each thread holds an implicit reference to its copy of a
 *           thread-local variable as long as the thread is alive and the
 *           ThreadLocal instance is accessible; after a thread goes away, all
 *           of its copies of thread-local instances are subject to garbage
 *           collection (unless other references to these copies exist). If your
 *           application or (if you are talking about request threads) container
 *           uses a thread pool that means that threads don't die. If necessary,
 *           you would need to deal with the thread locals yourself. The only
 *           clean way to do this is to call the ThreadLocal.remove() method.
 * 
 *           There are two reasons you might want to clean up thread locals for
 *           threads in a thread pool:
 * 
 *           to prevent memory (or hypothetically resource) leaks, or to prevent
 *           accidental leakage of information from one request to another via
 *           thread locals. Thread local memory leaks should not normally be a
 *           major issue with bounded thread pools since any thread locals are
 *           likely to get overwritten eventually; i.e. when the thread is
 *           reused. However, if you make the mistake of creating a new
 *           ThreadLocal instances over and over again (instead of using a
 *           static variable to hold a singleton instance), the thread local
 *           values won't get overwritten, and will accumulate in each thread's
 *           threadlocals map. This could result in a serious leak.
 * 
 *           Assuming that you are talking about thread locals that are created
 *           / used during a webapp's processing of an HTTP request, then one
 *           way to avoid the thread local leaks is to register a
 *           ServletRequestListener with your webapp's ServletContext and
 *           implement the listener's requestDestroyed method to cleanup the
 *           thread locals for the current thread.
 * 
 *           Note that in this context you also need to consider the possibility
 *           of information leaking from one request to another.
 *           ################
 *           ############################################################
 *
 */
public class ThreadLocalStorageTest {
	private static AtomicInteger nextId = new AtomicInteger(0);
	private static volatile AtomicInteger nonThreadLocalId = new AtomicInteger(
			0);
	private static ThreadLocal<Integer> transactionId = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return nextId.getAndIncrement();
		};
	};

	private static Integer getTransactionId() {
		return transactionId.get();
	}

	private static Integer getNextNonThreadLocalTransactionId() {
		return nonThreadLocalId.getAndIncrement();
	}

	private static Integer getNonThreadLocalTransactionId() {
		return nonThreadLocalId.get();
	}

	public static void main(String[] args) {
		Thread workerThreadOne = new Thread() {

			@Override
			public void run() {
				getNextNonThreadLocalTransactionId();
				System.out.println(getTransactionId()
						+ "is the transaction id for thread one"
						+ getNonThreadLocalTransactionId());
			}
		};
		Thread workerThreadTwo = new Thread() {
			public void run() {
				getNextNonThreadLocalTransactionId();
				System.out.println(getTransactionId()
						+ " is the transaction id for thread two"
						+ getNonThreadLocalTransactionId());
			};
		};

		Thread workerThreadThree = new Thread() {

			public void run() {
				getNextNonThreadLocalTransactionId();
				System.out.println(getTransactionId()
						+ " is the transaction id for thread three"
						+ getNonThreadLocalTransactionId());
			};
		};

		Thread workerThreadFour = new Thread() {
			public void run() {
				getNextNonThreadLocalTransactionId();
				System.out.println(getTransactionId()
						+ " is the transaction id for thread four"
						+ getNonThreadLocalTransactionId());

			};
		};

		Thread workerThreadFive = new Thread() {
			public void run() {
				getNextNonThreadLocalTransactionId();
				System.out.println(getTransactionId()
						+ " is the transaction id for thread five"
						+ getNonThreadLocalTransactionId());
			};
		};

		workerThreadOne.start();
		workerThreadTwo.start();
		workerThreadThree.start();
		workerThreadFour.start();
		workerThreadFive.start();
	}
}
