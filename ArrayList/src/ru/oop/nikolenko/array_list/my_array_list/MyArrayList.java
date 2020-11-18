package ru.oop.nikolenko.array_list.my_array_list;

import java.util.*;
import java.util.function.UnaryOperator;

public class MyArrayList<T> implements List<T> {
    private T[] items;
    private int size;
    private final int DEFAULT_CAPACITY = 10;
    private int modCount = 0;

    public MyArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = DEFAULT_CAPACITY;
    }

    public MyArrayList(T[] items) {
        if (items == null) {
            items = (T[]) new Object[DEFAULT_CAPACITY];
            size = DEFAULT_CAPACITY;
        }

        this.items = items;
        size = items.length;
    }

    public MyArrayList(int capacity) {
        checkCapacity(capacity);

        items = (T[]) new Object[capacity];
        size = capacity;
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
        indexOf(o);

        return (indexOf(o) == -1);
    }

    @Override
    public Iterator<T> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<T> {
        private int currentIndex = -1;
        private final int DEFAULT_MOD = modCount;

        public boolean hasNext() {
            return currentIndex + 1 < size;
        }

        public T next() {
            if (currentIndex + 1 >= size) {
                throw new NoSuchElementException();
            }

            if (DEFAULT_MOD != modCount) {
                throw new ConcurrentModificationException("list was changed");
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
    public boolean addAll(Collection<? extends T> c) {
        if (c == null) {
            return false;
        }

        ensureCapacity(size + c.size());

        T[] array = (T[]) c.toArray();

        System.arraycopy(array, 0, items, size, size + array.length);

        size += array.length;
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

        ensureCapacity(size + c.size());

        T[] array = (T[]) c.toArray();

        System.arraycopy(items, index, items, size, size + index);
        System.arraycopy(array, 0, items, index, index + array.length);

        size += array.length;
        ++modCount;

        return true;
    }


    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        for(int i = 0; i < size; i++) {
            operator.apply(items[i]);
        }
    }

    @Override
    public void sort(Comparator<? super T> c) {
        Arrays.sort(items, c);
    }

    @Override
    public void clear() {
        for(int i = 0; i < size; i++) {
            items[i] = null;
            size = 0;
            ++modCount;
        }
    }

    public void checkIndex(int index) {
        if(index < 0) {
            throw new IllegalArgumentException("index can't be < 0");
        }

        if(index >= size) {
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
    public void add(int index, Object element) {

    }

    @Override
    public Object remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator spliterator() {
        return null;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }
}
