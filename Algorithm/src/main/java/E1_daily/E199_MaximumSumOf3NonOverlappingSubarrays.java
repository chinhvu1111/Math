package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E199_MaximumSumOf3NonOverlappingSubarrays {

    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n=nums.length;
        int[] prefixSum=new int[n+1];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=nums[i];
            prefixSum[i+1]=sum;
        }
        //Space: O(n)
        int[][] dp=new int[n+1][4];
        List<Integer>[][] dpList=new ArrayList[n+1][4];
        int[] max=new int[4];
        List<Integer>[] maxIndices=new ArrayList[4];

        for(int i=0;i<4;i++){
            maxIndices[i]=new ArrayList<>();
        }

        //Time: O(n)
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MIN_VALUE);
            for(int j=0;j<4;j++){
                dpList[i][j]=new ArrayList<>();
            }
        }
        for(int i=0;i<=3;i++){
            dp[0][i]=0;
        }
        for (int i = 0; i < n; i++) {
            dp[i+1][0]=0;
        }
        //Time: O(n)
        for(int i=0;i<n;i++){
            for(int j=1;j<=3;j++){
                dp[i+1][j]=max[j];
                dpList[i+1][j]=maxIndices[j];
                if(i+1>=k&&dp[i+1-k][j-1]!=Integer.MIN_VALUE){
                    //[1,2,3]
                    if(dp[i+1][j]<dp[i+1-k][j-1]+prefixSum[i+1]-prefixSum[i+1-k]){
                        dp[i+1][j]=dp[i+1-k][j-1]+prefixSum[i+1]-prefixSum[i+1-k];
                        dpList[i+1][j]=new ArrayList<>();
                        dpList[i+1][j].addAll(dpList[i+1-k][j-1]);
                        dpList[i+1][j].add(i+1-k);
                    }
                }
                if(max[j]<dp[i+1][j]){
                    max[j]=dp[i+1][j];
                    maxIndices[j]=dpList[i+1][j];
                }
            }
        }
        int[] rs=new int[3];

        for(int i=0;i<3;i++){
            rs[i]=maxIndices[3].get(i);
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (an integer array nums) and (an integer k),
        //  + find ("three" non-overlapping subarrays of length k) with (maximum sum) and return them.
        //- Max sum of each subarray or max sum of all
        //  + All ==> limited by k
        //  ==> Make sense because for each subarray does not work with this example:
        //Ex:
        //nums = [1,2,3,7,10,5,3], k=2
        //[2,3],[7,10],[5,3]
        //[1,2],[3,7],[10,5]
        //
        //* Return the result as (a list of indices) representing (the starting position of each interval) (0-indexed).
        //- If there are multiple answers, return (the lexicographically smallest one).
        //
        //Example 1:
        //
        //Input: nums = [1,2,1,2,6,7,5,1], k = 2
        //Output: [0,3,5]
        //Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
        //We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= nums.length <= 2 * 10^4
        //1 <= nums[i] < 2^16 = 65536
        //1 <= k <= floor(nums.length / 3)
        //  + Length<=10^4 ==> Time: O(n*k)
        //
        //- Brainstorm
        //- Sum ==> Prefix sum
        //- We assume:
        //- dp[i][3]:
        //  + Calculate by using the previous elements
        //- For each element index=i, we have the options below:
        //  + ignore (Add to the previous gap)
        //  + add the current element to the new subarray from (the previous subarray)
        //
        //- Fomulas:
        //- dp[i][3] is max sum until we go to the ith element:
        //  + dp[i][3] = dp[j][2] + sum(j+1,i)
        //      + If we don't use all of elements between (j+1,i)
        //      ==> We also need to calculate:
        //          + dp[i][3] = dp[k>j][2] + sum(k+1,i)
        //      + [j][2]...(k....l).....[i][3]
        //      ==> dp[i][3] = dp[l][3]
        //          + We only need to store all of max dp[3]
        //- Because we use k=2
        //  + We can calculate with O(1)
//        int[] nums = {1,2,1,2,6,7,5,1};
//        int k = 2;
        //- Special cases:
        int[] nums = {7,13,20,19,19,2,10,1,1,19};
        int k = 3;
        //output=[0,3,7]
        //  + [7,13,20],[19,19,2],[1,1,19] = 101
        //rs= [1,4,7]
        //  + [13,20,19],[19,2,10],[1,1,19] = 13+20+19+19+2+10+1+1+19 = 104
        //      + dp[7][2]=dp[4][1]+x
        //      + dp[4][1]=dp[1][0]+x
        //          + but dp[1][0]==INTEGER_MIN:
        //          ==> Init: dp[i(1->n)][0]=0
        //  + 104 = 83+(1+1+19)
        int[] rs= maxSumOfThreeSubarrays(nums, k);

        for (int i = 0; i < rs.length; i++) {
            System.out.printf("%s, ", rs[i]);
        }
        System.out.println();
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //893. Groups of Special-Equivalent Strings
        //2522. Partition String Into Substrings With Values at Most K
        //1307. Verbal Arithmetic Puzzle
    }
}
