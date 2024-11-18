package E1_bitmanipulation;

public class E19_ShortestSubarrayWithORAtLeastKII_classic {
    static int MAX = 100000;
    static int bitscount = 32;

    // Array to store bit-wise
// prefix count
    static int[][] prefix_count;

    // Function to find the prefix sum
    static void findPrefixCount(int arr[], int n) {

        // Loop for each bit
        for (int i = 0; i < bitscount; i++) {
            // Loop to find prefix count
            prefix_count[i][0] = ((arr[0] >> i) & 1);
            for (int j = 1; j < n; j++) {
                prefix_count[i][j] = ((arr[j] >> i) & 1);
                prefix_count[i][j] += prefix_count[i][j - 1];
            }
        }
    }

    // Function to answer query
    static int rangeOr(int l, int r) {

        // To store the answer
        int ans = 0;

        // Loop for each bit
        for (int i = 0; i < bitscount; i++) {
            // To store the number of variables
            // with ith bit set
            int x;
            if (l == 0)
                x = prefix_count[i][r];
            else
                x = prefix_count[i][r] - prefix_count[i][l - 1];

            // Condition for ith bit
            // of answer to be set
            if (x != 0)
                ans = (ans | (1 << i));
        }

        return ans;
    }

    public static int minimumSubarrayLengthTLE(int[] nums, int k) {
        prefix_count = new int[bitscount][MAX];
        int n = nums.length;
        findPrefixCount(nums, n);

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j + i - 1 < n; j++) {
                int l = i + j - 1;
                if (rangeOr(j, l) >= k) {
                    return i;
                }
            }
        }
        return -1;
    }

    public static void addCurNumToWindow(int curNumber, int[] curBits, int addBit) {
        int count = 0;
        while (curNumber != 0) {
            int curBit = curNumber & 1;
            if (curBit == 1) {
                curBits[count] += addBit;
            }
            curNumber = curNumber >> 1;
            count++;
        }
    }

    public static int convertBinaryToDecimal(int[] curBits) {
        int curVal = 0;
        int mul2 = 1;

        for (int i = 0; i < curBits.length; i++) {
            if (curBits[i] != 0) {
                curVal = curVal + mul2;
            }
            mul2 *= 2;
        }
        return curVal;
    }

    public static int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] curBits = new int[32];
        int start = 0, end = 0;
        int rs = Integer.MAX_VALUE;

        //Time: O(n)
        while (end < n) {
            //Time: O(32)
            addCurNumToWindow(nums[end], curBits, 1);

            while (start <= end && convertBinaryToDecimal(curBits) >= k) {
                rs = Math.min(rs, end - start + 1);
                addCurNumToWindow(nums[start], curBits, -1);
                start++;
            }
            end++;
        }
        return rs == Integer.MAX_VALUE ? -1 : rs;
    }

    private static boolean hasValidSubarray(
            int[] nums,
            int targetSum,
            int windowSize
    ) {
        int arrayLength = nums.length;
        int[] bitCounts = new int[32]; // Tracks count of set bits at each position

        // Sliding window approach
        for (int right = 0; right < arrayLength; right++) {
            // Add current number to window
            addCurNumToWindow(nums[right], bitCounts, 1);

            // Remove leftmost number if window exceeds size
            if (right >= windowSize) {
                addCurNumToWindow(nums[right], bitCounts, -1);
            }

            // Check if current window is valid
            if (
                    right >= windowSize - 1 &&
                            convertBinaryToDecimal(bitCounts) >= targetSum
            ) {
                return true;
            }
        }
        return false;
    }

    public static int minimumSubarrayLengthBinarySearch(int[] nums, int k) {
        int left = 1;
        int right = nums.length;
        int minLength = -1;

        // Binary search on the length of subarray
        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (hasValidSubarray(nums, k, mid)) {
                minLength = mid;
                right = mid - 1; // Try to find smaller length
            } else {
                left = mid + 1; // Try larger length
            }
        }

        return minLength;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are (given an array nums of non-negative integers) and (an integer k).
        //- An array is called (special)
        //  + if (the bitwise OR of all of its elements) is (at least k).
        //* Return (the length) of (the shortest special non-empty subarray of nums),
        //  + or return -1 if (no special subarray exists).
        //- Tìm (shortest subarray) có all or >=k
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 2 * 10^5
        //0 <= nums[i] <= 10^9
        //0 <= k <= 10^9
        //  + len <= 2*10^5 ==> Time: O(n)
        //  + nums[i] <= 10^9 ==> Long
        //  + nums[i]>=0:
        //      + We can do this solution with 32 bits (max)
        //
        //- Brainstorm
        //- Shortest array has OR Value >= k
        //Ex:
        //k = 10101
        //val = 11101
        //val > k
        //11101 > 10101
        //  + Having more 1 bit
        //
        //- For the brute force approach:
        //  + It takes O(n^2)
        //  ==> Reduce
        //- For the problem that request to find the longest or shortest length of subarray:
        //  + Slice window
        //Ex:
        //a or b <= a or b or c
        //  + or c that can make OR val (EQUAL or BIGGER)
        //
        //Example 2:
        //Input: nums = [2,1,8], k = 10
        //Output: 3
        //
        //- If we do this stuff with slice window, we need to remove bit of the leftmost element out of the current value:
        //  + If the current value and the leftmost value don't have the same 1 bit --> Remove
        //
        //Ex:
        //a or b
        //  + if a or b <k
        //  => or c
        //a or b or c >= k
        //  + Because if remove(c)
        //  ==> (a or b <k), we can not get this
        //  + We need to remove(a) util we get the value >= k
        //
        //101101
        //or
        //001001
        //or
        //001101
        //=
        //101101
        //
        //- Nếu mình làm ntn:
        //  + a or b or c
        //  Muốn remove(a) ra khỏi cur_value như bình thường nếu remove các bit giống nhau:
        //      + 101101 remove(101101) ==> 000000
        //      => Ntn sẽ không đúng:
        //      001001 or 001001 or 001101 = 001101
        //- Ở đây ta không OR bình thường các số được:
        //
        //** KINH NGHIỆM
        //* Main point:
        //- Tính prefix count các bit tại các (vị trí ith)
        //  + Count[i]==0: bit ==0
        //  + Count[i]>=1: bit ==1
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //2. Binary search
        //2.0,
        //- We just search the min length between 1 and n
        //  + mid = low + (high-low)/2
        //- For each mid, we check whether we find the subarray with (OR value) or not:
        //  + Found: high=mid-1
        //      + rs=mid
        //  + Not found: low=mid+1
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(log(n))
        //- Time: O(n*log(n))
        //
        int x = 1 | 2 | 5 | 12 | 3;
        System.out.println(x);
        int y = 1 | 2;
        System.out.println(x ^ y);
        System.out.println(5 | 12 | 3);
        //1101
        //1010
        //1111
        //1 or 0 = 1
        //+ 1 xor 1 = 0
        //0 or 1 = 1
        //+ 0 xor 1 = 1
        //1 or 1 = 1
        //+ 1 xor 1 = 0 ==> Wrong
        int[] nums = {2, 1, 8};
        int k = 10;
        System.out.println("==================RS==================");
        System.out.println(minimumSubarrayLengthTLE(nums, k));
        System.out.println(minimumSubarrayLength(nums, k));
        System.out.println(minimumSubarrayLengthBinarySearch(nums, k));
    }
}
