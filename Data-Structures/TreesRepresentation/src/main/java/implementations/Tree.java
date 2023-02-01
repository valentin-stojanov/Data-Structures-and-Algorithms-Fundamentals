package implementations;

import interfaces.AbstractTree;

import java.util.*;

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
        if (this.key == null) {
            return bfsList;
        }
        Queue<Tree<E>> queue = new ArrayDeque<>();
        Tree<E> tree = this;

        queue.offer(tree);
        while (!queue.isEmpty()) {

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
        List<E> dfsList = new ArrayList<>();
        Tree<E> tree = this;

//        Queue<Tree<E>> queue = new ArrayDeque<>();
//        queue.offer(tree);
//        while (!queue.isEmpty()){
//            Tree<E> current = queue.poll();
//            dfsList.add(0, current.key);
//
//            for (int i = current.children.size() -1; i >= 0 ; i--) {
//                queue.offer(current.children.get(i));
//            }
//        }

        this.dfs(tree, dfsList);

        return dfsList;
    }

    private void dfs(Tree<E> tree, List<E> dfsList) {
        for (Tree<E> child : tree.children) {
            this.dfs(child, dfsList);
        }
        dfsList.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Tree<E> node = findNodeBfs(parentKey);
        if (node != null) {
            child.parent = node;
            node.children.add(child);
        }

    }

    private Tree<E> findNodeBfs(E key) {
        Queue<Tree<E>> queue = new ArrayDeque<>();
        Tree<E> node = null;
        Tree<E> tree = this;

        queue.offer(tree);
        while (!queue.isEmpty()) {
            Tree<E> currentTree = queue.peek();

            for (Tree<E> child : currentTree.children) {
                queue.offer(child);
            }

            if (key.equals(queue.poll().key)) {
                node = currentTree;
            }
        }

        return node;
    }

    @Override
    public void removeNode(E nodeKey) {
        Tree<E> nodeToRemove = findNodeBfs(nodeKey);

        if (nodeToRemove != null) {
            if (nodeToRemove.equals(this)) {
                this.children = null;
                this.key = null;
                return;
            }
            Tree<E> parent = nodeToRemove.parent;
            parent.children.remove(nodeToRemove);
            nodeToRemove.parent = null;
        }


    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Tree<E> firstNode = findNodeBfs(firstKey);
        Tree<E> secondNode = findNodeBfs(secondKey);

        if( firstKey == null || secondKey == null){
            throw new IllegalArgumentException();
        }

        if (isRoot(firstNode)){
            changeRootWith(secondNode);
            return;
        }

        if (isRoot(secondNode)){
            changeRootWith(firstNode);
            return;
        }

        Tree<E> parentOfFirstNode = firstNode.parent;
        int firstNodeIndexInParentChildren = parentOfFirstNode.children.indexOf(firstNode);

        Tree<E> parentOfSecondNode = secondNode.parent;
        int secondNodeIndexInParentChildren = parentOfSecondNode.children.indexOf(secondNode);

        parentOfFirstNode.children.remove(firstNodeIndexInParentChildren);
        parentOfFirstNode.children.add(firstNodeIndexInParentChildren, secondNode);
        secondNode.parent = parentOfFirstNode;

        parentOfSecondNode.children.remove(secondNodeIndexInParentChildren);
        parentOfSecondNode.children.add(secondNodeIndexInParentChildren, firstNode);
        firstNode.parent = parentOfSecondNode;

//        Tree<E> firstParent = firstNode.parent;
//        Tree<E> secondParent = secondNode.parent;
//
//        firstNode.parent = secondParent;
//        secondNode.parent = firstParent;
//
//        int firstIndex = firstParent.children.indexOf(firstNode);
//        int secondIndex = secondParent.children.indexOf(secondNode);
//
//        firstParent.children.set(firstIndex,secondNode);
//        secondParent.children.set(secondIndex, firstNode);

    }

    private boolean isRoot(Tree<E> firstNode) {
        return firstNode.parent == null;
    }

    private void changeRootWith(Tree<E> secondNode) {
        this.key = secondNode.key;
        this.children = secondNode.children;
    }
}



