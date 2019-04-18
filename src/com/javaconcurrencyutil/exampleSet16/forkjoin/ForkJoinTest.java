/**
 * 
 */
package com.javaconcurrencyutil.exampleSet16.forkjoin;

import java.util.concurrent.ForkJoinPool;

/**
 * The Fork/Join Framework in Java 7 is designed for work that can be broken down into smaller tasks and 
 * the results of those tasks combined to produce the final result.Fork/Join offers serious gains 
 * for solving problems that involve recursion.
 * @author Anuj Sharma
 *
 */
public class ForkJoinTest {
	
	public static void main(String[] args) {
		ForkJoinPool forkJoinPool=new ForkJoinPool(4);
		int[] data=new int[100];
		for (int i = 0; i < 100; i++) {
			data[i]=i+1;
		}
		int sum=forkJoinPool.invoke(new SumCalculator(0,100,data));
		System.out.println("Sum of numbers from 1 to 100 is : "+sum);
	}

}
