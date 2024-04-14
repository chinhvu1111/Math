package contest;

import java.util.HashSet;

public class E82_MaximumPrimeDifference {

    public static int maximumPrimeDifference(int[] nums) {
        HashSet<Integer> setNonPrimeNumber=new HashSet<>();
        int n=nums.length;
        int firstPrimeIndex=0;
        int secondPrimeIndex=0;

        for(int i=0;i<n;i++){
            if(!setNonPrimeNumber.contains(nums[i])&&isPrime(nums[i])){
                firstPrimeIndex=i;
                break;
            }
            setNonPrimeNumber.add(nums[i]);
        }
        for(int i=n-1;i>=0;i--){
            if(!setNonPrimeNumber.contains(nums[i])&&isPrime(nums[i])){
                secondPrimeIndex=i;
                break;
            }
            setNonPrimeNumber.add(nums[i]);
        }
        return Math.abs(firstPrimeIndex-secondPrimeIndex);
    }

    public static boolean isPrime(int num){
        if(num<=1){
            return false;
        }
        for(int i=2;i<=Math.sqrt(num);i++){
            if(num%i==0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums.
        //* Return an integer that is the maximum distance between the indices of two (not necessarily different) (prime numbers) in nums.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 3 * 105
        //1 <= nums[i] <= 100
        //The input is generated such that the number of prime numbers in the nums is at least one.
        //
        //- Brainstorm
        //
        //
//        int[] nums= {4,2,9,5,3};
        int[] nums= {4,8,2,8};
        System.out.println(maximumPrimeDifference(nums));
    }
}
