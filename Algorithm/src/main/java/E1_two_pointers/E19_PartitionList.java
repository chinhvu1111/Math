package E1_two_pointers;

import java.util.List;

public class E19_PartitionList {

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

    public static ListNode partition(ListNode head, int x) {
        ListNode lessThanElement = null;
        ListNode lessThanElementRoot = null;
//        ListNode lessThanElementPrev = null;
        ListNode greaterThanElement = null;
        ListNode greaterThanElementRoot = null;
        ListNode node=head;
        ListNode prevNode=null;

        while(node!=null){
            if(node.val<x){
//                lessThanElementPrev=lessThanElement;
                if(lessThanElement==null){
                    lessThanElement=node;
                    lessThanElementRoot=node;
                }else{
                    lessThanElement.next=node;
                    lessThanElement=node;
                }
            }else{
                if(greaterThanElement==null){
                    greaterThanElement=node;
                    greaterThanElementRoot=node;
                }else{
                    greaterThanElement.next=node;
                    greaterThanElement=node;
                }
            }
//            prevNode=node;
            node=node.next;
//            if(prevNode!=null){
//                prevNode.next=node.next;
//            }
        }
        if(greaterThanElement!=null){
            greaterThanElement.next=null;
        }
        if(lessThanElement==null){
            return greaterThanElementRoot;
        }
        lessThanElement.next=greaterThanElementRoot;
        return lessThanElementRoot;
    }

    public static void println(ListNode root){
        while(root!=null){
            System.out.printf("%s, ",root.val);
            root=root.next;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given the head of a linked list and a value x,
        // partition it such that all nodes less than x come before nodes greater than or equal to x.
        //- You should preserve the original relative order of the nodes in each of the two partitions.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //1 - 4 - 3 - 2 - 5 - 2
        //
        //
        int[] head = {1,4,3,2,5,2};
        int x = 3;
        ListNode root=new ListNode(head[0]);
        ListNode node=root;

        for(int i=1;i<head.length;i++){
            ListNode newNode=new ListNode(head[i]);
            node.next=newNode;
            node=newNode;
        }
        ListNode newRoot = partition(root, x);
        println(newRoot);
        //#Reference:
        //2161. Partition Array According to Given Pivot
    }
}
