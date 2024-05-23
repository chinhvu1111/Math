package contest;

public class E104_SpecialArrayI {

    public static boolean isArraySpecial(int[] nums) {
        int n=nums.length;
        if(n<=1){
            return true;
        }
        for(int i=0;i<n-1;i++){
            if(Math.abs((nums[i]%2)- (nums[i+1]%2))==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //An array is considered special if every pair of its adjacent elements contains two numbers with different parity.
        //You are given an array of integers nums. Return true if nums is a special array, otherwise, return false.
        //
    }
}
