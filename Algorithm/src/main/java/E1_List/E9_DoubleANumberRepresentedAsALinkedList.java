package E1_List;

public class E9_DoubleANumberRepresentedAsALinkedList {

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

    public static ListNode doubleIt(ListNode head) {
        ListNode root= reverseList(head);
//        println(node);
        ListNode node=root;
        ListNode prevNode=null;
        int remaining=0;

        while(node!=null){
            int newVal=(node.val*2+remaining)%10;
            remaining=(node.val*2+remaining)/10;
            node.val=newVal;
            prevNode=node;
            node=node.next;
        }
        if(remaining!=0){
            ListNode tailNode=new ListNode(remaining);
            prevNode.next=tailNode;
        }
        return reverseList(root);
    }

    public static ListNode reverseList(ListNode head){
        ListNode node=head;
        ListNode prevNode=null;

        while(node!=null){
            ListNode nextNode=node.next;
            node.next=prevNode;
            prevNode=node;
            node=nextNode;
        }
        return prevNode;
    }

    public static void println(ListNode root){
        ListNode node=root;
        while(node!=null){
            System.out.printf("%s,", node.val);
            node=node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- You are given (the head of a non-empty linked list) representing (a non-negative integer) without (leading zeroes).
        //* Return (the head of the linked list) after doubling it.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //The number of nodes in the list is in the range [1, 10^4]
        //0 <= Node.val <= 9
        //The input is generated such that the list represents a number that does not have leading zeros, except the number 0 itself.
        //
        //- Brainstorm
        //- Reverse cho nó 2 lần là được + remaining value thêm vào.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //
        int[] head = {4,5,1,9};
        ListNode root = new ListNode(head[0]);
        ListNode curNode = root;
        for(int i=1;i<head.length;i++){
            ListNode newNode=new ListNode(head[i]);
            curNode.next=newNode;
            curNode=newNode;
        }
        println(doubleIt(root));
        //
        //#Reference:
        //2513. Minimize the Maximum of Two Arrays
        //1541. Minimum Insertions to Balance a Parentheses String
        //1648. Sell Diminishing-Valued Colored Balls
    }
}
