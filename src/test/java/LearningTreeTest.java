import core.java.CommonTest;
import org.junit.jupiter.api.Test;

public class LearningTreeTest extends CommonTest {

    @Test
    public void testExams() {

        TreeNode t1 = new TreeNode(5);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(6);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(10);
        TreeNode t6 = new TreeNode(15);
        TreeNode t7 = new TreeNode(20);
        TreeNode t8 = new TreeNode(12);
        TreeNode t9 = new TreeNode(9);
        TreeNode t10 = new TreeNode(10);
        t5.left = t1;
        t5.right = t6;
        t6.left = t2;
        t6.right = t7;

        System.out.println(climbStairs(5));
    }

    private int traverse(TreeNode t) {
        int counter = 0;
        if ((t.left != null && t.right != null) ||
                (t.left == null && t.right == null)) {
            counter += 3;
        }
        if ((t.left != null && t.right != null)) {
            int lCounter = traverse(t.left);
            int rCounter = traverse(t.right);
            counter += lCounter + rCounter + 1;
        }
        return counter;
    }

    public boolean isValidBST(TreeNode node){
        if(node == null) return true;
        boolean result = true;
        if(node.left != null) {
            if(!compareAllLeaf(node.left, node.val, true)) return false;
            result = result && isValidBST(node.left);
        }

        if(node.right != null) {
            if(!compareAllLeaf(node.right, node.val, false)) return false;
            result = result && isValidBST(node.right);
        }

        return result;
    }

    private boolean compareAllLeaf(TreeNode node, int rootVale, boolean lessThan){
        if(lessThan && node.val >= rootVale) return false;
        if(!lessThan && node.val <= rootVale) return false;
        boolean result = true;
        if(node.left != null) {
            result = result && compareAllLeaf(node.left, rootVale, lessThan);
        }
        if(node.right != null) {
            result = result && compareAllLeaf(node.right, rootVale, lessThan);
        }
        return result;
    }

    public int maxDepth(TreeNode root) {
        if(root == null)
            return 0;
        return travelTreeDepth(root, 1);
    }

    private int travelTreeDepth(TreeNode root, int d) {
        int dl = 0, dr = 0;
        if(root.left!=null){
            dl = travelTreeDepth(root.left, d+1);
        }
        if(root.right!=null){
            dr = travelTreeDepth(root.right, d+1);
        }

        if(dl == 0 && dr ==0)
            return d;
        else
            return dl > dr ? dl : dr;
    }

    public class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
    }

    public int climbStairs(int n) {
        return climbStair(0, n, new int[n]);
    }

    private int climbStair(int step, int n, int[] memor) {
        if (step > n) {
            return 0;
        }
        if (step == n) {
            return 1;
        }
        if (memor[step] == 0)
            memor[step] = climbStair(step + 1, n, memor) + climbStair(step + 2, n, memor);
        return memor[step];
    }

}
