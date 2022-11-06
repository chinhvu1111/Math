package contest;

import java.util.HashMap;

public class E7_MaximumSumOfDistinctSubarraysWithLengthK {

    public static long maximumSubarraySum(int[] nums, int k) {
        int n=nums.length;
        long prefixSum[]=new long[n];
        long currentValue=0;
        HashMap<Integer, Integer> hashIndex=new HashMap<>();
        int start=0;
        int end=0;
        long rs=0;

        for(int i=0;i<n;i++){
            currentValue+=nums[i];
            prefixSum[i]=currentValue;
        }
        while (end<n){
            if(!hashIndex.containsKey(nums[end])){
                hashIndex.put(nums[end], end);
                if(end-start+1==k){
                    long leftValue=0;
                    if(start>0){
                        leftValue=prefixSum[start-1];
                    }
                    rs=Math.max(rs, prefixSum[end]-leftValue);
                    start++;
                    end++;
                    continue;
                }
                if(end-start+1<k){
                    end++;
                }else if(end-start+1>k){
                    start++;
                }
            }else{
                if(start<hashIndex.get(nums[end])+1){
                    start=hashIndex.get(nums[end])+1;
                }
                hashIndex.put(nums[end], end);
                if(end<start){
                    end=start;
                    continue;
                }
                if(end-start+1==k){
                    long leftValue=0;
                    if(start>0){
                        leftValue=prefixSum[start-1];
                    }
                    rs=Math.max(rs, prefixSum[end]-leftValue);
                    start++;
                    end++;
                    continue;
                }
                if(end-start+1<k){
                    end++;
                }else if(end-start+1>k){
                    start++;
                }
            }
//            System.out.println(rs);
        }

        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{1,5,4,2,9,9,9};
//        int k=3;
//        int[] arr=new int[]{4,4,5};
//        int k=3;
//        int[] arr=new int[]{4};
//        int k=1;
        int[] arr=new int[]{1,2,2};
        int k=2;
//        int[] arr=new int[]{9,9,9,1,2,3};
//        int k=3;
//        int[] arr=new int[]{1,1,2};
//        int k=2;
//        int[] arr=new int[]{9,18,10,13,17,9,19,2,1,18};
//        int k=5;
        System.out.println(maximumSubarraySum(arr, k));
    }
}
