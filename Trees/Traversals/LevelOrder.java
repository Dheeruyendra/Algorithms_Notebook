package Trees.Traversals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
 /*
     Time Complexity : O(N)
     Space Complexity : O(N)
 */
public class LevelOrder {
    class TreeNode{
        int val;
        TreeNode left, right;
        TreeNode(int val){
            this.val = val;
            left = right = null;
        }
    } 
    public List<List<Integer>> levelOrder(TreeNode root) { 
           List<List<Integer>> res = new ArrayList<>();
           Queue<TreeNode> q = new LinkedList();
           q.add(root);
           while(!q.isEmpty()){
               List<Integer> level = new ArrayList<>();
               int n = q.size();
               for(int i=0; i<n; i++){
                   TreeNode curr = q.poll();
                   level.add(curr.val);
                   if(curr.left != null)q.add(curr.left);
                   if(curr.right != null)q.add(curr.right); 
               }
               res.add(level);
           }

           return res;
    }    
}
