package implementations;

import interfaces.AbstractTree;

import java.util.ArrayList;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private ArrayList<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            this.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        return null;
    }

    @Override
    public List<E> orderDfs() {
        return null;
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {

    }

    @Override
    public void removeNode(E nodeKey) {

    }

    @Override
    public void swap(E firstKey, E secondKey) {

    }
}



