package com.ak.j8.pocs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamStringsList {
	
	private  void getListFilteredWithoutEmpty(){
		List <String> myString = Arrays.asList("Java", "", "C", "C++", "Jav", "", "Javad");
		myString.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList()).forEach(System.out::println);
	}
	
	private  void getListSorted(){
		List <String> myString = Arrays.asList("Java", "", "C", "C++", "Jav", "", "Javad");
		myString.stream().sorted().collect(Collectors.toList()).forEach(System.out::println);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new StreamStringsList().getListSorted();
	}

}
