package contest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class E253_MaximumCoinsFromKConsecutiveBags {

    public static int maxCoinsWrong(int[][] coins, int k) {
        // Step 1: Use a TreeMap to store coin additions and removals at each position
        TreeMap<Integer, Long> map = new TreeMap<>();

        // Step 2: Add coins at the start and subtract coins just after the end of each segment
        for (int[] coin : coins) {
            int li = coin[0];
            int ri = coin[1];
            int ci = coin[2];

            // Add coins at the start of the range
            map.put(li, map.getOrDefault(li, 0L) + ci);

            // Subtract coins just after the end of the range
            map.put(ri + 1, map.getOrDefault(ri + 1, 0L) - ci);
        }

        // Step 3: Now, we will slide over the map and calculate the sum of coins in `k` consecutive bags
        int maxCoins = 0;
        int currentCoins = 0;
        int windowStart = -1;  // We will keep track of the starting position of our window

        // Step 4: Traverse the TreeMap to simulate the sliding window
        for (Map.Entry<Integer, Long> entry : map.entrySet()) {
            int position = entry.getKey();
            Long coinsAtPosition = entry.getValue();

            // Add coins at the current position
            currentCoins += coinsAtPosition;

            // Calculate the length of the window
            if (windowStart == -1) {
                // Start the window
                windowStart = position;
            }

            // Check if the window is at least `k` units wide
            if (position - windowStart + 1 >= k) {
                // If the window has reached size k, calculate the maximum sum of coins
                maxCoins = Math.max(maxCoins, currentCoins);

                // Slide the window: remove coins at the window's start
                currentCoins -= map.get(windowStart);
                windowStart = map.higherKey(windowStart);  // Move the window start to the next key
            }
        }

        return maxCoins;
    }

    public static long maximumCoins(int[][] coins, int k) {
        int n=coins.length;
        TreeMap<Integer, Long> mapSortX = new TreeMap<>();
        for (int[] coin : coins) {
            int li = coin[0];
            int ri = coin[1];
            int ci = coin[2];

            mapSortX.put(li, mapSortX.getOrDefault(li, 0L) + ci);
            mapSortX.put(ri + 1, mapSortX.getOrDefault(ri + 1, 0L) - ci);
        }

        int maxCoins = 0;
        int currentCoins = 0;
        int windowStart = -1;

        for(Map.Entry<Integer, Long> entry: mapSortX.entrySet()){
            int position = entry.getKey();
            Long coinsAtPosition = entry.getValue();

            // Add coins at the current position
            currentCoins += coinsAtPosition;

            // Calculate the length of the window
            if (windowStart == -1) {
                // Start the window
                windowStart = position;
            }

            // Check if the window is at least `k` units wide
            if (position - windowStart + 1 >= k) {
                // If the window has reached size k, calculate the maximum sum of coins
                maxCoins = Math.max(maxCoins, currentCoins);

                // Slide the window: remove coins at the window's start
                currentCoins -= mapSortX.get(windowStart);
                windowStart = mapSortX.higherKey(windowStart);  // Move the window start to the next key
            }
        }

        return 0L;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (an infinite amount of bags) on a number line, one bag for each coordinate. Some of these bags contain coins.
        //- You are given a 2D array coins, where coins[i] = [li, ri, ci] denotes that every bag from li to ri contains ci coins.
        //- The segments that coins contain are non-overlapping.
        //- You are also given an integer k.
        //* Return (the maximum amount of coins) you can obtain by (collecting k ("consecutive") bags).
        //
        //Example 1:
        //
        //Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4
        //Output: 10
        //Explanation:
        //Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= coins.length <= 10^5
        //1 <= k <= 10^9
        //coins[i] == [li, ri, ci]
        //1 <= li <= ri <= 10^9
        //1 <= ci <= 1000
        //The given segments are (non-overlapping).
        //  + length<=10^5 ==> Time: O(n)
        //  + 1 <= li <= ri <= 10^9 ==> Time: O(log(k))
        //
        //- Brainstorm
        //Ex:
        //Input: coins = [[8,10,1],[1,3,2],[5,6,4]], k = 4
        //Output: 10
        //1,2,3,4,5,6,7,8,9,10,11
        //2,2,2,0,4,4,0,1,1,1, 0
        //  + Selecting bags at positions [3, 4, 5, 6] gives the maximum number of coins: 2 + 0 + 4 + 4 = 10.
        //
        //- sort(coins) = [[1,3,2],[5,6,4],[8,10,1]], k = 4
        //- Do we have any cases:
        //  + That we need to stand at (the middle of the specific range) to get the max coins?
        //  => If we have the range is 3 times longer than the first array
        //      + We need to remove the array from left to right
        //Ex:
        //[[0,2,1],[3,5,10],[6,9,3],[9,100,5]]
        //  + 0,2,(3,5,6,9,9,12)
        //
        //- Brute force
        //
        int[][] coins = {{8,10,1},{1,3,2},{5,6,4}};
        //[[1,3,2],[5,6,4],[8,10,1]], k = 4
        //2+2+2+4 = 10
        //
        //* ISSUE:
        //- Bài này mình không chứng minh được mệnh đề
        //==> Cần chứng minh được + tìm cách để code nhanh
        //
        //
        int k = 4;
        System.out.println(maxCoinsWrong(coins, k));
    }
}
