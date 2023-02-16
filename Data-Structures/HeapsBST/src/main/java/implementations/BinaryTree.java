package implementations;

import interfaces.AbstractBinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class BinaryTree<E> implements AbstractBinaryTree<E> {
    private E key;
    private BinaryTree<E> leftChild;
    private BinaryTree<E> rightChild;

    public BinaryTree(E key, BinaryTree<E> leftChild, BinaryTree<E> rightChild) {
        this.key = key;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public AbstractBinaryTree<E> getLeft() {
        return this.leftChild;
    }

    @Override
    public AbstractBinaryTree<E> getRight() {
        return this.rightChild;
    }

    @Override
    public void setKey(E key) {
        this.key = key;
    }

    @Override
    public String asIndentedPreOrder(int indent) {
        StringBuilder builder = new StringBuilder();
        dfsTraversPreOrder(this, builder, indent);
        return builder.toString().trim();
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();
        dfsTraversPreOrder(this, list);
        return list;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();
        dfsTraversInOrder(this, list);
        return list;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        List<AbstractBinaryTree<E>> list = new ArrayList<>();
        dfsTraversPostOrder(this, list);
        return list;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {
        dfsTraversInOrder(this, consumer);
    }

    private void dfsTraversInOrder(AbstractBinaryTree<E> tree, Consumer<E> consumer) {
        if (tree.getLeft() != null) {
            dfsTraversInOrder(tree.getLeft(), consumer);
        }
        consumer.accept(tree.getKey());
        if (tree.getRight() != null) {
            dfsTraversInOrder(tree.getRight(), consumer);
        }
    }

    private String createPadding(int indent) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }

    private void dfsTraversPreOrder(BinaryTree<E> root, StringBuilder builder, int indent) {
        builder.append(createPadding(indent))
                .append(root.getKey())
                .append(System.lineSeparator());
        if (root.leftChild != null) {
            dfsTraversPreOrder(root.leftChild, builder, indent + 2);
        }
        if (root.rightChild != null) {
            dfsTraversPreOrder(root.rightChild, builder, indent + 2);
        }
    }

    private void dfsTraversPreOrder(BinaryTree<E> tree, List<AbstractBinaryTree<E>> list) {
        list.add(tree);
        if (tree.getLeft() != null) {
            dfsTraversPreOrder(tree.leftChild, list);
        }
        if (tree.getRight() != null) {
            dfsTraversPreOrder(tree.rightChild, list);
        }
    }

    private void dfsTraversInOrder(BinaryTree<E> tree, List<AbstractBinaryTree<E>> list) {
        if (tree.getLeft() != null) {
            dfsTraversInOrder(tree.leftChild, list);
        }
        list.add(tree);
        if (tree.getRight() != null) {
            dfsTraversInOrder(tree.rightChild, list);
        }
    }

    private void dfsTraversPostOrder(BinaryTree<E> tree, List<AbstractBinaryTree<E>> list) {
        if (tree.getLeft() != null) {
            dfsTraversPostOrder(tree.leftChild, list);
        }
        if (tree.getRight() != null) {
            dfsTraversPostOrder(tree.rightChild, list);
        }
        list.add(tree);
    }
}
