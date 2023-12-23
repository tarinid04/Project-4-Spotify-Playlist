package dailymixes;

import student.TestCase;
import queue.EmptyQueueException;
// Virginia Tech Honor Code Pledge:
//
// As a Hokie, I will conduct myself with honor and integrity at all times.
// I will not lie, cheat, or steal, nor will I
// accept the actions of those who do.
// -- Tarini Duvvuri (tarinid)

/**
 * Test class for ArrayQueue.
 * 
 * @author Tarini Duvvuri
 * @version 10.31.23
 */
public class ArrayQueueTest extends TestCase {

    /**
     * Private ArrayQueue object.
     */
    private ArrayQueue<String> queue;

    /**
     * Private ArrayQueue object to represent different
     * queue state from 'queue' variable.
     */
    private ArrayQueue<String> queue1;

    /**
     * Set up the test environment.
     */
    public void setUp() {
        queue = new ArrayQueue<String>(6);
        queue.enqueue("Obj0");
        queue.enqueue("Obj00");
        queue.enqueue("Obj000");
        queue.dequeue();
        queue.dequeue();
        queue.enqueue("Obj0000");
        queue.enqueue("Obj1");
        queue.enqueue("Obj2");
        queue.enqueue("Obj3");
        queue.enqueue("Obj4");
        queue.dequeue();
        queue.dequeue();

        queue1 = new ArrayQueue<String>();
        queue1.enqueue("Obj0");
        queue1.enqueue("Obj1");
        queue1.enqueue("Obj2");
        queue1.enqueue("Obj3");
        queue1.enqueue("Obj4");
    }


    /**
     * Test all one-liner and simple getters methods.
     */
    public void testGetters() {
        assertEquals(queue.getSize(), 4);
        assertEquals(queue.getLengthOfUnderlyingArray(), 7);
        assertEquals(queue.getFront(), "Obj1");
        queue.dequeue();
        assertEquals(queue.getFront(), "Obj2");
        queue.dequeue();
        assertEquals(queue.getFront(), "Obj3");
        queue.dequeue();
        assertEquals(queue.getFront(), "Obj4");
        assertEquals(queue.getSize(), 1);
        queue.dequeue();
    }


    /**
     * Test exceptions thrown by getFront(), dequeue(), and toArray() methods.
     */
    public void testAllException() {
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
        queue1.dequeue();
        Exception e = null;
        try {
            queue1.dequeue();
        }
        catch (Exception exception) {
            e = exception;
        }
        assertTrue(e instanceof EmptyQueueException);

        Exception e2 = null;
        try {
            @SuppressWarnings("unused")
            Object[] arr = queue1.toArray();
        }
        catch (Exception exception) {
            e2 = exception;
        }
        assertTrue(e2 instanceof EmptyQueueException);
    }


    /**
     * Test clear() and isEmpty() methods.
     */
    public void testClearAndIsEmpty() {
        assertFalse(queue.isEmpty());
        queue.clear();
        assertTrue(queue.isEmpty());
        assertEquals(queue.getSize(), 0);
        assertEquals(queue.getLengthOfUnderlyingArray(), 21);
    }


    /**
     * Test the toString() method.
     */
    public void testToString() {
        String s = "[Obj1, Obj2, Obj3, Obj4]";
        assertEquals(queue.toString(), s);
    }


    /**
     * Test the equals() method.
     */
    public void testEquals() {
        assertFalse(queue.equals(null));
        assertTrue(queue.equals(queue));

        assertFalse(queue.equals((Object)1));

        assertFalse(queue.equals(queue1));
        queue1.dequeue();
        assertTrue(queue.equals(queue1));

        ArrayQueue<Integer> queue2 = new ArrayQueue<Integer>();
        assertFalse(queue.equals(queue2));
        queue2.enqueue(1);
        queue2.enqueue(2);
        queue2.enqueue(3);
        queue2.enqueue(4);
        assertFalse(queue.equals(queue2));
    }


    /**
     * Test toArray() method.
     */
    public void testToArray() {
        Object[] array1 = queue.toArray();
        Object[] array2 = queue1.toArray();

        assertEquals(array1.length, 4);
        assertEquals(array2.length, 5);

        assertEquals(array2[0], "Obj0");

        for (int i = 0; i < array1.length; i++) {
            assertEquals(array1[i], array2[i + 1]);
        }
    }


    /**
     * Test ensureCapacity() method.
     */
    public void testEnsureCapacity() {
        queue.enqueue("Obj5");
        queue.enqueue("Obj6");
        queue.enqueue("Obj7");
        queue.enqueue("Obj8");
        assertEquals(queue.getLengthOfUnderlyingArray(), 13);
        assertEquals(queue.getSize(), 8);
        Object[] array = queue.toArray();
        for (int i = 0; i < array.length; i++) {
            assertEquals(array[i], "Obj" + (i + 1));
        }
    }
}
