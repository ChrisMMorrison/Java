package trees;

public class ComparableTreeNode<E extends Comparable<E>> implements Comparable<ComparableTreeNode<E>>{
	
	private E value;
	private int count;
	private ComparableTreeNode<E> left;
	private ComparableTreeNode<E> right;
	
	public ComparableTreeNode() {
		value = null;
		count = 0;
		left = null;
		right = null;
	}
	
	public ComparableTreeNode(E data) {
		value = data;
		count = 1;
		left = null;
		right = null;
	}
	
	public ComparableTreeNode(E data, int num) {
		value = data;
		count = num;
		left = null;
		right = null;
	}
	
	public ComparableTreeNode(E data, ComparableTreeNode<E> left, ComparableTreeNode<E> right) {
		value = data;
		this.left = left;
		this.right = right;
		count = 1;
	}
	
	public int getCount() {
		return count;
	}

	public E getValue(){
	    return value;
    }
	
	public ComparableTreeNode<E> getLeft(){
		return left;
	}
	
	public ComparableTreeNode<E> getRight(){
		return right;
	}
	
	public void setValue(E value, int count) {
		this.value = value;
		this.count = count;
	}
	
	public void setLeft(ComparableTreeNode<E> left) {
		this.left = left;
	}
	
	public void setRight(ComparableTreeNode<E> right) {
		this.right = right;
	}
	
	public void increment() {
		count++;
	}
	
	//a.compareTo(b)
	//a.getValue.compareTo(b.getValue)
	public int compareTo(ComparableTreeNode<E> other) {
		int result = value.compareTo(other.getValue());
		return result;
		
	}
	
	public String toString() {
		return  value +":"+ count;
	}
	
}
