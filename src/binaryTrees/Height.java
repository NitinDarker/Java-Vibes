// Maximum Height/Depth of a Binary Tree ->
// A binary tree's maximum depth is the number of nodes along
// the longest path from the root node down to the farthest leaf node.

package binaryTrees;

import java.util.LinkedList;
import java.util.Queue;

public class Height {
    // Using Recursion
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = maxDepth(root.left);
        int rightHeight = maxDepth(root.right);
        return 1 + Math.max(leftHeight, rightHeight);
    }

    // Using Queue
    public int maxHeight(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        int height = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            height += 1;
            for (int i = 0; i < size; i++) {
                TreeNode front = q.remove();
                if (front.left != null) q.add(front.left);
                if (front.right != null) q.add(front.right);
            }
        }
        return height;
    }
}
