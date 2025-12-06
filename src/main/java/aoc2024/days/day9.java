package aoc2024.days;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class day9 {

	public static void main(String[] args) {
		
		//part1();
		//part1ForReal();
		
		
        // using String[]
		part2();
		//	Duration : 0.921 sec 
		
		// using int[]
		part2NewIdea();	
		//	Duration : 0.427 sec 
		 

        //part2StringBuilder();
	}

    private static void part1() {
		//String[] string = Fichier.lireLignes("src/AoC_2024/resources/day8Input1.txt");
		Path file = Paths.get("src/AoC_2024/resources/day9Input2.txt");
		String string = null;
		try {
			string = Files.readString(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

//		System.out.println(string);
		
		String fullString = "";
		int count = 0, length = string.length();
		
		for (int i = 0; i < length; i++) {
			
			if (count == 10) {
				count = 0;
			}
						
			if(i % 2 == 0) {
				int repeats = Character.getNumericValue(string.charAt(i));
				for (int j = 0; j < repeats; j++) {
					fullString += count;
				}
				count++;
			} else {
				int repeats = Character.getNumericValue(string.charAt(i));
				for (int j = 0; j < repeats; j++) {
					fullString += ".";
				}
			}

		}

//		System.out.println(fullString);
		
		String fragmentedString = "";
		int fullLength = fullString.length();
		for (int i = 0; i < fullLength; i++) {
			if(fullString.equals("")){
				break;
			} else if(fullString.charAt(0) == '.') {
				fullString = fullString.substring(1);
				int newfullLength = fullString.length();
				for (int j = newfullLength-1; j < newfullLength; j--) {
					if(fullString.charAt(j) != '.') {
						newfullLength = fullString.length();
						fragmentedString += fullString.charAt(j);
						fullString = fullString.substring(0, newfullLength-1);
						break;
					}
					fullString = fullString.substring(0, newfullLength-1);
				}
			} else {
				fragmentedString += fullString.charAt(0);
				fullString = fullString.substring(1);
			}
		}
		
//		System.out.println(fragmentedString);
		
		long somme = 0;
		int fragmentedLength = fragmentedString.length();
		for (int i = 0; i < fragmentedLength; i++) {
			int value = Character.getNumericValue(fragmentedString.charAt(i));
			int calcul = i * value;
			somme += calcul;
		}
		
		System.out.println(somme);
		
	}

    private static void part1ForReal() {
		//String[] string = Fichier.lireLignes("src/AoC_2024/resources/day8Input1.txt");
		Path file = Paths.get("src/AoC_2024/resources/day8Input9.txt");
		String string = null;
		try {
			string = Files.readString(file);
		} catch (IOException e) {
			
			e.printStackTrace();
		}

//		System.out.println(string);
		
		ArrayList<String> fullString = new ArrayList<String>();
		int count = 0, length = string.length();
		
		for (int i = 0; i < length; i++) {
					
			if(i % 2 == 0) {
				int repeats = Character.getNumericValue(string.charAt(i));
				for (int j = 0; j < repeats; j++) {
					fullString.add(String.valueOf(count));
				}
				count++;
			} else {
				int repeats = Character.getNumericValue(string.charAt(i));
				for (int j = 0; j < repeats; j++) {
					fullString.add(".");
				}
			}

		}

//		System.out.println(fullString);
		
		ArrayList<String> fragmentedString = new ArrayList<String>();
		
		int fullLength = fullString.size();
		
		for (int i = 0; i < fullLength; i++) {
			int newfullLength = fullString.size();
			if(newfullLength == 0){
				
				break;
			} else if(fullString.get(0).equals(".")) {
				fullString.remove(0);
				newfullLength = fullString.size();
				for (int j = newfullLength-1; j < newfullLength; j--) {
					
					if(!fullString.get(j).equals(".")) {
						
						fragmentedString.add(fullString.get(j));
						fullString.remove(j);
						break;
					}
					
					fullString.remove(j);
					newfullLength = fullString.size();
					
					if(j == 0) {
						break;
					}
				}
			} else {
				fragmentedString.add(fullString.get(0));
				fullString.remove(0);
			}

		}
		
//		System.out.println(fragmentedString);
		

		long somme = 0;
		int fragmentedLength = fragmentedString.size();
		
		for (int i = 0; i < fragmentedLength; i++) {
			int value = Integer.parseInt(fragmentedString.get(i));
			int calcul = i * value;
			somme += calcul;
		}
		
		System.out.println(somme);

	}

    private static void part2Original() {
		long startTime = System.nanoTime();
		
		Path file = Paths.get("src/AoC_2024/resources/day9Input2.txt");
		String string = null;
		try {
			string = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println(string);
		
		ArrayList<String> fullString = new ArrayList<String>();
		int count = 0, length = string.length();
		
		for (int i = 0; i < length; i++) {
					
			int repeats = Character.getNumericValue(string.charAt(i));
			String toAppend;
			
			if (i % 2 == 0) {
				toAppend = String.valueOf(count++);
			} else {
			    toAppend = ".";
			}
			
			for (int j = 0; j < repeats; j++) {
				fullString.add(toAppend);
			}
		}

//		System.out.println(Arrays.toString(fullString));

		int fullLength = fullString.size();
		String check = "";
		int j = fullLength-1;
		
		check = fullString.get(j);
		String oldCheck = check;
		int backCount = 0;
		for (int i = 0; i < j; i++) {
		
			check = fullString.get(j-i);
				
			if(!oldCheck.equals(".")) {
				
				if(!check.equals(oldCheck)) {
					int index = j-i;
					for (int k = 0; k <= index; k++) {
						if (fullString.get(k).equals(".")){
							int points = k;
							do {
								points++;
							} while (fullString.get(points).equals("."));
						
							if(backCount <=  points-k) {
								for (int m = 0; m < backCount; m++) {
									fullString.set(m+k,oldCheck);
									fullString.set(index+backCount-m,".");
								}
								backCount = 0;
//									System.out.println(Arrays.toString(fullString));
								break;
							}
						}
					}	
					backCount = 0;			
				}
			
			}else {
				backCount = 0;
			}
			oldCheck = check;
			backCount++;
		}

//		System.out.println(fullString);
		
		long somme = 0;
		int finalIndex = 0;
		for (int i = 0; i < fullLength; i++) {
			if(fullString.get(i).equals(".") && i > 0) {
				finalIndex++;
			} else {
				int value = Integer.parseInt(fullString.get(i));
				long calcul = (long) finalIndex * value;
//				System.out.printf("%d * %d = %d\n", value, finalIndex, calcul);
				somme += calcul;
				finalIndex++;
			}
		}
		
		System.out.println(somme);
		
		long endTime = System.nanoTime(); 
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
        
	}
	
	// copied the ArrayList<String> to a String[] to optimize the process and overall time got faster but still got same result :'(
    private static void part2() {
		long startTime = System.nanoTime();
		
		Path file = Paths.get("src/AoC_2024/resources/day9Input2.txt");
		String string = null;
		try {
			string = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println(string);
		
		ArrayList<String> array = new ArrayList<String>();
		int count = 0, length = string.length();
		
		for (int i = 0; i < length; i++) {
					
			int repeats = Character.getNumericValue(string.charAt(i));
			String toAppend;
			
			if (i % 2 == 0) {
				toAppend = String.valueOf(count++);
			} else {
			    toAppend = ".";
			}
			
			for (int j = 0; j < repeats; j++) {
				array.add(toAppend);
			}
		}
		file = null;
		string = null;
		
		String[] fullString = array.toArray(new String[0]);
		array = null;
		
//		System.out.println(Arrays.toString(fullString));
		ArrayList<String> alreadySeen = new ArrayList<String>();
		int fullLength = fullString.length;
		String check = "";
		String oldCheck = fullString[fullLength-1];
		int backCount = 0;
		for (int i = fullLength-1; i > 0; i--) {

			check = fullString[i];

			if(!oldCheck.equals(".")) {
				
				if(!check.equals(oldCheck)) {
					if(!alreadySeen.contains(oldCheck)) {
						for (int k = 0; k <= i; k++) {
						String test = fullString[k];

							if (fullString[k].equals(".")){
								int points = 0;
								do {
									points++;
								} while (fullString[k+points].equals("."));
							
								if(backCount <=  points) {
									for (int m = 0; m < backCount; m++) {
										fullString[m+k] = oldCheck;
										fullString[i+backCount-m] = ".";
										
									}
									alreadySeen.add(oldCheck);
									backCount = 0;
//										System.out.println(Arrays.toString(fullString));
									break;
								} else {
									k = k + points-1;
								}
							}
						}	
					
					}
					backCount = 0;				
				}
			}else {
				backCount = 0;
			}
			oldCheck = check;
			backCount++;
		}


//		System.out.println(Arrays.toString(fullString));

		long somme = 0;
		int finalIndex = 0;
		for (int i = 0; i < fullLength; i++) {
			if(fullString[i].equals(".") && i > 0) {
				finalIndex++;
			} else {
				int value = Integer.parseInt(fullString[i]);
				long calcul = (long) finalIndex * value;
//				System.out.printf("%d * %d = %d\n", value, finalIndex, calcul);
				somme += calcul;
				finalIndex++;
			}
		}
		
		
		System.out.println(somme);
		
		long endTime = System.nanoTime(); 
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
        
	}
	
	// Trying to change the logic from using strings to int, we'll see
    private static void part2NewIdea() {
        long startTime = System.nanoTime();

        Path file = Paths.get("src/AoC_2024/resources/day9Input2.txt");
        String string = null;
        try {
            string = Files.readString(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        int firstNumberOfString = 6;

//			System.out.println(string);

        ArrayList<Integer> array = new ArrayList<Integer>();
        int count = 0, length = string.length();

        for (int i = 0; i < length; i++) {

            int repeats = Character.getNumericValue(string.charAt(i));
            int toAdd;

            if (i % 2 == 0) {
                toAdd = count++;
            } else {
                toAdd = 0;
            }

            for (int j = 0; j < repeats; j++) {
                array.add(toAdd);
            }
        }

        int fullLength = array.size();

        int[] fullArray = new int[fullLength];
        for (int i = 0; i < fullLength; i++) {
            fullArray[i] = array.get(i);
        }

        // clearing memory
        array = null;
        file = null;
        string = null;
        count = 0;


//			System.out.println(Arrays.toString(fullArray));

        ArrayList<Integer> alreadySeen = new ArrayList<Integer>();
        int check;
        int oldCheck = fullArray[fullLength-1];
        int backCount = 0;

        // starting from the end of the array --
        for (int i = fullLength-1; i > firstNumberOfString; i--) {

            check = fullArray[i];

            if(!(oldCheck == 0)) {
                // if the new number is != than the previous one
                if(!(check == oldCheck)) {
                    // if the number has not been seen yet
                    if(!alreadySeen.contains(oldCheck)) {
                        // search left to right for zeros
                        for (int k = firstNumberOfString; k <= i; k++) {
                            if (fullArray[k] == 0){
                                int points = 0;
                                // count how many zeros after first one found
                                do {
                                    points++;
                                } while (fullArray[k+points] == 0);

                                if(backCount <=  points) {
                                    for (int m = 0; m < backCount; m++) {
                                        fullArray[m+k] = oldCheck;
                                        fullArray[i+backCount-m] = 0;
                                    }
                                    // add the number to the alreadySeen list
                                    alreadySeen.add(oldCheck);
                                    backCount = 0;
//										System.out.println(Arrays.toString(fullArray));
                                    break;
                                }else {
                                    // skip the zeros and check next number
                                    k = k + points-1;
                                }
                            }
                        }
                    }
                    backCount = 0;
                }
            }else {
                backCount = 0;
            }
            oldCheck = check;
            backCount++;
        }

//			System.out.println(Arrays.toString(fullArray));

        long somme = 0;

        for (int i = 0; i < fullLength; i++) {
            int value = fullArray[i];
            long calcul = (long) i * value;
//					System.out.printf("%d * %d = %d\n", value, i, calcul);
            somme += calcul;
        }

        System.out.println(somme);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");

    }

	
	// NOT GOOD (9999 is seen as 4 9) but keeping for stringBuilder logic
    private static void part2StringBuilder() {
		long startTime = System.nanoTime();
		
		Path file = Paths.get("src/AoC_2024/resources/day9Input2.txt");
		String string = null;
		try {
			string = Files.readString(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

//		System.out.println(string);
		
		StringBuilder fullString = new StringBuilder();
		int count = 0, length = string.length();
		
		for (int i = 0; i < length; i++) {
					
			int repeats = Character.getNumericValue(string.charAt(i));
			String toAppend;
			
			if (i % 2 == 0) {
				toAppend = String.valueOf(count++);
			} else {
			    toAppend = ".";
			}
			
			for (int j = 0; j < repeats; j++) {
			    fullString.append(toAppend);
			}
		}

//		System.out.println(fullString);
		
		
		int fullLength = fullString.length();
		char check = ' ';
		for (int j = fullLength-1; j > 0; j--) {
			if(check == '0') {
				break;
			}
				
			check = fullString.charAt(j);
			char oldCheck = check;
			int backCount = 0;
			for (int i = 0; i <= j; i++) {
			
				check = fullString.charAt(j-i);
					
				if(!(oldCheck == '.')) {
					
					if(!(check == oldCheck)) {
						int index = j-i;
						for (int k = 0; k <= index; k++) {
							if (fullString.charAt(k) == '.'){
								int points = k;
								do {
									points++;
								} while (fullString.charAt(points) == '.');
							
								if(backCount <=  points-k) {
									for (int m = 0; m < backCount; m++) {
										fullString.setCharAt(m+k,oldCheck);
										fullString.setCharAt(index+backCount-m, '.');
									}
									backCount = 0;
//									System.out.println(fullString);
									break;
								}
							}
						}	
						backCount = 0;			
					}
				
				}else {
					backCount = 0;
				}
				oldCheck = check;
				backCount++;
			}
		}
		

//		System.out.println(fullString);
		

		long somme = 0;
		int finalIndex = 0;
		for (int i = 0; i < fullLength; i++) {
			if(fullString.charAt(i) == '.' && i > 0) {
				finalIndex++;
			} else {
				int value = Character.getNumericValue(fullString.charAt(i));
				long calcul = (long) finalIndex * value;
//				System.out.printf("%d * %d = %d\n", value, finalIndex, calcul);
				somme += calcul;
				finalIndex++;
			}
		}
		
		
		System.out.println(somme);
		
		long endTime = System.nanoTime(); 
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Duration : " + duration/1000.00 + " sec ");
	}
	
}
