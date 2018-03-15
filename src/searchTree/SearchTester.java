package searchTree;

import bibleWords.*;


public class SearchTester {

    public static void main(String[] args) {
        AVLTree<Integer, Integer> tree = new AVLTree<>();
    /*    ComparableTree<Integer> old = new ComparableTree<>();
        Timer bigTimer = new Timer();
        NanoTimer timer = new NanoTimer();

        bigTimer.start();
        for (int i = 0; i < 90000; i++) {
    //        tree.insert(i, i);
        }
        bigTimer.stop();
        System.out.println(bigTimer.getTime());
        bigTimer.start();
        for (int i = 0; i < 90000; i++) {
            old.add(i);
        }
        bigTimer.stop();
        System.out.println(bigTimer.getTime());

        for (int i = 0; i < 90000; i += 4500) {
            timer.start();
            System.out.println("AVL: " + tree.getData(i));
            timer.stop();

            System.out.println("-----------------------------------------------\n");

            timer.start();
            System.out.println("BST: " + old.getNode(old.getRoot(), i).getValue());
            timer.stop();
            System.out.println("-----------------------------------------------\n");

        }*/

        for (int i = 0; i < 10; i++) {
            tree.insert((int)(Math.random() * 20), 0);
            tree.printIndexed();
            System.out.println("------------------------------------------------\n");
        }
    }
}
