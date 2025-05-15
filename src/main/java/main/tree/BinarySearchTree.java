package main.tree;

import lombok.Getter;

@Getter
public class BinarySearchTree<T extends String> implements Tree<T> {
    protected Node<T> root;

    @Override
    public boolean insert(T value) {
        if (contains(value)) return false;
        root = insert(root, value);
        return true;
    }

    private Node<T> insert(Node<T> node, T value) {
        if (node == null) return new Node<>(value);
        if (compare(value, node.key) < 0)
            node.left = insert(node.left, value);
        else
            node.right = insert(node.right, value);
        return node;
    }

    @Override
    public void remove(T value) {
        root = remove(root, value);
    }

    private Node<T> remove(Node<T> node, T value) {
        if (node == null) return null;
        int cmp = compare(value, node.key);
        if (cmp < 0) node.left = remove(node.left, value);
        else if (cmp > 0) node.right = remove(node.right, value);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;
            Node<T> min = getMin(node.right);
            node.key = min.key;
            node.right = remove(node.right, min.key);
        }
        return node;
    }

    Node<T> getMin(Node<T> node) {
        while (node.left != null) node = node.left;
        return node;
    }

    @Override
    public boolean contains(T value) {
        Node<T> current = root;
        while (current != null) {
            int cmp = compare(value, current.key);
            if (cmp == 0) return true;
            current = (cmp < 0) ? current.left : current.right;
        }
        return false;
    }

    protected int compare(T a, T b) {
        return (a.length() != b.length())
                ? Integer.compare(a.length(), b.length())
                : a.compareToIgnoreCase(b);
    }

    @Override
    public int height() {
        return calcHeight(root);
    }

    private int calcHeight(Node<T> node) {
        if (node == null) return 0;
        return 1 + Math.max(calcHeight(node.left), calcHeight(node.right));
    }

    public boolean isBalanced() {
        return isBalanced(root);
    }

    private boolean isBalanced(Node<T> node) {
        if (node == null) return true;
        int lh = calcHeight(node.left);
        int rh = calcHeight(node.right);
        return Math.abs(lh - rh) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }

}