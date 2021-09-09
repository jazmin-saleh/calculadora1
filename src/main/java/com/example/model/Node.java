package model;

public class Node<T> {
    private T info;
    private Node<T> left;
    private Node<T> right;

    public Node(T info) {
        this.info = info;
        this.left = null;
        this.right = null;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }
}
