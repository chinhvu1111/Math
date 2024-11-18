package E1_bitmanipulation;

public class E16_MaximumXORForEachQuery {

    public static int[] getMaximumXor(int[] nums, int maximumBit) {
        int n= nums.length;
        int xorVal = 0;
        int expectXorVal = (1<<maximumBit)-1;
        int[] rs=new int[n];
        for (int i = 0; i < n; i++) {
            xorVal^=nums[i];
            rs[n-i-1]=xorVal^expectXorVal;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given a sorted array nums of (n non-negative integers) and (an integer maximumBit).
        //- You want to perform the following (query n times):
        //  + Find (a non-negative integer k < 2^maximumBit) such that (nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR (k)) is maximized.
        //  + k is the answer to (the ith query).
        //  + Remove (the last element) from (the current array nums).
        //* Return an array answer, where answer[i] is the answer to (the ith query).
        //Example 1:
        //
        //Input: nums = [0,1,1,3], maximumBit = 2
        //Output: [0,3,2,3]
        //Explanation: The queries are answered as follows:
        //1st query: nums = [0,1,1,3], k = 0 since 0 XOR 1 XOR 1 XOR 3 XOR 0 = 3.
        //2nd query: nums = [0,1,1], k = 3 since 0 XOR 1 XOR 1 XOR 3 = 3.
        //3rd query: nums = [0,1], k = 2 since 0 XOR 1 XOR 2 = 3.
        //4th query: nums = [0], k = 3 since 0 XOR 3 = 3.
        //- We remove the last element one by one:
        //  + Find the k such that (nums[0] XOR nums[1] XOR ... XOR nums[nums.length-1] XOR (k)) is maximized.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //nums.length == n
        //1 <= n <= 10^5
        //1 <= maximumBit <= 20
        //0 <= nums[i] < 2^maximumBit
        //nums is sorted in ascending order.
        //  + Length <= 10^5 ==> Time: O(n)
        //  + k<2^maximumBit
        //      + Ex:
        //      maximumBit=2
        //      k < 11
        //
        //- Brainstorm
        //Ex:
        //How to maximize (xor_val = a xor b)
        //1011
        //xor
        //0001
        //=
        //1011
        //- a = 1011
        //  + We need to find k = 1111 xor a = (1111 xor 1011)
        //- n bit
        //  + bit_val = (1<<n)-1
        //Ex:
        //a = 00001101011
        //rs= 11111111111
        //11111111111
        //xor
        //00001101011
        //=
        //11110010100
        //
        //==> b has the limitation = (2^maximumBit - 1)
        //
        //1.1, Optimization
        //1.2, Complexity
        //
        int[] nums = {0,1,1,3};
        int maximumBit = 2;
        int[] rs = getMaximumXor(nums, maximumBit);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s,", rs[i]);
        }
        //#Reference:
        //2588. Count the Number of Beautiful Subarrays
    }
}
