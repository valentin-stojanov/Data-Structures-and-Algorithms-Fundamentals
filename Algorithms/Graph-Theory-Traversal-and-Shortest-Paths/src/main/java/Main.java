import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args)  {
        Scanner scanner = new Scanner(System.in);
        int numberOfLines = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();
        String numbers;
        for (int i = 0; i < numberOfLines; i++) {
            graph.add(new ArrayList<>());
            numbers = scanner.nextLine();
            if (numbers.trim().equals("")) {
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
        Map<String, Integer> dependenciesCount = getDependenciesCount(graph);

        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()){
            String key = graph.keySet()
                    .stream()
                    .filter(k -> dependenciesCount.get(k) == 0)
                    .findFirst()
                    .orElse(null);

            if (key == null){
                break;
            }

            for (String child : graph.get(key)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            sorted.add(key);
            graph.remove(key);

        }

        if (!graph.isEmpty()){
            throw new IllegalArgumentException();
        }

        return sorted;
    }

    private static Map<String, Integer> getDependenciesCount(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesCount = new LinkedHashMap<>();

        for (Map.Entry<String, List<String>> node : graph.entrySet()) {
            dependenciesCount.putIfAbsent(node.getKey(), 0);
            for (String child : node.getValue()) {
                dependenciesCount.putIfAbsent(child, 0);
                dependenciesCount.put(child, dependenciesCount.get(child) + 1);
            }
        }
        return dependenciesCount;
    }
}
