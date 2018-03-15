package searchTree;

public class AVLTree <K extends Comparable<K>, E>{

    private SearchTreeNode <K, E> root;

    public AVLTree(){
        root = null;
    }

    public E getData(K key){
        SearchTreeNode<K, E> node = getNode(root, key);
        if(node != null) {
            return node.getData();
        } else {
            return null;
        }
    }

    public SearchTreeNode<K, E> getNode(K key){
        return getNode(root, key);
    }

    private SearchTreeNode<K, E> getNode(SearchTreeNode<K, E> node, K key) {

       if(node == null){
           return null;
       }

        K testKey = node.getKey();

        if (key.compareTo(testKey) == 0) {
            return node;
        } else if (key.compareTo(testKey) > 0) {
            if (node.getRight() == null) {
               // System.err.println("Key does not exist in Search Tree");
                return null;
            } else {
                return getNode(node.getRight(), key);
            }
        } else {
            if (node.getLeft() == null) {
              //  System.err.println("Key does not exist in Search Tree");
                return null;
            } else {
                return getNode(node.getLeft(), key);
            }
        }
    }

    private int max(int a, int b){
        return (a > b) ? a : b;
    }

    /*Left Rotation (LL): following conversion when given pointer to a:
     *  a                 b
     *   \               / \
     *    b      ->     a   c
     *   / \             \
     *  x   c             x
     */
    private SearchTreeNode<K, E> leftRotate(SearchTreeNode<K, E> a){
        SearchTreeNode<K, E> b = a.getRight();

        a.setRight(b.getLeft());
        b.setLeft(a);
        return b;
    }

    /*Right Rotation (RR): following conversion when given pointer to c:
     *      c             b
     *     /             / \
     *    b      ->     a   c
     *   / \               /
     *  a   x             x
     */
    private SearchTreeNode<K, E> rightRotate(SearchTreeNode<K, E> c){
        SearchTreeNode<K, E> b = c.getLeft();

        c.setLeft(b.getRight());
        b.setRight(c);
        return b;
    }

    /*Left-Right Rotation (LR): following conversion when given pointer to c:
     *  a                b
     *   \              / \
     *    c     ->     a   c
     *   /
     *  b
     */
    private SearchTreeNode<K, E> leftRightRotate(SearchTreeNode<K, E> a){
        SearchTreeNode<K, E> b = rightRotate(a.getRight()); //right rotate right subtree
        a.setRight(b);
        return leftRotate(a);
    }

    /*Right-Left Rotation (RL): following conversion when given pointer to c:
     *      c             b
     *     /             / \
     *    a      ->     a   c
     *     \
     *      b
     */
    private SearchTreeNode<K, E> rightLeftRotate(SearchTreeNode<K, E> c){
        SearchTreeNode<K, E> b = leftRotate(c.getLeft()); //right rotate right subtree
        c.setLeft(b);
        return rightRotate(c);
    }



    public void insert(K key, E data){
        root = insert(root, new SearchTreeNode<>(key, data));
    }

    private SearchTreeNode<K, E> insert(SearchTreeNode<K, E> currentNode, SearchTreeNode<K, E> insertNode) {
        //perform insertion as normal before rotation
        if (currentNode == null) {
            insertNode.setHeight(0);
            return insertNode;
        }

        K key = insertNode.getKey();
        K currKey = currentNode.getKey();

        if (key.compareTo(currKey) < 0) {
            currentNode.setLeft(insert(currentNode.getLeft(), insertNode));
        } else if (key.compareTo(currKey) > 0) {
            currentNode.setRight(insert(currentNode.getRight(), insertNode));
        } else {
            //node exists with same key
            System.err.println("Key already exists in BST");
            return currentNode;
        }

        //now balance tree with rotations
        return balance(currentNode);
    }

    protected SearchTreeNode<K, E> balance(SearchTreeNode<K, E> currentNode){
        //find if subtrees are unbalanced
        int balance = findBalance(currentNode);

        if(balance > 1){
            if(findBalance(currentNode.getRight()) < -1){
                currentNode = leftRightRotate(currentNode);
            } else {
                currentNode = leftRotate(currentNode);
            }
        } else if(balance < -1){
            if(findBalance(currentNode.getLeft()) > 1){
                currentNode = rightLeftRotate(currentNode);
            } else {
                currentNode = rightRotate(currentNode);
            }
        }
        currentNode.setHeight(1 + max((currentNode.getLeft() == null) ? -1 : currentNode.getLeft().getHeight(), (currentNode.getRight() == null) ? -1 : currentNode.getRight().getHeight()));
        return currentNode;
    }

    public void remove(K key){
        root = remove(key, root);
    }

    public SearchTreeNode<K, E> remove(K toRemove, SearchTreeNode<K, E> node) {
        if(node == null) {
            //data to be removed is not in the tree
            throw new IllegalArgumentException();
        }

        if(toRemove.compareTo(node.getKey()) < 0) {
            node.setLeft(remove(toRemove, node.getLeft()));
        } else if(toRemove.compareTo(node.getKey()) > 0) {
            node.setRight(remove(toRemove, node.getRight()));
        } else if(node.getLeft() == null) {
            //at the node to delete with empty left subtree
            //just put its right node in its place
            node = node.getRight();
            return node;
        } else if(node.getRight() == null){
            //at node to delete with empty right subtree
            node = node.getLeft();
            return node;
        } else {
            //complex part: node has two subtrees
            SearchTreeNode<K, E> temp = findMinValue(node.getRight());
            node.setData(temp.getData());
            node.setKey(temp.getKey());
            node.setRight(removeMinValue(node.getRight()));
            return node;
        }
        return balance(node);
    }

    private SearchTreeNode<K, E> findMinValue(SearchTreeNode<K, E> node){
        if(node == null){
            return null;
        } else if(node.getLeft() == null) {
            return node;
        } else {
            return findMinValue(node.getLeft());
        }
    }

    private SearchTreeNode<K, E> removeMinValue(SearchTreeNode<K, E> node){
        if(node == null) {
            throw new IllegalArgumentException();
        } else if(node.getLeft() != null){
            node.setLeft(removeMinValue(node.getLeft()));
            return node;
        } else {
            return node.getRight();
        }
    }

    private int findBalance(SearchTreeNode<K, E> currentNode) {
        int leftHeight = (currentNode.getLeft() == null) ?  -1 : currentNode.getLeft().getHeight();
        int rightHeight = (currentNode.getRight() == null) ? -1 : currentNode.getRight().getHeight();

        return rightHeight - leftHeight;
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

    public void printTopN(int n) {
        for(int i = 0; i < n; i++) {
            SearchTreeNode<K, E> max = findMostCommon(root);
            System.out.println(max);
            remove(max.getKey());
        }
    }

    private SearchTreeNode<K, E> findMostCommon(SearchTreeNode<K, E> node) {
            if(node.getLeft() == null && node.getRight() == null) {
                return node;
            } else if(node.getLeft() == null) {
                SearchTreeNode<K, E> temp = findMostCommon(node.getRight());
                return ((int)(temp.getData()) > (int)(node.getData())) ? temp : node;
            } else if(node.getRight() == null) {
                SearchTreeNode<K, E> temp = findMostCommon(node.getLeft());
                return ((int)(temp.getData()) > (int)(node.getData())) ? temp : node;
            } else {
                SearchTreeNode<K, E> leftTemp = findMostCommon(node.getLeft());
                SearchTreeNode<K, E> rightTemp = findMostCommon(node.getRight());

                SearchTreeNode<K, E> max = ((int)(leftTemp.getData()) > (int)(rightTemp.getData())) ? leftTemp : rightTemp;
                return ((int)(max.getData()) > (int)(node.getData())) ? max : node;
            }
    }
}
