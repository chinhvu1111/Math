package E1_daily;

import java.util.Deque;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class E253_MakeThePrefixSumNonNegative {

    public static int makePrefSumNonNegative(int[] nums) {
        int n=nums.length;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        Deque<Integer> listNums=new LinkedList<>();
        long sum=0;

        for(int i=0;i<n;i++){
            listNums.add(nums[i]);
        }
        int rs=0;

        while(!listNums.isEmpty()){
            int curVal = listNums.removeFirst();
            sum+=curVal;
            minHeap.add(curVal);
            while (sum<0&&!minHeap.isEmpty()){
                int removedElement = minHeap.poll();
                sum-=removedElement;
                listNums.addLast(removedElement);
                rs++;
            }
        }
        return rs;
    }

    public static int makePrefSumNonNegativeRefer(int[] nums) {
        int n=nums.length;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();
        long sum=0;
        int rs=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            if(nums[i]<0){
                minHeap.add(nums[i]);
            }
            while (sum<0&&!minHeap.isEmpty()){
                int removedElement = minHeap.poll();
                sum-=removedElement;
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums).
        //- You can apply the following operation any number of times:
        //  + Pick (any element) from nums and put it at (the end of nums).
        //- (The prefix sum array of nums) is (an array prefix) of (the same length) as nums such that:
        //  + prefix[i] is (the sum of all the integers nums[j]) where (j) is in the inclusive range [0, i].
        //* Return (the minimum number of operations) such that the prefix sum array does (not contain) (negative integers).
        //- The test cases are generated such that it is always possible to make the prefix sum array non-negative.
        //
        //Example 2:
        //
        //Input: nums = [3,-5,-2,6]
        //Output: 1
        //Explanation: we can do one operation on index 1.
        //The array after the operation is [3,-2,6,-5]. The prefix sum array is [3, 1, 7, 2].
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //-10^9 <= nums[i] <= 10^9
        //  + length<=10^5 => Time: O(log(n))
        //  + nums[i]<=10^9 => long
        //
        //* Brainstorm:
        //- If we got any negative value:
        //  + We will put the min negative values from the MIN HEAP until:
        //      + We have (the positive value)
        //
        //1.1, Special cases
        //
        //1.2, Optimization (Priority queue)
        //- We don't need to use dequeue
        //  + Because we only need to move the (negative value) from (left to last)
        //  ==> The test case generated such that we always have (the negative value) to (remove)
        //  ==> So we don't need to care about (the order) of (negative values) (at the last) of the array
        //  ==> We just (poll them) so we don't need to (add them) to (the last of the array).
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //#Reference:
        //1642. Furthest Building You Can Reach
        int[] nums = {3,-5,-2,6};
        System.out.println(makePrefSumNonNegative(nums));
        System.out.println(makePrefSumNonNegativeRefer(nums));
    }
}
