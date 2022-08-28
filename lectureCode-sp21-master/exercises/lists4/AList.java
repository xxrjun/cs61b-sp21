/**
 * Array based list.
 *
 * @author Josh Hug
 */

public class AList<Item> {

    Item[] items;
    int size;

    /**
     * Creates an empty list.
     */
    public AList() {
        items = (Item[]) new Object[100];
        size = 0;
    }

    /**
     * Inserts X into the back of the list.
     */
    public void addLast(Item x) {
        if (size == items.length) {
            resize(size + size / 4);
        }

        items[size] = x;
        size += 1;
    }

    /**
     * Resizes the items array
     */
    private void resize(int capacity) {
        Item[] new_array = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, new_array, 0, size);
        items = new_array;
    }

    /**
     * Returns the item from the back of the list.
     */
    public Item getLast() {
        return items[size - 1];
    }

    /**
     * Gets the ith item in the list (0 is the front).
     */
    public Item get(int i) {
        return items[i];
    }

    /**
     * Returns the number of items in the list.
     */
    public int size() {
        return size;
    }

    /**
     * Deletes item from back of the list and
     * returns deleted item.
     */
    public Item removeLast() {
        Item deleted_item = getLast();

        // No Loitering!
        items[size] = null;
        size -= 1;

        // if usage ratio R is less than 0.4, just half array size.
        if (size < (items.length / 4)){
            resize(items.length / 2);
        }

        return deleted_item;
    }
} 