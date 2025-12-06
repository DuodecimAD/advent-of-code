package aoc2024.days;

import aoc2024.io.Fichier;

import java.util.ArrayList;


public class day7 {

	public static void main(String[] args) {
		
		//part1();
		//part2();
		part2ForReal();

	}

    private static void part1() {
		
		char[] operators = {'+', '*'};
		String[] string = Fichier.lireLignes("src/AoC_2024/resources/ex7Input1.txt");
		ArrayList<String[]> stringArray = new ArrayList<String[]>();
		long result = 0;
		
		for (int i = 0; i < string.length; i++) {
			stringArray.add(string[i].split(" "));
		}

        for (int t = 0; t < string.length; t++) {
        	ArrayList<Integer> monArray = new ArrayList<Integer>();
        	        	
//        	System.out.println("\n" + Arrays.toString(stringArray.get(t)));
        	long checkValue = Long.parseLong(stringArray.get(t)[0].substring(0, stringArray.get(t)[0].length()-1));
        	
        	int lengthy = stringArray.get(t).length;
        	
        	for (int i = 1; i < lengthy; i++) {
        		monArray.add(Integer.parseInt(stringArray.get(t)[i]));
        		
			}
//        	System.out.println("monArray = " +monArray);
        	int numOperators = monArray.size() - 1;
        	int totalCombinations = (int) Math.pow(operators.length, numOperators);
			
			for (int i = 0; i < totalCombinations; i++) {
				long somme = monArray.get(0);
				int temp = i;
				
//				System.out.print(somme);
				for (int j = 1; j < monArray.size(); j++) {
					
					char operator = operators[temp % operators.length]; // Merci ChatGPT mais la logique 
		            temp /= operators.length;							// me passe un peu au dessus de la tête :'(
					
		            if (operator == '+') {
	                    somme += monArray.get(j);
	                } else if (operator == '*') {
	                    somme *= monArray.get(j);
	                }
		            
//		            System.out.print(" " + operator + " ");
//	                System.out.print(monArray.get(j));
	                
				}
				if (somme == checkValue) {
					result += somme;
                	break;
                }
//				System.out.println();
						
			}

        }
        System.out.println("\nresult = " + result);
	}

    private static void part2() {
						
		char[] operators = {'+', '*', '|'};
		String[] string = Fichier.lireLignes("src/AoC_2024/resources/ex7Input1.txt");
		ArrayList<String[]> stringArray = new ArrayList<String[]>();
		long result = 0;
		
		for (int i = 0; i < string.length; i++) {
			stringArray.add(string[i].split(" "));
		}

        
        
        for (int t = 0; t < string.length; t++) {
        	ArrayList<Integer> monArray = new ArrayList<Integer>();
        	        	
 //       	System.out.println("\n" + Arrays.toString(stringArray.get(t)));
        	long checkValue = Long.parseLong(stringArray.get(t)[0].substring(0, stringArray.get(t)[0].length()-1));
 //       	System.out.println("checkValue = " +checkValue);
        	int lengthy = stringArray.get(t).length;
        	for (int i = 1; i < lengthy; i++) {
        		monArray.add(Integer.parseInt(stringArray.get(t)[i]));
        		
			}
 //       	System.out.println("monArray = " +monArray);
        	int numOperators = monArray.size() - 1;
        	int totalCombinations = (int) Math.pow(operators.length, numOperators);
			
			for (int i = 0; i < totalCombinations; i++) {
				long somme = monArray.get(0);
				int temp = i;
//				System.out.print(somme);
				char lastOperator = 0;
				int numbersConcat = 2;
				long lastChiffreAColler = 0;
				int k = 0;
				
				for (int j = 1; j < monArray.size(); j++) {
					
					char operator = operators[temp % operators.length]; // Merci ChatGPT mais la logique 
		            temp /= operators.length;							// me passe un peu au dessus de la tête :'(
					
		            if (operator == '+') {
	                    somme += monArray.get(j);
	                } else if (operator == '*') {
	                    somme *= monArray.get(j);
	                } else if (operator == '|' ) {
	                	long chiffreAColler = monArray.get(j);
	                	
	                	for (k = 1; k < numbersConcat; k++) {
	                		int chiffrePrecedent = monArray.get(j-k);
							chiffreAColler = Long.parseLong(chiffrePrecedent +  "" + chiffreAColler);
						}
	                	k = j - k+1;
	                	if(lastOperator == '+') {
	                		somme -= lastChiffreAColler;
	                		somme += chiffreAColler;
	                	} else if (lastOperator == '*'){
	                		somme /= lastChiffreAColler;
	                		somme *= chiffreAColler;
	                	} else {
	                		somme = chiffreAColler;
	                	}
	                	
	                	lastChiffreAColler = chiffreAColler;
	                	
	                } 
		            

		            if(operator == '+' || operator == '*') {
		            	lastOperator = operator;
		            	numbersConcat = 2;
		            	if(lastChiffreAColler == 0) {
			            	lastChiffreAColler = monArray.get(k+1);
			            } 
		            } else {
		            	numbersConcat++;
		            }
		            
//		            System.out.print(" " + operator + " ");
//	                System.out.print(monArray.get(j));
                
				}
//				System.out.print(" = " + somme);
//				System.out.println();
				
				if (somme == checkValue) {
					result += somme;
                	break;
                }
				
			}

        }
        System.out.println("\nresultat final = " + result);
	}

    private static void part2ForReal() {
		
		char[] operators = {'+', '*', '|'};
		String[] string = Fichier.lireLignes("src/AoC_2024/resources/ex7Input2.txt");
		ArrayList<String[]> stringArray = new ArrayList<String[]>();
		long result = 0;
		
		for (int i = 0; i < string.length; i++) {
			stringArray.add(string[i].split(" "));
		}
        
        for (int t = 0; t < string.length; t++) {
        	ArrayList<Integer> monArray = new ArrayList<Integer>();
        	        	
//      	System.out.println("\n" + Arrays.toString(stringArray.get(t)));
        	long checkValue = Long.parseLong(stringArray.get(t)[0].substring(0, stringArray.get(t)[0].length()-1));
//     		System.out.println("checkValue = " +checkValue);
        	int lengthy = stringArray.get(t).length;
        	for (int i = 1; i < lengthy; i++) {
        		monArray.add(Integer.parseInt(stringArray.get(t)[i]));
        		
			}
//       	System.out.println("monArray = " +monArray);
        	int numOperators = monArray.size() - 1;
        	int totalCombinations = (int) Math.pow(operators.length, numOperators);
			
			for (int i = 0; i < totalCombinations; i++) {
				long somme = monArray.get(0);
				int temp = i;
//				System.out.print(somme);
				char lastOperator = 0;
				int numbersConcat = 2;
				long lastChiffreAColler = 0;
				int k = 0;
				
				for (int j = 1; j < monArray.size(); j++) {
					
					char operator = operators[temp % operators.length]; // Merci ChatGPT mais la logique 
		            temp /= operators.length;							// me passe un peu au dessus de la tête :'(
					
		            if (operator == '+') {
	                    somme += monArray.get(j);
	                } else if (operator == '*') {
	                    somme *= monArray.get(j);
	                } else if (operator == '|' ) {
	                	long chiffreAColler = monArray.get(j);
	                	somme = Long.parseLong(somme +  "" + chiffreAColler);
	                } 
		            
//		            System.out.print(" " + operator + " ");
//	                System.out.print(monArray.get(j));
                
				}
//				System.out.print(" = " + somme);
//				System.out.println();
				
				if (somme == checkValue) {
					result += somme;
                	break;
                }
				
			}

        }
        System.out.println("\nresultat final = " + result);
	}

}
