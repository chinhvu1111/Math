package contest;

public class E257_MaximumDifferenceBetweenAdjacentElementsInACircularArray {

    public static int maxAdjacentDistance(int[] nums) {
        int n=nums.length;
        int diff=Integer.MIN_VALUE;
        for(int i=0;i<n;i++){
            if(i!=0){
                diff=Math.max(diff, Math.abs(nums[i]-nums[i-1]));
            }else{
                diff=Math.max(diff, Math.abs(nums[n-1]-nums[0]));
            }
        }
        return diff;
    }

    public static void main(String[] args) {
        int[] nums = {-2,1,-5};
        System.out.println(maxAdjacentDistance(nums));
        //output:3
        //expected: 6
    }
}
