package interviews;

import java.util.Stack;

public class E17_PalindromeLinkedList {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    public static boolean isPalindrome(ListNode head) {
        ListNode root=new ListNode(head.val);
        ListNode temp=head;
        int numberNodes=0;

        while(temp.next!=null){
            temp=temp.next;
            ListNode newNode=new ListNode(temp.val);
            newNode.next=root;
            root=newNode;
            numberNodes++;
        }
        int count=0;

        while(count<=numberNodes/2){
            if(root.val!= head.val){
                return false;
            }
            root=root.next;
            head=head.next;
            count++;
        }
        return true;
    }

    public static boolean isPalindromeStackWrong(ListNode head) {
        Stack<ListNode> stack=new Stack<>();
        stack.add(head);

        while (head.next!=null){
            head=head.next;
            ListNode listNode=stack.peek();

            if (listNode!=null&&listNode.val== head.val) {
                stack.pop();
            }else{
                stack.add(head);
            }
        }
        int lengthStack=stack.size();

        for(int i=0;i<lengthStack/2;i++){
            if(stack.get(i).val!=stack.get(lengthStack-i-1).val){
                return false;
            }
        }
        return true;
    }

    public static boolean isPalindromeOptimize(ListNode head) {
        ListNode middleNode=getMiddleNode(head);

        ListNode tail=reversetLinkedList(middleNode);
        ListNode temp=tail;
        ListNode temp1=head;
//        ListNode node=middleNode.next;

        while(temp!=null){
            if(temp.val!=temp1.val){
                return false;
            }
            temp=temp.next;
            temp1=temp1.next;

        }
        return true;
    }

    public static ListNode reversetLinkedList(ListNode middleNode){
        ListNode prevNode=middleNode;
        ListNode temp=null;

        if(prevNode!=null){
            temp=prevNode.next;
            prevNode.next=null;
//            temp.next=prevNode;
//            prevNode.next=null;
        }
        //Temp cần phải next nên temp sẽ đi đằng trước
        //Temp 1 cần lưu giá trị tiếp theo (next) --> Vì khi nối currentNode --> Previous node (Mất connection đến node tiếp theo r)
        while (temp!=null){
            ListNode nextNode=temp.next;
            temp.next=prevNode;
            prevNode=temp;
            temp=nextNode;
        }
        return prevNode;
    }

    public static ListNode getMiddleNode(ListNode head){
        ListNode slow=head;
        ListNode fast=head.next;

        while(fast!=null){
            ListNode prevNode=slow;
            slow=slow.next;
            fast=fast.next;
            if(fast!=null){
                fast=fast.next;
            }else {
                prevNode.next=null;
                break;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(2);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(1);
//        listNode.next = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;

//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(2);
//        ListNode listNode2 = new ListNode(2);
//        ListNode listNode3 = new ListNode(2);
//        ListNode listNode4 = new ListNode(1);
//        listNode.next = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//

        //Tư duy như sau:
        //Case 1 : Tránh cases:
        //Ex : 1,2,1,2
        //
        //Case 2:
        // [1,0,0]
        //Nếu nghĩ rằng nó chỉ có thể xuất hiện 1 phần tử sau khi reduce stack (1...)
        //Case 3:
        //1,2,1,2
        //Xuất hiện nhiều hơn 1 phần tử trong stacks

        //Có 2 case phổ biến:
        //Case 4 : 1,2,2,1
        //Case 5 : 1,2,3,2,1
        //Sai case 6 : [1,2]
        //Cần phải while(temp!=null) { check val --> Sau đó mới next}

        //
        //Với middle point khác nhau.
//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(2);
//        ListNode listNode2 = new ListNode(4);
//        ListNode listNode3 = new ListNode(2);
//        ListNode listNode4 = new ListNode(1);
//        listNode.next = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;

        ListNode listNode = new ListNode(1);
        ListNode listNode1 = new ListNode(2);
        ListNode listNode2 = new ListNode(2);
        ListNode listNode3 = new ListNode(1);
        listNode.next = listNode1;
        listNode1.next = listNode2;
        listNode2.next = listNode3;

//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(2);
//
//        listNode.next = listNode1;

        //Sai case 7 : [1,0,3,4,0,1]
//        ListNode listNode = new ListNode(1);
//        ListNode listNode1 = new ListNode(0);
//        ListNode listNode2 = new ListNode(3);
//        ListNode listNode3 = new ListNode(4);
//        ListNode listNode4 = new ListNode(0);
//        ListNode listNode5 = new ListNode(1);
//        listNode.next = listNode1;
//        listNode1.next = listNode2;
//        listNode2.next = listNode3;
//        listNode3.next = listNode4;
//        listNode4.next = listNode5;


        System.out.println(isPalindrome(listNode));
        //Với các dạng bài linjkedlist nếu muốn bỏ O(n) space --> Chỉ có thể là tạo ra 1 linkedlist mới
        //VD: Như bài này sẽ tạo ra 1 linkedlist dạng reverse
        //---> Sẽ phải loop 1 vòng để create linkedList trước.
        //- Nếu dùng stack thì vẫn phải xác định (middle point() nên vẫn cần xác định độ dài linkedlist
        //1, Phần có thể tối ưu ở đây là có thể xác định middle point
        //Middle point là gì : Là điểm chính giữa của 1 linked list có độ dài "lẻ" và sẽ là 2 points trong trường hợp linked list có độ dài "chẵn"
        //Để đo độ dài 1 linked list --> Đơn giản ta hướng đến việc traverse faster >>
        // Dùng 2 pointers trong trường hợp này để:
        //- 1 point để check độ dài nếu nó vượt quá next.next --> Và để lưu lại middle point
        //- 2 point để check chạy double linked list --> Dùng để check point chạy vượt biên (Length của linkedlist)

        //Ngoài ra còn reverse linked list nếu chỉ dùng 1 node để làm --> Sẽ rất khó vì:
        //+ Vừa cut next của node đó đến node trước + nối node đó lại với node sau
        //+ Node đằng sau đó cũng cần phải nối lại
        //--> Khởi tạo 2 node ngay từ đầu để lưu temp lại

        System.out.println(isPalindromeOptimize(listNode));
    }
}
