package E1_PrefixSum;

import java.util.HashMap;

public class E8_CountStrictlyIncreasingSubarrays {

    public static long countSubarrays(int[] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        long[] increase=new long[n];
        long rs=0;
        increase[0]=1;
        if(n==1){
            rs=1;
        }

        for(int i=1;i<n;i++){
            if(nums[i]>nums[i-1]){
                increase[i]=increase[i-1]+1;
            }else{
                rs+=(increase[i-1])*(increase[i-1]+1)/2;
                increase[i]=1;
            }
        }
        rs+=increase[n-1]*(increase[n-1]+1)/2;
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement:
        //- Given the nums array
        //* Return the number of subarrays that are in strictly increasing order.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^6
        //
        //- Brainstorm
        //- Với subarry has length=k ==> It contains the subarrays having the length <k
        //
        //Ex: 1,2,3,4,5
        //1,2,3,4,5
        //(1,2),(2,3),(3,4),(4,5)
        //(1,2,3),(2,3,4),(3,4,5)
        // 1 + 2 + 3 + 4 + 5 = 15 ==> (Sum all 1 --> 5)
        //Eg:
        //nums = [1,3,5,4,4,6]
        //[1,2,3,1,1,2]
        // 3*4/2 + 2*3/2 = 6+3
        //
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(n)
        //- Time: O(1)
        //
        //#Reference:
        //1863. Sum of All Subset XOR Totals
        //2459. Sort Array by Moving Items to Empty Space
        //1337. The K Weakest Rows in a Matrix
        //
        int[] arr=new int[]{1,3,5,4,4,6};
        System.out.println(countSubarrays(arr));
    }
}
