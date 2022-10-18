package interviews;

import java.util.Random;

public class E147_MajorityElement {

    public static Random random=new Random();

    public static int majorityElement(int[] nums) {
        return findMajorElement(nums, 0, nums.length-1);
    }

    public static int findMajorElement(int[] nums, int left, int right){
//        System.out.println(left+" "+right);
        if(left==right){
            return nums[left];
        }
        int pivot=left + random.nextInt(right - left + 1);
//        System.out.println(pivot);
        swap(nums, pivot, right);
        int n=nums.length;

        pivot=right;
        int currentIndex=left;
        int currentValue=nums[pivot];

        for(int i=left;i<right;i++){
            if(nums[i]==nums[pivot]){
                swap(nums, currentIndex++, i);
            }
        }
        swap(nums, currentIndex, right);
        if(currentIndex+1>n/2){
            return currentValue;
        }
        return findMajorElement(nums, currentIndex, right);
    }

    public static void swap(int[] nums, int i, int j){
        int tem=nums[i];
        nums[i]=nums[j];
        nums[j]=tem;
    }

    public static void main(String[] args) {
        int[] arr =new int[]{2,2,1,1,1,2,2};
//        int[] arr =new int[]{3,2,3};
//        int[] arr =new int[]{3};

        System.out.println(majorityElement(arr));
    }
}
