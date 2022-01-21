package dev.cesarcneto;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class GetNodeValue {

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

  static String iterativeGetNodeValue(Node head, int index) {
    int counter = 0;

    Node current = head;
    while (nonNull(current)) {
      if (counter == index) {
        return current.value;
      }
      counter++;
      current = current.next;
    }

    return null;
  }

  static String recursiveGetNodeValue(Node head, int index) {
    if (isNull(head))
      return null;

    if (index == 0)
      return head.value;

    return recursiveGetNodeValue(head.next, --index);
  }

  public static void main(String[] args) {
    System.out.println(String.format("iterativeGetNodeValue(2): %s", iterativeGetNodeValue(a, 2)));

    System.out.println(String.format("recursiveLinkedListFind(2): %s", recursiveGetNodeValue(a, 2)));
  }

}
