package deque;

import java.util.Iterator;

@SuppressWarnings("unchecked")
public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    /**
     * Constructor
     */
    public ArrayDeque() {
        items = (T[]) new Object[10];
        nextFirst = 3;
        nextLast = 4;
        size = 0;
    }

    /**
     * @return nextFirst
     */
    public int getNextFirst() {
        return nextFirst;
    }

    /**
     * @return nextLast
     */
    public int getNextLast() {
        return nextLast;
    }

    /**
     * Private helper function: used to resize the array deque.
     *
     * @param capacity
     */
    private void resize(int capacity) {
        T[] newArrayDeque = (T[]) new Object[capacity];
        int newNextFirst = (int) Math.round(capacity * 0.25);
        System.arraycopy(items, nextFirst + 1, newArrayDeque, newNextFirst + 1, size);

        items = newArrayDeque;
        nextFirst = newNextFirst;
        nextLast = nextFirst + size + 1;
    }


    private void resizeDealer() {
        if (nextFirst == -1) {
            // reach front, resize array deque length to 2x
            resize(items.length * 2);
        } else if (nextLast == items.length) {
            // reach end, resize array deque length to 2x
            resize(items.length * 2);
        } else if (size() > 10 && size() < (items.length / 4)) {
            // If Ratio Usage < 0.25 and size is larger than 10, half array deque length.
            resize(items.length / 2);
        }
    }


    /**
     * Add item to the first position of array deque.
     *
     * @param item
     */
    public void addFirst(T item) {
        items[nextFirst] = item;

        size += 1;
        nextFirst -= 1;

        resizeDealer();
    }

    /**
     * Add item to the last position of array deque.
     *
     * @param item
     */
    public void addLast(T item) {
        items[nextLast] = item;

        size += 1;
        nextLast += 1;

        resizeDealer();

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
            T firstItem = items[nextFirst + 1];


            size -= 1;
            nextFirst += 1;

            resizeDealer();

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
            T lastItem = items[nextLast - 1];

            size -= 1;
            nextLast -= 1;

            resizeDealer();

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
        if (index > nextFirst && index < nextLast) {
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
            for (int i = nextFirst + 1; i < nextLast; i++) {
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

        if (o instanceof LinkedListDeque) {
            for (int i = nextFirst + 1, j = 0; i < nextLast && j < other.size(); i++, j++) {
                if (!(this.get(i) == other.get(j))) {
                    return false;
                }
            }

        } else if (o instanceof ArrayDeque) {
            for (int i = 0; i < size(); i++) {
                if (!(this.get(i) == other.get(i))) {
                    return false;
                }
            }
        }


        return true;
    }
}
