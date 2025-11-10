package E1_daily;

import java.util.Arrays;

public class E278_PartitionArrayIntoDisjointIntervals {

    public static int partitionDisjoint(int[] nums) {
        int n=nums.length;
        int[] maxPrefix=new int[n];
        int[] minSuffix=new int[n];
        Arrays.fill(minSuffix, Integer.MAX_VALUE);

        for (int i = 0; i < n; i++) {
            int beforeLeft = i==0?0: maxPrefix[i-1];
            int beforeRight = n-i-1==n-1?Integer.MAX_VALUE: minSuffix[n-i];
            maxPrefix[i]=Math.max(beforeLeft, nums[i]);
            minSuffix[n-i-1]=Math.min(beforeRight, nums[n-i-1]);
        }
//        for (int i = 0; i < n; i++) {
////            System.out.printf("%s, ", maxPrefix[i]);
//            System.out.printf("%s, ", minSuffix[i]);
//        }
//        System.out.println();
        for (int i = 0; i < n; i++) {
//            if(minSuffix[i]==Integer.MAX_VALUE){
//                continue;
//            }
            if(i+1<n&&maxPrefix[i]<=minSuffix[i+1]){
                return i+1;
            }
        }
        return 1;
    }

    public static int partitionDisjointRefer(int[] nums) {
        int currMax = nums[0];
        int possibleMax = nums[0];
        int length = 1;

        for (int i = 1; i < nums.length; ++i) {
            if (nums[i] < currMax) {
                length = i + 1;
                currMax = possibleMax;
            } else {
                possibleMax = Math.max(possibleMax, nums[i]);
            }
        }

        return length;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array nums, partition it into two (contiguous) subarrays left and right so that:
        //  + Every element in left is less than or equal to every element in right.
        //  + left and right are (non-empty).
        //  + left has the (("smallest") possible size).
        //* Return (the length of left) after such (a partitioning).
        //- Test cases are generated such that partitioning exists.
        //
        //Example 2:
        //
        //Input: nums = [1,1,1,0,6,12]
        //Output: 4
        //Explanation: left = [1,1,1,0], right = [6,12]
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^6
        //There is at least one valid answer for the given input.
        //  + Length<=10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
//        int[] nums={5,0,3,8,6};
        int[] nums={1,1,1,0,6,12};
        System.out.println(partitionDisjoint(nums));
        System.out.println(partitionDisjointRefer(nums));
    }
}
