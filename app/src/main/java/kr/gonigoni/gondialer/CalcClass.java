package kr.gonigoni.gondialer;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;

public class CalcClass {

	/* constant variables. */
	private final static int TYPE_OPERAND = 1;
	private final static int TYPE_LEFT_PARENTHESIS = 2;
	private final static int TYPE_RIGHT_PARENTHESIS = 3;

	private final static int TYPE_PLUS = 4;
	private final static int TYPE_MINUS = 5;
	private final static int TYPE_MULTIPLY = 6;
	private final static int TYPE_DIVIDE = 7;

	// Token type and token (While conversion to postfix expression)
	private static int tokenType;
	private static String token;

	// Position of string (While conversion to postfix expression)
	private static int position;

	// List for postfix expression.
	private static LinkedList<String> postExpList = new LinkedList<String>();

	// Stack for making postfix expression.
	private static Stack<Character> operatorStack = new Stack<Character>();

	// Stack for getting result from expression.
	private static Stack<String> calcStack = new Stack<String>();

	// Check priority of operator.
	private boolean isPrior (char operatorInStack, char operatorInToken) {
		return (getPriority (operatorInStack, true) > getPriority (operatorInToken, false));
	}

	private int getPriority(char operator, boolean isInStack) {
		int priority = -1;

		switch (operator) {
			case '(':
				if (isInStack)
					priority = 3;
				else
					priority = 0;
				break;
			case '*':
			case '/':
				priority = 1;
				break;

			case '+':
			case '-':
				priority = 2;
				break;
		}
		return priority;
	}

	// Get next token from infix expression.
	private void getNextToken (int pos, String exp) {
		int start = pos;
		int end = pos;

		if (exp.charAt(pos) == '+') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_PLUS;
			position++;
			return;
		} else if (exp.charAt(pos) == '-') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_MINUS;
			position++;
			return;
		} else if (exp.charAt(pos) == '*') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_MULTIPLY;
			position++;
			return;
		} else if (exp.charAt(pos) == '/') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_DIVIDE;
			position++;
			return;
		} else if (exp.charAt(pos) == '(') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_LEFT_PARENTHESIS;
			position++;
			return;
		} else if (exp.charAt(pos) == ')') {
			token = Character.toString(exp.charAt(pos));
			tokenType = TYPE_RIGHT_PARENTHESIS;
			position++;
			return;
		} else {
			while (pos < exp.length()) {
				if (exp.charAt(pos) == '+' || exp.charAt(pos) == '-' ||
						exp.charAt(pos) == '*' || exp.charAt(pos) == '/' ||
						exp.charAt(pos) == '(' || exp.charAt(pos) == ')') {
					break;
				}

				pos++;
				end = pos;
			}

			token = exp.substring(start, end);
			tokenType = TYPE_OPERAND;
			position = end;
		}
	}

	// Clear all lists and stacks of this class.
	public void clearAll() {
		postExpList.clear();
		operatorStack.clear();
		calcStack.clear();
	}

	// Convert infix expression to postfix expression.
	public void toPostfix(String exp) {
		position = 0;
		token = "";

		while (position < exp.length()) {
			getNextToken(position, exp);

			if (tokenType == TYPE_OPERAND) {
				postExpList.add(token);
			} else if ( tokenType == TYPE_RIGHT_PARENTHESIS) {
				while (!operatorStack.isEmpty()) {
					char c = operatorStack.pop();

					if (c != '(') {
						postExpList.add(Character.toString(c));
					}
				}
			} else {

				// When you meet left parenthesis...
				while (!operatorStack.empty() &&
						!isPrior(operatorStack.peek(), token.charAt(0))) {
					char c = operatorStack.pop();

					if (c != '(')
						postExpList.add(Character.toString(c));
				}

				operatorStack.push(token.charAt(0));
			}

			token = "";
		}

		while (!operatorStack.isEmpty()) {
			char c = operatorStack.pop();

			if (c != '(') {
				postExpList.add(Character.toString(c));
			}
		}
	}

	// Get result from postfix expression.
	public double getResult(String exp) {
		double result = 0;
		double t1, t2;

		// 1. Clear all lists and stacks of this class.
		clearAll();

		// 2. Convert infix expression to postfix expression.
		toPostfix(exp);

		// 2-1. (For test) Print postfix expression.
		printPostfix();

		// 3. Get result from postfix expression.
		while (!postExpList.isEmpty()) {
			switch (postExpList.peek()) {
				case "+":
					t1 = Double.parseDouble(calcStack.pop());
					t2 = Double.parseDouble(calcStack.pop());
					result = t1 + t2;
					calcStack.push(Double.toString(result));
					postExpList.removeFirst();
					break;

				case "-":
					t1 = Double.parseDouble(calcStack.pop());
					t2 = Double.parseDouble(calcStack.pop());
					result = t1 - t2;
					calcStack.push(Double.toString(result));
					postExpList.removeFirst();
					break;

				case "*":
					t1 = Double.parseDouble(calcStack.pop());
					t2 = Double.parseDouble(calcStack.pop());
					result = t1 * t2;
					calcStack.push(Double.toString(result));
					postExpList.removeFirst();
					break;

				case "/":
					t1 = Double.parseDouble(calcStack.pop());
					t2 = Double.parseDouble(calcStack.pop());
					result = t1 / t2;
					calcStack.push(Double.toString(result));
					postExpList.removeFirst();
					break;

				default:
					calcStack.push(postExpList.removeFirst());
			}
		}

		result = Double.parseDouble(calcStack.pop());
		return result;
	}

	// For test: Print postfix expression.
	public void printPostfix () {
		Iterator<String> iterator = postExpList.iterator();
		String tmp;

		while (iterator.hasNext()) {
			tmp = iterator.next();
			System.out.print(tmp + " ");
		}

		System.out.println();
	}
}
