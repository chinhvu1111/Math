package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E107_RangeSumOfSortedSubarraySums {

    public static int rangeSum(int[] nums, int n, int left, int right) {
        int[] prefixSum =new int[n];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i]=sum;
        }
        List<Integer> newList=new ArrayList<>();

        for(int i=1;i<=n;i++){
            for(int j=0;j+i<=n;j++){
                int k=i+j-1;
                int leftSum=0;
                if(j>0){
                    leftSum=prefixSum[j-1];
                }
                newList.add(prefixSum[k]-leftSum);
            }
        }
        Collections.sort(newList);
        int result=0;

        for(int i=left-1;i<right;i++){
            result=(result+newList.get(i))%1_000_000_007;
        }
        return result;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{1,2,3,4};
//        int left=1;
//        int right=5;
//        int arr[]=new int[]{1,2,3,4};
//        int left=3;
//        int right=4;
        int arr[]=new int[]{1,2,3,4};
        int left=1;
        int right=10;
        System.out.println(rangeSum(arr, arr.length, left, right));
    }
}
