package main.tree;

public interface Tree<T> {
    boolean insert(T value);
    void remove(T value);
    boolean contains(T value);
    int height();
}
