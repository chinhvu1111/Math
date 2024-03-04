package contest;

import java.util.Arrays;

public class E44_MinimumOperationsToExceedThresholdValueI {

    public static int minOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length;
        int i;

        for(i=0;i<n;i++){
            if(nums[i]>=k){
                break;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 0-indexed integer array nums, and an integer k.
        //In one operation, you can (remove) one occurrence of (the smallest element of nums).
        //* Return the minimum number of operations needed so that all elements of the array are greater than or equal to k.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= nums.length <= 50
        //1 <= nums[i] <= 109
        //1 <= k <= 10^9
        //
        //- Brainstorm
        //
        int[] arr={1,1,2,4,9};
        int k=1;
//        int[] arr={1,1,2,4,9};
//        int k=9;
        System.out.println(minOperations(arr, k));
    }
}
