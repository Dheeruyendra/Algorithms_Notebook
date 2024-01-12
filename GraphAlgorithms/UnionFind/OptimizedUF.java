public class OptimizedUF {
 /*
    union: implementation using rank 
    find: implementation using path compression
    Time Complexity : 
                     Union-Find Constructor =>O(n), 
                     union => O(1) average case, O(logn) worst case , 
                     find => O(1) average case, O(logn) worst case, 
                     connected => O(1) average case, O(logn) worst case
    Space Complexity : O(n)
 */   
     int[] root;
     int[] rank;
     int count;

     OptimizedUF(int n){
        root = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            root[i] = i;
            rank[i] = 1;
        }
     }

     int find(int node){
        if(node == root[node])return node;
        root[node] = find(root[node]);
        return root[node];
     }

     void union(int p, int q){
          int rootP = find(p);
          int rootQ = find(q);
          if(rootP == rootQ)return;

          if(rank[rootP] > rank[rootQ]){
             root[rootQ] = rootP;
          }
          else if(rank[rootP]  < rank[rootQ]){
            root[rootP] = rootQ;
          }
          else {
            root[rootQ] = rootP;
            rank[rootP]+=1;
          }
     }

     boolean connected(int p, int q){
        return find(p) == find(q);
     }

}
