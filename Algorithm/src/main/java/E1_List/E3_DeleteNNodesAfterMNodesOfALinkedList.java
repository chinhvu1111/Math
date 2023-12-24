package E1_List;

public class E3_DeleteNNodesAfterMNodesOfALinkedList {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode deleteNodes(ListNode head, int m, int n) {
        ListNode node=head;
        int keepNum=0, removedNum=0;

        //arr=[1,2,3,4,5,6,7,8,9,10,11,12,13]
        // [(1,2),(3,4,5),...]
        //
        //m=2, n=3
        //rs= [1,2,6,7,11,12]
        //
        //
        while(node!=null){
            if(keepNum==m-1){
                // System.out.println(node.val);
                ListNode prevNode=node;

                while (node!=null&&removedNum<=n){
                    node=node.next;
                    removedNum++;
                }
                prevNode.next = node;
                removedNum=0;
                keepNum=0;
                continue;
            }else{
                node=node.next;
            }
            keepNum++;
        }
        return head;
    }

    public static void main(String[] args) {
        //** Đề bài:
        //- Keep m nodes starting with current node
        //- Remove the next n nodes
        //- Keep repeating steps 2 and 3 until you reach the end of the list.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //
        //- Brainstorm:
        //
        //#Reference:
        //1171. Remove Zero Sum Consecutive Nodes from Linked List
    }
}
