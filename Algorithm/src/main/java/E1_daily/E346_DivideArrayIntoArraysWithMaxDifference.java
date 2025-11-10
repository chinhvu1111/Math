package E1_daily;

import java.util.Arrays;

public class E346_DivideArrayIntoArraysWithMaxDifference {

    public static int[][] divideArray(int[] nums, int k) {
        Arrays.sort(nums);
        int n=nums.length/3;
        int[][] rs=new int[n][3];

        for(int i=0;i<nums.length;i+=3){
            for(int j=0;j<3;j++){
                if(j==0){
                    //i: 0,1,2,3,4,5
                    //=> 0,0,0,1,1,1
                    rs[i/3][j]=nums[i+j];
                    continue;
                }
                if(nums[i+j]-nums[i]>k){
                    return new int[][]{};
                }
                rs[i/3][j]=nums[i+j];
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an integer array nums of size n where n is a multiple of 3 and a positive integer k.
        //- Divide the array nums into (n / 3) arrays of (size 3) satisfying the following condition:
        //- The difference between any two elements in one array is (less than or equal to k).
        //* Return a 2D array containing the arrays. If it is impossible to satisfy the conditions,
        //- return (an empty array). And if there are multiple answers, return any of them.
        //
        //Example 1:
        //Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
        //Output: [[1,1,3],[3,4,5],[7,8,9]]
        //Explanation:
        //The difference between any two elements in each array is less than or equal to 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //n == nums.length
        //1 <= n <= 10^5
        //n is a multiple of 3
        //1 <= nums[i] <= 10^5
        //1 <= k <= 10^5
        //
        //* Brainstorm:
        //- Sort array
        //Input: nums = [1,3,4,8,7,9,3,5,1], k = 2
        //+ arr = [1,1,3,3,4,5,7,8,9], k = 2
        //- Just sort + split the array into 3 segments
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
//        int[] nums= {1,3,4,8,7,9,3,5,1};
//        int k=2;
        int[] nums= {2,4,2,2,5,2};
        //2,2,2,2,4,5
        int k=2;
        int[][] rs= divideArray(nums, k);
        for(int i=0;i<rs.length;i++){
            for (int j = 0; j < rs[i].length; j++) {
                System.out.printf("%s, ", rs[i][j]);
            }
            System.out.println();
        }
        //#Reference:
        //1263. Minimum Moves to Move a Box to Their Target Location - HARD
        //1984. Minimum Difference Between Highest and Lowest of K Scores
        //1989. Maximum Number of People That Can Be Caught in Tag
    }

}
