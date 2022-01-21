package dev.cesarcneto;

import static dev.cesarcneto.LinkedListValues.iterativeLinkedListValues;
import static dev.cesarcneto.LinkedListValues.recursiveLinkedListValues;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;

public class ReverseLinkedList {

  static Node createLinkedList() {
    // a -> b -> c -> d -> null
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    a.next = b;
    b.next = c;
    c.next = d;

    return a;
  }

  static Node iterativeReverseLinkedList(Node head) {

    Node prev = null;
    Node curr = head;
    
    while (nonNull(curr)) {
      Node next = curr.next;
      curr.next = prev;
      prev = curr;
      curr = next;
    }

    return prev;
  }

  static Node recursiveReverseLinkedList(Node head) {
    return recursiveReverseLinkedList(head, null);
  }

  private static Node recursiveReverseLinkedList(Node head, Node prev) {
    if (isNull(head))
      return prev;

    Node next = head.next;
    head.next = prev;

    return recursiveReverseLinkedList(next, head);
  }

  public static void main(String[] args) {

    Node iterativeLinkedList = createLinkedList();

    String iterativeBefore = Arrays.stream(iterativeLinkedListValues(iterativeLinkedList)).collect(joining(","));
    String iterativeAfter = Arrays.stream(
        iterativeLinkedListValues(iterativeReverseLinkedList(iterativeLinkedList)))
        .collect(joining(","));

    System.out.println(String.format("iterativeReverseLinkedList: %s ==> %s", iterativeBefore, iterativeAfter));

    Node recursiveLinkedList = createLinkedList();

    String recursiveBefore = Arrays.stream(iterativeLinkedListValues(recursiveLinkedList)).collect(joining(","));
    String recursiveAfter = Arrays.stream(
        recursiveLinkedListValues(recursiveReverseLinkedList(recursiveLinkedList)))
        .collect(joining(","));

    System.out.println(String.format("recursiveReverseLinkedList: %s ==> %s", recursiveBefore, recursiveAfter));
  }

}
