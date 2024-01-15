package GraphAlgorithms.DFS;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

public class DFS {
/* Time Complexity : O(V+E)
 * Space Complexity : O(V) 
 * 
 */
   
   //recursion
    void dfsTraversal(int curr, List<List<Integer>> adjList, boolean[] visited){
             visited[curr] = true;

             for(int next : adjList.get(curr)){
                if(!visited[next]){
                     dfsTraversal(next, adjList, visited);
                }
             }
    }

    //iterative
    boolean dfsTraversal(int src, int dest, int n, List<List<Integer>>adjList){
                    boolean[] visited = new boolean[n];
                    Deque<Integer> stack = new ArrayDeque<>();
                    stack.push(src);

                    while(!stack.isEmpty()){
                         int node = stack.pop();
                         visited[node] = true;

                         if(node == dest) return true;
                         
                         for(int neigh : adjList.get(node)){
                            if(!visited[neigh]) stack.push(neigh);
                         }
                    }
                 return false;
    }
}
