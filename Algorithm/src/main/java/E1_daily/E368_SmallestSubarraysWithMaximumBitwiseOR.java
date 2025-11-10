package E1_daily;

import java.util.Arrays;
import java.util.TreeSet;

public class E368_SmallestSubarraysWithMaximumBitwiseOR {
    public static int[] smallestSubarrays(int[] nums) {
        int n=nums.length;
        int max=0;

        for (int i = 0; i < n; i++) {
            max=Math.max(max, nums[i]);
        }
        int size=0;
        while(max!=0){
            max>>=1;
            size++;
        }
        TreeSet<Integer>[] treeSet=new TreeSet[size+1];
        int[] rs=new int[n];

        for(int i=0;i<n;i++){
            int num=nums[i];
            int pos=0;

            while(num!=0) {
                int bit = num & 1;
                if (bit == 1) {
                    if (treeSet[pos] == null) {
                        treeSet[pos] = new TreeSet<>();
                    }
                    treeSet[pos].add(i);
                }
                num = num >> 1;
                pos++;
            }
        }
        //11
        //100
//        for (int i = 0; i < size; i++) {
//            System.out.printf("Index: %s, %s\n", i, treeSet[i]);
//        }
        for (int i = 0; i < n; i++) {
            int minDist=Integer.MIN_VALUE;
            for (int j = 0; j < size; j++) {
                if(treeSet[j]!=null){
                    Integer nearPos = treeSet[j].ceiling(i);
                    if(nearPos==null){
                        continue;
                    }
                    minDist=Math.max(nearPos, minDist);
                }
            }
            rs[i]=(minDist==Integer.MIN_VALUE)?1:minDist-i+1;
        }
        return rs;
    }

    public static int[] smallestSubarraysOptimization(int[] nums) {
        int n = nums.length;
        int[] pos = new int[31];
        Arrays.fill(pos, -1);
        int[] ans = new int[n];
        //right => left
        for (int i = n - 1; i >= 0; --i) {
            int j = i;
            for (int bit = 0; bit < 31; ++bit) {
                if ((nums[i] & (1 << bit)) == 0) {
                    if (pos[bit] != -1) {
                        j = Math.max(j, pos[bit]);
                    }
                } else {
                    pos[bit] = i;
                }
            }
            ans[i] = j - i + 1;
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 0-indexed array nums of length n, consisting of non-negative integers.
        //- For each index (i from 0 to n - 1), you must determine the size of
        // (the minimum sized non-empty subarray of nums) starting at i (inclusive)
        // that has (the maximum possible bitwise OR).
        //- In other words, let (Bi-j) be the bitwise OR of the subarray nums[i...j].
        //- You need to find the smallest subarray starting at i,
        // such that bitwise OR of this subarray is equal to max(Bik) where i <= k <= n - 1.
        //- The bitwise OR of an array is the bitwise OR of all the numbers in it.
        //
        //* Return an integer array answer of size n where answer[i] is
        // the length of the minimum sized subarray starting at i with maximum bitwise OR.
        //  + Đại loại là max size của subarray với min size
        //
        //- A subarray is a contiguous non-empty sequence of elements within an array.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums.length
        //1 <= n <= 10^5
        //0 <= nums[i] <= 10^9
        //  + Time: O(n)/ O(log(nums[i]))
        //
        //* Brainstorm:
        //Example 1:
        //
        //Input: nums = [1,0,2,1,3]
        //Output: [3,3,2,2,1]
        //Explanation:
        //The maximum possible bitwise OR starting at any index is 3.
        //- Starting at index 0, the shortest subarray that yields it is [1,0,2].
        //- Starting at index 1, the shortest subarray that yields the maximum bitwise OR is [0,2,1].
        //- Starting at index 2, the shortest subarray that yields the maximum bitwise OR is [2,1].
        //- Starting at index 3, the shortest subarray that yields the maximum bitwise OR is [1,3].
        //- Starting at index 4, the shortest subarray that yields the maximum bitwise OR is [3].
        //Therefore, we return [3,3,2,2,1].
        //
        //- [1,0,2,1,3]
        //01: 1
        //11: 3
        //11: 3 ==> remove(1) it is still 11=3
        //
        //Ex:
        //a or b or c or d
        //b or c or d or e
        //  ==> (b or c or d)
        //- The bit could be (overlapping) between multiple numbers
        //- [1,0,2,1,3]
        //=>
        //[01,00,10,01,11
        //  + nearest with bit(i) = 1
        //      + max distance of all bit
        //- Hash<bit, treeSet<Index>>
        //==> []treeSet<Index>
        //
        //1.1, Case
        //
        //1.2, Optimization
        //- Rather than using the treeset:
        //  + We can loop from (right => left)
        //      + Use array to store the (latest/smallest) index of each bit
        //* Because we loop from (right -> left):
        //  + (All cache of indices) is (greater or equal) than (current index)
        //
        //1.3, Complexity
        //- Time: O(n*m*log(n)) => O(n*m)
        //- Space: O(n)
//        int[] nums = {1,0,2,1,3};
        int[] nums = {1,2};
//        int[] rs = smallestSubarrays(nums);
        int[] rs = smallestSubarraysOptimization(nums);
        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
    }
}
