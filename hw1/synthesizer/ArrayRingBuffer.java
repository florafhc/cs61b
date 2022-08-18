
package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int nextPoint(int point) {
        int next = point + 1;
        if (next > capacity() - 1) {
            next = 0;
        }
        return next;
    }
    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];

    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */

    @Override
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            fillCount = fillCount + 1;
            last = nextPoint(last);

        }

    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        T remove;
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            remove = rb[first];
            fillCount = fillCount - 1;
            first = nextPoint(first);
            return remove;
        }

    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
    }

    @Override
    public Iterator<T> iterator() {
        return new RbIterator();
    }

    private class RbIterator implements Iterator<T> {
        private int point;
        private int flag = 0;
        private RbIterator() {
            point = first;
        }
        @Override
        public boolean hasNext() {
            return !(point == first && flag == 1);
        }
        public T next() {
            T next;
            next = rb[point];
            point = nextPoint(point);
            flag = 1;
            return next;

        }
    }

}
