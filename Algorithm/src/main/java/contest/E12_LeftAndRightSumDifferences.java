package contest;

public class E12_LeftAndRightSumDifferences {

    public static int[] leftRigthDifference(int[] nums) {
        int n=nums.length;
        int[] left=new int[n];
        int sum=0;
        for(int i=0;i<n;i++){
            left[i]=sum;
            sum+=nums[i];
        }
        for(int i=0;i<n;i++){
            int currentValue=Math.abs(2*left[i]-sum+nums[i]);
//            System.out.println(currentValue);
            nums[i]=currentValue;
        }
        return nums;
    }

    public static void println(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = {10,4,8,3};
        leftRigthDifference(nums);
        println(nums);
    }
}
