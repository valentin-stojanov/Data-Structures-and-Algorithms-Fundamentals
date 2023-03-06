package solutions;

import java.util.*;

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
        if (firstAndSecond.size() > 2) {
            throw new IllegalArgumentException("Duplicated elements");
        } else if (firstAndSecond.size() == 1) {
            throw new IllegalArgumentException("Only one of the elements are in the Binary tree: " + firstAndSecond.get(0));
        } else if (firstAndSecond.size() == 0) {
            throw new IllegalArgumentException("Elements are not contained in the tree");
        }
//        firstAndSecond.get(0) ancestors <= firstAndSecond.get(1) ancestors because firstAndSecond are found using BFS algorithm.
        ArrayList<Integer> firstAncestors = findAncestors(firstAndSecond.get(0));
        ArrayList<Integer> secondAncestors = findAncestors(firstAndSecond.get(1));

//        calculating the depth difference between the second and the first elements.
        int startIndex = secondAncestors.size() - firstAncestors.size();
        /*
        (i > startIndex) needed if first and second are at the same branch;
        In all cases firstAncestors.size() <= secondAncestors.size() -> depth of the first element <= depth of the second element;
        [secondAncestors.get(i).equals(firstAncestors.get(i - startIndex)] comparing elements at the same depth.
        (i > startIndex) - needed if the elements are at the same branch we will start to compare first with itself which is wrong.

            tree        | first(23) second(13) -> LCA-14    | first(6)  second(13) -> LCA- 14 (same branch)
             7          |    7        7  -------------- 1   |   7       7
           /   \        |      \       \                    |    \       \
          21    14      |      14       14  ----------- 2   |     14      14
               /  \     |     /          \                  |      \       \
              23   6    |    23           6  ---------- 3   |       6       6
                    \   |                  \                |                \
                    13  |                   13 -------- 4   |                 13
        */
        for (int i = startIndex; i < secondAncestors.size(); i++) {
            if (secondAncestors.get(i).equals(firstAncestors.get(i - startIndex)) && i > startIndex) {
                commonAncestor = secondAncestors.get(i);
                break;
            }
        }


        return commonAncestor;
    }

    public List<Integer> topView() {
        Queue<BinaryTree> queue = new ArrayDeque<>();
        queue.offer(this);

        Map<BinaryTree, int[]> nodeCoordinatesMap = new HashMap<>();
//                                  new int[]{level, offset}
        nodeCoordinatesMap.put(this, new int[]{0, 0});

        List<Integer> topViewNodes = new ArrayList<>();
        topViewNodes.add(this.key);

        int[] leftmostNodeCoordinates = new int[]{0, 0};
        int[] rightmostNodeCoordinates = new int[]{0, 0};

        while (queue.size() > 0) {

            BinaryTree currentNode = queue.poll();
            int[] currentCoordinates = nodeCoordinatesMap.get(currentNode);

            BinaryTree first = currentNode.first;
            BinaryTree second = currentNode.second;

            if (first != null) {
                queue.offer(first);
                int[] firstCoordinates = new int[]{currentCoordinates[0] + 1, currentCoordinates[1] - 1};
                nodeCoordinatesMap.put(first, firstCoordinates);
                if (isLeftmostNode(leftmostNodeCoordinates, firstCoordinates)) {
                    leftmostNodeCoordinates = firstCoordinates;
                    topViewNodes.add(first.key);
                }
            }
            if (second != null) {
                queue.offer(second);
                int[] secondCoordinates = new int[]{currentCoordinates[0] + 1, currentCoordinates[1] + 1};
                nodeCoordinatesMap.put(second, secondCoordinates);
                if (isRightmostNode(rightmostNodeCoordinates, secondCoordinates)) {
                    rightmostNodeCoordinates = secondCoordinates;
                    topViewNodes.add(second.key);
                }
            }
        }
        return topViewNodes;
    }

    private boolean isRightmostNode(int[] rightmostNodeCoordinates, int[] currentCoordinates) {
        return currentCoordinates[1] > rightmostNodeCoordinates[1];

    }

    private boolean isLeftmostNode(int[] leftmostNodeCoordinates, int[] currentCoordinates) {
        return currentCoordinates[1] < leftmostNodeCoordinates[1];
    }


    private void printCoordinates(Map<BinaryTree, int[]> listCoordinates) {
        for (Map.Entry<BinaryTree, int[]> treeEntry : listCoordinates.entrySet()) {
            System.out.println(treeEntry.getKey().key + "-->" + Arrays.toString(treeEntry.getValue()));
        }
    }

    private ArrayList<Integer> findAncestors(BinaryTree binaryTree) {
        ArrayList<Integer> ancestors = new ArrayList<>();

        while (binaryTree != null) {
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


    private void setParent(BinaryTree child) {
        if (child != null) {
            child.parent = this;
        }
    }
}
