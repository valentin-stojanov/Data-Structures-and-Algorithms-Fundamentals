import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree<>(12);
        bst.insert(5);
        bst.insert(21);
        bst.insert(1);
        bst.insert(8);
        bst.insert(18);
        bst.insert(23);
    }

    @Test
    void shouldCreateBinarySearchTree() {
        assertEquals(Integer.valueOf(12), this.bst.getRoot().getValue());
    }

    @Test
    void insertNodes() {

//                       12
//                     /    \
//                    5      21
//                   / \     / \
//                  1   8   18  23

        var left5 = this.bst.getRoot().getLeft();
        var left1 = left5.getLeft();
        var right8 = left5.getRight();
        var right21 = this.bst.getRoot().getRight();
        var left18 = right21.getLeft();
        var right23 = right21.getRight();

        assertEquals(Integer.valueOf(5), left5.getValue());
        assertEquals(Integer.valueOf(1), left1.getValue());
        assertEquals(Integer.valueOf(18), left18.getValue());
        assertEquals(Integer.valueOf(21), right21.getValue());
        assertEquals(Integer.valueOf(23), right23.getValue());
        assertEquals(Integer.valueOf(8), right8.getValue());
    }

    @Test
    void consumerTest() {
        List<Integer> list = new ArrayList<>();
        this.bst.eachInOrder(list::add);
        List<Integer> expected = List.of(1, 5, 8, 12, 18, 21, 23);
        assertEquals(list, expected);
    }

    @Test
    void shouldFindNodeFive() {
        BinarySearchTree<Integer> tree = this.bst.search(5);
        List<Integer> list = new ArrayList<>();

        tree.eachInOrder(list::add);
        List<Integer> expected = List.of(1, 5, 8);

        assertEquals(list, expected);
    }

    @Test
    void shouldFindNodeTwentyOne() {
        BinarySearchTree<Integer> tree = this.bst.search(21);
        List<Integer> list = new ArrayList<>();

        tree.eachInOrder(list::add);
        List<Integer> expected = List.of(18, 21, 23);

        assertEquals(list, expected);
    }

    @Test
    void shouldFindNodeOne() {
        BinarySearchTree<Integer> tree = this.bst.search(1);
        List<Integer> list = new ArrayList<>();

        tree.eachInOrder(list::add);
        List<Integer> expected = List.of(1);

        assertEquals(list, expected);
    }

    @Test
    void shouldFindNodeTwentyThree() {
        BinarySearchTree<Integer> tree = this.bst.search(23);
        List<Integer> list = new ArrayList<>();

        tree.eachInOrder(list::add);
        List<Integer> expected = List.of(23);

        assertEquals(list, expected);
    }

    @Test
    void shouldFindTheRoot() {
        BinarySearchTree<Integer> tree = this.bst.search(12);
        List<Integer> list = new ArrayList<>();

        tree.eachInOrder(list::add);
        List<Integer> expected = List.of(1, 5, 8, 12, 18, 21, 23);

        assertEquals(list, expected);
    }

    @Test
    void shouldNotFindNodeTen() {
        BinarySearchTree<Integer> tree = this.bst.search(10);
        assertNull(tree.getRoot());
    }

    @Test
    void shouldInsertCorrectlyAfterCreatingEmptyBST() {
        BinarySearchTree<Integer> tree = this.bst.search(10);
        tree.insert(12);

        assertEquals(12, tree.getRoot().getValue());
    }

    @Test
    void shouldContainsNodeFive() {
        assertTrue(this.bst.contains(5));
    }

    @Test
    void shouldContainsNodeTwentyOne() {
        assertTrue(this.bst.contains(21));
    }

    @Test
    void shouldContainsNodeOne() {
        assertTrue(this.bst.contains(1));
    }

    @Test
    void shouldContainsNodeTwentyThree() {
        assertTrue(this.bst.contains(23));
    }

    @Test
    void shouldContainsTheRoot() {
        assertTrue(this.bst.contains(23));
    }

    @Test
    void shouldNotContainsNodeTen() {
        assertFalse(this.bst.contains(10));
    }

    @Test
    void shouldGetCorrectElementsBetweenOneAndEight() {
        List<Integer> integers = this.bst.range(1, 8);
        integers.sort(null);
        List<Integer> expected = List.of(1, 5, 8);

        assertEquals(expected, integers);
    }

    @Test
    void shouldGetAllElements() {
        List<Integer> integers = this.bst.range(1, 23);
        integers.sort(null);
        List<Integer> expected = List.of(1, 5, 8, 12, 18, 21, 23);

        assertEquals(expected, integers);
    }

    @Test
    void shouldNotGetElements() {
        List<Integer> integers = this.bst.range(24, 30);
        assertEquals(0, integers.size());
    }

    @Test
    void shouldDeleteOne() {
        this.bst.deleteMin();
        assertFalse(this.bst.contains(1));
    }

    @Test
    void shouldDeleteAllElements() {
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        assertNull(this.bst.getRoot());
    }

    @Test()
    void shouldThrowIllegalStateException() {
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        this.bst.deleteMin();
        assertThrows(IllegalArgumentException.class, () -> this.bst.deleteMin());
    }

}