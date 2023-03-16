package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedComponents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        /*
        9
        3 6
        3 4 5 6
        8
        0 1 5
        1 6
        1 3
        0 1 4

        2
         */

        Map<Integer, Vertex> graph = new HashMap<>();
        Map<Integer, Boolean> visited = new HashMap<>();
        int n = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < n; i++) {
            graph.put(i, new Vertex(i));
            visited.put(i, false);
        }

        for (int i = 0; i < n; i++) {
            String tokens = scanner.nextLine();
            if (tokens.equals("")) {
                continue;
            }

            Vertex currentNode = graph.get(i);
            List<Integer> nodeList = Arrays.stream(tokens.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            for (Integer integer : nodeList) {
                Vertex node = graph.get(integer);
                currentNode.getNeighbours().add(node);
            }
        }

        List<Deque<Integer>> componentsList = new ArrayList<>();
        for (Vertex node : graph.values()) {

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

    private static void dfsTraverse(Vertex node, Map<Integer, Vertex> graph, Map<Integer, Boolean> visited, Deque<Integer> components) {
        if (!visited.get(node.value)) {
            visited.put(node.value, true);
            components.push(node.value);

            List<Vertex> neighbours = graph.get(node.value).getNeighbours();
            for (Vertex neighbour : neighbours) {
                dfsTraverse(neighbour, graph, visited, components);
            }

        }

    }
}
