package org.b_forloops;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ForLoopsExampleJ {

	
	public static int largestPalindromOfTwoDigitNumber() {
		List<Integer> palindroms = new ArrayList<Integer>();
		for (int i = 100; i < 1000; i++) {
			for (int j = i; j < 1000; j++) {
				int productInt = i * j;
				String productStr = Integer.toString(productInt);
				if(productStr.equals(new StringBuffer(productStr).reverse().toString())) {
					palindroms.add(productInt);
				}
			}
		}
		return Collections.max(palindroms);
	}
	
	public static void main(String [] args) {
		System.out.println(largestPalindromOfTwoDigitNumber());
	}
}
