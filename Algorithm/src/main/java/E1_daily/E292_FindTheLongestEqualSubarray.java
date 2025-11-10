package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E292_FindTheLongestEqualSubarray {

    public static int longestEqualSubarray(List<Integer> nums, int k) {
        int n=nums.size();
        HashMap<Integer, List<Integer>> cacheIndices=new HashMap<>();
        int rs=1;

        for (int i = 0; i < n; i++) {
            List<Integer> curIndices=cacheIndices.getOrDefault(nums.get(i), new ArrayList<>());
            curIndices.add(i);
            int low = 0, high = curIndices.size() - 1;
            int maxBound = curIndices.size() - 1;
            int curRs=-1;
//            System.out.printf("Val: %s\n", nums.get(i));
//            System.out.println(curIndices);

            while (low<=high){
                    int mid=low+(high-low)/2;
                    //[1],[3],[7] (2,4,5,6)
                    //==> val = 7-1 - (3) = 4 (We need to remove 4 elements)
                    //4<=k ==> low=mid-1
                    //
                    int deletedNum=curIndices.get(maxBound)-curIndices.get(mid)-(maxBound-mid);
//                    System.out.printf("Mid: %s, high: %s, deleted number: %s\n", mid, high, deletedNum);
                    if(deletedNum<=k){
                        curRs=mid;
                        high=mid-1;
                    }else{
                        low=mid+1;
                    }
                }
            if(curRs!=-1){
//                    System.out.printf("Val:%s, index: %s, curRs: %s\n", nums.get(i), curRs, curIndices.size()-curRs);
                    //[0, 1, 4, 5]
                    rs=Math.max(curIndices.size()-curRs, rs);
                }
            cacheIndices.put(nums.get(i), curIndices);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums) and an integer k.
        //- A subarray is called equal if (all of its elements) are equal.
        //* Note that the empty subarray is an equal subarray.
        //* Return the length of the longest possible equal subarray after deleting at most k elements from nums.
        //- A subarray is a contiguous, possibly empty sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: nums = [1,3,2,3,1,3], k = 3
        //Output: 3
        //Explanation: It's optimal to delete the elements at index 2 and index 4.
        //After deleting them, nums becomes equal to [1, 3, 3, 3].
        //The longest equal subarray starts at i = 1 and ends at j = 3 with length equal to 3.
        //It can be proven that no longer equal subarrays can be created.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= nums.length
        //0 <= k <= nums.length
        //  + Length <= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //1.1, Case
        //
        //Input: nums = [1,3,2,3,1,3], k = 3
        //Output: 3
        //- Prefix sum (Count)
        //nums = [1,3,2,3,1,3], k = 3
        //prefix = [1,1,1,2,2,3], k = 3
        //count[1] = [0,4]
        //count[3] = [1,3,5]
        //count[2] = [2]
        //==> Binary search
//        Integer[] nums = {1,3,2,3,1,3};
//        int k = 3;
//        Integer[] nums = {1,1,2,2,1,1};
//        int k = 2;
        Integer[] nums = {3,1,5,3,1,1};
        int k = 0;
        System.out.println(longestEqualSubarray(Arrays.asList(nums), k));
        //
        //
        //1.2, Optimization
        //- For (each pair of points), classify them by (center) and (radius).
        //- We only need to record (one of the points P),
        //  since the other point is P' = 2 * center - P (using vector notation).
        //- For each (center) and (radius), look at (every possible rectangle) (two pairs of points P, P', Q, Q').
        //- The area of this rectangle dist(P, Q) * dist(P, Q') is a candidate answer.
        //
        //- We just need to care about (radius, center_point)
        //Ex:
        // A ---- center ---- B
        //  + From center, we can calculate (A and B)
        //- Cache Map(radius, Map<Center, List of points>>)
        //Map<Integer, Map<Point, List<Point>>> seen
        //- Loop all cache:
        //  + ==> For to find the result.
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n*log(n))
        //
        ///#Reference:
        //1499. Max Value of Equation - HARD
        //2747. Count Zero Request Servers
        //51. N-Queens
    }
}
