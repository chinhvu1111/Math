package contest;

import java.util.Arrays;

public class E258_MinimumCostToMakeArraysIdentical {

    public static long minCost(int[] arr, int[] brr, long k) {
        int n=arr.length;
        long firstRs=0;
        for(int i=0;i<n;i++){
            firstRs+=Math.abs(arr[i]-brr[i]);
        }
        long secondRs=k;
        Arrays.sort(arr);
        Arrays.sort(brr);
        for(int i=0;i<n;i++){
            secondRs+=Math.abs(arr[i]-brr[i]);
        }
        return Math.min(secondRs, firstRs);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integer arrays arr and brr of length n, and an integer k.
        //- You can perform the following operations on arr (any number of times):
        //  + Split arr into (any) number of (contiguous subarrays) and (rearrange these subarrays) in any order.
        //      + This operation has (a fixed cost of k).
        //  + Choose (any element) in arr and (add or subtract) (a positive integer x) to it.
        //      + The cost of this operation is (x).
        //* Return (the minimum total cost) to make (arr equal to brr).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: arr = [-7,9,5], brr = [7,-2,-5], k = 2
        //Output: 13
        //Explanation:
        //
        //Split arr into two contiguous subarrays: [-7] and [9, 5] and rearrange them as [9, 5, -7], with a cost of 2.
        //Subtract 2 from element arr[0]. The array becomes [7, 5, -7]. The cost of this operation is 2.
        //Subtract 7 from element arr[1]. The array becomes [7, -2, -7]. The cost of this operation is 7.
        //Add 2 to element arr[2]. The array becomes [7, -2, -5]. The cost of this operation is 2.
        //The total cost to make the arrays equal is 2 + 2 + 7 + 2 = 13.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= arr.length == brr.length <= 10^5
        //0 <= k <= 2 * 10^10
        //-10^5 <= arr[i] <= 10^5
        //-10^5 <= brr[i] <= 10^5
        //  + length <= 10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- Find min cost
        //  + Binary search
        //- Split the array into multiple subarrays
        //  + With (array size = 1) ==> We will have (the expected order)
        //Example 1:
        //Input: arr = [-7,9,5], brr = [7,-2,-5], k = 2
        //==> Sort arr and brr
        //arr = [-7,9,5]
        //brr = [7,-2,-5]
        //
//        int[] arr = {-7,9,5}, brr = {7,-2,-5};
//        int k = 2;
        int[] arr = {3}, brr = {5};
        int k = 2;
        long x= (long) Math.pow(10, 10);
        System.out.println(x);
        System.out.println(minCost(arr, brr, k));
    }
}
