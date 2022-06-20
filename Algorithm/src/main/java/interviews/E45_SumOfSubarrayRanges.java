package interviews;

import java.util.Stack;

public class E45_SumOfSubarrayRanges {

    public static long subArrayRanges(int[] nums) {
        int length=nums.length;
        long max[][]=new long[length][length];
        long min[][]=new long[length][length];
        long rs=0;

        for(int i=0;i<length;i++){
            max[i][i]=nums[i];
            min[i][i]=nums[i];
        }

        for(int i=2;i<=length;i++){
            for(int j=0;j+i-1<length;j++){
                int k=i+j-1;

                max[j][k]=Math.max(max[j][k-1], nums[k]);
                min[j][k]=Math.min(min[j][k-1], nums[k]);
                rs+=max[j][k]-min[j][k];
            }
        }
        return rs;
    }

    public static long subArrayRangesOptimized(int[] nums) {
        int length=nums.length;
        Stack<Integer> stack=new Stack<>();
        long maxSum=0;
        long minSum=0;

        for(int i=length-1;i>=0;i--){
            int minS=0;
            int maxS=0;
            int minCurrent=0;

            if(!stack.isEmpty()){
                minCurrent=Math.min(stack.peek(), nums[i]);
            }
//            if(!stack.isEmpty()&&stack.peek()>nums[i]){
//                minS+=minCurrent*
//            }
            while(!stack.isEmpty()&&stack.peek()<nums[i]){
                minS+=minCurrent;
                maxS+=nums[i];
                stack.pop();
            }
            if(!stack.isEmpty()){
                minS+=minCurrent;
                maxS+=stack.peek();
            }
            stack.add(nums[i]);
            maxSum+=maxS;
            minSum+=minS;
        }
        return maxSum-minSum;
    }

    public static void main(String[] args) {
        int arr[]=new int[]{1,3,3};
//        int arr[]=new int[]{1};
//        int arr[]=new int[]{1,2};
        System.out.println(subArrayRanges(arr));
        System.out.println(subArrayRangesOptimized(arr));
    }
}
