package dev.cesarcneto;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.joining;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class LinkedListValues {

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

  static String[] iterativeLinkedListValues(Node head) {
    List<String> result = new ArrayList<>();

    Node current = head;
    while (nonNull(current)) {
      result.add(current.value);
      current = current.next;
    }

    return result.toArray(new String[result.size()]);
  }

  static String[] recursiveLinkedListValues(Node head) {
    if (isNull(head))
      return new String[] {};

    return Stream.concat(
        Stream.of(head.value),
        Arrays.stream(recursiveLinkedListValues(head.next)))
        .toArray(String[]::new);
  }

  static String[] recursiveLinkedListValuesV2(Node head) {
    if (isNull(head))
      return new String[] {};

    List<String> values = new ArrayList<>();
    fillValues(head, values);
    return values.toArray(String[]::new);
  }

  private static void fillValues(Node head, List<String> values) {
    if (isNull(head))
      return;

    values.add(head.value);
    fillValues(head.next, values);
  }

  public static void main(String[] args) {
    System.out.println(
        "iterativeLinkedListValues:   " + Arrays.stream(iterativeLinkedListValues(a)).collect(joining(",")));

    System.out.println(
        "recursiveLinkedListValues:   " + Arrays.stream(recursiveLinkedListValues(a)).collect(joining(",")));

    System.out.println(
        "recursiveLinkedListValuesV2: " + Arrays.stream(recursiveLinkedListValuesV2(a)).collect(joining(",")));
  }

}
