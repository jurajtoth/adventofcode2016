package com.blekz.hourofcode2017.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blekz.hourofcode2017.common.AbstractSolution;
import com.blekz.hourofcode2017.common.ParsedInput;

public class Solution1a extends AbstractSolution {
	
	enum Dir {
		NORTH, WEST, EAST, SOUTH;
	}
	
	static class Facing {
		public Dir current;
		public Dir left;
		public Dir right;
		
		public Facing(Dir current, Dir left, Dir right) {
			super();
			this.current = current;
			this.left = left;
			this.right = right;
		}
	}
	
	static Facing NORTH_FACING = new Facing(Dir.NORTH, Dir.WEST, Dir.EAST);
	static Facing WEST_FACING = new Facing(Dir.WEST, Dir.SOUTH, Dir.NORTH);
	static Facing EAST_FACING = new Facing(Dir.EAST, Dir.NORTH, Dir.SOUTH);
	static Facing SOUTH_FACING = new Facing(Dir.SOUTH, Dir.EAST, Dir.WEST);
	private static final Map<Dir, Facing> FACING_MAP = new HashMap<>();
	static {
		FACING_MAP.put(Dir.NORTH, NORTH_FACING);
		FACING_MAP.put(Dir.WEST, WEST_FACING);
		FACING_MAP.put(Dir.SOUTH, SOUTH_FACING);
		FACING_MAP.put(Dir.EAST, EAST_FACING);
	}
	
	
	@Override
	protected String doSolution(ParsedInput parsedInput) {
		List<String> steps = parsedInput.getRowValues(0);
		Integer currentX = 0;
		Integer currentY = 0;
		Facing currentFacing = NORTH_FACING;
		for(String step : steps) {
			if(step.charAt(0) == 'R') {
				currentFacing = FACING_MAP.get(currentFacing.right);
			} else {
				currentFacing = FACING_MAP.get(currentFacing.left);
			}
			Integer numberOfSteps = new Integer(step.substring(1, step.length()));
			switch(currentFacing.current) {
				case NORTH: currentY-=numberOfSteps; break;
				case SOUTH: currentY+=numberOfSteps; break;
				case WEST:  currentX-=numberOfSteps; break;
				case EAST:  currentX+=numberOfSteps; break;
			}
		}
		return Math.abs(currentX) + Math.abs(currentY) + "";
	}

}
