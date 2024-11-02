package E1_daily;

import java.util.Comparator;
import java.util.TreeSet;

public class E143_MinimumNumberOfRemovalsToMakeMountainArray {

//    public static int minimumMountainRemovals(int[] nums) {
//        int n=nums.length;
//        TreeSet<int[]> maxPrefixLeft=new TreeSet<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
//        TreeSet<int[]> maxPrefixRight=new TreeSet<>(new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                return o1[0]-o2[0];
//            }
//        });
//        maxPrefixLeft.add(new int[]{nums[0], 1});
//        maxPrefixRight.add(new int[]{nums[n-1], 1});
//        int[] leftMax=new int[n];
//        int[] rightMax=new int[n];
//        leftMax[0]=1;
//        rightMax[n-1]=1;
//
//        for(int i=1;i<n;i++){
//            int[] leftSearchE = new int[]{nums[i], 1};
//            maxPrefixLeft.remove(leftSearchE);
//            int[] upperValLeft = maxPrefixLeft.floor(leftSearchE);
//            if(upperValLeft==null){
//                leftMax[i]=1;
//            }else{
//                leftMax[i]=upperValLeft[1]+1;
//            }
//            maxPrefixLeft.add(new int[]{nums[i], leftMax[i]});
//            int[] rightSearchE = new int[]{nums[n-i-1], 1};
//            maxPrefixRight.remove(rightSearchE);
//            int[] upperValRight = maxPrefixRight.floor(rightSearchE);
//            if(upperValRight==null){
//                rightMax[n-i-1]=1;
//            }else{
//                rightMax[n-i-1]=upperValRight[1]+1;
//            }
//            maxPrefixRight.add(new int[]{nums[n-i-1], rightMax[n-i-1]});
//        }
//        int maxLength=0;
//        for(int i=0;i<n;i++){
//            if(leftMax[i]>=2&&rightMax[i]>=2){
//                maxLength=Math.max(maxLength, leftMax[i]+rightMax[i]-1);
//            }
//        }
//        return n-maxLength;
//    }

    public static int minimumMountainRemovals(int[] nums) {
        int n=nums.length;
        int[] leftMax=new int[n];
        int[] rightMax=new int[n];
        leftMax[0]=1;
        rightMax[n-1]=1;

        for(int i=1;i<n;i++){
            int maxLeft=1;
            for(int j=i-1;j>=0;j--){
                if(nums[i]>nums[j]){
                    maxLeft=Math.max(leftMax[j]+1, maxLeft);
                }
            }
            leftMax[i]=maxLeft;
        }
        for(int i=n-1;i>=0;i--){
            int maxRight=1;
            for(int j=i+1;j<n;j++){
                if(nums[i]>nums[j]){
                    maxRight=Math.max(rightMax[j]+1, maxRight);
                }
            }
            rightMax[i]=maxRight;
        }
        int maxLength=0;
        for(int i=0;i<n;i++){
            if(leftMax[i]>=2&&rightMax[i]>=2){
                maxLength=Math.max(maxLength, leftMax[i]+rightMax[i]-1);
            }
        }
        return n-maxLength;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You may recall that an array arr is a mountain array if and only if:
        //  + arr.length >= 3
        //- There exists some index i (0-indexed) with 0 < i < arr.length - 1 such that:
        //  + arr[0] < arr[1] < ... < arr[i - 1] < arr[i]
        //  + arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
        //- Given an integer array nums,
        //* return (the minimum number of elements) to remove to make (nums a mountain array).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //3 <= nums.length <= 1000
        //1 <= nums[i] <= 10^9
        //It is guaranteed that you can make a mountain array out of nums.
        //  + Length của num == 1000 ==> Time: O(n^2) có thể được.
        //
        //- Brainstorm
        //
        //Example 2:
        //
        //Input: nums = [2,1,1,5,6,2,3,1]
        //Output: 3
        //Explanation: One solution is to remove the elements at indices 0, 1, and 5, making the array nums = [1,5,6,3,1].
        //                  6
        //                /   \
        //               5     \
        //             /        \     3
        //            3          \  /   \
        //2         /             2      \
        // \      /                       \
        //  1 -- 1                         1
        //- Tức là với mỗi node thì ta tìm:
        //  + Longest left length tăng dần
        //  + Longest right length giảm dần
        //
        //- Tìm longest length incremental (left -> right)
        //- Với nums[i]=x cần:
        //  + Tìm y <x nhưng (longest length)
        //Ex:
        //x = 8
        //+ Cần find y<8:
        //  + 1,2,(4),3,5,(6),[8]
        //  => Ở đây sẽ chọn [6] do length(6) = 4
        //  + Ta có thể chứng minh là nó sẽ ưu tiên chọn thằng max < x + (nearest) được không?
        //      + Cần phải gần nhất vì sẽ có case:
        //  + 1,2,(8),3,5,(6),[9]
        //      + Chọn 6 chứ không chọn 8
        //  + 1,2,(8),3,5,6,(7),(6)[9]
        //      + Chọn 7 chứ không chọn 6 ==> Nearest sai.
        //- Tìm max length mà y<x
        //Ex:
        //1,2,(8),3,5,6,(7),(6)[9]
        //1,2, 3, 3,4,5, 6,  5, 7
        //1,2,3,5,6,6,7,8,9
        //- Ta sẽ dùng thêm 1 treeSet để tính prefix_max:
        //- prefix_max[i] là longest max cho đến index=i
        //1,2,3,5,6,6,7,8,9
        //1,2,3,4,5,5,6,6,7
        //=> TreeSet(val, max length)
//        int[] nums = {2,1,1,5,6,2,3,1};
//        int[] nums = {1,3,1};
//        int[] nums = {1};
//        int[] nums = {9,8,1,7,6,5,4,3,2,1};
        //9,8,1,7,6,5,4,3,2,1
        //Result = 1
        //Expected = 2
        int[] nums = {1,16,84,9,29,71,86,79,72,12};
        //1,16,(84),(9),29,71,86,79,72,12
        //Result = 3
        //Expected = 2
        //- Các case mà có
        //==> Khó
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n^2)
        //
        System.out.println(minimumMountainRemovals(nums));
        //#Reference:
        //852. Peak Index in a Mountain Array
        //941. Valid Mountain Array
        //1095. Find in Mountain Array
    }
}
