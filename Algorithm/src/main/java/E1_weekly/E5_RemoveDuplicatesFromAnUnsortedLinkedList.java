package E1_weekly;

import java.util.HashMap;

public class E5_RemoveDuplicatesFromAnUnsortedLinkedList {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode deleteDuplicatesUnsorted(ListNode head) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        ListNode node=head;

        while (node!=null){
            mapCount.put(node.val, mapCount.getOrDefault(node.val, 0)+1);
            node=node.next;
        }
        node=head;
        ListNode prevNode=null;

        while (node!=null){
            if(mapCount.get(node.val)>1){
                //a -> b -> c
                if(prevNode!=null){
                    prevNode.next=node.next;
                }else{
                    head=node.next;
                }
            }else{
                prevNode=node;
            }
            node=node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the head of a linked list, find all the values that (appear more than once) in the list
        // and delete the nodes that have any of those values.
        //* Return the linked list after the deletions.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the list is in the range [1, 10^5]
        //1 <= Node.val <= 10^5
        //
        //- Brainstorm
        //
        //#Reference:
        //82. Remove Duplicates from Sorted List II
    }
}
