package solutions;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BinaryTree {

    private Integer key;
    private BinaryTree first;
    private BinaryTree second;

    private BinaryTree parent;

    public BinaryTree(int key, BinaryTree first, BinaryTree second) {
        this.key = key;
        this.first = first;
        this.second = second;

        setParent(this.first);
        setParent(this.second);

    }

    public Integer findLowestCommonAncestor(int first, int second) {
        Integer commonAncestor = null;
        ArrayList<BinaryTree> firstAndSecond = bfs(first, second);
        if (firstAndSecond.size() > 2){
            throw new IllegalArgumentException("Duplicated elements");
        } else if (firstAndSecond.size() == 1) {
            throw new IllegalArgumentException("Only one of the elements are in the Binary tree: " + firstAndSecond.get(0));
        } else if (firstAndSecond.size() == 0) {
            throw new IllegalArgumentException("Elements are not contained in the tree");
        }
//        firstAndSecond.get(0) ancestors <= firstAndSecond.get(1) ancestors because firstAndSecond are found using BFS algorithm.
        ArrayList<Integer> firstAncestors = findAncestors(firstAndSecond.get(0));
        ArrayList<Integer> secondAncestors = findAncestors(firstAndSecond.get(1));

        int startIndex = secondAncestors.size() - firstAncestors.size();
        /*
        (i > startIndex) needed if first and second are at the same branch
        */
        for (int i = startIndex; i < secondAncestors.size(); i++) {
            if (secondAncestors.get(i).equals(firstAncestors.get(i - startIndex)) && i > startIndex){
                commonAncestor = secondAncestors.get(i);
                break;
            }
        }
        return commonAncestor;
    }

    private ArrayList<Integer> findAncestors(BinaryTree binaryTree) {
        ArrayList<Integer> ancestors = new ArrayList<>();

        while (binaryTree != null){
            ancestors.add(binaryTree.key);
            binaryTree = binaryTree.parent;
        }
        return ancestors;
    }

    private ArrayList<BinaryTree> bfs(int first, int second) {
        ArrayList<BinaryTree> firstAndSecond = new ArrayList<>();
        BinaryTree currentTree = this;
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.offer(currentTree);

        while (queue.size() > 0) {

            BinaryTree tree = queue.poll();
            if (tree.first != null) {
                queue.offer(tree.first);
            }
            if (tree.second != null) {
                queue.offer(tree.second);
            }
            if (tree.key.equals(first) || tree.key.equals(second)) {
                firstAndSecond.add(tree);
            }
        }
        return firstAndSecond;
    }

    public List<Integer> topView() {
        return null;
    }



    private void setParent(BinaryTree child) {
        if (child != null) {
            child.parent = this;
        }
    }
}
