package com.tdd.string.calculator;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.rules.ExpectedException;
import org.junit.Rule;
import org.junit.After;
import org.junit.Before;

public class TestStringCalculator {
	StringCalculator calc = null;

	@Before
	public void setUp() {
		calc = new StringCalculator();
	}

	@After
	public void tearDown() {
		calc = null;
	}

	@Rule
	public ExpectedException exception = ExpectedException.none();

	// @Test
	public void canNotTakeMoreThan2Numbers() {
		exception.expect(RuntimeException.class);
		System.out.println("canNotTakeMoreThan2Numbers ");
		calc.add("3,4,5,6");
	}

	@Test
	public void forEmptyStringReturn0() {
		System.out.println("forEmptyStringReturn0 ");
		assertEquals(0, calc.add(""));
	}

	@Test
	public void for2NumsReturnSum() {
		System.out.println("for2NumsReturnSum ");
		assertEquals(9, calc.add("4,5"));
	}

	@Test
	public void for1NumsReturnNum1() {
		System.out.println(" for1NumsReturnNum1 ");
		assertEquals(4, calc.add("4"));
	}

	@Test
	public void for1NumsReturnSum() {
		System.out.println(" for1NumsReturnSum  ");
		assertEquals(4, calc.add("4,"));
	}

	@Test
	public void handleUnknownAmountOfNumbers() {
		System.out.println(" handleUnknownAmountOfNumbers  ");
		assertEquals(19, calc.add("4,5,7,3"));
	}

	@Test
	public void handleNewLinesBetweenNumbers() {
		System.out.println(" handleNewLinesBetweenNumbers ");
		assertEquals(10, calc.add("1\n6,3"));
	}

	@Test
	public void supportDifferentDelimiters() {
		System.out.println(" supportDifferentDelimiters ");
		assertEquals(13, calc.add("//;\n6;7"));
		assertEquals(14, calc.add("//*\n6*8"));
		assertEquals(15, calc.add("//-\n7-8"));
		assertEquals(15, calc.add("//|\n7|8"));
	}

	@Test
	public void canNotTakeNegativeNumbers() {
		System.out.println(" canNotTakeNegativeNumbers ");
		exception.expect(RuntimeException.class);
		exception.expectMessage("negatives not allowed: [-3, -2]");
		calc.add("-3,4,5,-2");
	}

	@Test
	public void numbersGreaterThan1000ShouldBeIgnored() {
		System.out.println(" numbersGreaterThan1000ShouldBeIgnored ");
		assertEquals(8, calc.add("//;\n8;1001"));
	}

	@Test
	public void supportAnyLengthDelimiters() {
		System.out.println(" supportAnyLengthDelimiters ");
		assertEquals(18, calc.add("//[***]\n8***10"));
		assertEquals(29, calc.add("//[|||]\n8|||10|||11"));
	}

	@Test
	public void supportMultipleDelimiters() {
		System.out.println(" supportMultipleDelimiters ");
		assertEquals(20, calc.add("//[;][,]\n8;10,2"));
		assertEquals(20, calc.add("//[*][?]\n8*10?2"));

	}

	@Test
	public void supportMultipleDelimitersMultipleLengths() {
		System.out.println(" supportMultipleDelimitersMultipleLengths ");
		assertEquals(20, calc.add("//[;;][,]\n8;;10,2"));
		assertEquals(20, calc.add("//[***][??]\n8***10??2"));
	}

}