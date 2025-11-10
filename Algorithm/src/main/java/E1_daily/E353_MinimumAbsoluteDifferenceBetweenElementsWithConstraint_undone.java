package E1_daily;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class E353_MinimumAbsoluteDifferenceBetweenElementsWithConstraint_undone {

    public static int minAbsoluteDifference(List<Integer> nums, int x) {
        int n=nums.size();
        int rs=Integer.MAX_VALUE;
        TreeSet<Integer> treeSet=new TreeSet<>();

        for (int i = 0; i < n; i++) {
            if(i>=x){
                treeSet.add(nums.get(i-x));
                Integer smallerElement = treeSet.floor(nums.get(i));
                if(smallerElement!=null){
                    rs=Math.min(rs, nums.get(i)-smallerElement);
                }
                Integer greaterElement = treeSet.ceiling(nums.get(i));
                if(greaterElement!=null){
                    rs=Math.min(rs, greaterElement-nums.get(i));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums) and (an integer x).
        //- Find (the minimum absolute difference) between two elements in the array that are (at least x) indices apart.
        //- In other words, find two indices i and j such that abs(i - j) >= x and abs(nums[i] - nums[j]) is minimized.
        //* Return an integer denoting the minimum absolute difference
        // between two elements that are at least x indices apart.
        //
        //Example 1:
        //
        //Input: nums = [4,3,2,4], x = 2
        //Output: 0
        //Explanation: We can select nums[0] = 4 and nums[3] = 4.
        //They are at least 2 indices apart, and their absolute difference is the minimum, 0.
        //It can be shown that 0 is the optimal answer.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //0 <= x < nums.length
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //#Reference:
        //532. K-diff Pairs in an Array
        //2903. Find Indices With Index and Value Difference I
        //2905. Find Indices With Index and Value Difference II
        Integer[] nums = {4,3,2,4};
        int x = 2;
        System.out.println(minAbsoluteDifference(Arrays.asList(nums), x));
    }
}
