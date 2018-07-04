package myImplementaion;

import java.util.Objects;

public class BinaryTree<T extends Comparable> {

    private Node<T> root;
    private int count = 0;
    private int depth = 0;

    public Node<T> getRoot() {
        return root;
    }


    public void add(T key) {

        // Create a new myImplementaion.Node and initialize it
        Node<T> newNode;
        newNode = new Node<>(key);
        count++;
        // If there is no root this becomes root
        if (root == null) root = newNode;
        else {
            // Set root as the myImplementaion.Node we will start with as we traverse the tree
            Node<T> focusNode = root;
            // Future parent for our new myImplementaion.Node
            Node<T> parent;
            while (true) {
                // root is the top parent so we start there
                parent = focusNode;
                // Check if the new node should go on the left side of the parent node
                if (key.compareTo(focusNode.getKey()) <  0){
                    // Switch focus to the left child
                    focusNode = focusNode.getLeftChild();
                    // If the left child has no children
                    if (focusNode == null) {
                        // then place the new node on the left of it
                        parent.setLeftChild(newNode);
                        return; // All Done
                    }
                } else { // If we get here put the node on the right
                    focusNode = focusNode.getRightChild();
                    // If the right child has no children
                    if (focusNode == null) {
                        // then place the new node on the right of it
                        parent.setRightChild(newNode);
                        return; // All Done
                    }
                }
            }
        }
    }

    // All nodes are visited in ascending order
    // Recursion is used to go to one node and
    // then go to its child nodes and so forth
/*
    public void inOrderTraverseTree(Node<T> focusNode) {
        if (focusNode != null) {
            // Traverse the left node
            inOrderTraverseTree(focusNode.getLeftChild());
            // Visit the currently focused on node
            System.out.println(focusNode);
            // Traverse the right node
            inOrderTraverseTree(focusNode.getRightChild());
        }
    }

    public void preorderTraverseTree(Node<T> focusNode) {
        if (focusNode != null) {
            System.out.println(focusNode);
            preorderTraverseTree(focusNode.getLeftChild());
            preorderTraverseTree(focusNode.getRightChild());
        }
    }

    public void postOrderTraverseTree(Node<T> focusNode) {
        if (focusNode != null) {
            postOrderTraverseTree(focusNode.getLeftChild());
            postOrderTraverseTree(focusNode.getRightChild());
            System.out.println(focusNode);
        }
    }
*/

    public Node<T> findNode(T key) {
        // Start at the top of the tree
        Node<T> focusNode = root;
        // While we haven't found the myImplementaion.Node
        // keep looking
        while (!focusNode.getKey().equals(key)) {
            // If we should search to the left
            // Shift the focus myImplementaion.Node to the right child
            // Shift the focus myImplementaion.Node to the left child
            if (key.compareTo(focusNode.getKey()) <0) focusNode = focusNode.getLeftChild();
            else                          focusNode = focusNode.getRightChild();
            // The node wasn't found
            if (focusNode == null) return null;
        }
        return focusNode;
    }

    public boolean remove(T key) {
        // Start at the top of the tree
        Node<T> focusNode = root;
        Node<T> parent = root;
        // When searching for a myImplementaion.Node this will tell us whether to search to the right or left
        boolean isItALeftChild = true;
        // While we haven't found the myImplementaion.Node keep looking
        while (!Objects.equals(key, focusNode.getKey())) {
            parent = focusNode;
            // If we should search to the left
            if (key.compareTo(focusNode.getKey()) < 0) {
                isItALeftChild = true;
                // Shift the focus myImplementaion.Node to the left child
                focusNode = focusNode.getLeftChild();
            } else {
                // Greater than focus node so go to the right
                isItALeftChild = false;
                // Shift the focus myImplementaion.Node to the right child
                focusNode = focusNode.getRightChild();
            }
            // The node wasn't found
            if (focusNode == null) return false;
        }
        count--;
        // If myImplementaion.Node doesn't have children delete it
        if (focusNode.getLeftChild() == null && focusNode.getRightChild() == null) {
            // If root delete it
            if (focusNode == root) root = null;
                // If it was marked as a left child oy the parent delete it in its parent
            else if (isItALeftChild) parent.setLeftChild(null);
                // Vice versa for the right child
            else                     parent.setRightChild(null);
        }
        // If no right child
        else if (focusNode.getRightChild() == null) {
            if (focusNode == root) root = focusNode.getLeftChild();
                // If focus myImplementaion.Node was on the left of parent, move the focus Nodes left child up to the parent node
            else if (isItALeftChild) parent.setLeftChild(focusNode.getLeftChild());
                // Vice versa for the right child
            else                     parent.setRightChild(focusNode.getLeftChild());
        }
        // If no left child
        else if (focusNode.getLeftChild() == null) {
            if (focusNode == root) root = focusNode.getRightChild();

                // If focus myImplementaion.Node was on the left of parent, move the focus Nodes right child up to the parent node
            else if (isItALeftChild) parent.setLeftChild(focusNode.getRightChild());
                // Vice versa for the left child
            else                     parent.setRightChild(focusNode.getRightChild());
        }
        // Two children so I need to find the deleted nodes replacement
        else {
            Node<T> replacement = getReplacementNode(focusNode);
            // If the focusNode is root replace root, with the replacement
            if (focusNode == root) root = replacement;
                // If the deleted node was a left child, make the replacement the left child
            else if (isItALeftChild)
                parent.setLeftChild(replacement);
                // Vice versa if it was a right child
            else
                parent.setRightChild(replacement);
            replacement.setLeftChild(focusNode.getLeftChild());
        }
        return true;
    }

    private Node<T> getReplacementNode(Node<T> replacedNode) {
        Node<T> replacementParent = replacedNode;
        Node<T> replacement = replacedNode;
        Node<T> focusNode = replacedNode.getRightChild();
        // While there are no more left children
        while (focusNode != null) {
            replacementParent = replacement;
            replacement = focusNode;
            focusNode = focusNode.getLeftChild();
        }
        // If the replacement isn't the right child, move the replacement into the parents
        // leftChild slot and move the replaced node's right child into the replacements rightChild
        if (replacement != replacedNode.getRightChild()) {
            replacementParent.setLeftChild(replacement.getRightChild());
            replacement.setRightChild(replacedNode.getRightChild());
        }
        return replacement;
    }

    public int depthOfTree(Node node) {
        if (null == node) return 0;
        int hLeftSub = depthOfTree(node.getLeftChild());
        int hRightSub = depthOfTree(node.getRightChild());
        return Math.max(hLeftSub, hRightSub) + 1;
    }


    public void display() {
        int depth = depthOfTree(root);
        int width = (int) Math.pow(2, depth) * 2;

        int len = width * depth * 2 + 2;
        StringBuilder sb = new StringBuilder(len);
        for (int i = 1; i <= len; i++)
            sb.append(i < len - 2 && i % width == 0 ? "\n" : ' ');

        displayR(sb, width / 2, 1, width / 4, width, root, " ");
        System.out.println(sb);
    }

    private void displayR(StringBuilder sb, int c, int r, int d, int w, Node<T> n, String edge) {
        if (n != null) {
            displayR(sb, c - d, r + 2, d / 2, w, n.getLeftChild(), " /");

            String s = String.valueOf(n.getKey());
            int idx1 = r * w + c - (s.length() + 1) / 2;
            int idx2 = idx1 + s.length();
            int idx3 = idx1 - w;
            if (idx2 < sb.length())
                sb.replace(idx1, idx2, s).replace(idx3, idx3 + 2, edge);

            displayR(sb, c + d, r + 2, d / 2, w, n.getRightChild(), "\\ ");
        }
    }
}