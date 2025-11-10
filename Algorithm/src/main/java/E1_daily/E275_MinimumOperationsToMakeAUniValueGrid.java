package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E275_MinimumOperationsToMakeAUniValueGrid {

    public static int minOperations(int[][] grid, int x) {
        List<Integer> list=new ArrayList<>();
        int n=grid.length;
        int m=grid[0].length;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                list.add(grid[i][j]);
            }
        }
        Collections.sort(list);
        int len=list.size();
        System.out.println(list);

        for (int i = 0; i <len; i++) {
//            System.out.printf("%s %s\n", list.get(i)%x, list.get(i+1)%x);
            if(i+1<len&&((list.get(i)%x)!=(list.get(i+1)%x))){
                System.out.println(i);
                return -1;
            }
        }
        int rs=0;
        int rs1=0;
        if(len%2==0){
            //0,1,2,3
            int firstPilar = list.get(len/2);
            int secondPilar = list.get(len/2-1);
            for(int i=0;i<len;i++){
                rs+=Math.abs((firstPilar-list.get(i)))/x;
                rs1+=Math.abs((secondPilar-list.get(i)))/x;
            }
            return Math.min(rs, rs1);
        }
        int firstPilar = list.get(len/2);
        for(int i=0;i<len;i++){
            rs+=Math.abs((firstPilar-list.get(i)))/x;
        }
        return rs;
    }

    public static int minOperationsRefactor(int[][] grid, int x) {
        int n=grid.length;
        int m=grid[0].length;
        int[] arr=new int[n*m];
        int index=0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                arr[index++]=grid[i][j];
            }
        }
        Arrays.sort(arr);
        int len=arr.length;
//        System.out.println(list);

        for (int i = 0; i <len; i++) {
//            System.out.printf("%s %s\n", list.get(i)%x, list.get(i+1)%x);
            if(i+1<len&&((arr[i]%x)!=(arr[i+1]%x))){
//                System.out.println(i);
                return -1;
            }
        }
        int rs=0;
        int pilar = arr[len/2];

        for (int j : arr) {
            rs += Math.abs((pilar - j)) / x;
        }
        return rs;
    }

    public static int minOperationsPrefixSum(int[][] grid, int x) {
        // Initialize an empty array to store all numbers
        ArrayList<Integer> numsArray = new ArrayList<>();
        int result = Integer.MAX_VALUE;

        // Flatten the grid into numsArray and check if all elements have the same remainder when divided by x
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[0].length; col++) {
                // If remainder of any element doesn't match the first element, return -1
                if (grid[row][col] % x != grid[0][0] % x) return -1;
                numsArray.add(grid[row][col]);
            }
        }

        // Sort numsArray in non-decreasing order to easily calculate operations
        Collections.sort(numsArray);

        int length = numsArray.size();
        int[] prefixSum = new int[length];
        int[] suffixSum = new int[length];

        // Calculate the prefix sum up to each index (excluding the current element)
        for (int index = 1; index < length; index++) {
            prefixSum[index] = prefixSum[index - 1] + numsArray.get(index - 1);
        }

        // Calculate the suffix sum from each index (excluding the current element)
        for (int index = length - 2; index >= 0; index--) {
            suffixSum[index] = suffixSum[index + 1] + numsArray.get(index + 1);
        }

        // Iterate through numsArray to calculate the number of operations for each potential common value
        for (int index = 0; index < length; index++) {
            int leftOperations =
                    (numsArray.get(index) * index - prefixSum[index]) / x;
            int rightOperations =
                    (suffixSum[index] -
                            numsArray.get(index) * (length - index - 1)) /
                            x;
            // Update the result with the minimum operations needed
            result = Math.min(result, leftOperations + rightOperations);
        }

        return result;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D integer grid of size m x n and an integer x.
        //- In one operation, you can (add x) to or (subtract x) from (any element) in the grid.
        //- (A uni-value grid) is a grid where all the elements of it are (equal).
        //* Return (the minimum number of operations) to make (the grid uni-value).
        //  + If it is not possible, return -1.
        //
        //Example 1:
        //
        //Input: grid = [
        // [2,4],
        // [6,8]
        //],
        //x = 2
        //Output: 4
        //Explanation: We can make every element equal to 4 by doing the following:
        //- Add x to 2 once.
        //- Subtract x from 6 once.
        //- Subtract x from 8 twice.
        //A total of 4 operations were used.
        //
        //** Idea
        //1. Median
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == grid.length
        //n == grid[i].length
        //1 <= m, n <= 10^5
        //1 <= m * n <= 10^5
        //1 <= x, grid[i][j] <= 10^4
        //  + n*m<=10^5 ==> Time: O(n)
        //
        //* Brainstorm:
        //- [2,4,6,8]
        //- How to choose the middle element?
        //
        //[2,4,6,8]
        //x=2
        //
        //[2,5,9,12]
        //x=3
        //===> (e[i]%x) Same remainder ==> OK
        //<> return -1
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- We dont need to consider list[len/2] and list[len/2-1]
        //  + Because we choose list[len/2] or list[len/2-1]
        //  Ex:
        //  a,b,c,d
        //  pilar = b: rs=(b-a)+(c-b)+(d-b)
        //  pilar = c: rs1=(c-a)+(c-b)+(d-c)
        //      + rs=rs1=(d-a)
        //==> We don't need care about (length of array) is (even or odd)
        //
        //1.3, Complexity
        //- Time: O(n)
        //- Space: O(n)
        //
        //2. Prefix sum
        //2.0,
        //- [2,4,(6),8,10]
        //- At element[i]=6:
        //  + Number of operation in the left = (prefix_sum[i]-pilar*(index+1))%x
        //nums   = [2,4,(6),8,10]
        //prefix = [2,6,12,20,30]
        //==> The suffix rule will be the same as the prefix one.
        //
//        int[][] grid = {{2,4},{6,8}};
//        int x = 2;
        int[][] grid = {{1,2},{1,2}};
        int x = 2;
        System.out.println(minOperations(grid, x));
        System.out.println(minOperationsRefactor(grid, x));
        System.out.println(minOperationsPrefixSum(grid, x));
    }
}
