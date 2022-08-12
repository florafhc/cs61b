public class ArrayDeque<T>{
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T[]) new Object[8];
        nextFirst = 0;
        nextLast = 1;
        size = 0;
    }

    /** return the previous/next index in the circular array */
    private int minusOne(int index){
        index = index - 1;
        if (index < 0){
            index = items.length - 1;
        }
        return index;
    }
    private int plusOne(int index){
        index = index + 1;
        if (index >= items.length){
            index = 0;
        }
        return index;
    }

    /** Resize array */
    private void resize(int tar_length){
        T[] a = (T[]) new Object[tar_length];
        for (int i = 0; i < size; i += 1) {
            int start = plusOne(nextFirst);
            a[i] = items[start];
            start = plusOne(start);
        }
        items = a;
        nextFirst = a.length - 1;
        nextLast = size;
    }

    /** Add an item of type T to the front of the deque */
    public void addFirst(T x){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = minusOne(nextFirst);
        size = size + 1;

    }

    /** Add an item of type T to the back of the deque */
    public void addLast(T x){
        if(size == items.length){
            resize(size * 2);
        }
        items[nextLast] = x;
        size = size + 1;
        nextLast = plusOne(nextLast);


    }


    /** Return true if deque is empty, false otherwise */
    public boolean isEmpty(){
        if(size == 0){
            return true;
        }else{
            return false;
        }
    }

    /** Return the number of items in the deque */
    public int size(){
        return size;
    }

    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque(){
        int point = plusOne(nextFirst);
        for (int i = 0; i < size; i += 1){
            System.out.print(items[point] + " ");
            point = plusOne(point);
        }
    }

    /** Remove and return the item at the front of the deque.
     * If no such item exists, return null. */
    public T removeFirst(){
        if (isEmpty()){
            return null;
        }else{
            T remove;
            remove = items[plusOne(nextFirst)];
            nextFirst = plusOne(nextFirst);
            size = size - 1;
            double ratio = size / items.length;
            if (ratio < 0.25 && items.length >= 16){
                resize(items.length / 2);
            }
            return remove;
        }

    }

    /** Remove and return the item at the last of the deque.
     * If no such item exists, return null. */
    public T removeLast(){
        if (isEmpty()){
            return null;
        }else{
            T remove;
            remove = items[minusOne(nextLast)];
            nextLast = minusOne(nextLast);
            size = size - 1;
            double ratio = size / items.length;
            if (ratio < 0.25 && items.length >= 16){
                resize(items.length / 2);
            }
            return remove;
        }

    }

    /** Get the item at the given index, where 0 is the front, 1 is the next.
     * If no such item exists, returns null.*/
    public T get(int index){
        if (index > size - 1){
            return null;
        }else {
            int point = plusOne(nextFirst);
            for (int i = 0; i < index; i += 1){
                point = plusOne(point);
            }
            return items[point];
        }

    }


    public static void main(String[] args){
        ArrayDeque L = new ArrayDeque();
        for (int i = 0; i < 8; i++){
            L.addLast(i);
        }
        // L.removeFirst();
        // L.removeLast();
        System.out.println(L.get(3));
        L.printDeque();
        L.resize(16);
        L.resize(8);



    }


}
