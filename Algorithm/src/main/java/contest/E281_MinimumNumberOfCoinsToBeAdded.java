package contest;

import java.util.Arrays;

public class E281_MinimumNumberOfCoinsToBeAdded {

    public static int minimumAddedCoins(int[] coins, int target) {
        Arrays.sort(coins);
        int n=coins.length;
        int rs=0;
        int curMax=0;
        int index=0;

        while (curMax<target){
            if(index<n&&coins[index]<=curMax+1){
                curMax+=coins[index];
                index++;
            }else{
                curMax+=curMax+1;
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array coins), representing (the values of the coins available), and (an integer target).
        //- (An integer x) is obtainable if there exists (a subsequence of coins) that (sums to x).
        //* Return (the minimum number of coins) of any value that need to be added to the array
        // so that (every integer in the range [1, target]) is obtainable.
        //- (A subsequence of an array) is a new non-empty array that is formed from the original array by deleting some (possibly none)
        // of the elements without disturbing the relative positions of the remaining elements.
        //
        //Example 1:
        //
        //Input: coins = [1,4,10], target = 19
        //Output: 2
        //Explanation: We need to add coins 2 and 8. The resulting array will be [1,2,4,8,10].
        //It can be shown that all integers from 1 to 19 are obtainable from the resulting array,
        // and that 2 is the minimum number of coins that need to be added to the array.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= target <= 10^5
        //1 <= coins.length <= 10^5
        //1 <= coins[i] <= target
        //  + Length<=10^5 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Check whether x is obtainable or not
        //  + Dynamic programming is complex
        //
        //Example 1:
        //Input: coins = [1,4,10], target = 19
        //Output: 2
        //[1,19] = [1,2,3,...,19]
        //- Sort array
        //  + [1,4,10]
        //    [1,2,3..19]
        //  ==> Add(2,3) and then adding more
        //  - 5 = 1+4 ==> We don't need to add new element
        //  + Ex:
        //      + We can not calculate x
        //  ==> x is biggest value
        //  x=6 = 1+2+4
        //  + <> if we cannot recreate x ==> Add X to the array
        //
        //- [1,...,x]:
        //  + We can form the [x+1,...,x+x-1] = [x+1,2*x-1]
        //  ==> Add(x+1)
        //      + We can form [x+1,2*x+1]
        //
        //- Cho những giá trị min đầu tiên
        //Ex:
        //cois = [1,10], target=12
        //1,2, cần thêm
        //  ==> [1,2] tính được cho [1,2,3]
        //  + 4 ngoài vùng ==> Cần add thêm 4
        //  [1,2,4] ==> Tính được thêm từ [1,2,3] ==> [1,2,3,4,5,6]
        //      ==> Ta có thể tính đến (4+2)
        //
        int[] coins = {1,1,1};
        int target = 20;
        System.out.println(minimumAddedCoins(coins, target));
    }
}
