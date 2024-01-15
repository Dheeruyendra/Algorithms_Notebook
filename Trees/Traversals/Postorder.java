package Trees.Traversals;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/*
 *  Time Complexity : O(N)
 *  Space Complexity : O(N)
 *  
 * Time Complexity : left subtree; right subtree; process current node
 */
public class Postorder {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack1 = new ArrayDeque<>();
        Deque<TreeNode> stack2 = new ArrayDeque<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode curr = stack1.pop();
            if (curr.left != null)
                stack1.push(curr.left);
            if (curr.right != null)
                stack1.push(curr.right);
            stack2.push(curr);
        }
        while (!stack2.isEmpty()) {
            res.add(stack2.pop().val);
        }
        return res;
    }
}
