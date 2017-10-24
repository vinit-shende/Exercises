package com.tdd.string.runner;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import com.tdd.string.calculator.TestStringCalculator;

public class TestRunner {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestStringCalculator.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println("All Junit test cases successful? " + result.wasSuccessful());
	}
}
