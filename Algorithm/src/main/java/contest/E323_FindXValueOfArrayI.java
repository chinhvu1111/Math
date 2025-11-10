package contest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class E323_FindXValueOfArrayI {

    public static long[] resultArray(int[] nums, int k) {
        int n = nums.length;
//        HashMap<Long, Integer> mapCountPrefix=new HashMap<>();
//        long mul=1;
        long[] rs=new long[k];
        int[][] dp = new int[n+1][k];
        for (int i = 0; i < n; i++) {
            //prefix_val % k == j
            //
            //0
            //1
            //2
            //3
            //4
            //end with j
            //- Use the dynamic programming to solve this problem
            dp[i+1][nums[i]%k]++;
            for (int j = 0; j < k; j++) {
                if(dp[i][j]>0){
                    int curRemainder = (int) ((long) j * nums[i] % k);
                    //a*b ==> Long (Over int)
                    //==> Cast (a to long) and then multiplying with(b)
                    dp[i+1][curRemainder]+=dp[i][j];
                }
            }
            for (int j = 0; j < k; j++) {
                rs[j]+=dp[i+1][j];
            }
        }
        return rs;
    }

    public static long[] resultArrayRefactor(int[] nums, int k) {
        int n = nums.length;
//        HashMap<Long, Integer> mapCountPrefix=new HashMap<>();
//        long mul=1;
        long[] rs=new long[k];
        int[] dp = new int[k];
        for (int i = 0; i < n; i++) {
            int[] count = new int[k];
            //prefix_val % k == j
            //
            //0
            //1
            //2
            //3
            //4
            //end with j
            //- Use the dynamic programming to solve this problem
            count[nums[i]%k]++;
            for (int j = 0; j < k; j++) {
                if(dp[j]>0){
                    int curRemainder = (int) ((long) j * nums[i] % k);
                    //a*b ==> Long (Over int)
                    //==> Cast (a to long) and then multiplying with(b)
                    count[curRemainder]+=dp[j];
                }
            }
            dp=count;
            for (int j = 0; j < k; j++) {
                rs[j]+=count[j];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an array of positive integers nums), and (a positive integer k).
        //- You are allowed to perform (an operation once) on nums, where in each operation
        // you can remove (any non-overlapping) prefix and suffix from nums such that nums remains (non-empty).
        //- You need to find (the x-value of nums), which is the number of ways to perform this operation
        // so that (the product of the remaining elements) leaves (a remainder of x when divided by k).
        //
        //* Return (an array result of size k) where result[x] is the x-value of nums for (0 <= x <= k - 1).
        //- A prefix of an array is a subarray that starts from the beginning of the array and extends to any point within it.
        //- A suffix of an array is a subarray that starts at any point within the array and extends to the end of the array.
        //- A subarray is a contiguous sequence of elements within an array.
        //* Note that the (prefix and suffix) to be chosen for the operation (can be empty).
        //
        //Example 2:
        //
        //Input: nums = [1,2,4,8,16,32], k = 4
        //
        //Output: [18,1,2,0]
        //
        //Explanation:
        //
        //For x = 0, the only operations that do not result in x = 0 are:
        //Remove the empty prefix and the suffix [4, 8, 16, 32]. nums becomes [1, 2].
        //Remove the empty prefix and the suffix [2, 4, 8, 16, 32]. nums becomes [1].
        //Remove the prefix [1] and the suffix [4, 8, 16, 32]. nums becomes [2].
        //For x = 1, the only possible operation is:
        //Remove the empty prefix and the suffix [2, 4, 8, 16, 32]. nums becomes [1].
        //For x = 2, the possible operations are:
        //Remove the empty prefix and the suffix [4, 8, 16, 32]. nums becomes [1, 2].
        //Remove the prefix [1] and the suffix [4, 8, 16, 32]. nums becomes [2].
        //For x = 3, there is no possible way to perform the operation.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums[i] <= 10^9
        //1 <= nums.length <= 10^5
        //1 <= k <= 5
        //  + Length<=10^5 ==> Time: O(n*k)
        //  + nums[i]<=10^9 ==> Long/ Binary search
        //  + k<=5
        //      + k=1,2,3,4
        //
        //- Brainstorm
        //- Prefix multiplication
        //
        //- k=1,2,3,4
        //
        //- x = 0:
        //  + element[i]%k==0 ==> OK
        //- x = 1:
        //  + mul=y*k+1
        //- x = 2:
        //  + mul=y*k+2
        //- x = 3:
        //  + mul=y*k+3
        //- x = 4:
        //  + mul=y*k+4
        //
        //- For each index:
        //  + Calculate for them
        //- a%k=1
        //  ==> How to determine(a)
        //- (k*x+a)*(k*x+b)
        //
        //- mul%k==i
        //=> mul=(k*x+i)
        //
        //* How to realize this problem as the dynamic programming problem?
        //
        //(x*y)%a
        //= ((x%a)*(y%a))%a
        //Ex:
        //(2*9)%4 = 2 (4*4)
        //= 2*1= 2
        //
        //2*3 = (2%4)*(7%4)
        //==> 14%4 = 2
        //
        //- Each time end with (i):
        //  + We can multiply with (j: 0 ->k-1)
        //      + This is (the previous remainder)
        //
        //* NOTE:
        //a*b ==> Long (Over int)
        //==> Cast (a to long) and then multiplying with(b)
        //
        int[] nums = {1,2,3,4,5};
        int k = 3;
//        int[] nums = {1,2,4,8,16,32};
//        int k = 4;
//        long[] rs= resultArray(nums, k);
        long[] rs= resultArrayRefactor(nums, k);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
    }
}
