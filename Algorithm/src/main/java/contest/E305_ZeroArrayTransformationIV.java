package contest;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E305_ZeroArrayTransformationIV {

    public static boolean isAllZero(int[] nums, int[][] queries, int index){
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int[] transformedArr=new int[n+1];

        for(int i=0;i<=index;i++){
            prefixSum[queries[i][0]]-=queries[i][2];
            prefixSum[queries[i][1]+1]+=queries[i][2];
        }
        HashSet<Integer> exists=new HashSet<>();
        for(int i=0;i<n;i++){
            if(i!=0){
                prefixSum[i]+=prefixSum[i-1];
            }
            transformedArr[i]=prefixSum[i]+nums[i];
            if(transformedArr[i]!=0){
                if(!exists.contains(transformedArr[i])){
                    return false;
                }
            }
            exists.add(prefixSum[i]);
        }
        return true;
    }

    public static int minZeroArrayWrong(int[] nums, int[][] queries) {
        int m=queries.length;
        int low=0, high=m-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
//            System.out.println(mid);
            if(isAllZero(nums, queries, mid)){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs==-1?rs: rs+1;
    }

    public static int minZeroArray(int[] nums, int[][] queries) {
        int n=nums.length;
        int m=queries.length;
        int rs=-1;

        //Time: O(n)
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                continue;
            }
            List<int[]> listInput=new ArrayList<>();

            //Time: O(m)
            for(int j=0;j<m;j++){
                if(i<=queries[j][1]&&i>=queries[j][0]){
                    listInput.add(new int[]{j, queries[j][2]});
                }
            }
            int l=listInput.size();
            boolean[][] dp=new boolean[l][nums[i]+1];
            int curRs=Integer.MAX_VALUE;

            if(!listInput.isEmpty()&&listInput.get(0)[1]<=nums[i]){
                dp[0][listInput.get(0)[1]]=true;
                if(listInput.get(0)[1]==nums[i]){
//                    System.out.println("First valid");
                    curRs=listInput.get(0)[0];
//                    rs=Math.max(curRs, rs);
//                    System.out.println(nums[i]);
//                    continue;
                }
            }
//            System.out.println(nums[i]);
//            System.out.println(listInput);
//            System.out.println(i);
//            for (int j = 0; j < listInput.size(); j++) {
//                System.out.printf("Index: %s, val: %s, ", listInput.get(j)[0], listInput.get(j)[1]);
//            }
//            System.out.println();
            //Time: O(m)
            for(int j=1;j<l&&curRs==Integer.MAX_VALUE;j++){
                boolean isValid=false;

                if(listInput.get(j)[1]<=nums[i]){
                    dp[j][listInput.get(j)[1]]=true;
                    if(listInput.get(j)[1]==nums[i]){
                        curRs=listInput.get(j)[0];
//                        System.out.println(nums[i]);
                        break;
                    }
                }
                //Time: O(10)
                for(int k=1;k<=nums[i];k++){
                    if(dp[j-1][k]){
                        dp[j][k] = dp[j-1][k];
                    }
                }
                for(int k=1;k<=nums[i];k++){
                    if(dp[j-1][k]&&k+listInput.get(j)[1]<=nums[i]){
                       dp[j][k+listInput.get(j)[1]] = true;
//                       System.out.printf("%s %s\n",nums[i], k+listInput.get(j));
                       if(k+listInput.get(j)[1]==nums[i]){
                           curRs=listInput.get(j)[0];
                           isValid=true;
//                           System.out.printf("Valid: %s\n",nums[i]);
                       }
                    }
                    if(isValid){
                        break;
                    }
                }
                if(isValid){
                    break;
                }
            }
            rs=Math.max(curRs, rs);
//            System.out.printf("Valid: %s, CurRs: %s\n",nums[i], curRs);
        }
        return rs==Integer.MAX_VALUE?-1:rs+1;
    }

    public static int minZeroArrayRefactor(int[] nums, int[][] queries) {
        int n=nums.length;
        int m=queries.length;
        int rs=-1;

        //Time: O(n)
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                continue;
            }
            List<int[]> listInput=new ArrayList<>();

            //Time: O(m)
            for(int j=0;j<m;j++){
                if(i<=queries[j][1]&&i>=queries[j][0]){
                    listInput.add(new int[]{j, queries[j][2]});
                }
            }
            int l=listInput.size();
            boolean[][] dp=new boolean[l][nums[i]+1];
            int curRs=Integer.MAX_VALUE;
            //Time: O(m)
            for(int j=0;j<l&&curRs==Integer.MAX_VALUE;j++){

                if(listInput.get(j)[1]<=nums[i]){
                    dp[j][listInput.get(j)[1]]=true;
                    if(listInput.get(j)[1]==nums[i]){
                        curRs=listInput.get(j)[0];
                        continue;
                    }
                }
                //Time: O(max_num)
                for(int k=1;k<=nums[i];k++){
                    if(j>=1&&dp[j-1][k]){
                        dp[j][k] = dp[j-1][k];
                    }
                }
                for(int k=1;k<=nums[i]&&curRs==Integer.MAX_VALUE;k++){
                    if(j>=1&&dp[j-1][k]&&k+listInput.get(j)[1]<=nums[i]){
                        dp[j][k+listInput.get(j)[1]] = true;
                        if(k+listInput.get(j)[1]==nums[i]){
                            curRs=listInput.get(j)[0];
                        }
                    }
                }
            }
            rs=Math.max(curRs, rs);
        }
        return rs==Integer.MAX_VALUE?-1:rs+1;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri, vali].
        //- Each queries[i] represents the following action on nums:
        //  + Select a subset of indices in the range [li, ri] from nums.
        //  + Decrement the value at each selected index by exactly vali.
        //- A Zero Array is an array with all its elements equal to 0.
        //* Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence,
        // nums becomes a Zero Array. If no such k exists, return -1.
        //* A subset of an array is a selection of elements (possibly none) of the array.
        //
        //Example 1:
        //Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
        //Output: 2
        //
        //Explanation:
        //
        //For query 0 (l = 0, r = 2, val = 1):
        //Decrement the values at indices [0, 2] by 1.
        //The array will become [1, 0, 1].
        //For query 1 (l = 0, r = 2, val = 1):
        //Decrement the values at indices [0, 2] by 1.
        //The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 10
        //0 <= nums[i] <= 1000
        //1 <= queries.length <= 1000
        //queries[i] = [li, ri, vali]
        //0 <= li <= ri < nums.length
        //1 <= vali <= 10
        //  + Length of queries <=1000 ==> Time: O(n^2)
        //
        //- Brainstorm
        //
        //Ex:
        //Input: nums = [1,2,3,2,1], queries = [[0,1,1],[1,2,1],[2,3,2],[3,4,1],[4,4,1]]
        //Output: 4
        //(0--1)
        //   (1--2)
        //      (2--3)
        //==> Prefix sum + sweep line
        //
        //- Binary search + check
        //
        //
//        int[] nums = {2,0,2};
//        int[][] queries = {{0,2,1},{0,2,1},{1,1,3}};
//        int[] nums = {1,2,3,2,1};
//        //(0--1)
//        //   (1--2)
//        //      (2--3)
//        //         (3--4)
//        //            (4)
//        int[][] queries = {{0,1,1},{1,2,1},{2,3,2},{3,4,1},{4,4,1}};
//        int[] nums = {2};
//        //(0--1)
//        //   (1--2)
//        //      (2--3)
//        //         (3--4)
//        //            (4)
//        int[][] queries = {{0,0,6},{0,0,2},{0,0,9},{0,0,5},{0,0,10}};
        //Output: 1
        //Expected: 2
        //6 > 2 ==> cannot subtract
        //
        //(4,7,3) ==> (0,2,5),(0,2,4),(0,2,3)
        //  => rs=3
        //(0------4)
        //  (1---3)
        //     (2------5)
        //
        //- [-1,3,4]
        //  + ==> nums[i] = 0
        //
        //- We can not use sweep line
        //==> Use dynamic programming
        //
        //- We should find (the combination of queries[i][2]) such that (sum of them) is equal to X
        //- For each element in nums:
        //  + Check whether the current number can make by multiple numbers or not
        //  ==> min length
        //  ==> Max of min
        //- Sub problem:
        //  + Given array, check whether X could be sum of subarray with min length
        //Ex:
        //x=10
        //arr= [1,3,7,2,9]
        //10 = 1+9
        //10 = 3+7
        //- For each element, we can:
        //  + Choose
        //  + Ignore
        //* x <=10
        //==> Không cần xét all sum
        //- For e in arr:
        //  + Check i (1 -> x):
        //      + if(prev[i-1][i] && 1+e <=x):
        //          + prev[i][1+e] = true
        //  + Check if prev[i][x] return
        //* Solution:
        //  + Add all valid indices for each number in array to the list
        //  + For each element ==> Using dynamic programming to get min index
        //
        //1.1, Cases
        //
//        int[] nums = {4,7,3};
//        int[][] queries = {{0,2,5},{0,2,4},{0,2,3}};
        //x=7
        //arr = [5,4,3]
//        int[] nums = {2,0,2};
//        int[][] queries = {{0,2,1},{0,2,1},{1,1,3}};
        int[] nums = {1,2,3,2,1};
        int[][] queries = {{0,1,1},{1,2,1},{2,3,2},{3,4,1},{4,4,1}};
        //
//        System.out.println(isAllZero(nums, queries, 2));
//        System.out.println(minZeroArrayWrong(nums, queries));
        System.out.println(minZeroArray(nums, queries));
        System.out.println(minZeroArrayRefactor(nums, queries));
        //
        //1.2, Optimization
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n*m*max_num)
        //
        //
    }
}
