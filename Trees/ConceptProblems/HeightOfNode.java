package Trees.ConceptProblems;

import java.util.List;

public class HeightOfNode {
    /*
     * The height of a node is the length of the longest path to a leaf, measured in
     * the number of edges or nodes.
     * 
     * Forward Pass : no information is conveyed because the height of the child
     * node is not dependent on its parent
     * Backward Pass : convey the height of the child node to its parent
     * {height[parent] = max(height[child1], height[child2], ...) + 1}
     */
    static void heightOfNode(int curr, List<List<Integer>> graph, boolean[] visited, int[] height) {
        visited[curr] = true;
        int maxHeight = 0;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                heightOfNode(next, graph, visited, height);
                maxHeight = Math.max(maxHeight, height[next]);
            }
        }
        height[curr] = maxHeight + 1;
    }
    // int[] height will contain the height of each node
}
