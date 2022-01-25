package dev.cesarcneto.graphs;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HasPath {

  static Map<String, List<String>> graph = Map.of(
      "g", List.of("h"),
      "h", List.of(),
      "i", List.of("g", "k"),
      "j", List.of("i"),
      "k", List.of());

  static boolean hasPathBdf(Map<String, List<String>> graph, String srcNode, String dstNode) {
    Deque<String> queue = new LinkedList<>(List.of(srcNode));

    while (!queue.isEmpty()) {
      String currentNode = queue.poll();
      if (currentNode == dstNode)
        return true;

      for (String neighbour : graph.get(currentNode)) {
        queue.offer(neighbour);
      }
    }

    return true;
  }

  static boolean hasPathDpf(Map<String, List<String>> graph, String srcNode, String dstNode) {
    if (Objects.equals(srcNode, dstNode))
      return true;

    for (String neighbour : graph.get(srcNode)) {
      if (hasPathDpf(graph, neighbour, dstNode)) {
        return true;
      }
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println("hasPathDpf:");
    System.out.println(String.format("Has j to k path? => %s", hasPathDpf(graph, "j", "k")));
    System.out.println("---------------------");
    System.out.println("hasPathBdf:");
    System.out.println(String.format("Has j to k path? => %s", hasPathBdf(graph, "j", "k")));
  }

}
