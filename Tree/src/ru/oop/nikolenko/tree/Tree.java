package ru.oop.nikolenko.tree;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
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
        root = new TreeNode<>(data);
        size = 1;
    }

    private int compare(T data1, T data2) {
        if (comparator != null) {
            return comparator.compare(data1, data2);
        }

        if (data1 == null) {
            if (data2 == null) {
                return 0;
            }

            return -1;
        }

        if (data2 == null) {
            return 1;
        }

        //noinspection unchecked
        Comparable<T> comparableData1 = (Comparable<T>) data1;

        return comparableData1.compareTo(data2);
    }

    public T getRootData() {
        if (root == null) {
            throw new NoSuchElementException("tree is empty");
        }

        return root.getData();
    }

    public void add(T data) {
        if (root == null) {
            root = new TreeNode<>(data);
            ++size;
            return;
        }

        TreeNode<T> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) < 0) {
                TreeNode<T> leftNode = currentNode.getLeft();

                if (leftNode != null) {
                    currentNode = leftNode;
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    break;
                }
            } else {
                TreeNode<T> rightNode = currentNode.getRight();

                if (rightNode != null) {
                    currentNode = rightNode;
                } else {
                    currentNode.setRight(new TreeNode<>(data));
                    break;
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

        return findNodeAndParent(data) != null;
    }

    private TreeNode<T>[] findNodeAndParent(T data) {
        //noinspection unchecked
        TreeNode<T>[] array = (TreeNode<T>[]) new TreeNode<?>[2];
        TreeNode<T> node = root;
        TreeNode<T> parent = null;

        while (true) {
            int comparisonResult = compare(data, node.getData());

            if (comparisonResult == 0) {
                array[0] = node;
                array[1] = parent;
                return array;
            }

            if (comparisonResult < 0) {
                if (node.getLeft() != null) {
                    parent = node;
                    node = node.getLeft();
                } else {
                    return null;
                }
            } else {
                if (node.getRight() != null) {
                    parent = node;
                    node = node.getRight();
                } else {
                    return null;
                }
            }
        }
    }

    private void setNodeChildToParent(TreeNode<T> node, TreeNode<T> nodeChild, TreeNode<T> nodeParent) {
        if (node == nodeParent.getLeft()) {
            nodeParent.setLeft(nodeChild);
            return;
        }

        if (node == nodeParent.getRight()) {
            nodeParent.setRight(nodeChild);
            return;
        }

        throw new IllegalArgumentException("parent hasn't this node");
    }

    public boolean remove(T data) {
        if (root == null) {
            return false;
        }

        if (size == 1) {
            if (compare(data, root.getData()) != 0) {
                return false;
            }

            root = null;
            size = 0;

            return true;
        }

        if (compare(root.getData(), data) == 0) {
            if (root.getRight() == null) {
                root = root.getLeft();
                --size;

                return true;
            }

            if (root.getLeft() == null) {
                root = root.getRight();
                --size;

                return true;
            }
        }

        TreeNode<T>[] nodeAndParentArray = findNodeAndParent(data);

        if (nodeAndParentArray == null) {
            return false;
        }

        TreeNode<T> node = nodeAndParentArray[0];
        TreeNode<T> parent = nodeAndParentArray[1];

        if (node.getRight() == null) {
            setNodeChildToParent(node, node.getLeft(), parent);
            --size;
            return true;
        }

        TreeNode<T> previousNode = node;
        TreeNode<T> lastLeftList = node.getRight();

        while (lastLeftList.getLeft() != null) {
            previousNode = lastLeftList;
            lastLeftList = lastLeftList.getLeft();
        }

        nodeAndParentArray[0].setData(lastLeftList.getData());
        setNodeChildToParent(lastLeftList, lastLeftList.getRight(), previousNode);

        --size;

        return true;
    }

    public int size() {
        return size;
    }

    public Object[] toArray() {
        Object[] treeArray = new Object[size];

        AtomicInteger i = new AtomicInteger();

        Consumer<T> consumer = t -> {
            treeArray[i.get()] = t;
            i.getAndIncrement();
        };

        visitInDeep(consumer);

        return treeArray;
    }

    @Override
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        Queue<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);
        TreeNode<T> node;

        stringBuilder.append("{");

        while (!queue.isEmpty()) {
            node = queue.remove();

            stringBuilder.append(node.getData());

            if (node.getLeft() == null && node.getRight() == null) {
                stringBuilder.append(", ");
            } else {
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                    stringBuilder.append(" (").append(node.getLeft().getData()).append(", ");
                } else {
                    stringBuilder.append(" (-, ");
                }

                if (node.getRight() != null) {
                    queue.add(node.getRight());
                    stringBuilder.append(node.getRight().getData()).append("), ");
                } else {
                    stringBuilder.append("-), ");
                }
            }
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }

    public void visitInWidth(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode<T> queueNode = queue.remove();
            consumer.accept(queueNode.getData());

            if (queueNode.getLeft() != null) {
                queue.add(queueNode.getLeft());
            }

            if (queueNode.getRight() != null) {
                queue.add(queueNode.getRight());
            }
        }
    }

    public void visitInDeepWithRecursion(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        visitInDeepWithRecursion(root, consumer);
    }

    private void visitInDeepWithRecursion(TreeNode<T> node, Consumer<T> consumer) {
        consumer.accept(node.getData());

        if (node.getLeft() != null) {
            visitInDeepWithRecursion(node.getLeft(), consumer);
        }

        if (node.getRight() != null) {
            visitInDeepWithRecursion(node.getRight(), consumer);
        }
    }

    public void visitInDeep(Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        Deque<TreeNode<T>> stack = new LinkedList<>();
        stack.addLast(root);

        while (!stack.isEmpty()) {
            TreeNode<T> dequeNode = stack.removeLast();
            consumer.accept(dequeNode.getData());

            if (dequeNode.getRight() != null) {
                stack.addLast(dequeNode.getRight());
            }

            if (dequeNode.getLeft() != null) {
                stack.addLast(dequeNode.getLeft());
            }
        }
    }

    /*
    @Override
    public String toString() {
        if (root == null) {
            return "{}";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");

        Consumer<T> consumer = x -> stringBuilder.append(x).append(", ");

        visitInWidth(consumer);

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }
     */
}