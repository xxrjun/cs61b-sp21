package deque;

public class ArrayDeque<T> {

    private T items[];
    private int size;

    /**
     * Constructor
     */
    public ArrayDeque() {
        items = (T[]) new Object[100];
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

        for (int i = size(); i > 0; i--) {
            items[i] = items[i - 1];
        }
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
     * @return whether the array deque is empty (size == 0)
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @return size: the length of items in array deque
     */
    public int size() {
        return size;
    }

    /**
     * Print whole deque.
     */
    public void printDeque() {
        if (!this.isEmpty()) {
            for (int i = 0; i < size - 1; i++) {
                System.out.print(items[i] + " ");
            }
            System.out.println(items[size - 1]);
        }
    }

    /**
     * Remove the first item of array deque.
     *
     * @return firstItem or null
     */
    public T removeFirst() {
        if (!this.isEmpty()) {
            T firstItem = items[0];

            for (int i = 0; i < size; i++) {
                items[i] = items[i + 1];
            }

            size -= 1;

            // If Ratio Usage < 0.25, half array deque length.
            if (size() < (items.length / 4)) {
                resize(size() / 2);
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
                resize(size() / 2);
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
}