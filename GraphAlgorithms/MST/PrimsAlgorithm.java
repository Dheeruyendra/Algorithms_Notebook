package graphAlgorithms.MST;

import java.util.List;
import java.util.PriorityQueue;

public class PrimsAlgorithm {
// prim's algorithm  is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph.\
// Time complexity: O(ElogV)
// Space complexity: O(V)
        int primsAlgorithm(List<List<int[]>> adjList) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        int v = adjList.size();
        pq.add(new int[]{0, 0}); // {vertex, weight}
        boolean[] visited = new boolean[v];
        int mst = 0;

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            if (visited[curr[0]]) continue;

            mst += curr[1];
            visited[curr[0]] = true;

            for (int[] next : adjList.get(curr[0])) {
                if (!visited[next[0]]) {
                    pq.add(new int[]{next[0], next[1]});
                }
            }
        }
        return mst; // return the minimum spanning tree weight
        //List<List<int[]>> adjList = new ArrayList<>(); int[] : {vertex, weight}
    }
     
}
