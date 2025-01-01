package contest;

import java.util.Arrays;
import java.util.HashSet;

public class E243_MaximumNumberOfDistinctElementsAfterOperations {

    public static int maxDistinctElements(int[] nums, int k) {
        int n=nums.length;
        Arrays.sort(nums);
        int prevNum=Integer.MIN_VALUE;
        int[] newArr=new int[n];
        int i;

        for (i = 0; i < n; i++) {
            if(prevNum==Integer.MIN_VALUE){
                prevNum=nums[i]-k;
            }else{
                //x
                //y
                //-2,4, k=3
                //  + [-3,3]
                if(nums[i]-k>prevNum){
//                    if(nums[i]-k>prevNum){
//                        prevNum=nums[i]-k;
//                    }else{
//                        prevNum=prevNum+1;
//                    }
                    prevNum=nums[i]-k;
                }else if(nums[i]+k>prevNum){
                    prevNum=prevNum+1;
                }else{
                    prevNum=Math.max(prevNum, nums[i]);
                }
            }
            newArr[i]=prevNum;
//            System.out.println(prevNum);
        }
//        for(int j=i;j<n;j++){
//            newArr[j]=nums[j];
//        }
        HashSet<Integer> set=new HashSet<>();
        for (int j = 0; j < n; j++) {
            set.add(newArr[j]);
        }
        return set.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array nums) and (an integer k).
        //- You are allowed to perform the following operation on each element of the array (at most once):
        //  + Add an integer in the range [-k, k] to the element.
        //* Return (the maximum possible number of distinct elements) in nums after (performing the operations).
        //
        //Example 1:
        //Input: nums = [1,2,2,3,3,4], k = 2
        //Output: 6
        //Explanation:
        //nums changes to [-1, 0, 1, 2, 3, 4] after performing operations on the first four elements.
        //
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //0 <= k <= 10^9
        //  + length<=10^5 ==> Time: O(n)
        //  + nums[i]<=10^9 ==> Long/ Binary search
        //
        //- Brainstorm
        //- Min ==> distinct
        //Ex:
        //nums = [1,2,2,3,3,4], k = 2
        //[-1,2,2,3,3,4] k=[-2,2]
        //[-1,0,2,3,3,4] k=[-2,2]
        //[-1,0,1,3,3,4] k=[-1,2]
        //[-1,0,1,2,3,4] k=[-1,2]
        //[-1,0,1,2,3,4] k=[0,2]
        //
        //Ex:
        //nums=[4,4,4,4], k=1
        //nums=[3,4,4,4] k=[-1,1]
        //nums=[3,4,4,4] k=[0,1]
        //nums=[3,4,5,4] k=[1,1]
        //
//        int[] nums = {1,2,2,3,3,4};
//        int k = 2;
//        int[] nums = {9,5,5};
//        int k = 0;
        //output: 3
        //rs:2
//        int[] nums = {9,9,10,10,7};
//        int k = 0;
        //output: 4
        //rs:3
//        int[] nums = {9,10,9,9,9};
//        int k = 1;
        //[8,9,10,9,10]
        //9,9,9,9,10
        int[] nums = {9,10,9,9,9,20,100,21,55};
        int k = 1;
        System.out.println(maxDistinctElements(nums, k));
    }
}
