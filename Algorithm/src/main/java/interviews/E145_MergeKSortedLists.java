package interviews;

public class E145_MergeKSortedLists {


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

    public static ListNode mergeTwoList(ListNode left, ListNode right){
        ListNode tempLeft=left;
        ListNode tempRight=right;
        ListNode beforeLeft=null;

        while (tempLeft!=null){
            if(tempLeft.val> tempRight.val){
                if(beforeLeft!=null){
                    beforeLeft.next=tempRight;
                    tempRight=tempRight.next;
                }
            }
            beforeLeft=tempLeft;
            tempLeft=tempLeft.next;
        }
        if(tempRight!=null&&beforeLeft!=null){
            beforeLeft.next=tempRight;
        }
        ListNode printNode=left;

        while (printNode!=null){
            printNode=printNode.next;
        }
        return left;
    }

    public static ListNode mergeKLists(ListNode[] lists) {

        return null;
    }

    public static void main(String[] args) {
        ListNode listNode=new ListNode(1);
        ListNode listNode1=new ListNode(4);
        ListNode listNode2=new ListNode(5);
        listNode.next=listNode1;
        listNode1.next=listNode2;

        ListNode listNode3=new ListNode(1);
        ListNode listNode4=new ListNode(3);
        ListNode listNode5=new ListNode(4);
        listNode3.next=listNode4;
        listNode4.next=listNode5;

        ListNode[] lists=new ListNode[]{listNode, listNode3};

        mergeTwoList(listNode, listNode3);
    }
}
