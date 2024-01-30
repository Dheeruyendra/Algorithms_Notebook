package GraphAlgorithms.TopologicalSorting;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.ArrayList;

public class KhansAlgorithm {

    List<Integer> topologicalSorting(int numberOfNodes, List<List<Integer>> adjList) {
        int[] inDegree = new int[numberOfNodes];
        for (List<Integer> edges : adjList) {
            for (int edge : edges) {
                inDegree[edge] += 1;
            }
        }

        Queue<Integer> q = new LinkedList<>();
        List<Integer> topologicalOrder = new ArrayList<>();

        while (!q.isEmpty()) {
            int currNode = q.poll();
            topologicalOrder.add(currNode);
            for (int edge : adjList.get(currNode)) {
                inDegree[edge] -= 1;
                if (inDegree[edge] == 0) {
                    q.add(edge);
                }
            }
        }

        // If the topological order is not possible because of cycle, return an empty
        // list
        if (topologicalOrder.size() != numberOfNodes) {
            return new ArrayList<>();
        }
        return topologicalOrder;
    }
}
