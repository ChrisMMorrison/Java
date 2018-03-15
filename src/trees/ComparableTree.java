package trees;

import java.util.InputMismatchException;

public class ComparableTree<E extends Comparable<E>> {

	private ComparableTreeNode<E> root;
	
	public ComparableTree() {
		root = null;
	}
	
	public void add(E object) {
		ComparableTreeNode<E> newNode = new ComparableTreeNode<>(object);
		
		if(root == null) {
			root = newNode;
		} else {
		    addNode(root, newNode);
		}
	}
	
	public void add(E object, int value) {
		ComparableTreeNode<E> newNode = new ComparableTreeNode<>(object, value);
		
		if(root == null) {
			root = newNode;
		} else {
			addNode(root, newNode);
		}
	}
	
	public void add(ComparableTreeNode<E> newNode) {
				
		if(root == null) {
			root = newNode;
		} else {
			addNode(root, newNode);
		}
	}

	public ComparableTreeNode<E> getNode(ComparableTreeNode<E> node, E key) {

		if(node == null){
			return null;
		}

		E testKey = node.getValue();

		if (key.compareTo(testKey) == 0) {
			return node;
		} else if (key.compareTo(testKey) > 0) {
			if (node.getRight() == null) {
				// System.err.println("Key does not exist in Search Tree");
				return null;
			} else {
				return getNode(node.getRight(), key);
			}
		} else {
			if (node.getLeft() == null) {
				//  System.err.println("Key does not exist in Search Tree");
				return null;
			} else {
				return getNode(node.getLeft(), key);
			}
		}
	}
							//alex					michhael
	private void addNode(ComparableTreeNode<E> pt, ComparableTreeNode<E> newNode) {
		if(pt.compareTo(newNode) > 0) {
			if(pt.getLeft() == null) {
				pt.setLeft(newNode);
			} else {
				addNode(pt.getLeft(), newNode);
			}
		} else if(pt.compareTo(newNode) < 0) {
			if(pt.getRight() == null) {
				pt.setRight(newNode);
			} else {
				addNode(pt.getRight(), newNode);
			}
		//the current node is equal to newNode	
		} else {
			pt.increment();
//			System.out.println("Incremented");
		}
	}
	
	public void addSortedByCount(E object, int value) {
		ComparableTreeNode<E> newNode = new ComparableTreeNode<>(object, value);
		
		if(root == null) {
			root = newNode;
		} else {
			addNodeSortedByCount(root, newNode);
		}
	}
	
	private void addNodeSortedByCount(ComparableTreeNode<E> pt, ComparableTreeNode<E> newNode) {
		if(pt.getCount() > newNode.getCount()) {
			if(pt.getLeft() == null) {
				pt.setLeft(newNode);
			} else {
				addNodeSortedByCount(pt.getLeft(), newNode);
			}
		} else if(pt.getCount() < newNode.getCount()) {
			if(pt.getRight() == null) {
				pt.setRight(newNode);
			} else {
				addNodeSortedByCount(pt.getRight(), newNode);
			}
		} else {
			throw new InputMismatchException("element already in list");
		}
	}
	
	public void remove(E toRemove) {
		root = remove(toRemove, root);
	}
	
	public ComparableTreeNode<E> remove(E toRemove, ComparableTreeNode<E> node){
		if(node == null) {
			//data to be removed is not in the tree
			throw new IllegalArgumentException();
		}
		
		if(toRemove.compareTo(node.getValue()) < 0) {
			node.setLeft(remove(toRemove, node.getLeft()));
		} else if(toRemove.compareTo(node.getValue()) > 0) {
			node.setRight(remove(toRemove, node.getRight()));
		} else if(node.getLeft() == null) {
			//at the node to delete with empty left subtree
			//just put its right node in its place
			node = node.getRight();
		} else if(node.getRight() == null){
			//at node to delete with empty right subtree
			node = node.getLeft();
		} else {
			//complex part: node has two subtrees
			ComparableTreeNode<E> temp = findMinValue(node.getRight()); 
			node.setValue(temp.getValue(), temp.getCount());
			node.setRight(removeMinValue(node.getRight()));
		}
		return node;
	}
	
	//finds the minimum value in the subtree of a node
	private ComparableTreeNode<E> findMinValue(ComparableTreeNode<E> node) {
		if(node == null){
			return null;
		} else if(node.getLeft() == null) {
			return node;
		} else {
			return findMinValue(node.getLeft());
		}
	}
	
	public void removeMinValue() {
		root = removeMinValue(root);
	}
	
	//removes the minimum value in the subtree of a node and returns its subtree
	private ComparableTreeNode<E> removeMinValue(ComparableTreeNode<E> node){
		if(node == null) {
			throw new IllegalArgumentException();
		} else if(node.getLeft() != null){
			node.setLeft(removeMinValue(node.getLeft()));
			return node;
		} else {
			return node.getRight();
		}
	}
	
	//prints tree sideways, with subtrees indexed
	public void printIndexed() {
		printIndexed(root, 0, true);
	}
	
	private void printIndexed(ComparableTreeNode<E> node, int depth, boolean parentIsDown) {
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
	
	public void printAll() {
		printAll(root);
	}
	
	private void printAll(ComparableTreeNode<E> node) {
		System.out.println(node);
		if(node.getLeft() != null) printAll(node.getLeft());
		if(node.getRight() != null) printAll(node.getRight());
	}
	
	public void printTopN(int n) {
		for(int i = 0; i < n; i++) {
			ComparableTreeNode<E> max = findMostCommon(root);
			System.out.println(max);
			remove(max.getValue());
		}
	}
	
	public ComparableTreeNode<E> getRoot(){
		return root;
	}
	
	public ComparableTreeNode<E> findMostCommon(ComparableTreeNode<E> node) {
		if(node.getLeft() == null && node.getRight() == null) {
			return node;
		} else if(node.getLeft() == null) {
			ComparableTreeNode<E> temp = findMostCommon(node.getRight());
			return (temp.getCount() > node.getCount()) ? temp : node;
		} else if(node.getRight() == null) {
			ComparableTreeNode<E> temp = findMostCommon(node.getLeft());
			return (temp.getCount() > node.getCount()) ? temp : node;
		} else {
			ComparableTreeNode<E> leftTemp = findMostCommon(node.getLeft());
			ComparableTreeNode<E> rightTemp = findMostCommon(node.getRight());
			
			ComparableTreeNode<E> max = (leftTemp.getCount() > rightTemp.getCount()) ? leftTemp : rightTemp;
			return (max.getCount() > node.getCount()) ? max : node;
		}
	}
}