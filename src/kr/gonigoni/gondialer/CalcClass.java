package kr.gonigoni.gondialer;

import java.util.Stack;

import android.widget.Toast;

public class CalcClass {
	
	private Stack<Character> expression = new Stack<Character>();
	private int result;
	
	public CalcClass()
	{
		expression.clear();
		result = 0;
	}
	
	public int getResult(String originalExp)
	{
		//int i;
		
		//expression.clear();		// clear stack
		
		//for(i = 0; i < originalExp.length(); i++)
		//{
		//	expression.push(originalExp.charAt(i));
		//}
		return result;
	}
}
