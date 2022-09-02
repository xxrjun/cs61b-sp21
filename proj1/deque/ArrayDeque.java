package deque;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;

    /**
     * Constructor
     */
    public ArrayDeque() {
        items = (T[]) new Object[10];
        size = 0;
    }

    /**
     * Private helper function: used to resize the array deque.
     *
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newArrayDeque = (T[]) new Object[capacity];
        System.arraycopy(items, 0, newArrayDeque, 0, size);
        items = newArrayDeque;
    }

    /**
     * Add item to the first position of array deque.
     *
     * @param item
     */
    public void addFirst(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        T[] newItems = (T[]) new Object[items.length];
        System.arraycopy(items, 0, newItems, 1, size());
        items = newItems;
        items[0] = item;

        size += 1;
    }

    /**
     * Add item to the last position of array deque.
     *
     * @param item
     */
    public void addLast(T item) {
        if (size() == items.length) {
            resize(size() * 2);
        }

        items[size] = item;
        size += 1;
    }

    /**
     * @return size: the length of items in array deque
     */
    public int size() {
        return size;
    }

    /**
     * Remove the first item of array deque.
     *
     * @return firstItem or null
     */
    public T removeFirst() {
        if (!this.isEmpty()) {
            T firstItem = items[0];

            T[] newItems = (T[]) new Object[items.length];
            System.arraycopy(items, 1, newItems, 0, size() - 1);
            items = newItems;

            size -= 1;

            // If Ratio Usage < 0.25, half array deque length.
            if (size() < (items.length / 4)) {
                resize(items.length / 2);
            }

            return firstItem;
        }

        return null;
    }

    /**
     * Remove the last item of array deque.
     *
     * @return lastItem: the last item should be removed.
     */
    public T removeLast() {
        // Validation for empty array deque
        if (!this.isEmpty()) {
            T lastItem = items[size - 1];
            size -= 1;

            // If Ratio Usage < 0.25, half array deque length.
            if (size() < (items.length / 4)) {
                resize(items.length / 2);
            }

            return lastItem;
        }


        return null;
    }

    /**
     * Get the item by index.
     *
     * @param index
     * @return item
     */
    public T get(int index) {
        // Index validation
        if (index >= 0 && index < items.length) {
            return items[index];
        } else {
            return null;
        }
    }

    /**
     * Print whole deque.
     */
    public void printDeque() {
        if (!this.isEmpty()) {
            for (int i = 0; i < size() - 1; i++) {
                System.out.print(get(i) + " ");
            }
            System.out.println(get(size() - 1));
        }
    }

    /**
     * @return iterator
     */
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int seer;

        ArrayDequeIterator() {
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

    /**
     * @param o
     * @return whether two objects equal
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        Deque<T> other = null;
        if (o instanceof LinkedListDeque) {
            other = (LinkedListDeque<T>) o;
        } else if (o instanceof ArrayDeque) {
            other = (ArrayDeque<T>) o;
        }

        if (other == null) {
            return false;
        }

        if (this.size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }

        return true;
    }
}
