package E1_daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class E349_FindSubsequenceOfLengthKWithTheLargestSum {

    public static int[] maxSubsequence(int[] nums, int k) {
        int n=nums.length;
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });

        for (int i = 0; i < n; i++) {
            queue.add(new int[]{nums[i], i});
            if(queue.size()>k){
                queue.poll();
            }
        }
        int[] indices=new int[n];
        Arrays.fill(indices, Integer.MIN_VALUE);

        while (!queue.isEmpty()){
            int[] e = queue.poll();
            indices[e[1]]=e[0];
        }
        int[] rs=new int[k];
        int index=0;
        for (int i = 0; i < n; i++) {
            if(indices[i]!=Integer.MIN_VALUE){
                rs[index++]=indices[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums and an integer k.
        //- You want to find (a subsequence of nums) of (length k) that has (the largest sum).
        //* Return (any such subsequence) as an integer array of length k.
        //- A subsequence is an array that can be derived from another array by
        // deleting some or no elements without changing the order of the remaining elements.
        //
        //Ex:
        //Input: nums = [2,1,3,3], k = 2
        //Output: [3,3]
        //Explanation:
        //The subsequence has the largest sum of 3 + 3 = 6.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //
        //
        //* Brainstorm:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Use only one for loop
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(1)
        //
        int[] nums = {-1,-2,3,4};
        int k=3;
        int[] rs = maxSubsequence(nums, k);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        System.out.println();
        //#Reference:
        //2163. Minimum Difference in Sums After Removal of Elements
    }
}
