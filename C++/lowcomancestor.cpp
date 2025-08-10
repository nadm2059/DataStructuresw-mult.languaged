#include <iostream>
using namespace std;
// Binary tree node
struct TreeNode {
    int val;
    TreeNode* left;
    TreeNode* right;
    TreeNode(int v) : val(v), left(nullptr), right(nullptr) {}
};

// Find lowest common ancestor
TreeNode* lowestCommonAncestor(TreeNode* root, TreeNode* p, TreeNode* q) {
    if (!root || root == p || root == q) return root;  // Base case
    TreeNode* left = lowestCommonAncestor(root->left, p, q);  // Recurse left
    TreeNode* right = lowestCommonAncestor(root->right, p, q);  // Recurse right
    if (left && right) return root;  // Found p and q in both sides
    return left ? left : right;  // Return non-null side
}
int main() {
    TreeNode* root = new TreeNode(3);
    root->left = new TreeNode(5);
    root->right = new TreeNode(1);
    root->left->left = new TreeNode(6);
    root->left->right = new TreeNode(2);
    TreeNode* p = root->left;         // Node 5
    TreeNode* q = root->left->right;  // Node 2

    TreeNode* lca = lowestCommonAncestor(root, p, q);
    cout << "LCA of " << p->val << " and " << q->val << ": " << lca->val << endl;

    return 0;
}