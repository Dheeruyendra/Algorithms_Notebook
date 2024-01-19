package GraphAlgorithms.DFS;
/* 
 *  Time Complexity : O(n*m) where n is number of rows and m are number of columns
 *  Space Complexity: O(n*m) 
 */

public class DFSMatrix {
  int[] dx = { 1, -1, 0, 0 };
  int[] dy = { 0, 0, 1, -1 };

  void MatrixDFS(int i, int j, int[][] matrix, boolean[][] visited) {
    visited[i][j] = true;
    for (int k = 0; k < 4; k++) {
      int nx = dx[k];
      int ny = dy[k];

      if (isValid(nx, ny, matrix) && !visited[nx][ny]) {
        MatrixDFS(nx, ny, matrix, visited);
      }
    }
  }

  boolean isValid(int i, int j, int[][] matrix) {
    if (i < 0 || j < 0 || i >= matrix.length || j >= matrix[0].length)
      return false;
    return true;
  }
}
