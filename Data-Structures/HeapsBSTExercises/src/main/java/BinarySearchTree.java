import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    public BinarySearchTree(E element) {
        this.root = new Node<>(element);
    }

    public static class Node<E> {
        private E value;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
        }

        public Node<E> getLeft() {
            return this.leftChild;
        }

        public Node<E> getRight() {
            return this.rightChild;
        }

        public E getValue() {
            return this.value;
        }
    }

    public void eachInOrder(Consumer<E> consumer) {
        inOrder(this.getRoot(), consumer);
    }

    private void inOrder(Node<E> node, Consumer<E> consumer) {
        if (node.getLeft() != null){
            inOrder(node.getLeft(), consumer);
        }
        consumer.accept(node.getValue());
        if (node.getRight() != null){
            inOrder(node.getRight(), consumer);
        }
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        Node<E> node = this.getRoot();
        insertRecursively(element, node);
    }

    private void insertRecursively(E element, Node<E> node) {

        if (node.getValue().compareTo(element) > 0) {
            if (node.leftChild != null) {
                insertRecursively(element, node.leftChild);
            } else {
                node.leftChild = new Node<>(element);
            }
        } else {
            if (node.rightChild != null) {
                insertRecursively(element, node.rightChild);
            } else {
                node.rightChild = new Node<>(element);
            }
        }
    }

    public boolean contains(E element) {
        return false;
    }

    public BinarySearchTree<E> search(E element) {
        return null;
    }

    public List<E> range(E first, E second) {
        return null;
    }

    public void deleteMin() {

    }

    public void deleteMax() {

    }

    public int count() {
        return 0;
    }

    public int rank(E element) {
        return 0;
    }

    public E ceil(E element) {
        return null;
    }

    public E floor(E element) {
        return null;
    }
}
