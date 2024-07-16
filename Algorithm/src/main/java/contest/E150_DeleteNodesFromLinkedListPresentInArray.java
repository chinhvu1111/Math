package contest;

import java.util.HashSet;

public class E150_DeleteNodesFromLinkedListPresentInArray {

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

    public static ListNode modifiedList(int[] nums, ListNode head) {
        ListNode node=head;
        HashSet<Integer> exists=new HashSet<>();

        for(int e: nums){
            exists.add(e);
        }
        ListNode prev=null;

        while (node!=null){
            if(exists.contains(node.val)){
                if(prev!=null){
                    prev.next=node.next;
                }else{
                    head=node.next;
                }
                //prev -> node -> node.next
            }else{
                prev=node;
            }
            node=node.next;
        }
        return head;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of integers nums and the head of a linked list.
        //* Return the head of the modified linked list after (removing all nodes) from the linked list that (have a value that exists in nums).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 105
        //1 <= nums[i] <= 105
        //All elements in nums are unique.
        //The number of nodes in the given list is in the range [1, 105].
        //1 <= Node.val <= 105
        //The input is generated such that there is at least one node in the linked list that has a value not present in nums.
        //
        //** Brainstorm
        //
        //
    }
}
