public class QuickUnionByRankUF {
/*
        Time Complexity : 
                         Union-Find Constructor =>O(n), 
                         union => O(logn) , 
                         find => O(logn), 
                         connected => O(logn)
        Space Complexity : O(n)

*/    
    int[] root; //root[i] = stores parent of i and if root[i] = i then i is root node   
    int[] rank; //rank[i] = rank of subtree rooted at i(size of tree is rank[i])
    int count;
    QuickUnionByRankUF(int n){
        count = n;
        root = new int[n];
        rank = new int[n];
        for(int i=0; i<n; i++){
            root[i] = i;
            rank[i] = 1;//initially size of each tree is 1
        }
    }

    int noOfComponents(){
        return count;
    }
    //finds root node of componet containing node
    int find(int node){
        if(node < 0 || node >= root.length){
            throw new IllegalArgumentException("Invalid node");
        }
        while(node != root[node]){
            node = root[node];
        }
        return node;
    }

    //connects two nodes by connecting their components
    void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;

        //connect smaller tree to larger tree
         if(rank[rootP] < rank[rootQ]){
                root[rootP] = rootQ;
         }  
         else if(rank[rootP] > rank[rootQ]){
                root[rootQ] = rootP;
         }
         else {
            root[rootP] = rootQ;
            rank[rootQ]+=1;
         }
            count--;
    }

    boolean connected(int p, int q){
        return find(p) == find(q);
    }
}
