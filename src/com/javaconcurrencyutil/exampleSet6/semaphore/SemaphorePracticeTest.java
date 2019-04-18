package com.javaconcurrencyutil.exampleSet6.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;



public class SemaphorePracticeTest {
	static Logger log = Logger.getLogger("SemaphorePracticeTest");
	public static void main(String[] args) {
		ProductInquiryService service = new ProductInquiryService();
		Thread[] workerThread = { new SemaphoreThreadOne(service),
				new SemaphoreThreadTwo(service),
				new SemaphoreThreadThree(service)
		};

		for(int i=0;i< workerThread.length;i++){
			workerThread[i].start();
		}

		for(int i=0;i< workerThread.length;i++){
			try {
				workerThread[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.log(Level.INFO,"Main Thread Exiting");
	}

	static class ProductInquiryService{
		final Logger log = Logger.getLogger("Product service"); 
		private final Semaphore semaphore;

		public ProductInquiryService() {
			this.semaphore = new Semaphore(1,true);
		}

		public List<String> getProductLocation(String productId, String name) {
			List<String> productLocation =null;
			try {
				semaphore.acquire();
				Thread.sleep(1000);
				log.log(Level.INFO,name + " has aquired the Lock. Permits now are: "+semaphore.availablePermits() );
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				semaphore.release();
				log.log(Level.INFO,name+ " is going to release the lock.Permits now are :"+ semaphore.availablePermits());
			}


			return productLocation;
		}


	}
	// Static Thread class 001
	static class SemaphoreThreadOne extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadOne(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P001","ThreadOne");
		}

	}
	// Static Thread class -002
	static class SemaphoreThreadTwo extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadTwo(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P002","ThreadTwo");
		}

	}
	// static Thread class 003
	static class SemaphoreThreadThree extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadThree(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P003","ThreadThree");
		}

	}
}
