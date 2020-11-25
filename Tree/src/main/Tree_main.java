package main;

import ru.oop.nikolenko.array_list.my_array_list.MyArrayList;
import tree.Tree;
import java.util.Arrays;
import java.util.function.UnaryOperator;

public class Tree_main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>(50);

        Integer[] array1 = new Integer[]{30, 12, 2, 80, 69, 97, 12};
        MyArrayList<Integer> list1 = new MyArrayList<>(array1);

        tree.addAll(list1);
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Корень: " + tree.getRootValue());

        System.out.println("Добавили элемент \"78\":");
        tree.add(78);
        System.out.println(tree);

        System.out.println("Добавили элемент \"97\":");
        tree.add(97);
        System.out.println(tree);

        System.out.println("Добавили элемент \"85\" и \"35\":");
        tree.add(85);
        tree.add(35);
        System.out.println(tree);

        System.out.println("Удалили элемент \"35\": " + tree.remove(35));
        System.out.println("Удалили элемент \"35\": " + tree.remove(35));
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Добавили элемент \"35\" и \"40\":");
        tree.add(35);
        tree.add(40);
        System.out.println(tree);

        System.out.println("Удалили элемент \"50\": " + tree.remove(50));
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Удалили элемент \"30\": " + tree.remove(30));
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Удалили элемент \"12\": " + tree.remove(12));
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Количество элементов: " + tree.size());

        System.out.println("Дерево включает \"12\": " + tree.contains(12));
        System.out.println("Дерево включает \"71\": " + tree.contains(71));

        Object[] array2 = tree.toArray();
        System.out.println(Arrays.toString(array2));

        UnaryOperator<Integer> uo = z -> z + 2;
        tree.replaceAll(uo);
        System.out.println("Дерево:");
        System.out.println(tree);

        System.out.println("Корень: " + tree.getRootValue());
    }
}