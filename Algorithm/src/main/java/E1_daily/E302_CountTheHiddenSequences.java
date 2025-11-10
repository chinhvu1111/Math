package E1_daily;

public class E302_CountTheHiddenSequences {

    public static int numberOfArrays(int[] differences, int lower, int upper) {
        int minSum=0;
        int maxSum=0;
        int sum=0;
        int n=differences.length;

        for (int i = 0; i < n; i++) {
            sum+=differences[i];
            minSum=Math.min(minSum, sum);
            maxSum=Math.max(maxSum, sum);
        }
        int rs=0;

        for(int i=lower;i<=upper;i++){
            int maxVal = i + maxSum-minSum;
            if(maxVal>=i&&maxVal<=upper){
                rs++;
            }
        }
        return rs;
    }

    public static int numberOfArraysRefer(int[] differences, int lower, int upper) {
        int minSum=0;
        int maxSum=0;
        int sum=0;
        int n=differences.length;

        for (int i = 0; i < n; i++) {
            sum+=differences[i];
            minSum=Math.min(minSum, sum);
            maxSum=Math.max(maxSum, sum);
        }
        int maxRange = maxSum-minSum;
        //count(min_val)
        if(maxRange<0){
            return 0;
        }
        int rs=upper-maxRange-lower+1;
        if(rs<0){
            return 0;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed array of n integers differences,
        // which describes the differences between (each pair of consecutive integers) of a hidden sequence of length (n + 1).
        //- More formally, call (the hidden sequence hidden), then we have
        // that differences[i] = hidden[i + 1] - hidden[i].
        //
        //- You are further given two integers (lower and upper) that describe the inclusive range of values [lower, upper]
        // that (the hidden sequence) can contain.
        //+ For example, given differences = [1, -3, 4], lower = 1, upper = 6,
        // the hidden sequence is a sequence of length 4 whose elements are in between 1 and 6 (inclusive).
        //[3, 4, 1, 5] and [4, 5, 2, 6] are possible hidden sequences.
        //[5, 6, 3, 7] is not possible since it contains an element greater than 6.
        //[1, 2, 3, 4] is not possible since the differences are not correct.
        //* Return (the number of ("possible") hidden sequences) there are.
        // If there are no possible sequences, return 0.
        //
        //Example 2:
        //
        //Input: differences = [3,-4,5,1,-2], lower = -4, upper = 5
        //Output: 4
        //Explanation:
        // The possible hidden sequences are:
        //- [-3, 0, -4, 1, 2, 0]
        //- [-2, 1, -3, 2, 3, 1]
        //- [-1, 2, -2, 3, 4, 2]
        //- [0, 3, -1, 4, 5, 3]
        //Thus, we return 4.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == differences.length
        //1 <= n <= 10^5
        //-10^5 <= differences[i] <= 10^5
        //-10^5 <= lower <= upper <= 10^5
        //  + length<= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //- Find (min, max) for each case
        //
        //Example 2:
        //
        //Input: differences = [3,-4,5,1,-2], lower = -4, upper = 5
        //Output: 4
        //
        //- sum = 3-4+5+1-2 = 3
        //  ==> It is not used to determine the min element
        //- prefix sum = [3,-1,4,5,3]
        //
        //- How to determine the min, max value in differences?
        //Example 2:
        //
        //Input: differences = [3,-4,5,1,-2], lower = -4, upper = 5
        //Output: 4
        //arr[0] = x
        //prefix_val = [x, 3+x, x-1, x+4, x+5, x+3]
        //  + min = x-1
        //  + max = x+5
        //- If min = z
        //  + max =z+6
        //  ==> Check valid
        //
        //
        //1.1, Case
        //differences = [-40]
        //lower = -46
        //upper = 53
        //
        //1.2, Optimization
        //- We don't need to loop all elements from (lower to upper)
        //- We have range
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(upper-lower+len(diff)) ==> O(n)
        //
//        int[] differences = {1,-3,4};
//        int lower = 1, upper = 6;
        int[] differences = {-40};
        int lower = -46, upper = 53;
        //
        //#Reference:
        //609. Find Duplicate File in System
        //2532. Time to Cross a Bridge
        //1604. Alert Using Same Key-Card Three or More Times in a One Hour Period
        System.out.println(numberOfArrays(differences, lower, upper));
        System.out.println(numberOfArraysRefer(differences, lower, upper));
    }
}
