package bibleWords;

import searchTree.*;

public class BibleAVL extends AVLTree{

    private AVLTree<String, Integer> tree;

    public BibleAVL(){
        tree = new AVLTree<>();
    }

    public void add(String token){
        if(tree.getData(token) == null){
            tree.insert(token, 1);
        } else {
            SearchTreeNode<String, Integer> node = tree.getNode(token);
            node.setData(node.getData() + 1);
        }
    }

    public AVLTree getTree(){
        return tree;
    }
}
