package GraphAlgorithms.ShortestPath;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Arrays;

/*
 * Time-Complexity : O(E*logV)
 * Space Complexity : O(V)
 */
public class DijkstraAlgorithm {
    class Pair {
        int node, wt;

        Pair(int x, int y) {
            node = x;
            wt = y;
        }
    }

    // shortest path from src to all other vertices
    public void shortestPath(int src, List<List<Pair>> adjList) {
        int n = adjList.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        pq.add(new int[] { src, 0 }); // node and cost to reach this node

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int costToReachNode = curr[1];
            if (dist[node] < costToReachNode)
                continue; // we already know better way to reach this node
            for (Pair neigh : adjList.get(node)) {
                if (dist[neigh.node] > costToReachNode + neigh.wt) {
                    dist[neigh.node] = neigh.wt + costToReachNode;
                    pq.add(new int[] { neigh.node, dist[neigh.node] });
                }
            }
        }

        // dist[] array will contains minimum cost to reach each node from source node
    }
}
