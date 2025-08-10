using System;

public class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int x) { val = x; }
}

public class BinaryTree {
    public TreeNode LowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        var left = LowestCommonAncestor(root.left, p, q);
        var right = LowestCommonAncestor(root.right, p, q);
        return left != null && right != null ? root : left ?? right;
    }

    public static void Main(string[] args) {
        /*
                3
               / \
              5   1
             / \ / \
            6  2 0  8
              / \
             7   4
        */
        
        // Constructing the tree
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left; // Node with value 5
        TreeNode q = root.left.right.right; // Node with value 4

        BinaryTree bt = new BinaryTree();
        TreeNode lca = bt.LowestCommonAncestor(root, p, q);

        Console.WriteLine($"Lowest Common Ancestor of {p.val} and {q.val} is: {lca.val}");
    }
}
