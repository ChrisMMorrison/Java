package linkedlist;

public class GenericLinkedList<E> {
	
	private GenericListNode<E> head;
	private GenericListNode<E> tail;
	
	private int numItems;
	
	public GenericLinkedList() {
		head = null;
		tail = head;
		numItems = 0;
	}
	
	public GenericLinkedList(GenericListNode<E> data) {
		head = data;
		tail = head;
		numItems = 1;
	}
	
	public GenericLinkedList(E data) {
		head = new GenericListNode<E>(data);
		tail = head;
		numItems = 1;
	}
	
	public void append(GenericListNode<E> appendNode) {
		add(appendNode, numItems);
	}
	
	public void append(E data, int value) {
		add(new GenericListNode<E>(data, value), numItems);
	}
	
	public void append(E data) {
		add(new GenericListNode<E>(data, 1), numItems);
	}
	public void add(E data, int value, int pos) {
		add(new GenericListNode<E>(data, value), pos);
	}
	
	public void add(GenericListNode<E> addNode, int pos) {
		if(pos == 0) {
			addNode.setNext(head);
			head = addNode;
			if(numItems == 0) {
				tail = head;
			}
			numItems++;
		} else if(pos == numItems) {
			tail.setNext(addNode);
			tail = addNode;
			numItems++;
		} else if(pos > numItems) {
			throw new IndexOutOfBoundsException("position to insert is larger than LinkedList size");
		} else {
			GenericListNode<E> tmp = head;
			GenericListNode<E> lag = head;
			
			for(int i = 0; i < pos; i++) {
				lag = tmp;
				tmp = tmp.getNext();
			}
			
			lag.setNext(addNode);
			addNode.setNext(tmp);
			numItems++;
		}
	} 
	
	//used in hashTable
	public void insert(E toAdd) {
		GenericListNode<E> tmp = head;
		while(tmp.getNext() != null && tmp.getData().equals(toAdd) != true) {
			tmp = tmp.getNext();
		}
		if(tmp.getData().equals(toAdd)) {
			tmp.increment();
		} else {
			GenericListNode<E> addNode = new GenericListNode<E>(toAdd);
			tail.setNext(addNode);
			tail = addNode;
			numItems++;
		//	System.out.println("Collision");
		}	
	}
	
	public void remove(E toRemove) {
		boolean success = removeNode(toRemove);
		if(success != true) {
			throw new IllegalArgumentException("element to remove is not in list");
		}
	}
	//removes first instance of toRemove
	private boolean removeNode(E toRemove) {
		GenericListNode<E> tmp = head;
		GenericListNode<E> lag = head;
		
		if(tmp.getData().equals(toRemove)) {
			head = head.getNext();
			numItems = numItems - 1;
			return true;
		} else {
			while(tmp.getNext() != null) {
				lag = tmp;
				tmp = tmp.getNext();
				if(tmp.getData().equals(toRemove)) {
					lag.setNext(tmp.getNext());
					numItems = numItems - 1;
					return true;
				}
			}
		}
		return false;
	}
	/*
	public void insertInOrder2(E comp) {
		GenericListNode<E> tmp = head;
		GenericListNode<E> lag = head;
		GenericListNode<E> insert = new GenericListNode<E>(comp);
		
		//find index of word that has no words before it where compareTo() return >= 0
		while(tmp != null && tmp.compareTo(insert) < 0) {
			lag = tmp;
			tmp = tmp.getNext();
		}
		
		//if list is empty then head points to nowhere
		//point head to the new node with comp
		if(head == null) {
			head = insert;
			numItems++;
		} else if(tmp == head){
			if(tmp.compareTo(insert) == 0) {
				tmp.increment();
			} else {
				insert.setNext(head);
				head = insert;
				numItems++;
			}
		} else if(tmp == null) {
			if(lag.compareTo(insert) == 0) {
				lag.increment();
			} else {
				lag.setNext(insert);
				numItems++;
			}
		} else if(tmp.compareTo(insert) == 0) {
			tmp.increment();
		} else {
			lag.setNext(insert);
			insert.setNext(tmp);
			numItems++;
		}
	}
	
	public void insertInOrder(E comparable) {
		GenericListNode<E> tmp = head;
		GenericListNode<E> lag = head;
		int result;
		boolean indexFound = false;
		
		while(tmp != null && !indexFound) {
			result = tmp.getData().compareTo(comparable);
			if(result > 0) {
				indexFound = true;
			} else if(result < 0) {
				lag = tmp;
				tmp = tmp.getNext();
			} else {
				tmp.increment();
				return;
			}
		}
		
		GenericListNode<E> newNode = new GenericListNode<E>(comparable);
		
		if(tmp == head) {
			newNode.setNext(head);
			head = newNode;
		} else if(tmp == null) {
			tail.setNext(newNode);
			tail = newNode;
			numItems++;
		} else {
			lag.setNext(newNode);
			newNode.setNext(tmp);
		}
		
		numItems++;
		
	}
	*/
	public E getData(int pos) {
		return getNode(pos).getData();
	}
	
	public GenericListNode<E> getNode(int pos) {
		if(pos < numItems) {
			GenericListNode<E> tmp = head;
			for(int i = 0; i < pos; i++) {
				tmp = tmp.getNext();
			}
			return tmp;
		} else {
			throw new IndexOutOfBoundsException();
		}
	}
	
	public GenericListNode<E> getHead(){
		return head;
	}
	
	public int getNumItems() {
		return numItems;
	}
	
	public void printList() {
		GenericListNode<E> tmp = head;
		System.out.println(tmp);
		while(tmp.getNext() != null) {
			tmp = tmp.getNext();
			System.out.println(tmp);
		}
	}
	
	public GenericListNode<E> getMostCommon() {
		GenericListNode<E> max = null;
		int maxCount = -1;
		
		for(int i = 0; i < numItems; i++) {
			GenericListNode<E> tmp = getNode(i);
			if(maxCount < tmp.getCount()) {
				maxCount = tmp.getCount();
				max = tmp;
			}
		}
		return max;
	}
	
	public void printTopN(int num) {
		if(num == 0) {
			num = numItems;
		}
		
		for(int i = 0; i < num; i++) {
			int max = 0;
			GenericListNode<E> maxNode = null;
			GenericListNode<E> tmp = head;
			while(tmp != null) {
				if(tmp.getCount() > max) {
					maxNode = tmp;
					max = maxNode.getCount();
				}
				tmp = tmp.getNext();
			}
			System.out.println(maxNode.getData() + ": " + max);
			
			if(maxNode == head) {
				head = maxNode.getNext(); //special case if first in list
			} else {
				GenericListNode<E> lag = head;
				while(lag.getNext() != maxNode) {
					lag = lag.getNext();
				}
				lag.setNext(maxNode.getNext());
			}
		}
	}

}
