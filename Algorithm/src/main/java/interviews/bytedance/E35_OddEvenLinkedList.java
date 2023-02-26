package interviews.bytedance;

public class E35_OddEvenLinkedList {

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

    public static ListNode oddEvenList(ListNode head) {
        int index=0;
        ListNode nodeEven=head;
        ListNode node=head;
        ListNode nodeOdd=null;
        ListNode rootOdd=null;

        while (node!=null){
            if(index%2==1){
                if(rootOdd==null){
                    rootOdd=node;
                }
                if(nodeOdd!=null){
                    nodeOdd.next=node;
                }
                nodeOdd=node;
            }else{
                if(!nodeEven.equals(node)){
                    nodeEven.next=node;
                }
                nodeEven=node;
            }
            node=node.next;
            index++;
        }
        if(nodeOdd!=null&&nodeOdd.next!=null){
            nodeOdd.next=null;
        }
        //Last element có thể là:
        //- even
        //- odd
        //VD:
        //+ even
        //1 --> 2 --> 3 --> 4
        //+ odd
        //1 --> 2 --> 3
        if(nodeEven!=null){
            nodeEven.next=rootOdd;
        }
        return head;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }

    public static double myPow(double x, int n) {
        double ans = 1;
        long absN = Math.abs((long)n);
        while(absN > 0) {
            if((absN&1)==1) ans *= x;
            absN >>= 1;
            x *= x;
        }
        return n < 0 ?  1/ans : ans;
    }

    public static void main(String[] args) {
//        int[] arrL1=new int[]{7,2,4,3};
        int[] arrL1=new int[]{1,2,3,4,5};

        ListNode l1Root=null;
        ListNode l1=null;

        for(Integer e: arrL1){
            ListNode currentNode=new ListNode(e);
            if(l1==null){
                l1Root=currentNode;
                l1=currentNode;
            }else{
                l1.next=currentNode;
                l1=currentNode;
            }
        }
        ListNode newNode=oddEvenList(l1Root);
        println(newNode);

        //Test:
        System.out.println(myPow(4, 6));

        //** Đề bài:
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //VD: [1,2,3,4,5]
        //
        //1 -> 2 -> 3 -> 4 -> 5
        //
        //1 --> 3
        //2 --> 4
        //- Các bước như sau:
        //1: nodeEven=1
        //2: nodeOdd=2
        //3: nodeEven.next=currentNode
        //nodeEven=currentNode
        //currentNode=currentNode.next
        //4: nodeOdd.next=currentNode
        //nodeOdd=currentNode
        //currentNode=currentNode.next
        //#Reference:
        //329. Longest Increasing Path in a Matrix
        //725. Split Linked List in Parts
    }
}
