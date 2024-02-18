package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E107_LongestArithmeticSubsequenceOfGivenDifference {

    public static int longestSubsequence(int[] arr, int difference) {
        HashMap<Integer, Integer> valToLength=new HashMap<>();
        int n=arr.length;
        int rs=0;

        for (int j : arr) {
            int prevVal = j - difference;
            int curVal;
            if (valToLength.containsKey(prevVal)) {
                curVal = valToLength.get(prevVal) + 1;
            } else {
                curVal = 1;
            }
            if (!valToLength.containsKey(j) || valToLength.get(j) < curVal) {
                valToLength.put(j, curVal);
            }
            rs = Math.max(rs, curVal);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an integer array arr and an integer difference
        //* Return the length of the longest subsequence in arr which is an arithmetic sequence such
        // that the difference between adjacent elements in the subsequence equals (difference).
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= arr.length <= 10^5
        //-10^4 <= arr[i], difference <= 10^4
        //--> Length của arr khá lớn
        //--> O(N) / O(k*N)
        //
        //- Brainstorm
        //Ex:
        //Input: arr = [1,5,7,8,5,3,4,2,1], difference = -2
        //Output: 4
        //Explanation: The longest arithmetic subsequence is [7,5,3,1].
        //
        //- Giả sử dp[i] : là chiều dài dài nhất của subsequence nếu end with (value=i)
        //
        //
        //1.2, Optimization
        //1.3, Complexity
        //- N is the number of elements of array
        //- Space : O(n)
        //- Time : O(n)
//        int[] arr={1,5,7,8,5,3,4,2,1};
//        int diff=-2;
//        int[] arr={1,3,5,7};
//        int diff=1;
//        int[] arr={1,2,3,4};
//        int diff=1;
        int[] arr={1};
        int diff=1;
        System.out.println(longestSubsequence(arr, diff));
    }
}
