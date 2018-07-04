package myImplementaion;

import lombok.Data;

@Data
public class Node<T extends Comparable> implements Comparable<T> {

    private T key;

    private Node<T> leftChild;
    private Node<T> rightChild;

    Node(T key) {
        this.key = key;
    }

    @Override
    public boolean equals(Object key) {
        if(key instanceof Number) {
            return this.key == key;
        } else {
            return this.key.equals(key);
        }
    }

    public String toString() {
        //return name + " has the key " + key;
        return String.format("The key %-3s\tLeft Child: %-15s\tRight Child: %-15s\t",
            key, leftChild == null ? null : leftChild.key, rightChild == null ? null : rightChild.key);
    }

    @Override
    public int compareTo(T key) {
        return this.key.compareTo(key);
    }
}