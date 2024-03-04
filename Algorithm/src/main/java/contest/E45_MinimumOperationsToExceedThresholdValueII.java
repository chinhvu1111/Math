package contest;

import java.util.PriorityQueue;

public class E45_MinimumOperationsToExceedThresholdValueII{

    public static int minOperations(int[] nums, int k) {
        PriorityQueue<Long> queue=new PriorityQueue<>();
        for(long e: nums){
            queue.add(e);
        }
//        System.out.println(queue);
        int rs=0;
        //1,2,3,4, k = 2
        while(queue.size()>=2&&queue.peek()<k){
            long first=queue.poll();
            long second=queue.poll();
            long newElement=Math.min(first, second)* 2L + Math.max(first, second);
            queue.add(newElement);
            System.out.println(queue);
            rs++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 0-indexed integer array nums, and an integer k.
        //In one operation, you will:
        //- Take the two smallest integers x and y in nums.
        //- Remove x and y from nums.
        //- Add min(x, y) * 2 + max(x, y) anywhere in the array.
        //* Note that you can only apply the described operation if nums contains at least two elements.
        //
        //Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //2 <= nums.length <= 2 * 105
        //1 <= nums[i] <= 109
        //1 <= k <= 109
        //The input is generated such that an answer always exists. That is, there exists some sequence of operations after which all elements of the array are greater than or equal to k.
        //
        //- Brainstorm
        //
        //
//        int[] arr={2,11,10,1,3};
//        int k=10;
//        int[] arr={1,1,2,4,9};
//        int k=20;
//        int[] arr={1,1,2,4,9};
//        int k=20;
        int[] arr={1000000000,999999999,1000000000,999999999,1000000000,999999999};
        int k=1000000000;
        System.out.println(minOperations(arr, k));
    }
}
