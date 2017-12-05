package com.blekz.hourofcode2017.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ParsedInput {
	
	private Map<Integer, Map<Integer, String>> data = new TreeMap<>();
	
	public void addData(Integer row, Integer column, String val) {
		if(data.get(row) == null) {
			data.put(row, new TreeMap<Integer, String>());
		}
		data.get(row).put(column, val);
	}
	
	public String getValue(Integer row, Integer column) {
		if(data.get(row) == null) {
			return null;
		}
		return data.get(row).get(column);
	}
	
	public Integer getValueAsInteger(Integer row, Integer column) {
		String value = getValue(row, column);
		if(value == null) {
			return null;
		}
		return new Integer(value);
	}
	
	public String getSingleValue() {
		return data.get(0).get(0);
	}
	
	public void setValue(Integer row, Integer column, String value) {
		data.get(row).put(column, value);
	}
	
	public void setValue(Integer row, Integer column, Integer value) {
		setValue(row, column, value+"");
	}
	
	public Integer getSingleValueAsInteger() {
		return new Integer(getSingleValue());
	}
	
	public List<String> getRowValues(Integer row) {
		return new ArrayList<String>(data.get(row).values());
	}
	
	public List<Integer> getRowValuesAsInteger(Integer row) {
		return getRowValues(row).stream().map(s -> new Integer(s)).collect(Collectors.toList());
	}
	
	public List<String> getColumnValues(Integer column) {
		List<String> values = new ArrayList<>();
		for(Map<Integer, String> row : data.values()) {
			String value = row.get(column);
			if(value != null) {
				values.add(value);
			}
		}
		return values;
	}
	
	public List<Integer> getColumnValuesAsInteger(Integer column) {
		return getColumnValues(column).stream().map(s -> new Integer(s)).collect(Collectors.toList());
	}
	
	public Integer numberOfLines() {
		return data.size();
	}
	
	public static ParsedInput parseInputFile(File inputFile) {
		ParsedInput parsedInput = new ParsedInput();
		try(BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
			int row = 0;
			while(reader.ready()) {
				String line = reader.readLine();
				StringTokenizer tokenizer = new StringTokenizer(line);
				int column = 0;
				while(tokenizer.hasMoreTokens()) {
					parsedInput.addData(row, column, tokenizer.nextToken());
					column++;
				}
				row++;
			}
		} catch (IOException e) {
			throw new IllegalStateException("Failed parsing input", e);
		}
		return parsedInput;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(Map<Integer, String> row : data.values()) {
			for(String val : row.values()) {
				sb.append(val);
				sb.append(" ");
			}
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}

}
