package kr.gonigoni.gondialer;

import java.util.Stack;

public class CalcClass {
	
	private Stack<Character> expression = new Stack<Character>();
	private int result;
	
	public CalcClass()
	{
		expression.clear();
		result = 0;
	}
	
	public void MakeStack(String originalExp)
	{
		int i;
		
		expression.clear();		// clear stack
		
		for(i = 0; i < originalExp.length(); i++)
		{
			expression.push(originalExp.charAt(i));
		}
	}
	
	public int getResult()
	{
		result = 0;			// result
		
		return result;
	}
	
}
