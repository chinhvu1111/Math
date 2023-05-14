package contest;

import java.util.Arrays;

public class E24_SumInAMatrix {

    public static int matrixSum(int[][] nums) {
        int n=nums.length;
        if(n==0){
            return 0;
        }
        int m=nums[0].length;

        for(int i=0;i<n;i++){
            int[] currentArr=nums[i];
            Arrays.sort(currentArr);
        }
        int rs=0;

        for(int i=0;i<m;i++){
            int max=Integer.MIN_VALUE;

            for(int j=0;j<n;j++){
                max=Math.max(max, nums[j][i]);
            }
            rs+=max;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirment:
        //-
        //** Idea
        //1.
        //1.1,
        //- Cần sort sao cho tối ưu
        //[
        //[7,2,1],
        //[2,4,6],
        //[1,8,3],
        //[3,2,1]
        //]
        //--> sort
        //[
        //[7,2,1],
        //[6,4,2],
        //[8,3,1],
        //[3,2,1]
        //]
        //- Sort bằng array : currentArr[]=arr[0]
        //Sort currentArr[]
        //- For để tính all sum
        //
    }
}
