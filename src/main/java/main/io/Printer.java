package main.io;

import main.tree.BinarySearchTree;
import main.tree.Node;

public class Printer {
    public static <T extends String> void print(BinarySearchTree<T> tree) {
        print("", tree.getRoot(), false);
    }

    private static <T extends String> void print(String prefix, Node<T> node, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "├── " : "└── ") + node.key);
            print(prefix + (isLeft ? "│   " : "    "), node.left, true);
            print(prefix + (isLeft ? "│   " : "    "), node.right, false);
        }
    }
}
