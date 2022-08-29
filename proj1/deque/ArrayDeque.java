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

    public void addFirst(T item) {
        
    }

    public void addLast(T item) {

    }

    /**
     * @return whether the array deque is empty (size == 0)
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * @return size: the length of items in array deque
     */
    public int size() {
        return size;
    }

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
