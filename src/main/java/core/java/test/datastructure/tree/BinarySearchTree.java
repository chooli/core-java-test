package core.java.test.datastructure.tree;

public class BinarySearchTree {

    TreeNode root;

    public BinarySearchTree(TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() { return this.root; }

    /**
     * insert a new node to the right leaf node
     *
     * @param node a new node to be inserted
     */
    public void insert(TreeNode node) {
        root = recursiveInsert(root, node);
    }

    private TreeNode recursiveInsert(TreeNode root, TreeNode node) {
        if (root == null) return node;
        int val = node.val;
        if (root.val > val) { root.left = recursiveInsert(root.left, node); }
        else if (root.val < val) { root.right = recursiveInsert(root.right, node); }
        return root;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        recursivePrint(root, sb);
        sb.append("]");
        return sb.toString();
    }

    private void recursivePrint(TreeNode node, StringBuilder sb) {
        if (node == null) return;
        if (node.left != null) recursivePrint(node.left, sb);
        sb.append(node.val).append(" ");
        if (node.right != null) recursivePrint(node.right, sb);
    }

}
