package interviews.bytedance;

import java.util.List;

public class E3_ReverseLinkedListII {

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

    public static ListNode reverseBetween(ListNode head, int left, int right) {
        int i=1;
        ListNode node=head;
        //Node vị trí left-1
        ListNode prevNode=null;
        while (i<left&&node!=null){
            prevNode=node;
            node=node.next;
            i++;
        }
//        System.out.println(node.val);
        //Node vị trí right
        ListNode prevTemp=null;
        //Node vị trí right+1
        ListNode nextNode=null;
        //(prev) --> 2 --> 3 --> 4 --> 5 --> (nextNode)
        //(prev) <-- 2 <-- 3 <--4 <-- 5
        //Node vị trí left
        ListNode nodeLeft=node;
        while (node!=null&&i<=right){
            nextNode=node.next;
            node.next=prevTemp;
            prevTemp=node;
            node=nextNode;
            i++;
        }
//        System.out.println(node.val);
        if(prevNode!=null){
            prevNode.next=prevTemp;
        }else{
            head=prevTemp;
        }
        if(nodeLeft!=null){
            nodeLeft.next=node;
        }
        return head;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //[(1),2,3,4,(5),2,3]
        //left = 1
        //right = 5
        //rs: [5,4,3,2,1,2,3]
//        ListNode listNode=new ListNode(1);
//        ListNode listNode1=new ListNode(2);
//        ListNode listNode2=new ListNode(3);
//        ListNode listNode3=new ListNode(4);
//        ListNode listNode4=new ListNode(5);
//        listNode.next=listNode1;
//        listNode1.next=listNode2;
//        listNode2.next=listNode3;
//        listNode3.next=listNode4;
//        println(listNode);
//        listNode=reverseBetween(listNode, 2, 4);
//        println(listNode);

        ListNode listNode=new ListNode(1);
        ListNode listNode1=new ListNode(2);
        ListNode listNode2=new ListNode(3);
        ListNode listNode3=new ListNode(4);
        ListNode listNode4=new ListNode(5);
        ListNode listNode5=new ListNode(2);
        ListNode listNode6=new ListNode(3);
        listNode.next=listNode1;
        listNode1.next=listNode2;
        listNode2.next=listNode3;
        listNode3.next=listNode4;
        listNode4.next=listNode5;
        listNode5.next=listNode6;
        println(listNode);
        listNode=reverseBetween(listNode, 1, 5);
        println(listNode);
        //#Reference
        //93. Restore IP Addresses
        //138. Copy List with Random Pointer
        //25. Reverse Nodes in k-Group
        //147. Insertion Sort List
    }
}
