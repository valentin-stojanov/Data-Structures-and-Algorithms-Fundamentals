package implementations;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

public class TreeFactory {
    private Map<Integer, Tree<Integer>> nodesByKeys;

    public TreeFactory() {
        this.nodesByKeys = new LinkedHashMap<>();
    }

    public Tree<Integer> createTreeFromStrings(String[] input) {
        for (String tuple : input) {
            int[] nodeChild = Arrays.stream(tuple.split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            Tree<Integer> parentNode = createNodeByKey(nodeChild[0]);
            Tree<Integer> childNode = createNodeByKey(nodeChild[1]);

            addEdge(parentNode, childNode);
            parentNode.addChild(childNode);
        }

        return this.getRoot();
    }


    private Tree<Integer> getRoot() {
        for (Tree<Integer> node : nodesByKeys.values()) {
            if (node.getParent() == null) {
                return node;
            }
        }
        return null;
    }

    private Tree<Integer> createNodeByKey(int key) {
        Tree<Integer> tree = this.nodesByKeys.putIfAbsent(key, new Tree<>(key));

        if (tree == null /* when the tree is created for the first time*/) {
            return this.nodesByKeys.get(key);
        }
        return tree;
    }

    private void addEdge(Tree<Integer> parent, Tree<Integer> child) {
        child.setParent(parent);
    }
}



