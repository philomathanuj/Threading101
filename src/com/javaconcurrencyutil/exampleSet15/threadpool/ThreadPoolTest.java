/**
 * 
 */
package com.javaconcurrencyutil.exampleSet15.threadpool;


/**
 * @author Anuj Sharma
 *
 */
public class ThreadPoolTest {
	public static void main(String[] args) {
		ThreadPool threadPool = new ThreadPool();
		Runnable[] tasks = { new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					System.out.println("Task one has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("Task two has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(3000);
					System.out.println("Task three has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(4000);
					System.out.println("Task four has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("Task five has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(6000);
					System.out.println("Task six has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(7000);
					System.out.println("Task seven has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(8000);
					System.out.println("Task eight has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		},

		new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(9000);
					System.out.println("Task nine has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(10000);
					System.out.println("Task ten has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(11000);
					System.out.println("Task elevan has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		}, new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(12000);
					System.out.println("Task twelve has run");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}
		} };

		for (Runnable task : tasks) {
			threadPool.execute(task);
		}
        try {
			Thread.sleep(15000);
			threadPool.shutDown();
			System.out.println("Exiting Main Thread");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		

	}
}
