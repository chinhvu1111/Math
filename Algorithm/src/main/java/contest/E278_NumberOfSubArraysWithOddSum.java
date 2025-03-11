package contest;

public class E278_NumberOfSubArraysWithOddSum {

    public static int numOfSubarrays(int[] arr) {
        int n=arr.length;
        int prefixBitSet=0;
        int oddNum=0, evenNum=1;
        int rs=0;
        int mod=1_000_000_007;

        for(int i=1;i<=n;i++){
            prefixBitSet+=(arr[i-1]%2);
            //The number of bit is even
            //==> We need to get the collection with odd bit
            //  + We need to remove the collection with "odd" bit
            if(prefixBitSet%2==0){
                //1,1,1(odd_number=2), 1
                //
                //  + 1 <=> empty
                //      + 4(even) - 3(odd)
                //  + 1 <=> 1,1
                //      + 4(even) - 1(odd)
                rs=(rs+oddNum)%mod;
            }else{
                //1,1,1(odd=2), (1,1)(even)
                //
                rs=(rs+evenNum)%mod;
            }
            if(prefixBitSet%2==1){
                oddNum++;
            }else{
                evenNum++;
            }
        }
        return rs;
    }

    public static int numOfSubarraysOptimization(int[] arr) {
        final int MOD = 1_000_000_007;
        int count = 0, prefixSum = 0;
        // evenCount starts as one since the initial sum (0) is even
        int oddCount = 0, evenCount = 1;

        for (int num : arr) {
            prefixSum += num;
            // If current prefix sum is even, add the number of odd subarrays
            if (prefixSum % 2 == 0) {
                count += oddCount;
                evenCount++;
            } else {
                // If current prefix sum is odd, add the number of even
                // subarrays
                count += evenCount;
                oddCount++;
            }

            count %= MOD; // To handle large results
        }

        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of integers arr,
        //* return (the number of subarrays) with (an odd sum).
        //Since the answer can be very large, return it modulo 10^9 + 7.
        //
        //Example 1:
        //
        //Input: arr = [1,3,5]
        //Output: 4
        //Explanation: All subarrays are [[1],[1,3],[1,3,5],[3],[3,5],[5]]
        //All sub-arrays sum are [1,4,9,3,8,5].
        //Odd sums are [1,9,3,5] so the answer is 4.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= arr.length <= 10^5
        //1 <= arr[i] <= 100
        //  + Length<= 10^5 ==> Time: O(n*log(n))
        //
        //- Brainstorm
        //
        //- arr   =   [1,3,5]
        //  arr%2 =   [1,1,1]
        //  prefix =  [1,2,3]
        //  odd_num = [2,2,3]
        //  even_num= [1,2,2]
        //
//        int[] arr = {1,3,5};
        int[] arr = {2,4,6};
        //
        //1.2, Special case
        //1.3, Optimization
        //- Same idea
        //
        //1.4, Complexity
        //- Space: O(n) -> O(1)
        //- Time: O(n)
        //
        //#Reference:
        //2098. Subsequence of Size K With the Largest Even Sum
        System.out.println(numOfSubarrays(arr));
        System.out.println(numOfSubarraysOptimization(arr));
    }
}
