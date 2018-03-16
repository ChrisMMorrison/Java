package searchTree;

public class SearchTester {

    public static void main(String[] args) {
       BottomUpSplayTree<Integer, String> splay = new BottomUpSplayTree<>();
       splay.insert(4, "first");
       splay.insert(6, "second");
        System.out.println("----------------------------------------------");
       splay.printIndexed();
        System.out.println("----------------------------------------------");
       splay.insert(5, "third");
       System.out.println("----------------------------------------------");
       splay.printAll();
    }
}
