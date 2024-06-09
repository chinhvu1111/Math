package contest;

public class E122_FindTheMaximumLengthOfAGoodSubsequenceI {

    public static int maximumLength(int[] nums, int k) {
        int n=nums.length;
        int[][] dp=new int[n][k+1];
        //1,2,1,1,3
        //k=2

        //Index=i
        for(int i=0;i<n;i++){
            //Can tinh so cap = h
            for(int j=i-1;j>=0;j--){
                if(nums[i]!=nums[j]){
                    for(int h=0;h<k;h++){
                        if(h!=0&&dp[j][h]==0){
                            continue;
                        }
                        dp[i][h+1]=Math.max(dp[i][h+1], dp[j][h]+1);
                    }
                }else{
                    for(int h=0;h<=k;h++){
                        if(h!=0&&dp[j][h]==0){
                            continue;
                        }
                        dp[i][h]=Math.max(dp[i][h], dp[j][h]+1);
                    }
                }
            }
        }
//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j <= k; j++) {
//                System.out.printf("h=%s, value=%s -- ", j, dp[i][j]);
//            }
//            System.out.println();
//        }
        int rs=0;
        for(int i=0;i<n;i++){
            for(int j=0;j<=k;j++){
                rs=Math.max(rs, dp[i][j]);
            }
        }
//        if(rs==0){
//            return 0;
//        }
        return rs+1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums and (a non-negative integer k).
        //- (A sequence of integers seq) is called good if there are (at most k indices i)
        // in the range [0, seq.length - 2] such that seq[i] != seq[i + 1].
        //* Return (the maximum possible length) of (a good subsequence of nums).
        //
        //Example 1:
        //Input: nums = [1,2,1,1,3], k = 2
        //Output: 4
        //Explanation:
        //The maximum length subsequence is [1,2,1,1,3].
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 500
        //1 <= nums[i] <= 10^9
        //0 <= k <= min(nums.length, 25)
        //==> size không quá lớn
        //
        //- Brainstorm
        //- Get max length của subsequence sao cho (seq[i] != seq[i + 1])
        //  + 0<=i<= length(seq)-2
        //- k==0
        //  + Tức là không có pair nào != nhau
        //  ==> Bằng nhau hết
        //- k=1
        //  + 1 pair khác là được
        //
        //- Prefix sum được không?
        //Example 1:
        //Input: nums = [1,2,1,1,3], k = 2
        //Output: 4
        //
        //value([1,2,1,1,3]) - value([1,2]) = value([2,1,1,3])
        //
        //- Ở đây là subsequence ==> Không dùng prefix sum được
        //
        //==> Dùng dynamic
        //
        //- dp[i][j]: Chiều dài max của subsequenc cho đến index=i
        //  + j là số lượng pair != nhau
        //
        //0:0,1,0
        //1:
        //2
        //3
        //4
//        int[] nums = {1,2,1,1,3};
//        int k = 2;
//        int[] nums = {1,2,3,4,5,1};
//        int k = 0;
//        int[] nums = {1,2,3,4,5};
//        int k = 0;
        int[] nums = {2, 15};
        int k = 2;
        System.out.println(maximumLength(nums, k));
    }
}
