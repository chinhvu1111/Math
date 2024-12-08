package contest;

import java.util.Arrays;
import java.util.List;

public class E221_MinimumPositiveSumSubarray {

    public static int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int sum=0;
        int n=nums.size();
        int[] prefixSum=new int[n+1];

        for (int i = 0; i < n; i++) {
            sum+=nums.get(i);
            prefixSum[i+1]=sum;
        }
        int rs=Integer.MAX_VALUE;
        for(int i=l;i<=r;i++){
            for(int j=0;j+i<=n;j++){
                if(prefixSum[j+i]-prefixSum[j]>0){
                    rs=Math.min(prefixSum[j+i]-prefixSum[j], rs);
                }
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static void main(String[] args) {
        //You are given an integer array nums and two integers l and r.
        // Your task is to find the minimum sum of a subarray whose size is between l and r (inclusive) and whose sum is greater than 0.
        //* Return the minimum sum of such a subarray. If no such subarray exists, return -1.
        //A subarray is a contiguous non-empty sequence of elements within an array.
        Integer[] nums = {3, -2, 1, 4};
        int l = 2, r = 3;
        System.out.println(minimumSumSubarray(Arrays.asList(nums), l, r));
    }
}
