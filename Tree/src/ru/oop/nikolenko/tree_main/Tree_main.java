package ru.oop.nikolenko.tree_main;

import ru.oop.nikolenko.my_array_list.MyArrayList;
import ru.oop.nikolenko.tree.Tree;

import java.util.Arrays;
import java.util.function.UnaryOperator;

public class Tree_main {
    public static void main(String[] args) {
        Tree<Integer> tree1 = new Tree<>(50);

        Integer[] array1 = new Integer[]{30, 12, 2, 80, 69, 97, 12};
        MyArrayList<Integer> list1 = new MyArrayList<>(array1);

        tree1.addAll(list1);
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Значение коряня дерева 1: " + tree1.getRootData());

        System.out.println("Добавили элемент \"78\"  в дерево 1:");
        tree1.add(78);
        System.out.println(tree1);

        System.out.println("Добавили элемент \"97\" в дерево 1:");
        tree1.add(97);
        System.out.println(tree1);

        System.out.println("Добавили элемент \"85\" и \"35\" в дерево 1:");
        tree1.add(85);
        tree1.add(35);
        System.out.println(tree1);

        System.out.println("Удалили элемент \"35\" из дерева 1: " + tree1.remove(35));
        System.out.println("Удалили элемент \"35\" из дерева 1: " + tree1.remove(35));
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Добавили элемент \"35\" и \"40\" в дерево 1:");
        tree1.add(35);
        tree1.add(40);
        System.out.println(tree1);

        System.out.println("Удалили элемент \"50\": " + tree1.remove(50));
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Удалили элемент \"30\": " + tree1.remove(30));
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Удалили элемент \"12\": " + tree1.remove(12));
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Количество элементов дерева 1: " + tree1.size());

        System.out.println("Дерево 1 включает \"12\": " + tree1.contains(12));
        System.out.println("Дерево 1 включает \"71\": " + tree1.contains(71));

        System.out.println("Массив объектов дерева 1:");
        Object[] array2 = tree1.toArray();
        System.out.println(Arrays.toString(array2));

        System.out.println("К каждому объекту дерева 1 добавили 2:");
        UnaryOperator<Integer> uo = z -> z + 2;
        tree1.replaceAll(uo);
        System.out.println("Дерево 1:");
        System.out.println(tree1);

        System.out.println("Значение корня дерева 1: " + tree1.getRootData());

        Tree<Integer> tree2 = new Tree<>();
        System.out.println("Дерево 2:");
        System.out.println(tree2);

        System.out.println("Дерево 2 включает \"12\": " + tree2.contains(12));
        System.out.println("Удалить \"12\" из дерева 2: " + tree2.remove(1));

        System.out.println("Добавили элемент \"97\" в дерево 2:");
        tree2.add(97);
        System.out.println(tree2);

        System.out.println("Удалили элемент \"97\" из дерева 2: " + tree2.remove(97));
        System.out.println("Дерево 2:");
        System.out.println(tree2);

        System.out.println("Добавили элементы \"50\", \"40\" и \"60\"  в дерево 2:");
        tree2.add(50);
        tree2.add(40);
        tree2.add(60);
        System.out.println(tree2);

        System.out.println("Удалили элемент \"50\" из дерева 2: " + tree2.remove(50));
        System.out.println("Дерево 2:");
        System.out.println(tree2);

        System.out.println("Удалили элемент \"60\" из дерева 2: " + tree2.remove(60));
        System.out.println("Дерево 2:");
        System.out.println(tree2);

        System.out.println("Удалили элемент \"40\" из дерева 2: " + tree2.remove(40));
        System.out.println("Дерево 2:");
        System.out.println(tree2);

        System.out.println("Массив объектов дерева 2:");
        Object[] array3 = tree2.toArray();
        System.out.println(Arrays.toString(array3));
    }
}