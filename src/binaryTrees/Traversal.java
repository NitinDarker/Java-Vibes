package binaryTrees;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Traversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> q = new LinkedList<TreeNode>();
        q.add(root);
        while (!q.isEmpty()) {
            List<Integer> arr = new ArrayList<>();
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode front = q.remove();
                arr.add(front.val);
                if (front.left != null) q.add(front.left);
                if (front.right != null) q.add(front.right);
            }
            res.add(arr);
        }
        return res;
    }
}
