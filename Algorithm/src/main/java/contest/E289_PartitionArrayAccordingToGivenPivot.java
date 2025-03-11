package contest;

import java.util.ArrayList;
import java.util.List;

public class E289_PartitionArrayAccordingToGivenPivot {

    public static int[] pivotArray(int[] nums, int pivot) {
        int n=nums.length;
        int[] rs=new int[n];
        int index=0;

        for (int i = 0; i < n; i++) {
            if(nums[i]<pivot){
                rs[index++] = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if(nums[i]==pivot){
                rs[index++] = nums[i];
            }
        }
        for (int i = 0; i < n; i++) {
            if(nums[i]>pivot){
                rs[index++] = nums[i];
            }
        }

        return rs;
    }

    public static int[] pivotArrayRefer(int[] nums, int pivot) {
        int[] ans = new int[nums.length];
        int lessI = 0;
        int greaterI = nums.length - 1;
        for (int i = 0, j = nums.length - 1; i < nums.length; i++, j--) {
            if (nums[i] < pivot) {
                ans[lessI] = nums[i];
                lessI++;
            }
            if (nums[j] > pivot) {
                ans[greaterI] = nums[j];
                greaterI--;
            }
        }
        while (lessI <= greaterI) {
            ans[lessI] = pivot;
            lessI++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums and (an integer pivot).
        //- (Rearrange nums) such that the following conditions are satisfied:
        //  + Every element (less than pivot) appears (before) every element greater than pivot.
        //  + Every element (equal to pivot) appears in between the elements less than and greater than pivot.
        //  + The relative order of the elements less than pivot and the elements greater than pivot is maintained.
        //- More formally, consider every (pi, pj)
        //  + where pi is the new position of (the ith element) and pj is the new position of (the jth element).
        //- If i < j and both elements are smaller (or larger) than pivot, then pi < pj.
        //* Return nums after (the rearrangement).
        //
        //Example 1:
        //
        //Input: nums = [9,12,5,10,14,3,10], pivot = 10
        //Output: [9,5,3,10,10,12,14]
        //Explanation:
        //The elements 9, 5, and 3 are less than the pivot so they are on the left side of the array.
        //The elements 12 and 14 are greater than the pivot so they are on the right side of the array.
        //The relative ordering of the elements less than and greater than pivot is also maintained.
        // [9, 5, 3] and [12, 14] are the respective orderings.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //- Brainstorm
        //- Use 2 pointers from
        //  + left to right i++
        //  + right to left new_i--
        //
        //1.1, Cases
        //
        //1.2, Optimization
        //1,3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        int[] nums = {9,12,5,10,14,3,10};
        int pivot = 10;
        int[] rs=pivotArrayRefer(nums, pivot);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
    }
}
