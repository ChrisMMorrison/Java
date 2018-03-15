package linkedlist;

public class GenericDoubleListNode<E> {
	
	private E data;
	private int count;
	
	private GenericDoubleListNode<E> next;
	private GenericDoubleListNode<E> prior;
	
	public GenericDoubleListNode() {
		next = null;	prior = null;
		count = 0;		data = null;
	}
	
	public GenericDoubleListNode(E element, int num) {
		next = null;	prior = null;
		count = num;	data = element;
	}
	
	public GenericDoubleListNode(GenericDoubleListNode<E> priorNode, GenericDoubleListNode<E> nextNode) {
		next = nextNode;	prior = priorNode;
		count = 0;			data = null;
	}
	
	public GenericDoubleListNode(E element, int num, GenericDoubleListNode<E> priorNode, GenericDoubleListNode<E> nextNode) {
		next = nextNode;	prior = priorNode;
		count = num;		data = element;
	}
	
	public E getData() {
		return data;
	}
	
	public int getCount() {
		return count;
	}
	
	public GenericDoubleListNode<E> getPrior(){
		return prior;
	}
	
	public GenericDoubleListNode<E> getNext(){
		return next;
	}
	
	public void setData(E element) {
		data = element;
	}
	
	public void setCount(int num) {
		count = num;
	}
	
	public void setPrior(GenericDoubleListNode<E> node) {
		prior = node;
	}
	
	public void setNext(GenericDoubleListNode<E> node) {
		next = node;
	}
	
	public String toString() {
		return data.toString() +" : "+ count;
	}

}
