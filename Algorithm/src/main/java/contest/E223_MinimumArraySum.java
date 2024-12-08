package contest;

import java.util.Arrays;
import java.util.HashMap;

public class E223_MinimumArraySum {

    public static HashMap<String, Integer> memo;

    public static int solution(int index, int[] nums, int k, int op1, int op2, int[] prefixSum){
        if(index<0){
            return 0;
        }
        int curRs=Integer.MAX_VALUE;
        String curKey = index+"_"+op1+"_"+op2;
        int curMinElement;
        if(memo.containsKey(curKey)){
            return memo.get(curKey);
        }
        if(nums[index]>=k){
            if(op2>=1){
                curRs=Math.min(curRs, solution(index-1, nums, k, op1, op2-1, prefixSum)+nums[index]-k);
            }
            if(op1>=1&&op2>=1){
                curMinElement = (int) Math.ceil(((double) (nums[index] - k) / 2));
                if(Math.ceil((double) nums[index] / 2)>=k){
                    curMinElement= (int) Math.min(curMinElement, Math.ceil((double) nums[index] / 2) - k);
                }
                curRs=Math.min(curRs, solution(index-1, nums, k, op1-1, op2-1, prefixSum)+curMinElement);
            }
            if(op1>=1){
                curRs=Math.min(curRs, solution(index-1, nums, k, op1-1, op2, prefixSum)+(int) Math.ceil(((double) nums[index]/ 2)));
            }
        }else{
            if(op1>=1){
                curRs=Math.min(curRs, solution(index-1, nums, k, op1-1, op2, prefixSum)+(int)Math.ceil((double) nums[index]/2));
            }
        }
        curRs=Math.min(curRs, solution(index-1, nums, k, op1, op2, prefixSum)+nums[index]);
        if(curRs==Integer.MAX_VALUE){
            curRs= prefixSum[index];
        }
//        System.out.printf("Index: %s, rs: %s, key: %s\n", index, curRs, curKey);
        memo.put(curKey, curRs);
        return curRs;
    }

    public static int minArraySum(int[] nums, int k, int op1, int op2) {
        memo=new HashMap<>();
        Arrays.sort(nums);
        int n= nums.length;
        int[] prefixSum=new int[n];
        int sum=0;
        for(int i=n-1;i>=0;i--){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        return solution(n-1, nums, k, op1, op2, prefixSum);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums and three integers (k, op1, and op2).
        //- You can perform the following operations on nums:
        //  + Operation 1: Choose (an index i) and divide (nums[i] by 2), rounding (up to the nearest whole number).
        //      + You can perform this operation at most (op1 times), and (not) (more than once per index).
        //  + Operation 2: Choose (an index i) and (subtract k from nums[i]), but only if (nums[i] is greater than or equal to k).
        //      + You can perform this operation at most (op2 times), and (not) (more than once per index).
        //
        //* Note: Both operations can be applied to the same index, but at most once each.
        //  ==> Có thể apply cùng index
        //* Return (the minimum possible sum) of all elements in nums after (performing "any" number of operations).
        //
        //Example 1:
        //Input: nums = [2,8,3,19,3], k = 3, op1 = 1, op2 = 1
        //Output: 23
        //
        //Explanation:
        //Apply Operation 2 to nums[1] = 8, making nums[1] = 5.
        //Apply Operation 1 to nums[3] = 19, making nums[3] = 10.
        //The resulting array becomes [2, 5, 3, 10, 3], which has the minimum possible sum of 23 after applying the operations.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 100
        //0 <= nums[i] <= 10^5 ==> (Positive always)
        //0 <= k <= 10^5
        //0 <= op1, op2 <= nums.length
        //  - Length<=100 ==> Time: O(n^3)
        //
        //- Brainstorm
        //- op1, op2 are limited
        //- op2:
        //  + We only need to check elements with (value is greater k)
        //  ==> If we apply this operation
        //      + Sum will be changes (x*k) (x<=op1)
        //- op1:
        //  + We apply (all of indices)
        //
        //Ex:
        // nums = [2,8,3,19,3], k = 3, op1 = 1, op2 = 1
        //sort(nums) = [2,3,3,8,19]
        //dp[i][j][0,1,2]
        //  + Sum if we apply the operation [1,2] or non operation on the element[i]
        //  + using
        //dp[i][j][0] = max(dp[i][0
        //
//        int[] nums = {2,8,3,19,3};
//        int k = 3, op1 = 1, op2 = 1;
        //
        //** Kinh nghiệm:
        //- Chú ý với last element index=0:
        //  + Nếu vẫn lấy ==> Thì vẫn cần apply operation chứ không phải return luôn
        //  ==> Tức là cần chỉ định rõ xem last index sẽ (==0 or <0)
        //      + Xử lý ntn nếu <0
        //- Round up ==> Ceil
        //- Round down ==> Floor
        //==> Nhớ cái này.
        //
        int[] nums = {2,4,3};
        int k = 3, op1 = 2, op2 = 1;
        System.out.println(minArraySum(nums, k, op1, op2));
        System.out.println(Math.round(1.6));
        System.out.println(Math.round(1.3));
    }
}
