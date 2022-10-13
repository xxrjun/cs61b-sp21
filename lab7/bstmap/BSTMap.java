package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K> {

    private Node root;   // root of BSTMap

    /**
     * Initializes an empty BSTMap
     */
    public BSTMap() {

    }

    /**
     * Sets the root as null.
     */
    @Override
    public void clear() {
        root = null;
    }

    /**
     * Returns true if and only if this dictionary contains KEY as the
     * key of some key-value pair
     *
     * @param key the key
     * @return {@code true} if and only if this BSTMap contains KEY as
     * the key of some key-value pair, {@code false} otherwise.
     */
    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }

        Node cur = root;
        int cmp;
        while (cur != null) {
            cmp = key.compareTo(cur.key);
            if (cmp == 0) {
                return true;
            } else if (cmp > 0) {
                cur = cur.right;
            } else {
                cur = cur.left;
            }
        }

        return false;
    }

    /**
     * Returns the value associated with the given key.
     *
     * @param key the key
     * @return the value associated with the given key if the key is in the BSTMap.
     * and {@code null} if the key is not in the BSTMap.
     * @throws IllegalArgumentException if {@code key} if {@code null}.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node node, K key) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (node == null) return null;

        int cmp = key.compareTo((K) node.key);
        if (cmp == 0) {
            return node.val;
        } else if (cmp < 0) {
            return get(node.left, key);
        } else {
            return get(node.right, key);
        }
    }

    /**
     * @return the number of key-value pars in this BSTMap.
     */
    @Override
    public int size() {
        return size(root);
    }

    // return the number of key-value pairs in BSTMap rooted at specified node.
    private int size(Node node) {
        if (node == null) return 0;
        else return node.size;
    }

    /**
     * Inserts the specified key-value pair into the BSTMap, overwriting the old node value with
     * the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this BSTMap
     * if the specified value if {@code null}.
     *
     * @param key the key
     * @param val the value
     * @throws IllegalArgumentException if {@code key} is {@code null}.
     */
    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key.");
        root = put(root, key, val);

    }

    private Node put(Node node, K key, V val) {
        if (node == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(node.key);
        if (cmp == 0) {
            node.val = val;
        } else if (cmp > 0) {
            node.right = put(node.right, key, val);
        } else {
            node.left = put(node.left, key, val);
        }

        if (cmp != 0) {
            node.size = 1 + size(node.left) + size(node.right);
        }

        return node;

    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node node) {
        if (root == null) {
            return;
        }

        printInOrder(root.left);
        System.out.println(node.key.toString() + " -> " + node.val.toString());
        printInOrder(root.right);
    }

    /**
     * Remove key-value value by key.
     *
     * @param key the key
     * @return the value in key-value pair we removed.
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key.");
        }

        if (!containsKey(key)) {
            return null;
        }

        V deletedNodeVal = get(key);


        root = remove(root, key);


        return deletedNodeVal;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls remove() with a null key.");
        }

        if (!containsKey(key)) {
            return null;
        }

        V deletedNodeVal = get(key);

        root = remove(root, key);


        return deletedNodeVal;
    }

    public Node remove(Node node, K key) {
        if (node == null) return null;

        /* Find the key we want to delete and replace it with its successor. */
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = remove(node.left, key);
        } else if (cmp > 0) {
            node.right = remove(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }

            Node originRoot = node;
            node = min(node.right);
            node.right = removeMin(originRoot.right);
            node.left = originRoot.left;
        }

        node.size = size(node.left) + size(node.right) + 1;

        return node;

    }

    /**
     * @return the node with minimum key (inorder successor).
     */
    private Node min(Node node) {
        if(node.left == null) return node;

        return min(node.left);
    }

    /**
     *
     * @param node
     * @return the subtree without minimum key (inorder successor).
     */
    private Node removeMin(Node node){
        if(node.left == null)  return node.right;
        node.left = removeMin(node.left);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    /**
     *
     * @return {@code BSTMapKeySet}
     */
    @Override
    public Set<K> keySet() {
        Set<K> BSTMapKeySet = new HashSet<>();

        for (K key : this) {
            BSTMapKeySet.add(key);
        }

        return BSTMapKeySet;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class Node {
        private Node left, right;
        private final K key;
        private V val;

        private int size;

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    private class BSTMapIterator implements Iterator<K> {
        List<Node> list;

        public BSTMapIterator() {
            list = new LinkedList<>();
            list.add(root);
        }

        @Override
        public boolean hasNext() {
            return !list.isEmpty();
        }

        @Override
        public K next() {
            Node cur = list.remove(0);

            /* Do not add node into list if is null. */
            if (cur.left != null)
                list.add(cur.left);
            if (cur.right != null)
                list.add(cur.right);

            return cur.key;
        }
    }
}
