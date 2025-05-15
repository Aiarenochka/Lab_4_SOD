package main.tree;

public class Node<T> {
    public T key;
    public Node<T> left;
    public Node<T> right;
    int height;

    public Node(T key) {
        this.key = key;
        this.height = 1;
    }
}