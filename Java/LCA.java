public class LCA {

    // Definition for a binary tree node (nested static class)
    static class TreeNode { 
        int val;                  // Node value
        TreeNode left, right;     // Left and right child nodes
        
        TreeNode(int val) { 
            this.val = val;       // Constructor
        }
    }

    // Recursive method to find Lowest Common Ancestor of nodes p and q in binary tree rooted at root
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;  // Base cases
        TreeNode left = lowestCommonAncestor(root.left, p, q);   // Search left subtree
        TreeNode right = lowestCommonAncestor(root.right, p, q); // Search right subtree

        if (left != null && right != null) return root;          // If p and q found in different subtrees, root is LCA
        return left != null ? left : right;                       // Otherwise return non-null child
    }

    // Main method to test LCA functionality
    public static void main(String[] args) {
        // Construct binary tree:
        //         3
        //        / \
        //       5   1
        //      / \ / \
        //     6  2 0  8
        //       / \
        //      7   4

        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        TreeNode p = root.left;             // Node with value 5
        TreeNode q = root.left.right.right; // Node with value 4

        // Find LCA of p and q
        TreeNode lca = lowestCommonAncestor(root, p, q);

        System.out.println("Lowest Common Ancestor of " + p.val + " and " + q.val + " is: " + lca.val);
        // Expected output: 5
    }
}
