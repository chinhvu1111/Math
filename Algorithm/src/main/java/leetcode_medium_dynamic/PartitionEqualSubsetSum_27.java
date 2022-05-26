/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

/**
 *
 * @author chinhvu
 */
public class PartitionEqualSubsetSum_27 {
    
    public static boolean canPartition(int[] nums) {
        int sum=0;
        int n=nums.length;
        for(int i=0;i<n;i++){
            sum+=nums[i];
        }
        int dp[]=new int[sum/2+1];
        for(int i=0;i<n;i++){
            if(nums[i]<=sum/2){
                dp[nums[i]]=1;
            }
        }
        dp[0]=1;
        for(int i=1;i<=sum/2;i++){
            for(int j=0;j<n;j++){
                if(i>=nums[j]){
                    dp[i]=dp[i-nums[j]];
                }
            }
        }
        if(dp[sum/2]==1){
            return true;
        }
        return false;
    }
    
    public static void main(String[] args) {
        int arr[]=new int[]{1,5,11,5};
        System.out.println(canPartition(arr));
    }
}
