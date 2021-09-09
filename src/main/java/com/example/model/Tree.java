package model;

import java.util.Stack;

public class Tree<T> {
    private Node<T> root;
    private Stack<Node<T>> pila;

    public Tree(Stack<Node<T>> pila) {
        this.pila = pila;
        root = null;
    }

    private boolean isEmpty() {
        return root == null;
    }

    public boolean isOperand(T op) {
        return op.toString().charAt(0) >= '*' && op.toString().charAt(0) <= '/';
    }

    public boolean isNumber(T op) {
        try {
            double a = Double.parseDouble(op.toString());
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Node<T> getRoot() {
        return root;
    }

    private Node<T> findFather(Node<T> node) {
        Stack<Node<T>> operand = (Stack<Node<T>>) pila.clone();
        operand.removeIf(tNode -> isNumber(tNode.getInfo()));
        for (Node<T> tNode : operand) {
            if (tNode.getLeft() == node || tNode.getRight() == node) {
                return tNode;
            }
        }
        operand.clear();
        return root;
    }

    public double operate() {
        Stack<Node<T>> pilaCopy = (Stack<Node<T>>) pila.clone();
        pilaCopy.removeIf(tNode->tNode.getRight() == null || tNode.getLeft() == null);
        for (Node<T> tNode : pilaCopy) {
            if (isNumber(tNode.getLeft().getInfo()) && isNumber(tNode.getRight().getInfo())) {
                tNode.setInfo((T) Double.toString(operation(tNode, tNode.getLeft(), tNode.getRight())));
                tNode.setRight(null);
                tNode.setLeft(null);
            }
        }
        return Double.parseDouble(pilaCopy.lastElement().getInfo().toString());
    }

    private double operation(Node<T> operator, Node<T> left, Node<T> right) {
        switch (operator.getInfo().toString().charAt(0)) {
            case '*':
                return Double.parseDouble(left.getInfo().toString()) * Double.parseDouble(right.getInfo().toString());
            case '/':
                return Double.parseDouble(left.getInfo().toString()) / Double.parseDouble(right.getInfo().toString());
            case '-':
                return Double.parseDouble(left.getInfo().toString()) - Double.parseDouble(right.getInfo().toString());
            case '+':
                return Double.parseDouble(left.getInfo().toString()) + Double.parseDouble(right.getInfo().toString());
            case '^':
                return Math.pow(Double.parseDouble(left.getInfo().toString()), Double.parseDouble(right.getInfo().toString()));
            case '%':
                return Double.parseDouble(left.getInfo().toString()) % Double.parseDouble(right.getInfo().toString());
            default:
                return 0;
        }
    }

    public void createTree() {
        Stack<Node<T>> pilaCopy = (Stack<Node<T>>) pila.clone();
        if (isEmpty()) {
            root = pilaCopy.pop();
        }
        Node<T> aux = root;
        while (!pilaCopy.isEmpty() && aux != null) {
            if (aux.getRight() == null) {
                aux.setRight(pilaCopy.pop());
                aux = isNumber(aux.getRight().getInfo()) ? aux : aux.getRight();
            } else if (aux.getLeft() == null) {
                aux.setLeft(pilaCopy.pop());
                aux = isNumber(aux.getLeft().getInfo()) ? aux : aux.getLeft();
            } else {
                aux = findFather(aux);
            }
        }
        pilaCopy.clear();
    }
}