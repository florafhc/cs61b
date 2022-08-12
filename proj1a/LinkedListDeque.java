public class LinkedListDeque<T> {
    private class Node {
        private T item;
        private Node next;
        private Node prev;
        public Node(T i, Node n, Node p) {
            item = i;
            next = n;
            prev = p;
        }
    }
    private Node sentinel;
    private int size;

    /**  Create LinkedListDeque */
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        size = 0;
    }

    /** Add an item of type T to the front of the deque */
    public void addFirst(T x) {
        if (isEmpty()) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.next = new Node(x, sentinel.next, sentinel);
            sentinel.next.next.prev = sentinel.next;
        }
        size = size + 1;
    }

    /** Add an item of type T to the back of the deque */
    public void addLast(T x) {
        if (isEmpty()) {
            sentinel.next = new Node(x, sentinel, sentinel);
            sentinel.prev = sentinel.next;
        } else {
            sentinel.prev.next = new Node(x, sentinel, sentinel.prev);
            sentinel.prev = sentinel.prev.next;
        }
        size = size + 1;
    }

    /** Return true if deque is empty, false otherwise. */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** return the number of items in the deque */
    public int size() {
        return size;
    }

    /** Print the items in the deque from first to last, separated by a space */
    public void printDeque() {
        Node p = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(p.item + " ");
            p = p.next;
        }
    }

    /** Remove and return the item at the first of the deque.
     * If no such item exists, return null. */
    public T removeFirst() {
        T remove;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            remove = sentinel.next.item;
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size = size - 1;
            return remove;
        } else {
            remove = sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size = size - 1;
            return remove;
        }
    }
    /** Remove and return the item at the back of the deque.
     * If no such item exists, return null. */
    public T removeLast() {
        T remove;
        if (size == 0) {
            return null;
        } else if (size == 1) {
            remove = sentinel.next.item;
            sentinel.next = sentinel;
            sentinel.prev = sentinel;
            size = size - 1;
            return remove;
        } else {
            remove = sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size = size - 1;
            return remove;
        }

    }

    /** Get the item at the given index.
     * 0 is the front, 1 is the next item.
     * If no such item exists, return null.*/
    public T get(int index) {
        if (index > size - 1) {
            return null;
        } else {
            int i = 0;
            T get;
            Node p = sentinel.next;
            while (i < index) {
                p = p.next;
                i = i + 1;
            }
            get = p.item;
            return get;
        }
    }

    public T getRecursive(int index) {
        return getRecursiveHelp(sentinel.next, index);
    }
    private T getRecursiveHelp(Node first, int index) {
        if (first.next == null && index > 0) {
            return null;
        } else if (index == 0) {
            return first.item;
        } else {
            index = index - 1;
            return (getRecursiveHelp(first.next, index));
        }
    }

}
