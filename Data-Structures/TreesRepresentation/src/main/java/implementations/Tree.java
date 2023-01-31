package implementations;

import interfaces.AbstractTree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Tree<E> implements AbstractTree<E> {

    private E key;
    private Tree<E> parent;
    private ArrayList<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    @Override
    public List<E> orderBfs() {
        List<E> bfsList = new ArrayList<>();
        Queue<Tree<E>> queue = new ArrayDeque<>();
        Tree<E> tree = this;

        queue.offer(tree);
        while (!queue.isEmpty()){

            Tree<E> currentTree = queue.peek();

            for (Tree<E> child : currentTree.children) {
                queue.offer(child);
            }

            bfsList.add(queue.poll().key);
        }

        return bfsList;
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



