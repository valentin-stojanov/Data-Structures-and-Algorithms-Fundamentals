package exercise.topologicalSorting;

import java.util.*;

public class TopologicalSorting {
    public static void main(String[] args) {

        Map<String, List<String>> graph = new LinkedHashMap<>();
        graph.put("A", new ArrayList<>(List.of("B", "C")));
        graph.put("B", new ArrayList<>(List.of("D", "E")));
        graph.put("C", new ArrayList<>(List.of("F")));
        graph.put("D", new ArrayList<>(List.of("C", "F")));
        graph.put("E", new ArrayList<>(List.of("D")));
        graph.put("F", new ArrayList<>());

        System.out.println(topSort(graph));


    }

    public static Collection<String> topSort(Map<String, List<String>> graph) {
        Map<String, Integer> dependenciesMap = new HashMap<>();

        for (Map.Entry<String, List<String>> entry : graph.entrySet()) {
            String node = entry.getKey();
            List<String> nodeChildren = entry.getValue();
            dependenciesMap.putIfAbsent(node, 0);
            for (String nodeChild : nodeChildren) {
                dependenciesMap.putIfAbsent(nodeChild, 0);
                dependenciesMap.put(nodeChild, dependenciesMap.get(nodeChild) + 1);
            }
        }

        List<String> sorted = new ArrayList<>();

        while (!graph.isEmpty()) {

            String nodeToRemove = dependenciesMap.entrySet()
                    .stream()
                    .filter(e -> e.getValue() == 0)
                    .map(Map.Entry::getKey)
                    .findFirst()
                    .orElse(null);

            if (nodeToRemove == null) {
                break;
            }

            List<String> edgesToBeDecreased = graph.remove(nodeToRemove);
            sorted.add(nodeToRemove);
            dependenciesMap.remove(nodeToRemove);

            for (String node : edgesToBeDecreased) {
                dependenciesMap.put(node, dependenciesMap.get(node) - 1);
            }
        }

        if (graph.size() > 0){
            throw new IllegalStateException("There is one or more cycles in the Graph");
        }
        return sorted;
    }
}
