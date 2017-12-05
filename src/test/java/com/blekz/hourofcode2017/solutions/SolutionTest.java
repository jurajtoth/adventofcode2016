package com.blekz.hourofcode2017.solutions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;
import com.blekz.hourofcode2017.common.Runner;


public class SolutionTest {
	
	private final static String TEST_INPUT_FILE_PREFIX = "src/test/resources/solution";
	
	@Test
	public void testAllSolutions() {
		for(AbstractSolution solution : Runner.getAllSolutionClasses()) {
			String index = solution.getSolutionIndex();
			List<TestData> testData = getTestData(index);
			for(TestData test : testData) {
				String errorMsg = String.format("Invalid result for solution %s, test index %s", solution.getSolutionIndex(), test.testDataIndex);
				Assert.assertEquals(errorMsg, test.output, solution.calculateSolution(test.input));
				System.out.println(String.format("Solution %s, test index %s passed OK.", solution.getSolutionIndex(), test.testDataIndex));
			}
		}
	}
	
	private List<TestData> getTestData(String solutionIndex) {
		List<TestData> testData = new ArrayList<>();
		int testIndex = 1;
		while(true) {
			File inputFile = new File(TEST_INPUT_FILE_PREFIX+solutionIndex+"/input"+testIndex+".txt");
			File resultFile = new File(TEST_INPUT_FILE_PREFIX+solutionIndex+"/result"+testIndex+".txt");
			if(!inputFile.exists() && !resultFile.exists()) {
				break;
			}
			ParsedInput input = ParsedInput.parseInputFile(inputFile);
			// result is always 1 value
			String result = ParsedInput.parseInputFile(resultFile).getValue(0, 0);
			testData.add(new TestData(input, result, testIndex));
			testIndex++;
		}
		if(testData.isEmpty()) {
			throw new IllegalStateException("No test data for solution "+solutionIndex);
		}
		return testData;
	}
	
	class TestData {
		ParsedInput input;
		String output;
		Integer testDataIndex;
		
		public TestData(ParsedInput input, String output, Integer testDataIndex) {
			this.input = input;
			this.output = output;
			this.testDataIndex = testDataIndex;
		}
	}

}
