package E1_daily;

public class E363_ConvertBinaryNumberInALinkedListToInteger {

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

    public static int getDecimalValue(ListNode head) {
        ListNode node = head;
        ListNode prev = null;

        //prev -> node -> next
        //a -> b -> c
        //=>
        //a <- b <- c
        while(node!=null){
//            System.out.println(node.val);
            ListNode nextNode = node.next;
            node.next = prev;
            prev = node;
            node = nextNode;
        }
        node = prev;
        int rs=0;
        int mul=1;
        while(node!=null){
//            System.out.println(node.val);
            rs+=mul*node.val;
            mul = mul << 1;
            node = node.next;
        }
        return rs;
    }

    public static int getDecimalValueOptimization(ListNode head) {
        int num = head.val;
        while (head.next != null) {
            num = (num << 1) | head.next.val;
            head = head.next;
        }
        return num;
    }

    public static void main(String[] args) {
        //** Requirement
        //
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Thay vì reverse thì ta dùng công thức ntn:
        //  + num = (num << 1) | head.next.val;
        //  + 100 | 1 => thêm 1 vào cuối
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //# Reference:
        //
        //
        ListNode node=new ListNode(1);
        ListNode node1=new ListNode(0);
        node.next=node1;
        ListNode node2=new ListNode(1);
        node1.next=node2;
        System.out.println(getDecimalValue(node));
        System.out.println(getDecimalValueOptimization(node));
        //
        //#Reference:
        //3574. Maximize Subarray GCD Score
        //1716. Calculate Money in Leetcode Bank
        //1627. Graph Connectivity With Threshold
    }
}
