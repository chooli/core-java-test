package core.java.test.datastructure.tree;

public class TreeNode {
    public int val;

    public TreeNode left;

    public TreeNode right;

    public TreeNode() {}

    public TreeNode(int val) { this.val = val; }

    public TreeNode(int val, TreeNode left, TreeNode right) {
      this.val = val;
      this.left = left;
      this.right = right;
    }

    public int getVal() { return this.val; }

    public String toString() {
        return "tree node ["+this.val+"]";
    }
}
