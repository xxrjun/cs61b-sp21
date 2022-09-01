package timingtest;

/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList<Item> {
	private class IntNode {
		public Item item;
		public IntNode next;
		public IntNode prev;

		public IntNode(IntNode p, Item i, IntNode n) {
			item = i;
			prev = p;
			next = n;
		}
	}

	/* The first item (if it exists) is at sentinel.next. */
	private IntNode sentinel;
	private int size;

	/** Creates an empty timingtest.SLList. */
	public SLList() {
		sentinel = new IntNode(null, null, null);
		size = 0;
	}

	public SLList(Item x) {
		sentinel = new IntNode(null, null, null);
		sentinel.next = new IntNode(sentinel, x, sentinel);
		size = 1;
	}

	/** Adds x to the front of the list. */
	public void addFirst(Item x) {
		if(this.isEmpty()){
			IntNode newNode = new IntNode(sentinel, x, sentinel);
			sentinel.next = newNode;
			sentinel.prev = newNode;
		} else {
			IntNode curFirst = sentinel.next;
			IntNode newNode = new IntNode(sentinel, x, sentinel.next);
			sentinel.next = newNode;
			curFirst.prev = newNode;
		}

		size += 1;
	}

	/** Returns the first item in the list. */
	public Item getFirst() {
		return sentinel.next.item;
	}

	/** Adds x to the end of the list. */
	public void addLast(Item x) {
		if(this.isEmpty()){
			IntNode newNode = new IntNode(sentinel, x, sentinel);
			sentinel.next = newNode;
			sentinel.prev = newNode;
		} else {
			IntNode curLast = sentinel.prev;
			IntNode newNode = new IntNode(sentinel.prev, x, sentinel);
			sentinel.prev = newNode;
			curLast.next = newNode;
		}

		size += 1;
	}

	/** returns last item in the list */
	public Item getLast() {
		return sentinel.prev.item;
	}


	/** Returns the size of the list. */
	public int size() {
		return size;
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public static void main(String[] args) {
		/* Creates a list of one integer, namely 10 */
		SLList L = new SLList();
		L.addLast(20);
		System.out.println(L.size());
	}
}
