package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E104_Triangle {

    public static int minimumTotal(List<List<Integer>> triangle) {
        int n=triangle.size();
        int[] newRow=new int[n];

        //1,2,3
        //1,2,3,4
        for(int i=0;i<n;i++){
            List<Integer> curRow=triangle.get(i);
            List<Integer> newRecord=new ArrayList<>();
            int m=curRow.size();

            for(int j=0;j<curRow.size();j++){
                int preval=0;
                if(i>=1){
                    int val=newRow[j];
                    int val1=Integer.MAX_VALUE;

                    if(j>=1){
                        val1=newRow[j-1];
                    }
                    preval=Math.min(val, val1);
                }
                int curVal=preval+curRow.get(j);
                newRecord.add(curVal);
            }
            for(int j=0;j<n;j++){
                if(j<newRecord.size()){
                    newRow[j]=newRecord.get(j);
                }else{
                    newRow[j]=Integer.MAX_VALUE;
                }
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<n;i++){
            if(newRow[i]!=Integer.MAX_VALUE){
                rs=Math.min(rs, newRow[i]);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given a triangle array,
        //- Nếu ta ở index =i ở current row --> có thể move đến index=i or i+1 ở next row
        //* return the minimum path sum from top to bottom.
        //* Follow up:
        //- Could you do this using only O(n) extra space, where n is the total number of rows in the triangle?
        //
        //For each step, you may move to an adjacent number of the row below.
        // More formally, if you are on index i on the current row,
        // you may move to either index i or index i + 1 on the next row.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= triangle.length <= 200
        //triangle[0].length == 1
        //triangle[i].length == triangle[i - 1].length + 1
        //-104 <= triangle[i][j] <= 104
        //
        //- Brainstorm
        //- Ta sẽ chỉ cần cache lại các value từ previous row mà thôi
        //
        //1.2, Optimization
        //1.3, Complexity
        //- n is the size of triangle
        //- Space : O(n)
        //- Time : O(n^2)
//        int[][] arr={{2},{3,4},{6,5,7},{4,1,8,3}};
        int[][] arr={{-10}};
        List<List<Integer>> input=new ArrayList<>();

        for(int[] curInput: arr){
            List<Integer> curList=new ArrayList<>();

            for(int val: curInput){
                curList.add(val);
            }
            input.add(curList);
        }
        System.out.println(minimumTotal(input));
        //#Reference:
        //2475. Number of Unequal Triplets in Array
        //2750. Ways to Split Array Into Good Subarrays
        //2456. Most Popular Video Creator
    }
}
