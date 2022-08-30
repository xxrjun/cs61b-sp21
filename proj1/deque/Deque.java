package deque;

public interface Deque<T> {

    /**
     * Add item to the first position of array deque.
     *
     * @param item
     */
    public void addFirst(T item);

    /**
     * Add item to the last position of array deque.
     *
     * @param item
     */
    public void addLast(T item);

    /**
     * @return whether the array deque is empty (size == 0)
     */
    public boolean isEmpty();

    /**
     * @return size: the length of items in array deque
     */
    public int size();


    /**
     * Remove the first item of array deque.
     *
     * @return firstItem or null
     */
    public T removeFirst();

    /**
     * Remove the last item of array deque.
     *
     * @return lastItem: the last item should be removed.
     */
    public T removeLast();

    /**
     * Get the item by index.
     *
     * @param index
     * @return item
     */
    public T get(int index);

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
