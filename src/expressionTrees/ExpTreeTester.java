package expressionTrees;

import java.util.*;
import util.*;

public class ExpTreeTester {
	
	public static void main(String[] args) {
	
		ExpressionTree tree = new ExpressionTree();
		String result = tree.infixToPostfix("x + 2 + 3 * 2 ^ 3");
		System.out.println(result);
		tree.parsePostFix(result);
		
		System.out.println(tree.evaluate(0));
		
		tree.printIndexed();
		
		//ExpressionTree diff = tree.derivate();

	//	diff.printIndexed();
		
	}
}