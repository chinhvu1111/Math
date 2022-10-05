package interviews;

import java.util.Arrays;

public class E170_SingleNumberII {

    public static int singleNumber(int[] nums) {

        return -1;
    }

    public static int singleNumberSort(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;

        for(int i=0;i<n;i+=2){
//            System.out.printf("%s %s, ", nums[i], nums[i+2]);
            if(i+2<n&&nums[i]==nums[i+2]){
                i++;
            }else {
                return nums[i];
            }
        }
//        System.out.println(i);
        return -1;
    }

    public static void main(String[] args) {
//        int arr[]=new int[]{2,2,3,2};
        int arr[]=new int[]{0,1,0,1,0,1,99};
//        int arr[]=new int[]{0,1,0,1,0,1,99,200,200,200};
        System.out.println(singleNumber(arr));
//        System.out.println(1<<1);
        System.out.println(singleNumberSort(arr));
    }
}
