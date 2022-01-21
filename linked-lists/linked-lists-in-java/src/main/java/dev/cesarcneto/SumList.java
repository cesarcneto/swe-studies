package dev.cesarcneto;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class SumList {

  public static class IntNode {

    final int value;
    IntNode next;

    public IntNode(int value) {
      this.value = value;
      this.next = null;
    }
  }

  static IntNode a = new IntNode(2);
  static IntNode b = new IntNode(8);
  static IntNode c = new IntNode(3);
  static IntNode d = new IntNode(7);

  // 2 -> 8 -> 3 -> 7 -> null
  static {
    a.next = b;
    b.next = c;
    c.next = d;
  }

  static int iterativeSumList(IntNode head) {
    int sum = 0;

    IntNode current = head;
    while (nonNull(current)) {
      sum += current.value;
      current = current.next;
    }

    return sum;
  }

  static int recursiveSumList(IntNode head) {
    if (isNull(head))
      return 0;

    return head.value + recursiveSumList(head.next);
  }

  public static void main(String[] args) {
    System.out.println("iterativeSumList:   " + iterativeSumList(a));

    System.out.println("recursiveSumList:   " + recursiveSumList(a));

  }

}
