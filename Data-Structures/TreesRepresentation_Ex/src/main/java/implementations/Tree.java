package implementations;

import interfaces.AbstractTree;


import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key) {
        this.key = key;
        this.children = new ArrayList<>();
    }

    @Override
    public void setParent(Tree<E> parent) {
        this.parent = parent;
    }

    @Override
    public void addChild(Tree<E> child) {
        this.children.add(child);
    }

    @Override
    public Tree<E> getParent() {
        return this.parent;
    }

    @Override
    public E getKey() {
        return this.key;
    }

    @Override
    public String getAsString() {
        StringBuilder builder = new StringBuilder();

        dfsTravers(this, builder, 0);
        System.out.println();
        return builder.toString().trim();
    }

    @Override
    public List<E> getLeafKeys() {
//        List<E> leafs = new ArrayList<>();
//        findLeafsDfs(this, leafs);

        return findLeafsBfs(this);
    }

    private List<E> findLeafsBfs(Tree<E> tree) {
        List<E> leafs = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(tree);

        while (queue.size() > 0) {
            Tree<E> currentTree = queue.poll();
            for (Tree<E> child : currentTree.children) {
                if (child.children.size() > 0) {
                    queue.offer(child);
                } else {
                    leafs.add(child.key);
                }
            }

        }
        return leafs;
    }

    @Override
    public List<E> getMiddleKeys() {
        return null;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        return null;
    }

    @Override
    public List<E> getLongestPath() {
        return null;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        return null;
    }

    @Override
    public List<Tree<E>> subTreesWithGivenSum(int sum) {
        return null;
    }

    private void dfsTravers(Tree<E> tree, StringBuilder builder, int level) {
        builder.append(System.lineSeparator()).append(stringRepeat("  ", level)).append(tree.key);

        for (Tree<E> child : tree.children) {
            dfsTravers(child, builder, level + 1);
        }
    }

    private String stringRepeat(String str, int n) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            newStr.append(str);
        }
        return newStr.toString();
    }

    private void findLeafsDfs(Tree<E> tree, List<E> leafs) {
        if (tree.children.size() == 0) {
            leafs.add(tree.key);
        } else {
            for (Tree<E> child : tree.children) {
                findLeafsDfs(child, leafs);
            }
        }
    }
}



