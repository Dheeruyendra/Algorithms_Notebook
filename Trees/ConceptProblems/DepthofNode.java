package Trees.ConceptProblems;

import java.util.List;

public class DepthofNode {
    /*
     * The depth of a node is the distance from the root, measured in the number of
     * edges.
     * Forward Pass : convey the depth of the parent node to its children
     * {depth[child] = (parentNodeDepth+1)}
     * Backward Pass : no information is conveyed because the depth of the node is
     * not dependent on its children
     */
    static void depthOfNode(int curr, List<List<Integer>> graph, boolean[] visited, int currDepth, int[] depth) {
        visited[curr] = true;
        depth[curr] = currDepth;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                depthOfNode(next, graph, visited, currDepth + 1, depth);
            }
        }
    }
    // int[] depth will contain the depth of each node
}
