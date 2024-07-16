package E1_List;

public class E10_MergeNodesInBetweenZeros {

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

    public static ListNode mergeNodes(ListNode head) {
        ListNode node = head;
        ListNode prevNode = null;

        while (node!=null){
            //Steps:
            //- Replace (the zero node) with the node having the val == sum
            ListNode curHead=node;
            node=node.next;
            int curSum=0;

            while(node!=null&&node.val!=0){
                curSum+=node.val;
                node=node.next;
            }
            if(curSum!=0){
                curHead.val=curSum;
                curHead.next=node;
            }else if(prevNode!=null){
                prevNode.next=null;
            }
            prevNode=curHead;
        }
        return head;
    }

    public static ListNode mergeNodesRefactor(ListNode head) {
        ListNode node = head.next;
        ListNode newNode = node;

        while (node!=null){
            ListNode curHead = node;
            int curSum=0;

            while(node!=null&&node.val!=0){
                curSum+=node.val;
                node=node.next;
            }
            if(node!=null){
                node=node.next;
            }
            curHead.val=curSum;
            curHead.next=node;
        }
        return newNode;
    }

    public static void println(ListNode node){
        while(node!=null){
            System.out.println(node.val);
            node=node.next;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (the head of a linked list), which contains (a series of integers) separated by (0's).
        //- The (beginning) and (end) of the linked list will have (Node.val == 0).
        //- For every (two consecutive 0's), (merge all) the nodes lying in between them into (a single node)
        // whose value is (the sum of all the merged nodes).
        // The modified list should not contain any 0's.
        //* Return (the head) of the modified linked list.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //The number of nodes in the list is in the range [3, 2 * 10^5].
        //0 <= Node.val <= 1000
        //There are no two consecutive nodes with Node.val == 0.
        //The beginning and end of the linked list have Node.val == 0.
        //
        //- Brainstorm
        //- Lấy start node với (val==0) làm mốc
        //  + Sau đó cộng dồn lên sum ==> Gán cho nó = sum
        //- Cần lưu lại prev node để có thể loại bỏ ending node has (val==0)
        //
        //* Kinh nghiệm:
        //- Chọn mốc traverse cho simpler.
        //
        //1.1, Optimization
        //- Ta có thể cộng cả giá trị bằng ==0 luôn
        //  ==> Không cần tách ra: Làm cho code simpler
        //- Chọn mốc là (node.val!=0):
        //  + Init node = head.next
        //  + current_node.next = node (Sau khi traverse)
        //      ==> Code sẽ look simpler
        //- Bài này ngoài ra có thể làm recursion
        //  ==> Space : O(n)
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        int[] head = {0,3,1,0,4,5,2,0};
        int n=head.length;
        ListNode root=new ListNode(head[0]);
        ListNode node=root;

        for(int i=1;i<n;i++){
            ListNode newNode=new ListNode(head[i]);
            node.next=newNode;
            node=newNode;
        }
//        ListNode newRoot = mergeNodes(root);
        ListNode newRoot = mergeNodesRefactor(root);
        println(newRoot);
        //#Reference:
        //817. Linked List Components
    }
}
