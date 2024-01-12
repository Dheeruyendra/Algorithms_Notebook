class QuickFindUF{
/*
      Time Complexity : 
                     Union-Find Constructor =>O(n), 
                     union => O(n) , 
                     find => O(1), 
                     connected => O(1)

      Space Complexity : O(n)
*/    
   int[] root;  // root[i] = root of i
   int count;
   QuickFindUF(int n){
        count = n; // initially n components
        root = new int[n]; // initially each component is its own root
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
     return root[node];
   }  
   //connects two nodes by connecting their components
   public void union(int p, int q){
         int rootP = find(p);
         int rootQ = find(q);
         if(rootP == rootQ) return;

        //connect p to q by setting root of all nodes in p component to root of q
         for(int i=0; i<root.length; i++){
             if(root[i] == rootP){
                 root[i] = rootQ;
             }
         }
         count--;
   }

   public boolean connected(int p, int q){
       return find(p) == find(q);
   }

}