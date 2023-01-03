package interviews;

import java.util.Arrays;
import java.util.Random;

public class E260_ShuffleAnArray {

    public static class Suffle{
        public int[] originalArray;
        public int[] arr;
        public Random random;
        public Suffle(int[] nums) {
            originalArray= Arrays.copyOf(nums, nums.length);
            arr= Arrays.copyOf(nums, nums.length);
            random=new Random();
        }

        public int[] reset() {
            return originalArray;
        }

        public int[] shuffle() {
            int i=random.nextInt(50);
            int j=random.nextInt(50);
            int temp=arr[i];
            arr[i]=arr[j];
            arr[j]=temp;
            return arr;
        }
    }

    public static void main(String[] args) {
        //#Reference
        //385. Mini Parser
        //1833. Maximum Ice Cream Bars
        //2187. Minimum Time to Complete Trips
        //18. 4Sum
    }
}
