package searchTree;

public class SearchTreeNode <K extends Comparable<K>, E>{
	private K key;
	private E value;
	private int height;

	private SearchTreeNode<K, E> left, right;

	public SearchTreeNode(K key, E data) {
		this.key = key;
		value = data;
		left = null;
		right = null;
	}

	public SearchTreeNode(K key, E data, SearchTreeNode<K, E> leftNode, SearchTreeNode<K, E> rightNode) {
		this.key = key;
		value = data;
		left = leftNode;
		right = rightNode;
	}

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public K getKey() { return key; }

	public E getData() { return value; }

	public void setKey(K newKey) { key = newKey; }
	
	public void setData(E data) {
		value = data;
	}
	
	public SearchTreeNode<K, E> getLeft(){
		return left;
	}
	
	public SearchTreeNode<K, E> getRight(){ return right; }

	public void setLeft(SearchTreeNode<K, E> leftNode) { left = leftNode; }
	
	public void setRight(SearchTreeNode<K, E> rightNode) { right = rightNode; }
	
	public String toString() {
		return "key: " + key + " | " + "data: " + value;
	}
}
