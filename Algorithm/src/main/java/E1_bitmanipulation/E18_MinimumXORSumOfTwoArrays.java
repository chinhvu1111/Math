package E1_bitmanipulation;

import java.util.Arrays;

public class E18_MinimumXORSumOfTwoArrays {

    public static int[] memo;

    public static int dfs(int index, int[] nums1, int[] nums2, int m, int mask){
        if(index>=nums1.length){
            return 0;
        }
        //
        //0110001
        //0010000
        //
        if(memo[mask]==Integer.MAX_VALUE){
            //Time: O(m)
            for(int i=0;i<m;i++){
                if(((mask>>i)&1)==0){
                    memo[mask] = Math.min(memo[mask], (nums1[index] ^ nums2[i]) + dfs(index+1, nums1, nums2, m, mask|(1<<i)));
                }
            }
        }
//        else{
//            System.out.printf("Duplicated case: %s %s\n", index, Integer.toBinaryString(mask));
//        }
        return memo[mask];
    }

    public static int minimumXORSum(int[] nums1, int[] nums2) {
        int m=nums2.length;
        //Time: O(2^m)
        memo = new int[1<<m];
        Arrays.fill(memo, Integer.MAX_VALUE);
        return dfs(0, nums1, nums2, m, 0);
    }

    public static int minimumXORSumIterative(int[] nums1, int[] nums2) {
        int n = nums1.length;
        //* n==m
        //The xorVal of (nums1[i] and nums2[j])
        int memo[][] = new int[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = nums1[i] ^ nums2[j];
            }
        }

        //Result of the (index=0, mask)
        int dp[][] = new int[n][1 << n];
        for(int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for(int i = 0; i < n; i++) {
            dp[0][1 << i] = memo[0][i];
        }

        //Time: O(n)
        //  + (Loop position of nums1)
        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < dp[i-1].length; j++) {
                //- dp[i][j]:
                //  + The result for (choosing the j) is (the last bit == 1)
                //  ==> We calculate the next reuls as dp[i][j|bitMask]
                if ( dp[i-1][j] != Integer.MAX_VALUE ) {
                    //Time: O(m)
                    //  + Choose the bit
                    for(int a = 0; a < memo[i].length; a++) {
                        int bitmaskA = 1 << a;

                        if ( (j & bitmaskA) == 0) {
                            dp[i][j|bitmaskA] = Math.min(dp[i][j|bitmaskA], dp[i-1][j] + memo[i][a]);
                        }
                    }
                }
            }
        }

        // for(int i = 0; i < dp.length; i++) {
        //     for(int j = 0; j < dp[i].length; j++) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        return dp[dp.length-1][ (1 << n)-1 ];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given two integer arrays nums1 and nums2 of length n.
        //- The (XOR sum of the two integer arrays)
        //  is (nums1[0] XOR nums2[0]) + (nums1[1] XOR nums2[1]) + ... + (nums1[n - 1] XOR nums2[n - 1]) (0-indexed).
        //  + For example, the XOR sum of [1,2,3] and [3,2,1] is equal to (1 XOR 3) + (2 XOR 2) + (3 XOR 1) = 2 + 0 + 2 = 4.
        //- Rearrange (the elements of nums2) such that (the resulting XOR sum) is (minimized).
        //* Return (the XOR sum) after (the rearrangement).
        //
        //Example 2:
        //
        //Input: nums1 = [1,0,3], nums2 = [5,3,4]
        //Output: 8
        //Explanation: Rearrange nums2 so that it becomes [5,4,3].
        //The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == nums1.length
        //n == nums2.length
        //1 <= n <= 14
        //0 <= nums1[i], nums2[i] <= 10^7
        //  + n is not big => Time: O(n^3)
        //  + nums1[i] <= 10^7, ==> We may be use the binary search as the solution
        //
        //- Brainstorm
        //- Brute force:
        //
        //Example 2:
        //
        //Input: nums1 = [1,0,3], nums2 = [5,3,4]
        //Output: 8
        //Explanation: Rearrange nums2 so that it becomes [5,4,3].
        //The XOR sum is (1 XOR 5) + (0 XOR 4) + (3 XOR 3) = 4 + 4 + 0 = 8.
        //0,1,2
        //=====
        //0,1,2
        //1,0,2 ==> Permutation of the (0,1,2)
        //  + Time: O(n⋅n!*n) = O(n^2*n!)
        //  ==> Big
        //
        //- We don't need care about (the order):
        //
        //
        //- How to minimize (a xor b):
        //Ex:
        //101001
        //xor
        //101001
        //=
        //0
        //
        //- We don't try to rearrange the nums2 array:
        //==> We try to match the elements between two arrays
        //- For (each element) in (nums1):
        //  + We will choose (the specific element) in (nums2)
        //** Main point:
        //- For each element in nums1:
        //  + We will choose (the remaining elements) in nums2
        //  ==> This stuff will be cached
        //  ==> We can use the bitmask at this step
        //
        //- How about the nums1?
        //  + We increase the index (0 -> n-1)
        //Ex:
        //index = 0:
        //  + Mask = 00000
        //index = 1:
        //  + Mask = 00001
        //  + Mask = 00010
        //  + Mask = 00100
        //  + Mask = 01000
        //  + Mask = 10000
        //- There are not any mask that (index=i) has that is duplicated with the cases of the (index<i)
        //  + The number of (used bits) will be (increased)
        //
        //- We will define the dp with 1 dimension
        //  + Upper bound = 1<<(num2_size) - 1
        //- Because there is no duplicate case, we (don't need to cache) the value
        //
        //- dp[i] = (nums[i]^nums[j]) + dfs(mask|1<<j)
        //
        //- Shift operation:
        //  + Start with (index==0)
        //
        //- Avoid (recalculating):
        //  + 00000 or 00001 or 10000 = 100001
        //  == 00000 or 10000 or 00001 = 100001
        //  ==> Avoid recalculating
        //  => Mark the calculated memo[i] != MAX_INT
        //
        //- Time analysis:
        //  + Since Since there are (2^m unique masks) and (each) call (iterates over there are m elements),
        //  the total time complexity is:
        //      + Time: O(2^m*m)
        //
        //- We can also solve this problem using the iteration approach:
        //- dp[i][j]:
        //  + The result for (choosing the j) is (the last bit == 1)
        //  ==> We calculate the next reuls as dp[i][j|bitMask]
        //
        //** KINH NGHIỆM:
        //- We can remove (the index in the memoziation) if:
        //  + If we remove this (index==i):
        //      + The cases with (index==i) does not exist in the later layer with (index=i+1)
        //- a^b+c ==> should be (a^b) + c
        //  ==> Interpret to (a^(b+c)) will be wrong.
        //==> We remove the memoziation but we also need to avoid the recalculation:
        //  + 00000 or 00001 or 10000 = 100001
        //  == 00000 or 10000 or 00001 = 100001
        //  ==> Avoid recalculating
        //  => Mark the calculated memo[i] != MAX_INT
        //
        //- Nếu kết quả của phép tính (mask) đẩy vào đầu method:
        //  + Mỗi lần call method --> Loop m times
        //  + Time = (số lần unique(mask) * số lần calls)
        //      => Time complexity : O(unique_num(mask) * call_times)
        //
        //- Integer to String binary:
        //  + Integer.toBinaryString(mask)
        //
        //* Pronunciation:
        //Mũ số (Exponent /ɪkˈspəʊnənt/):
        //25: two to the power of five.
        //Đối với các mũ từ 4 trở lên, quy tắc: cả phần số mũ lẫn phần cơ số đều áp dụng quy tắc số đếm.
        //Số đếm (cơ số) + to the power of + số đếm (số mũ).
        //Đối với số mũ là 2 hoặc 3, đọc là squared (bình phương) và cubed (lập phương).
        //102: ten squared.
        //103: ten cubed.
        //
        //
//        int[] nums1 = {1,0,3}, nums2 = {5,3,4};
        int[] nums1 = {72,97,8,32,15}, nums2 = {63,97,57,60,83};
        //rs: 144
        //Expected: 152
//        System.out.println(3>>1);
//        System.out.println(1<<0);
        System.out.println(minimumXORSum(nums1, nums2));
        System.out.println(minimumXORSumIterative(nums1, nums2));
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(2^m+n)
        //- Time: O(2^m*m)
        //  + Time complexity is O(n*2^n), because we have 2^n states and O(n) transactions frome one state to others. Space complexity is O(2^n).
        //               0000
        //              /   \     \     \
        //           0001   0010  0100  1000 : m times
        //
        //
        //#Reference:
        //2143. Choose Numbers From Two Arrays in Range
        //2172. Maximum AND Sum of Array
        //1908. Game of Nim
        //1879. Minimum XOR Sum of Two Arrays
        //1611. Minimum One Bit Operations to Make Integers Zero
    }
}
