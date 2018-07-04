package myImplementaion;

import java.util.Arrays;
import java.util.List;

public class BinaryTreeMain {

    private void go() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        List<Integer> keys = Arrays.asList(50, 40, 45, 25, 15, 30, 5, 10, 75, 85, 35, 60, 20);
        keys.forEach(tree::add);

        System.out.println();
        tree.display();
        tree.remove(25);
        System.out.println("Remove node with key 25");
        tree.display();
    }

    public static void main(String[] args) {
        new BinaryTreeMain().go();
    }

}
