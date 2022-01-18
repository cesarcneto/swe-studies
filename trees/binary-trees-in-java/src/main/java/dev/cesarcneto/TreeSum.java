package dev.cesarcneto;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeSum {

  public record Node(int value, Node left, Node right) {
    public Node(int value) {
      this(value, null, null);
    }
  }

/*
     3
		/ \
	 11  4
	/ \   \
 4	 2   1
*/
    static Node one = new Node(1);
    static Node two = new Node(2);
    static Node four2 = new Node(4);
    static Node eleven = new Node(11, four2, two);
    static Node four1 = new Node(4, null, one);
    static Node three = new Node(3, eleven, four1);
    
    private static int treeSumBdf(Node root) {
      if(root == null) return 0;
  
      int sum = 0;

      Queue<Node> queue = new LinkedList<>(List.of(root));
      while(!queue.isEmpty()) {
        Node current = queue.poll();
        sum += current.value();
  
        if(current.left() != null) queue.offer(current.left());
        if(current.right() != null) queue.offer(current.right());
      }
  
      return sum;
    }

  private static int treeSumDpf(Node root) {
    if(root == null) return 0;

    return root.value + treeSumDpf(root.right()) + treeSumDpf(root.left());
  }

  public static void main(String[] args) {
    System.out.println(String.format("treeSumBdf -> %s", treeSumBdf(three)));

    System.out.println(String.format("treeSumDpf -> %s", treeSumDpf(three)));
  }

}
