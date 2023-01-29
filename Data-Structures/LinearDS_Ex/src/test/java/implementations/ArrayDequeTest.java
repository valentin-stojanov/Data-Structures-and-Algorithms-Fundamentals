package implementations;

import org.junit.jupiter.api.BeforeEach;
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
    void ShouldInitializeEmptyArrayDeque(){
        ArrayDeque<Integer> emptySet = new ArrayDeque<>();
        assertEquals(0, emptySet.size());
        assertEquals(7, emptySet.capacity());
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
    void ShouldRemoveElementAtGivenIndex() {
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


}