package leetcode_medium;

public class MaximumLengthOfRepeatedSubarray_48 {

    public static int findLength(int[] nums1, int[] nums2) {
        int n=nums1.length;
        int m=nums2.length;
        int dp[][]=new int[n+1][m+1];
        int rs=0;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(nums1[i-1]==nums2[j-1]){
                    dp[i][j]=dp[i-1][j-1]+1;
                    rs=Math.max(rs, dp[i][j]);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,2,1};
//        int arr1[]=new int[]{3,2,1,4,7};
//        int arr[]=new int[]{0,0,0,0,0};
//        int arr1[]=new int[]{0,0,0,0,0};
        int arr[]=new int[]{0,1,1,1,1};
        int arr1[]=new int[]{1,0,1,0,1};
        System.out.println(findLength(arr, arr1));
    }
}
