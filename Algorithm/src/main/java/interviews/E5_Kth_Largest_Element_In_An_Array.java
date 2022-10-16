package interviews;

import java.util.*;
import java.util.stream.Collectors;

public class E5_Kth_Largest_Element_In_An_Array {

    public static int findKthLargest1(int[] nums, int k) {
        List<Integer> numsList = Arrays.stream(nums).boxed().collect(Collectors.toList());
        Collections.sort(numsList);
        int temp=1_000_00;
        int rs=0;
        int count=0;

        for(int i= numsList.size()-1;i>=0;i--){
            if(numsList.get(i)<temp){
                temp=numsList.get(i);
                rs=temp;
            }
            count++;
            if(count==k){
                return rs;
            }
        }
        return rs;
    }

    static class Node{
        int value;
        Node next;

        public Node(int value) {
            this.value = value;
        }
    }

    public static int findKthLargest(int[] nums, int k) {
        Node root=null;
        int count=0;

        for(int i=0;i<nums.length;i++){
            Node node=new Node(nums[i]);

            if(root==null){
                root=node;
                count++;
            }else{
                Node tempNode=root;
                Node preNode=null;
                while (tempNode!=null&&tempNode.value>=nums[i]){
                    preNode=tempNode;
                    tempNode=tempNode.next;
                }
                if(preNode!=null){
                    node.next=preNode.next;
                    preNode.next=node;
                    count++;
                }else{
                    node.next=root;
                    root=node;
                    count++;
                }
//                if(tempNode!=null&&count>k){
//                    root=root.next;
//                }
                if(tempNode==null&&count<k){
                    preNode.next=node;
                    count++;
                }
            }
        }
        return getLast(root, k);
    }

    private static int getLast(Node root, int k){
        Node tempNode=root;
        int count=1;

        while (tempNode.next!=null&&count<k){
            tempNode=tempNode.next;
            count++;
        }
        return tempNode.value;
    }

    public int findKthLargest2(int[] nums, int k) {
        return 0;
    }

    public int KthLargest(int[] nums, int left, int right, int k) {
        if(left==right){
            return nums[left];
        }
        return 0;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{3,2,3,1,2,4,5,5,6};
        int k=4;
        System.out.println(findKthLargest1(arr, k));
        System.out.println(findKthLargest(arr, k));
    }
}
