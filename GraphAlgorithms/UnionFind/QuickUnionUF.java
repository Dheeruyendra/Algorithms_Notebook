public class QuickUnionUF {
/*
        Time Complexity : 
                         Union-Find Constructor =>O(n), 
                         union => O(n) , 
                         find => O(n), 
                         connected => O(n)
    
        Space Complexity : O(n)
*/ 
    int[] root; //root[i] = stores parent of i and if root[i] = i then i is root node
    int count;
    QuickUnionUF(int n){
        count = n;
        root = new int[n];
        for(int i=0; i<n; i++){
            root[i] = i;
        }
    }
    public int noOfComponents(){
        return count;
    }
    //finds root node of componet containing node
    public int find(int node){
        if(node < 0 || node >= root.length){
            throw new IllegalArgumentException("Invalid node");
        }
        while(node != root[node]){
            node = root[node];
        }
        return node;
    }
    //connects two nodes by connecting their components
    public void union(int p, int q){
        int rootP = find(p);
        int rootQ = find(q);
        if(rootP == rootQ) return;
        root[rootP] = rootQ;
        count--;
    }
    public boolean connected(int p, int q){
        return find(p) == find(q);
    }
}
