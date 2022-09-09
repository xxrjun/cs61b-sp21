package bstmap;

import edu.princeton.cs.algs4.BST;

import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private BSTNode root;   // root of BSTMap

    /**
     * Initializes an empty BSTMap
     */
    public BSTMap() {

    }

    /**
     * Returns true is this BSTMap is empty.
     *
     * @return {@code true} if this BSTMap is empty; {@code false} otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Sets the root as null.
     */
    @Override
    public void clear() {
        root = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (root == null) {
            return false;
        }

        BSTNode cur = root;
        int cmp;
        while(cur != null){
            cmp = key.compareTo(cur.key);
            if(cmp == 0){
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

    private V get(BSTNode node, K key) {
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
    private int size(BSTNode node) {
        if (node == null) return 0;
        else return node.size;
    }

    /**
     * Inserts the specified key-value pair into the BSTMap, overwriting the old node value with
     * the new value if the symbol table already contains the specified key.
     * Deletes the specified key (and its associated value) from this BSTMap
     * if the specified value if {@code null}.
     *
     * @param key
     * @param val
     * @throws IllegalArgumentException if {@code key} is {@code null}.
     */
    @Override
    public void put(K key, V val) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key.");
        root = put(root, key, val);

    }

    private BSTNode put(BSTNode node, K key, V val) {
        if (node == null) {
            return new BSTNode(key, val, 1);
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
        throw new UnsupportedOperationException();
    }

    private class BSTNode {
        private BSTNode left, right;
        private final K key;
        private V val;
        private int size;

        public BSTNode(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }


    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();

    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
