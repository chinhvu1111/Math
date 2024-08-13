package contest;

public class E165_FindTheCountOfMonotonicPairsI {

    public static int countOfPairs(int[] nums) {
        int n=nums.length;
        int maxNum=0;

        for(int i=0;i<n;i++){
            maxNum=Math.max(maxNum, nums[i]);
        }
        long[][] dp=new long[n][maxNum+1];

        for(int i=0;i<=nums[0];i++){
            dp[0][i]=1;
        }
        for(int i=1;i<n;i++){
            //Chọn
            //3 = 2+1
            //==> 2 ==> Gắn với cái gì đằng trước
            //- a,b
            //+ a tăng dần
            //+ b giảm dần
            //a == j ==> a đằng trước <= a hiện tại
            for(int j=0;j<=nums[i];j++){
                long curRs=0;
                for(int k=0;k<=j;k++){
                    if(nums[i-1]-k>=nums[i]-j){
                        curRs=(curRs+dp[i-1][k])%1_000_000_007;
                    }
                }
                dp[i][j]=curRs;
            }
        }
        int rs=0;
        for(int i=0;i<=nums[n-1];i++){
            rs= (int) ((rs+dp[n-1][i]%1_000_000_007)%1_000_000_007);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array of positive integers nums of length n.
        //We call a pair of non-negative integer arrays (arr1, arr2) monotonic if:
        //- The lengths of both arrays are n.
        //  + arr1 is monotonically (non-decreasing), in other words, arr1[0] <= arr1[1] <= ... <= arr1[n - 1].
        //  + arr2 is monotonically (non-increasing), in other words, arr2[0] >= arr2[1] >= ... >= arr2[n - 1].
        //  + arr1[i] + arr2[i] == nums[i] for all 0 <= i <= n - 1.
        //* Return (the count of monotonic pairs).
        //- Since the answer may be very large, return it modulo (10^9 + 7).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n == nums.length <= 2000
        //1 <= nums[i] <= 50
        //+ Size không quá lớn:
        //  ==> Time: O(n^2*k)
        //+ Giới hạn số 50:
        //  ==> Có thể loop được không?
        //- Số pair rất lớn:
        //  + Khó mà loop hết cases được
        //
        //** Brainstorm
        //Example 1:
        //Input: nums = [2,3,2]
        //The good pairs are:
        //([0, 1, 1], [2, 2, 1])
        //([0, 1, 2], [2, 2, 0])
        //([0, 2, 2], [2, 1, 0])
        //([1, 2, 2], [1, 1, 0])
        //
        //- x=a+b
        //+ a: increase
        //+ b: decrease
        //
        //x = 50 = a+b
        //  + số cặp = 1+49 -> 49+1 (50 cặp)
        //- 2 có thể:
        //  2 = 2+0
        //- Dynamic được không?
        //- a số sau lớn hơn số trước:
        //  + Tính cho cả 50 cases luôn được không?
        //- dp[i][51]:
        //- dp[i][j]:
        //  + Số lượng các phần tử kết thúc bởi i và a=j
        //- CT ntn:
        //+ dp[i][j] tính theo dp[i-1][x] ntn?
        //  + if(nums[i]-j>=nums[i-1]-x&&...)
        //
        //nums = [2,3,2]
        int[]  nums = {2,3,2};
//        int[]  nums = {5,5,5,5};
//        int[]  nums = {5};
//        int[]  nums = {0};
        System.out.println(countOfPairs(nums));
    }
}
