/**
 * 
 */
package com.javaconcurrencyutil.exampleSet14.phaser;

import java.util.concurrent.Phaser;

/**
 * @author Anuj Sharma
 *		Phaser: 
 *         A reusable synchronization barrier, similar in functionality to
 *         CyclicBarrier and CountDownLatch but supporting more flexible usage.
 *         Registration. Unlike the case for other barriers, the number of
 *         parties registered to synchronize on a phaser may vary over time.
 *         Tasks may be registered at any time (using methods register(),
 *         bulkRegister(int), or forms of constructors establishing initial
 *         numbers of parties), and optionally deregistered upon any arrival
 *         (using arriveAndDeregister()). As is the case with most basic
 *         synchronization constructs, registration and deregistration affect
 *         only internal counts; they do not establish any further internal
 *         bookkeeping, so tasks cannot query whether they are registered.
 *         (However, you can introduce such bookkeeping by subclassing this
 *         class.)
 * 
 *         Synchronization. Like a CyclicBarrier, a Phaser may be repeatedly
 *         awaited. Method arriveAndAwaitAdvance() has effect analogous to
 *         CyclicBarrier.await. Each generation of a phaser has an associated
 *         phase number. The phase number starts at zero, and advances when all
 *         parties arrive at the phaser, wrapping around to zero after reaching
 *         Integer.MAX_VALUE. The use of phase numbers enables independent
 *         control of actions upon arrival at a phaser and upon awaiting others,
 *         via two kinds of methods that may be invoked by any registered party:
 *         • Arrival. Methods arrive() and arriveAndDeregister() record arrival.
 *         These methods do not block, but return an associated arrival phase
 *         number; that is, the phase number of the phaser to which the arrival
 *         applied. When the final party for a given phase arrives, an optional
 *         action is performed and the phase advances. These actions are
 *         performed by the party triggering a phase advance, and are arranged
 *         by overriding method onAdvance(int, int), which also controls
 *         termination. Overriding this method is similar to, but more flexible
 *         than, providing a barrier action to a CyclicBarrier. • Waiting.
 *         Method awaitAdvance(int) requires an argument indicating an arrival
 *         phase number, and returns when the phaser advances to (or is already
 *         at) a different phase. Unlike similar constructions using
 *         CyclicBarrier, method awaitAdvance continues to wait even if the
 *         waiting thread is interrupted. Interruptible and timeout versions are
 *         also available, but exceptions encountered while tasks wait
 *         interruptibly or with timeout do not change the state of the phaser.
 *         If necessary, you can perform any associated recovery within handlers
 *         of those exceptions, often after invoking forceTermination. Phasers
 *         may also be used by tasks executing in a ForkJoinPool, which will
 *         ensure sufficient parallelism to execute tasks when others are
 *         blocked waiting for a phase to advance.
 * 
 *         Termination. A phaser may enter a termination state, that may be
 *         checked using method isTerminated(). Upon termination, all
 *         synchronization methods immediately return without waiting for
 *         advance, as indicated by a negative return value. Similarly, attempts
 *         to register upon termination have no effect. Termination is triggered
 *         when an invocation of onAdvance returns true. The default
 *         implementation returns true if a deregistration has caused the number
 *         of registered parties to become zero. As illustrated below, when
 *         phasers control actions with a fixed number of iterations, it is
 *         often convenient to override this method to cause termination when
 *         the current phase number reaches a threshold. Method
 *         forceTermination() is also available to abruptly release waiting
 *         threads and allow them to terminate.
 * 
 *         Tiering. Phasers may be tiered (i.e., constructed in tree structures)
 *         to reduce contention. Phasers with large numbers of parties that
 *         would otherwise experience heavy synchronization contention costs may
 *         instead be set up so that groups of sub-phasers share a common
 *         parent. This may greatly increase throughput even though it incurs
 *         greater per-operation overhead.
 * 
 *         In a tree of tiered phasers, registration and deregistration of child
 *         phasers with their parent are managed automatically. Whenever the
 *         number of registered parties of a child phaser becomes non-zero (as
 *         established in the Phaser(Phaser,int) constructor, register(), or
 *         bulkRegister(int)), the child phaser is registered with its parent.
 *         Whenever the number of registered parties becomes zero as the result
 *         of an invocation of arriveAndDeregister(), the child phaser is
 *         deregistered from its parent.
 * 
 *         Monitoring. While synchronization methods may be invoked only by
 *         registered parties, the current state of a phaser may be monitored by
 *         any caller. At any given moment there are getRegisteredParties()
 *         parties in total, of which getArrivedParties() have arrived at the
 *         current phase (getPhase()). When the remaining
 *         (getUnarrivedParties()) parties arrive, the phase advances. The
 *         values returned by these methods may reflect transient states and so
 *         are not in general useful for synchronization control. Method
 *         toString() returns snapshots of these state queries in a form
 *         convenient for informal monitoring.
 * 
 *         Sample usages:
 * 
 *         A Phaser may be used instead of a CountDownLatch to control a
 *         one-shot action serving a variable number of parties. The typical
 *         idiom is for the method setting this up to first register, then start
 *         the actions, then deregister
 * 
 * 
 *
 */
public class PhaserTest {

	public static void main(String[] args) {
		Phaser phaser = new Phaser();// phaser starts with one party
		phaser.register();
		PhaserTest phaserTest = new PhaserTest();
		Thread[] tasks = { phaserTest.new FirstTask(phaser),
				phaserTest.new SecondTask(phaser),
				phaserTest.new ThirdTask(phaser) };// three parties are added
		for (int i = 0; i < tasks.length; i++) {
			tasks[i].start();
		}
		// so parties have to wait until all the parties have arrived the
		// phaser.
		// when we deregister then parties will wait for only 3 parties to
		// arrive phaser.
		phaser.arriveAndDeregister();

	}

	class FirstTask extends Thread {
		private Phaser phaser;

		public FirstTask(Phaser phaser) {
			this.phaser = phaser;
		}

		@Override
		public void run() {
			phaser.register();
			try {
				Thread.sleep(1000);
				System.out.println("First Task Is Complete");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println("Arrive and Await Adavance is over");

		}
	}

	class SecondTask extends Thread {
		private Phaser phaser;

		public SecondTask(Phaser phaser) {
			this.phaser = phaser;
		}

		@Override
		public void run() {
			phaser.register();
			try {
				Thread.sleep(2000);
				System.out.println("Second Task Is Complete");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println("Second Arrive and Await Adavance is over");

		}
	}

	class ThirdTask extends Thread {
		private Phaser phaser;

		public ThirdTask(Phaser phaser) {
			this.phaser = phaser;
		}

		@Override
		public void run() {
			phaser.register();
			try {
				Thread.sleep(3000);
				System.out.println("Third Task Is Complete");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			phaser.arriveAndAwaitAdvance();
			System.out.println("Third Arrive and Await Adavance is over");

		}
	}

}
