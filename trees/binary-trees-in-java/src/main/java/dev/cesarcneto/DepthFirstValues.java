package dev.cesarcneto;

import static java.lang.System.out;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class DepthFirstValues {

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
    
  private static String[] iterativeDepthFirstValues(Node root) {
    if(root == null) return new String[]{};

    List<String> result = new ArrayList<>();

    Deque<Node> stack = new ArrayDeque<>(List.of(root));
    while(!stack.isEmpty()) {
      Node current = stack.pop();
      result.add(current.value());

      if(current.right() != null) stack.push(current.right());
      if(current.left() != null) stack.push(current.left());
    }

    return result.toArray(new String[result.size()]);
  }

  private static String[] recursiveDepthFirstValues(Node root) {
    if(root == null) return new String[]{};

    String[] rightValues = recursiveDepthFirstValues(root.right());
    String[] leftValues = recursiveDepthFirstValues(root.left());

    List<String> result = new ArrayList<>();
    result.add(root.value());
    result.addAll(Arrays.asList(leftValues));
    result.addAll(Arrays.asList(rightValues));
    return result.toArray(new String[result.size()]);
  }

  public static void main(String[] args) {
    System.out.println("iterativeDepthFirstValues:");
    Arrays.stream(iterativeDepthFirstValues(a)).forEach(out::println);

    System.out.println("recursiveDepthFirstValues:");
    Arrays.stream(recursiveDepthFirstValues(a)).forEach(out::println);
  }

}
