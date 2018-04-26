package searchTree;

public class SplayTester {

    public static void main(String[] args) {
        TopDownSplayTree<String> tree = TopDownSplayTree.create();
        tree.insert("1");
        tree.insert("2");
        tree.insert("9");
        tree.insert("5");
        tree.printIndexed();
        System.out.println("-----------------------------");
        tree.insert("7");

        tree.printIndexed();
    }
}
