package E1_daily;

public class E91_SplitLinkedListInParts {

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

    public static ListNode[] splitListToParts(ListNode head, int k) {
        int n=0;
        ListNode node = head;

        while(node!=null){
            node=node.next;
            n++;
        }
        int remainingVal = n%k;
        int sizeList = n/k;
        node=head;
        ListNode[] rs=new ListNode[k];
        int index=0;

        while(node!=null){
            int curSize=sizeList;

            if(remainingVal>0){
                curSize++;
                remainingVal--;
            }
            rs[index]=node;
            ListNode prevNode=node;
            index++;
            for (int i = 0; i < curSize; i++) {
                prevNode=node;
                node=node.next;
            }
            prevNode.next=null;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the head of a singly linked list) and (an integer k), split (the linked list) into (k consecutive) (linked list parts).
        //- The (length of each part) should be (as equal as possible):
        //  + no two parts should have a size differing by (more than one).
        //- This may lead to some parts being null.
        //- The parts should be in (the order of occurrence) in (the input list), and (parts occurring earlier) should
        // always have a size greater than or equal to parts occurring later.
        //* Return (an array of the k parts).
        //
        //- Tức là bài toán chỉ về việc chia 1 list thành k phần sao cho bằng nhau nhất có thể
        //  + Các list đằng sau luôn có size>= đằng trước (Và khác nhau nhiều nhất 1)
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //The number of nodes in the list is in the range [0, 1000].
        //0 <= Node.val <= 1000
        //1 <= k <= 50
        //
        //- Brainstorm
        //Example 2:
        //
        //Input: head = [1,2,3,4,5,6,7,8,9,10], k = 3
        //Output: [[1,2,3,4],[5,6,7],[8,9,10]]
        //Explanation:
        //The input has been split into consecutive parts with size difference at most 1, and earlier parts are a larger size than the later parts.
        //- Ta cần tính ra các size của list bằng cách dùng (size, k)
        //Ex:
        //n=10,k=3
        //3,3,3,1
        //n%k = 1
        //  + Số dư này sẽ được add vào dần dần các element của các list.
        //n=3, k=5
        //  + n/k = 3/5 = 0
        //  + n%k = 3 ==> Sẽ được add dần vào từ đấu ==> Cuối
        //(1,1,1,[],[])
        //
        //
        int[] head = {1,2,3};
        int k = 5;
        ListNode node=null;
        ListNode root=null;
        for(int i=0;i<head.length;i++){
            ListNode tmp=new ListNode(head[i]);
            if(node!=null){
                node.next=tmp;
            }else{
                root=tmp;
            }
            node=tmp;
        }
        ListNode[] nodes = splitListToParts(root, k);

        for(int i=0;i<nodes.length;i++){
            if(nodes[i]!=null){
                System.out.println(nodes[i].val);
            }else{
                System.out.println();
            }
        }
        //1.1, Optimization
        //- Có thể tạo list mới ==> Để tránh modify list
        //  + Rủi ro lớn.
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //#Reference:
        //2674. Split a Circular Linked List
    }
}
