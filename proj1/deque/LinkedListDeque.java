package deque;

import javax.sound.sampled.Line;

public class LinkedListDeque<T> {

    /**
     * @param <T>
     */
    private static class LinkedNode<T> {
        public LinkedNode prev;
        public T item;
        public LinkedNode next;

        public LinkedNode(LinkedNode prev, T item, LinkedNode next) {
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
        if(this.isEmpty()){
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
     *
     * @param item
     */
    public void addLast(T item) {
        if(this.isEmpty()){
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
     *  Remove first node of deque
     *
     * @return deleted_item
     */
    public T removeFirst(){
        if(this.isEmpty()){
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

    public T removeLast(){
        if(this.isEmpty()){
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
     * @return whether the deque is empty.
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * @return size: the size fo deque.
     */
    public int size() {
        return size;
    }

    /**
     *  Prints whole deque by iterating
     */
    public void printDeque() {
        if(!this.isEmpty()){
            LinkedNode<T> cur = sentinel.next;

            for(int i = 0 ; i < size - 1; i++){
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
    public T get(int index) {
        if(index >= size || this.isEmpty()) {
            return null;
        } else {
            LinkedNode<T> cur = sentinel.next;

            for(int i = 0; i < index; i++){
                cur = cur.next;
            }

            return cur.item;
        }
    }


}
