# Define a class for a node in a binary tree
class TreeNode:
    def __init__(self, val=0):
        self.val = val        # Value of the node
        self.left = None      # Reference to the left child
        self.right = None     # Reference to the right child

# Function to find the lowest common ancestor (LCA) of two nodes in a binary tree
def lowest_common_ancestor(root, p, q):
    if not root or root == p or root == q:  # Base case: if root is None or matches p or q
        return root  # Return the root (could be one of the LCA candidates)

    # Recursively search in the left subtree
    left = lowest_common_ancestor(root.left, p, q)
    # Recursively search in the right subtree
    right = lowest_common_ancestor(root.right, p, q)

    if left and right:  # If both left and right are non-null, current node is the LCA
        return root

    # If only one side is non-null, return that side
    return left if left else right

# Main function to demonstrate the LCA algorithm
def main():
    print("Lowest Common Ancestor Demo")  # Title for output

    # Manually build the binary tree:
    #         3
    #       /   \
    #      5     1
    #     / \   / \
    #    6   2 0   8
    #       / \
    #      7   4

    root = TreeNode(3)  # Root node with value 3
    root.left = TreeNode(5)  # Left child of root is 5
    root.right = TreeNode(1)  # Right child of root is 1
    root.left.left = TreeNode(6)  # Left child of 5 is 6
    root.left.right = TreeNode(2)  # Right child of 5 is 2
    root.right.left = TreeNode(0)  # Left child of 1 is 0
    root.right.right = TreeNode(8)  # Right child of 1 is 8
    root.left.right.left = TreeNode(7)  # Left child of 2 is 7
    root.left.right.right = TreeNode(4)  # Right child of 2 is 4

    p = root.left  # Node with value 5
    q = root.left.right.right  # Node with value 4

    # Find the LCA of nodes 5 and 4
    lca = lowest_common_ancestor(root, p, q)

    # Print the result
    print(f"LCA of {p.val} and {q.val} is: {lca.val}")  # Expected: 5

# Only run the main function if this file is executed directly
if __name__ == "__main__":
    main()
