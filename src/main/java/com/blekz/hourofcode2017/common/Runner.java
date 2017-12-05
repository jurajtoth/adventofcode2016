package com.blekz.hourofcode2017.common;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public class Runner {
	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		for(AbstractSolution solution : getAllSolutionClasses()) {
			System.out.println("Solution "+solution.getSolutionIndex()+": "+solution.calculateSolution());
		}
	}
	
	public static List<AbstractSolution> getAllSolutionClasses() {
		boolean goNext = true;
		int index = 1;
		char letterIndex = 'a';
		List<AbstractSolution> solutions = new ArrayList<>();
		while(goNext) {
			try {
				// max number of solutions that will be parsed
				if(index == 100) {
					break;
				}
				Class<AbstractSolution> clazz = (Class<AbstractSolution>) Class.forName("com.blekz.hourofcode2017.solutions.Solution"+index+letterIndex);
				AbstractSolution solution = null;
				try {
					solution = clazz.getConstructor().newInstance();
				} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | SecurityException e) {
					e.printStackTrace();
					System.exit(1);
				}
				solutions.add(solution);
				letterIndex++;
			} catch(ClassNotFoundException e) {
				letterIndex = 'a';
				index++;
			}
		}
		return solutions;
	}

}
