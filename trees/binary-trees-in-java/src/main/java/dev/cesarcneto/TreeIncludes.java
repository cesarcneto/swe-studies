package dev.cesarcneto;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class TreeIncludes {

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
    
    private static boolean treeIncludesBdf(Node root, String target) {
      if(root == null) return false;
  
      Deque<Node> queue = new ArrayDeque<>(List.of(root));
      while(!queue.isEmpty()) {
        Node current = queue.poll();
        if (current.value().equals(target)) return true;
  
        if(current.left() != null) queue.offer(current.left());
        if(current.right() != null) queue.offer(current.right());
      }
  
      return false;
    }

  private static boolean treeIncludesDpf(Node root, String target) {
    if(root == null) return false;

    if(root.value().equals(target)) return true;

    return treeIncludesDpf(root.right(), target) || treeIncludesDpf(root.left(), target);
  }

  public static void main(String[] args) {
    System.out.println(String.format("treeIncludesBdf: includes(%s)? -> %s", e.value(), treeIncludesBdf(a, "e")));

    System.out.println(String.format("treeIncludesDpf: includes(%s)? -> %s", e.value(), treeIncludesDpf(a, "e")));
  }

}
