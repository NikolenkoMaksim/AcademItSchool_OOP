package ru.oop.nikolenko.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private TreeNode<T> root;
    private int size;
    private Comparator<? super T> comparator;

    public Tree() {

    }

    public Tree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public Tree(T data) {
        checkData(data);

        root = new TreeNode<>(data);
        size = 1;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        //noinspection unchecked
        Comparable<T> data3 = (Comparable<T>) data1;

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        return data3.compareTo(data2);
    }

    public T getRootData() {
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }

        return root.getData();
    }

    private void checkData(T data) {
        if (comparator == null) {
            //noinspection unchecked
            Comparable<T> comparableData = (Comparable<T>) data;
        }
    }

    public void add(T data) {
        checkData(data);

        boolean isNotAdded = true;

        if (root == null) {
            root = new TreeNode<>(data);
            ++size;
            return;
        }

        TreeNode<T> currentNode = root;

        while (isNotAdded) {
            if (compare(data, currentNode.getData()) < 0) {
                TreeNode<T> leftNode = currentNode.getLeft();

                if (leftNode != null) {
                    currentNode = leftNode;
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    isNotAdded = false;
                }
            } else {
                TreeNode<T> rightNode = currentNode.getRight();

                if (rightNode != null) {
                    currentNode = rightNode;
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    isNotAdded = false;
                }
            }
        }

        ++size;
    }

    public void addAll(Collection<T> c) {
        if (c == null) {
            throw new IllegalArgumentException("collection can't be null");
        }

        for (T node : c) {
            add(node);
        }
    }

    public boolean contains(T data) {
        if (root == null) {
            return false;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            int comparisonResult = compare(data, currentNode.getData());

            if (comparisonResult == 0) {
                return true;
            }

            if (comparisonResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    return false;
                }
            }
        }
    }

    private TreeNode<T>[] findNodeAndParent(T data) {
        //noinspection unchecked
        TreeNode<T>[] array = (TreeNode<T>[]) new TreeNode<?>[2];
        array[0] = root;
        array[1] = null;

        while (true) {
            int comparisonResult = compare(data, array[0].getData());

            if (comparisonResult == 0) {
                return array;
            }

            if (comparisonResult < 0) {
                if (array[0].getLeft() != null) {
                    array[1] = array[0];
                    array[0] = array[0].getLeft();
                } else {
                    return null;
                }
            } else {
                if (array[0].getRight() != null) {
                    array[1] = array[0];
                    array[0] = array[0].getRight();
                } else {
                    return null;
                }
            }
        }
    }

    private void setNodeRightChildToParent(TreeNode<T> node, TreeNode<T> parent) {
        if (parent.getLeft() == node) {
            parent.setLeft(node.getRight());
            return;
        }

        if (parent.getRight() == node) {
            parent.setRight(node.getRight());
            return;
        }

        throw new IllegalArgumentException("parent hasn't this node");
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        if (size == 1) {
            if (data != root.getData()) {
                return false;
            }

            root = null;
            return true;
        }

        if (compare(root.getData(), data) == 0 && root.getRight() == null) {
            root = root.getLeft();

            return true;
        }

        TreeNode<T>[] array = findNodeAndParent(data);

        if (array == null) {
            return false;
        }

        if (array[0].getRight() == null) {
            setNodeRightChildToParent(array[0], array[1]);
            --size;
            return true;
        }

        TreeNode<T> previousNode = array[0];
        TreeNode<T> lastLeftList = array[0].getRight();

        while (lastLeftList.getLeft() != null) {
            previousNode = lastLeftList;
            lastLeftList = lastLeftList.getLeft();
        }

        array[0].setData(lastLeftList.getData());
        setNodeRightChildToParent(lastLeftList, previousNode);

        --size;

        return true;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        if (root == null) {
            return new Object[0];
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        TreeNode<T> node;

        Object[] array = new Object[size];
        int i = 0;

        while (!stack.isEmpty()) {
            node = stack.getLast();

            array[i] = node.getData();
            ++i;

            stack.removeLast();

            if (node.getRight() != null) {
                stack.addLast(node.getRight());
            }

            if (node.getLeft() != null) {
                stack.addLast(node.getLeft());
            }
        }

        return array;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Deque<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);
        TreeNode<T> node;

        stringBuilder.append("{");

        while (!queue.isEmpty()) {
            node = queue.getFirst();

            stringBuilder.append(node.getData());

            if (node.getLeft() == null && node.getRight() == null) {
                stringBuilder.append(", ");
            } else {
                if (node.getLeft() != null) {
                    queue.addLast(node.getLeft());
                    stringBuilder.append(" (").append(node.getLeft().getData()).append(", ");
                } else {
                    stringBuilder.append(" (-, ");
                }

                if (node.getRight() != null) {
                    queue.addLast(node.getRight());
                    stringBuilder.append(node.getRight().getData()).append("), ");
                } else {
                    stringBuilder.append("-), ");
                }
            }

            queue.removeFirst();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    private void visitInWidth(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        Deque<TreeNode<T>> queue = new LinkedList<>();
        TreeNode<T> queueNode;

        queue.add(node);

        while (!queue.isEmpty()) {
            queueNode = queue.getFirst();
            consumer.accept(queueNode.getData());

            if (queueNode.getLeft() != null) {
                queue.addLast(queueNode.getLeft());
            }

            if (queueNode.getRight() != null) {
                queue.addLast(queueNode.getRight());
            }

            queue.removeFirst();
        }
    }

    private void visitInDeepWithRecursion(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitInDeepWithRecursion(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitInDeepWithRecursion(node.getRight(), consumer);
        }
    }

    private void visitInDeep(TreeNode<T> node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(node);

        TreeNode<T> dequeNode;

        while (!stack.isEmpty()) {
            dequeNode = stack.getLast();
            consumer.accept(dequeNode.getData());

            stack.removeLast();

            if (dequeNode.getRight() != null) {
                stack.addLast(dequeNode.getRight());
            }

            if (dequeNode.getLeft() != null) {
                stack.addLast(dequeNode.getLeft());
            }
        }
    }
}

