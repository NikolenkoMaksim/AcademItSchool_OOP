package ru.oop.nikolenko.tree;

public class TreeNode<T extends Comparable<T>> {
    private T data;
    private TreeNode<T> left;
    private TreeNode<T> right;

    public TreeNode(T data) {
        checkData(data);

        this.data = data;
    }

    private void checkData(T data) {
        if (data == null) {
            throw new IllegalArgumentException("data can't be null");
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        checkData(data);

        this.data = data;
    }

    public TreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<T> left) {
        this.left = left;
    }

    public TreeNode<T> getRight() {
        return right;
    }

    public void setRight(TreeNode<T> right) {
        this.right = right;
    }
}
