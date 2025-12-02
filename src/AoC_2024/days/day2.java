package AoC_2024.days;

import AoC_2024.io.Fichier;

import java.util.Arrays;

public class day2 {

	public static void main(String[] args) {
		//part1();
		part2();
	}


	public static void part1() {
		String[] file = Fichier.lireLignes("src/resources/ex2Input2.txt");

		int count=0;

		int[][] fullTableau = new int[file.length][];

		for (int i = 0; i < file.length; i++) {
			String[] split = file[i].split(" ");
			fullTableau[i] = new int[split.length];
			for (int j = 0; j < split.length; j++) {
				fullTableau[i][j] = Integer.parseInt(split[j]);
			}
		}

		int problem = 0;

		for (int[] element : fullTableau) {

			problem= 0;
			if(element[0] < element[element.length-1]) {
				for (int j = 1; j < element.length; j++) {
					if(element[j-1] < element[j] && (element[j]-element[j-1]) <= 3) {

					} else {
						problem++;
					}
				}

				if(problem == 0) {
					count++;
				}

			} else if (element[0] > element[element.length-1]) {
				for (int j = 1; j < element.length; j++) {
					if(element[j-1] > element[j] && (element[j-1]-element[j]) <= 3) {

					} else {
						problem++;
					}
				}
				if(problem == 0) {
					count++;
				}
			}

		}

		System.out.println(count);

	}

	public static void part2() {
		String[] file = Fichier.lireLignes("src/resources/ex2Input2.txt");

		int count = 0;

		int[][] fullTableau = new int[file.length][];
		for (int i = 0; i < file.length; i++) {
		    String[] split = file[i].split(" ");
		    fullTableau[i] = new int[split.length];
		    for (int j = 0; j < split.length; j++) {
		        fullTableau[i][j] = Integer.parseInt(split[j]);
		    }
		}

		int upDown, diff, firstLast, diffValue, lastValueError;

		for (int i = 0; i < fullTableau.length; i++) {

		    upDown = 0;
		    diff = 0;
		    firstLast = 0;
		    lastValueError = 0;

		    for (int j = 1; j < fullTableau[i].length; j++) {

		        if (lastValueError == 0) {
		            diffValue = fullTableau[i][j] - fullTableau[i][j - 1];
		        } else {
		            diffValue = fullTableau[i][j] - fullTableau[i][j - 2];
		            lastValueError = 0;
		        }

		        if (Math.abs(diffValue) > 3) {
		            if (j == 1 || j == fullTableau[i].length - 1) {
		                firstLast++;
		                lastValueError = 1;
		            } else {
		                diff++;
		                lastValueError = 1;
		            }
		        } else if (diffValue < 0) {
		            upDown++;
		            lastValueError = 1;
		        } else if (diffValue == 0) {
		        	diff++;
		        }
		    }

		    if (diff == 0) {
		        if (firstLast == 0) {
		            if (upDown == 0 || upDown == 1) {
		                count++;
		            }
		        } else if (firstLast == 1) {
		            if (upDown == 0) {
		                count++;
		            }
		        }
		    }
		    System.out.println(i + " " + count + " " + diff + " " + upDown + " " + Arrays.toString(fullTableau[i]));
		}

		System.out.println(count);

	}





}
