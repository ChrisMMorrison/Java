package bibleWords;

import java.util.ArrayList;
import java.util.Arrays;

import linkedlist.*;

public class OrderArrayList {
	
	private Listnode[] array;
	private int numItems;
	
	public OrderArrayList() {
		array = new Listnode[1000000];
		numItems = 0;
	}
	
	public void insertStringInOrder(String word) {
		int i = 0;
		while((i < numItems) && ((array[i].getData()).compareTo(word) < 0)) {
			i++;
		}
		//if i =numItems then the word is greater than every word and should be appended
		if(i == numItems) {
			array[numItems] = new Listnode(word);
			numItems++;
		} else if(array[i].compareTo(word) == 0) {	//if the value at i is equal to the current word
			array[i].incrementCount();
		} else { //if the value at i is the value at which a new word should be inserted
			shiftRightFrom(i);
			array[i] = new Listnode(word);
			numItems++;
		}
	}
	
	public void binaryInsertStringInOrder(String word) {
		binaryInsert(word, 0, numItems-1);
	}
	
	public void binaryInsert(String word, int left, int right) {
		if(left <= right) {
			
			int middle = (left + right) / 2; 
			if (array[middle].compareTo(word) == 0) {
				array[middle].incrementCount();
			} else if (array[middle].compareTo(word) > 0) {
				binaryInsert(word, left, middle - 1); 
			} else {
				binaryInsert(word, middle + 1, right);
			}
		} else {
			shiftRightFrom(left);
			array[left] = new Listnode(word);
			numItems++;
		}
	}

	public void shiftRightFrom(int index) {
		int o = numItems;
		while(o >= index) {
			array[o+1] = array[o];
			o--;
		}
	}
	
	public void printTopN(int num) {
		
		ArrayList<Listnode> arrayList = new ArrayList<Listnode>(Arrays.asList(array));
		
		if(num == 0) {
			num = numItems;
		}
		
		for(int i = 0; i < num; i++) {
			int max = 0, maxIndex = 0;
			for(int o = 0; o < numItems; o++) {
				if(arrayList.get(o).getCount() > max) {
					max = arrayList.get(o).getCount();
					maxIndex = o;
				}
			}
			System.out.println(arrayList.get(maxIndex).getData() + ": " + max);
			arrayList.remove(maxIndex);
			numItems--;
		}
	}
	
	public void printFirstN(int num) {
		for(int i = 0; i < num; i++) {
			System.out.println(array[i]);
		}
	}
	
	public String toString() {
		String str = "";
		for(int i = 0; i < array.length; i++) {
			str += array[i] + ": " + array[i].getCount() + "\n";
		}
		
		return str;
	}

}
