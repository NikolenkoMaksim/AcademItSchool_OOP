package ru.oop.nikolenko.hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    final static int DEFAULT_LENGTH = 10;

    private ArrayList<T>[] lists;
    private int size;
    private int modCount;

    public HashTable() {
        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList<?>[DEFAULT_LENGTH];
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length (" + length + ") can't be <= 0");
        }

        //noinspection unchecked
        lists = (ArrayList<T>[]) new ArrayList<?>[length];
    }

    @Override
    public int size() {
        return size;
    }

    public int getLength() {
        return lists.length;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        int listIndex = getListIndex(o);

        return lists[listIndex] != null && lists[listIndex].contains(o);
    }

    private int getListIndex(Object o) {
        if (o == null) {
            return 0;
        }

        return Math.abs(o.hashCode() % lists.length);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private int globalIndex = -1;
        private int elementIndex = -1;
        private int listIndex = 0;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("list was changed");
            }

            return globalIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("there isn't next element (global Index = " + globalIndex + ", size = " + size + ")");
            }

            while (lists[listIndex] == null || lists[listIndex].isEmpty() || (elementIndex + 1) == lists[listIndex].size()) {
                listIndex++;
                elementIndex = -1;
            }

            ++elementIndex;
            ++globalIndex;

            return lists[listIndex].get(elementIndex);
        }
    }

    public Object[] toArray() {
        Object[] array = new Object[size];

        int i = 0;

        for (T e : this) {
            array[i] = e;
            i++;
        }

        return array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(lists, size, a.getClass());
        }

        int i = 0;

        for (T e : this) {
            //noinspection unchecked
            a[i] = (T1) e;
            i++;
        }

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T element) {
        int listIndex = getListIndex(element);

        createList(listIndex);

        boolean isAdded = lists[listIndex].add(element);

        if (isAdded) {
            ++size;
            ++modCount;
        }

        return isAdded;
    }

    private void createList(int listIndex) {
        if (lists[listIndex] == null) {
            lists[listIndex] = new ArrayList<>();
        }
    }

    @Override
    public boolean remove(Object o) {
        int listIndex = getListIndex(o);

        boolean isRemoved = lists[listIndex].remove(o);

        if (isRemoved) {
            --size;
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c) {
            if (!contains(e)) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T e : c) {
            int listIndex = getListIndex(e);

            createList(listIndex);

            if (!lists[listIndex].add(e)) {
                return false;
            }

            ++size;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        boolean isRemoved = false;
        size = 0;

        for (ArrayList<T> list : lists) {
            if (list.removeAll(c)) {
                isRemoved = true;
            }

            size += list.size();
        }

        if (isRemoved) {
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRetained = false;

        size = 0;

        for (ArrayList<T> list : lists) {
            if (list.retainAll(c)) {
                isRetained = true;
            }
            size += list.size();
        }

        if (isRetained) {
            ++modCount;
        }

        return isRetained;
    }

    @Override
    public void clear() {
        for (ArrayList<T> list : lists) {
            if (list != null) {
                list.clear();
            }
        }

        ++modCount;
        size = 0;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        if (size > 0) {
            for (ArrayList<T> list : lists) {
                stringBuilder.append(list).append(", ");
            }

            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        } else {
            stringBuilder.append("}");
        }

        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        //noinspection unchecked
        HashTable<T> h = (HashTable<T>) obj;

        return Arrays.equals(lists, h.lists);
    }
}
