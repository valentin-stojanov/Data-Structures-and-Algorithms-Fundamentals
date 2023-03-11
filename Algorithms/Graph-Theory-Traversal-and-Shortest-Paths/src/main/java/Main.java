import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfLines = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();
        String numbers;
        for (int i = 0; i < numberOfLines; i++) {
            graph.add(new ArrayList<>());
            numbers = scanner.nextLine();
            if (numbers.trim() == "") {
                continue;
            }

            List<Integer> integers = Arrays.stream(numbers.split("\\s+"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
            graph.get(graph.size() - 1).addAll(integers);

        }

        getConnectedComponents(graph);

    }

    public static List<Deque<Integer>> getConnectedComponents(List<List<Integer>> graph) {
        List<Deque<Integer>> componentsList = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];


        for (int i = 0; i < graph.size(); i++) {

            if (!visited[i]) {
                Deque<Integer> deque = new ArrayDeque<>();
                dfsTravers(i, visited, deque, graph);
                componentsList.add(deque);
            }
        }
        return componentsList;
    }

    private static void dfsTravers(int node, boolean[] visited, Deque<Integer> integerDeque, List<List<Integer>> graph) {
        if (!visited[node]) {
            visited[node] = true;
            for (Integer child : graph.get(node)) {
                dfsTravers(child, visited, integerDeque, graph);
            }
            integerDeque.offer(node);
        }

    }


    public static Collection<String> topSort(Map<String, List<String>> graph) {
        throw new AssertionError("Not Implemented");
    }
}
