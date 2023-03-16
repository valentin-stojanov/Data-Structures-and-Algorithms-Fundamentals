package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Map<Integer, Node> graph = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            graph.put(i, new Node(i));
            visited.put(i, false);
        }

        for (int i = 0; i < n; i++) {
            String tokens = scanner.nextLine();
            if (tokens.equals("")) {
                continue;
            }

            Node currentNode = graph.get(i);
            List<Integer> nodeList = Arrays.stream(tokens.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (Integer integer : nodeList) {
                Node node = graph.get(integer);
                currentNode.getNeighbours().add(node);
            }
        }

        List<Deque<Integer>> componentsList = new ArrayList<>();
        for (Node node : graph.values()) {

            if (!visited.get(node.value)) {
                Deque<Integer> components = new ArrayDeque<>();
                dfsTraverse(node, graph, visited, components);
                componentsList.add(components);
            }
        }

        for (Deque<Integer> integers : componentsList) {
            System.out.println(integers);
        }
    }

    private static void dfsTraverse(Node node, Map<Integer, Node> graph, Map<Integer, Boolean> visited, Deque<Integer> components) {
        if (!visited.get(node.value)) {
            visited.put(node.value, true);
            components.push(node.value);

            List<Node> neighbours = graph.get(node.value).getNeighbours();
            for (Node neighbour : neighbours) {
                dfsTraverse(neighbour, graph, visited, components);
            }

        }

    }
}
