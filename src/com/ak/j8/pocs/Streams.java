package com.ak.j8.pocs;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Streams {
	public static void main(String args[]){
		List <String> myString = Arrays.asList("Java", "", "C", "C++", "Jav", "", "Javad");
		myString.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList()).forEach(System.out::println);
	}
}
