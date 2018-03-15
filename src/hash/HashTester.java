package hash;


public class HashTester {
	
	private HashTable<Integer> hashTable;
	
	public void run() {
		hashTable = new HashTable<>(10);
		
		for(int i = 1; i <= 20; i++) {
			hashTable.add(i);
		}
		
		while(hashTable.hasNext()) {
			System.out.println(hashTable.next());
		}
	}
	
	public static void main(String[] args) {
		new HashTester().run();
	}
}
