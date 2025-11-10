package E1_daily;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E306_CountOfInterestingSubarrays {

    public static long countInterestingSubarrays(List<Integer> nums, int modulo, int k) {
        int n=nums.size();
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        mapCount.put(0, 1);
        int count=0;
        long rs=0;

        for (int i = 0; i < n; i++) {
            if((nums.get(i)%modulo)==k){
                count++;
            }
            //0 <= k < modulo
            //(x-y) = l*module+k
//            int curRemainder = count%modulo;
//            int prev=curRemainder>=k?curRemainder-k:curRemainder+k;
            rs+=(long)mapCount.getOrDefault((count-k)%modulo, 0);
//            System.out.printf("Index: %s, Plus: %s, curRemainder: %s, prev: %s\n", i, mapCount.getOrDefault(prev, 0), curRemainder, prev);
//            System.out.println(i);
//            System.out.println(curRemainder);
//            System.out.println(mapCount);
//            if((count%modulo)==0){
//                mapCount.put(count%modulo, mapCount.getOrDefault(count%modulo, 0)+1);
//            }
            mapCount.put(count%modulo, mapCount.getOrDefault(count%modulo, 0)+1);
        }
        return rs;
    }

    public static long countInterestingSubarraysRefer(
            List<Integer> nums,
            int modulo,
            int k
    ) {
        int n = nums.size();
        HashMap<Integer, Integer> cnt = new HashMap<>();
        long res = 0;
        int prefix = 0;
        cnt.put(0, 1);
        for (int i = 0; i < n; i++) {
            prefix += nums.get(i) % modulo == k ? 1 : 0;
            res += cnt.getOrDefault((prefix - k + modulo) % modulo, 0);
            cnt.put(prefix % modulo, cnt.getOrDefault(prefix % modulo, 0) + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a 0-indexed integer array nums), (an integer modulo), and (an integer k).
        //- Your task is to find (the count of subarrays) that are interesting.
        //- A subarray nums[l..r] is interesting if the following condition holds:
        //  + Let cnt be (the number of indices i) in the range [l, r]
        //      + such that (nums[i] % modulo == k).
        //      Then, (cnt % modulo == k).
        //
        //* Return an integer denoting (the count of ("interesting") subarrays).
        //* Note:
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 1:
        //
        //Input: nums = [3,2,4], modulo = 2, k = 1
        //Output: 3
        //Explanation:
        //- In this example the interesting subarrays are:
        //The subarray nums[0..0] which is [3].
        //- There is only one index, i = 0, in the range [0, 0] that satisfies nums[i] % modulo == k.
        //- Hence, cnt = 1 and cnt % modulo == k.
        //The subarray nums[0..1] which is [3,2].
        //- There is only one index, i = 0, in the range [0, 1] that satisfies nums[i] % modulo == k.
        //- Hence, cnt = 1 and cnt % modulo == k.
        //The subarray nums[0..2] which is [3,2,4].
        //- There is only one index, i = 0, in the range [0, 2] that satisfies nums[i] % modulo == k.
        //- Hence, cnt = 1 and cnt % modulo == k.
        //It can be shown that there are no other interesting subarrays. So, the answer is 3.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //1 <= modulo <= 10^9
        //0 <= k < modulo
        //  + nums.length <= 10^5 ==> Time: (n*k)
        //
        //* Brainstorm:
        //
        //Example 1:
        //
        //Input: nums = [3,2,4], modulo = 2, k = 1
        //Output: 3
        //- Prefix sum
        //nums      = [3,2,4]
        //prefix    = [1,1,1]
        //- HashMap ==> Count module
        //- 3 = 10-7
        //  + 3%2=1
        //- Change a little bit:
        //  + prefix[i]%mod == k
        //      + rs+= count(prefix[i]%mod ==0)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(min(n,modulo))
        //  + It is necessary to use a hash map to store the frequency of each element's modulo result in the array.
        //  There can be at most O(min(n,modulo)) different modulo results, so the required space is O(min(n,modulo)).
        //- Time: O(n)
        //
//        Integer[] nums = {3,2,4};
//        int modulo = 2, k = 1;
//        Integer[] nums = {3,1,9,6};
//        int modulo = 3, k = 0;
//        Integer[] nums = {2,2,5};
//        int modulo = 3, k = 2;
        //5%3 == 2
        //rs+=0
        //Expected: 2
        //Wrong case:
        Integer[] nums = {2,2,1,2};
        //count = [1,2,2,3]
        //  + 3%mod = 0
        //      + 0+2 ==> rs+=2 (2 count==2)
        int modulo = 3, k = 2;
        //Expected: 3
        //[2,1,2]: 1
        //[2,2]: 1
        //==> (count-k)%modulo
        System.out.println(countInterestingSubarrays(Arrays.asList(nums), modulo, k));
        System.out.println(countInterestingSubarraysRefer(Arrays.asList(nums), modulo, k));
    }
}
