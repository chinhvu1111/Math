package E1_daily;

import java.util.HashMap;

public class E251_3SumWithMultiplicity {

    public static int threeSumMulti(int[] arr, int target) {
        int n=arr.length;
        int rs=0;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
//        int prevVal=-1;
//        int prevPairNum=0;
        int mod=1_000_000_007;

        for(int i=0;i<n;i++){
            int remainingSum=target-arr[i];
            int curCount=0;

            for(int j=0;j<=remainingSum/2;j++){
                if(j!=remainingSum-j){
                    curCount=(curCount+mapCount.getOrDefault(j,0)*mapCount.getOrDefault(remainingSum-j, 0))%mod;
                }else{
                    int count = mapCount.getOrDefault(j,0);
                    curCount=(curCount+count*(count-1)/2)%mod;
                }
            }
//                System.out.printf("arr[i]: %s, remainingSum: %s, %s\n", arr[i], remainingSum, curCount);
            rs=(rs+curCount)%mod;
//            prevPairNum=curCount;
            mapCount.put(arr[i], mapCount.getOrDefault(arr[i], 0)+1);
//            prevVal=arr[i];
        }
        return rs;
    }

    public static int threeSumMultiReference(int[] A, int target) {
        int MOD = 1_000_000_007;
        long[] count = new long[101];
        for (int x: A)
            count[x]++;

        long ans = 0;

        // All different
        for (int x = 0; x <= 100; ++x)
            for (int y = x+1; y <= 100; ++y) {
                int z = target - x - y;
                if (y < z && z <= 100) {
                    ans += count[x] * count[y] * count[z];
                    ans %= MOD;
                }
            }

        // x == y != z
        for (int x = 0; x <= 100; ++x) {
            int z = target - 2*x;
            if (x < z && z <= 100) {
                ans += count[x] * (count[x] - 1) / 2 * count[z];
                ans %= MOD;
            }
        }

        // x != y == z
        for (int x = 0; x <= 100; ++x) {
            if (target % 2 == x % 2) {
                int y = (target - x) / 2;
                if (x < y && y <= 100) {
                    ans += count[x] * count[y] * (count[y] - 1) / 2;
                    ans %= MOD;
                }
            }
        }

        // x == y == z
        if (target % 3 == 0) {
            int x = target / 3;
            if (0 <= x && x <= 100) {
                ans += count[x] * (count[x] - 1) * (count[x] - 2) / 6;
                ans %= MOD;
            }
        }

        return (int) ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an integer array arr, and an integer target,
        //* Return the number of tuples i, j, k such that i < j < k and arr[i] + arr[j] + arr[k] == target.
        //- As the answer can be very large, return it modulo (10^9 + 7).
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //3 <= arr.length <= 3000
        //0 <= arr[i] <= 100
        //  + We can loop 100 times in the nested loop
        //0 <= target <= 300
        //
        //- Brainstorm
        //- a+b+c = target
        //  + a + b = target - c
        //
        //- Could we use (the prefix sum) as the solution?
        //Example 1:
        //
        //Input: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
        //Output: 20
        //arr = [1,1,2,2,3,3,4,4,(5),5], target = 8
        //  - At (target - 5) = 8-5 = 3
        //  2sum=3 we have:
        //  + (1,2) ==> Loop from 1->100 to count
        //
        //
        //1.1, Special cases
//        int[] arr = {0,0,0};
//        int target = 0;
        //- init = 0
        int[] arr = {3,3,3,0};
        int target = 6;
        //- [3,3,3,0], target = 6
        //  + count[3]=3 ==> [3,3] exists 3 times
        //  ==> rs+=3*(3-1)/2
        //
        //1.2, Optimization
        //- We split the problem into 3 cases:
        //  + All different
        //  + x == y != z
        //  + x != y == z
        //  + x == y == z
        //
        //1.3, Complexity
        //- Space: O(n) => O(w)
        //- Time: O(n^w) => O(n+w^2)
//        int[] arr = {1,1,2,2,3,3,4,4,5,5};
//        int target = 8;
//        int[] arr = {1,1,2,2,3,3,4,4,5,5};
//        int target = 8;
        //
        //#Reference:
        //1170. Compare Strings by Frequency of the Smallest Character
        //1985. Find the Kth Largest Integer in the Array
        //3284. Sum of Consecutive Subarrays
        System.out.println(threeSumMulti(arr, target));
        System.out.println(threeSumMultiReference(arr, target));
    }
}
