package trees;

public class BinaryTreeNode<E> {
	
	private E value;
	private BinaryTreeNode<E> left;
	private BinaryTreeNode<E> right;
	
	public BinaryTreeNode() {
		
	}
	
	public BinaryTreeNode(E data) {
		value = data;
		left = null;
		right = null;
	}
	
	public BinaryTreeNode(E data, BinaryTreeNode<E> left, BinaryTreeNode<E> right) {
		value = data;
		this.left = left;
		this.right = right;
	}
	
	public E getValue() {
		return value;
	}
	
	public BinaryTreeNode<E> getLeft(){
		return left;
	}
	
	public BinaryTreeNode<E> getRight(){
		return right;
	}
	
	public void setValue(E value) {
		this.value = value;
	}
	
	public void setLeft(BinaryTreeNode<E> left) {
		this.left = left;
	}
	
	public void setRight(BinaryTreeNode<E> right) {
		this.right = right;
	}
	
	public String toString() {
		return  value.toString();
	}
	
}
