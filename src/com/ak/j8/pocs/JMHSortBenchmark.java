package com.ak.j8.pocs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

@State(Scope.Thread)
public class JMHSortBenchmark {

	List<Integer> arrayList;
	int[] array;
	Random random;

	@Setup(Level.Trial)
	public void init() {
		random = new Random();
		array = new int[1000];
		arrayList = new ArrayList<Integer>();
		for (int i = 0; i < 10000000; i++) {
			int randomNumber = random.nextInt();
			array[i] = randomNumber;
			arrayList.add(new Integer(randomNumber));
		}
	}

	@Benchmark
	public void arraysSort() {
		Arrays.sort(array);
	}

	@Benchmark
	public void collectionsSort() {
		Collections.sort(arrayList);
	}

	public static void main(String[] args) throws RunnerException {

		Options options = new OptionsBuilder()
				.include(JMHSortBenchmark.class.getSimpleName()).threads(1)
				.forks(1).shouldFailOnError(true).shouldDoGC(true)
				.jvmArgs("-server").build();
		new Runner(options).run();

	}
}
