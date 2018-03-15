 package linkedlist;

public class LinkedList {
	
	Listnode head, last;
	int numItems;
	
	public LinkedList() {
		head = last = null;
		numItems = 0;
	}
	
	public void insertStringInOrder(String comp) {
		Listnode tmp = head;
		Listnode lag = head;
		Listnode insert = new Listnode(comp, null);
		
		//find index of word that has no words before it where compareTo() return >= 0
		while(tmp != null && tmp.getData().compareTo(comp) < 0) {
			lag = tmp;
			tmp = tmp.getNext();
		}
		
		//if list is empty then head points to nowhere
		//point head to the new node with comp
		if(head == null) {
			head = insert;
			numItems++;
		} else if(tmp == head){
			if(tmp.compareTo(comp) == 0) {
				tmp.incrementCount();
			} else {
				insert.setNext(head);
				head = insert;
				numItems++;
			}
		} else if(tmp == null) {
			if(lag.compareTo(comp) == 0) {
				lag.incrementCount();
			} else {
				lag.setNext(insert);
				numItems++;
			}
		} else if(tmp.compareTo(comp) == 0) {
			tmp.incrementCount();
		} else {
			lag.setNext(insert);
			insert.setNext(tmp);
			numItems++;
		}
		
	//	printTopN(numItems);
		
	}
	
	public void removeNode(int pos){
		Listnode N = head;
		for(int i = 0; i < pos; i++) {
			N = N.getNext();
		}
		
		if(N == head) {
			head = N.getNext(); //special case if first in list
		} else {
			Listnode tmp = head;
			while(tmp.getNext() != N) {
				tmp = tmp.getNext();
			}
			tmp.setNext(N.getNext());
		}
	}
	
	public void printTopN(int num) {
		if(num == 0) {
			num = numItems;
		}
		
		for(int i = 0; i < num; i++) {
			int max = 0;
			Listnode maxNode = null;
			Listnode tmp = head;
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
				Listnode lag = head;
				while(lag.getNext() != maxNode) {
					lag = lag.getNext();
				}
				lag.setNext(maxNode.getNext());
			}
		}
	}
	
	public void printFirstN(int num){
		Listnode tmp = head;
		for(int i= 0; i< num; i++) {
			System.out.println(tmp);
			tmp = tmp.getNext();
		}
	}
	
	//prints all items in the list
	public String toString() {
		Listnode tmp = head;
		String result = "";		
		while(tmp != last) {
			result += tmp.getData() + " | " + tmp.getCount() + "\n";
			tmp = tmp.getNext();
		}
		
		return result;
	}
}
