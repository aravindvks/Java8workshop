package com.ak.j8.pocs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import org.openjdk.jmh.annotations.Benchmark;

public class StreamIntegers {

	private  void getFilteredDistinctListJava8(){
		List <Integer> distinctInts = Arrays.asList(1,2,3,4,5,0,7,8,9,9,9,1,3,4,5,6,7,8,8,8,4,33,2,23,4,5,6,7);
		
		distinctInts.stream()
					.distinct()  // Internal Filtering
					.collect(Collectors.toList()) // Collection
					.forEach(System.out::println); // Terminal Operation
	}
	

	private  void getFilteredDistinctListJava7(){
		List <Integer> ints = Arrays.asList(1,2,3,4,5,0,7,8,9,9,9,1,3,4,5,6,7,8,8,8,4,33,2,23,4,5,6,7);
		List <Integer> distinctInts = new ArrayList <Integer>();
		
		for(Integer integer : ints){
			if(!distinctInts.contains(integer)){
				distinctInts.add(integer);
			}
		}
		for(Integer integer : distinctInts){
			System.out.println(integer);
		}
	}
	
	@Benchmark
	public void getDistinctSortedListJava8(){
		List <Integer> ints = Arrays.asList(1,2,3,4,5,0,7,8,9,9,9,1,3,4,5,6,7,8,8,8,4,33,2,23,4,5,6,7);
		ints.stream()
			.distinct()  // Internal Filtering
			.sorted() // Lazy (On demand) Internal processing
			.collect(Collectors.toList()) // Collection
			.forEach(System.out::println); // Terminal Operation
	}
	
	@Benchmark
	public void getDistinctSortedListJava7(){
		List <Integer> ints = Arrays.asList(1,2,3,4,5,0,7,8,9,9,9,1,3,4,5,6,7,8,8,8,4,33,2,23,4,5,6,7);
		List <Integer> distinctInts = new ArrayList <Integer>();
				
		//External filtering with custom code
		for(Integer integer : ints){
			if(!distinctInts.contains(integer)){
				distinctInts.add(integer);
			}
		}
		//External sorting
		Collections.sort(distinctInts);
		// External loop process to print
		for(Integer integer : distinctInts){
			System.out.println(integer);
		}
	}
	
	private void getSquareOfDistictListJava8(){
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		numbers.stream().map( i -> i*i).distinct().collect(Collectors.toList()).forEach(System.out::println);
	}
	
	
		
	private void getStatsJava8(){
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		IntSummaryStatistics stats = numbers.stream().mapToInt((x) -> x).summaryStatistics();
		System.out.println("Average of all numbers 8: " + stats.getAverage());
	}
	
	private void getStatsJava7(){
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		int sum = 0;
		for(int i = 0 ; i < numbers.size(); i++){
			sum = sum + numbers.get(i);
		}
		System.out.println("Average of all numbers 7: " + sum/numbers.size());
	}
	
	public static void main(String[] args) {
		for(int i =0; i < 10000000 ; i++){
			new StreamIntegers().getDistinctSortedListJava7();
		}
	}
}
