package singly_lincked_list;

import list_item.ListItem;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList(ListItem<T> head) {
        this.head = head;
        length = 1;
    }

    public SinglyLinkedList(T[] array) {
        ListItem<T> previousItem = new ListItem<T>(array[array.length-1]);

        for(int i = array.length - 2; i >= 0; i--) {
            previousItem = new ListItem<T>(array[i], previousItem);
        }

        head = previousItem;
        length = array.length;
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<T>(data);
        length = 1;
    }

    public ListItem<T> getHead() {
        return head;
    }

    public void setHead(ListItem<T> head) {
        this.head = head;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        ListItem<T> next = head;

        while(next != null) {
            stringBuilder.append(next.getData()).append(", ");
            next = next.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
}
