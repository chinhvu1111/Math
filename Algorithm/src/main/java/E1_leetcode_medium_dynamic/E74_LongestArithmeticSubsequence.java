package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E74_LongestArithmeticSubsequence {

    public static int longestArithSeqLength(int[] nums) {
        if(nums.length==0){
            return 0;
        }
        int min= Arrays.stream(nums).min().getAsInt();
        int max= Arrays.stream(nums).max().getAsInt();
        int n=nums.length;
        int dp[][]=new int[n][(max-min)*2+1];
        int rs=0;

        for(int i=0;i<n;i++){

            for(int j=i-1;j>=0;j--){
                int diff=(nums[i]-nums[j])+max-min;

                dp[i][diff]=Math.max(dp[i][diff], dp[j][diff]+1);
                rs=Math.max(rs, dp[i][diff]);
            }
        }
        return rs+1;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{3,6,9,12};
//        int arr[]=new int[]{9,4,7,2,10};
//        int arr[]=new int[]{};
//        int arr[]=new int[]{1};
//        int arr[]=new int[]{20,1,15,3,10,5,8};
        int arr[]=new int[]{22,8,57,41,36,46,42,28,42,14,9,43,27,51,0,0,38,50,31,60,29,31,20,23,37,53,27,1,47,42,28,31,10,35,39,12,15,6,35,31,45,21,30,19,5,5,4,18,38,51,10,7,20,38,28,53,15,55,60,56,43,48,34,53,54,55,14,9,56,52};
        System.out.println(longestArithSeqLength(arr));
    }
}
