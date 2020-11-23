package ru.oop.nikolenko.array_list.my_array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int modCount;

    public MyArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(T[] items) {
        if (items == null) {
            this.items = (T[]) new Object[DEFAULT_CAPACITY];
        } else {
            this.items = Arrays.copyOf(items, items.length);
            size = items.length;
        }
    }

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        items = (T[]) new Object[capacity];
    }

    public MyArrayList(int capacity, T[] items) {
        if (items == null) {
            checkCapacity(capacity);

            this.items = (T[]) new Object[capacity];
        } else {
            this.items = Arrays.copyOf(items, Math.max(items.length, capacity));
            size = items.length;
        }
    }

    private void checkCapacity(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity can't be <= 0");
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
        return !(indexOf(o) == -1);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int DEFAULT_MOD = modCount;

        private void checkMod() {
            if (DEFAULT_MOD != modCount) {
                throw new ConcurrentModificationException("list was changed");
            }
        }

        public boolean hasNext() {
            checkMod();

            return currentIndex + 1 < size;
        }

        public T next() {
            checkMod();

            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException();
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
        if (a == null) {
            throw new NullPointerException("a can't be null");
        }

        a = (T1[]) Arrays.copyOf(items, Math.max(size, a.length), a.getClass());

        for (int i = size; i < a.length; i++) {
            a[i] = null;
        }

        return a;
    }

    @Override
    public boolean add(T data) {
        checkSize();

        items[size] = data;
        ++size;
        ++modCount;

        return true;
    }

    private void checkSize() {
        if (size == items.length) {
            ensureCapacity(2 * size);
        }
    }

    public void ensureCapacity(int desiredCapacity) {
        checkCapacity(desiredCapacity);

        if (items.length < desiredCapacity) {
            items = Arrays.copyOf(items, desiredCapacity);
        }
    }

    public void trimToSize() {
        items = Arrays.copyOf(items, size);
    }

    @Override
    public boolean remove(Object o) {
        int index = indexOf(o);

        if (index != -1) {
            if (index <= size - 1) {
                System.arraycopy(items, index + 1, items, index, size - 1 - index);
            }

            items[size] = null;
            --size;
            ++modCount;

            return true;
        }

        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        for (Object e : c) {
            if (indexOf(e) == -1) {
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            return false;
        }

        ensureCapacity(size + c.size());

        for (T e : c) {
            items[size] = e;
            ++size;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        if (c == null) {
            return false;
        }

        if (index >= size) {
            return false;
        }

        ensureCapacity(items.length + c.size());
        System.arraycopy(items, index, items, index + c.size(), size - index);

        int index2 = index;

        for (T e : c) {
            items[index2] = e;
            index2++;
        }

        size += c.size();
        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
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
        if (c == null) {
            return false;
        }

        boolean isRetain = false;

        for (int i = size - 1; i >= 0; i--) {
            if (!c.contains(items[i])) {
                remove(i);
                isRetain = true;
            }
        }

        return isRetain;
    }


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
            throw new IllegalArgumentException("index can't be < 0");
        }

        if (index >= size) {
            throw new IllegalArgumentException("index can't be >= size");
        }
    }

    @Override
    public T get(int index) {
        checkIndex(index);

        return items[index];
    }

    @Override
    public T set(int index, T element) {
        checkIndex(index);

        T data = items[index];
        items[index] = element;

        return data;
    }

    @Override
    public void add(int index, T element) {
        checkIndex(index);

        checkSize();

        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = element;

        ++size;
        ++modCount;
    }

    @Override
    public T remove(int index) {
        checkIndex(index);

        T element = items[index];

        if (index != size - 1) {
            System.arraycopy(items, index + 1, items, index, size - 1 - index);
        }

        items[size - 1] = null;
        --size;
        ++modCount;

        return element;
    }

    @Override
    public int indexOf(Object o) {
        for (int i = 0; i < size; i++) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        for (int i = size - 1; i >= 0; i--) {
            if (items[i].equals(o)) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(items);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }

        MyArrayList m = (MyArrayList) obj;

        return Arrays.equals(items, m.items);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("{");

        if (size > 0) {
            for (int i = 0; i < size; i++) {
                stringBuilder.append(items[i]).append(", ");
            }

            stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");
        } else {
            stringBuilder.append("}");
        }

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
}
