package GraphAlgorithms.CycleDetection;
import java.util.List;

public class DirectedGraph {
/*
  if a node is already visited in the current dfs cycle then there is a cycle
*/   
    //graph coloring method can also be used to detect cycle in directed graph
    boolean isCyclic(int curr, List<List<Integer>>adjList, int[]visited){
             visited[curr] = 1;
             
             for(int next : adjList.get(curr)){
                 if(visited[next] == 0){
                     if(isCyclic(next, adjList, visited)) return true;
                 }else if(visited[next] == 1) return true;
             }
                visited[curr] = 2; //backtrack and mark the node as visited and this node cannot be part of any cycle
                return false;
    }
    //dfs method using extra space to detect cycle in directed graph
    boolean isCyclic(int curr, List<List<Integer>> adjList, boolean[] visited, boolean[] dfsVisited){
        visited[curr] = true;
        dfsVisited[curr] = true; //mark the node as visited in the current dfs cycle
        
        for(int next : adjList.get(curr)){
            if(!visited[next]){
                if(isCyclic(next, adjList, visited, dfsVisited)) return true;
            }else if(dfsVisited[next]) return true;
        }
        dfsVisited[curr] = false; //backtrack and mark the node as not visited in the current dfs cycle
        return false;
    }  
}
