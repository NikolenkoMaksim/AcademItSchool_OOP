package ru.oop.nikolenko.my_array_list_main;

import ru.oop.nikolenko.my_array_list.MyArrayList;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        MyArrayList<Integer> list1 = new MyArrayList<>();
        System.out.println("Коллекция 1:");
        System.out.println(list1);

        Integer[] array1 = {1, 2, 4, 5, 7};
        MyArrayList<Integer> list2 = new MyArrayList<>(array1);
        System.out.println("Коллекция 2:");
        System.out.println(list2);

        MyArrayList<Integer> list3 = new MyArrayList<>(0);
        System.out.println("Коллекция 3:");
        System.out.println(list3);

        MyArrayList<Integer> list4 = new MyArrayList<>(7, array1);
        System.out.println("Коллекция 4:");
        System.out.println(list4);

        System.out.println("К коллекции 4 применили метод \"trimToSize()\":");
        list4.trimToSize();
        System.out.println(list4);

        list2.ensureCapacity(8);
        System.out.println("К коллекции 2 применили метод \"ensureCapacity(8). Коллекция 2\":");
        System.out.println(list2);

        System.out.println("Размер коллекции 2:");
        System.out.println(list2.size());
        System.out.println("К коллекции 2 применили метод \"isEmpty()\":");
        System.out.println(list2.isEmpty());
        System.out.println("К коллекции 1 применили метод \"isEmpty()\":");
        System.out.println(list1.isEmpty());
        System.out.println("К коллекции 2 применили метод \"indexOf(2)\":");
        System.out.println(list2.indexOf(2));
        System.out.println("К коллекции 2 применили метод \"indexOf(8)\":");
        System.out.println(list2.indexOf(8));
        System.out.println("К коллекции 2 применили метод \"contains(2)\":");
        System.out.println(list2.contains(2));

        MyArrayList<Integer> list5 = new MyArrayList<>(7, array1);
        System.out.println("Коллекция 5:");
        System.out.println(list5);
        Integer[] array2 = list5.toArray();
        System.out.println("К коллекции 5 применили метод \"toArray()\":");
        System.out.println(Arrays.toString(array2));

        Object[] array3 = {8, 9, 10, 47, 78, 7, 8};
        System.out.println("array3:");
        System.out.println(Arrays.toString(array3));
        array3 = list5.toArray(array3);
        System.out.println("К коллекции 5 применили метод \"toArray(array3)\":");
        System.out.println(Arrays.toString(array3));

        System.out.println("К коллекции 1 применили метод \"add(1)\":");
        System.out.println(list1.add(1));
        System.out.println("Коллекция 1:");
        System.out.println(list1);

        System.out.println("К коллекции 1 применили метод \"addAll(list2)\" два раза:");
        System.out.println(list1.addAll(list2));
        System.out.println(list1.addAll(list2));
        System.out.println(list1);

        System.out.println("К коллекции 1 применили метод \"containsAll(list2)\":");
        System.out.println(list1.containsAll(list2));
        list2.add(6);
        System.out.println("К коллекции 2 применили метод \"add(6)\":");
        System.out.println(list2);
        System.out.println("К коллекции 1 применили метод \"containsAll(list2)\":");
        System.out.println(list1.containsAll(list2));
        System.out.println("К коллекции 1 применили метод \"contains(3)\":");
        System.out.println(list1.contains(3));
        System.out.println("К коллекции 1 применили метод \"contains(7)\":");
        System.out.println(list1.contains(7));

        System.out.println("К коллекции 2 применили метод \"retainAll(list1)\":");
        System.out.println(list2.retainAll(list1));
        System.out.println("Коллекция 1:");
        System.out.println(list1);
        System.out.println("Коллекция 2:");
        System.out.println(list2);
        System.out.println("К коллекции 2 применили метод \"retainAll(list1)\":");
        System.out.println(list2.retainAll(list1));

        System.out.println("К коллекции 1 применили метод \"remove(4)\":");
        System.out.println(list1.remove(4));
        System.out.println("Коллекция 1:");
        System.out.println(list1);

        System.out.println("К коллекции 1 применили метод \"removeAll(list4)\":");
        System.out.println(list1.removeAll(list4));
        System.out.println("Коллекция 1:");
        System.out.println(list1);
        System.out.println("Коллекция 4:");
        System.out.println(list4);

        Integer[] array4 = {11, 2, 6, 9, 7};
        MyArrayList<Integer> list6 = new MyArrayList<>(array4);
        System.out.println("Коллекция 6:");
        System.out.println(list6);
        System.out.println("К коллекции 6 применили метод \"addAll(2, list4)\":");
        System.out.println(list6.addAll(2, list4));
        System.out.println("Коллекция 6:");
        System.out.println(list6);

        System.out.println("К коллекции 6 применили метод \"removeAll(list4)\":");
        System.out.println(list6.removeAll(list4));
        System.out.println("Коллекция 6:");
        System.out.println(list6);

        list6.clear();
        System.out.println("К коллекции 6 применили метод \"clear()\".");
        System.out.println("Коллекция 6:");
        System.out.println(list6);

        MyArrayList<Integer> list7 = new MyArrayList<>(array4);
        System.out.println("Коллекция 7:");
        System.out.println(list7);

        System.out.println("Элемент по индексу 2 в колекции 7: " + list7.get(2));
        list7.set(2, 7);
        System.out.println("В коллекции 7 второй элемент заменили на 7:");
        System.out.println(list7);
        list7.add(2, 8);
        System.out.println("В коллекцию 7 добавили 8 по индексу 2:");
        System.out.println(list7);
        list7.remove(1);
        System.out.println("В коллекции 7 удалили элемент по индексу 1:");
        System.out.println(list7);
        System.out.println("Последний элемент со значением 7 имеет индекс " + list7.lastIndexOf(7));

        System.out.println("Хешкод коллекции 7: " + list7.hashCode());

        MyArrayList<Integer> list8 = new MyArrayList<>(list7.toArray());
        System.out.println("Коллекция 8:");
        System.out.println(list8);

        System.out.println("Коллекции 7 и 8 эквиваленты: " + list7.equals(list8));
        System.out.println("Коллекции 7 и 1 эквиваленты: " + list7.equals(list1));
        list7.trimToSize();
        System.out.println("Размер колекции 7 сократили до количества элементов");
        System.out.println("Коллекции 7 и 8 эквиваленты:" + list7.equals(list8));

        int sum = 0;

        for (Integer e : list7) {
            sum += e;
        }

        System.out.println("Сумма элементов коллекции 7 равна " + sum);

        list7.add(2, null);
        System.out.println("К коллекции 7 применили метод \"add(2, null)\":");
        System.out.println(list7);
        list7.add(5, null);
        System.out.println("К коллекции 7 применили метод \"add(5, null)\":");
        System.out.println(list7);
        System.out.println("К коллекции 7 применили метод \"indexOf(null)\":");
        System.out.println(list7.indexOf(null));
        System.out.println("К коллекции 7 применили метод \"lastIndexOf(null)\":");
        System.out.println(list7.lastIndexOf(null));
        System.out.println("К коллекции 7 применили метод \"indexOf(7)\":");
        System.out.println(list7.indexOf(7));
        System.out.println("К коллекции 7 применили метод \"lastIndexOf(9)\":");
        System.out.println(list7.lastIndexOf(9));
        System.out.println("К коллекции 7 применили метод \"contains(null)\":");
        System.out.println(list7.contains(null));
        System.out.println("К коллекции 7 применили метод \"contains(7)\":");
        System.out.println(list7.contains(7));
    }
}
