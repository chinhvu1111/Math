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

            if(fast==null){
                break;
            }
        }
        return slow;
    }

    public static ListNode reverseLinkedListLast(ListNode startNode, ListNode middleNode){
        ListNode node=startNode;
        ListNode nextNode=node.next;
        node.next=null;

        //1, Cách tư duy next nodes đôi một
        //a(node) --> b(node.next) --> c(saved)
        //a(node) <-- b(node.next) -|-> c
        //Thực tế tư duy như sau:
        //a <-- b, b <--c , c <--...
        //
        //2, Cách tư duy điều kiện loop traverse
        while (node!=middleNode){
            ListNode tmp=nextNode;
            //Saved c
            ListNode tempNextNextNode=nextNode.next;
            //b --> a
            nextNode.next=node;
//            node.next=null;
            node=tmp;
            nextNode=tempNextNextNode;
        }
        return node;
    }

    public static TreeNode buildTreeLeft(ListNode listNode){
        ListNode temp=listNode;
        TreeNode treeNode=new TreeNode(temp.val);
        TreeNode tempTreeNode=treeNode;

        while (temp.next!=null){
            temp=temp.next;
            tempTreeNode.left=new TreeNode(temp.val);
            tempTreeNode=tempTreeNode.left;
        }
        return treeNode;
    }

    public static TreeNode buildTreeRight(ListNode listNode){
        ListNode temp=listNode;
        TreeNode treeNode=new TreeNode(temp.val);
        TreeNode tempTreeNode=treeNode;

        while (temp.next!=null){
            temp=temp.next;
            tempTreeNode.right=new TreeNode(temp.val);
            tempTreeNode=tempTreeNode.right;
        }
        return treeNode;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if(head==null){
            return null;
        }
        //VD:
        //10(slow, fast), -3, 0, 5, 9
        //10, -3(slow), 0(fast), 5, 9
        //10, -3, 0(slow), 5, 9(fast)
        ListNode middleNode=findMiddleNode(head);
        System.out.println(middleNode.val);
        ListNode middleNextNode=middleNode.next;
        reverseLinkedListLast(head, middleNode);
        TreeNode leftTreeNode=buildTreeLeft(middleNode);
        TreeNode rightTreeNode;

        if(middleNextNode!=null){
            rightTreeNode=buildTreeRight(middleNextNode);
            leftTreeNode.right=rightTreeNode;
        }

        return leftTreeNode;
    }

    public static void rotateLeft(ListNode listNode){

    }

    public static void rotateRight(ListNode listNode){

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
//        System.out.println(sortedListToBST(listNode));

        //Test case 2: Test case phổ biến với bài rùa và thỏ
        ListNode listNode5=new ListNode(1);
        listNode5.next= new ListNode(3);
//        System.out.println(sortedListToBST(listNode5));
        //Test case 3:
        //[0,1,2,3,4,5]
        //
        ListNode listNode6=new ListNode(0);
        ListNode listNode7=new ListNode(1);
        ListNode listNode8=new ListNode(2);
        ListNode listNode9=new ListNode(3);
        ListNode listNode10=new ListNode(4);
        ListNode listNode11=new ListNode(5);
        listNode6.next=listNode7;
        listNode7.next=listNode8;
        listNode8.next=listNode9;
        listNode9.next=listNode10;
        listNode10.next=listNode11;
        System.out.println(sortedListToBST(listNode6));
    }
}
