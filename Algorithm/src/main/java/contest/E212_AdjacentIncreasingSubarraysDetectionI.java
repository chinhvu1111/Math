package contest;

import java.util.Arrays;
import java.util.List;

public class E212_AdjacentIncreasingSubarraysDetectionI {

    public static boolean hasIncreasingSubarrays(List<Integer> nums, int k) {
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
        for(int i=0;i<n;i++){
            if(prefix[i]>=k&&i+k<n&&prefix[i+k]>=k){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //- Given an array nums of n integers and an integer k, determine whether there exist two adjacent subarrays of length k
        // such that both subarrays are strictly increasing.
        //- Specifically, check if there are two subarrays starting at indices a and b (a < b), where:
        //- Both subarrays nums[a..a + k - 1] and nums[b..b + k - 1] are strictly increasing.
        //- The (subarrays must be adjacent), meaning b = a + k.
        //* Return true if it is possible to find two such subarrays, and false otherwise.
        //- A subarray is a contiguous non-empty sequence of elements within an array.
//        Integer[] nums = {2,5,7,8,9,2,3,4,3,1};
//        int k = 3;
        Integer[] nums = {5,8,-2,-1};
        int k = 2;
        System.out.println(hasIncreasingSubarrays(Arrays.asList(nums), k));
        //[5,8,-2,-1]
        //2
    }
}
