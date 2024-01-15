package GraphAlgorithms.DFS;
import java.util.ArrayList;
import java.util.List;

public class AllPathsFromSrcToDest {
       
    
    //note: here graph matrix is the adjacency list and graph is a DAG
    void allPaths(int src, int dest, int[][] graph){
         List<List<Integer>> paths = new ArrayList<>();
         List<Integer> currPath = new ArrayList<>();
         dfs(src, dest, graph, currPath, paths);
    }

    void dfs(int currNode, int dest, int[][]graph, List<Integer>currPath, List<List<Integer>>paths){
              currPath.add(currNode);
              if(currNode == dest){
                  paths.add(new ArrayList<>(currPath));
                  return;
              }

              for(int neigh : graph[currNode]){
                   dfs(neigh, dest, graph, currPath, paths);
                   currPath.remove(currPath.size()-1);
              }
    }
}
