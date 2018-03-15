package expressionTrees;

import java.util.*;

import trees.*;
import util.*;
import wordLadder.Queue;

public class ExpressionTree {
	
	private BinaryTreeNode<String> root;
	
	//takes spaced RPN string and converts to expression tree
	public void parsePostFix(String postFix) {
		Stack<BinaryTreeNode<String>> expressionStack = new Stack<>();
		boolean hasX = false;
		
		ArrayList<Integer> spaceIndexes = StringUtils.findOccurences(postFix, " ");
		
		for(int i = 0; i < spaceIndexes.size()-1; i++) {
			
			String token = postFix.substring(spaceIndexes.get(i)+1, spaceIndexes.get(i+1));
			
			if(token.equals("x")) {
				BinaryTreeNode<String> tmp = new BinaryTreeNode<>(token);
				expressionStack.push(tmp);
				hasX = true;
			} else if(token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/") || token.equals("^")) {
				BinaryTreeNode<String> tmp = new BinaryTreeNode<>(token);
				try {
					tmp.setRight(expressionStack.pop());
					tmp.setLeft(expressionStack.pop());
				} catch(EmptyStackException e) {
					System.err.println("Postfix expression has invalid arguments");
					return;
				}
				expressionStack.push(tmp);
			} else if(StringUtils.isNumeric(token)){
					BinaryTreeNode<String> tmp = new BinaryTreeNode<>(token);
					expressionStack.push(tmp);
			} else {
				System.err.println("Postfix expression has invalid token: "+ token);
				return;
			}
		}
		if(hasX) {
			root = expressionStack.pop();
		} else {
			System.err.println("Postfix expression does not contain variable 'x'");
		}
			
	}
	
	public double evaluate(double xVal) {
		return evaluate(xVal, root);
	}
	
	private double evaluate(double xVal, BinaryTreeNode<String> node) {
		
		if(node.getLeft() == null && node.getRight() == null) {
			if(node.getValue().equals("x")) {
				return xVal;
			} else {
				return Double.parseDouble(node.getValue());
			}
		} else {
			String operator = node.getValue();
			
			switch(operator) {
			case("*"):
				return evaluate(xVal, node.getLeft()) * evaluate(xVal, node.getRight());
			case("+"):
				return evaluate(xVal, node.getLeft()) + evaluate(xVal, node.getRight());
			case("-"):
				return evaluate(xVal, node.getLeft()) - evaluate(xVal, node.getRight());
			case("/"):
				return evaluate(xVal, node.getLeft()) / evaluate(xVal, node.getRight());
			case("^"):
				return Math.pow(evaluate(xVal, node.getLeft()), evaluate(xVal, node.getRight()));
			}
		}
		return Double.NaN;
		
	}
	
	public BinaryTreeNode<String> derivate(BinaryTreeNode<String> node){
		
			String operator = node.getValue();
			
			switch(operator) {
			case("+"):
				node.setLeft(derivate(node.getLeft()));
				node.setRight(derivate(node.getRight()));
				return node;
			case("-"):
				node.setLeft(derivate(node.getLeft()));
				node.setRight(derivate(node.getRight()));
				return node;
			case("*"):
				BinaryTreeNode<String> left = new BinaryTreeNode<>("*", node.getLeft(), derivate(node.getRight()));
				BinaryTreeNode<String> right = new BinaryTreeNode<>("*", derivate(node.getLeft()), node.getRight());
				
				node.setValue("+");
				node.setLeft(left);
				node.setRight(right);
				return node;
			case("/"):
				BinaryTreeNode<String> rightNumerator = new BinaryTreeNode<>("*", node.getLeft(), derivate(node.getRight()));
				BinaryTreeNode<String> leftNumerator = new BinaryTreeNode<>("*", derivate(node.getLeft()), node.getRight());
				BinaryTreeNode<String> numerator = new BinaryTreeNode<>("-", leftNumerator, rightNumerator);
				
				BinaryTreeNode<String> denominator = new BinaryTreeNode<>("^", node.getRight(), new BinaryTreeNode<String>("2"));
				
				node.setLeft(numerator);
				node.setRight(denominator);
				return node;
			case("^"):
				BinaryTreeNode<String> value = new BinaryTreeNode<>("-", node.getRight(), new BinaryTreeNode<String>("1"));
				BinaryTreeNode<String> exp = new BinaryTreeNode<>("^", node.getLeft(), value);	
				
				node.setValue("*");
				node.setLeft(exp);
				return node;
			case("x"):
				node.setValue("1");
				return node;
			default:
				node.setValue("0");
				return node;
			}
	}
	
	public ExpressionTree derivate() {
		BinaryTreeNode<String> node = derivate(root);
		ExpressionTree tree = new ExpressionTree();
		setNode(node); //gross
		return tree;
	}
	
	private void setNode(BinaryTreeNode<String> node) {
		root = node;
	}
		
	
	public void printIndexed() {
		printIndexed(root, 0, true);
	}
	
	private void printIndexed(BinaryTreeNode<String> node, int depth, boolean parentIsDown) {
		if(node != null) {
			printIndexed(node.getRight(), depth+1, true);
			
			String tabs = "";
			for(int i = 1; i <= depth; i++) {
				tabs += "\t";
			}
			String direction = "";
			
			if(depth != 0) {
				direction = parentIsDown ? "v " : "^ ";
			}
			
			System.out.println(tabs + direction + node);

			printIndexed(node.getLeft(), depth+1, false);
		}
	}
	
	public String infixToPostfix(String infix) {
		Queue<String> postQueue = new Queue<>();
		Stack<String> operatorStack = new Stack<>();
		
		ArrayList<Integer> spaceIndexes = StringUtils.findOccurences(infix, " ");
		
		for(int i = 0; i < spaceIndexes.size()-1; i++) {
			String token = infix.substring(spaceIndexes.get(i)+1, spaceIndexes.get(i+1));
			
			if(StringUtils.isNumeric(token) || token.equals("x")){
				postQueue.add(token);
			} else if(token.equals("(")){
				operatorStack.push(token);
			} else if(token.equals(")")) {
				while(!operatorStack.peek().equals("(")){
					postQueue.add(operatorStack.pop());
				}
				operatorStack.pop();
			} else {
				while(!operatorStack.empty() && (getPriority(operatorStack.peek()) >= getPriority(token))) {
					postQueue.add(operatorStack.pop());
				}
				operatorStack.push(token);
			}
		}
		
		while(!operatorStack.empty()) {
			postQueue.add(operatorStack.pop());
		}
		String result = "";
		while(postQueue.getNumItems() > 0) {
			if(postQueue.getNumItems() == 1) {
				result += (postQueue.remove());
			} else {
				result += (postQueue.remove() + " ");
			}
		}
		
		return result;
	}
	
	public int getPriority(String operator) {
		switch(operator){
		case "^":
			return 4;
		case "*":
			return 3;
		case "/":
			return 3;
		case "+":
			return 2;
		case "-":
			return 2;
		default:
			return -1;
		}
	}
	
	

}
