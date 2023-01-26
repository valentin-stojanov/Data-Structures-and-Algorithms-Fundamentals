package implementations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTestJunit5 {

    private DoublyLinkedList<String> list;

    @BeforeEach
    void setUp() {
        try {
            this.list = new DoublyLinkedList<>();
            for (int i = 0; i < 100; i++) {
                list.addLast(String.valueOf(i));
            }
        } catch (Exception ignored) {
            this.list = new DoublyLinkedList<>();
        }
    }

    @Test
    void shouldInitializeEmptyList() {
        DoublyLinkedList<String> emptyList = new DoublyLinkedList<>();
        Assertions.assertEquals( 0, emptyList.size());
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
    void addLast_ShouldAddOneElement(){
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
    void shouldCorrectAddFirstAndLastWhenCombined(){
        DoublyLinkedList<Integer> numbers = new DoublyLinkedList<>();

        for (int i = 0; i < 5; i++) {
            if (i % 2 != 0){
                numbers.addFirst(i);
            }else {
                numbers.addLast(i);
            }
        }

        for (int i = 4; i >= 0; i--) {
            if (i % 2 == 0){
               assertEquals(i, numbers.removeLast());
            }else {
                assertEquals(i, numbers.removeFirst());
            }
        }
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() {
    }

    @Test
    void removeLast() {
    }

    @Test
    void getFirst() {
    }

    @Test
    void getLast() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void iterator() {
    }
}