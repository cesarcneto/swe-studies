package dev.cesarcneto.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class ShortestPath {

  static List<List<String>> edgeList = List.of(
      List.of("w", "x"),
      List.of("x", "y"),
      List.of("z", "y"),
      List.of("z", "v"),
      List.of("w", "v"));

  static Map<String, List<String>> buildGraph(List<List<String>> edges) {

    Map<String, List<String>> graph = new HashMap<>();
    for (List<String> item : edges) {
      assert item.size() == 2;
      String[] edge = item.toArray(new String[item.size()]);
      graph.merge(edge[0], List.of(edge[1]), remapEdge());
      graph.merge(edge[1], List.of(edge[0]), remapEdge());
    }

    return graph;
  }

  private static BiFunction<? super List<String>, ? super List<String>, ? extends List<String>> remapEdge() {
    return (oldV, newV) -> {
      if (oldV == null)
        return newV;

      List<String> newList = new ArrayList<>(oldV);
      newList.addAll(newV);
      return newList;
    };
  }

  public record NodePathLength(String label, int pathLength) {
    public String toString() {
      return String.format("[%s %s]", label, pathLength);
    }
  }

  static int shortestPath(List<List<String>> edges, String src, String dst) {

    Map<String, List<String>> graph = buildGraph(edges);
    Deque<NodePathLength> queue = new ArrayDeque<>(List.of(new NodePathLength(src, 0)));
    Set<String> visitedNodes = new LinkedHashSet<>(List.of(src));

    while (!queue.isEmpty()) {

      NodePathLength currentNode = queue.poll();
      if(currentNode.label().equals(dst)) {
        return currentNode.pathLength();
      }

      for(String neighbour : graph.get(currentNode.label())) {
        if(!visitedNodes.contains(neighbour)) {
          visitedNodes.add(neighbour);
          queue.offer(new NodePathLength(neighbour, currentNode.pathLength() + 1));
        }
      }
    }
    
    return -1;
  }

  public static void main(String[] args) {
    System.out.println("shortestPath:");
    System.out.println(
        String.format("The shortest path of graph %s\nhas %s edges", buildGraph(edgeList), shortestPath(edgeList, "w", "z")));
  }

}
