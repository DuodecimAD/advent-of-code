package AoC_2024.days;

import AoC_2024.io.Fichier;

import java.util.ArrayList;
import java.util.Arrays;


public class day5 {

	public static void main(String[] args) {
		
		part1();

	}

    private static void part1() {
		String[] file = Fichier.lireLignes("src/AoC_2024/resources/ex5Input1.txt");
		String[] file2 = Fichier.lireLignes("src/AoC_2024/resources/ex5Input2.txt");
		

		int indexA = -1, indexB = -1;
		int target = 29;
		int left;
		int right;
		
		
		// on split "A|B" en tableau A,B
		ArrayList<int[]> checkArray = new ArrayList<>();
		
		for (int i = 0; i < file.length; i++) {
			left = Integer.parseInt(file[i].substring(0, file[i].indexOf("|")));
			right = Integer.parseInt(file[i].substring(file[i].indexOf("|")+1));
			
			checkArray.add(new int[]{left, right});

			//System.out.println(Arrays.toString(checkArray.get(i)));
			
		}
		
		// on fait un tableau avec 1x les nombres dans le bon ordre
		ArrayList<Integer> fullArray = new ArrayList<>();
		
		int currentValue;
		for (int i = 0; i < checkArray.size(); i++) {
			for (int j = 0; j < checkArray.get(i).length; j++) {
				
				currentValue = checkArray.get(i)[j];
				
				if(j == 0) {
					indexA = fullArray.indexOf(currentValue);

					if (indexA == -1) {
						fullArray.add(currentValue);
						indexA = fullArray.indexOf(currentValue);
					}
					
				} else {
					indexB = fullArray.indexOf(currentValue);
					
					if (indexB == -1) {
						fullArray.add(currentValue);
		                indexB = fullArray.indexOf(currentValue);
					}
					
					if (indexA >= 0 && indexB == -1) {
						if (indexB < indexA) {
		                    fullArray.add(indexA, currentValue);
		                }
					}	
				}
			}
		}
				
		System.out.println("tableau to check " + fullArray);
		
		// on fait un tableau avec les liste à check
		ArrayList<int[]> compareArray = new ArrayList<>();
		
		for (int i = 0; i < file2.length; i++) {
			
			String[] split = file2[i].split(",");
			
			int[] intArray = new int[split.length];
			
			for (int j = 0; j < split.length; j++) {
		        intArray[j] = Integer.parseInt(split[j]);
		    }
			
			compareArray.add(intArray);
		}
		for (int i = 0; i < compareArray.size(); i++) {
			System.out.println(Arrays.toString(compareArray.get(i)));
		}
		
		// on check si les lignes de nombres sont correctes 
		int nombre, index, currentIndex = -1, somme=0;
		boolean check;
		
		for (int i = 0; i < compareArray.size(); i++) {
			check  = true;
			currentIndex = -2;
			for (int j = 0; j < compareArray.get(i).length; j++) {
				
				nombre = compareArray.get(i)[j];
				index = fullArray.indexOf(nombre);
				
				System.out.println();
				
				if(index < currentIndex) {
					check = false;
					break;
				}
				currentIndex = index;
				
			}
			
			if(check) {
				System.out.println(compareArray.get(i)[compareArray.get(i).length/2]);
				somme+=compareArray.get(i)[compareArray.get(i).length/2];
			} 
		}

	}

}
