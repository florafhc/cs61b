// TODO: Make sure to make this class a part of the synthesizer package
package synthesizer;

import java.util.Iterator;
//TODO: Make sure to make this class and all of its methods public
//TODO: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    private int nextPoint(int point) {
        int next = point + 1;
        if (point > capacity - 1) {
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
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
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
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
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

        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update 
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        return rb[first];
        // TODO: Return the first item. None of your instance variables should change.
    }

    @Override
    public Iterator<T> iterator() {
        return new rbIterator();
    }

    private class rbIterator implements Iterator<T> {
        private int point;
        private rbIterator() {
            point = first;
        }
        @Override
        public boolean hasNext() {
            return !(nextPoint(point) == first);
        }
        public T next() {
            T next;
            next = rb[point];
            point = nextPoint(point);
            return next;

        }
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.
}
