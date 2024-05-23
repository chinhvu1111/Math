package E1_List;

public class E7_DeleteNodeInALinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void deleteNode(ListNode node) {
        //- Delete node:
        //  + Find the node
        //1 -> (5) -> 3 -> 8
        //
        while (node!=null){
            ListNode curNode = node.next;
            if(curNode!=null){
                node.val=curNode.val;
                if(curNode.next==null){
                    node.next=null;
                    break;
                }
            }
            node=curNode;
        }
    }

    public static void deleteNodeOptimization(ListNode node) {
        //- Delete node:
        //  + Find the node
        //1 -> (5) -> 3 -> 8
        //  + Assign 5 to 3
        //  + Assign 5.next=8
        //
        ListNode curNode = node.next;
        node.val=curNode.val;
        node.next=curNode.next;
    }

    public static void println(ListNode root){
        ListNode node=root;
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- Delete the given node. Note that by deleting the node, we do not mean removing it from memory. We mean:
        //  + The value of the given node should not exist in the linked list.
        //  + The number of nodes in the linked list should decrease by one.
        //  + All the values before node should be in the same order.
        //  + All the values after node should be in the same order.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //
        //
        //- Brainstorm
        //
        //#Reference:
        //2487. Remove Nodes From Linked List
        int[] head = {4,5,1,9};
        int node = 5;
        ListNode root = new ListNode(head[0]);
        ListNode curNode = root;
        for(int i=1;i<head.length;i++){
            ListNode newNode=new ListNode(head[i]);
            curNode.next=newNode;
            curNode=newNode;
        }
//        deleteNode(root.next);
        deleteNodeOptimization(root.next);
        println(root);
    }
}
