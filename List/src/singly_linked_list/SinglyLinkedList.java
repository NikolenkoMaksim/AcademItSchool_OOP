package singly_linked_list;

public class SinglyLinkedList<T> {
    private ListItem<T> head;
    private int length;

    public SinglyLinkedList() {
    }

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
            return null;
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
            throw new NullPointerException("list is empty");
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

    public T delete(int index) {
        checkIndex(index);

        if (index == 0) {
            return deleteFirst();
        } else {
            ListItem<T> item = getItem(index - 1);
            T data = item.getNext().getData();
            item.setNext(item.getNext().getNext());
            --length;

            return data;
        }
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

    public boolean deleteData(T value) {
        checkHead();

        if (head.getData().equals(value)) {
            deleteFirst();
            return true;
        }

        for (ListItem<T> e = head; e.getNext() != null; e = e.getNext()) {
            if (e.getNext().getData() == value) {
                e.setNext(e.getNext().getNext());
                --length;

                return true;
            }
        }

        return false;
    }

    public void invert() {
        checkHead();

        ListItem<T> previousItem = head;
        ListItem<T> nextItem = head.getNext();
        head.setNext(null);

        ListItem<T> e = nextItem;

        while (e != null) {
            nextItem = e.getNext();
            e.setNext(previousItem);
            previousItem = e;
            e = nextItem;
        }

        head = previousItem;
    }

    public SinglyLinkedList<T> getCopy() {
        checkHead();

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
