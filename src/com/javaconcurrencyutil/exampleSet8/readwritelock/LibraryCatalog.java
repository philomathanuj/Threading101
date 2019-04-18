package com.javaconcurrencyutil.exampleSet8.readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public  class LibraryCatalog{
	static ReadWriteLock readWriteLock=new ReentrantReadWriteLock();
	static Map<String,List<String>> catalog;
	private static LibraryCatalog instance;
	
	public static LibraryCatalog getInstance(){
		if(instance==null){
			synchronized (readWriteLock) {
				if(instance==null){
					catalog=new ConcurrentHashMap<String, List<String>>();
					List<String> fictionBookList=new ArrayList<String>();
					fictionBookList.add("The Tao of Physics");
					fictionBookList.add("Head First Java");
					fictionBookList.add("Midnight's Children");
					
					fictionBookList.add("The Kite Runner");
					
					List<String> nonFictionBooks=new ArrayList<String>();
					nonFictionBooks.add("Audacity of Hope");
					nonFictionBooks.add("Dreams From My Father");
					nonFictionBooks.add("The Accidental Prime Minister");
					nonFictionBooks.add("The Man Who Knew Infinity");
					
					catalog.put("Fiction",fictionBookList);
					catalog.put("Non-Fiction",nonFictionBooks);
					instance=new LibraryCatalog();
					return instance;
				}
			}
		}
		return instance;
	}
	
	private LibraryCatalog() {
	
	}
	
	public List<String> getBooksBasedOnCategory(String categoryId){
		readWriteLock.readLock().lock();
		List<String> books=new ArrayList<String>();
		try{
		books=catalog.get(categoryId);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		readWriteLock.readLock().unlock();
		}
		return books;
	}
	
	public void updateCatalog(String categoryId,List<String> books){
		readWriteLock.writeLock().lock();
		try{
		books=LibraryCatalog.catalog.put(categoryId, books);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
		readWriteLock.writeLock().unlock();
		}
		
	}
	
	
}