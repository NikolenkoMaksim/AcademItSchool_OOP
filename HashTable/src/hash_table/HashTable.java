package hash_table;

import java.util.*;

public class HashTable<T> implements Collection<T> {
    private ArrayList<T>[] lists;
    private int size;
    private int modCount;
    private final int DEFAULT_LENGTH = 10;

    public HashTable() {
        lists = (ArrayList<T>[]) new ArrayList<?>[DEFAULT_LENGTH];
    }

    public HashTable(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("length can't be <= 0");
        }

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
        return lists[Math.abs(o.hashCode() % lists.length)].contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return new HashTableIterator();
    }

    private class HashTableIterator implements Iterator<T> {
        private int count = -1;
        private int currentIndex = 0;
        private int currentList = 0;
        private final int DEFAULT_MOD = modCount;

        private void checkMod() {
            if (DEFAULT_MOD != modCount) {
                throw new ConcurrentModificationException("list was changed");
            }
        }

        public boolean hasNext() {
            checkMod();

            return count + 1 < size;
        }

        public T next() {
            if (count + 1 >= size) {
                throw new NoSuchElementException();
            }

            checkMod();

            while (lists[currentList].isEmpty() || (currentList + 1) == lists[currentList].size()) {
                currentList++;
            }

            ++currentIndex;
            ++count;

            return lists[currentList].get(currentIndex);
        }
    }

    @Override
    public T[][] toArray() {
        Object[][] array = new Object[lists.length][];

        for (int i = 0; i < lists.length; i++) {
            array[i] = Arrays.copyOf(lists[i].toArray(), size);
        }

        return (T[][]) array;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        if (a == null) {
            throw new NullPointerException("a can't be null");
        }

        a = Arrays.copyOf(a, Math.max(size, a.length));
        int i = 0;

        for (T e : this) {
            a[i] = (T1) e;
            i++;
        }

        while (i < a.length) {
            a[i] = null;
            i++;
        }

        return a;
    }

    @Override
    public boolean add(T element) {
        int listNumber = Math.abs(element.hashCode() % lists.length);

        checkList(listNumber);

        boolean isAdded = lists[listNumber].add(element);

        if (isAdded) {
            ++size;
            ++modCount;
        }

        return isAdded;
    }

    private void checkList(int listNumber) {
        if (lists[listNumber] == null) {
            lists[listNumber] = new ArrayList<>();
        }
    }

    @Override
    public boolean remove(Object o) {
        boolean isRemoved = lists[Math.abs(o.hashCode() % lists.length)].remove(o);

        if (isRemoved) {
            --size;
            ++modCount;
        }

        return isRemoved;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        for (Object e : c) {
            if (!lists[Math.abs(e.hashCode() % lists.length)].contains(e)) {
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

        for (T e : c) {
            int listNumber = Math.abs(e.hashCode() % lists.length);

            checkList(listNumber);

            if (!lists[listNumber].add(e)) {
                return false;
            }

            ++size;
        }

        ++modCount;

        return true;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean isRemoved = false;

        for (Object e : c) {
            if (remove(e)) {
                isRemoved = true;
            }
            ;
        }

        return isRemoved;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        if (c == null) {
            return false;
        }

        boolean isRetained = false;

        for (T e : this) {
            if (c.contains(e)) {
                remove(e);
                isRetained = true;
            }
        }

        return isRetained;
    }

    @Override
    public void clear() {
        lists = (ArrayList<T>[]) new ArrayList<?>[lists.length];
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

        HashTable<T> h = (HashTable<T>) obj;

        return Arrays.equals(lists, h.lists);
    }
}
