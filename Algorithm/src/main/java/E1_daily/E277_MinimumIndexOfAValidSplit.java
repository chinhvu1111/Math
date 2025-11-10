package E1_daily;

import java.util.*;

public class E277_MinimumIndexOfAValidSplit {

    public static int minimumIndex(List<Integer> nums) {
        int n=nums.size();
        TreeSet<int[]> sortCount=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
        int[] prefixMax=new int[n];
        int[] suffixMax=new int[n];
        Arrays.fill(prefixMax, -1);
        Arrays.fill(suffixMax, -1);
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        //nums = [3,3,3,3,7,2,2]
        //nums = [3,3,3,3,2,2,2,2]
        //
        //- At index=i,
        //  + we have (only one element) having the number of appearance is greater than (a half of length) of subarray
        //  ==> We can use max heap
        for (int i = 0; i < n; i++) {
            int e = nums.get(i);
            int curCount = mapCount.getOrDefault(e, 0);
            int[] curElement = new int[]{e, curCount};

            sortCount.remove(curElement);
            mapCount.put(e, curCount+1);
            sortCount.add(new int[]{e, curCount+1});
//            System.out.println(i);
            int[] maxElement = sortCount.last();
            if(maxElement[1]*2>(i+1)){
//                System.out.printf("Max: e=%s, count=%s\n", sortCount.last()[0], sortCount.last()[1]);
//                System.out.printf("Min: e=%s, count=%s\n", sortCount.first()[0], sortCount.first()[1]);
//                System.out.println(mapCount);
                prefixMax[i]=maxElement[0];
            }
//            System.out.printf("i=%s %s\n", i, prefixMax[i]);
        }
        mapCount=new HashMap<>();
        sortCount=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[1]!=o2[1]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });
//        System.out.println();
        for (int i = n-1; i >=0; i--) {
            int e = nums.get(i);
            int curCount = mapCount.getOrDefault(e, 0);
            int[] curElement = new int[]{e, curCount};

            sortCount.remove(curElement);
            mapCount.put(e, curCount+1);
            sortCount.add(new int[]{e, curCount+1});
//            System.out.printf("index=%s, count: %s, len: %s\n",i, curCount+1, n-i);
            int[] maxElement = sortCount.last();
            //0,1,(2),3,4
            if(maxElement[1]*2>(n-i)){
//                System.out.printf("Max: e=%s, count=%s\n", sortCount.last()[0], sortCount.last()[1]);
//                System.out.printf("Min: e=%s, count=%s\n", sortCount.first()[0], sortCount.first()[1]);
                suffixMax[i]=maxElement[0];
            }
//            System.out.printf("i=%s %s\n", i, prefixMax[i]);
        }
        for(int i=0;i<n;i++){
//            System.out.printf("index: %s, prefix: %s, suffix:%s\n", i, prefixMax[i], suffixMax[i]);
            if(prefixMax[i]==-1||suffixMax[i]==-1){
                continue;
            }
            if(i+1<n&&prefixMax[i]==suffixMax[i+1]){
                return i;
            }
        }
        return -1;
    }

    public static int minimumIndexRefer(List<Integer> nums) {
        Map<Integer, Integer> firstMap = new HashMap<>();
        Map<Integer, Integer> secondMap = new HashMap<>();
        int n = nums.size();

        // Add all elements of nums to secondMap
        for (int num : nums) {
            secondMap.put(num, secondMap.getOrDefault(num, 0) + 1);
        }

        for (int index = 0; index < n; index++) {
            // Create split at current index
            int num = nums.get(index);
            secondMap.put(num, secondMap.get(num) - 1);
            firstMap.put(num, firstMap.getOrDefault(num, 0) + 1);

            // Check if valid split
            if (
                    firstMap.get(num) * 2 > index + 1 &&
                            secondMap.get(num) * 2 > n - index - 1
            ) {
                return index;
            }
        }

        // No valid split exists
        return -1;
    }

    public static int minimumIndexRefer1(List<Integer> nums) {
        // Find the majority element
        int x = nums.get(0), count = 0, xCount = 0, n = nums.size();

        for (int num : nums) {
            if (num == x) {
                count++;
            } else {
                count--;
            }
            if (count == 0) {
                x = num;
                count = 1;
            }
        }

        // Count frequency of majority element
        for (int num : nums) {
            if (num == x) {
                xCount++;
            }
        }

        // Check if valid split is possible
        count = 0;
        for (int index = 0; index < n; index++) {
            if (nums.get(index) == x) {
                count++;
            }
            int remainingCount = xCount - count;
            // Check if both left and right partitions satisfy the condition
            if (count * 2 > index + 1 && remainingCount * 2 > n - index - 1) {
                return index;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- An element x of (an integer array arr of length m) is dominant if (more than half the elements of arr) have (a value of x).
        //- You are given (a 0-indexed integer array nums of length n) with (one dominant element).
        //- You can split nums (at an index i) into two arrays nums[0, ..., i] and nums[i + 1, ..., n - 1], but the split is (only valid if):
        //  + 0 <= i < n - 1
        //- nums[0, ..., i], and nums[i + 1, ..., n - 1] have the ("same") (dominant element).
        //- Here, nums[i, ..., j] denotes (the subarray of nums) (starting at index i and ending at index j), both ends being ("inclusive").
        //- Particularly, if (j < i) then nums[i, ..., j] denotes an ("empty subarray").
        //* Return (the minimum index of a valid split).
        // If no valid split exists, return -1.
        //
        //Example 2:
        //
        //Input: nums = [2,1,3,1,1,1,7,1,2,1]
        //Output: 4
        //Explanation: We can split the array at index 4 to obtain arrays [2,1,3,1,1] and [1,7,1,2,1].
        //In array [2,1,3,1,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
        //In array [1,7,1,2,1], element 1 is dominant since it occurs thrice in the array and 3 * 2 > 5.
        //Both [2,1,3,1,1] and [1,7,1,2,1] have the same dominant element as nums, so this is a valid split.
        //It can be shown that index 4 is the minimum index of a valid split.
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10^5
        //1 <= nums[i] <= 10^9
        //nums has ("exactly") (one dominant element).
        //  + length<=10^5 ==> Time: O(n)
        //  + nums[i] <= 10^9 ==> Long
        //
        //* Brainstorm:
        //- Prefix (max count)
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- Mình không cần phải dùng max heap để đánh dấu count
        //- Xét index=i:
        //  + Min index, element[index]=x
        //  ==> Prefix ta sẽ lấy thằng element có (min index == x)
        //      + Vì kiểu gì length is increase ==> (count*2-length) ==> Decrease (bất lợi)
        //  ==> Ta chỉ cần xét (x=element[i]) + check secondMap.get(x) xem count của thằng thứ 2 có ok không là được
        //==> SMART
        //
        Integer[] nums = {2,1,3,1,1,1,7,1,2,1};
//        Integer[] nums = {1,2,1,1};
        //4-1 = 3
        //
        System.out.println(minimumIndex(Arrays.asList(nums)));
        System.out.println(minimumIndexRefer(Arrays.asList(nums)));
        System.out.println(minimumIndexRefer1(Arrays.asList(nums)));
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n) ==> O(n)
        //
        //#Reference:
        //915. Partition Array into Disjoint Intervals
    }
}
