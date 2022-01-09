package dev.cesarcneto.graphs;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConnectedComponentsCount {

  static Map<String, List<String>> graph = Map.of(
      "3", List.of(),
      "4", List.of("6"),
      "6", List.of("4", "5", "7", "8"),
      "8", List.of("6"),
      "7", List.of("6"),
      "5", List.of("6"),
      "1", List.of("2"),
      "2", List.of("1"));

  static int connectedComponentsCount(Map<String, List<String>> graph) {

    Set<String> visitedNodes = new LinkedHashSet<String>();
    int count = 0;

    for (String node : graph.keySet()) {
      if (explore(graph, node, visitedNodes))
        count++;
    }

    return count;
  }

  private static boolean explore(Map<String, List<String>> graph, String node, Set<String> visited) {
    if (visited.contains(node))
      return false;

    visited.add(node);

    for (String neighbour : graph.get(node)) {
      explore(graph, neighbour, visited);
    }

    return true;
  }

  public static void main(String[] args) {
    System.out.println("connectedComponentsCount:");
    System.out.println(String.format("The graph %s\nhas %s connected components", graph, connectedComponentsCount(graph)));
  }

}
