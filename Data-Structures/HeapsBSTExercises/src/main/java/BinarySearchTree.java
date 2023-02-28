import java.util.ArrayList;
import java.util.function.Consumer;

import java.util.List;

public class BinarySearchTree<E extends Comparable<E>> {
    private Node<E> root;

    private BinarySearchTree(Node<E> node) {
        this();
        this.root = node;
    }

    public BinarySearchTree(E element) {
        this(new Node<>(element));
    }

    public BinarySearchTree() {
    }

    public static class Node<E> {
        private E value;
        private int size;
        private Node<E> leftChild;
        private Node<E> rightChild;

        public Node(E value) {
            this.value = value;
            this.size = 1;
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
        if (node.getLeft() != null) {
            inOrder(node.getLeft(), consumer);
        }
        consumer.accept(node.getValue());
        if (node.getRight() != null) {
            inOrder(node.getRight(), consumer);
        }
    }

    public Node<E> getRoot() {
        return this.root;
    }

    public void insert(E element) {
        Node<E> node = this.getRoot();
        if (node == null) {
            this.root = new Node<>(element);
            return;
        }
        insertRecursively(element, node);
    }

    private void insertRecursively(E element, Node<E> node) {

        if (node.getValue().compareTo(element) > 0) {
            if (node.leftChild != null) {
                node.size++;
                insertRecursively(element, node.leftChild);
            } else {
                node.leftChild = new Node<>(element);
                node.size++;
            }
        } else {
            if (node.rightChild != null) {
                node.size++;
                insertRecursively(element, node.rightChild);
            } else {
                node.rightChild = new Node<>(element);
                node.size++;
            }
        }
    }

    public boolean contains(E element) {
        return this.search(element).getRoot() != null;
    }

    public BinarySearchTree<E> search(E element) {
        Node<E> treeRoot = null;

        Node<E> currentNode = this.root;

        while (currentNode != null) {
            if (currentNode.value.equals(element)) {
                treeRoot = currentNode;
                break;
            } else if (element.compareTo(currentNode.getValue()) > 0) {
                currentNode = currentNode.getRight();
            } else {
                currentNode = currentNode.getLeft();
            }
        }

        return new BinarySearchTree<E>(treeRoot);
    }

    public List<E> range(E first, E second) {
        List<E> elements = new ArrayList<>();

        this.eachInOrder(n -> {
            if (first.compareTo(n) <= 0 && second.compareTo(n) >= 0) {
                elements.add(n);
            }
        });

        return elements;
    }

    public void deleteMin() {
        Node<E> currentNode = this.getRoot();

        checkIfTreeIsEmpty(this.getRoot());
        currentNode.size--;
        while (currentNode.getLeft() != null && currentNode.getLeft().getLeft() != null) {
            currentNode = currentNode.getLeft();
            currentNode.size--;
        }

        Node<E> leftChild = currentNode.leftChild;
        Node<E> rightChild = currentNode.rightChild;
        if (leftChild == null) {
            this.root = rightChild;
        } else if (leftChild.rightChild != null && leftChild.value.compareTo(leftChild.rightChild.value) < 0) {
            currentNode.leftChild = leftChild.rightChild;
        } else {
            currentNode.leftChild = null;
        }
    }

    public void deleteMax() {
        Node<E> currentNode = this.getRoot();
        checkIfTreeIsEmpty(this.getRoot());

        currentNode.size--;
        while (currentNode.getRight() != null && currentNode.getRight().getRight() != null) {
            currentNode = currentNode.getRight();
            currentNode.size--;
        }

        Node<E> leftChild = currentNode.leftChild;
        Node<E> rightChild = currentNode.rightChild;

        if (rightChild == null) {
            this.root = leftChild;
        } else if (rightChild.leftChild != null && rightChild.value.compareTo(rightChild.leftChild.value) > 0) {
            currentNode.rightChild = rightChild.leftChild;
        } else {
            currentNode.rightChild = null;
        }
    }

    public int count() {
        return this.root == null? 0: this.root.size;
    }

    public int rank(E element) {
        if (element == null){
            return 0;
        }
        Integer [] rank = new Integer[1];
        rank[0] = 0;

        if (this.root.value.compareTo(element) > 0){
            dfsTravers(rank, this.root.leftChild, element);
        }else if (this.root.value.compareTo(element) < 0){
//            adding the size of left part of the tree (left part (root.leftChildren tree) and the root are always smaller than root.rightChild)
            if (this.root.leftChild == null){
                rank[0] += 1;
            }else {
                rank[0] += this.root.leftChild.size + 1;
            }
            dfsTravers(rank,this.root.rightChild, element);
        }else {
            if (this.root.leftChild == null){
                return 0;
            }
//            if the element is the root -> all left children are smaller.
            rank[0] = this.root.leftChild.size;
        }
        return rank[0];
    }

    private void dfsTravers(Integer[] rank, Node<E> node, E element) {
        if (node == null){
            return;
        }

        if (node.value.compareTo(element) < 0){
            rank[0]++;
        }
        dfsTravers(rank, node.leftChild, element);
        dfsTravers(rank, node.rightChild, element);
    }

    private BinarySearchTree<E> searchParentOf(E element) {
        Node<E> treeRoot = null;

        Node<E> currentNode = this.root;

        while (currentNode != null ) {

            if (currentNode.leftChild != null && currentNode.leftChild.value.equals(element)) {
                treeRoot = currentNode;
                break;
            }else if (currentNode.rightChild != null && currentNode.rightChild.value.equals(element)){
                treeRoot = currentNode;
                break;
            }
            else if (element.compareTo(currentNode.getValue()) > 0) {
                currentNode = currentNode.getRight();
            } else {
                currentNode = currentNode.getLeft();
            }
        }

        return new BinarySearchTree<E>(treeRoot);
    }


    public E ceil(E element) {
        return null;
    }

    public E floor(E element) {
        return null;
    }

    private void checkIfTreeIsEmpty(Node<E> currentNode) {
        if (currentNode == null) {
            throw new IllegalArgumentException();
        }
    }
}
