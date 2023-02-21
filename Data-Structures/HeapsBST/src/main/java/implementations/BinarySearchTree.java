package implementations;

import interfaces.AbstractBinarySearchTree;

public class BinarySearchTree<E extends Comparable<E>> implements AbstractBinarySearchTree<E> {

    private Node<E> tree;

    public BinarySearchTree() {

    }

    private BinarySearchTree(Node<E> node) {
        this.tree = node;
    }

    @Override
    public void insert(E element) {
        if (this.tree == null) {
            this.tree = new Node<>(element);
            return;
        }

        Node<E> currentNode = this.tree;
        while (true) {
            if (isCurrentElementLessThanElement(currentNode.value, element)) {
                if (currentNode.rightChild == null) {
                    currentNode.rightChild = new Node<>(element);
                    break;
                }
                currentNode = currentNode.rightChild;
            } else {
                if (currentNode.leftChild == null) {
                    currentNode.leftChild = new Node<>(element);
                    break;
                }
                currentNode = currentNode.leftChild;
            }
        }

    }

    @Override
    public boolean contains(E element) {
        Node<E> currentNode = this.getNode(element);
        return currentNode != null;
    }

    @Override
    public AbstractBinarySearchTree<E> search(E element) {
        Node<E> node = this.getNode(element);
        return new BinarySearchTree<>(node);
    }

    @Override
    public Node<E> getRoot() {
        return this.tree;
    }

    @Override
    public Node<E> getLeft() {
        return this.tree.leftChild;
    }

    @Override
    public Node<E> getRight() {
        return this.tree.rightChild;
    }

    @Override
    public E getValue() {
        return this.tree.value;
    }

    private boolean isCurrentElementLessThanElement(E currentElement, E element) {
        return currentElement.compareTo(element) < 0;
    }

    private Node<E> getNode(E element) {
        Node<E> currentNode = this.tree;
        boolean isFound = false;

        while (currentNode != null) {
            if (currentNode.value.compareTo(element) == 0) {
                isFound = true;
                break;
            } else if (isCurrentElementLessThanElement(currentNode.value, element)) {
                currentNode = currentNode.rightChild;
            } else {
                currentNode = currentNode.leftChild;
            }
        }
        return isFound ? currentNode : null;
    }
}
