package dev.cesarcneto.graphs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class UndirectedPath {

  static List<List<String>> edgeList = List.of(
      List.of("i", "j"),
      List.of("k", "i"),
      List.of("m", "k"),
      List.of("k", "l"),
      List.of("o", "n"));

  static Map<String, List<String>> buildGraph(List<List<String>> edges) {

    Map<String, List<String>> graph = new HashMap<>();
    for (List<String> item : edgeList) {
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

  static boolean hasUndirectedPath(List<List<String>> edges, String src, String dst) {
    Map<String, List<String>> graph = buildGraph(edges);
    return hasPath(graph, src, dst, new LinkedHashSet<>());
  }

  private static boolean hasPath(Map<String, List<String>> graph, String src, String dst, Set<String> visitedNodes) {

    if (src == dst)
      return true;

    if (visitedNodes.contains(src))
      return false;
    visitedNodes.add(src);

    for (String neighbor : graph.get(src)) {
      if (hasPath(graph, neighbor, dst, visitedNodes))
        return true;
    }

    return false;
  }

  public static void main(String[] args) {
    System.out.println("hasUndirectedPath:");
    System.out.println(String.format("Has undirectedPath from j to m? => %s", hasUndirectedPath(edgeList, "j", "m")));
  }

}
