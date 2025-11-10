package E1_daily;

import java.util.HashMap;

public class E266_SumOfDistances {

    public static long[] distance(int[] nums) {
        int n=nums.length;
        HashMap<Integer, Long> prefixSum=new HashMap<>();
        HashMap<Integer, Long> prefixCount=new HashMap<>();
        HashMap<Integer, Long> prefixTotalSum=new HashMap<>();
        HashMap<Integer, Long> prefixTotalCount=new HashMap<>();

        for(int i=0;i<n;i++){
            prefixTotalSum.put(nums[i], prefixTotalSum.getOrDefault(nums[i], 0L)+i);
            prefixTotalCount.put(nums[i], prefixTotalCount.getOrDefault(nums[i], 0L)+1);
        }
        System.out.println(prefixTotalCount);
        long[] rs=new long[n];
        for (int i = 0; i < n; i++) {
            long totalCount = prefixTotalCount.get(nums[i]);
            long totalSum = prefixTotalSum.get(nums[i]);
            long leftCount = prefixCount.getOrDefault(nums[i], 0L);
            long leftSum = prefixSum.getOrDefault(nums[i], 0L);
            long rightCount = totalCount-1-leftCount;
            long rightIndicesSum=0;
            if(rightCount>0){
                //mid
                //(mid*k-left*k)+(right*k-mid*k)
                rightIndicesSum=totalSum-leftSum-i;
            }
//            System.out.println(prefixCount);
//            System.out.printf("Left count: %s, right count: %s, rightIndicesSum: %s, totalSum: %s, leftSum: %s, totalCount: %s\n",
//                    leftCount, rightCount, rightIndicesSum, totalSum, leftSum, totalCount);
            rs[i]=(leftCount*i-leftSum)+(rightIndicesSum-rightCount*i);
//            prefixTotalCount.put(nums[i], prefixTotalCount.getOrDefault(nums[i], 0L)+1);
            prefixSum.put(nums[i], prefixSum.getOrDefault(nums[i], 0L)+i);
            prefixCount.put(nums[i], prefixCount.getOrDefault(nums[i], 0L)+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums).
        //- There exists (an array arr) of length nums.length,
        // where arr[i] is the sum of |i - j|
        //  + over (all j) such that (nums[j] == nums[i] and j != i).
        //- If there is no such j, set arr[i] to be 0.
        //
        //Example 1:
        //
        //Input: nums = [1,3,1,1,2]
        //Output: [5,0,3,4,0]
        //Explanation:
        //When i = 0, nums[0] == nums[2] and nums[0] == nums[3]. Therefore, arr[0] = |0 - 2| + |0 - 3| = 5.
        //When i = 1, arr[1] = 0 because there is no other index with value 3.
        //When i = 2, nums[2] == nums[0] and nums[2] == nums[3]. Therefore, arr[2] = |2 - 0| + |2 - 3| = 3.
        //When i = 3, nums[3] == nums[0] and nums[3] == nums[2]. Therefore, arr[3] = |3 - 0| + |3 - 2| = 4.
        //When i = 4, arr[4] = 0 because there is no other index with value 2.
        //
        //Return the array arr.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^9
        //  + length<=10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //Ex:
        //Input: nums = [1,3,1,1,2]
        //Output: [5,0,3,4,0]
        //[a,(b),c]
        //|b-a|+|c-b| = index(b)-index(a)+index(c)-index(b)
        //  + Use prefix sum for (left/right) of (index=i)
        //- prefixSum[i] = sum(index in the left) at (index=i)
        //- suffixSum[i] = sum(index in the right) at (index=i)
        //  ==> We can use (prefix sum) + (total_prefix_sum) to remove (suffix_sum)
        //
        //1.1, Special cases
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        int[] nums = {1,3,1,1,2};
        long[] rs=distance(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
