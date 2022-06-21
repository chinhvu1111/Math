package interviews;

import java.util.Arrays;

public class E48_MaxNumberOfKSumPairs {

    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0;
        int right=nums.length-1;
        int rs=0;

        while (left<right){
            if(k-nums[left]>nums[right]){
                left++;
            }else if(k-nums[left]<nums[right]){
                right--;
            }else{
                rs++;
                left++;
                right--;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{3,1,3,4,3};
        int nums[]=new int[]{1,2,3,4};
//        System.out.println(maxOperations(nums, 6));
        System.out.println(maxOperations(nums, 5));
    }
}
