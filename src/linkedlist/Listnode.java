package linkedlist;

public class Listnode implements Comparable{

		private String data;
		private int count;
		private Listnode next;
		
		public Listnode(String d) {
			data = d;
			next = null;
			count = 1;
		}
		
		public Listnode(String d, Listnode n) {
			data = d;
			next = n;
			count = 1;
		}
		
		public String getData() {
			return  data;
		}
		
		public int getCount() {
			return count;
		}
		
		public Listnode getNext() {
			return next;
		}
		
		public void setData(String d) {
			data = d;
		}
		
		public void incrementCount() {
			count++;
		}
		
		public void setNext(Listnode n) {
			next = n;
		}

		public int compareTo(Object o) {
			
			return data.compareTo((String)o);
		}
		
		public String toString() {
			return data + ": " + count;
		}
}
