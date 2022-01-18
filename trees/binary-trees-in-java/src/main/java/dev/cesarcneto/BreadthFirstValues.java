package dev.cesarcneto;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstValues {

  public record Node(String value, Node left, Node right) {
    public Node(String value) {
      this(value, null, null);
    }
  }

/*
     a
		/ \
	 b   c
	/ \   \
 d	 e   f
*/
    static Node f = new Node("f");
    static Node e = new Node("e");
    static Node d = new Node("d");
    static Node b = new Node("b", d, e);
    static Node c = new Node("c", null, f);
    static Node a = new Node("a", b, c);
    
  private static String[] breadthFirstValues(Node root) {
    if(root == null) return new String[]{};

    List<String> result = new ArrayList<>();

    Queue<Node> queue = new LinkedList<>(List.of(root));
    while(!queue.isEmpty()) {
      Node current = queue.poll();
      result.add(current.value());

      if(current.left() != null) queue.offer(current.left());
      if(current.right() != null) queue.offer(current.right());
    }

    return result.toArray(new String[result.size()]);
  }

  public static void main(String[] args) {
    System.out.println("breadthFirstValues:");
    Arrays.stream(breadthFirstValues(a)).forEach(out::println);
  }

}
