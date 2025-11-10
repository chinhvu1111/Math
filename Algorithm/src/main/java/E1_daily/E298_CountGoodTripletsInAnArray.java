package E1_daily;

import java.util.Arrays;
import java.util.HashMap;

public class E298_CountGoodTripletsInAnArray {

    public static long goodTriplets(int[] nums1, int[] nums2) {
        int n=nums1.length;
        HashMap<Integer, Integer> valToPos1=new HashMap<>();
        HashMap<Integer, Integer> valToPos2=new HashMap<>();

        for (int i = 0; i < n; i++) {
            valToPos1.put(nums1[i], i);
            valToPos2.put(nums2[i], i);
        }
        Arrays.sort(nums1);

        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two 0-indexed arrays nums1 and nums2 of length n,
        //  both of which are permutations of [0, 1, ..., n - 1].
        //- (A good triplet) is a set of (3 distinct values) which are present in (increasing order) by position both in nums1 and nums2.
        //- In other words, if we consider
        //  + (pos1-v) as (the index) of the value v in nums1
        //  + (pos2-v) as (the index) of the value v in nums2,
        // then a good triplet will be a set (x, y, z) where 0 <= x, y, z <= n - 1,
        // such that (pos1x < pos1y < pos1z) and (pos2x < pos2y < pos2z).
        //* Return (the total number of good triplets).
        //
        //Example 1:
        //
        //Input: nums1 = [2,0,1,3], nums2 = [0,1,2,3]
        //Output: 1
        //Explanation:
        //There are 4 triplets (x,y,z) such that pos1x < pos1y < pos1z.
        //  They are (2,0,1), (2,0,3), (2,1,3), and (0,1,3).
        //Out of those triplets, only the triplet (0,1,3) satisfies pos2x < pos2y < pos2z. Hence, there is only 1 good triplet.
        //- (0,1,3)
        //  + (pos1-x, pos1-y, pos1-y) = [2,0,3]
        //  + (pos2-x, pos2-y, pos2-y) = [0,1,3]
        //
        //Let's compute these:
        //
        //nums1 = [2, 0, 1, 3]
        //→ pos1 = {2: 0, 0: 1, 1: 2, 3: 3}
        //
        //nums2 = [0, 1, 2, 3]
        //→ pos2 = {0: 0, 1: 1, 2: 2, 3: 3}
        //
        //Triplet: (0, 1, 3)
        //pos1: 0 → 1, 1 → 2, 3 → 3 ✅
        //pos2: 0 → 0, 1 → 1, 3 → 3 ✅
        //
        //✅ Good triplet!
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums1.length == nums2.length
        //3 <= n <= 10^5
        //0 <= nums1[i], nums2[i] <= n - 1
        //nums1 and nums2 are permutations of [0, 1, ..., n - 1].
        //  + Time: O(n*k)
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
        //
    }
}
