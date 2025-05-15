package main.controller;

import main.io.Printer;
import main.tree.AVLTree;
import main.tree.BinarySearchTree;
import main.tree.Tree;

import java.util.Random;
import java.util.Scanner;

public class Controller {
    private final Scanner scanner = new Scanner(System.in);
    private final Random random = new Random();
    private final BinarySearchTree<String> bst = new BinarySearchTree<>();
    private final AVLTree<String> avl = new AVLTree<>();

    public void run() {
        while (true) {
            System.out.print("""
                Меню:
                1. Операції з BST
                2. Операції з AVL
                3. Вимірювання часу
                0. Вихід
                >>\s""");
            switch (scanner.nextInt()) {
                case 1 -> bstOperations();
                case 2 -> avlOperations();
                case 3 -> measureTime();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private void bstOperations() {
        while (true) {
            System.out.print("""
                BST:
                1. Додати елемент
                2. Видалити елемент
                3. Пошук елемента
                4. Вивести дерево
                5. Додати зразки
                6. Висота дерева
                7. Перевірка на збалансованість
                0. Назад
                >>\s""");
            switch (scanner.nextInt()) {
                case 1 -> System.out.println(bst.insert(scanner.next()) ? "Додано" : "Вже є");
                case 2 -> bst.remove(scanner.next());
                case 3 -> System.out.println(bst.contains(scanner.next()) ? "Так" : "Ні");
                case 4 -> Printer.print(bst);
                case 5 -> insertSamples(bst);
                case 6 -> System.out.println("Висота: " + bst.height());
                case 7 -> System.out.println(bst.isBalanced() ? "Збалансоване" : "Ні");
                case 0 -> { return; }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private void avlOperations() {
        while (true) {
            System.out.print("""
                AVL:
                1. Додати елемент
                2. Видалити елемент
                3. Пошук елемента
                4. Вивести дерево
                5. Додати зразки
                6. Висота дерева
                0. Назад
                >>\s""");
            switch (scanner.nextInt()) {
                case 1 -> System.out.println(avl.insert(scanner.next()) ? "Додано" : "Вже є");
                case 2 -> avl.remove(scanner.next());
                case 3 -> System.out.println(avl.contains(scanner.next()) ? "Так" : "Ні");
                case 4 -> Printer.print(avl);
                case 5 -> insertSamples(avl);
                case 6 -> System.out.println("Висота: " + avl.height());
                case 0 -> { return; }
                default -> System.out.println("Невірний вибір.");
            }
        }
    }

    private void insertSamples(Tree<String> tree) {
        tree.insert("bandura");
        tree.insert("guitar");
        tree.insert("trembita");
        tree.insert("flute");
        tree.insert("double bass");
        tree.insert("cymbals");
    }

    private void measureTime() {
        System.out.println("Вимірювання часу вставки у BST та AVL:");

        measureAndInsert(bst, "BST", 100_000);
        measureAndInsert(avl, "AVL", 100_000);

        measureAndInsert(bst, "BST", 1_000_000);
        measureAndInsert(avl, "AVL", 1_000_000);
    }

    private void measureAndInsert(Tree<String> tree, String name, int count) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            tree.insert(String.valueOf(random.nextInt()));
        }
        long end = System.currentTimeMillis();
        System.out.println(name + ": час вставки " + count + " елементів: " + (end - start) + " мс");
    }

}