package E1_daily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class E300_CountEqualAndDivisiblePairsInAnArray {

    public static int gcd(int a, int b){
        return b==0?a:gcd(b, a%b);
    }

    public static int countPairs(int[] nums, int k) {
        Map<Integer, List<Integer>> mpp = new HashMap<>();
        //Map[val <=> list of indices]
        for (int i = 0; i < nums.length; i++)
            mpp.computeIfAbsent(nums[i], x -> new ArrayList<>()).add(i);

        List<Integer> divisors = new ArrayList<>();
        for (int d = 1; d * d <= k; d++) {
            if (k % d == 0) {
                divisors.add(d);
                if (d != k / d) divisors.add(k / d);
            }
        }
        int pairs=0;
        for (Map.Entry<Integer, List<Integer>> entry: mpp.entrySet()){
            //Same value
            Map<Integer, Integer> curMap=new HashMap<>();
            List<Integer> listIndices=entry.getValue();

            for (int i: listIndices){
                int gcdd = gcd(i, k);
                //Fix (the greater value)
                int need = k/gcdd;
                pairs+=curMap.getOrDefault(need, 0);
                //Count in (the list of divisors)
                for (int d: divisors){
                    if(i%d==0){
                        curMap.put(d, curMap.getOrDefault(d, 0)+1);
                    }
                }
            }
        }
        return pairs;
    }

    public static void main(String[] args) {
        //- Given a 0-indexed integer array nums of length n and an integer k,
        //* return the number of pairs (i, j) where 0 <= i < j < n,
        //such that nums[i] == nums[j] and (i * j) is divisible by k.
        //
        //Example 1:
        //
        //Input: nums = [3,1,2,2,2,1,3], k = 2
        //Output: 4
        //Explanation:
        //There are 4 pairs that meet all the requirements:
        //- nums[0] == nums[6], and 0 * 6 == 0, which is divisible by 2.
        //- nums[2] == nums[3], and 2 * 3 == 6, which is divisible by 2.
        //- nums[2] == nums[4], and 2 * 4 == 8, which is divisible by 2.
        //- nums[3] == nums[4], and 3 * 4 == 12, which is divisible by 2.
        //
        //* Refer:
        //- Description has been showed above.
        //
        int[] nums = {3,1,2,2,2,1,3};
        int k = 2;
        //
        System.out.println(countPairs(nums, k));
    }
}
