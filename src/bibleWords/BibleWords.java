package bibleWords;

import java.util.ArrayList;

import linkedlist.*;
import trees.*;
import hash.*;

public class BibleWords {
	
	private GenericLinkedList<String> list;
	private OrderArrayList array, array2;
	private ComparableTree<String> tree;
	private BibleAVL AVLTree;
	private HashTable<String> hashTable;
	
	public static void main(String[] args) {
		new BibleWords().init();
	}
	
	private void init() {
		
		EasyReader reader = new EasyReader("BibleKingJames.txt");
		if (reader.bad()){
			     System.err.println("Can't open Bible");
			     System.exit(1);
			   }
		
		list = new GenericLinkedList<>();
		array = new OrderArrayList();
		array2 = new OrderArrayList();
		tree = new ComparableTree<>();
		AVLTree = new BibleAVL();
		hashTable = new HashTable<>(9990000);
		
		ArrayList<String> words = new ArrayList<>();
		String word;
		
	//	for(int i = 0; i < 7; i++) {
			reader = new EasyReader("BibleKingJames.txt");
			while((word = reader.readWord()) != null) {
				word = word.toLowerCase();
				char[] chars = word.toCharArray();
				if(!Character.isDigit(chars[0])) {
					words.add(word);
				}
			}
			reader = new EasyReader("words_alpha.txt");
			while((word = reader.readWord()) != null) {
				word = word.toLowerCase();
				char[] chars = word.toCharArray();
				if(!Character.isDigit(chars[0])) {
					words.add(word);
				}
			}
		//	System.err.println(i+1);
		//}
			

		System.err.println("Bible Loaded, Beginning Comparisons...");
		Timer timer = new Timer();
		/*
		timer.start();
		for(String token : words) {
			list.insertInOrder2(token);
		}
		
		timer.stop();
		
		System.out.println("LinkedList Results: ");
		list.printTopN(10);
		System.out.println(timer.getTime());
		
		timer.start();
		for(String token : words) {
			list.insertInOrder(token);
		}
		
		timer.stop();

		System.out.println("LinkedList2 Results: ");
		list.printTopN(10);
		System.out.println(timer.getTime());
		*/
		timer.start();
		
		for(String token : words) {
			array.insertStringInOrder(token);
		}	
		
		timer.stop();
		
		System.out.println();
		System.out.println("Array with Sequential Sort Results:");
		array.printTopN(10);
		System.out.println(timer.getTime());

		timer.start();
		for(String token : words) {
				array2.binaryInsertStringInOrder(token);
			}	
		
		timer.stop();
		
		System.out.println();
		System.out.println("Array with Binary Sort Results:");
		array2.printTopN(10);
		System.out.println(timer.getTime());
		reader.close();
		
		timer.start();
		for(String token : words) {
				hashTable.add(token);
			}	
		
		timer.stop();
		
		System.out.println();
		System.out.println("Hash table results:");
		hashTable.printTopN(10);
		System.out.println(timer.getTime());

		timer.start();
		for(String token : words) {
				tree.add(token);
			}	
		
		timer.stop();
		
		System.out.println();
		System.out.println("Binary Tree Results:");
		tree.printTopN(10);
		System.out.println(timer.getTime());

     /*   timer.start();
        for(String token : words) {
            AVLTree.add(token);
        }

        timer.stop();

        System.out.println();
        System.out.println("AVL Tree Results:");
        AVLTree.getTree().printTopN(10);
        System.out.println(timer.getTime());
		reader.close();
			*/
	}
}
