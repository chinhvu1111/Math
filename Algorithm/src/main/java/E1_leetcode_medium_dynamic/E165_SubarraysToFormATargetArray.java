package E1_leetcode_medium_dynamic;

public class E165_SubarraysToFormATargetArray {
    public int minNumberOperations(int[] target) {
        int count=target[0];
        int n=target.length;
        for(int i=1;i<n;i++){
            count+=Math.max(target[i]-target[i-1], 0);
        }
        return count;
    }

    public static void main(String[] args) {
        //https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/solutions/754682/wall-of-bricks/
        //- Bài toán con của:
        //+ E158_MinimumOperationsToMakeArrayEqualToTarget (contest)
        //
        //Let us assume that target represents height of columns on a square grid.
        // One operation corresponds to laying out continuous row of bricks.
        // What is the number of these rows? To find this number we count the number of left edges of these rows.
        //
        //Example: [3,1,5,4,2,3,4,2]. Left edges are marked by red color. Total number of left edges is 3 + 4 + 1 + 1.
        //
        //    1
        //    1 1     1
        //1   1 1   1 1
        //1   1 1 1 1 1 1
        //1 1 1 1 1 1 1 1
        //1   4     1  1
        //==>rs = SUM( Max(diff (current height) and (previous height), 0) )
    }
}
