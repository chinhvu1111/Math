package interviews.bytedance;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class E9_AddTwoNumbersII {

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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode temp=l1;
        ListNode prevNode=null;
        ListNode temp1=l2;
        ListNode prevNode1=null;

        while (temp!=null){
            //Reverse list 1
            ListNode nextNodeL1=temp.next;
            temp.next=prevNode;
            prevNode=temp;
            temp=nextNodeL1;
        }
        while (temp1!=null){
            //Reverse list 2
            ListNode nextNodeL2=temp1.next;
            temp1.next=prevNode1;
            prevNode1=temp1;
            temp1=nextNodeL2;
        }
        ListNode headL1=prevNode;
        ListNode headL2=prevNode1;
        ListNode newNode=null;
        int memNum=0;

        while (headL1!=null||headL2!=null||memNum!=0){
            ListNode nodeL1=null;
            ListNode nodeL2=null;

            if(headL1!=null){
                nodeL1=headL1;
                headL1=headL1.next;
            }
            if(headL2!=null){
                nodeL2=headL2;
                headL2=headL2.next;
            }
            int left=nodeL1==null?0:nodeL1.val;
            int right=nodeL2==null?0:nodeL2.val;
            int newValue=(left+right+memNum)%10;
            ListNode currentNode=null;
            currentNode=new ListNode(newValue);
            if(newNode==null){
                newNode=currentNode;
            }else{
                currentNode.next=newNode;
                newNode=currentNode;
            }
            memNum=(left+right+memNum)/10;
        }
//        if(memNum!=0){
//            ListNode currentNode=new ListNode(memNum);
//            currentNode.next=newNode;
//            newNode=currentNode;
//        }

        return newNode;
    }

    public static ListNode addTwoNumbersStack(ListNode l1, ListNode l2) {
        Queue<Integer> l1Stack=new LinkedList<>();
        Queue<Integer> l2Stack=new LinkedList<>();
        ListNode tempL1=l1;
        ListNode tempL2=l2;

        while (tempL1!=null){
            l1Stack.add(tempL1.val);
            tempL1=tempL1.next;
        }
        while (tempL2!=null){
            l2Stack.add(tempL2.val);
            tempL2=tempL2.next;
        }
        int memNum=0;
        ListNode newNode = null;
        ListNode prevNode = null;

        while (!l1Stack.isEmpty()||!l2Stack.isEmpty()||memNum!=0){
            Integer num1=null;
            Integer num2=null;

            if(!l1Stack.isEmpty()){
                num1=l1Stack.poll();
            }
            if(!l2Stack.isEmpty()){
                num2=l2Stack.poll();
            }
            int left=num1==null?0:num1;
            int right=num2==null?0:num2;
            int newValue=(left+right+memNum)%10;
            ListNode currentNode=null;
            currentNode=new ListNode(newValue);
            if(newNode==null){
                newNode=currentNode;
                prevNode=newNode;
            }else{
                newNode.next=currentNode;
                newNode=currentNode;
            }
            memNum=(left+right+memNum)/10;
        }
        return prevNode;
    }

    public static void println(ListNode head){
        while (head!=null){
            System.out.printf("%s, ",head.val);
            head=head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
//        int[] arrL1=new int[]{7,2,4,3};
//        int[] arrL2=new int[]{5,6,4};
        //Case 1:
        //- Case phải thêm digit
//        int[] arrL1=new int[]{5};
//        int[] arrL2=new int[]{5};
        //Case 2:
        //- Thêm >1 digits vào list
//        int[] arrL1=new int[]{1};
//        int[] arrL2=new int[]{9, 9};
        int[] arrL1=new int[]{7,2,4,3};
        int[] arrL2=new int[]{5,6,4};
        ListNode l1=null;
        ListNode l1Root=null;
        ListNode l2=null;
        ListNode l2Root=null;

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

        for(Integer e: arrL2){
            ListNode currentNode=new ListNode(e);
            if(l2==null){
                l2Root=currentNode;
                l2=currentNode;
            }else{
                l2.next=currentNode;
                l2=currentNode;
            }
        }
        println(l1Root);
        println(l2Root);
        println(addTwoNumbers(l1Root, l2Root));
//        println(addTwoNumbersStack(l1Root, l2Root));
        //
        //** Đề bài:
        //- Sinh ra 1 list biểu diễn kết quả sum của 2 số đã cho (2 số cũng biểu diễn bằng list)
        //VD:
        //int[] arrL1=new int[]{7,2,4,3};
        //int[] arrL2=new int[]{5,6,4};
        //==> 7, 8, 0, 7
        //+ Cộng từ right--> left + cộng có nhớ
        //+ Làm thế nào để xử lý mà không reverse
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1.
        //- Reverse để có thể lấy đúng thứ tự tính toàn từ cuối lên
        //1.1,
        //- Reverse nhớ rằng điều kiện out loop phải có cả (số nhớ (memNumber) !=0)
        //- value= (left+ right + memNumber)%10
        //- memNumber=(left+ right + memNumber)/10
        //
        //1.2, Ta dùng queue + tư duy tương tự để không phải reverse list
        //1.3, Tối ưu hơn bằng cách dùng solution như sau:
        //https://leetcode.com/problems/add-two-numbers-ii/solutions/687339/java-o-n-solution-with-follow-up-question-no-recursion-no-stacks/
        //VD:
        //9,4,4,3
        //5,6,7
        //- Ta sẽ cộng bình thường từ (Left --> right trước)
        //= 9<-9<-10<-10
        //- Sau đó ta sẽ thực hiện việc chuẩn hoá
        //Từ right --> left, bù số dư vào
        //==> Result : 1,0,0,1,0
        //1.4, Format code phổ biến:
        //Khi muốn add node mới vào next.
        //=======
//        currentNode=new ListNode(newValue);
//        if(newNode==null){
//            newNode=currentNode;
//            prevNode=newNode;
//        }else{
//            newNode.next=currentNode;
//            newNode=currentNode;
//        }
        //=======
        //#Reference:
        //446. Arithmetic Slices II - Subsequence
        //2. Add Two Numbers
        //1634. Add Two Polynomials Represented as Linked Lists
        //3. Longest Substring Without Repeating Characters
        //43. Multiply Strings
        //67. Add Binary
        //371. Sum of Two Integers
    }
}
