package hackApplication;

import java.util.regex.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.TreeMap;
import java.math.BigInteger;
import java.util.*;

public class Strings {

	public static void main(String[] args) {

		Scanner scn = new Scanner(System.in);
		String s = scn.nextLine();

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (map.containsKey(c)) {
				int cnt = map.get(c);
				map.put(c, ++cnt);
			} else {
				map.put(c, 1);
			}
		}
		map = MapUtil.sortByValue(map);

		Character nine = (Character) map.keySet().toArray()[0];
		Character eight = (Character) map.keySet().toArray()[1];
		Character seven = (Character) map.keySet().toArray()[2];
		Character six = (Character) map.keySet().toArray()[3];
		Character five = (Character) map.keySet().toArray()[4];
		Character four = (Character) map.keySet().toArray()[5];
		Character three = (Character) map.keySet().toArray()[6];
		Character two = (Character) map.keySet().toArray()[7];
		Character one = (Character) map.keySet().toArray()[8];
		Character zero = (Character) map.keySet().toArray()[9];

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c == nine) {
				s = s.replace(nine, '9');
			} else if (c == eight) {
				s = s.replace(eight, '8');
			} else if (c == seven) {
				s = s.replace(seven, '7');
			} else if (c == six) {
				s = s.replace(six, '6');
			} else if (c == five) {
				s = s.replace(five, '5');
			} else if (c == four) {
				s = s.replace(four, '4');
			} else if (c == three) {
				s = s.replace(three, '3');
			} else if (c == two) {
				s = s.replace(two, '2');
			} else if (c == one) {
				s = s.replace(one, '1');
			} else if (c == zero) {
				s = s.replace(zero, '0');
			}
		}

		BigInteger sum = new BigInteger("0");
		BigInteger result = new BigInteger("0");
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
			String number = matcher.group();
			sum = sum.add(new BigInteger(number));
			number = "0";
		}
		System.out.println(sum);

	}

}
