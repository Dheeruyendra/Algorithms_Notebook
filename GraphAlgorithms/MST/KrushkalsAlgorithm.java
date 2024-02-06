package graphAlgorithms.MST;

import java.util.Collections;
import java.util.List;

public class KrushkalsAlgorithm {
// Krushkal's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph.
// Time complexity: O(ElogV)
// Space complexity: O(V)    
     class UnionFind{
        int[] root, rank;
        UnionFind(int n){
          root = new int[n];
          rank = new int[n];
          for(int i=0; i<n; i++)root[i] = i;    
        }
        
        int find(int x){
              if(x == root[x])return x;
              return root[x] = find(root[x]);
        }
        
        boolean union(int p, int q){
             int rootP = find(p);
             int rootQ = find(q);
             if(rootP == rootQ)return false;
            
             if(rank[rootP] > rank[rootQ]){
                 root[rootQ] = rootP;
             }
            else if(rank[rootP] < rank[rootQ]){
                root[rootP] = rootQ;
            }
            else{
                root[rootP] = rootQ;
                rank[rootQ] +=1;
            }
            return true;
        }
        
    }
     int krushkalsAlgorithm(List<int[]>edges, int v){
           Collections.sort(edges, (a, b)->Integer.compare(a[2], b[2]));
           UnionFind uf = new UnionFind(v);
           int mst = 0; 
          for(int[] edge : edges){
               if(uf.union(edge[0], edge[1]))mst+=edge[2];
           }
        return mst;
    }
    //List<int[]> edges = new ArrayList<>(); int[] : {u, v, weight}
}
