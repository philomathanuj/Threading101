/**
 * 
 */
package com.javaconcurrencyutil.exampleSet6.semaphore;

import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Anuj Sharma A counting semaphore. Conceptually, a semaphore maintains
 *         a set of permits. Each acquire(), blocks if necessary until a permit
 *         is available, and then takes it. Each release() adds a permit,
 *         potentially releasing a blocking acquirer. However, no actual permit
 *         objects are used; the Semaphore just keeps a count of the number
 *         available and acts accordingly. Semaphores are often used to restrict
 *         the number of threads that can access some (physical or logical)
 *         resource. 
 */
public class SemaphoreExampleTest {
	
	static Logger log = Logger.getLogger("SemaphoreExampleTest");

	public static void main(String[] args) {
		ProductInquiryService service=new ProductInquiryService();
		Thread[] workerThreads={new SemaphoreThreadOne(service),
			new SemaphoreThreadTwo(service),
			new SemaphoreThreadThree(service),
			new SemaphoreThreadFour(service),
			new SemaphoreThreadFive(service),
			new SemaphoreThreadSix(service),
			};
		for (int i = 0; i < workerThreads.length; i++) {
			workerThreads[i].start();
		}
		
		for (int i = 0; i < workerThreads.length; i++) {
			try {
				workerThreads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		log.log(Level.INFO,"Main Thread Exiting ");
	}
	
	
	
	static class ProductInquiryService{
		final Logger log = Logger.getLogger("Product Service ");
		private final Semaphore semaphore;
		
		ProductInquiryService(){
			this.semaphore=new Semaphore(1,true);
		}
		
		public List<String> getProductLocation(String productId,String name){
			List<String> productLocations=null;
			try {	
				semaphore.acquire();
				Thread.sleep(1000);
				log.log(Level.INFO,name+" has acuqired the semaphore. Permits now "+semaphore.availablePermits());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			finally{
				semaphore.release();
				log.log(Level.INFO,name+" is going to release the semaphore with permits "+semaphore.availablePermits());
				
			}
			return productLocations;
			
			
		}

			}
	
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
	
	static class SemaphoreThreadFour extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadFour(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P004","ThreadFour");
		}
	}
	
	static class SemaphoreThreadFive extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadFive(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P005","ThreadFive");
		}
	}
	
	static class SemaphoreThreadSix extends Thread{
		private ProductInquiryService service;
		public SemaphoreThreadSix(ProductInquiryService service){
			this.service=service;
		}
		@Override
		public void run() {
			service.getProductLocation("P006","ThreadSix");
		}
	}

}
