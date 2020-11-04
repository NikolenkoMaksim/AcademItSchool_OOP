package singly_lincked_list;

import list_item.ListItem;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList(T[] array) {
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

    private ListItem<T> getIndexItem(int index) {
        ListItem<T> item = head;

        for (int i = 0; i < index; i++) {
            item = item.getNext();
        }

        return item;
    }

    @Override
    public String toString() {
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

    public T getHeadData() {
        return head.getData();
    }

    public T getIndexItemData(int index) {
        checkIndex(index);

        return getIndexItem(index).getData();
    }

    public T setIndexItemData(int index, T data) {
        checkIndex(index);

        ListItem<T> item = getIndexItem(index);
        T oldData = item.getData();
        item.setData(data);

        return oldData;
    }

    public T deleteHead() {
        T data = head.getData();
        head = head.getNext();
        --length;

        return data;
    }

    public T deleteIndexItem(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteHead();
        } else {
            ListItem<T> item = getIndexItem(index - 1);
            T data = item.getNext().getData();
            item.setNext(item.getNext().getNext());
            --length;

            return data;
        }
    }

    public void addToHead(T data) {
        head = new ListItem<>(data, head);
        ++length;
    }

    public void addIndexItem(int index, T data) {
        checkIndex(index);

        if (index == 0) {
            addToHead(data);
        } else {
            ListItem<T> item = getIndexItem(index - 1);
            item.setNext(new ListItem<>(data, item.getNext()));
            ++length;
        }
    }

    public boolean deleteValue(T value) {
        if (head.getData() == value) {
            deleteHead();
            return true;
        }

        for (ListItem<T> i = head; i.getNext() != null; i = i.getNext()) {
            if (i.getNext().getData() == value) {
                i.setNext(i.getNext().getNext());
                --length;

                return true;
            }
        }

        return false;
    }

    public void invert() {
        ListItem<T> previousItem = head;
        ListItem<T> nextItem = head.getNext();
        head.setNext(null);

        ListItem<T> i = nextItem;

        while (i != null) {
            nextItem = i.getNext();
            i.setNext(previousItem);
            previousItem = i;
            i = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> getCopy() {
        SinglyLinkedList<T> newList = new SinglyLinkedList<>(head.getData());
        ListItem<T> previousItem = newList.head;

        for (ListItem<T> i = head.getNext(); i != null; i = i.getNext()) {
            previousItem.setNext(new ListItem<>(i.getData()));
            ++newList.length;
            previousItem = previousItem.getNext();
        }

        return newList;
    }
}
