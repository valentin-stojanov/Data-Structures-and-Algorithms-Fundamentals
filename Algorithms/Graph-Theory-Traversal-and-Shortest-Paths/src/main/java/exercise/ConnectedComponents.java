package exercise;

import java.util.*;
import java.util.stream.Collectors;

public class ConnectedComponents {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String tokens = scanner.nextLine();
            List<Integer> neighbours;
            if (tokens.equals("")) {
                neighbours = new ArrayList<>();
            } else {
                neighbours = Arrays.stream(tokens
                                .split("\\s+"))
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());
            }

            graph.add(neighbours);
        }

        boolean[] visited = new boolean[graph.size()];
        List<Deque<Integer>> componentsList = new ArrayList<>();

        for (int node = 0; node < graph.size(); node++) {
            if (!visited[node]) {
                Deque<Integer> components = new ArrayDeque<>();
                dfsTraverse(node, graph, visited, components);
                componentsList.add(components);
            }
        }



    }

    private static void dfsTraverse(int node, List<List<Integer>> graph, boolean[] visited, Deque<Integer> components) {
        visited[node] = true;
        components.push(node);
        List<Integer> neighbours = graph.get(node);

        for (Integer neighbour : neighbours) {
            if (!visited[neighbour]) {
                dfsTraverse(neighbour, graph, visited, components);
            }
        }
    }
}
