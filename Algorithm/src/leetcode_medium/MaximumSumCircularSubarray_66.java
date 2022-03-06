package leetcode_medium;

import java.util.Arrays;

public class MaximumSumCircularSubarray_66 {

    public static int maxSubarraySumCircular(int[] nums) {
        int n=nums.length;

        int minC=0;
        int maxC=0;
        int min=Integer.MAX_VALUE;
        int max=Integer.MIN_VALUE;
        int sum = 0;
        int maxArr=nums[0];

        for(int i=0;i<n;i++){
            sum+=nums[i];
            maxArr=Math.max(maxArr, nums[i]);

            if(minC+nums[i]>=0){
                minC=0;
            }else{
                minC+=nums[i];
                min=Math.min(minC, min);
            }
            if(maxC+nums[i]>=0){
                maxC+=nums[i];
                max=Math.max(maxC, max);
            }else{
                maxC=0;
            }
        }
        int rs=Math.max(max, sum-min);

        if(rs==0){
            return maxArr;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,-2,3,-2};
//        int arr[]=new int[]{5,-3,5};
//        int arr[]=new int[]{-3,-2,-3};
//        int arr[]=new int[]{-2,4,-5,4,-5,9,4};
        //Sai: 14
        //15: Sai do check điều kiên min sai (minC+arr[i]>=0: --> Mới reset minC;
        int arr[]=new int[]{-10,-7,9,-7,6,9,-9,-4,-8,-5};
        //Sai: 15
        //16: Sai điều kiện check maxC: (maxC+arr[i]>=0 --> maxC+=arr[i]);
        System.out.println(maxSubarraySumCircular(arr));
    }
}
