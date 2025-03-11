package E1_daily;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E255_MaxSumOfAPairWithEqualSumOfDigits {

    public static int maximumSum(int[] nums) {
        int n=nums.length;
        HashMap<Integer, PriorityQueue<Integer>> sumToNum=new HashMap<>();
        int rs=Integer.MIN_VALUE;

        for(int i=0;i<n;i++){
            int num=nums[i];
            int sumDigit=0;
            while(num!=0){
                sumDigit+=num%10;
                num=num/10;
            }
            PriorityQueue<Integer> curNum=sumToNum.getOrDefault(sumDigit, new PriorityQueue<>());
            curNum.add(nums[i]);
            while(curNum.size()>2){
                curNum.poll();
            }
            sumToNum.put(sumDigit, curNum);
        }
//        System.out.println(sumToNum);
        for(Map.Entry<Integer, PriorityQueue<Integer>> e: sumToNum.entrySet()){
            if(e.getValue().size()>=2){
                PriorityQueue<Integer> curQueue = e.getValue();
                rs=Math.max(rs, curQueue.poll()+curQueue.poll());
            }
        }
        return rs==Integer.MIN_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed array nums) consisting of positive integers.
        //- You can choose two indices (i and j), such that (i != j),
        //  and (the sum of digits) of the number nums[i] is equal to that of nums[j].
        //* Return the (maximum value) of (nums[i] + nums[j])
        //  that you can obtain over (all possible indices i and j) that satisfy the conditions.
        //
        //Example 1:
        //
        //Input: nums = [18,43,36,13,7]
        //Output: 54
        //Explanation: The pairs (i, j) that satisfy the conditions are:
        //- (0, 2), both numbers have a sum of digits equal to 9, and their sum is 18 + 36 = 54.
        //- (1, 4), both numbers have a sum of digits equal to 7, and their sum is 43 + 7 = 50.
        //So the maximum sum that we can obtain is 54.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //  + length<=10^5 ==> Time: O(n)
        //
        //* Brainstorm:
        //- HashMap
        //  + <Sum, PriorityQueue>
        //
        //1.1, Special cases
        //
        //1.2, Optimization
        //- PriorityQueue ==> Only save (2 elements)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
//        int[] nums = {18,43,36,13,7};
        int[] nums = {229,398,269,317,420,464,491,218,439,153,482,169,411,93,147,50,347,210,251,366,401};
        System.out.println(maximumSum(nums));
        //
        //#Reference:
        //2353. Design a Food Rating System
        //1929. Concatenation of Array
        //3247. Number of Subsequences with Odd Sum
        //3409. Longest Subsequence With Decreasing Adjacent Difference
        //2868. The Wording Game => hard
        //1943. Describe the Painting
    }
}
