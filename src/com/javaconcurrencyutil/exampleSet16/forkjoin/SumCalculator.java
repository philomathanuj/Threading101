/**
 * 
 */
package com.javaconcurrencyutil.exampleSet16.forkjoin;

import java.util.concurrent.RecursiveTask;

/**
 * @author Anuj Sharma
 *
 */
public class SumCalculator extends RecursiveTask<Integer> {

	/**After this threshold has reached tasks will not be further forked and computation
	 *  will be performed in the currently running compute method. 
	 * */
	private static final long serialVersionUID = -1530503146445747136L;

	private static final int SEQUENTIAL_THRESHOLD=10;
	
	private int start;
	private int end;
	private int[] data;
	
	public SumCalculator(int start,int end, int[] data) {
		this.start=start;
		this.end=end;
		this.data=data;
	}
	@Override
	protected Integer compute() {
		int sum=0;
		if(end-start <=SEQUENTIAL_THRESHOLD){
		for (int i = start; i < end ; i++) {
			sum=sum+data[i];
		}
		}
		else{
			int mid=(start+end)/2;
			SumCalculator left=new SumCalculator(start,mid, data);
			SumCalculator right=new SumCalculator(mid, end, data);
			left.fork();
			right.fork();
			sum=right.join()+left.join();
			return sum; //right.compute()+left.join();
		}
		return sum;
	}

}
