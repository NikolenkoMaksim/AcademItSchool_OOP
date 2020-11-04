package singly_list_main;

import singly_lincked_list.SinglyLinkedList;

public class SinglyListMain {
    public static void main(String[] args) {
        Integer[] data = new Integer[]{0, null, 2, 3, 4};
        SinglyLinkedList<Integer> list1 = new SinglyLinkedList<>(data);
        System.out.println(list1);

        System.out.println(list1.getIndexItemData(2));

        System.out.println(list1.getHeadData());

        System.out.println(list1.setIndexItemData(1, 1));
        System.out.println(list1);

        System.out.println(list1.deleteIndexItem(1));
        System.out.println(list1);

        list1.addToHead(-1);
        System.out.println(list1);

        System.out.println(list1.deleteIndexItem(0));
        System.out.println(list1);

        list1.addIndexItem(1, 1);
        System.out.println(list1);

        list1.addIndexItem(1, 2);
        System.out.println(list1);

        list1.addIndexItem(0, 5);
        System.out.println(list1);

        System.out.println(list1.deleteValue(4));
        System.out.println(list1.deleteValue(9));
        System.out.println(list1.deleteValue(5));
        System.out.println(list1.deleteValue(2));
        System.out.println(list1);
        System.out.println(list1.getLength());

        SinglyLinkedList<Integer> list2 = list1.getCopy();
        System.out.println(list2);
        System.out.println(list1.getLength());

        list1.invert();
        System.out.println(list1);
        System.out.println(list2);

        list2.setIndexItemData(1, 5);
        System.out.println(list1);
        System.out.println(list2);

        SinglyLinkedList<String> list3 = new SinglyLinkedList<>(new String[]{"Hello,", "it", "is", "me"});
        System.out.println(list3);
    }
}
