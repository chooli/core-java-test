package core.java.algo.tree;

import core.java.CommonTest;
import core.java.test.datastructure.tree.TreeNode;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BinaryTreeTest extends CommonTest {

    TreeNode root;

    @BeforeAll
    void setup() {
        TreeNode left1 = new TreeNode(6);
        TreeNode left2 = new TreeNode(4);
        TreeNode left3 = new TreeNode(2);
        TreeNode right1 = new TreeNode(1);
        TreeNode right2 = new TreeNode(3);
        TreeNode right3 = new TreeNode(5);

        root = new TreeNode(8);
        root.left = left1;
        root.right = right1;

        left1.left = left2;
        left1.right = right2;
        left2.left = left3;
        left2.right = right3;
    }

    @Test
    void test() {
        TreeNode target = findDirectParentNode(root, 3);
        println(target);

        assertTrue(true);
    }

    TreeNode findDirectParentNode(TreeNode node, int target) {
        if (node == null) return null;
        if (node.val == target) return new TreeNode(-1);

        TreeNode left = findDirectParentNode(node.left, target);
        TreeNode right = findDirectParentNode(node.right, target);

        if (left != null) return left.val == -1 ? node : left;
        else return right == null ? null : right.val == -1 ? node : right ;
    }
}
