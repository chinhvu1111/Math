package E1_daily;

import java.util.HashMap;

public class E299_CountTheNumberOfGoodSubarrays {

    public static long countGoodWrong(int[] nums, int k) {
        int n=nums.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int start=0;
        long rs=0;
        int pairNumber=0;
        int end=0;

        while (end<n&&pairNumber<k){
            if(mapCount.containsKey(nums[end])){
                pairNumber+=mapCount.get(nums[end]);
            }
            mapCount.put(nums[end], mapCount.getOrDefault(nums[end], 0)+1);
            end++;
        }
        if(pairNumber>=k){
            rs++;
        }
        System.out.printf("First pair number: %s, end: %s\n", pairNumber, end);
        //
        while (end<n) {
            mapCount.put(nums[end], mapCount.getOrDefault(nums[end], 0)+1);
            if(mapCount.containsKey(nums[end])){
                pairNumber+=mapCount.get(nums[end]);
            }
            int prevStart=start;
            boolean isValid=false;

            while (start<=end&&pairNumber>=k){
                isValid=true;
                //[1,1,1,1]
                //3+2+1
                //[1,1,1]
                //2+1
                if(mapCount.containsKey(nums[start])){
                    pairNumber-=mapCount.get(nums[start])-1;
                }
                if(mapCount.get(nums[start])!=1){
                    mapCount.put(nums[start], mapCount.get(nums[start])-1);
                }else{
                    mapCount.remove(nums[start]);
                }
                start++;
            }
            if(isValid){
                rs+=start-prevStart;
                System.out.printf("end: %s, plus: %s\n", end, start-prevStart);
            }
            end++;
        }
        return rs;
    }

    public static long countGood(int[] nums, int k) {
        int n=nums.length;
        int start=0, end=0;
        int pairNumber=0;
        long rs=0;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        while (end<n&&pairNumber<k){
            if(mapCount.containsKey(nums[end])){
                pairNumber+=mapCount.get(nums[end]);
            }
            mapCount.put(nums[end], mapCount.getOrDefault(nums[end], 0)+1);
            end++;
        }
        //nums = [3,1,4,3,2,(2)[valid],4(end)], k = 2
        //0,1,2(n-1)
        if(pairNumber>=k){
//            end--;
//            System.out.printf("start: %s, end: %s, pairNumber: %s\n", start, end-1, pairNumber);
//            System.out.printf("Plus: %s\n", n-end+1);
            rs+=n-end+1;
        }
//        mapCount.put(nums[end], mapCount.get(nums[end])+1);
//        pairNumber+=mapCount.get(nums[end]);
//        if(mapCount.get(nums[end])==0){
//            mapCount.remove(nums[end]);
//        }
//        start++;
        while (start<=end&&end<=n&&start<n){
            mapCount.put(nums[start], mapCount.get(nums[start])-1);
            pairNumber-=mapCount.get(nums[start]);
            if(mapCount.get(nums[start])==0){
                mapCount.remove(nums[start]);
            }
            //prev_end ==> valid, end ==> valid with prev_end++
//            int prevEnd=end;
            start++;
            while (end<n&&pairNumber<k){
                if(mapCount.containsKey(nums[end])){
                    pairNumber+=mapCount.get(nums[end]);
//                    System.out.printf("End-val: %s, Sub-plus: %s, End: %s\n", nums[end], mapCount.get(nums[end]), end);
                }
                mapCount.put(nums[end], mapCount.getOrDefault(nums[end], 0)+1);
//                prevEnd=end;
                end++;
            }
//            System.out.printf("start: %s, end: %s, pairNumber: %s, remove: %s, val: %s\n",
//                    start, end, pairNumber, mapCount.get(nums[start]), nums[start]);
            if(pairNumber>=k){
//                end=prevEnd;
//                System.out.printf("Plus: %s\n", n-end+1);
                rs+=n-end+1;
            }
//            mapCount.put(nums[end], mapCount.get(nums[end])-1);
//            pairNumber-=mapCount.get(nums[end]);
//            if(mapCount.get(nums[end])==0){
//                mapCount.remove(nums[end]);
//            }
        }
        return rs;
    }

    public static long countGoodRefer(int[] nums, int k) {
        int n = nums.length;
        int same = 0, right = -1;
        HashMap<Integer, Integer> cnt = new HashMap<>();
        long ans = 0;

        for (int left = 0; left < n; ++left) {
            while (same < k && right + 1 < n) {
                ++right;
                same += cnt.getOrDefault(nums[right], 0);
                cnt.put(nums[right], cnt.getOrDefault(nums[right], 0) + 1);
            }
            if (same >= k) {
                ans += n - right;
            }
            //- Minus after that
            cnt.put(nums[left], cnt.get(nums[left]) - 1);
            same -= cnt.get(nums[left]);
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an integer array nums) and (an integer k),
        //* Return (the number of good subarrays of nums).
        //- A subarray arr is good
        //  + if there are (at least k pairs) of indices (i, j) such that (i < j) and (arr[i] == arr[j]).
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //Example 2:
        //
        //Input: nums = [3,1,4,3,2,2,4], k = 2
        //Output: 4
        //Explanation: There are 4 different good subarrays:
        //- [3,1,4,3,2,2] that has 2 pairs.
        //- [3,1,4,3,2,2,4] that has 3 pairs.
        //- [1,4,3,2,2,4] that has 2 pairs.
        //- [4,3,2,2,4] that has 2 pairs.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i], k <= 10^9
        //  + Length<=10^5 ==> Time: O(n*k)
        //  + nums[i], k <= 10^9 ==> Long type
        //
        //* Brainstorm:
        //
        //Example 1:
        //Input: nums = [1,1,1,1,1], k = 10
        //rs=1
        //sum = 4+3+2+1 = n*(n-1)/2 = 4*5/2 = 10
        //Ex:
        //[1,1,1]
        //+ Pair number = 2+1 = 3
        //
        //Example 2:
        //
        //Input: nums = [3,1,4,3,2,2,4], k = 2
        //Output: 4
        //Explanation: There are 4 different good subarrays:
        //- [3,1,4,3,2,2] that has 2 pairs.
        //- [3,1,4,3,2,2,4] that has 3 pairs.
        //- [1,4,3,2,2,4] that has 2 pairs.
        //- [4,3,2,2,4] that has 2 pairs.
        //- Prefix sum
        //
        //Example 2:
        //Input:
        //nums =   [3,1,4,3,2,2,4], k = 2
        //prefix = [0,0,0,1,0,2,3]
        //  + If we stand at [4], we cannot (cut off) (the prefix) to reduce to (3)
        //Ex:
        //1....2.....1.....2...
        //0    0     1     2
        //- How about slide window?
        //
        //Ex:
        //1....2.....1.....2...
        //0    0     1     2
        //- For each index=i, we calculate how many subarray end with (index=i)?
        //
        //nums = [3,1,4,3,2,2,4], k = 2
        //Ex:
        //[1,2,3,4,4,6,6,3,9]
        //First:
        //- start=0, end=6
        //  + start++ ==> rs+=4
        //- How about [3]
        // ==> combine with (index=2)(3)
        //
        //* Main point:
        //- We can not start with (good + cut)
        //  + Because it is difficult to determine (the segment) to cut out
        //- Fix left ==> Go right:
        //  + Not good nearest ==> new idea
        //- Chuyển từ (not good current) ==> (not good khác further)
        //  + Ngay sau (not good) sẽ là (VALID)
        //  ==> VALID
        //
        //nums = [3,1,4,3,2,(2)[valid],4(end)], k = 2
        //0,1,2(n-1)
        //
        //- End:
        //  ==> We need to (add end) at (the first turn) of (the loop)
        //- End could be out of the array:
        //  + (End<=n && start<n)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
//        int[] nums = {1,1,1,1,1};
//        int k = 10;
        int[] nums = {3,1,4,3,2,2,4};
        int k = 2;
//        System.out.println(countGoodWrong(nums, k));
        System.out.println(countGood(nums, k));
        System.out.println(countGoodRefer(nums, k));
    }
}
