import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


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

}