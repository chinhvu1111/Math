package E1_List;

public class E8_RemoveNodesFromLinkedList {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode removeNodes(ListNode head) {
        head=reverseList(head);
//        println(head);
        int maxVal= head.val;
        ListNode curNode=head;
        ListNode prevNode=null;
        while(curNode!=null){
            maxVal=Math.max(maxVal, curNode.val);
            ListNode nextNode=curNode.next;

            if(curNode.val<maxVal){
                if(nextNode!=null){
                    curNode.val=nextNode.val;
                    curNode.next=nextNode.next;
                }else{
                    prevNode.next = null;
                    break;
                }
            }else{
                prevNode=curNode;
                curNode=curNode.next;
            }
        }
//        println(head);
        head=reverseList(head);
        return head;
    }

    public static ListNode reverseList(ListNode head){
        ListNode prevNode=null;
        ListNode curNode=head;
        //null -> 1 -> 2 -> 3
        //
        while(curNode!=null){
            ListNode nextNode=curNode.next;
            curNode.next=prevNode;
            prevNode=curNode;
            curNode=nextNode;
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
        //- You are given the (head) of a linked list.
        //Remove (every node) which has a node with (a greater value anywhere) to the (right side of it).
        //* Return the head of the modified linked list.
        //- Tức là xoá 1 node đi khi <=> có right node of it has greater value.
        //Ex:
        // 5 -> 2 -> 13 -> 3 -> 8
        //Input: head = [5,2,13,3,8]
        //Output: [13,8]
        //Explanation: The nodes that should be removed are 5, 2 and 3.
        //- Node 13 is to the right of node 5.
        //- Node 13 is to the right of node 2.
        //- Node 8 is to the right of node 3.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraints:
        //The number of the nodes in the given list is in the range [1, 10^5].
        //1 <= Node.val <= 10^5
        //
        //- Brainstorm:
        //- Với bài này thì:
        //  + Cẩn thận với case max value nằm ở giữa:
        //  Ex:
        //  5 -> 2 -> 12 -> 10 -> 8
        //  => 10 sẽ không bị xoá dó 12 nằm left của 10 chứ không phải right.
        //- Đi từ cuối lên
        //  + Lấy max val --> Nếu node nào có (val != max) ==> Delete
        //- Làm cách này thì ta cần:
        //  + Reverse 2 lần.
        //
        int[] head = {4,5,1,9};
        ListNode root = new ListNode(head[0]);
        ListNode curNode = root;
        for(int i=1;i<head.length;i++){
            ListNode newNode=new ListNode(head[i]);
            curNode.next=newNode;
            curNode=newNode;
        }
        removeNodes(root);
        println(root);
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n)
        //#Reference:
        //1172. Dinner Plate Stacks
        //2645. Minimum Additions to Make Valid String
        //1367. Linked List in Binary Tree
    }
}
