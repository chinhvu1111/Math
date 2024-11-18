package contest;

public class E217_ZeroArrayTransformationI {

    public static boolean isZeroArray(int[] nums, int[][] queries) {
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int[] rs=new int[n+1];

        for(int[] q: queries){
            prefixSum[q[0]]--;
            if(q[1]+1<n){
                prefixSum[q[1]+1]++;
            }
        }
        int sum=0;
        for(int i=0;i<n;i++){
            sum+=prefixSum[i];
            rs[i]=nums[i]+sum;
            if(rs[i]<0){
                rs[i]=0;
            }
        }
        for(int i=0;i<n;i++){
            if(rs[i]!=0){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given an integer array nums of length n and a 2D array queries, where queries[i] = [li, ri].
        //- For each queries[i]:
        //  + Select (a subset of indices) within the range [li, ri] in nums.
        //  + Decrement the values at (the selected indices) by 1.
        //  + A Zero Array is an array where (all elements) are equal to 0.
        //* Return true if
        //  + it is possible to transform nums into a Zero Array after processing all the (queries sequentially), otherwise return false.
        //- A subset of an array is a selection of elements (possibly none) of the array.
        //
        //Example 2:
        //
        //Input: nums = [4,3,2,1], queries = [[1,3],[0,2]]
        //Output: false
        //Explanation:
        //For i = 0:
        //Select the subset of indices as [1, 2, 3] and decrement the values at these indices by 1.
        //The array will become [4, 2, 1, 0].
        //For i = 1:
        //Select the subset of indices as [0, 1, 2] and decrement the values at these indices by 1.
        //The array will become [3, 1, 0, 0], which is not a Zero Array.
        //
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= nums.length <= 10^5
        //0 <= nums[i] <= 10^5
        //1 <= queries.length <= 10^5
        //queries[i].length == 2
        //0 <= li <= ri < nums.length
        //  + Len<=10^5 ==> Time: O(n)
        //
        //** Brainstorm
        //
//        int[] nums = {4,3,2,1};
//        int[][] queries = {{1,3},{0,2}};
        int[] nums = {1,0,1};
        int[][] queries = {{0,2}};
        System.out.println(isZeroArray(nums, queries));
        //
    }
}
