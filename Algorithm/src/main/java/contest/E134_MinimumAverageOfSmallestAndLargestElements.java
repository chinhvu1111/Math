package contest;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E134_MinimumAverageOfSmallestAndLargestElements {

    public static double minimumAverage(int[] nums) {
        Arrays.sort(nums);
        int n=nums.length;
        int left=0, right=n-1;
        PriorityQueue<Double> averages=new PriorityQueue<>();

        while(left<=right){
            averages.add(((double)nums[left]+(double)nums[right])/2);
            left++;
            right--;
        }
        if(averages.isEmpty()){
            return -1;
        }
        return averages.peek();
    }

    public static void main(String[] args) {

    }
}
