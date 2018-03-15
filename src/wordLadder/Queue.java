package wordLadder;

import linkedlist.GenericListNode;

public class Queue<E> {

	private GenericListNode<E> head;
	private GenericListNode<E> tail;
	private int count;
	
	public Queue() {
		head = null;
		tail = head;
		count = 0;
	}
	
	public Queue(E element) {
		head = new GenericListNode<>(element, 1);
		tail = head;
		count = 1;
	}
	
	public void add(E element) {
		add(new GenericListNode<E>(element, 1));
	}
	
	public void add(GenericListNode<E> node) {
		//list is empty
		if(head == null) {
			head = node;
			tail = head;
		} else {
			tail.setNext(node);
			tail = node;	
		}
		count++;
	}
	
	public E remove() {
		GenericListNode<E> tmp = head;
		head = head.getNext();
		count--;
		return tmp.getData();
	}
	
	public E peek() {
		return head.getData();
	}
	
	public int getNumItems() {
		return count;
	}
	
}
	
	