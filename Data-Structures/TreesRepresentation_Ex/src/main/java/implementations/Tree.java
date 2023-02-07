package implementations;

import interfaces.AbstractTree;


import java.util.ArrayList;
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

        dfsTravers(this, builder, -1);
        System.out.println();
        return builder.toString().trim();
    }

    private void dfsTravers(Tree<E> tree, StringBuilder builder, int level) {
        if (tree.children.size() == 0) {
//            builder.append(System.lineSeparator()).append("  ".repeat(level + 1)).append(tree.key); /*can be used after Java 11 */
            builder.append(System.lineSeparator()).append(stringRepeat("  ", level + 1)).append(tree.key);
            return;
        }else {
            level++;
        }

//        builder.append(System.lineSeparator()).append("  ".repeat(level)).append(tree.key); /*can be used after Java 11 */
        builder.append(System.lineSeparator()).append(stringRepeat("  ", level)).append(tree.key);
        for (Tree<E> child : tree.children) {
            dfsTravers(child, builder, level);
        }
    }

    private String stringRepeat(String str, int n) {
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < n; i++) {
            newStr.append(str);
        }
        return newStr.toString();
    }

    @Override
    public List<E> getLeafKeys() {
        return null;
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
}



