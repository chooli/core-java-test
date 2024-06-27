package core.java.algo.list;

import core.java.CommonTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ListTest extends CommonTest {

    @BeforeAll
    public void setup() {
        System.out.println("start learning tests ... ");
    }

    @Test
    void testExams() {

        List<Integer> subExp = new LinkedList<>();

    }

    public boolean hasCycle(ListNode head) {
        if(head == null)
            return false;
        Set<ListNode> reference = new HashSet<>();
        while(head!=null) {
            ListNode tmp = head;
            head = head.next;
            if(reference.contains(head) || tmp == head)
                return true;
            tmp.next = null;
            reference.add(tmp);
        }
        return false;
    }

    public boolean isPalindrome(ListNode head) {
        ListNode n0 = head, n1 = head;
        int len = getCountOfList(head), j = 0;
        for(int i=0;i<len-j;i++){
            for(int k=len-1;k>j;k--){
                n1 = n1.next;
            }
            if(head.val != n1.val)
                return false;
            head = head.next;
            n1 = n0;
            j++;
        }
        return true;
    }

    private int getCountOfList(ListNode l) {
        int count = 0;
        while(l != null){
            l = l.next;
            count++;
        }
        return count;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l2 == null) return l1;
        if(l1 == null) return l2;

        ListNode nn = new ListNode(-1);
        ListNode n0 = nn;

        while(l1 != null) {
            if(l2 == null || l1.val <= l2.val) {
                nn.next = l1;
                l1 = l1.next;
                nn = nn.next;
            }

            while(l2 != null) {
                if(l1 != null && l2.val > l1.val){
                    break;
                }
                nn.next = l2;
                l2 = l2.next;
                nn = nn.next;
            }

        }

        return n0.next;
    }

    public ListNode reverseList(ListNode head) {
        ListNode fake = new ListNode(0);
        ListNode current = head;
        while (current != null) {
            ListNode tmp = current.next;
            current.next = fake.next;
            fake.next = current;
            current = tmp;
        }
        return fake.next;
    }

    public ListNode _reverseList(ListNode head) {
        if(head ==null || head.next == null)
            return head;

        ListNode firstNode = head;
        ListNode previousNode = null;
        ListNode endNode = null;

        while(head.next != null) {
            previousNode = head;
            head = head.next;

            if(firstNode.next == null) break;

            if(head.next == null) {
                previousNode.next = null;
                if(endNode == null) endNode = head;
                head.next = previousNode;
                head = firstNode;
            }
        }

        return endNode;
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if(n<1) return head;

        ListNode current = head;
        int total = 1;
        while(current.next!=null) {
            current = current.next;
            total++;
        }

        current = head;
        if(total == n) return current.next;

        for(int i=1;i<total-n;i++){
            current = current.next;
        }
        if(current.next!=null && current.next.next!=null){
            ListNode last = current.next.next;
            current.next = last;
        } else {
          current.next = null;
        }

        return head;
    }

    private ListNode _addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        int carry = 0;
        while(l1!=null || l2!=null || carry!=0){
            if(l1!=null){
                carry += l1.val;
                l1 = l1.next;
            }
            if(l2!=null){
                carry += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(carry%10);
            p = p.next;
            carry /= 10;
        }
        return dummy.next;
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode firstNode = null;
        ListNode currentNode = null;
        int val1;
        int val2;
        int upperAddition = 0;

        while(l1!=null || l2!=null || upperAddition!=0) {
            if(l1!=null){
                val1 = l1.val;
                l1 = l1.next;
            } else {
                val1 = 0;
            }
            if(l2!=null){
                val2 = l2.val;
                l2 = l2.next;
            } else {
                val2 = 0;
            }

            int result = val1 + val2 + upperAddition;
            upperAddition = 0;
            if(result >= 10) {
                upperAddition ++;
            }

            ListNode newNode = new ListNode(result % 10);

            if(currentNode == null){
                currentNode = newNode;
                firstNode = currentNode;
            }else{
                currentNode.next = newNode;
                currentNode = currentNode.next;
            }

        }

        return firstNode;
    }

    private int[] getLeafNodeVal(ListNode l){
        int[] result;

        if(l.next!=null) {
            result = getLeafNodeVal(l.next);
            //delete the leaf node
            if(result[0]!=0 && result[1]==0) {
                l.next = null;
                result[1] = 1;
            }

            return result;
        }

        return new int[]{l.val, 0};
    }

    private void printLinkedList(ListNode node) {
        if(node==null) {
            System.out.print("Null");
            return;
        }
        System.out.print(node.val);
        if(node.next!=null) {
            System.out.print("->");
            printLinkedList(node.next);
        }
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }

        @Override public String toString() {
            return String.valueOf(this.val);
        }
    }

}
