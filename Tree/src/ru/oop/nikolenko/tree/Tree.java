package ru.oop.nikolenko.tree;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class Tree<T extends Comparable<T>> {
    private TreeNode<T> root;
    private int size;

    public Tree() {
    }

    public Tree(T data) {
        checkData(data);

        root = new TreeNode<>(data);
        ++size;
    }

    public T getRootData() {
        return root.getData();
    }

    private void checkData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
    }

    public void add(T data) {
        checkData(data);

        boolean isNotAdded = true;

        if (root == null) {
            root = new TreeNode<>(data);
            size = 1;
            return;
        }

        TreeNode<T> currentNode = root;

        while (isNotAdded) {
            if (data.compareTo(currentNode.getData()) < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();
                } else {
                    currentNode.setLeft(new TreeNode<>(data));
                    isNotAdded = false;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();
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
            if (data.compareTo(currentNode.getData()) == 0) {
                return true;
            }

            if (data.compareTo(currentNode.getData()) < 0) {
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
        TreeNode<T>[] array = (TreeNode<T>[]) new TreeNode<?>[2];
        array[0] = root;
        array[1] = null;

        while (true) {
            if (data.compareTo(array[0].getData()) == 0) {
                return array;
            }

            if (data.compareTo(array[0].getData()) < 0) {
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

        if(root.getData() == data && root.getRight() == null) {
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

    public void replaceAll(UnaryOperator<T> operator) {
        if (root == null) {
            return;
        }

        if (operator == null) {
            throw new IllegalArgumentException("operator can't be null");
        }

        visit(root, operator);
    }

    private void visit(TreeNode<T> node, UnaryOperator<T> operator) {
        node.setData(operator.apply(node.getData()));

        if (node.getLeft() != null) {
            visit(node.getLeft(), operator);
        }

        if (node.getRight() != null) {
            visit(node.getRight(), operator);
        }
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
}

