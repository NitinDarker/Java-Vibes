package binaryTrees;

public class LCA {
    // For Binary Tree
    public TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode leftVal = lca(root.left, p, q);
        TreeNode rightVal = lca(root.right, p, q);

        if (leftVal != null && rightVal != null) return root;
        else if (leftVal == null) return rightVal;
        else return leftVal;
    }

    // For BST
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        int rootVal = root.val;
        int pVal = p.val;
        int qVal = q.val;

        if (rootVal < pVal && rootVal < qVal) {
            // both p and q are right of root
            return lowestCommonAncestor(root.right, p, q);
        }
        else if (rootVal > pVal && rootVal > qVal) {
            // both p and q are left of root
            return lowestCommonAncestor(root.left, p, q);
        }
        else {
            return root;
        }
    }
}
