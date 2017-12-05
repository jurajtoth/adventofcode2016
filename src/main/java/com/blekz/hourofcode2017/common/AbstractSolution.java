package com.blekz.hourofcode2017.common;

import java.io.File;

public abstract class AbstractSolution {
	
	private static final String SOLUTION_CLASSNAME_PREFIX = "Solution";
	private static final String INPUT_FILE_PREFIX = "src/main/resources/input";
	
	protected abstract String doSolution(ParsedInput parsedInput);
	
	public String calculateSolution() {
		return doSolution(parseInput());
	}
	
	public String calculateSolution(ParsedInput parsedInput) {
		return doSolution(parsedInput);
	}
	
	private ParsedInput parseInput() {
		try {
			File inputFile = getInputFile();
			return ParsedInput.parseInputFile(inputFile);
		} catch(Exception e) {
			e.printStackTrace();
			System.exit(1);
			return null;
		}
	}
	
	public String getSolutionIndex() {
		return this.getClass().getSimpleName().toString().replaceAll(SOLUTION_CLASSNAME_PREFIX, "");
	}
	
	private File getInputFile() {
		String solutionNumber = getSolutionIndex();
		File inputFile = new File(INPUT_FILE_PREFIX + solutionNumber + ".txt");
		return inputFile;
	}
	
}
