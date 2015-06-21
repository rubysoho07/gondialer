package kr.gonigoni.gondialer;

import java.util.Stack;

public class CalcClass {
	
	private Stack<String> operand = new Stack<String>();
	private Stack<String> expression = new Stack<String>();
	private int result;
	
	public CalcClass()
	{
		// 초기화
		operand.clear();
		expression.clear();
		result = 0;
	}
	
	/**
	 * Initialize expression and operand stack.
	 */
	public void initialize()
	{
		// 초기화
		operand.clear();
		expression.clear();
		result = 0;
	}
	
	/**
	 * Convert string to postfix stack.
	 * @param originalExp
	 */
	public void convertToPostfix(String originalExp)
	{
		int num_start = 0;
		int num_end = 0;
		int i;
		
		for(i = 0; i < originalExp.length(); i++)
		{
			switch(originalExp.charAt(i))
			{
				case '+':
				case '-':
				case '*':
				case '/':
					expression.add(originalExp.substring(num_start, num_end));
					operand.add(Character.toString(originalExp.charAt(i)));
					num_start = i+1;
					num_end = num_start;
					break;
				default:
					num_end++;
			}
		}
		
	}
	
	public int getResult()
	{
		
		return result;
	}
}
