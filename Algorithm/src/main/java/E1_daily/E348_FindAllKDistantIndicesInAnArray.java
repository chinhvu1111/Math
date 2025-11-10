package E1_daily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E348_FindAllKDistantIndicesInAnArray {

    public static List<Integer> findKDistantIndices(int[] nums, int key, int k) {
        int n=nums.length;
        List<Integer> indices=new ArrayList<>();

        for(int i=0;i<n;i++){
            if(nums[i]==key){
                indices.add(i);
            }
        }
        //<1---3>
        //  <2-----5?
        //==> max index
        List<Integer> rsSet=new ArrayList<>();
        int curIndex=0;

        for(int i=0;i<indices.size();i++){
            int min = Math.max(curIndex, indices.get(i)-k);
            int max = Math.max(curIndex, indices.get(i)+k);
            for (int j = min; j <=max&&j<n; j++) {
                rsSet.add(j);
            }
            curIndex=Math.max(curIndex+1, max+1);
        }
        return rsSet;
    }

    public static List<Integer> findKDistantIndicesRefactor(int[] nums, int key, int k) {
        int n=nums.length;
        List<Integer> indices=new ArrayList<>();
        //<1---3>
        //  <2-----5?
        //==> max index
        List<Integer> rsSet=new ArrayList<>();
        int curIndex=0;

        for(int i=0;i<n;i++){
            if(nums[i]!=key){
                continue;
            }
            int min = Math.max(curIndex, i-k);
            int max = Math.max(curIndex, i+k);
            for (int j = min; j <=max&&j<n; j++) {
                rsSet.add(j);
            }
            curIndex=Math.max(curIndex+1, max+1);
        }
        return rsSet;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed integer array nums and two integers (key and k).
        //- (A k-distant index) is (an index i) of nums for which
        // there exists (at least one index j) such that |i - j| <= k and (nums[j] == key).
        //* Return (a list of all k-distant indices) sorted in (increasing order).
        //
        //Example 1:
        //
        //Input: nums = [3,4,9,1,3,9,5], key = 9, k = 1
        //Output: [1,2,3,4,5,6]
        //Explanation: Here, nums[2] == key and nums[5] == key.
        //- For index 0, |0 - 2| > k and |0 - 5| > k, so there is no j where |0 - j| <= k and nums[j] == key.
        // Thus, 0 is not a k-distant index.
        //- For index 1, |1 - 2| <= k and nums[2] == key, so 1 is a k-distant index.
        //- For index 2, |2 - 2| <= k and nums[2] == key, so 2 is a k-distant index.
        //- For index 3, |3 - 2| <= k and nums[2] == key, so 3 is a k-distant index.
        //- For index 4, |4 - 5| <= k and nums[5] == key, so 4 is a k-distant index.
        //- For index 5, |5 - 5| <= k and nums[5] == key, so 5 is a k-distant index.
        //- For index 6, |6 - 5| <= k and nums[5] == key, so 6 is a k-distant index.
        //Thus, we return [1,2,3,4,5,6] which is sorted in increasing order.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 1000
        //1 <= nums[i] <= 1000
        //key is an integer from the array nums.
        //1 <= k <= nums.length
        //  + Time: O(n^2)
        //
        //* Brainstorm:
        //- |x-y|<=k
        //   ==> x = [y-k,y+k]
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
        //# Reference:
        //243. Shortest Word Distance
        //2817. Minimum Absolute Difference Between Elements With Constraint - MED
        //
//        int[] nums = {3,4,9,1,3,9,5};
//        int key = 9, k = 1;
        int[] nums = {2,2,2,2,2};
        int key = 2, k = 2;
        System.out.println(findKDistantIndices(nums, key, k));
        System.out.println(findKDistantIndicesRefactor(nums, key, k));
    }
}
