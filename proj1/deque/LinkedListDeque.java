package deque;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {

    /**
     * @param <T>
     */
    private static class LinkedNode<T> {
        private LinkedNode prev;
        private T item;
        private LinkedNode next;

        LinkedNode(LinkedNode prev, T item, LinkedNode next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

    }

    private LinkedNode<T> sentinel;
    private int size;

    /**
     * Creating a new Linked List Deque
     */
    public LinkedListDeque() {
        sentinel = new LinkedNode(null, null, null);
        size = 0;
    }

    /**
     * @param item: an item of type T.
     */
    public void addFirst(T item) {
        if (this.isEmpty()) {
            LinkedNode<T> newNode = new LinkedNode<>(sentinel, item, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            LinkedNode<T> curFirst = sentinel.next;
            LinkedNode<T> newNode = new LinkedNode<>(sentinel, item, sentinel.next);
            sentinel.next = newNode;
            curFirst.prev = newNode;
        }

        size += 1;
    }

    /**
     * @param item
     */
    public void addLast(T item) {
        if (this.isEmpty()) {
            LinkedNode<T> newNode = new LinkedNode<>(sentinel, item, sentinel);
            sentinel.next = newNode;
            sentinel.prev = newNode;
        } else {
            LinkedNode<T> curLast = sentinel.prev;
            LinkedNode<T> newNode = new LinkedNode<>(sentinel.prev, item, sentinel);
            sentinel.prev = newNode;
            curLast.next = newNode;
        }

        size += 1;
    }

    /**
     * Remove first node of deque
     *
     * @return deleted_item
     */
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        LinkedNode<T> curFirst = sentinel.next;
        sentinel.next = curFirst.next;
        curFirst.next.prev = sentinel;

        // Get the deleted item and null out deleted item.
        T deletedItem = curFirst.item;
        curFirst = null;

        size -= 1;

        return deletedItem;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        LinkedNode<T> curLast = sentinel.prev;
        sentinel.prev = curLast.prev;
        curLast.prev.next = sentinel;

        // Get the deleted item and null out deleted item.
        T deletedItem = curLast.item;
        curLast = null;

        return deletedItem;
    }

    /**
     * @return size: the size fo deque.
     */
    public int size() {
        return size;
    }

    /**
     * Gets the item by index
     *
     * @param index
     * @return item
     */
    @Override
    public T get(int index) {
        if (index >= size || this.isEmpty()) {
            return null;
        } else {
            LinkedNode<T> cur = sentinel.next;

            for (int i = 0; i < index; i++) {
                cur = cur.next;
            }

            return cur.item;
        }
    }

    public T getRecursive(int index) {
        if (isEmpty()) {
            return null;
        }

        LinkedNode<T> head = sentinel.next;

        T item = getRecursive(head, index).item;

        return item;
    }

    private LinkedNode<T> getRecursive(LinkedNode<T> cur, int index) {
        if (index == 0) {
            return cur;
        } else {
            return getRecursive(cur.next, index - 1);
        }
    }


    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int seer;

        LinkedListDequeIterator() {
            seer = 0;
        }

        @Override
        public boolean hasNext() {
            return seer < size();
        }

        @Override
        public T next() {
            return get(seer++);
        }
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        LinkedListDeque<T> other = (LinkedListDeque<T>) o;
        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (this.get(i) != other.get(i)) {
                return false;
            }
        }

        return true;
    }

}
