function lowestCommonAncestor(root, p, q) {
    if (!root || root === p || root === q) return root;
    let left = lowestCommonAncestor(root.left, p, q);
    let right = lowestCommonAncestor(root.right, p, q);
    return left && right ? root : left || right;
}

// Helper class for TreeNode
class TreeNode {
    constructor(val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

function mainLCA() {
    // Build a sample binary tree
    const root = new TreeNode(3);
    root.left = new TreeNode(5);
    root.right = new TreeNode(1);
    root.left.left = new TreeNode(6);
    root.left.right = new TreeNode(2);
    root.left.right.left = new TreeNode(7);
    root.left.right.right = new TreeNode(4);
    root.right.left = new TreeNode(0);
    root.right.right = new TreeNode(8);

    // Nodes to find LCA for
    const p = root.left;  // Node with value 5
    const q = root.left.right.right; // Node with value 4

    // Find LCA
    const lca = lowestCommonAncestor(root, p, q);

    // Print value of LCA node
    console.log("Lowest Common Ancestor:", lca ? lca.val : null);
}

mainLCA();
