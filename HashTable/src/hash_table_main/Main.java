package hash_table_main;

import hash_table.HashTable;
import ru.oop.nikolenko.array_list.my_array_list.MyArrayList;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        HashTable<Double> table1 = new HashTable<>(7);
        HashTable<Double> table2 = new HashTable<>();

        Double[] array1 = {1.1, 2.2, 8.2, 7.6, 8.6, 4.4, 1.1, 0.11, 0.999, 1e2};
        MyArrayList<Double> list1 = new MyArrayList<Double>(array1);
        System.out.println(list1);

        table1.addAll(list1);
        System.out.println(table1);
        table2.addAll(list1);
        System.out.println(table2);
    }
}
