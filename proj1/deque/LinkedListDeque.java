package deque;

public class LinkedListDeque<T> implements Deque<T> {

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
    @Override
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
    @Override
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
    @Override
    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }

        LinkedNode<T> curFirst = sentinel.next;
        sentinel.next = curFirst.next;
        curFirst.next.prev = sentinel;

        // Get the deleted item and null out deleted item.
        T deleted_item = curFirst.item;
        curFirst = null;

        size -= 1;

        return deleted_item;
    }

    @Override
    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }

        LinkedNode<T> curLast = sentinel.prev;
        sentinel.prev = curLast.prev;
        curLast.prev.next = sentinel;

        // Get the deleted item and null out deleted item.
        T deleted_item = curLast.item;
        curLast = null;

        return deleted_item;
    }

    /**
     * @return size: the size fo deque.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Prints whole deque by iterating
     */
    @Override
    public void printDeque() {
        if (!this.isEmpty()) {
            LinkedNode<T> cur = sentinel.next;

            for (int i = 0; i < size - 1; i++) {
                System.out.print(cur.item + " ");
                cur = cur.next;
            }
            System.out.println(cur.item);
        }
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
}
