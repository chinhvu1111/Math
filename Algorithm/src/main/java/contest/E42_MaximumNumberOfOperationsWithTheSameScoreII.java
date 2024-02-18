package contest;

import java.util.Arrays;

public class E42_MaximumNumberOfOperationsWithTheSameScoreII {

    //Ex:
    //nums=[3,2,1,2,3,4]
    //+ n=6
    //low=0, high=1
    public static int[][] dp;
    public static int solution(int[] nums, int low, int high, int fixedSum){
        //low=0, high=5
        //
        if(high-low<1){
            return 0;
        }
        if(dp[low][high]!=-1){
            return dp[low][high];
        }
        int curRs=0;
        //Case 1:
        int curSum=nums[low]+nums[low+1];
//        System.out.printf("Case 1: fixed sum : %s\n", curSum);
        if(curSum==fixedSum){
            curRs=Math.max(curRs, solution(nums, low+2, high, fixedSum)+1);
        }
        //Case 2:
        curSum=nums[high]+nums[high-1];
//        System.out.printf("Case 2: fixed sum : %s\n", curSum);
        if(curSum==fixedSum){
            curRs=Math.max(curRs, solution(nums, low, high-2, fixedSum)+1);
        }
        //Case 3:
        curSum=nums[low]+nums[high];
//        System.out.printf("Case 3: fixed sum : %s\n", curSum);
        if(curSum==fixedSum){
            curRs=Math.max(curRs, solution(nums, low+1, high-1, fixedSum)+1);
        }
        return dp[low][high]=curRs;
    }

    public static void resetDp(int[][] dp){
        for(int[] arr: dp){
            Arrays.fill(arr, -1);
        }
    }

    public static int maxOperations(int[] nums) {
        int n=nums.length;
        if(n<=1){
            return 0;
        }
        dp=new int[n][n];
        resetDp(dp);
        //Case 1:
        int curSum=nums[0]+nums[1];
        int curRs=0;
//        System.out.println(curSum);
        curRs=Math.max(curRs, solution(nums, 2, n-1, curSum)+1);
        System.out.println(curRs);
        resetDp(dp);
        //Case 2:
        curSum=nums[n-2]+nums[n-1];
        curRs=Math.max(curRs, solution(nums, 0, n-3, curSum)+1);
        System.out.println(curRs);
        resetDp(dp);
        //Case 3:
        curSum=nums[0]+nums[n-1];
        curRs=Math.max(curRs, solution(nums, 1, n-2, curSum)+1);
        System.out.println(curRs);
        return curRs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an array of integers called nums, you can perform (any) of the following operation while nums contains at least 2 elements:
        //+ Choose the first two elements of nums and delete them.
        //+ Choose the last two elements of nums and delete them.
        //+ Choose the first and the last elements of nums and delete them.
        //- The score of the operation is the sum of the deleted elements.
        //* Your task is to find the maximum number of operations that can be performed, such that all operations have the same score.
        //* Return the maximum number of operations possible that satisfy the condition mentioned above.
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //2 <= nums.length <= 2000
        //1 <= nums[i] <= 1000
        //
        //- Brainstorm
        //
        //- Thử dùng đệ quy (Top-down approach) xử lý xem ntn
        //  +
//        int[] nums=new int[]{3,2,1,2,3,4};
//        int[] nums=new int[]{3,2,6,1,4};
//        int[] nums=new int[]{1,9,7,3,2,7,4,12,2,6};
//        int[] nums=new int[]{8,3,11,3,5,12,9};
//        int[] nums=new int[]{1,7,4,5};
        int[] nums=new int[]{4,1,3,3};
        System.out.println(maxOperations(nums));
    }
}
