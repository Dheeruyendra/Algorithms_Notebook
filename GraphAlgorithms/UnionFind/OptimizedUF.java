package graphAlgorithms.UnionFind;
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
        count = n; // number of components
     }


/*
 *  Explanation of find method using path compression
 *  1. find root of component containing node
 *  2. compress path by setting root of each node to root of its parent
 *  3. return root of component containing node
 * 
 * Time Complexity : O(1) average case, O(logn) worst case
 */
     int find(int node){
        if(node == root[node])return node;
        root[node] = find(root[node]);
        return root[node];
     }

/*
*  Explanation of union method using rank
*  1. find root of p and q
*  2. connect p to q by setting root of smaller component to root of larger component
*  3. if both components have same rank then increase rank of root of p by 1
*  4. decrease count by 1
*  Time Complexity : O(1) average case, O(logn) worst case
*/     

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
            count--;
     }

     boolean connected(int p, int q){
        return find(p) == find(q);
     }

     int noOfComponents(){
        return count;
     }

}
