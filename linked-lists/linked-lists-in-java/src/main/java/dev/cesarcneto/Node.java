package dev.cesarcneto;

public class Node {

  final String value;
  Node next;

  public Node(String value) {
    this.value = value;
    this.next = null;
  }

  @Override
  public String toString() {
    return String.format("%s -> %s", value, next);
  }

}
