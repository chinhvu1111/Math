package contest;

import java.util.HashMap;

public class E247_CountSpecialSubsequences {

    public static long numberOfSubsequences(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        long rs =0l;

        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                int qSVal=nums[i]*nums[j];
                for (int k = i+2; k < j-1; k++) {
                    if(qSVal%nums[k]==0&&mapCount.containsKey(qSVal/nums[k])){
                        rs+=mapCount.get(qSVal/nums[k]);
                    }
                }
            }
            if(i>=1){
                mapCount.put(nums[i-1], mapCount.getOrDefault(nums[i-1], 0)+1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array nums consisting of (positive integers).
        //- (A special subsequence) is defined as a subsequence of length 4,
        // represented by indices (p, q, r, s), where p < q < r < s.
        // This subsequence must satisfy the following conditions:
        //  + nums[p] * nums[r] == nums[q] * nums[s]
        //  + There must be (at least one element) between (each pair of indices).
        //  In other words, q - p > 1, r - q > 1 and s - r > 1.
        //
        //- A subsequence is a sequence derived from the array (by deleting zero or more elements)
        // without changing the order of the remaining elements.
        //* Return (the number of different special subsequences) in nums.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //7 <= nums.length <= 1000
        //1 <= nums[i] <= 1000
        //  + Length<=1000 ==> Time: O(n^2)
        //
        //- Brainstorm
        //Ex:
        //Check subsequence (1, 3, 3, 1)
        //  + This is valid
        //
        //- a.......b
        //      c.......d
        //- Brute force:
        //  +
        //
        int[] nums = {3,4,3,4,3,4,3,4};
        System.out.println(numberOfSubsequences(nums));
    }
}
