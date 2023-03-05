package leetcode_medium_dynamic;

import java.util.HashSet;

public class E86_FindSubarraysWithEqualSum {

    public static boolean findSubarrays(int[] nums) {
        int n=nums.length;
        HashSet<Integer> hashSet=new HashSet<>();
        for(int i=1;i<n;i++){
            if(!hashSet.contains(nums[i]+nums[i-1])){
                hashSet.add(nums[i]+nums[i-1]);
            }else{
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //#Reference
        //2396. Strictly Palindromic Number
        //1477. Find Two Non-overlapping Sub-arrays Each With Target Sum
    }
}
