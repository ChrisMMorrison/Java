package linkedlist;

public class GenericListNode<E>{
	
	private E data;
	private int count;
	private GenericListNode<E> next;
	
	public GenericListNode(E data) {
		this.data = data;
		count = 1;
		next = null;
	}
	
	public GenericListNode(E data, GenericListNode<E> nextNode) {
		this.data = data;
		count = 1;
		next = nextNode;
	}
	
	public GenericListNode(E data, int count) {
		this.data = data;
		this.count = count;
		next = null;
	}
	
	public GenericListNode(E data, int count, GenericListNode<E> nextNode) {
		this.data = data;
		this.count = count;
		next = nextNode;
	}
	
	public void setData(E newData) {
		data = newData;
	}
	
	public E getData() {
		return data;
	}
	
	public void setCount(int num) {
		count = num;
	}
	
	public void increment() {
		count = count+1;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setNext(GenericListNode<E> nextNode) {
		next = nextNode;
	}
	
	public GenericListNode<E> getNext(){
		return next;
	}
	
	public String toString() {
		return data +" : "+ count;
	}

}
