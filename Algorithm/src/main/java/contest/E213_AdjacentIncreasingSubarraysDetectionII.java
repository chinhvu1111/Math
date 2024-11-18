package contest;

import java.util.Arrays;
import java.util.List;

public class E213_AdjacentIncreasingSubarraysDetectionII {

    public static int maxIncreasingSubarrays(List<Integer> nums) {
        int n=nums.size();
        int[] prefix=new int[n];
        prefix[0]=1;

        for(int i=1;i<n;i++){
            if(nums.get(i)>nums.get(i-1)){
                prefix[i]=prefix[i-1]+1;
            }else{
                prefix[i]=1;
            }
        }
//        for (int i = 0; i < n; i++) {
//            System.out.printf("%s,", prefix[i]);
//        }
        int rs=0;
        for (int i = 0; i < n; i++) {
            int curVal= prefix[i];
            if(i>=curVal&&prefix[i-curVal]>=curVal){
                rs=Math.max(rs, curVal);
            }else{
                //1,2,3,4,5
                //==> 2
                //1,2,3,4
                //==> 2
                rs=Math.max(rs, curVal/2);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given an array nums of n integers, your task is to find (the maximum value of k) for which there exist two adjacent subarrays of length k each,
        // such that both subarrays are strictly increasing.
        //- Specifically, check if there are two subarrays of length k starting at indices a and b (a < b), where:
        //  + Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
        //  + The subarrays must be adjacent, meaning b = a + k.
        //* Return (the maximum possible value of k).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //2 <= nums.length <= 2 * 10^5
        //-109 <= nums[i] <= 10^9
        //  + n>2*10^5 ==> Time: O(n)
        //
        //** Brainstorm
        //Ex:
        //nums = [2,5,7,8,9,2,3,4,3,1]
        //prefix = [1,2,3,4,5,1,2,3,1,1,1]
        //
        //- prefix[i]==3:
        //  - prefix[i-k]>=3 ==> Get ks
        //
//        Integer[] nums = {2,5,7,8,9,2,3,4,3,1};
        //Special case:
        //1,2,3,4,5,6
        //==> rs = 3
        Integer[] nums = {1,2,3,4,4,4,4,5,6,7};
        System.out.println(maxIncreasingSubarrays(Arrays.asList(nums)));
        //
    }
}
