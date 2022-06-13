package interviews;

import java.util.LinkedList;

public class E20_MaximumTwinSumOfALinkedList {

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

    public static int pairSum(ListNode head) {
        ListNode middleNode=getMiddleNode(head);
        ListNode tail=reverseLinkedList(middleNode);

        int rs=0;
        while(tail!=null){
            rs=Math.max(rs, tail.val+head.val);
            tail=tail.next;
            head=head.next;
        }
        return rs;
    }

    public static ListNode reverseLinkedList(ListNode middleNode){
        ListNode prevNode=middleNode;
        ListNode temp=null;

        if(prevNode!=null){
            temp=prevNode.next;
            prevNode.next=null;
        }

        while(temp!=null){
            ListNode nextNode=temp.next;
            temp.next=prevNode;
            prevNode=temp;
            temp=nextNode;
        }
        return prevNode;
    }

    public static ListNode getMiddleNode(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;

        while(fast!=null){
            slow=slow.next;
            fast=fast.next;
            if(fast!=null){
                fast=fast.next;
            }else{
                break;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(4);
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(2);
        ListNode listNode3=new ListNode(3);
        ListNode listNode4=new ListNode(1);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        //Tư duy như cũ:
        //Chú ý:
        //Khi init:
        //slow =head;
        //fast=head.next;
        System.out.println(pairSum(listNode));
    }
}
