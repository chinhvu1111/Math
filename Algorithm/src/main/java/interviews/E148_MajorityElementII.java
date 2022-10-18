package interviews;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class E148_MajorityElementII {

    public static Random random=new Random();
    public static List<Integer> rs;

    public static List<Integer> majorityElement(int[] nums) {
        rs=new ArrayList<>();
        findMajorElement(nums, 0, nums.length-1);
        return rs;
    }

    public static void findMajorElement(int[] nums, int left, int right){
//        System.out.println(left+" "+right);
        if(left>=right){
            return;
        }
        if(left==right-1){
            if(nums[left]==nums[right]&&2>nums.length/3){
                rs.add(nums[left]);
            }else if(1>nums.length/3){
                rs.add(nums[left]);
            }
            return;
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
        if(currentIndex+1>n/3){
            rs.add(currentValue);
        }
        findMajorElement(nums, currentIndex, right);
    }

    public static void swap(int[] nums, int i, int j){
        int tem=nums[i];
        nums[i]=nums[j];
        nums[j]=tem;
    }

    public static void main(String[] args) {
        int[] nums=new int[]{1,2};
        System.out.println(majorityElement(nums));
    }
}
