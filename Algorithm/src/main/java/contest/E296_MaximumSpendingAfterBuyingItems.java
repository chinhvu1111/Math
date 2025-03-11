package contest;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E296_MaximumSpendingAfterBuyingItems {

    public static long maxSpending(int[][] values) {
        int n=values.length;
        int m=values[0].length;
        PriorityQueue<Integer> minHeap=new PriorityQueue<>();

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                minHeap.add(values[i][j]);
            }
        }
        long rs=0;
        int count=1;
        while (!minHeap.isEmpty()) {
            rs+=(long)minHeap.poll()*count;
            count++;
        }

        return rs;
    }

    public static long maxSpendingRefer(int[][] values) {
        int n=values.length;
        int m=values[0].length;
        int[] sortArr=new int[n*m];
        int index=0;

        for(int i=0;i<n;i++){
            for (int j = 0; j < m; j++) {
                sortArr[index++]=values[i][j];
            }
        }
        long rs=0;
        Arrays.sort(sortArr);

        for (int i = 0; i < n*m; i++) {
            rs+= (long) sortArr[i] *(i+1);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed m * n integer matrix values,
        //  representing (the values of m * n different items) in (m different shops).
        //- Each shop has (n items)
        //  + where the (jth item) in the (ith shop) has a value of values[i][j].
        //- Additionally, the items in the (ith shop) are sorted in (non-increasing order of value).
        //  + That is, values[i][j] >= values[i][j + 1] for all 0 <= j < n - 1.
        //- On each day, you would like to buy (a single item) from (one of the shops).
        //- Specifically, On the (dth day) you can:
        //  + Pick (any shop i).
        //  + Buy the rightmost "available" (item j) for the price of (values[i][j] * d).
        //  That is, find (the greatest index j) such that (item j) was never bought before, and buy it for the price of (values[i][j] * d).
        //* Note that (all items) are pairwise different.
        //- For example, if you have bought (item 0) from (shop 1),
        // you can still buy (item 0) from any other shop.
        //* Return (the maximum amount of money) that can be spent on buying all (m * n products).
        //
        //Example 1:
        //
        //Input:
        //values = [
        // [8,5,2], shop-0
        // [6,4,1], shop-1
        // [9,7,3]  shop-2
        // ]
        //Output: 285
        //Explanation: On the first day, we buy product 2 from shop 1 for a price of values[1][2] * 1 = 1.
        //On the second day, we buy product 2 from shop 0 for a price of values[0][2] * 2 = 4.
        //On the third day, we buy product 2 from shop 2 for a price of values[2][2] * 3 = 9.
        //On the fourth day, we buy product 1 from shop 1 for a price of values[1][1] * 4 = 16.
        //On the fifth day, we buy product 1 from shop 0 for a price of values[0][1] * 5 = 25.
        //On the sixth day, we buy product 0 from shop 1 for a price of values[1][0] * 6 = 36.
        //On the seventh day, we buy product 1 from shop 2 for a price of values[2][1] * 7 = 49.
        //On the eighth day, we buy product 0 from shop 0 for a price of values[0][0] * 8 = 64.
        //On the ninth day, we buy product 0 from shop 2 for a price of values[2][0] * 9 = 81.
        //Hence, our total spending is equal to 285.
        //It can be shown that 285 is the maximum amount of money that can be spent buying all m * n products.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= m == values.length <= 10
        //1 <= n == values[i].length <= 10^4
        //1 <= values[i][j] <= 10^6
        //values[i] are sorted in non-increasing order.
        //  + Length<=10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //Example 1:
        //
        //Input:
        //values = [
        // [8,5,2], shop-0
        // [6,4,1], shop-1
        // [9,7,3]  shop-2
        // ]
        //
        //
        //1.1, Cases
        //
        //1.2, Optimize
        //
        //
        //1.3, Complexity
        //
        //
    }
}
