package Trees.Traversals;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Deque;
import java.util.ArrayList;

/*  Time Complexity : O(N)
 *  Space Complexity : O(N)
 * 
 *  Inorder Traversal : traverse left subtree; process current node; traverse right subtree
 */
public class Inorder {
    class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            left = null;
            right = null;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode top = stack.pop();
            res.add(top.val);
            root = top.right;
        }
        return res;
    }
}
