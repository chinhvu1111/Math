package E1_daily;

import java.util.Arrays;

public class E288_MaximumNumberOfCoinsYouCanGet {

    public static int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int n=piles.length;
        int low=0, high=n-1;
        int rs=0;
        //0,1,2
        while (low<high&&high>=1){
            rs+=piles[high-1];
//            System.out.println(rs);
            low++;
            high-=2;
        }
        return rs;
    }

    public static int maxCoinsRefer(int[] piles) {
        Arrays.sort(piles);
        int ans = 0;

        for (int i = piles.length / 3; i < piles.length; i += 2) {
            ans += piles[i];
        }

        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (3n piles of coins) of varying size,
        //  you and your friends will take (piles of coins) as follows:
        //  + In each step, you will choose (any 3 piles of coins) (not necessarily consecutive).
        //  + Of your choice, Alice will pick the pile with (the maximum number of coins).
        //  + You will pick (the next pile) with (the maximum number of coins).
        //  + Your friend Bob will pick (the last pile).
        //  + Repeat until there are (no more) piles of coins.
        //- Given an array of integers piles where piles[i] is the number of coins in (the ith pile).
        //* Return (the maximum number of coins) that you can have.
        //
        //- Choose (the triple) in sequence
        //
        //Example 1:
        //
        //Input: piles = [2,4,1,2,7,8]
        //Output: 9
        //Explanation: Choose the triplet (2, 7, 8), Alice Pick the pile with 8 coins,
        // you the pile with 7 coins and Bob the last one.
        //Choose the triplet (1, 2, 4), Alice Pick the pile with 4 coins,
        // you the pile with 2 coins and Bob the last one.
        //The maximum number of coins which you can have are: 7 + 2 = 9.
        //On the other hand if we choose this arrangement (1, 2, 8), (2, 4, 7)
        // you only get 2 + 4 = 6 coins which is not optimal.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= piles.length <= 10^5
        //piles.length % 3 == 0
        //1 <= piles[i] <= 10^4
        //  + length <= 10^5 => Time: O(n)
        //
        //* Brainstorm:
        //
        //Ex:
        //Input: piles = [2,4,1,2,7,8]
        //Output: 9
        //- As max as possible
        //  ==>
        //- Sort[1,2,2,4,7,8]
        //  + [7,2]
        //  + [4,2]
        //  ==> as max as ==> Get index as max as possible
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
        //#Reference:
        //2718. Sum of Matrix After Queries
        //3012. Minimize Length of Array Using Operations
        //3239. Minimum Number of Flips to Make Binary Grid Palindromic I
        int[] piles = {2,4,1,2,7,8};
        System.out.println(maxCoins(piles));
        System.out.println(maxCoinsRefer(piles));
    }
}
