package dev.cesarcneto;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class LinkedListTraversal {

  static Node a = new Node("a");
  static Node b = new Node("b");
  static Node c = new Node("c");
  static Node d = new Node("d");

  // a -> b -> c -> d -> null
  static {
    a.next = b;
    b.next = c;
    c.next = d;
  }

  static void iterativePrintLinkedListValues(Node head) {
    Node current = head;
    while(nonNull(current)) {
      System.out.println(current.value);
      current = current.next;
    }
  }

  static void recursivePrintLinkedListValues(Node head) {
    if(isNull(head)) return;
    System.out.println(head.value);

    recursivePrintLinkedListValues(head.next);
  }

  public static void main(String[] args) {
    System.out.println("iterativePrintLinkedListValues:");
    iterativePrintLinkedListValues(a);

    System.out.println("recursivePrintLinkedListValues:");
    recursivePrintLinkedListValues(a);
  }
  
}
