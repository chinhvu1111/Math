package contest;

public class E55_FindTheSumOfEncryptedIntegers {

    public static int sumOfEncryptedInt(int[] nums) {
        int rs=0;

        for(int e: nums){
            int max=0;
            int nextDigit=-1;
            int nextVal=e;
            int length=0;

            while(nextVal!=0){
                nextDigit=nextVal%10;
                max=Math.max(max, nextDigit);
                nextVal=nextVal/10;
                length++;
            }
            int curVal=0;
            for(int i=0;i<length;i++){
                curVal=curVal*10+max;
            }
            rs+=curVal;
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an integer array nums containing positive integers.
        // We define a function encrypt such that encrypt(x) replaces every digit in x with the largest digit in x.
        // For example, encrypt(523) = 555 and encrypt(213) = 333
        //Return the sum of encrypted elements.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 50
        //1 <= nums[i] <= 1000
        //
        //- Brainstorm
        //
        //
        int[] nums = {10,21,31};
        System.out.println(sumOfEncryptedInt(nums));
    }
}
