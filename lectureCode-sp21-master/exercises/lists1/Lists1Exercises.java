public class Lists1Exercises {
    /** Returns an IntList identical to L, but with
      * each element incremented by x. L is not allowed
      * to change. */
    public static IntList incrList(IntList L, int x) {
        /* Your code here. */
        IntList cur = L;

        while(cur != null){
            cur.first += x;
            cur = cur.rest;
        }

        return L;        
    }

    /** Returns an IntList identical to L, but with
      * each element incremented by x. Not allowed to use
      * the 'new' keyword. */
    public static IntList dincrList(IntList L, int x) {
        /* Your code here. */
        IntList cur = L;

        while(cur != null){
            cur.first -= x;
            cur = cur.rest;
        }

        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(5, null);
        L.rest = new IntList(7, null);
        L.rest.rest = new IntList(9, null);

        System.out.println("List size (recursion): " + L.size());
        System.out.println("List size (iterative): " +L.iterativeSize());

        // Test your answers by uncommenting. Or copy and paste the
        // code for incrList and dincrList into IntList.java and
        // run it in the visualizer.
         System.out.println("Get value in index 1: " + L.get(1));

         System.out.print("Origin List: ");
         L.printList();

         incrList(L, 3);
         System.out.print("After increment: ");
         L.printList();

         dincrList(L, 3);
         System.out.print("After decrement: ");
         L.printList();
    }
}