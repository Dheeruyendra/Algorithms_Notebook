package GraphAlgorithms.CycleDetection;
import java.util.List;

public class UndirectedGraph {
/*
if a node is already visited and it is not the parent of the current node then there is a cycle
*/  
    boolean isCyclic(int curr, int parent, List<List<Integer>> adjList, boolean[] visited){
        visited[curr] = true;
        
        for(int next : adjList.get(curr)){
            if(!visited[next]){
                if(isCyclic(next, curr, adjList, visited)) return true;
            }else if(next != parent) return true;
        }
        return false;
    }
}
