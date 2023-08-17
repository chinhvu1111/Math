package contest;

import java.util.HashMap;
import java.util.List;

public class E32_InsertGreatestCommonDivisorsInLinkedList {

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

    public static int getGreatestCommonDivisor(int x, int y){
        if(x==0||y==0){
            return 0;
        }
        int x1=x;
        x=Math.min(x, y);
        y=Math.max(x1, y);

        while (y%x!=0){
            //y>=x
            int temp=x;
            x=y%x;
            y=temp;
        }
        return Math.min(x, y);
    }

    public static void insertNode(ListNode node, int value){
        ListNode newNode;
        newNode=new ListNode(value);

        if(node.next==null){
            node.next=newNode;
            return;
        }
        ListNode oldNextNode=node.next;
        node.next=newNode;
        newNode.next=oldNextNode;
    }

    public static ListNode insertGreatestCommonDivisors(ListNode head) {
        ListNode node=head;
        HashMap<String, Integer> hashMapCommonDivVal=new HashMap<>();

        while (node!=null){
            ListNode nextNode=node.next;
            if(nextNode!=null){
                int commonDivVal;
                String key=node.val+" "+nextNode.val;

                if(hashMapCommonDivVal.containsKey(key)){
                    commonDivVal=hashMapCommonDivVal.get(key);
//                    System.out.printf("Hash %s", commonDivVal);
                }else{
                    commonDivVal=getGreatestCommonDivisor(node.val, nextNode.val);
                    hashMapCommonDivVal.put(key, commonDivVal);
                    String key1=nextNode.val+" "+node.val;
                    hashMapCommonDivVal.put(key1, commonDivVal);
                }
                insertNode(node, commonDivVal);
//                System.out.printf("%s %s %s\n", node.val, commonDivVal, nextNode.val);
            }
            node=nextNode;
        }
        return head;
    }

    public static void println(ListNode node){
        while (node!=null){
            System.out.printf("%s, ",node.val);
            node=node.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Requirement:
        //-
        //
        // Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //+ Constraints:
        //0 <= purchaseAmount <= 100
        //
        //- Brainstorm
        //-
//        System.out.println(getGreatestCommonDivisor(1,1));
//        System.out.println(getGreatestCommonDivisor(1,2));
//        System.out.println(getGreatestCommonDivisor(6,10));
//        System.out.println(getGreatestCommonDivisor(12,10));
//        System.out.println(getGreatestCommonDivisor(12,18));
//        System.out.println(getGreatestCommonDivisor(12,24));
//        int[] arr={18,6,10,3};
//        int[] arr={7};
//        int[] arr={};
        int[] arr={18,6,18,6};
        ListNode node=new ListNode(arr[0]);
        ListNode root=node;
        int n=arr.length;
        for(int i=1;i<n;i++){
            ListNode newNode=new ListNode(arr[i]);
            node.next=newNode;
            node=newNode;
        }
        insertGreatestCommonDivisors(root);
        println(root);
    }
}
