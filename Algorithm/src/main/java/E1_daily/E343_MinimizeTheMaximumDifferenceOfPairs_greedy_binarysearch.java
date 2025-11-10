package E1_daily;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class E343_MinimizeTheMaximumDifferenceOfPairs_greedy_binarysearch {

    public static int minimizeMaxWrong(int[] nums, int p) {
        if(p==0){
            return 0;
        }
        Arrays.sort(nums);
        PriorityQueue<Integer> queue=new PriorityQueue<>(Collections.reverseOrder());
        int n=nums.length;

        for(int i=0;i<n-1;i+=2){
            queue.add(nums[i+1]-nums[i]);
            while (queue.size()>p){
                queue.poll();
            }
        }
        int first=queue.peek();
        queue.clear();
        for(int i=1;i<n-1;i+=2){
            queue.add(nums[i+1]-nums[i]);
            while (queue.size()>p){
                queue.poll();
            }
        }
        int second=Integer.MAX_VALUE;
        if(!queue.isEmpty()){
            second=queue.peek();
        }
//        System.out.println(queue);
        return Math.min(first, second);
    }

    public static int minimizeMax(int[] nums, int p) {
        if(p==0){
            return 0;
        }
        Arrays.sort(nums);
        int n=nums.length;
        int max=0;

        for (int i = 0; i < n; i++) {
            max=Math.max(nums[i], max);
        }
        int low=0, high=max;
        int rs=-1;

        while(low<=high){
            int med=low+(high-low)/2;
            int count=0;
            for(int i=0;i<n-1;i++){
                if(med>=nums[i+1]-nums[i]){
                    count++;
                    if(count==p){
                        break;
                    }
                    i++;
                }
            }
            if(count>=p){
                rs=med;
                high=med-1;
            }else{
                low=med+1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums) and (an integer p).
        //- Find (p pairs of indices of nums) such that the (maximum difference) amongst (all the pairs) is minimized.
        //- Also, ensure (no index) appears (more than once) amongst (the p pairs).
        //  + Không có (index xuất hiện hơn 1 lần)
        //- Note that for a pair of elements (at the index i and j),
        // the difference of this pair is |nums[i] - nums[j]|,
        // where |x| represents the absolute value of x.
        //* Return (the minimum maximum difference) among (all p pairs).
        //- We define (the maximum of an empty set) to be zero.
        //
        //Example 1:
        //
        //Input: nums = [10,1,2,7,1,3], p = 2
        //Output: 1
        //Explanation: The first pair is formed from the indices 1 and 4,
        // and the second pair is formed from the indices 2 and 5.
        //The maximum difference is max(|nums[1] - nums[4]|, |nums[2] - nums[5]|) = max(0, 1) = 1.
        //- Therefore, we return 1.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^9
        //0 <= p <= (nums.length)/2
        //  + Time: O(n*k)
        //  + nums[i] <= 10^9 ==> Binary search???
        //
        //* Brainstorm:
        //- Sort(nums)
        //- Input: nums = [10,1,2,7,1,3], p = 2
        //  + Sort(nums) = [1,1,2,3,7,10]
        //  + ranges = [0,1,1,4,3]
        //  ==> Use the priorityQueue
        //
        //
        //1.1, Case
        //
        //int[] nums = {4,2,1,2};
        //1,(2,2),4
//        int p = 1;
//        int[] nums = {3,4,2,3,2,1,2};
//        int p = 3;
        //[1,2,2,2,3,3,4]
        //- Choose:
        //  + [(1,2),2,2,3,3,4]
        //  + [1,(2,2),2,3,3,4]
        //[(1,2),(2,2),(3,3),4]
        //[1,(2,2),(2,3),(3,4)]

        //[(1,2),(2,2),(3,3),7] ==> Valid
        //[1,(2,2),(2,3),(3,7)]
        //
        //- How about:
        //sort = [(0,0),(0,1),3,(5,6)]
        int[] nums = {3,0,5,0,0,1,6};
        int p = 3;
        //sort = [0,0,0,1,3,5,6]
        //sort = [(0,0),(0,1),3,(5,6)]
        //Expected: 1
        //
        //- a,b,c,d
        //- If we choose (a,b)
        //  ==> We can not choose (b,c)
        //  ==> We can choose (c,d)
        //
        //- Can we use binary search?
        //sort = [(0,0),(0,1),3,(5,6)]
        //  + If we choose [(0,0),(0,1),(3,5),6]
        //      + It will be invalid (5-3)>1
        //  + a,b,c,d
        //      + if (a,b) is not valid
        //          + That is mean b is (bigger than expected)
        //      ==> we check (b,c)
        //      ==> Greedy choose the pair as soon as possible
        //- binary search + greedy
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(1)
        //
        System.out.println(minimizeMax(nums, p));
        //#Reference:
    }
}
