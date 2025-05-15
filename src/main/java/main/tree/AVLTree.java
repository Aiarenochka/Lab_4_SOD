package main.tree;

public class AVLTree<T extends String> extends BinarySearchTree<T> {

    @Override
    public boolean insert(T value) {
        if (contains(value)) return false;
        root = insertAVL(root, value);
        return true;
    }

    private Node<T> insertAVL(Node<T> node, T value) {
        if (node == null) return new Node<>(value);

        if (compare(value, node.key) < 0) {
            node.left = insertAVL(node.left, value);
        } else {
            node.right = insertAVL(node.right, value);
        }

        return balance(node);
    }

    @Override
    public void remove(T value) {
        root = removeAVL(root, value);
    }

    private Node<T> removeAVL(Node<T> node, T value) {
        if (node == null) return null;

        if (compare(value, node.key) < 0) {
            node.left = removeAVL(node.left, value);
        } else if (compare(value, node.key) > 0) {
            node.right = removeAVL(node.right, value);
        } else {
            // Вузол з одним або без дітей
            if (node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else {
                // Вузол з двома дітьми: шукаємо мінімальний у правому піддереві
                Node<T> min = getMin(node.right);
                node.key = min.key;
                node.right = removeAVL(node.right, min.key);
            }
        }

        return (node == null) ? null : balance(node);
    }

    @Override
    protected Node<T> getMin(Node<T> node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    private Node<T> balance(Node<T> node) {
        updateHeight(node);
        int balance = getBalance(node);

        if (balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }

        if (balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private void updateHeight(Node<T> node) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
    }

    private int height(Node<T> node) {
        return (node == null) ? 0 : node.height;
    }

    private int getBalance(Node<T> node) {
        return height(node.left) - height(node.right);
    }

    private Node<T> rotateRight(Node<T> root) {
        Node<T> newRoot = root.left;
        Node<T> temp = newRoot.right;

        newRoot.right = root;
        root.left = temp;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }


    private Node<T> rotateLeft(Node<T> root) {
        Node<T> newRoot = root.right;
        Node<T> temp = newRoot.left;

        newRoot.left = root;
        root.right = temp;

        updateHeight(root);
        updateHeight(newRoot);

        return newRoot;
    }

}
