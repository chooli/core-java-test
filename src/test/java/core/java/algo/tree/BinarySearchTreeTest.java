package core.java.algo.tree;

import core.java.CommonTest;
import core.java.test.datastructure.tree.BinarySearchTree;
import core.java.test.datastructure.tree.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BinarySearchTreeTest extends CommonTest {

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
    public void testExams() {
        System.out.println(binarySearchTree);
        TreeNode root = binarySearchTree.getRoot();
        //recurFlatten(root, queue);
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.poll();

        List<Integer> result = new ArrayList<>();
        queue.add(root);
        viewLeft(queue, result);
        printList(result);
        Assertions.assertTrue(true);
    }

    private void viewLeft(Queue<TreeNode> queue, List<Integer> result) {
        Queue<TreeNode> childNodeQueue = new ArrayDeque<>();
        boolean isAdded = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.remove();
            if (!isAdded) {
                result.add(node.val);
                isAdded = true;
            }
            System.out.println(node.val);

            if (node.left!=null) childNodeQueue.add(node.left);
            if (node.right!=null) childNodeQueue.add(node.right);
        }

        System.out.println("--");
        if (!childNodeQueue.isEmpty()) viewLeft(childNodeQueue, result);
    }

    private void recurFlatten(TreeNode node, Queue<TreeNode> queue) {
        if (node != null) {
            queue.add(node);
            if (node.left != null) {
                recurFlatten(node.left, queue);
            }
            if (node.right != null) {
                recurFlatten(node.right, queue);
            }
        }
    }

    @Test
    public void findPaths() {
        int count = 0, distance = 7;
        int[] steps = new int[]{1, 2};
        TreeNode root = new TreeNode(0);
        Set<Integer> uniqueSteps = new HashSet<>();
        count = findStepPaths(root, distance, steps, count, uniqueSteps);
        System.out.println(count);
        Assertions.assertTrue(true);
    }

    private int findStepPaths(TreeNode node, int distance, int[] steps, int count, Set<Integer> uniqueSteps) {
        int val = node.getVal();
        if (val == distance) {
            count++;
            return count;
        }

        int stepL = getNextStep(distance, val, steps[0], uniqueSteps);
        int stepR = getNextStep(distance, val, steps[1], uniqueSteps);
        int leftCount = 0, rightCount = 0;
        if (stepL > 0) {
            node.left = new TreeNode(stepL);
            leftCount = findStepPaths(node.left, distance, steps, leftCount, uniqueSteps);
        }
        if (stepR > 0) {
            node.right = new TreeNode(stepR);
            rightCount = findStepPaths(node.right, distance, steps, rightCount, uniqueSteps);
        }
        count += leftCount + rightCount;
        return count;
    }

    private int getNextStep(int distance, int val, int step, final Set<Integer> uniqueSteps) {
        int nextStep = 0;
        int stepForward = val + step;
        int stepBackward = val - step;
        if (stepForward <= distance)
            nextStep = stepForward;
        if (stepBackward > 0 && !uniqueSteps.contains(stepBackward))
            nextStep = stepBackward;
        uniqueSteps.add(nextStep);
        //System.out.println(nextStep);
        return nextStep;
    }

    @Test
    public void findDepth() {
        int depth = 0;
        depth = recurFindDepth(binarySearchTree.getRoot(), depth);
        System.out.println(depth);
        Assertions.assertTrue(true);
    }

    private int recurFindDepth(TreeNode node, int depth) {
        depth++;
        int leftDepth = 0, rightDepth = 0;
        if (node.left != null) leftDepth = recurFindDepth(node.left, leftDepth);
        if (node.right != null) rightDepth = recurFindDepth(node.right, rightDepth);
        depth += Math.max(leftDepth, rightDepth);
        return depth;
    }

}
