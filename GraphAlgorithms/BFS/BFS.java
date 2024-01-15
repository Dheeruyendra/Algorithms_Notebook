package GraphAlgorithms.BFS;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Time-Complexity: O(V+E)
//Space-Complexity: O(V)
public class BFS {
     public void bfsTraversal(int src, List<List<Integer>>adjList){
            int n = adjList.size();
            boolean[] visited = new boolean[n];
            Queue<Integer> q = new LinkedList<>();
            q.add(src);

            while(!q.isEmpty()){
                 int currNode = q.poll();
                 for(int neigh : adjList.get(currNode)){
                    if(!visited[neigh]){
                        q.add(neigh);
                        visited[neigh] = true;
                    }
                 }
            }
     }
}
