package tree;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.function.UnaryOperator;

public class Tree<T extends Comparable<T>> {
    private final TreeNode<T> root;
    private int size;

    public Tree(T data) {
        checkData(data);

        this.root = new TreeNode<>(data);
        ++size;
    }

    public T getRootValue() {
        return root.getData();
    }

    private void checkData(T data) {
        if(data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
    }

    private void checkRoot() {
        if (root.getData() == null) {
            throw new IllegalArgumentException("root can't be null");
        }
    }

    public void add(T data) {
        checkData(data);

        boolean isNotAdded = true;

        if (root.getData() == null) {
            root.setData(data);
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

        for (T e : c) {
            add(e);
        }
    }

    public boolean contains(T data) {
        checkRoot();

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

    private TreeNode<T>[] findElementAndParent(T data) {
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

    private void changeChild(TreeNode<T> parent, TreeNode<T> child) {
        if (parent.getLeft() == child) {
            parent.setLeft(child.getRight());
            return;
        }

        if (parent.getRight() == child) {
            parent.setRight(child.getRight());
            return;
        }

        throw new IllegalArgumentException("parent hasn't this child");
    }

    public boolean remove(T data) {
        checkRoot();

        TreeNode<T>[] array = findElementAndParent(data);

        if (array == null) {
            return false;
        }

        if (array[0].getRight() == null) {
            changeChild(array[1], array[0]);
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
        changeChild(previousNode, lastLeftList);

        --size;

        return true;
    }


    public int size() {
        return size;
    }

    public void replaceAll(UnaryOperator<T> operator) {
        checkRoot();

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
        checkRoot();

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
        checkRoot();

        StringBuilder stringBuilder = new StringBuilder();
        Deque<TreeNode<T>> queue = new LinkedList<>();

        queue.add(root);
        TreeNode<T> node;

        stringBuilder.append("{");

        while (!queue.isEmpty()) {
            node = queue.getFirst();

            stringBuilder.append(node.getData()).append(" (");

            if (node.getLeft() != null) {
                queue.addLast(node.getLeft());
                stringBuilder.append(node.getLeft().getData()).append(", ");
            } else {
                stringBuilder.append("null, ");
            }

            if (node.getRight() != null) {
                queue.addLast(node.getRight());
                stringBuilder.append(node.getRight().getData()).append("), ");
            } else {
                stringBuilder.append("null), ");
            }

            queue.removeFirst();
        }

        stringBuilder.delete(stringBuilder.length() - 2, stringBuilder.length()).append("}");

        return stringBuilder.toString();
    }


}

