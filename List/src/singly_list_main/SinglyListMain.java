package singly_list_main;

import singly_lincked_list.SinglyLinkedList;

public class SinglyListMain {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{0, 1, 2, 3, 4};
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<Integer>(data);
        System.out.println(list1);
    }
}
