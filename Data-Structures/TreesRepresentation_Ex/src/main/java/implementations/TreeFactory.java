package implementations;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

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

            this.nodesByKeys.putIfAbsent(nodeChild[0], parentNode);
            this.nodesByKeys.putIfAbsent(nodeChild[1], childNode);

            nodesByKeys.get(nodeChild[1]).setParent(this.nodesByKeys.get(nodeChild[0]));

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

    public Tree<Integer> createNodeByKey(int key) {
        return new Tree<>(key);
    }

    public void addEdge(Tree<Integer> parent, Tree<Integer> child) {
        child.setParent(parent);
    }
}



