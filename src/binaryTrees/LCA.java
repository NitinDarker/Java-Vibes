package binaryTrees;

public class LCA {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;

        TreeNode leftVal = lowestCommonAncestor(root.left, p, q);
        TreeNode rightVal = lowestCommonAncestor(root.right, p, q);

        if (leftVal != null && rightVal != null) return root;
        else if (leftVal == null) return rightVal;
        else return leftVal;
    }
}
