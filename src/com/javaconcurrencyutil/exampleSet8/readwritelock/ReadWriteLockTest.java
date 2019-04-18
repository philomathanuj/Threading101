/**
 * 
 */
package com.javaconcurrencyutil.exampleSet8.readwritelock;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Anuj Sharma
 *
 */
public class ReadWriteLockTest {
	
	
	public static void main(String[] args) {
		Thread[] readerThreads={new Thread(){
			@Override
			public void run() {
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> books=catalog.getBooksBasedOnCategory("Fiction");
				System.out.println("Reader Thread One Catalog [Fiction]  "+books.toString());
			}
		},
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(6000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> books=catalog.getBooksBasedOnCategory("Fiction");
							System.out.println("Reader Thread Two [Fiction] "+books.toString());
			}
		},
		new Thread(){
			@Override
			public void run() {
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> books=catalog.getBooksBasedOnCategory("Non-Fiction");
			System.out.println("Reader Thread Three [Non-Fiction] "+books.toString());
			}
		},
		new Thread(){
			@Override
			public void run() {
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<String> books=catalog.getBooksBasedOnCategory("Non-Fiction");
			System.out.println("Reader Thread Four [Non-Fiction] "+books.toString());
			}
		}
		};
		
		Thread[] writerThreads={new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> booksToBeUpdated=new ArrayList<String>();
				booksToBeUpdated.add("Memoirs of a Geisha");
				catalog.updateCatalog("Fiction",booksToBeUpdated);
				System.out.println(" Writer Thread One [Fiction] "+catalog.getBooksBasedOnCategory("Fiction").toString());
			}
		},
		new Thread(){
			@Override
			public void run() {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> booksToBeUpdated=new ArrayList<String>();
				booksToBeUpdated.add("India After Gandhi");
				catalog.updateCatalog("Non-Fiction",booksToBeUpdated);
				
			System.out.println("Write Thread Two [Non-Fiction] "+catalog.getBooksBasedOnCategory("Non-Fiction").toString());
			}
		},
		new Thread(){
			@Override
			public void run() {
				LibraryCatalog catalog=LibraryCatalog.getInstance();
				List<String> booksToBeUpdated=new ArrayList<String>();
				booksToBeUpdated.add("How to Solve It");
				try {
					Thread.sleep(4000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				catalog.updateCatalog("Non-Fiction",booksToBeUpdated);
				
			System.out.println("Writer Thread Three [Non-Fiction] "+catalog.getBooksBasedOnCategory("Non-Fiction"));
			}
		}
		};
		
		for (int i = 0; i < readerThreads.length; i++) {
			readerThreads[i].start();
		}
		
		for (int i = 0; i < writerThreads.length; i++) {
			writerThreads[i].start();
		}
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(LibraryCatalog.getInstance().getBooksBasedOnCategory("Fiction"));
		System.out.println(LibraryCatalog.getInstance().getBooksBasedOnCategory("Non-Fiction"));
	}
	
	
}
