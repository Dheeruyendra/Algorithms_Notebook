package Trees.ConceptProblems;

import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DiameterOfTree {
    /*
     * Forward Pass: no information is passed
     * Backward Pass: convey the height of the child
     * Diameter through a node = height[maxChild1] + height[maxChild2] + 1
     */
    static int diameter = 0; // global variable to store the diameter of the tree

    static int diameterOfTree(int curr, List<List<Integer>> graph, boolean[] visited) {
        visited[curr] = true;
        PriorityQueue<Integer> childHeight = new PriorityQueue<>(Collections.reverseOrder());
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                int nextHeight = diameterOfTree(next, graph, visited);
                childHeight.add(nextHeight);
            }
        }

        int maxChild1 = (childHeight.size() > 0) ? childHeight.poll() : 0;
        int maxChild2 = (childHeight.size() > 0) ? childHeight.poll() : 0;
        diameter = Math.max(diameter, maxChild1 + maxChild2 + 1);

        return maxChild1 + 1; // return the height of the current node
    }
}
