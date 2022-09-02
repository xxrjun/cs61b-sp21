import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ArraySet<T> implements Iterable<T> {

    private T[] items;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    private class ArraySetIterator implements Iterator<T> {
        private int seer;

        public ArraySetIterator() {
            seer = 0;
        }

        @Override
        public boolean hasNext() {
            return seer < size;
        }

        @Override
        public T next() {
            return items[seer++];
        }
    }

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    /* Returns true if this map contains a mapping for the specified key.
     */
    public boolean contains(T x) {
        for (int i = 0; i < size(); i++) {
            // for null case
            if (items[i] == null) {
                if (x == null) {
                    return true;
                }
            } else if (items[i].equals(x)) {
                return true;
            }
        }
        return false;
    }

    /* Associates the specified value with the specified key in this map. 
       Throws an IllegalArgumentException if the key is null. */
    public void add(T x) {
        if (!contains(x)) {
            items[size] = x;
            size += 1;
        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        List<String> listOfItems = new ArrayList<>();
        for (T item : this) {
            listOfItems.add(item.toString());
        }

        return "{" + String.join(", ", listOfItems) + "}";

    }

    @Override
    public boolean equals(Object other) {
        /* Actually same object. */
        if (this == other) {
            return true;
        }

        /* Other object cannot be null, cuz this must be not null. */
        if (other == null) {
            return false;
        }

        /* Two object must be same type if they equal. */
        if (this.getClass() != other.getClass()) {
            return false;
        }

        ArraySet<T> o = (ArraySet<T>) other;
        /* Two object must have same size if they equal */
        if (this.size != o.size()) {
            return false;
        }

        /* Compare element */
        for (T item : o) {
            if (!this.contains(item)) {
                return false;
            }
        }

        return true;


    }

    /* OLD TOSTRING METHOD
    @Override
    public String toString() {
        StringBuilder returnSB = new StringBuilder("{");
        for (int i = 0; i < size - 1; i++) {
            returnSB.append(items[i].toString();
            returnSB.append(", ");
        }
        returnSB.append(items[size - 1]);
        returnSB.append("}");

        return returnSB.toString();
    } */

    public static void main(String[] args) {
        ArraySet<Integer> as1 = new ArraySet<>();
        as1.add(10);
        as1.add(20);
        as1.add(5);
        as1.add(30);

        ArraySet<Integer> as2 = new ArraySet<>();
        as2.add(10);
        as2.add(20);
        as2.add(5);
        as2.add(30);

        System.out.println("as1: " + as1);
        System.out.println("as2: " + as2);
        System.out.println("as1 == as2: " + (as1 == as2));
        System.out.println("as1.equals(as2): " + as1.equals(as2));

        /* Ugly Way when iterate
        Iterator<Integer> seer2 = as2.iterator();
        while (seer2.hasNext()) {
            int i = seer2.next();
            System.out.println(i);
        } */

        /* Better way: forEach
        for (int n : as2) {
            System.out.println(n);
        } */

    }

    /* Also to do:
    1. Make ArraySet implement the Iterable<T> interface.
    2. Implement a toString method.
    3. Implement an equals() method.
    */
}
