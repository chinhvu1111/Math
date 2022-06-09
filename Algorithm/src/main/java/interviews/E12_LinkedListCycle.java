package interviews;

import java.util.HashSet;

public class E12_LinkedListCycle {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public static boolean hasCycle(ListNode head) {
        HashSet<ListNode> listNodes=new HashSet<>();
        listNodes.add(head);

        while(head!=null){
            head=head.next;
            if(listNodes.contains(head)){
                return true;
            }
            listNodes.add(head);
        }
        return false;
    }

    public static boolean hasCycleOptimize(ListNode head) {
        if(head==null){
            return false;
        }

        ListNode slow=head;
        ListNode fast=head.next;

        while(slow!=null&&fast!=null){
            slow=slow.next;
            fast=fast.next;
            if(fast!=null){
                fast=fast.next;
            }
            if(fast==slow){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(3);
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(0);
        ListNode listNode3=new ListNode(-4);
        listNode3.next=listNode1;
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
//        listNode1.next=listNode;

        System.out.println(hasCycle(listNode));
    }
}
