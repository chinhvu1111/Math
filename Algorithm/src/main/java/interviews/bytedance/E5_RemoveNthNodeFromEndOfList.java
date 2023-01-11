package interviews.bytedance;

public class E5_RemoveNthNodeFromEndOfList {

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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 0;
        ListNode temp=head;

        while (temp!=null){
            temp=temp.next;
            length++;
        }
        temp=head;
        int index=0;

        ListNode prevNode=null;
        while (temp!=null&&index<length-n){
            prevNode=temp;
            temp=temp.next;
            index++;
        }
//        System.out.println(temp.val);
        if(prevNode!=null&&temp!=null){
            prevNode.next=temp.next;
        }else if(prevNode==null&&temp!=null){
            head=temp.next;
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
//        int[] nodes=new int[]{1,2,3,4,5};
        int[] nodes=new int[]{1};
        ListNode listNode=new ListNode(nodes[0]);
        ListNode temp=listNode;

        for(int i=1;i<nodes.length;i++){
            ListNode currentNode=new ListNode(nodes[i]);
            temp.next=currentNode;
            temp=currentNode;
        }
        println(listNode);
//        listNode=rotateRight(listNode, 2);
//        listNode=rotateRight(listNode, 4);
//        listNode=rotateRight(listNode, 1);
//        listNode=removeNthFromEnd(listNode, 2);
        listNode=removeNthFromEnd(listNode, 1);
        println(listNode);
        //#Reference
        //1721. Swapping Nodes in a Linked List
        //1474. Delete N Nodes After M Nodes of a Linked List
        //2095. Delete the Middle Node of a Linked List
    }
}
