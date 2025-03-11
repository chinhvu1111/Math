package contest;

public class E294_MinimumRecolorsToGetKConsecutiveBlackBlocks {

    public static int minimumRecolors(String blocks, int k) {
        int n=blocks.length();
        int[] prefixSum=new int[n+1];
        int rs=0;

        for(int i=1;i<=n;i++){
            if(blocks.charAt(i-1)=='B'){
                prefixSum[i]=prefixSum[i-1]+1;
            }else{
                prefixSum[i]=prefixSum[i-1];
            }
            if(i>=k){
                rs=Math.max(rs, prefixSum[i]-prefixSum[i-k]);
            }
        }
        return k-rs;
    }

    public static int minimumRecolorsRefer(String blocks, int k) {
        int left = 0, numWhites = 0, numRecolors = Integer.MAX_VALUE;

        // Move right pointer
        for (int right = 0; right < blocks.length(); right++) {
            // Increment numWhites if block at right pointer is white
            if (blocks.charAt(right) == 'W') {
                numWhites++;
            }

            // k consecutive elements are found
            if (right - left + 1 == k) {
                // Update minumum
                numRecolors = Math.min(numRecolors, numWhites);

                // Decrement numWhites if block at left pointer is white
                if (blocks.charAt(left) == 'W') numWhites--;

                // Move left pointer
                left++;
            }
        }
        return numRecolors;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed string blocks of length n,
        //  where blocks[i] is either 'W' or 'B',
        // representing the color of (the ith block).
        //  + The characters 'W' and 'B' denote the colors white and black, respectively.
        //- You are also given (an integer k), which is (the desired number of consecutive ("black") blocks).
        //- In (one operation), you can recolor
        //  + a white block such that it becomes a black block.
        //* Return (the minimum number of operations) needed such that there is (at least one occurrence) of (k consecutive black blocks).
        //
        //Example 1:
        //
        //Input: blocks = "WBBWWBBWBW", k = 7
        //Output: 3
        //Explanation:
        //One way to achieve 7 consecutive black blocks is to recolor the 0th, 3rd, and 4th blocks
        //so that blocks = "BBBBBBBWBW".
        //It can be shown that there is no way to achieve 7 consecutive black blocks in less than 3 operations.
        //Therefore, we return 3.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == blocks.length
        //1 <= n <= 100
        //blocks[i] is either 'W' or 'B'.
        //1 <= k <= n
        //
        //- Brainstorm
        //Ex:
        //Input: blocks = "WBBWWBBWBW", k = 7
        //Output: 3
        //- Prefix sum:
        //blocks = "WBBWWBBWBW"
        //- prefix[i]: coun(B) at ith
        //  + rs= Max(prefix[i]-prefix[i-k])
        //
        //1.1, Cases
        //1.2, Optimization
        //- Prefix sum -> Slice window to optimize space from O(n) to O(1)
        //1.3, Complexity
        //- Space: O(n) -> O(1)
        //- Time: O(n)
        //
        String blocks = "WBBWWBBWBW";
        int k = 7;
        System.out.println(minimumRecolors(blocks, k));
        System.out.println(minimumRecolorsRefer(blocks, k));
    }
}
