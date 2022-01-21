package dev.cesarcneto;

import static dev.cesarcneto.LinkedListValues.iterativeLinkedListValues;
import static dev.cesarcneto.LinkedListValues.recursiveLinkedListValues;
import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;

public class ZipperLists {

  static Node createLinkedList() {
    // a -> b -> c -> d -> e -> f -> null
    Node a = new Node("a");
    Node b = new Node("b");
    Node c = new Node("c");
    Node d = new Node("d");
    Node e = new Node("e");
    Node f = new Node("f");

    a.next = b;
    b.next = c;
    c.next = d;
    d.next = e;
    e.next = f;

    return a;
  }

  static Node createAnotherLinkedList() {
    // q -> r -> null
    Node q = new Node("q");
    Node r = new Node("r");

    q.next = r;

    return q;
  }

  static Node iterativeZipLinkedList(Node head1, Node head2) {

    Node tail = head1;
    Node current1 = head1.next;
    Node current2 = head2;

    int counter = 0;

    while (nonNull(current1) && nonNull(current2)) {

      if (counter % 2 == 0) {
        tail.next = current2;
        current2 = current2.next;
      } else {
        tail.next = current1;
        current1 = current1.next;
      }

      tail = tail.next;
      counter++;
    }

    if (nonNull(tail)) {
      if (nonNull(current1) && nonNull(current1.next)) {
        tail.next = current1;
      }

      if (nonNull(current2) && nonNull(current2.next)) {
        tail.next = current2;
      }
    }

    return head1;
  }

  static Node recursiveZipLinkedList(Node head1, Node head2) {
    if (isNull(head1) && isNull(head2)) return null;
    if (isNull(head1)) return head2;
    if (isNull(head2)) return head1;

    Node next1 = head1.next;
    Node next2 = head2.next;

    head1.next = head2;
    head2.next = recursiveZipLinkedList(next1, next2);
    return head1;
  }

  public static void main(String[] args) {

    Node iterativeLinkedList = createLinkedList();
    Node iterativeAnotherLinkedList = createAnotherLinkedList();

    String iterativeBefore1 = Arrays.stream(iterativeLinkedListValues(iterativeLinkedList)).collect(joining(","));
    String iterativeBefore2 = Arrays.stream(iterativeLinkedListValues(iterativeAnotherLinkedList))
        .collect(joining(","));
    String iterativeAfter = Arrays.stream(
        iterativeLinkedListValues(iterativeZipLinkedList(iterativeLinkedList, iterativeAnotherLinkedList)))
        .collect(joining(","));

    System.out.println(
        String.format("iterativeZipLinkedList: %s + %s ==> %s", iterativeBefore1, iterativeBefore2, iterativeAfter));

    Node recursiveLinkedList = createLinkedList();
    Node recursiveAnotherLinkedList = createAnotherLinkedList();

    String recursiveBefore1 = Arrays.stream(recursiveLinkedListValues(recursiveLinkedList)).collect(joining(","));
    String recursiveBefore2 = Arrays.stream(recursiveLinkedListValues(recursiveAnotherLinkedList))
        .collect(joining(","));
    String recursiveAfter = Arrays.stream(
        recursiveLinkedListValues(recursiveZipLinkedList(recursiveLinkedList, recursiveAnotherLinkedList)))
        .collect(joining(","));

    System.out.println(
        String.format("recursiveZipLinkedList: %s + %s ==> %s", recursiveBefore1, recursiveBefore2, recursiveAfter));
  }

}
