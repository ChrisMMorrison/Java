package morseCode;

import trees.*;
import linkedlist.*;

import java.util.Scanner;
import bibleWords.EasyReader;

public class MorseCode{
	
	ComparableTree<String> morseTree;
	GenericLinkedList<String> codedList;
	
	public void buildTree() {
		EasyReader reader = new EasyReader("MorseCode.txt");
		if (reader.bad()) {
			System.err.println("Can't open Text");
			System.exit(1);
		}
		
		codedList = new GenericLinkedList<>();
		
		morseTree = new ComparableTree<>();
		morseTree.add("Start", 25);
		
		while(!reader.eof()) {
			String line = reader.readLine();
			if(line != null) {
				int index = line.indexOf(":");
				String token = line.substring(0, index);
				int value = Integer.parseInt(line.substring(index+1));
				
				codedList.append(token, value);
				morseTree.addSortedByCount(token, value);
				
			}
		}
		
	}
	
	public String decode(String morseCode) {
		//word indexes is padded with -1 and bits.length so that letters in the front and back dont cause errors
		GenericLinkedList<Integer> wordIndexes = new GenericLinkedList<>(-1);
		char[] bits = morseCode.toCharArray();
		String result = "";
		
		//find each space in between morse code
		int lastPos = 0;
		while(morseCode.indexOf(" ", lastPos) > 0) {
			int pos = morseCode.indexOf(" ", lastPos);
			wordIndexes.append(pos);
			lastPos = pos+1;
		}
		wordIndexes.append(bits.length);
			
		for(int i = 0; i < wordIndexes.getNumItems()-1; i++) {
			
			//split up morsecode into individual coded letters from the whitespace
			int start = wordIndexes.getNode(i).getData() +1;
			int end = wordIndexes.getNode(i+1).getData()-1;
			ComparableTreeNode<String> decodedLetter = morseTree.getRoot();
			
			for(int o = start; o <= end; o++) {
				if(bits[o] == '.' && decodedLetter.getLeft() != null) {
					decodedLetter = decodedLetter.getLeft();
				} else if(bits[o] == '-' && decodedLetter.getRight() != null) {
					decodedLetter = decodedLetter.getRight();
				//if we have traversed the tree and we haven't found a matching letter 
				} else if(decodedLetter.getRight() == null || decodedLetter.getLeft() == null) {
					System.err.print("code sequence is not valid morse code");
					return "";
				//if there are some characters other than . or -	
				} else {
					System.err.print("code does not consist of . or -");
					return "";
				}
			}
			//although we have found a matching node in the tree, the node is not valid morse code
			if(decodedLetter.getValue().equals("n/a")) {
				System.err.print("code sequence is not valid morse code");
				return "";
			} else {
				result += decodedLetter.getValue();
			}
		}
		return result;
	}
	
	public String encode(String word) {
		char[] letters = word.toCharArray();
		String result = "";
		
		//find corresponding numerical value to each character
		for(int i = 0; i < letters.length; i++) {
			GenericListNode<String> tmp = codedList.getHead();
			while(tmp.getNext() != null && (tmp.getData().indexOf(letters[i]) < 0 || tmp.getData().equals("n/a"))) {
				tmp = tmp.getNext();
			}
			
			if(tmp.getNext() == null) {
				System.err.print("the character \"" +letters[i]+ "\" has no morse code equivalent");
				return "";
			}
			int count = tmp.getCount();
			ComparableTreeNode<String> root = morseTree.getRoot();
			//cycle through tree looking for matching values
			while(root.getCount() != count) {
				if(root.getCount() > count) {
					result += ".";
					root = root.getLeft();
				} else {
					result += "-";
					root = root.getRight();
				}
			}
			result += " ";
		}
		return result;
	}
	
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while(scanner.hasNext()) {
			String line = scanner.nextLine();
			if(line.indexOf(".") >= 0 || line.indexOf("-") >= 0) {
				System.out.println(decode(line));
			} else if(line.equals("print tree")) { 
				morseTree.printIndexed();
			}else {
				System.out.println(encode(line));
			}
		}
		scanner.close();
		
	}
	
	public static void main(String[] args){
		MorseCode morseCode = new MorseCode();
		morseCode.buildTree();
		morseCode.run();
	}

}
