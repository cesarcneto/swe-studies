package dev.cesarcneto.graphs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

public class BdfAndDpfTravesals {

  static Map<String, List<String>> graph = Map.of(
          "a", List.of("b", "c"),
          "b", List.of("d"),
          "c", List.of("e"),
          "d", List.of("f"),
          "e", List.of(),
          "f", List.of());

  static void depthFirstPrint(Map<String, List<String>> graph, String source) {
    Deque<String> stack = new ArrayDeque<>(List.of(source));
    
    while (!stack.isEmpty()) {
      String currentNode = stack.pop();
      System.out.println(currentNode);

      for (String neighbor : graph.get(currentNode)) {
        stack.push(neighbor);
      }
    }
  }

  static void breadthFirstPrint(Map<String, List<String>> graph, String source) {
    Deque<String> queue = new ArrayDeque<>(List.of(source));

    while(!queue.isEmpty()) {
      String currentNode = queue.poll();
      System.out.println(currentNode);

      for(String neighbour : graph.get(currentNode)){
        queue.offer(neighbour);
      }
    }
  }

  static void depthFirstRecursivePrint(Map<String, List<String>> graph, String source) {
    System.out.println(source);

    for (String neighbor : graph.get(source)) {
      depthFirstRecursivePrint(graph, neighbor);
    }
  }

  public static void main(String[] args) {
    System.out.println("depthFirstPrint:");
    depthFirstPrint(graph, "a");
    System.out.println("--------------------");
    System.out.println("depthFirstRecursivePrint:");
    depthFirstRecursivePrint(graph, "a");
    System.out.println("--------------------");
    System.out.println("breadthFirstPrint:");
    breadthFirstPrint(graph, "a");
  }

}
