package wordLadder;

import java.util.*;

import bibleWords.EasyReader;
import bibleWords.Timer;
//import hash.HashTable;

public class WordLadder {
	
	private ArrayList<Word> wordList;
	private Queue<Stack<String>> masterQueue;
	private Stack<String> masterCopy;
	
	public WordLadder(String file) {
		EasyReader reader = new EasyReader(file);
		wordList = new ArrayList<>(9999999);
		
		String word;
		while((word = reader.readWord()) != null) {
			wordList.add(new Word(word));
		}
	}
	
	public Stack<String> findWordLadder(String start, String end) {
		masterQueue = new Queue<Stack<String>>();
		//masterCopy starts with start word
		masterCopy = new Stack<String>();
		masterCopy.push(start);
		
		//cycle through words one letter off start word
		findOneLetterOffWords(start);
		
		//otherwise, keep cycling through words one letter off of top word in the first stack in queue until top word is = to end word
		while(masterQueue.getNumItems() > 0 && !masterQueue.peek().peek().equals(end)) {
			Stack<String> stack = masterQueue.remove();
			masterCopy = stack;
			findOneLetterOffWords(stack.peek());
		}
		
		if(masterQueue.getNumItems() <= 0) {
			System.err.println("No word ladder can be found");
			return new Stack<>();
		}
		
		//reset used flags for all words
		for(Word word : wordList) {
			word.setUsed();
		}
		
		return masterQueue.remove();
	}
	
	//cycles through wordList looking for one letter off words
	//any words found are pushed on top of a copy of the masterCopy and added to the queue
	public void findOneLetterOffWords(String base){
		
		Iterator<Word> iterator = wordList.iterator();
		while(iterator.hasNext()) {
			Word word = iterator.next();
			if(!word.isUsed() && isOneLetterOff(base, word.getString())) {
				word.setUsed();
				//warning is suppressed since masterCopy is guaranteed to be a Stack<String>
				@SuppressWarnings("unchecked")
				Stack<String> stack = (Stack<String>)(masterCopy.clone());
				stack.push(word.getString());
				masterQueue.add(stack);
			}
		}
	}
	
	//checks if supplied words are equal except for one letter
	public boolean isOneLetterOff(String base, String word) {
		boolean oneLetterOff = false;
		char[] baseLetters = base.toCharArray();
		char[] wordLetters = word.toCharArray();
		
		if(baseLetters.length != wordLetters.length) {
			return false;
		}
		
		for(int i = 0; i < base.length(); i++) {
			if(baseLetters[i] != wordLetters[i]) {
				if(oneLetterOff) {
					//word is now 2 letters off
					return false;
				} else {
					oneLetterOff = true;
				}
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		WordLadder wordLadder = new WordLadder("words_alpha.txt");
		Scanner scanner = new Scanner(System.in);
		System.out.println("Input words in form 'startWord|endWord'");
		while(scanner.hasNext()) {
			String token = scanner.next();
			token = token.toLowerCase();
			int pos = token.indexOf("|");
			if(pos > 1) {
				String start = token.substring(0, pos);
				String end = token.substring(pos+1);
				
				if(end.length() != start.length()) {
					System.err.println("Start and End word must be same length");
				} else {
					Timer timer = new Timer();
					timer.start();
					Stack<String> ladder = wordLadder.findWordLadder(start, end);
					for(String str : ladder) {
						System.out.println(str);
					}
					
					timer.stop();
					System.out.println("Elapsed Time: " + timer.getTime());
					System.out.println("------------------------------------------");
				}
				
				
			} else {
				System.err.println("Invalid Input");
			}
			
		} 
		scanner.close();
	}

}
