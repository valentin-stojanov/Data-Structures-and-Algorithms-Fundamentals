package solutions;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class LowestCommonAncestorTest {
    private BinaryTree binaryTree;
    @BeforeEach
    void setUp(){
        this.binaryTree =
                new BinaryTree(7,
                        new BinaryTree(21, null, null),
                        new BinaryTree(14,
                                new BinaryTree(23, null, null),
                                new BinaryTree(6, null,
                                        new BinaryTree(13, null, null))));
    }
    @Test
    void testLCA() {
        assertEquals(Integer.valueOf(7), this.binaryTree.findLowestCommonAncestor(21, 13));
        assertEquals(Integer.valueOf(14), this.binaryTree.findLowestCommonAncestor(23, 13));
        assertEquals(Integer.valueOf(14), this.binaryTree.findLowestCommonAncestor(6, 13));
        assertNull(this.binaryTree.findLowestCommonAncestor(7, 13));

    }

}