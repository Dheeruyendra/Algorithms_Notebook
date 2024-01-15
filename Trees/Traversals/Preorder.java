package Trees.Traversals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
/*
 * Time Complexity : O(N) where n is number of nodes in the binary tree
 * Space Complexity : O(N) 
 * 
 * Preorder Traversal : process current node; traverse left subtree; traverse right subtree
 */
public class Preorder {
class TreeNode{
     int val;
     TreeNode left, right;
     TreeNode(int val){
        this.val = val;
        left = null;
        right = null;
     }
}    
    public List<Integer> preorderTraversal(TreeNode root) {
           List<Integer> res = new ArrayList<>();
           Deque<TreeNode> stack = new ArrayDeque<>();
           stack.push(root);
           while(!stack.isEmpty()){
                TreeNode curr = stack.pop();
                res.add(curr.val);

                if(curr.right != null)stack.push(curr.right);
                if(curr.left != null)stack.push(curr.left);
           }
           return res; 
    }    
}
