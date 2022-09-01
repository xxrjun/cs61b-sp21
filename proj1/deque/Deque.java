package deque;

public interface Deque<T> {

    /**
     * Add item to the first position of array deque.
     *
     * @param item
     */
    void addFirst(T item);

    /**
     * Add item to the last position of array deque.
     *
     * @param item
     */
    void addLast(T item);

    /**
     * @return whether the array deque is empty (size == 0)
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }

    ;

    /**
     * @return size: the length of items in array deque
     */
    int size();


    /**
     * Remove the first item of array deque.
     *
     * @return firstItem or null
     */
    T removeFirst();

    /**
     * Remove the last item of array deque.
     *
     * @return lastItem: the last item should be removed.
     */
    T removeLast();

    /**
     * Get the item by index.
     *
     * @param index
     * @return item
     */
    T get(int index);

    /**
     * Print whole deque.
     */
    default void printDeque() {
        if (!this.isEmpty()) {
            for (int i = 0; i < size() - 1; i++) {
                System.out.print(get(i) + " ");
            }
            System.out.println(get(size() - 1));
        }
    }
}
