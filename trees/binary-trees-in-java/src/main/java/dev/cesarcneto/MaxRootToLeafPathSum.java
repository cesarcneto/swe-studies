package dev.cesarcneto;

import static java.lang.Math.max;

public class MaxRootToLeafPathSum {

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
 4	 2   1
*/
    static Node one = new Node(1);
    static Node two = new Node(2);
    static Node four = new Node(4);
    static Node eleven = new Node(11, four, two);
    static Node three = new Node(3, null, one);
    static Node five = new Node(5, eleven, three);
    

  private static int maxRootToLeafPathSumDpf(Node root, int rootToLeafPathSum) {
    if(root == null) return rootToLeafPathSum;

    int currentValue = root.value();

    int rightValue = maxRootToLeafPathSumDpf(root.right(), rootToLeafPathSum + currentValue);
    int leftValue = maxRootToLeafPathSumDpf(root.left(), rootToLeafPathSum + currentValue);
    
    return max(rightValue, leftValue);
  }

  private static int maxRootToLeafPathSumDpfV2(Node root) {
    if(root == null) return Integer.MIN_VALUE;
    if(root.left() == null && root.right() == null) return root.value();

    int rightValue = maxRootToLeafPathSumDpfV2(root.right());
    int leftValue = maxRootToLeafPathSumDpfV2(root.left());
    
    return max(rightValue, leftValue) + root.value();
  }

  public static void main(String[] args) {
    int initialValue = 0;
    System.out.println(String.format("maxRootToLeafPathSumDpf -> %s", maxRootToLeafPathSumDpf(five, initialValue)));

    System.out.println(String.format("maxRootToLeafPathSumDpfV2 -> %s", maxRootToLeafPathSumDpfV2(five)));
  }

}
