package GraphAlgorithms.DFS;
import java.util.List;
public class DFS {
/* Time Complexity : O(V+E)
 * Space Complexity : O(V) 
 * 
 */
    void dfsTraversal(int curr, List<List<Integer>> adjList, boolean[] visited){
             visited[curr] = true;

             for(int next : adjList.get(curr)){
                if(!visited[next]){
                     dfsTraversal(next, adjList, visited);
                }
             }
    }
}
