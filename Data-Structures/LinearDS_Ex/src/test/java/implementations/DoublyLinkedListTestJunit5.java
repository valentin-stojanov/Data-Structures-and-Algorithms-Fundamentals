package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTestJunit5 {

    private DoublyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        this.list = new DoublyLinkedList<>();

        for (int i = 0; i < 10; i++) {
            list.addLast(String.valueOf(i));
        }
    }

    @Test
    void shouldInitializeEmptyList() {
        DoublyLinkedList<String> emptyList = new DoublyLinkedList<>();
        Assertions.assertEquals(0, emptyList.size());
    }


    @Test
    void addFirst_shouldAddOneElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("first");

//        Test for infinite loop.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            assertEquals("first", list.getFirst(), "Possible infinite loop for getFirst()");
            assertEquals("first", list.removeFirst(), "Possible infinite loop for getFirst()");
        });

        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, list::getFirst);

    }

    @Test
    void addLast_ShouldAddOneElement() {
        DoublyLinkedList<String> list = new DoublyLinkedList<>();

        list.addFirst("first");
//        Test for infinite loop.
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            assertEquals("first", list.getLast());
            assertEquals("first", list.removeLast());
        }, "Possible infinite loop for getLast()");

        assertEquals(0, list.size());
        assertThrows(IllegalStateException.class, list::getLast);

    }

    @Test
    void shouldCorrectAddFirstAndLastWhenCombined() {
        DoublyLinkedList<Integer> numbers = new DoublyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            if (i % 2 != 0) {
                numbers.addFirst(i);
            } else {
                numbers.addLast(i);
            }
        }

        for (int i = 4; i >= 0; i--) {
            if (i % 2 == 0) {
                assertEquals(i, numbers.removeLast());
            } else {
                assertEquals(i, numbers.removeFirst());
            }
        }

        assertEquals(0, numbers.size());
    }

    @Test
    void ShouldAddLastElementMultipleTimeCorrectly() {

        assertEquals("9", this.list.getLast());

    }

    @Test
    void ShouldRemoveFirstElementMultipleTime() {
        for (int i = 0; i < 10; i++) {
            assertEquals(String.valueOf(i), this.list.removeFirst());
        }

        assertEquals(0, this.list.size());
    }

    @Test
    void ShouldRemoveLastElementMultipleTime() {
        for (int i = 9; i >= 0; i--) {
            assertEquals(String.valueOf(i), this.list.removeLast());
        }

        assertEquals(0, this.list.size());
    }

    @Test
    void ShouldReturnFirstElement() {
        assertEquals("0", this.list.getFirst());
        assertEquals(10, this.list.size());
    }

    @Test
    void ShouldReturnLastElement() {
        assertEquals("9", this.list.getLast());
        assertEquals(10, this.list.size());
    }

    @Test
    void ShouldReturnNumberOfElements() {
        assertEquals(10, this.list.size());
    }

    @Test
    void ShouldReturnZeroSizeForEmptyCollection() {
        DoublyLinkedList<Integer> emptyList = new DoublyLinkedList<>();
        assertEquals(0, emptyList.size());
    }

    @Test
    void ShouldTraversFromHeadToTail() {
        int currentNumber = 0;

        for (String s : this.list) {
            assertEquals(s, String.valueOf(currentNumber++));
        }
    }
}