import core.java.test.datastructure.tree.BinarySearchTree;
import core.java.test.datastructure.tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.Stack;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TreeTraverseTest {

    private final BinarySearchTree binarySearchTree = new BinarySearchTree(null);

    @BeforeAll
    public void setup() {
        //init tree nodes
        binarySearchTree.insert(new TreeNode(7));
        binarySearchTree.insert(new TreeNode(8));
        binarySearchTree.insert(new TreeNode(3));
        binarySearchTree.insert(new TreeNode(9));
        binarySearchTree.insert(new TreeNode(2));
        binarySearchTree.insert(new TreeNode(10));
        binarySearchTree.insert(new TreeNode(6));
        binarySearchTree.insert(new TreeNode(21));
    }

    @Test
    void findDeepth() {
        int depth = findDepthTravers(binarySearchTree.getRoot(), 0);
        System.out.println("depth: " + depth);
    }

    int findDepthTravers(TreeNode node, int depth) {
        if (node == null) return depth;
        depth++;
        int depthLeft = findDepthTravers(node.left, depth);
        int depthRight = findDepthTravers(node.right, depth);
        return Math.max(depthLeft, depthRight);
    }

    @Test
    public void inorderWhile() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = binarySearchTree.getRoot();
        TreeNode current = root;

        while(current != null || !stack.isEmpty()) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            TreeNode child = stack.pop();
            System.out.println(child.val);
            current = child.right;
        }
    }

    @Test
    public void preorderRecursion(){
        TreeNode root = binarySearchTree.getRoot();
        preorderRecursionHelper(root);
        Assertions.assertTrue(true);
    }

    private void preorderRecursionHelper(TreeNode node) {
        if (node != null) {
            System.out.println(node.val);

            preorderRecursionHelper(node.left);
            preorderRecursionHelper(node.right);
        }
    }

    @Test
    void preorderWhile() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = binarySearchTree.getRoot();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode child = stack.pop();
            System.out.println(child.val);

            if (child.right != null) stack.push(child.right);
            if (child.left != null) stack.push(child.left);
        }
    }

    @Test
    void postorderWhile() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = binarySearchTree.getRoot();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode child = stack.peek();

            if (child.left != null) {
                stack.push(child.left);
                child.left = null;
            } else if (child.right != null) {
                stack.push(child.right);
                child.right = null;
            } else {
                child = stack.pop();
                System.out.println(child.val);
            }
        }
    }
}
