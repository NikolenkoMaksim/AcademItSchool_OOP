package singly_list_main;

import singly_lincked_list.SinglyLinkedList;

public class SinglyListMain {
    public static void main(String[] args) {
        System.out.println("Проверка конструктора. Список 1:");
        Integer[] data = new Integer[]{0, null, 2, 3, 4};
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(data);
        System.out.println(list1);

        System.out.println("Вывод элемента по индексу 2:");
        System.out.println(list1.getIndexItemData(2));

        System.out.println("Вывод первого элемента:");
        System.out.println(list1.getHeadData());

        System.out.println("Замена элемента по индексу 1 на 1:");
        System.out.println(list1.setIndexItemData(1, 1));
        System.out.println(list1);

        System.out.println("Удаление элемента по индексу 1:");
        System.out.println(list1.deleteIndexItem(1));
        System.out.println(list1);

        System.out.println("Вставить -1 в начало списка:");
        list1.addToHead(-1);
        System.out.println(list1);

        System.out.println("Удаление элемента по индексу 0:");
        System.out.println(list1.deleteIndexItem(0));
        System.out.println(list1);

        System.out.println("Вставить 1 по индексу 1:");
        list1.addIndexItem(1, 1);
        System.out.println(list1);

        System.out.println("Вставить 2 по индексу 1:");
        list1.addIndexItem(1, 2);
        System.out.println(list1);

        System.out.println("Вставить 5 по индексу 0:");
        list1.addIndexItem(0, 5);
        System.out.println(list1);

        System.out.println("Удаление значений 4, 9, 5, 2:");
        System.out.println(list1.deleteValue(4));
        System.out.println(list1.deleteValue(9));
        System.out.println(list1.deleteValue(5));
        System.out.println(list1.deleteValue(2));
        System.out.println(list1);

        System.out.println("Длина списка 1:");
        System.out.println(list1.getLength());

        System.out.println("Список 1 скопировали. Список 2:");
        SinglyLinkedList<Integer> list2 = list1.getCopy();
        System.out.println(list2);

        System.out.println("Длина списка 2:");
        System.out.println(list2.getLength());

        System.out.println("Список 1 развернули:");
        list1.invert();
        System.out.println(list1);

        System.out.println("Список 2:");
        System.out.println(list2);

        System.out.println("Вставить значение 5 по индексу 1 в список 2:");
        list2.setIndexItemData(1, 5);
        System.out.println(list2);

        System.out.println("Список 1:");
        System.out.println(list1);

        System.out.println("Список 3:");
        SinglyLinkedList<String> list3 = new SinglyLinkedList<>(new String[]{"Hello,", "it", "is", "me"});
        System.out.println(list3);
    }
}