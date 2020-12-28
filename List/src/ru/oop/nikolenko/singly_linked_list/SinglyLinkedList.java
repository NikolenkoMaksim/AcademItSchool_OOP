package ru.oop.nikolenko.singly_linked_list;

import java.util.NoSuchElementException;
import java.util.Objects;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(T[] array) {
        if (array.length == 0) {
            return;
        }

        ListItem<T> previousItem = new ListItem<>(array[array.length - 1]);

        for (int i = array.length - 2; i >= 0; i--) {
            previousItem = new ListItem<>(array[i], previousItem);
        }

        head = previousItem;
        length = array.length;
    }

    public SinglyLinkedList(T data) {
        head = new ListItem<>(data);
        length = 1;
    }

    public int getLength() {
        return length;
    }

    private void checkIndex(int index) {
        if (index >= length) {
            throw new IndexOutOfBoundsException("index value " + index + " >= list length (" + length + ")");
        }

        if (index < 0) {
            throw new IndexOutOfBoundsException("index value " + index + " < 0");
        }
    }

    private ListItem<T> getItem(int index) {
        checkIndex(index);

        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        ListItem<T> next = head;

        while (next != null) {
            stringBuilder.append(next.getData()).append(", ");
            next = next.getNext();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    private void checkHead() {
        if (head == null) {
            throw new NoSuchElementException("list is empty");
        }
    }

    public T getFirst() {
        checkHead();

        return head.getData();
    }

    public T getData(int index) {
        checkIndex(index);

        return getItem(index).getData();
    }

    public T setData(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getItem(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T deleteFirst() {
        checkHead();

        T data = head.getData();
        head = head.getNext();
        --length;

        return data;
    }

    public T deleteByIndex(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        }

        ListItem<T> item = getItem(index - 1);
        T data = item.getNext().getData();
        item.setNext(item.getNext().getNext());
        --length;

        return data;
    }

    public void addFirst(T data) {
        head = new ListItem<>(data, head);
        ++length;
    }

    public void add(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            addFirst(data);
            return;
        }

        ListItem<T> item = getItem(index - 1);
        item.setNext(new ListItem<>(data, item.getNext()));
        ++length;
    }

    public boolean deleteByData(T data) {
        if (length == 0) {
            return false;
        }

        if (Objects.equals(head.getData(), data)) {
            deleteFirst();
            return true;
        }

        for (ListItem<T> item = head; item.getNext() != null; item = item.getNext()) {
            if (Objects.equals(item.getNext().getData(), data)) {
                item.setNext(item.getNext().getNext());
                --length;
                return true;
            }
        }

        return false;
    }

    public void invert() {
        if (head == null) {
            return;
        }

        ListItem<T> previousItem = head;
        ListItem<T> nextItem = head.getNext();
        head.setNext(null);

        ListItem<T> currentItem = nextItem;

        while (currentItem != null) {
            nextItem = currentItem.getNext();
            currentItem.setNext(previousItem);
            previousItem = currentItem;
            currentItem = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> getCopy() {
        if (head == null) {
            return new SinglyLinkedList<>();
        }

        SinglyLinkedList<T> newList = new SinglyLinkedList<>(head.getData());
        ListItem<T> previousItem = newList.head;
        newList.length = length;

        for (ListItem<T> item = head.getNext(); item != null; item = item.getNext()) {
            previousItem.setNext(new ListItem<>(item.getData()));
            previousItem = previousItem.getNext();
        }

        return newList;
    }
}
