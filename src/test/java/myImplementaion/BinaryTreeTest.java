package myImplementaion;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BinaryTreeTest {
    BinaryTree<Integer> tree;
    @Before
    public void setUp() throws Exception {
        tree = new BinaryTree<>();
        List<Integer> keys = Arrays.asList(50, 40, 45, 25, 15, 30, 5, 10, 75, 85, 35, 60, 20);
        keys.forEach(tree::add);
    }

    @Test
    public void validateBST1() {    // Validate add
        tree.display();
        assertTrue(tree.validateBST());
    }

    @Test
    public void validateBST2() {    // Validate remove of internal node with two children
        tree.remove(25);
        tree.display();
        assertTrue(tree.validateBST());
    }

    @Test
    public void validateBST3() {    // Validate remove of internal node with one child
        tree.remove(30);
        tree.display();
        assertTrue(tree.validateBST());
    }

    @Test
    public void validateBST4() {    // Validate remove of leaf node
        tree.remove(35);
        tree.display();
        assertTrue(tree.validateBST());
    }

    @Test
    public void validateQueueAdd() {    // Validate queue expansion
        int size = 5;
        int max = size + 1;
        Integer[] array = {0, 1, 2, 3, 4};
        Queue<Integer> q = new Queue<>(size);
        for (int i = 0; i < max; i++) {
            q.enqueue(i);
        }
        assertEquals(2 * size, q.getSize());
        for (int i = 0; i < q.getSize(); i++) {
            assertTrue((i < max && i == q.dequeue()) || (i >= max && q.dequeue() == null));
        }
    }
}