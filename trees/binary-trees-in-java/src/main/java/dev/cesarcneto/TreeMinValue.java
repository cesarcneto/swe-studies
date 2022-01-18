package dev.cesarcneto;

import static java.lang.Math.min;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeMinValue {

  public record Node(int value, Node left, Node right) {
    public Node(int value) {
      this(value, null, null);
    }
  }

/*
     5
		/ \
	 11  3
	/ \   \
 4	 15  12
*/
    static Node twelve = new Node(12);
    static Node fifthteen = new Node(15);
    static Node four = new Node(4);
    static Node eleven = new Node(11, four, fifthteen);
    static Node three = new Node(3, null, twelve);
    static Node five = new Node(5, eleven, three);
    
    private static int treeMinValueBdf(Node root) {
      if(root == null) return 0;
  
      int minValue = Integer.MAX_VALUE;

      Queue<Node> queue = new LinkedList<>(List.of(root));
      while(!queue.isEmpty()) {
        Node current = queue.poll();
        minValue = min(minValue, current.value());
  
        if(current.left() != null) queue.offer(current.left());
        if(current.right() != null) queue.offer(current.right());
      }
  
      return minValue;
    }

  private static int treeMinValueDpf(Node root) {
    if(root == null) return Integer.MAX_VALUE;

    int rightValue = treeMinValueDpf(root.right());
    int leftValue = treeMinValueDpf(root.left());
    return min(root.value(), min(rightValue, leftValue));
  }

  public static void main(String[] args) {
    System.out.println(String.format("treeMinValueBdf -> %s", treeMinValueBdf(five)));

    System.out.println(String.format("treeMinValueDpf -> %s", treeMinValueDpf(five)));
  }

}
