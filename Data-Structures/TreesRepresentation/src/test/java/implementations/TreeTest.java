package implementations;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class TreeTest {

    private Tree<Integer> tree;

    @Before
    public void setUp() {
        this.tree = new Tree<>(7,
                new Tree<>(19,
                        new Tree<>(1),
                        new Tree<>(12),
                        new Tree<>(31)),
                new Tree<>(21),
                new Tree<>(14,
                        new Tree<>(23),
                        new Tree<>(6))
        );
    }

    @Test
    public void testTreeConstructor() {
        assertNotNull(tree);
    }

    @Test
    public void testTreeBfs() {
        Integer[] expected = {7, 19, 21, 14, 1, 12, 31, 23, 6};
        int index = 0;
        for (Integer num : tree.orderBfs()) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testTreeDfs() {
        Integer[] expected = {1, 12, 31, 19, 21, 23, 6, 14, 7};
        int index = 0;
        for (Integer num : tree.orderDfs()) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testAddTree() {
        tree.addChild(1, new Tree<>(-1, new Tree<>(-2), new Tree<>(-3)));
        Integer[] expected = {-2, -3, -1, 1, 12, 31, 19, 21, 23, 6, 14, 7};
        int index = 0;
        for (Integer num : tree.orderDfs()) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testRemoveNode() {
        tree.removeNode(19);
        Integer[] expected = {7, 21, 14, 23, 6};

        List<Integer> integers = tree.orderBfs();
        assertEquals(expected.length, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testRemoveRootNode() {
        tree.removeNode(7);

        List<Integer> integers = tree.orderBfs();
        assertEquals(0, integers.size());
    }

    @Test
    public void testSwap() {
        tree.swap(19, 14);
        Integer[] expected = {7, 14, 21, 19, 23, 6, 1, 12, 31};
        List<Integer> integers = tree.orderBfs();
        assertEquals(expected.length, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testSwapLeafsInSameChildrenList() {
        tree.swap(1, 31);
        Integer[] expected = {7, 19, 21, 14, 31, 12, 1, 23, 6};
        List<Integer> integers = tree.orderBfs();
        assertEquals(expected.length, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testSwapAtDifferentLevels() {
        tree.swap(19, 6);
        Integer[] expected = {7, 6, 21, 14, 23, 19, 1, 12, 31};
        List<Integer> integers = tree.orderBfs();
        assertEquals(expected.length, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testSwapParentWithLeaf() {
        tree.swap(19, 31);
        Integer[] expected = {7, 31, 21, 14, 23, 6};
        List<Integer> integers = tree.orderBfs();
        assertEquals(expected.length, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }

    @Test
    public void testSwapLeafWithRoot() {
        tree.swap(6, 7);
        Integer[] expected = {6};
        List<Integer> integers = tree.orderBfs();
        assertEquals(1, integers.size());

    }

    @Test
    public void testSwapTreeWithRoot() {
        tree.swap(19, 7);
        Integer[] expected = {19,1,12,31};
        List<Integer> integers = tree.orderBfs();
        assertEquals(4, integers.size());
        int index = 0;
        for (Integer num : integers) {
            assertEquals(expected[index++], num);
        }
    }
}