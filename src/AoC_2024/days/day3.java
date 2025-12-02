package AoC_2024.days;

import AoC_2024.io.Fichier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class day3 {

	public static void main(String[] args) {

		// part1();
		part2();

	}

	public static void part1() {
		String[] file = Fichier.lireLignes("src/AoC_2024/resources/ex3Input2.txt");
		String input = Arrays.toString(file);
		String regex = "mul\\(\\d{1,3}[,]\\d{1,3}\\)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		/*
		 * if (matcher.find()) { String match = matcher.group(); // Retrieves the
		 * matched substring System.out.println("Matched substring: " + match); } else {
		 * System.out.println("No match found."); }
		 */

		ArrayList<String> matches = new ArrayList<>();

		while (matcher.find()) {
			matches.add(matcher.group());
		}

		String[] mulArray = matches.toArray(new String[0]);

		int mul = 0;
		int total = 0;

		for (int i = 0; i < mulArray.length; i++) {
			mul = 0;
			mulArray[i] = mulArray[i].substring(4, mulArray[i].length() - 1);
			mul = Integer.parseInt(mulArray[i].substring(0, mulArray[i].indexOf(",")))
					* Integer.parseInt(mulArray[i].substring(mulArray[i].indexOf(",") + 1));
			total += mul;
			System.out.println(mulArray[i] + " : " + mul);
		}

		System.out.println("total = " + total);

	}

	public static void part2() {
		String[] file = Fichier.lireLignes("src/AoC_2024/resources/ex3Input2.txt");
		String input = Arrays.toString(file);
		String regex = "mul\\(\\d{1,3}[,]\\d{1,3}\\)|don't\\(\\)|do\\(\\)";

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		ArrayList<String> matches = new ArrayList<>();

		while (matcher.find()) {
			matches.add(matcher.group());
		}

		String[] mulArray = matches.toArray(new String[0]);

		int mul = 0;
		int total = 0;
		boolean activated = true;

		for (int i = 0; i < mulArray.length; i++) {
			System.out.print(mulArray[i]);
			if (mulArray[i].equals("don't()")) {
				activated = false;
			} else if (mulArray[i].equals("do()")) {
				activated = true;
			}

			if (activated && !mulArray[i].equals("do()")) {
				mul = 0;
				mulArray[i] = mulArray[i].substring(4, mulArray[i].length() - 1);
				mul = Integer.parseInt(mulArray[i].substring(0, mulArray[i].indexOf(",")))
						* Integer.parseInt(mulArray[i].substring(mulArray[i].indexOf(",") + 1));
				total += mul;
			}

			System.out.println(" : " + total);

		}

		System.out.println("total = " + total);
	}

}
