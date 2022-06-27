package interviews;

import java.util.Arrays;

public class E48_MaxNumberOfKSumPairs {

    public static int maxOperations(int[] nums, int k) {
        Arrays.sort(nums);
        int left=0;
        int right=nums.length-1;
        int rs=0;

        while (left<right){
            if(nums[left]+nums[right]==k){
                rs++;
                left++;
                right--;
            }else if(k-nums[left]<nums[right]){
                right--;
            }else if(k-nums[left]>nums[right]){
                left++;
            }
        }
        return rs;
    }

    public int maxOperationsOptimized(int[] nums, int k) {
        // Sorting the Array
        Arrays.sort(nums);
        //Initializing the Count
        int count=0;
        for(int i=0,j=nums.length-1; i<j;){
            //if Integer at i and j = equal, increament the Count and move the pointer
            if(nums[i]+nums[j]==k){
                count +=1;
                j--;
                i++;
            }
            // if the Integers at i and j are greater the k, we need the decrease the sum so we move the j pointer by -1
            else if(nums[i]+nums[j]>k){
                j--;
            }
            // else we increment the i pointer by +1
            else {
                i++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
//        int nums[]=new int[]{3,1,3,4,3};
        int nums[]=new int[]{1,2,3,4};
//        System.out.println(maxOperations(nums, 6));
        //Bài này thể hiện 1 điều rằng thay đổi thứ tự if else cũng có thể giảm tốc độ run
        //EXP:
        //- Ta luôn hướng đến việc để các điều kiện muốn xảy ra ở đâu tiên --> Để tối ưu.
        //===> Hay là để (điều kiện conditions tỉ lệ xảy ra nhiều nhất lên đầu)
        //CODE :
//        if(nums[i]+nums[j]==k){
//            count +=1;
//            j--;
//            i++;
//        }
//        // if the Integers at i and j are greater the k, we need the decrease the sum so we move the j pointer by -1
//        else if(nums[i]+nums[j]>k){
//            j--;
//        }
//        // else we increment the i pointer by +1
//        else {
//            i++;
//        }
        System.out.println(maxOperations(nums, 5));
    }
}
