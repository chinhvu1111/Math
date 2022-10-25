package interviews;

import java.util.List;

public class E179_ConvertSortedListToBinarySearchTree {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static ListNode findMiddleNode(ListNode node){
        ListNode slow=node;
        ListNode fast=node;

        while (fast.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        return slow;
    }

    public static void reverseLinkedList(ListNode middleNode){
        ListNode node=middleNode;
        ListNode nextNode=node.next;

        //a(node) --> b(node.next) --> c(saved)
        //a(node) <-- b(node.next) -|-> c
        //Thực tế tư duy như sau:
        //a <-- b, b <--c , c <--...
        while (nextNode!=null){
            ListNode tmp=nextNode;
            //Saved c
            ListNode tempNextNextNode=nextNode.next;
            //b --> a
            nextNode.next=node;
            node.next=null;
            node=tmp;
            nextNode=tempNextNextNode;
        }
    }

    public static TreeNode sortedListToBST(ListNode head) {
        //VD:
        //10(slow, fast), -3, 0, 5, 9
        //10, -3(slow), 0(fast), 5, 9
        //10, -3, 0(slow), 5, 9(fast)
        ListNode middleNode=findMiddleNode(head);
        System.out.println(middleNode.val);
        reverseLinkedList(middleNode);

        return null;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(10);
        ListNode listNode1=new ListNode(-3);
        ListNode listNode2=new ListNode(0);
        ListNode listNode3=new ListNode(5);
        ListNode listNode4=new ListNode(9);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        System.out.println(sortedListToBST(listNode));
    }
}
