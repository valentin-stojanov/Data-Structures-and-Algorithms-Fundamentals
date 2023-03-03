package solutions;

import interfaces.Heap;
import model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class MinHeapTest {
    private MinHeap<Product> minHeap;

    @BeforeEach
    public void setUp() {
        this.minHeap = new MinHeap<>();
        List<Integer> elements = new ArrayList<>(List.of(15, 25, 6, 9, 5, 8, 17, 16));
        for (Integer element : elements) {
            this.minHeap.add(new Product(element));
        }
    }

    @Test
    void checkStructureOfTheHeap(){
        List<Integer> expected = List.of(5,6,8,9,15,16,17,25);
        for (int i = 0; i < expected.size(); i++) {
            assertEquals(expected.get(i), this.minHeap.poll().getPrice());
        }
    }

    @Test
    public void testHeapifyUpAddOne() {
        Heap<Product> heap = new MinHeap<>();
        heap.add(new Product(13));
        assertEquals(13, heap.peek().getPrice());
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenPeakEmptyTree() {
        Heap<Product> heap = new MinHeap<>();
        assertThrows( IllegalStateException.class, heap::peek);
    }

    @Test
    public void shouldThrowIllegalStateExceptionWhenPollEmptyTree() {
        Heap<Product> heap = new MinHeap<>();
        assertThrows( IllegalStateException.class, heap::poll);
    }

    @Test
    public void testHeapifyUpAddMany() {
        assertEquals(5, minHeap.peek().getPrice());
    }

    @Test
    public void testSizeShouldBeCorrect() {
        assertEquals(8, this.minHeap.size());
    }

    @Test
    public void testDecreaseSingleElement() {
        MinHeap<Product> heap = new MinHeap<>();

        heap.add(new Product(3));

        heap.decrease(new Product(3));

        assertEquals(2, heap.peek().getPrice());
    }
}