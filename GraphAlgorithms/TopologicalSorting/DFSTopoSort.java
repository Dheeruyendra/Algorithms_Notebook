package GraphAlgorithms.TopologicalSorting;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class DFSTopoSort {
    void dfsTopoSort(int currNode, List<List<Integer>> adjList, boolean[] visited, List<Integer> topoLogicalOrder) {
        visited[currNode] = true;
        for (int next : adjList.get(currNode)) {
            if (!visited[next]) {
                dfsTopoSort(next, adjList, visited, topoLogicalOrder);
            }
        }
        topoLogicalOrder.add(currNode);
    }

    List<Integer> topoSort(int vertices, List<List<Integer>> adjList) {
        boolean[] visited = new boolean[vertices];
        List<Integer> topoLogicalOrder = new ArrayList<>();

        for (int i = 0; i <= vertices; i++) {
            if (!visited[i]) {
                dfsTopoSort(i, adjList, visited, topoLogicalOrder);
            }
        }
        Collections.reverse(topoLogicalOrder);
        return topoLogicalOrder;
    }
}
