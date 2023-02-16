package implementations;

import interfaces.AbstractBinaryTree;

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
        dfsTravers(this, builder, indent);
        return builder.toString();
    }

    private void dfsTravers(BinaryTree<E> root, StringBuilder builder, int indent) {
        builder.append(createPadding(indent))
                .append(root.getKey())
                .append(System.lineSeparator());
        if (root.leftChild != null){
            dfsTravers(root.leftChild, builder, indent + 2);
        }
        if (root.rightChild != null){
            dfsTravers(root.rightChild, builder, indent + 2);
        }
    }

    @Override
    public List<AbstractBinaryTree<E>> preOrder() {
        return null;
    }

    @Override
    public List<AbstractBinaryTree<E>> inOrder() {
        return null;
    }

    @Override
    public List<AbstractBinaryTree<E>> postOrder() {
        return null;
    }

    @Override
    public void forEachInOrder(Consumer<E> consumer) {

    }

    private String createPadding(int indent) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < indent; i++) {
            builder.append(" ");
        }
        return builder.toString();
    }
}
