package dailymixes;

import queue.EmptyQueueException;
import queue.QueueInterface;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * A generic array-based implementation of a queue.
 *
 * @param <T>
 *            The data type for the queue elements.
 * 
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class ArrayQueue<T> implements QueueInterface<T> {
    /**
     * The default capacity for the ArrayQueue.
     * This constant specifies the initial capacity of the ArrayQueue when
     * created without a capacity parameter.
     */
    static final int DEFAULT_CAPACITY = 20;
    private T[] queue;
    private int dequeueIndex;
    private int size;
    private int enqueueIndex;

    /**
     * Constructs a new queue with the given capacity.
     *
     * @param desiredCapacity
     *            The desired capacity of the queue.
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int desiredCapacity) {
        queue = (T[])new Object[desiredCapacity + 1];
        enqueueIndex = desiredCapacity;
        dequeueIndex = 0;
        size = 0;
    }


    /**
     * Constructs a new queue with the default capacity.
     */
    public ArrayQueue() {
        this(ArrayQueue.DEFAULT_CAPACITY);
    }


    /**
     * Clears the queue and resets its parameters to their initial state.
     */
    @SuppressWarnings("unchecked")
    public void clear() {
        queue = (T[])new Object[ArrayQueue.DEFAULT_CAPACITY + 1];
        enqueueIndex = ArrayQueue.DEFAULT_CAPACITY;
        dequeueIndex = 0;
        size = 0;
    }


    /**
     * Removes and returns the front item from the queue.
     *
     * @return The removed item.
     */
    public T dequeue() {
        T front = getFront();
        queue[dequeueIndex] = null;
        dequeueIndex = (dequeueIndex + 1) % queue.length;
        size--;
        return front;
    }


    /**
     * Adds an item to the back of the queue.
     *
     * @param item
     *            The item to be added.
     */
    @Override
    public void enqueue(T item) {
        ensureCapacity();
        enqueueIndex = (enqueueIndex + 1) % queue.length;
        queue[enqueueIndex] = item;
        size++;
    }


    /**
     * Retrieves the front item in the queue.
     *
     * @return The first item in the queue.
     */
    @Override
    public T getFront() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        return queue[dequeueIndex];
    }


    /**
     * Checks if the queue is empty.
     *
     * @return True if the queue is empty, otherwise false.
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }


    /**
     * Returns the number of items in the queue.
     *
     * @return The size of the queue.
     */
    public int getSize() {
        return size;
    }


    /**
     * Returns the capacity of the underlying array.
     *
     * @return The length of the queue's underlying array.
     */
    public int getLengthOfUnderlyingArray() {
        return queue.length;
    }


    /**
     * Increases the length of the array if it overflows.
     */
    @SuppressWarnings("unchecked")
    private void ensureCapacity() {
        if ((enqueueIndex + 2) % queue.length == dequeueIndex) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            queue = (T[])new Object[newSize - 1];

            int j = dequeueIndex;
            for (int i = 0; i < oldSize - 1; i++) {
                queue[i] = oldQueue[j];
                j = (j + 1) % oldSize;
            }

            dequeueIndex = 0;
            enqueueIndex = oldSize - 2;
        }
    }


    /**
     * Helper method to manage the indexing of the circular queue.
     *
     * @param index
     *            The current index value.
     * @return The next index value if the index is increased by one.
     */
    private int incrementIndex(int index) {
        return (index + 1) % queue.length;
    }


    /**
     * Returns an array representation of the queue.
     *
     * @return An Object[] array representing the queue.
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }
        @SuppressWarnings("unchecked")
        Object[] returnArray = (T[])new Object[size];
        int j = dequeueIndex;
        for (int i = 0; i < returnArray.length; i++) {
            returnArray[i] = queue[j];
            j = incrementIndex(j);
        }
        return returnArray;
    }


    /**
     * Returns a string representation of the queue.
     *
     * @return A string representation of the queue.
     */
    public String toString() {
        StringBuilder s = new StringBuilder("[");
        int j = dequeueIndex;
        for (int i = 0; i < size; i++) {
            s.append(queue[j]);
            if (i != size - 1) {
                s.append(", ");
            }
            j = incrementIndex(j);
        }
        s.append("]");
        return s.toString();
    }


    /**
     * Compares this queue with another object for equality.
     *
     * @param obj
     *            The object to compare with.
     * @return True if both objects have the same queue order and size,
     *         otherwise false.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (this.getClass() == obj.getClass()) {
            ArrayQueue<?> other = (ArrayQueue<?>)obj;

            if (this.getSize() == other.getSize()) {
                if (this.getSize() != 0) {
                    Object[] thisArray = this.toArray();
                    Object[] otherArray = other.toArray();
                    for (int i = 0; i < thisArray.length; i++) {
                        if (!thisArray[i].equals(otherArray[i])) {
                            return false;
                        }
                    }
                }
                return true;
            }
        }
        return false;
    }
}
