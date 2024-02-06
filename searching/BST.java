package searching;

import java.util.NoSuchElementException;

import fundamentals.Queue;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root; // root of BST

    private class Node {
        private Key key; // sorted by key
        private Value val; // associated data
        private Node left, right; // left and right subtrees
        private int size; // number of nodes in subtree rooted here

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BST() {
    }

    /**************************************************
     * check if key exist in the symbol table
     **************************************************/
    public boolean contains(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        return get(key) != null;
    }

    /****************************************************
     * Get value associated with key
     ****************************************************/
    public Value get(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        return get(root, key);
    }

 /*
      Logic :
        1. if key is less than root, search in left subtree
        2. if key is greater than root, search in right subtree
        3. if key is equal to root, return value
 */   
    private Value get(Node x, Key key) {
        if (x == null)
            return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return get(x.left, key);
        else if (cmp > 0)
            return get(x.right, key);
        else
            return x.val; // bingo! key found
    }

    /****************************************************************
     * Add Node to symbol table :
     *  1. if key is less than root, add to left subtree
     * 2. if key is greater than root, add to right subtree
     * 3. if key is equal to root, update value
     ***************************************************************/
    public void put(Key key, Value val) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null)
            return new Node(key, val, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = put(x.left, key, val);
        else if (cmp > 0)
            x.right = put(x.right, key, val);
        else
            x.val = val;
        x.size = size(x.left) + size(x.right);
        return x;
    }

    /****************************************************************
     * Delete Node from BST:
     * 1. if key is less than root, delete from left subtree
     * 2. if key is greater than root, delete from right subtree
     * 3. if key is equal to root, delete the node
     *      a. if node has only one child, return the child
     *      b. if node has two children, replace with successor
     *      i. find the successor (min node in right subtree)
     *      ii. delete the successor from right subtree
     *     iii. replace the node with successor
     * 4. update size of the node
     ***************************************************************/

    public void deleteMin() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        root = deleteMin(root);
    }

    private Node deleteMin(Node x) {
        if (x.left == null)
            return x.right;
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null)
            return x.left;
        x.right = deleteMax(x.right);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            x.left = delete(x.left, key);
        else if (cmp > 0)
            x.right = delete(x.right, key);
        else {
            if (x.left == null)
                return x.right;
            if (x.right == null)
                return x.left;
            else {
                Node t = x;
                x = min(t.right);
                x.right = deleteMin(t.right);
                x.left = t.left;
            }
        }
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    /****************************************************************************
     * get min and max key in the symbol table
     * 1. min : leftmost node in the BST
     * 2. max : rightmost node in the BST
     *
     ****************************************************************************/

    public Key min() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null)
            return x;
        return min(x.left);
    }

    public Key max() {
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");
        return max(root).key;
    }

    private Node max(Node x) {
        if (x.right == null)
            return x;
        return max(x.right);
    }

    /*******************************************************************************
     * floor of key :  largest key that is less than or equal to key
     *                1. if key is less than root, floor is in left subtree
     *                2. if key is greater than root, floor is in right subtree
     *                3. if key is equal to root, floor is root
     *******************************************************************************/
    public Key floor(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");

        Node x = floor(root, key);
        if (x == null)
            throw new NoSuchElementException("argument is too small, all elements are bigger");
        else
            return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        else if (cmp < 0)
            return floor(x.left, key);
        else {
            Node t = floor(x.right, key); // look for more closest key
            if (t != null)
                return t;
            else
                return x;
        }
    }

    public Key floor2(Key key) {
        Key x = floor2(root, key, null);
        if (x == null)
            throw new IllegalArgumentException("key is too small");
        return x;
    }

    private Key floor2(Node x, Key key, Key best) {
        if (x == null)
            return best;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return floor2(x.left, key, best);
        else if (cmp > 0)
            return floor2(x.right, key, x.key);
        else
            return x.key;
    }
/*
 *  ceiling : smallest key that is greater than or equal to key
 * 1. if key is less than root, ceiling is in right subtree
 * 2. if key is greater than root, ceiling is in right subtree
 * 3. if key is equal to root, ceiling is root
 */
    public Key ceiling(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        if (isEmpty())
            throw new NoSuchElementException("symbol table is empty");

        Node x = ceiling(root, key);
        if (x == null)
            throw new NoSuchElementException("key is too big");
        return x.key;
    }

    private Node ceiling(Node x, Key key) {
        if (x == null)
            return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0)
            return x;
        else if (cmp > 0)
            return ceiling(x.right, key);
        else {
            Node t = ceiling(x.left, key);
            if (t != null)
                return t;
            else
                return x;
        }
    }

    /*********************************************************************
     * select : return key of rank k (i.e. key which has k keys less than it)
     * i.e. node which has 'rank' no of left children
     * 1. if leftSize is greater than rank, select in left subtree
     * 2. if leftSize is less than rank, select in right subtree
     * 3. if leftSize is equal to rank, return the key
     *********************************************************************/
    public Key select(int rank) {
        if (rank < 0 || rank >= size())
            throw new IllegalArgumentException("rank is invalid");
        return select(root, rank);
    }

    private Key select(Node x, int rank) {
        if (x == null)
            return null;
        int leftSize = size(x.left);
        if (leftSize > rank)
            return select(x.left, rank);
        else if (leftSize < rank)
            return select(x.right, rank);
        else
            return x.key;
    }
/*
 * rank : number of keys less than key
 * 1. if key is less than root, rank is in left subtree
 * 2. if key is greater than root, rank is in right subtree
 * 3. if key is equal to root, rank is size of left subtree
 * 4. if key is not found, return size of left subtree
 * 5. if key is found, return size of left subtree
 */
    public int rank(Key key) {
        if (key == null)
            throw new IllegalArgumentException("key is null");
        return rank(root, key);
    }

    private int rank(Node x, Key key) {
        if (x == null)
            return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0)
            return rank(x.left, key);
        else if (cmp > 0)
            return rank(x.right, key) + size(x.left) + 1;
        else
            return size(x.left);
    }

    public Iterable<Key> keys() {
        if (isEmpty())
            return new Queue<Key>();
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("lo range is null");
        if (hi == null)
            throw new IllegalArgumentException("hi range is null");

        Queue<Key> queue = new Queue<>();
        keys(root, queue, lo, hi);
        return queue;
    }

    /*
     *  keys in the range [lo, hi]
     */
    private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
        if (x == null)
            return;
        int cmplo = lo.compareTo(x.key); 
        int cmphi = hi.compareTo(x.key);

        if (cmplo < 0) // if lo is less than root, keys in left subtree
            keys(x.left, queue, lo, hi);
        if (cmplo <= 0 && cmphi >= 0) // if lo is less than or equal to root and hi is greater than or equal to root, add root to queue
            queue.enqueue(x.key);
        if (cmphi > 0) // if hi is greater than root, keys in right subtree
            keys(x.right, queue, lo, hi);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        else
            return x.size;
    }

    /***********************************************************************
     * check BST integrity
     ************************************************************************/
    private boolean isBST(Node root) {
        if (root == null)
            return true;
        return isBST(root, null, null); 
    }

    private boolean isBST(Node x, Key min, Key max) {
        if (x == null)
            return true;
        if (min != null && x.key.compareTo(min) <= 0) // if min is not null and key is less than min, return false
            return false;
        if (max != null && x.key.compareTo(max) <= 0) // if max is not null and key is greater than max,
            return false;
        return isBST(x.left, min, x.key) && isBST(x.right, x.key, max); // check left and right subtree for BST
    }

    private int height() {
        return height(root);
    }

    private int height(Node x) {
        if (x == null)
            return -1; // height of empty tree is -1
        return Math.max(height(x.left), height(x.right)) + 1; // height of tree = max of left and right subtree + 1
    }

    public int size(Key lo, Key hi) {
        if (lo == null)
            throw new IllegalArgumentException("lo range is invalid");
        if (hi == null)
            throw new IllegalArgumentException("hi range is invalid");

        if (lo.compareTo(hi) > 0) // if lo is greater than hi, return 0
            return 0;
        if (contains(hi))
            return rank(hi) - rank(lo) + 1; // if hi is in the tree, size = rank of hi(keys less than hi) - rank of lo(keys less than lo) + 1
        else
            return rank(hi) - rank(lo); // if hi is not in the tree, size = rank of hi(keys less than hi) - rank of lo(keys less than lo)
    }

    public Iterable<Key> levelOrder() {
        Queue<Key> keys = new Queue<>();
        Queue<Node> queue = new Queue<>();
        queue.enqueue(root);
        while (!queue.isEmpty()) {
            Node x = queue.dequeue();
            if (x == null)
                continue;
            keys.enqueue(x.key);

            queue.enqueue(x.left);
            queue.enqueue(x.right);
        }
        return keys;
    }

    public static void main(String[] args) {
        BST<String, Integer> st = new BST<>();
        st.put("Google", 1);
        st.put("Openai", 2);

        int key = st.get("Google");
        System.out.println(key);
    }

}
