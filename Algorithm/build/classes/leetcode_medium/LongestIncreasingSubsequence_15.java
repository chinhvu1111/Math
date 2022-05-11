/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package leetcode_medium_dynamic;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Vector;

/**
 *
 * @author chinhvu
 */
public class LongestIncreasingSubsequence_15 {
    
    public static int dp[];
    
    public static int lengthOfLIS(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        int max[]=new int[n];
        int rs=0;
        
        for(int i=0;i<n;i++){
            int min=0;
            
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]&&min<dp[j]){
                    min=dp[j];
                    
                    if(min==j+1||max[j]==dp[j]){
                        break;
                    }
                }
            }
            dp[i]=min+1;
            rs=Math.max(dp[i], rs);
            max[i]=Math.max(rs, max[i]);
        }
        return rs;
    }
    
    public static int lengthOfLIS1(int[] nums) {
        int n=nums.length;
        int dp[]=new int[n];
        int max[]=new int[n];
        int rs=0;
        
        for(int i=0;i<n;i++){
            int min=0;
            int low=0;
            int high=i;
            
            while(low<=high){
                if(nums[i]>nums[low]){
                    min=Math.max(min, dp[low]);
                }
                low++;
                if(nums[i]>nums[high]){
                    min=Math.max(min, dp[high]);
                }
                high--;
            }
            dp[i]=min+1;
            rs=Math.max(dp[i], rs);
            max[i]=Math.max(rs, max[i]);
        }
        return rs;
    }
    
    public static int findElement(int low, int high, int value){
        if(low>=high){
            return low;
        }
        
        int mid=(low+high)/2;
        int rs=-1;
        
        if(dp[mid]<value){
            rs=findElement(mid+1, high, value);
        }else{
            rs=findElement(0, mid, value);
        }
//        if(mid>0&&dp[mid]==dp[mid-1]){
//            rs=Math.min(rs, findElement(mid, high, value));
//        }
        return rs;
    }
    
    public static int lengthOfLIS2(int[] nums) {
        int n=nums.length;
        dp=new int[n];
        dp[0]=nums[0];
        int high=1;
        
        for(int i=1;i<n;i++){
            int last=dp[high-1];
            
            if(nums[i]>=last){
                dp[high]=nums[i];
                high++;
            }
            else{
                //Chậm hay nhanh thì dùng search thư viện nhanh hơn
                //VD: 1,2,3,5 --> 4
                //return (-3)
                int index=Arrays.binarySearch(dp, 0, high, nums[i]);
                
                if(index<0){
                    index=-(index+1);
                }
//                int index=findElement(0, high, nums[i]);
                dp[index]=nums[i];
            }
        }
        return high;
    }
    
    public int lengthOfLIS3(int[] nums) {
        // dp keeps some of the visited element in a sorted list, and its size is lengthOfLIS sofar.
        // It always keeps the our best chance to build a LIS in the future.
        int[] dp= new int[nums.length];
        int len = 0;
        for (int num: nums) {
          int i = Arrays.binarySearch(dp, 0, len, num);
          // If not found, change i to new insertion point index.
          if (i < 0) {
            i = -(i+1);
          }
          // If num is the biggest, we should add it into the end of dp.
          // If num is not the biggest, we should keep it in dp and replace the previous num in
          // this position. Because even if num can't build longer LIS directly, it can help
          // build a smaller dp, and we will have the best chance to build a LIS in the future.
          // All elements before this position will be the best(smallest) LIS sor far. 
          dp[i] = num;
          if (i == len){
            len++;
          }
        }
        // dp doesn't keep LIS, and only keep the lengthOfLIS.
        return len;       
    }
    
    public static void main(String[] args) {
//        int arr[]=new int[]{0,1,0,3,2,3};
//        int arr[]=new int[]{1,3,6,7,9,4,10,5,6};
//        int arr[]=new int[]{10,9,2,5,3,7,101,18};
        int arr[]=new int[]{4,10,4,3,8,9};
        System.out.println(lengthOfLIS(arr));
        System.out.println(lengthOfLIS2(arr));
    }
}
