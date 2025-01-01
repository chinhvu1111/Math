package E1_daily;

import java.util.*;

public class E193_TakeGiftsFromTheRichestPile {

    public static long pickGifts(int[] gifts, int k) {
        int n=gifts.length;
        PriorityQueue<Integer> maxHeap=new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2-o1;
            }
        });
        //Time: O(n*log(n))
        for (int i = 0; i < n; i++) {
            maxHeap.add(gifts[i]);
        }
        //Time: O(k*log(n))
        for(int i=0;i<k;i++){
            int curVal = maxHeap.poll();
            curVal= (int) Math.sqrt(curVal);
            maxHeap.add(curVal);
        }
        long rs = 0;

        while (!maxHeap.isEmpty()){
            rs+=maxHeap.poll();
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (an integer array gifts) denoting (the number of gifts) in various piles.
        //- Every second, you do the following:
        //  + Choose the pile with (the maximum number of gifts).
        //  + If there is (more than one pile) with (the maximum number of gifts), choose any.
        //  + Leave behind (the floor of the square root) of (the number of gifts) in the pile.
        //      + Take the rest of the gifts.
        //* Return (the number of gifts remaining) after (k seconds).
        //
        //Example 1:
        //
        //Input: gifts = [25,64,9,4,100], k = 4
        //Output: 29
        //Explanation:
        //The gifts are taken in the following way:
        //- In the first second, the last pile is chosen and 10 gifts are left behind.
        //- Then the second pile is chosen and 8 gifts are left behind.
        //- After that the first pile is chosen and 5 gifts are left behind.
        //- Finally, the last pile is chosen again and 3 gifts are left behind.
        //The final remaining gifts are [5,8,9,4,3], so the total number of gifts remaining is 29.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= gifts.length <= 10^3
        //1 <= gifts[i] <= 10^9
        //1 <= k <= 10^3
        //  + k<=10^3
        //  + Length<=10^3
        //  ==> Pre-calculate
        //
        //- Brainstorm
        //Ex
        //gifts = [25,64,9,4,100], k = 4
        //sort(gifts) = [100,64,25,9,4]
        //- m = k/n
        //  + for( 0 -> m%n):
        //      + sqrt(sqrt(100))...
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Time: O((k+n)*log(n))
        //- Space: O(n)
        //
        int[] gifts = {25,64,9,4,100};
        int k = 4;
        System.out.println(pickGifts(gifts, k));
        //
        //#Reference:
        //1537. Get the Maximum Score
        //2549. Count Distinct Numbers on Board
        //1246. Palindrome Removal
    }
}
