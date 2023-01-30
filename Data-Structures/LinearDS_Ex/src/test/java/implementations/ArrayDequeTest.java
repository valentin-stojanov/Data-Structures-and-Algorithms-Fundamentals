package implementations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayDequeTest {

    ArrayDeque<Integer> deque = new ArrayDeque<>();

    @BeforeEach
    void setUp(){
        for (int i = 0; i < 10; i++) {
            this.deque.add(i);
        }
    }

    @Test
    void shouldInitializeEmptyArrayDeque(){
        ArrayDeque<Integer> emptySet = new ArrayDeque<>();
        assertEquals(0, emptySet.size());
        assertEquals(7, emptySet.capacity());
    }

    @Test
    void size(){
        assertEquals(10, this.deque.size());
    }

    @Test
    void capacity(){
        assertEquals(5, this.deque.capacity());
    }

    @Test
    void getByIndex(){
        assertEquals(0, this.deque.get(0));
        assertEquals(9, this.deque.get(9));
        assertThrows(IndexOutOfBoundsException.class, () -> this.deque.get(-1));
        assertThrows(IndexOutOfBoundsException.class, () -> this.deque.get(10));
    }


    @Test
    @DisplayName("Add - adds an element at the end")
    void add(){
        this.deque.add(10);
        assertEquals(10, this.deque.get(10));
        assertEquals(11, this.deque.size());
        assertEquals(20, this.deque.capacity());

    }

    @Test
    @DisplayName("AddFirst - adds an element in front of all other elements")
    void addFirst(){
        this.deque.addFirst(10);
        assertEquals(9, this.deque.get(10));
        assertEquals(11, this.deque.size());
        assertEquals(4, this.deque.capacity());
    }

    @Test
    @DisplayName("AddLast - adds an element after the last one")
    void addLast(){
        this.deque.addLast(10);
        assertEquals(10, this.deque.get(10));
        assertEquals(11, this.deque.size());
        assertEquals(20, this.deque.capacity());

    }

    @Test
    @DisplayName("Offer - adds an element the same way a Queue does")
    void offer(){
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        queue.offer(1);
        queue.offer(2);
        queue.offer(3);
        assertEquals(1, queue.peek());
        assertEquals(1, queue.poll());
        assertEquals(2, queue.poll());
        assertEquals(3, queue.poll());
    }

    @Test
    @DisplayName("Push - adds an element the same way a Stack does")
    void push(){
        ArrayDeque<Integer> stackTest = new ArrayDeque<>();
        stackTest.push(10);
        stackTest.push(11);
        stackTest.push(12);
        assertEquals(12, stackTest.peek());
        assertEquals(12, stackTest.pop());
        assertEquals(11, stackTest.pop());
        assertEquals(10, stackTest.pop());
    }

    @Test
    void shouldAddElementsLikeList(){
        int currentNumber = 0;
        for (Integer num : this.deque) {
            assertEquals(currentNumber++, num);
        }
    }





    @Test
    void checkForCorrectIndexingOfElements() {
        assertEquals(0, this.deque.get(0));
        assertEquals(5, this.deque.get(5));
        assertEquals(9, this.deque.get(9));
    }

    @Test
    void shouldRemoveElementAtGivenIndex() {
        assertEquals(4, this.deque.remove(4));
        assertEquals(0, this.deque.get(0));
        assertEquals(9, this.deque.get(8));

        assertEquals(9, this.deque.size());
        assertEquals(6, this.deque.capacity());

    }

    @Test
    void shouldPollCorrectElement(){
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");

        assertEquals("1", queue.poll());
        assertEquals(2, queue.size());
        assertEquals(5, queue.capacity());
    }

    @Test
    void shouldPeakCorrectElementQueue(){
        ArrayDeque<String> queue = new ArrayDeque<>();
        queue.offer("1");
        queue.offer("2");
        queue.offer("3");

        assertEquals("1", queue.peek());
        assertEquals(3, queue.size());
        assertEquals(4, queue.capacity());
    }

    @Test
    void shouldPopCorrectElement(){
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("3", stack.poll());
        assertEquals(2, stack.size());
        assertEquals(5, stack.capacity());
    }

    @Test
    void shouldPeakCorrectElementStack(){
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("1");
        stack.push("2");
        stack.push("3");

        assertEquals("3", stack.peek());
        assertEquals(3, stack.size());
        assertEquals(4, stack.capacity());
    }

    @Test
    void shouldInsertCorrectAtIndexInLeftHalf() {

        this.deque.insert(3, 13);

        assertEquals(2, this.deque.get(2));
        assertEquals(13, this.deque.get(3));
        assertEquals(3, this.deque.get(4));
        assertEquals(11, this.deque.size());
        assertEquals(4, this.deque.capacity());
    }

    @Test
    void shouldInsertCorrectAtIndexInLeftHalfAfterResize() {
        this.deque.insert(0, -1);
        this.deque.insert(0, -2);
        this.deque.insert(0, -3);
        this.deque.insert(0, -4);
        this.deque.insert(0, -5);
        this.deque.insert(5, 100);

        assertEquals(-5, this.deque.get(0));
        assertEquals(-1, this.deque.get(4));
        assertEquals(100, this.deque.get(5));
        assertEquals(0, this.deque.get(6));
        assertEquals(9, this.deque.get(15));
        assertEquals(16, this.deque.size());
        assertEquals(15, this.deque.capacity());
    }

    @Test
    void shouldInsertCorrectAtIndexInRightHalfAfterResize() {
        this.deque.insert(6, 69);

        assertEquals(0, this.deque.get(0));
        assertEquals(5, this.deque.get(5));
        assertEquals(69, this.deque.get(6));
        assertEquals(6, this.deque.get(7));
        assertEquals(9, this.deque.get(10));
        assertEquals(11, this.deque.size());
        assertEquals(20, this.deque.capacity());
    }


}