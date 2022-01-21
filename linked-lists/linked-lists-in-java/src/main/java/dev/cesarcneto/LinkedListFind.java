package dev.cesarcneto;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.Objects;

public class LinkedListFind {

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

  static boolean iterativeLinkedListFind(Node head, String target) {
    Node current = head;
    while (nonNull(current)) {
      if (Objects.equals(current.value, target)) {
        return true;
      }
      current = current.next;
    }

    return false;
  }

  static boolean recursiveLinkedListFind(Node head, String target) {
    if (isNull(head))
      return false;

    if (Objects.equals(head.value, target))
      return true;

    return recursiveLinkedListFind(head.next, target);
  }

  public static void main(String[] args) {
    System.out.println(String.format("iterativeLinkedListFind('c'): %s", iterativeLinkedListFind(a, "c")));

    System.out.println(String.format("recursiveLinkedListFind('c'): %s", recursiveLinkedListFind(a, "c")));
  }

}
