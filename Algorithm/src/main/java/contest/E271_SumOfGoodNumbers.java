package contest;

public class E271_SumOfGoodNumbers {

    public static int sumOfGoodNumbers(int[] nums, int k) {
        int rs=0;
        int n= nums.length;

        for(int i=0;i<n;i++){
            boolean isValid=true;
            if(i>=k&&nums[i]<=nums[i-k]){
                isValid=false;
            }
            if(i+k<n&&nums[i]<=nums[i+k]){
                isValid=false;
            }
            if(isValid){
                rs+=nums[i];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //- Given an array of integers nums and an integer k,
        // an element nums[i] is considered good if it is strictly greater than the elements at indices i - k and i + k (if those indices exist).
        // If neither of these indices exists, nums[i] is still considered good.
        //* Return the sum of all the good elements in the array.
    }
}
