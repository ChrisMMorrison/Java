package searchTree;

import java.util.Stack;

public class BottomUpSplayTree<K extends Comparable<K>, E> {

    private SearchTreeNode<K, E> root;
    private Stack<SearchTreeNode<K, E>> accessPath;

    public BottomUpSplayTree() {
        root = null;
    }

    /*Left Zig (LL): following conversion when given pointer to a:
     *  a                 b
     *   \               / \
     *    b      ->     a   c
     *   / \             \
     *  x   c             x
     */
    private SearchTreeNode<K, E> leftZig(SearchTreeNode<K, E> a){
        SearchTreeNode<K, E> b = a.getRight();

        a.setRight(b.getLeft());
        b.setLeft(a);
        return b;
    }

    /*Right Zig (RR): following conversion when given pointer to c:
     *      c             b
     *     /             / \
     *    b      ->     a   c
     *   / \               /
     *  a   x             x
     */
    private SearchTreeNode<K, E> rightZig(SearchTreeNode<K, E> c){
        SearchTreeNode<K, E> b = c.getLeft();

        c.setLeft(b.getRight());
        b.setRight(c);
        return b;
    }

    /*Left-Right Zig-zag (LR): following conversion when given pointer to c:
     *  a                b
     *   \              / \
     *    c     ->     a   c
     *   /
     *  b
     */
    private SearchTreeNode<K, E> leftZigZag(SearchTreeNode<K, E> a){
        SearchTreeNode<K, E> b = rightZig(a.getRight()); //right rotate right subtree
        a.setRight(b);
        return leftZig(a);
    }

    /*Right-Left Zig-zag (RL): following conversion when given pointer to c:
     *      c             b
     *     /             / \
     *    a      ->     a   c
     *     \
     *      b
     */
    private SearchTreeNode<K, E> rightZigZag(SearchTreeNode<K, E> c){
        SearchTreeNode<K, E> b = leftZig(c.getLeft()); //right rotate right subtree
        c.setLeft(b);
        return rightZig(c);
    }

    private SearchTreeNode<K, E> rightZigZig(SearchTreeNode<K, E> node){
        SearchTreeNode<K, E> newNode =  rightZig(node);
        return rightZig(newNode);
    }
    private SearchTreeNode<K, E> leftZigZig(SearchTreeNode<K, E> node){
        SearchTreeNode<K, E> newNode =  leftZig(node);
        return leftZig(newNode);
    }

    public void insert(K key, E data){
        accessPath = new Stack<>();
        if(root != null){
            accessPath.push(root);
        }
        root = insert(root, new SearchTreeNode<K, E>(key, data));
    }

    private SearchTreeNode<K, E> insert(SearchTreeNode<K, E> currentNode, SearchTreeNode<K, E> insertNode){
        if (currentNode == null) {
            insertNode.setHeight(0);
            return insertNode;
        }

        K key = insertNode.getKey();
        K currKey = currentNode.getKey();
        accessPath.push(currentNode);

        if (key.compareTo(currKey) < 0) {
            currentNode.setLeft(insert(currentNode.getLeft(), insertNode));
        } else if (key.compareTo(currKey) > 0) {
            currentNode.setRight(insert(currentNode.getRight(), insertNode));

        } else {
            //node exists with same key
            System.err.println("Key already exists in Splay Tree");
            return currentNode;
        }

        return splay(currentNode, insertNode);
    }

    private SearchTreeNode<K, E> splay(SearchTreeNode<K, E> currentNode, SearchTreeNode<K, E> otherNode){
        boolean XisLeftChild = (currentNode.getKey().compareTo(otherNode.getKey()) > 0);

        //if p is root
        if(root.getKey().equals(accessPath.peek().getKey())){
            if(XisLeftChild){
                return rightZig(accessPath.pop());
            } else {
                return leftZig(accessPath.pop());
            }
            //otherwise check if zig-zigging or zig-zagging
        } else {
            SearchTreeNode<K, E> parent = accessPath.pop();
            SearchTreeNode<K, E> grandparent = accessPath.pop();
            if (grandparent.getKey().compareTo(parent.getKey()) > 0) {
                if(XisLeftChild){
                    //left-left
                    return rightZigZig(grandparent);
                } else {
                    //left-right
                    return rightZigZag(grandparent);
                }
            } else {
                if(XisLeftChild){
                    //right-left
                    return leftZigZag(grandparent);
                } else {
                    //right-right
                    return leftZigZig(grandparent);
                }
            }
        }
    }

    public void printIndexed() {
        printIndexed(root, 0, true);
    }

    private void printIndexed(SearchTreeNode<K, E> node, int depth, boolean parentIsDown) {
        if(node != null) {
            printIndexed(node.getRight(), depth+1, true);

            StringBuilder tabs = new StringBuilder();
            for(int i = 1; i <= depth; i++) {
                tabs.append("\t\t");
            }
            String direction = "";

            if(depth != 0) {
                direction = parentIsDown ? "v " : "^ ";
            }

            System.out.println(tabs + direction + node);

            printIndexed(node.getLeft(), depth+1, false);
        }
    }
}
