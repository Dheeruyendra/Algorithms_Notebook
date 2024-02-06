package graphAlgorithms.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class BFSMatrix {
    int[] dx = { 1, -1, 0, 0 };
    int[] dy = { 0, 0, 1, -1 };

    // another implementation using array
    void bfs(int i, int j, int[][] adjMatrix) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[adjMatrix.length][adjMatrix[0].length];
        q.add(new int[] { i, j });

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            for (int k = 0; k < 4; k++) {
                int nx = dx[k] + curr[0];
                int ny = dy[k] + curr[1];

                if (isValid(i, j, adjMatrix)) {
                    visited[nx][ny] = true;
                    q.add(new int[] { nx, ny });
                }
            }
        }
    }

    class Pair {
        int x, y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // int[] dx = { 1, -1, 0, 0 };
    // int[] dy = { 0, 0, 1, -1 };

    public void bfsTraversal(int i, int j, int[][] adjMatrix) {
        Queue<Pair> q = new LinkedList();
        Set<Pair> visited = new HashSet<>();
        q.add(new Pair(i, j));
        visited.add(new Pair(i, j));

        while (!q.isEmpty()) {
            Pair curr = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = dx[i] + curr.x;
                int ny = dy[i] + curr.y;

                Pair newPair = new Pair(nx, ny);
                if (isValid(nx, ny, adjMatrix) && !visited.contains(newPair)) {
                    visited.add(newPair);
                    q.add(newPair);
                }
            }
        }
    }

    boolean isValid(int i, int j, int[][] adjList) {
        if (i < 0 || j < 0 || i >= adjList.length || j >= adjList[0].length)
            return false;
        return true;
    }
}
