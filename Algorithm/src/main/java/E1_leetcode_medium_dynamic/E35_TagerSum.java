/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package E1_leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class E35_TagerSum {
    public static int findTargetSumWays(int arr[], int target){
        if(arr.length==1&&(arr[0]==target||arr[0]==-1*target)){
            return 1;
        }
        if(arr.length == 1){
            return 0;
        }
        int n=arr.length;
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=arr[i];
        }
        if(sum<target||sum<Math.abs(target)){
            return 0;
        }
        int dp[][]=new int[n][sum*2+1];
        dp[0][arr[0]+sum]=1;
        if(sum-arr[0]==sum+arr[0]){
            dp[0][sum-arr[0]]=2;
        }else{
            dp[0][sum-arr[0]]=1;
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<=sum;j++){
                if(j+sum<=sum*2
                        &&j-arr[i]+sum>=0){
                    dp[i][j+sum]+=dp[i-1][j-arr[i]+sum];
                }
                if(j+arr[i]+sum<=sum*2){
                    dp[i][j+sum]+=dp[i-1][j+arr[i]+sum];
                }
                if(-j+sum==j+sum){
                    continue;
                }
                if(-j+sum<=sum*2
                        &&-j-arr[i]+sum>=0){
                    dp[i][-j+sum]+=dp[i-1][-j-arr[i]+sum];
                }
                if(-j+arr[i]+sum<=sum*2
                        &&-j+sum>=0){
                    dp[i][-j+sum]+=dp[i-1][-j+arr[i]+sum];
                }
            }
        }
        return dp[n-1][target+sum];
    }

    public static void main(String[] args) {
//        int[] nums = {1,1,1,1,1};
//        int target = 3;
        int[] nums = {100, 100};
        int target = -400;
        System.out.println(findTargetSumWays(nums, target));
    }
}
