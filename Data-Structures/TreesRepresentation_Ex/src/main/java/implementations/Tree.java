package implementations;

import interfaces.AbstractTree;


import java.util.*;

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

    @Override
    public List<E> getMiddleKeys() {
        List<E> middleKeys = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> currentTree = queue.poll();
            for (Tree<E> child : currentTree.children) {
                if (isMiddleNode(child)) {
                    middleKeys.add(child.key);
                } else {
                    queue.offer(child);
                }
            }
        }
        return middleKeys;
    }

    @Override
    public Tree<E> getDeepestLeftmostNode() {
        List<Tree<E>> allNodes = getAllNodes(this);
        Tree<E> deepestLeftmostNode = null;
        int deepestNodeDeep = -1;

        for (int i = 0; i < allNodes.size(); i++) {
            Tree<E> currentNode = allNodes.get(i);
            int currentDeep = findDeep(currentNode);
            if (currentDeep > deepestNodeDeep) {
                deepestLeftmostNode = currentNode;
                deepestNodeDeep = currentDeep;
            }
        }
        return deepestLeftmostNode;
    }

    @Override
    public List<E> getLongestPath() {
        Tree<E> deepestLeftmostNode = this.getDeepestLeftmostNode();
        List<Tree<E>> pathFromNodes = findPathFromNode(deepestLeftmostNode);
        List<E> path = getPathFromPathFromNodes(pathFromNodes);

        return path;
    }

    @Override
    public List<List<E>> pathsWithGivenSum(int sum) {
        List<Tree<E>> allNodes = getAllNodes(this);
        List<List<E>> paths = new ArrayList<>();

        for (Tree<E> node : allNodes) {
            int currentSum = findSumOfPath(node);
            if (currentSum == sum){
                List<Tree<E>> currentNodePath = findPathFromNode(node);
                List<E> currentPath = getPathFromPathFromNodes(currentNodePath);
                paths.add(currentPath);
            }
        }
        return paths;
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
        if (isLeaf(tree)) {
            leafs.add(tree.key);
        } else {
            for (Tree<E> child : tree.children) {
                findLeafsDfs(child, leafs);
            }
        }
    }


    //     An internal node (also known as an inner node, inode for short, or branch node) is any node of a tree that has child nodes.(https://en.wikipedia.org/wiki/Tree_(data_structure))
    private boolean isMiddleNode(Tree<E> node) {
        return !isLeaf(node);
    }

    //    An external node (also known as an outer node, leaf node, or terminal node) is any node that does not have child nodes (https://en.wikipedia.org/wiki/Tree_(data_structure))
    private boolean isLeaf(Tree<E> node) {
        return node.children.size() == 0;
    }

    private List<E> findLeafsBfs(Tree<E> tree) {
        List<E> leafs = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(tree);

        while (queue.size() > 0) {
            Tree<E> currentTree = queue.poll();
            for (Tree<E> child : currentTree.children) {
                if (isLeaf(child)) {
                    leafs.add(child.key);
                } else {
                    queue.offer(child);
                }
            }

        }
        return leafs;
    }
    private List<Tree<E>> getAllNodes(Tree<E> tree) {
        List<Tree<E>> nodes = new ArrayList<>();
        nodes.add(tree);
        Deque<Tree<E>> queue = new ArrayDeque<>();

        queue.offer(tree);
        while (queue.size() > 0) {
            Tree<E> currentTree = queue.poll();
            for (Tree<E> child : currentTree.children) {
                nodes.add(child);
                if (!isLeaf(child)) {
                    queue.offer(child);
                }
            }
        }
        return nodes;
    }

    private int findDeep(Tree<E> node) {
        int deep = 0;
        while (node.parent != null) {
            deep++;
            node = node.parent;
        }
        return deep;
    }

    private List<Tree<E>> findPathFromNode(Tree<E> node) {
        List<Tree<E>> path = new ArrayList<>();
        Tree<E> currentNode = node;

        while (currentNode.parent != null) {
            path.add(currentNode);
            currentNode = currentNode.parent;
        }
        path.add(currentNode);

        return path;
    }

    private int findSumOfSubtree(Tree<E> tree) {
        List<Tree<E>> treeNodes = getAllNodes(tree);
        int sum = 0;
        for (Tree<E> treeNode : treeNodes) {
            sum += (int) treeNode.key;
        }
        return sum;
    }

    private List<E> getPathFromPathFromNodes(List<Tree<E>> pathFromNodes){
        List<E> path = new ArrayList<>();

        for (int i = pathFromNodes.size() - 1; i >= 0 ; i--) {
            path.add(pathFromNodes.get(i).key);
        }
        return path;
    }

    private int findSumOfPath(Tree<E> node) {
        List<Tree<E>> pathFromNode = findPathFromNode(node);
        int sum = 0;
        for (Tree<E> pathNode : pathFromNode) {
            sum += (int) pathNode.key;
        }
        return sum;
    }
}



