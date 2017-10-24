package com.tdd.string.calculator;

import java.util.*;

public class StringCalculator {

	Map<String, String> map = new HashMap<String, String>();

	public int add(String numbers) {

		String input = numbers;
		// String delimiter = ",|\n";
		String delimiter = "";
		String finalD = "";
		List<String> delims = new ArrayList<String>();

		input = prepareDelimiterList(numbers, input, delims);
		
		// default delimiter
		if (delims.size() == 0) {

			finalD = "|,|\n";

		} else { // prepare delimiter from list of delimiters
			finalD = prepareDelimiterString(finalD, delims);
		}

		// System.out.println("delimiter:"+ finalD.substring(1));
		return add(input, finalD.substring(1));

	}

	private String prepareDelimiterString(String finalD, List<String> delims) {
		String delimiter;
		for (int j = 0; j < delims.size(); j++) {
			delimiter = delims.get(j);
			String tempDe = "";
			for (int i = 0; i < delimiter.length(); i++) {
				String tempD = "" + delimiter.charAt(i);
				tempDe = tempDe + "\\" + tempD;

			}
			finalD = finalD + "|" + tempDe;
		}
		return finalD;
	}

	private String prepareDelimiterList(String numbers, String input, List<String> delims) {
		String delimiter;
		if (numbers != null && !numbers.isEmpty()) {
			// check multiple delimiters and prepare list
			if (numbers.startsWith("//[")) {
				boolean flag1 = false;
				boolean flag2 = false;
				String delimT = "";
				for (int i = numbers.indexOf("["); i < numbers.lastIndexOf("]") + 1; i++) {
					if (numbers.substring(i, i + 1).equals("[")) {
						flag1 = true;
					} else if (numbers.substring(i, i + 1).equals("]")) {
						flag2 = true;
					} else if (flag1) {
						delimT = delimT + numbers.substring(i, i + 1);
					}
					if (flag1 && flag2) {
						delims.add(delimT);
						flag1 = false;
						flag2 = false;
						delimT = "";
					}

				}
				// “//[*][%]\n1*2%3”
				int indNewLine = numbers.indexOf("\n");
				// delimiter = numbers.substring(3,indNewLine-1);
				input = numbers.substring(indNewLine + 1);
			}
			// check single delimiter and put in list
			else if (numbers.startsWith("//")) {
				int indNewLine = numbers.indexOf("\n");
				delimiter = numbers.substring(2, indNewLine);
				delims.add(delimiter);
				input = numbers.substring(indNewLine + 1);
			}

		}
		return input;
	}

	private int add(String numbers, String delimiter) {
		int sum = 0;
		List<Integer> negNums = new ArrayList<Integer>();

		if (numbers.isEmpty()) {
			return 0;
		}

		String[] nums = numbers.split(delimiter);

		// if (nums.length > 2) {
		// throw new RuntimeException();
		// }

		for (int i = 0; i < nums.length; i++) {

			String temp = "0";

			if (!nums[i].isEmpty()) {
				temp = nums[i];
			}
			int tnum = Integer.parseInt(temp);
			if (tnum < 0) {
				negNums.add(tnum);
			} else if (tnum <= 1000) {
				sum = sum + tnum;
			}
		}
		if (negNums.size() > 0) {
			throw new RuntimeException("negatives not allowed: " + negNums);

		}

		return sum;
	}
}