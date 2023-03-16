import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        List<List<Integer>> graph = new ArrayList<>();
//        String numbers;
//        for (int i = 0; i < n; i++) {
//            graph.add(new ArrayList<>());
//            numbers = scanner.nextLine();
//            if (numbers.trim().equals("")) {
//                continue;
//            }
//
//            List<Integer> integers = Arrays.stream(numbers.split("\\s+"))
//                    .map(Integer::parseInt)
//                    .collect(Collectors.toList());
//            graph.get(graph.size() - 1).addAll(integers);
//
//        }
//
//        getConnectedComponents(graph);

        for (int i = 0; i < n + 1; i++) {
            graph.add(new ArrayList<>());
        }

        int edges = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < edges; i++) {
            int[] edge = Arrays.stream(scanner.nextLine().split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int parent = edge[0];
            int child = edge[1];

            graph.get(parent)
                    .add(child);
        }

        int startNode = Integer.parseInt(scanner.nextLine());
        int endNode = Integer.parseInt(scanner.nextLine());

        List<Integer> path = getShortestPath(graph, startNode, endNode);

        printResult(path);
    }

    private static void printResult(List<Integer> path) {
        StringBuilder builder = new StringBuilder();
        builder.append("Shortest path length is: ")
                .append(path.size() -1)
                .append(System.lineSeparator());

        for (int i = path.size() - 1; i >= 0 ; i--) {
            builder.append(path.get(i))
                    .append(" ");
        }
        System.out.println(builder);
    }

    private static List<Integer> getShortestPath(List<List<Integer>> graph, int startNode, int endNode) {
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.size() + 1];
        int[] prevNodes = new int[graph.size() + 1];

        for (int i = 0; i < prevNodes.length; i++) {
            prevNodes[i] = -1;
        }
        Deque<Integer> queue = new ArrayDeque<>();

        queue.offer(startNode);

        while (queue.size() > 0){
            int node = queue.poll();
            visited[node] = true;
            if (node == endNode){
                break;
            }

            for (Integer child : graph.get(node)) {
                if (!visited[child]){
                    visited[child] = true;
                    prevNodes[child] = node;
                    queue.offer(child);
                }
            }
        }

        path.add(endNode);
        int prevNode = prevNodes[endNode];

        while (prevNode != -1){
            path.add(prevNode);
            prevNode = prevNodes[prevNode];
        }

        return path;
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

        while (!graph.isEmpty()) {
            String key = graph.keySet()
                    .stream()
                    .filter(k -> dependenciesCount.get(k) == 0)
                    .findFirst()
                    .orElse(null);

            if (key == null) {
                break;
            }

            for (String child : graph.get(key)) {
                dependenciesCount.put(child, dependenciesCount.get(child) - 1);
            }

            sorted.add(key);
            graph.remove(key);

        }

        if (!graph.isEmpty()) {
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
