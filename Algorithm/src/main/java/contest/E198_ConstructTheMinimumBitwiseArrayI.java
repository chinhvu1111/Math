package contest;

import java.util.Arrays;
import java.util.List;

public class E198_ConstructTheMinimumBitwiseArrayI {

    public static int[] minBitwiseArray(List<Integer> nums) {
        int n=nums.size();
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            boolean isValid=false;
            for(int j=-1000;j<=nums.get(i);j++){
                if((j|(j+1))==nums.get(i)){
                    isValid=true;
                    rs[i]=j;
                    break;
                }
            }
            if(!isValid){
                rs[i]=-1;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an array nums consisting of (n prime integers).
        //You need to construct an array ans of length n, such that,
        //  + for each index i, the bitwise OR of ans[i] and ans[i] + 1 is equal to nums[i],
        //  Ex: i.e. ans[i] OR (ans[i] + 1) == nums[i].
        //  + Additionally, you must (minimize) (each value of ans[i]) in the resulting array.
        //- If it is not possible to find such a value for ans[i] that satisfies the condition, then set ans[i] = -1.
        //- A prime number is a natural number greater than 1 with only two factors, 1 and itself.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 100
        //2 <= nums[i] <= 1000
        //nums[i] is a prime number.
        //
        //** Brainstorm
        //
        //
//        Integer[] nums = {11,13,31};
        Integer[] nums = {2,3,5,7};
        int[] rs= minBitwiseArray(Arrays.asList(nums));
        for (int i = 0; i < rs.length; i++) {
            System.out.println(rs[i]);
        }
    }
}
