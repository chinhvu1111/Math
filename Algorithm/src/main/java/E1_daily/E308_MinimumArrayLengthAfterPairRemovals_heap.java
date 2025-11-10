package E1_daily;

import java.util.*;

public class E308_MinimumArrayLengthAfterPairRemovals_heap {

    public static int minLengthAfterRemovals(List<Integer> nums) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=nums.size();

        for(int i=0;i<n;i++){
            mapCount.put(nums.get(i), mapCount.getOrDefault(nums.get(i), 0)+1);
        }
        PriorityQueue<int[]> maxHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1]-o1[1];
            }
        });
        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            maxHeap.add(new int[]{e.getKey(), e.getValue()});
        }
        while(maxHeap.size()>1){
            int[] maxCountElement = maxHeap.poll();
            if(maxHeap.isEmpty()){
                break;
            }
            int[] prevMaxElement = maxHeap.poll();
            maxCountElement[1]--;
            prevMaxElement[1]--;
            if(maxCountElement[1]!=0){
                maxHeap.add(maxCountElement);
            }
            if(prevMaxElement[1]!=0){
                maxHeap.add(prevMaxElement);
            }
        }
        if(maxHeap.isEmpty()){
            return 0;
        }
        return maxHeap.peek()[1];
    }

    public static int minLengthAfterRemovalsTTwoPointers(List<Integer> nums) {
        int len = nums.size();
        int ans = nums.size(), i = 0, j = (len + 1) / 2;

        while(i < len/ 2 && j < len) {
            if (nums.get(i) < nums.get(j)) {
                ans-=2;
            }
            i++;
            j++;
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array num sorted in (non-decreasing order).
        //- You can perform the following operation any number of times:
        //  + Choose two indices, i and j, where (nums[i] < nums[j]).
        //  + Then, remove the elements at indices (i and j) from nums.
        //- The remaining elements retain their original order, and the array is re-indexed.
        //* Return (the minimum length of nums) after applying (the operation zero or more times).
        //
        //Example 4:
        //
        //Input: nums = [2,3,4,4,4]
        //Output: 1
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //nums is sorted in non-decreasing order.
        //  + nums.length <= 10^5 ==> Time: O(n)
        //  + nums[i] <= 10^9 ==> Long
        //
        //* Brainstorm:
        //- 2 pointers
        //
        //Example 4:
        //Input: nums = [2,3,4,4,4]
        //Output: 1
        //=> count = [(2:1),(3:1),(4:3)]
        //
        //Ex:
        //Input: nums = [2,3,4,4,5,6,6,7]
        //=> sort by count (in desc)
        //
        //
        //1.1, Case
//        Integer[] nums = {2,3,4,4,4};
        Integer[] nums = {1,1,2,2,3,3};
        //(2-3),(2-3)
        //(1,1)
        //  ==> 3 parts if we try to match pair of count
        //  ==> Wrong
        //- Valid:
        //(1,2),(1,3),(2,3)
        //  ==> 0
        //(1,2,3)
        //
        //- Invalid:
        //(1,2),(1,2),(3,3)
        //  ==> 2
        //==> count--
        //  + Not count-=x because count-- < count
        //  ==> We need to reorder them
        //
        //* Main point:
        //- Minimize the value with (MAX count)
        //- {1,1,2,2,3,3};
        //  + 1-3
        //  ==> count[2]=1
        //  ==> count[3]=1
        //  ==> Need to minimize the count[1]
        //      ==> The heap need to reordered
        //
        //1.2, Optimization
        //- 2 pointers
        //- array num sorted in (non-decreasing order).
        //
        //Example 4:
        //Input: nums = [2,3,4,4,4]
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        System.out.println(minLengthAfterRemovals(Arrays.asList(nums)));
        System.out.println(minLengthAfterRemovalsTTwoPointers(Arrays.asList(nums)));
    }
}
