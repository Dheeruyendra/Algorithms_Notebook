package Trees.ConceptProblems;

import java.util.List;

public class SizeofSubtree {
    /*
     * Forward Pass: no information is passed because of size of child subtree is
     * not dependent on its parent
     * Backward Pass: convey the size of the child subtree to its parent
     * {size[parent] = size[child1] + size[child2] + ... + 1}
     */
    static int sizeOfSubtree(int curr, List<List<Integer>> graph, boolean[] visited, int[] size) {
        visited[curr] = true;
        int currSubtreeSize = 1;
        for (int next : graph.get(curr)) {
            if (!visited[next]) {
                int nextSize = sizeOfSubtree(next, graph, visited, size);
                currSubtreeSize += nextSize;
            }
        }
        return size[curr] = currSubtreeSize;
    }
    // int[] size will contain the size of subtree rooted at each node
}
