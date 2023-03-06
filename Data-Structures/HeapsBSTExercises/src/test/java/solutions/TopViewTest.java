package solutions;

import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class TopViewTest {

    @Test
    public void testTopView() {
        BinaryTree binaryTree =
                new BinaryTree(1,
                        new BinaryTree(2,
                                new BinaryTree(4, null, null),
                                new BinaryTree(5, null, null)),
                        new BinaryTree(3,
                                new BinaryTree(6, null, null),
                                new BinaryTree(7, null, null)));


        List<Integer> list = binaryTree.topView();

        Collections.sort(list);
        Integer[] expected = {1, 2, 3, 4, 7};

        assertEquals(expected.length, list.size());

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }
                                        /*
                                         1
                                      /    \
                                     2      3
                                      \       \
                                       4       5
                                      / \     /  \
                                     6   9   7    8
                                           X
                                          /  \
                                         10   13
                                        /       \
                                       11        14
                                      /            \
                                     12             15
                                         */
    @Test
    public void secondTestTopView() {
        BinaryTree binaryTree = new BinaryTree(1,
                new BinaryTree(2, null,
                        new BinaryTree(4,
                                new BinaryTree(6, null, null),
                                new BinaryTree(9,
                                        null, new BinaryTree(13, null,
                                        new BinaryTree(14, null,
                                                new BinaryTree(15, null, null)))))),
                new BinaryTree(3, null,
                        new BinaryTree(5,
                                new BinaryTree(7,
                                        new BinaryTree(10,
                                                new BinaryTree(11,
                                                        new BinaryTree(12, null, null), null), null),
                                        null),
                                new BinaryTree(8, null, null))));

        List<Integer> list = binaryTree.topView();

        Collections.sort(list);
        Integer[] expected = {1, 2, 3, 5, 8, 12, 15};

        assertEquals(expected.length, list.size());

        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }
}
