package trees;

import java.util.*;

public class TreeNode<T> {
	
	private List<TreeNode<T>> children;
	private TreeNode<T> parent;
	private T data;
	
	public TreeNode(T data) {
		children = new ArrayList<TreeNode<T>>();
		parent = null;
		this.data = data;
	}
	
	public TreeNode(T data, TreeNode<T> parent) {
		children = new ArrayList<TreeNode<T>>();
		this.parent = parent;
		this.data = data;
	}
	
	public List<TreeNode<T>> getChildren(){
		return children;
	}
	
	public void setParent(TreeNode<T> parent) {
		//parent.addChild(this);
		this.parent = parent;
	}
	
	public void addChild(T data) {
		TreeNode<T> child = new TreeNode<T>(data);
		child.setParent(this);
		children.add(child);
	}
	
	public void addChild(TreeNode<T> child) {
		child.setParent(this);
		children.add(child);
	}
	
	public T getData() {
		return data;
	}
	
	public void setData(T data) {
		this.data = data;
	}
	
	public boolean isRoot() {
		return (parent == null);
	}
	
	public boolean isLeaf() {
		return (children.size() == 0);
	}
	
	public void removeParent() {
		parent = null;
	}
	
	public String toString() {
		return data.toString();
	}
	
	public static void printAll(TreeNode<?> node) {
		System.out.println(node);
		for(TreeNode<?> child : node.getChildren()) {
			TreeNode.printAll(child);
		}
	}
	
}
