package E1_daily;

public class E314_ShortestImpossibleSequenceOfRolls {

    public static int shortestSequence(int[] rolls, int k) {
        return 1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array rolls) of length n and (an integer k).
        //- You roll a k sided dice numbered from (1 to k), (n times),
        //  + where the result of (the ith roll) is rolls[i].
        //* Return (the length of the shortest sequence) of rolls so that there's (no such subsequence) in rolls.
        //- (A sequence of rolls) of (length len) is the result of (rolling a k sided dice len times).
        //  + Return shortest length of rolls such that có tồn tại subsequence (không thể lấy) trong array
        //
        //Example 1:
        //
        //Input: rolls = [4,2,1,2,3,3,2,4,1], k = 4
        //Output: 3
        //Explanation: Every sequence of rolls of length 1, [1], [2], [3], [4], can be taken from rolls.
        //Every sequence of rolls of length 2, [1, 1], [1, 2], ..., [4, 4], can be taken from rolls.
        //The sequence [1, 4, 2] cannot be taken from rolls, so we return 3.
        //Note that there are other sequences that cannot be taken from rolls.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == rolls.length
        //1 <= n <= 10^5
        //1 <= rolls[i] <= k <= 10^5
        //  + n <= 10^5 ==> Time: O(n*k)
        //
        //* Brainstorm:
        //
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //
        //
    }
}
