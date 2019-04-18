/**
 * 
 */
package com.javaconcurrencyutil.exampleSet7.reentrantlock;

public class UpdateRecordThread extends Thread{
	@Override
	public void run() {
		RecordUpdateUtility util=new RecordUpdateUtility();
		util.updateRecords("REC01","Processed");
	}
}
