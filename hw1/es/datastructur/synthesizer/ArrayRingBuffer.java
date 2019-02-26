package es.datastructur.synthesizer;

import java.util.Iterator;


public class ArrayRingBuffer<T> implements BoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        first = 0;
        last = 0;
        fillCount = 0;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    public int capacity() {
        return rb.length;
    }

    public int fillCount() {
        return fillCount;
    }

    public void enqueue(T x) {
        if (capacity() == fillCount()) {
            throw new RuntimeException("Ring Buffer overflow");
        }
        rb[last] = x;
        last = (last + 1) % rb.length;
        fillCount++;
        return;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T dequeue() {
        if (fillCount() == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T holder = rb[first];
        rb[first] = null;
        first = (first + 1) % rb.length;
        fillCount--;
        return holder;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    public T peek() {
        if (fillCount() == 0) {
            throw new RuntimeException("Ring Buffer underflow");
        }
        T holder = rb[first];
        return holder;
    }

    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int start;

        public ArrayRingBufferIterator() {
            start = first;
        }

        public boolean hasNext() {
            return start != last;
        }

        public T next() {
            T hold = peek();
            start = (start + 1) % rb.length;
            return hold;
        }
    }

    @Override
    public boolean equals(Object o) {
        ArrayRingBuffer other = (ArrayRingBuffer) o;
        if (other.fillCount() != fillCount()) {
            return false;
        }
        for (int i = first; i < other.capacity(); i = (i + 1) % other.fillCount()) {
            if (rb[i] != other.rb[i]) {
                return false;
            }
        }
        return true;
    }
}
