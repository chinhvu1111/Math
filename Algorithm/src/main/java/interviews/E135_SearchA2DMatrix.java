package interviews;

public class E135_SearchA2DMatrix {

    public static int searchArr(int[] arr, int target){
        int rs=-1;
        int low=0, high=arr.length-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]>=target){
                if(arr[mid]==target){
                    rs=mid;
                }
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int n=matrix.length;
        int m=matrix[0].length;

        int low=0, high=n-1;
        int rs=-1, index=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(matrix[mid][m-1]>=target){
                index=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        if(index==-1){
            return false;
        }
        if(index>0){
            rs=searchArr(matrix[index-1], target);
            if(rs!=-1){
                return true;
            }
        }
        rs=searchArr(matrix[index], target);
        return rs!=-1;
    }

    public static boolean searchMatrixRefer(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;

        // binary search
        int left = 0, right = m * n - 1;
        int pivotIdx, pivotElement;
        while (left <= right) {
            pivotIdx = (left + right) / 2;
            pivotElement = matrix[pivotIdx / n][pivotIdx % n];
            if (target == pivotElement) return true;
            else {
                if (target < pivotElement) right = pivotIdx - 1;
                else left = pivotIdx + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        //** Requirement
        //- You are given an m x n integer matrix matrix with the following two properties:
        //  + Each row is sorted in non-decreasing order.
        //  + The first integer of each row is greater than the last integer of the previous row.
        //- Given an integer target,
        //* return true if target is in matrix or false otherwise.
        //- You must write a solution in O(log(m * n)) time complexity.
        //
        //* Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == matrix.length
        //n == matrix[i].length
        //1 <= m, n <= 100
        //-10^4 <= matrix[i][j], target <= 10^4
        //
        //- Brainstorm
        //- Just search (the last row)
        //  + Find (the row) to search in the next step
        //
        //1.1, Cases
        //1.2, Optimization
        //- We can search arr with 2 dimensions
        //  + We only (use the index) to search instead of (using more space)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(log(n*m))/ O(log(n))
        //
//        int[][] arr=new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        int[][] arr=new int[][]{{1,3,5,7}, {10,11,16,20}, {23,30,34,60}};
//        int[][] arr=new int[][]{{1,3,5,7,13}};
        int[][] arr=new int[][]{{1},{3}};
//        int[][] arr=new int[][]{{-9,-8,-8},{-5,-3,-2},{0,2,2},{4,6,8}};

//        int rs[]=getMiddlePoint(0,0, 2,3, 2, 3);
//        System.out.printf("%s %s\n", rs[0], rs[1]);
        System.out.println(searchMatrix(arr, 3));
        System.out.println(searchMatrix(arr, 13));
        System.out.println(searchMatrixRefer(arr, 13));

    }
}
