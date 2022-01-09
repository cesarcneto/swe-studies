package dev.cesarcneto.graphs;

import static java.lang.String.format;
import static java.util.stream.Collectors.joining;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class MinimumIslandSize {

  static String[][] grid = new String[][] {
      { "W", "L", "W", "W", "L", "W" },
      { "L", "L", "W", "W", "L", "W" },
      { "W", "L", "W", "W", "W", "W" },
      { "W", "W", "W", "L", "L", "W" },
      { "W", "W", "W", "L", "L", "W" },
      { "W", "W", "W", "L", "W", "W" }
  };

  static int minimumIslandSize(String[][] grid) {

    Set<String> visitedNodes = new LinkedHashSet<String>();
    int minSize = Integer.MAX_VALUE;

    for (int row = 0; row < grid.length; row++) {
      for (int col = 0; col < grid[row].length; col++) {
        int landSize = countLandSize(grid, row, col, visitedNodes);
        if (landSize > 0) {
          minSize = Math.min(minSize, landSize);
        }
      }
    }

    return minSize == Integer.MAX_VALUE ? 0 : minSize;
  }

  private static int countLandSize(String[][] grid, int row, int col, Set<String> visited) {

    boolean isRowInbounds = row >= 0 && row < grid.length;
    boolean isColInbounds = col >= 0 && col < grid[0].length;
    if (!isRowInbounds || !isColInbounds)
      return 0;

    if ("W".equals(grid[row][col]))
      return 0;

    String node = format("%s,%s", row, col);
    if (visited.contains(node))
      return 0;

    visited.add(node);

    return 1 + countLandSize(grid, row - 1, col, visited)
        + countLandSize(grid, row + 1, col, visited)
        + countLandSize(grid, row, col - 1, visited)
        + countLandSize(grid, row, col + 1, visited);
  }

  public static void main(String[] args) {
    System.out.println("minimumIslandSize:");
    System.out.println(
        """
            The input grid:
            %s
            """.stripIndent().formatted(Arrays.stream(grid).map(Arrays::toString).collect(joining("\n"))));
    System.out.println(String.format("and the minimum island size is %s", minimumIslandSize(grid)));
  }

}
