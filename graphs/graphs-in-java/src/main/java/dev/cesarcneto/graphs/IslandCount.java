package dev.cesarcneto.graphs;

import static java.lang.String.format;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class IslandCount {

  static String[][] grid = new String[][] {
      { "W", "L", "W", "W", "W" },
      { "W", "L", "W", "W", "W" },
      { "W", "W", "W", "L", "W" },
      { "W", "W", "L", "L", "W" },
      { "L", "W", "W", "L", "L" },
      { "L", "L", "W", "W", "W" }
  };

  static int islandCount(String[][] grid) {

    Set<String> visitedNodes = new LinkedHashSet<String>();
    int count = 0;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        if (explore(grid, row, col, visitedNodes))
          count++;
      }
    }

    return count;
  }

  private static boolean explore(String[][] grid, int row, int col, Set<String> visited) {

    boolean isRowInbounds = row >= 0 && row < grid.length;
    boolean isColInbounds = col >= 0 && col < grid[0].length;
    if(!isRowInbounds || !isColInbounds) return false;

    if("W".equals(grid[row][col])) return false;

    String node = format("%s,%s", row, col);
    if (visited.contains(node))
      return false;

    visited.add(node);

    explore(grid, row - 1, col, visited); // explore top
    explore(grid, row + 1, col, visited); // explore bottom
    explore(grid, row, col - 1, visited); // explore left
    explore(grid, row, col + 1, visited); // explore right

    return true;
  }

  public static void main(String[] args) {
    System.out.println("islandCount:");
    System.out.println(
        """
            The input grid yields the graph 
            %s
            that has %s islands
            """.stripIndent().formatted(Arrays.stream(grid).map(Arrays::toString).collect(Collectors.joining("\n")), islandCount(grid)));
  }

}
