package wordLadder;

public class QueueTester {
	
	public static void main(String[] args) {
		Queue<String> queue = new Queue<>();
		
		queue.add("first");
		queue.add("second");
		queue.add("third");
		queue.add("fourth");
		
		for(int i = 0; i < 4; i++) {
			System.out.println(queue.remove());
		}
	}

}
