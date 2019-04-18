package com.javaconcurrencyutil.exampleSet7.reentrantlock;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RecordUpdateUtility{
		 Lock lock=new ReentrantLock();
		static Logger log=Logger.getLogger("RecordUpdateUtility");
		
		public  void updateRecords(String recordId,String newRecordValue){
			if(this.lock.tryLock()){
				log.log(Level.INFO,"Lock has been acquired");
			}
			else{
				log.log(Level.INFO,"Lock not available");
			}
			
			// do some logging/validations
			updateReordsInDB(recordId,newRecordValue);
		}

		private void updateReordsInDB(String recordId, String newRecordValue) {
			try{
			log.log(Level.INFO,"Record has been updated successfully");
			}
			catch(Exception exception){
				log.log(Level.INFO,"Error occured while updating record",exception);	
			}
			finally{
				lock.unlock();
				log.log(Level.INFO,"Lock has been released");
			}
			
		}
	}