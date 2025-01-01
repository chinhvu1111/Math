package E1_weekly;

import java.util.Stack;

public class E22_MaximalRangeThatEachElementIsMaximumInIt {

    public static int[] maximumLengthOfRanges(int[] nums) {
        int n=nums.length;
        int[] countLeft=new int[n];
        int[] countRight=new int[n];
        Stack<Integer> indicesLeft=new Stack<>();
        Stack<Integer> indicesRight=new Stack<>();

        for(int i=0;i<n;i++){
            countLeft[i]=1;
            while(!indicesLeft.isEmpty()&&nums[i]>=nums[indicesLeft.peek()]){
                countLeft[i]+=countLeft[indicesLeft.pop()];
            }
            indicesLeft.add(i);
        }
        for(int i=n-1;i>=0;i--){
            countRight[i]=1;
            while(!indicesRight.isEmpty()&&nums[i]>=nums[indicesRight.peek()]){
                countRight[i]+=countRight[indicesRight.pop()];
            }
            indicesRight.add(i);
        }
        int[] rs=new int[n];

        for (int i = 0; i < n; i++) {
            rs[i]=countLeft[i]+countRight[i]-1;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed array nums of (distinct integers).
        //- Let us define (a 0-indexed array ans) of (the same length as nums) in the following way:
        //  + ans[i] is (the maximum length of a subarray nums[l..r]),
        //  such that (the maximum element) in that subarray is equal to (nums[i]).
        //* Return the array ans.
        //- Note that a subarray is (a contiguous part of the array).
        //
        //Example 1:
        //
        //Input: nums = [1,5,4,3,6]
        //Output: [1,4,2,1,5]
        //Explanation:
        //For nums[0] the longest subarray in which 1 is the maximum is nums[0..0] so ans[0] = 1.
        //For nums[1] the longest subarray in which 5 is the maximum is nums[0..3] so ans[1] = 4.
        //For nums[2] the longest subarray in which 4 is the maximum is nums[2..3] so ans[2] = 2.
        //For nums[3] the longest subarray in which 3 is the maximum is nums[3..3] so ans[3] = 1.
        //For nums[4] the longest subarray in which 6 is the maximum is nums[0..4] so ans[4] = 5.
        //
        //** Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^5
        //All elements in nums are (distinct).
        //  + length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //Ex:
        //nums =        [1,5,4,3,6]
        //countLeft=    [1,2,1,1,5]
        //  + Motonoic queue
        //+ init count[i]=1
        //+ while nums[i]>stack.peek():
        //      count[i]+=count[stack.pop()]
        //      stack.pop()
        //
        int[] nums = {1,5,4,3,6};
        int[] rs=maximumLengthOfRanges(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
    }
}
