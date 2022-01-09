package dev.cesarcneto.graphs;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LargestComponentCount {

  static Map<String, List<String>> graph = Map.of(
      "0", List.of("8", "1", "5"),
      "1", List.of("0"),
      "5", List.of("0", "8"),
      "8", List.of("0", "5"),
      "2", List.of("3", "4"),
      "3", List.of("2", "4"),
      "4", List.of("3", "2"));

  static int largestComponentCount(Map<String, List<String>> graph) {

    Set<String> visitedNodes = new LinkedHashSet<String>();
    int largestComponentSize = 0;

    for (String node : graph.keySet()) {
      int size = exploreSize(graph, node, visitedNodes);
      if (size > largestComponentSize) largestComponentSize = size;  
    }

    return largestComponentSize;
  }

  private static int exploreSize(Map<String, List<String>> graph, String node, Set<String> visited) {
    if (visited.contains(node))
      return 0; 

    visited.add(node);

    int size = 1; // current node count

    for (String neighbour : graph.get(node)) {
      size += exploreSize(graph, neighbour, visited);
    }

    return size;
  }

  public static void main(String[] args) {
    System.out.println("largestComponentCount:");
    System.out.println(String.format("The largest component of graph %s\nhas %s components", graph, largestComponentCount(graph)));
  }

}
