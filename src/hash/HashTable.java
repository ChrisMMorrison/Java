package hash;

import java.util.*;

import linkedlist.*;

public class HashTable<E> {
	
	List<GenericLinkedList<E>> hashTable;
	int length;
	
	public HashTable(int length) {
		hashTable = new ArrayList<>(length);
		this.length = length;
		for(int i = 0; i <= length; i++) {
			hashTable.add(null);
		}
	}
	
	public void add(E newObj) {
		int hashCode = (newObj.hashCode() % length);
		if(hashCode < 0) {
			hashCode *= -1;
		}
		if(hashTable.get(hashCode) == null) {	
			hashTable.set(hashCode, new GenericLinkedList<E>(newObj));
		} else {
			hashTable.get(hashCode).insert(newObj);
		}
	}
	
	public void printTopN(int n) {
		
		for(int o = 0; o < n; o++) {
			E max = null;
			int maxCount = 0;
			for(int i = 0; i < length; i++) {
				GenericLinkedList<E> list = hashTable.get(i);
				if(list != null) {
					GenericListNode<E> newNode = list.getMostCommon();
					if(newNode.getCount() > maxCount) {
						max = newNode.getData();
						maxCount = newNode.getCount();
					}
				}
			}
			System.out.println(max +" : "+ maxCount);
			remove(max);
		}
		
	}
	
	public void print() {
		for(int i = 0; i < hashTable.size(); i++) {
			GenericLinkedList<E> list = hashTable.get(i);
			if(list != null) {
				System.out.print(i +" | ");
				list.printList();
			}
		}
	}
	
	public void remove(E toRemove) {
		int hashCode = (toRemove.hashCode() % length);
		if(hashCode < 0) {
			hashCode *= -1;
		}
		GenericLinkedList<E> list = hashTable.get(hashCode);
		if(list == null) {
			throw new IllegalArgumentException(toRemove + " does not exist in hash table");
		} else {
			list.remove(toRemove);
			
			if(list.getNumItems() < 1) {
				hashTable.set(hashCode, null);
			}
		}
	}
	
	//ghetto iterator below!
	public int hashTablePos = 0;
	int linkedListPos = 0;
	
	public E next() {
		GenericLinkedList<E> data = null;
		
		while((data = hashTable.get(hashTablePos)) == null) {
			hashTablePos++;
		}
		
		if(linkedListPos < data.getNumItems()-1) {
			int tmp = linkedListPos;
			linkedListPos++;
			return data.getData(tmp);
		} else {
			int tmp = linkedListPos;
			linkedListPos = 0;
			hashTablePos++;
			return data.getData(tmp);
		}
		
	}
	
	//checks if there is a next item 
	//return false if starting at the previous position there is no new item before the end of the HashTable
	public boolean hasNext() {
		int tmp = hashTablePos;
		GenericLinkedList<E> data = null;
		
		while(tmp < length && (data = hashTable.get(tmp)) == null) {
			tmp++;
		}

        return data != null;
    }
	
	public void resetIterator() {
		hashTablePos = 0;
	}
}
