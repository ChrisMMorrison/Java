package wordLadder;

//wrapper class so that if a word is used can be tracked

public class Word {
	
	private String string;
	private boolean used;
	
	public Word(String word) {
		string = word;
		used = false;
	}
	
	public String getString() {
		return string;
	}
	
	public void setString(String word) {
		string = word;
	}
	
	public void setUsed() {
		used = !used;
	}
	
	public boolean isUsed() {
		return used;
	}

}
