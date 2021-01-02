package ru.oop.nikolenko.my_array_list;

import java.util.*;

public class MyArrayList<T> implements List<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] items;
    private int size;
    private int modCount;

    public MyArrayList() {
        //noinspection unchecked
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(T[] items) {
        checkItems(items);

        this.items = Arrays.copyOf(items, items.length);
        size = items.length;
    }

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        //noinspection unchecked
        items = (T[]) new Object[capacity];
    }

    public MyArrayList(int capacity, T[] items) {
        checkItems(items);

        this.items = Arrays.copyOf(items, Math.max(items.length, capacity));
        size = items.length;
    }

    private void checkCapacity(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("capacity (" + capacity + ") can't be < 0");
        }
    }

    private void checkItems(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("items can't be null");
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int initialModCount = modCount;

        public boolean hasNext() {
            if (initialModCount != modCount) {
                throw new ConcurrentModificationException("list was changed");
            }

            return currentIndex + 1 < size;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("there isn't next element (current Index = " + currentIndex + ", size = " + size + ")");
            }

            ++currentIndex;
            return items[currentIndex];
        }
    }

    @Override
    public T[] toArray() {
        return Arrays.copyOf(items, size);
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a.length < size) {
            //noinspection unchecked
            return (T1[]) Arrays.copyOf(items, size, a.getClass());
        }

        System.arraycopy(items, 0, a, 0, size);

        if (a.length > size) {
            a[size] = null;
        }

        return a;
    }

    @Override
    public boolean add(T data) {
        add(size, data);

        return true;
    }

    public void ensureCapacity(int desiredCapacity) {
        checkCapacity(desiredCapacity);

        if (items.length < desiredCapacity) {
            items = Arrays.copyOf(items, desiredCapacity);
        }
    }

    public void trimToSize() {
        if (items.length > size) {
            items = Arrays.copyOf(items, size);
        }
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index == -1) {
            return false;
        }

        remove(index);

        return true;
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
        return addAll(size, c);
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        checkIndexForAdd(index);

        ensureCapacity(size + c.size());
        System.arraycopy(items, index, items, index + c.size(), size - index);

        int i = index;

        for (T e : c) {
            items[i] = e;
            i++;
        }

        size += c.size();
        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c.isEmpty()) {
            return false;
        }

        boolean isRemoved = false;

        for (int i = size - 1; i >= 0; i--) {
            if (c.contains(items[i])) {
                remove(i);
                isRemoved = true;
            }
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        boolean isRetained = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRetained = true;
            }
        }

        return isRetained;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }

        size = 0;
        ++modCount;
    }

    private void checkIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index (" + index + ") can't be < 0");
        }

        if (index >= size) {
            throw new IllegalArgumentException("index (" + index + ") can't be >= size (" + size + ")");
        }
    }

    private void checkIndexForAdd(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("index (" + index + ") can't be < 0");
        }

        if (index > size) {
            throw new IllegalArgumentException("index (" + index + ") can't be > size (" + size + ")");
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T data) {
        checkIndex(index);

        T oldData = items[index];
        items[index] = data;

        return oldData;
    }

    @Override
    public void add(int index, T data) {
        checkIndexForAdd(index);

        if (items.length == 0) {
            //noinspection unchecked
            items = (T[]) new Object[DEFAULT_CAPACITY];
        } else if (size == items.length) {
            items = Arrays.copyOf(items, 2 * items.length);
        }

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = data;

        ++size;
        ++modCount;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T removedData = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        items[size - 1] = null;
        --size;
        ++modCount;

        return removedData;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (Objects.equals(items[i], o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int hashCode() {
        final int prime = 13;
        int hash = 1;

        for (int i = 0; i < size; i++) {
            hash = prime * hash + (items[i] != null ? items[i].hashCode() : 0);
        }

        return hash;
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
        MyArrayList<T> list = (MyArrayList<T>) obj;

        if (size != list.size) {
            return false;
        }

        for (int i = 0; i < size; i++) {
            if (!Objects.equals(items[i], list.items[i])) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        for (int i = 0; i < size; i++) {
            stringBuilder.append(items[i]).append(", ");
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    //методы без реаризации
    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    /* Не нужные, но реаризованные методы

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        if (operator == null) {
            throw new IllegalArgumentException("operator can't be null");
        }

        for (int i = 0; i < size; i++) {
            items[i] = operator.apply(items[i]);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        if (c == null) {
            throw new IllegalArgumentException("comparator can't be null");
        }

        Arrays.sort(items, 0, size, c);
    }

   */
}
