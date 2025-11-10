package E1_binary_search_topic;

public class E29_SearchA2DMatrixII_classic {

    public static int solution(int low, int high, int[] arr, int target){
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(arr[mid]>=target){
                rs=mid;
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
        int low=0, high=m-1;

        for(int i=0;i<n;i++){
            int curIndex=solution(low, high, matrix[i], target);
//            System.out.printf("Index: %s, curIndex: %s, low: %s, high: %s\n", i, curIndex, low, high);
            if(curIndex==-1){
                high=m-1;
            }else if(matrix[i][curIndex]==target){
                return true;
            }else{
                high=curIndex;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Write an efficient algorithm that searches for a value target in an m x n integer matrix "matrix".
        //- This matrix has the following properties:
        //- Integers in each row are sorted in ascending from left to right.
        //- Integers in each column are sorted in ascending from top to bottom.
        //
        //Input: matrix =
        // [
        //  [1,4,7,11,15],
        //  [2,5,8,12,19],
        //  [3,6,9,16,22],
        //  [10,13,14,17,24],
        //  [18,21,23,26,30]
        //],
        // target = 5
        //Output: true
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == matrix.length
        //n == matrix[i].length
        //1 <= n, m <= 300
        //-109 <= matrix[i][j] <= 109
        //All the integers in each row are sorted in ascending order.
        //All the integers in each column are sorted in ascending order.
        //-109 <= target <= 109
        //
        //- Brainstorm
        //Input:
        // matrix =
        // [
        //  [1,4,7,11,15],
        //  [2,5,8,12,19],
        //  [3,6,9,16,22],
        //  [10,13,14,17,24],
        //  [18,21,23,26,30]
        //  ], target = 5
        //Output: true
        //
        //- Search(val):
        //  + Search[ min(value)>=target ]:
        //      + That means we don't need to find (value,...)
        //      ==> Out of range
        //  + Search(5)
        //  + [1,15]: 7
        //  + [2,8]: 5 ==> Return 5
//        int[][] matrix =
//                {
//                        {1,4,7,11,15},
//                        {2,5,8,12,19},
//                        {3,6,9,16,22},
//                        {10,13,14,17,24},
//                        {18,21,23,26,30}
//                };
//        int target = 5;
        int[][] matrix =
                {
                        {1,2,3,4,5},
                        {6,7,8,9,10},
                        {11,12,13,14,15},
                        {16,17,18,19,20},
                        {21,22,23,24,25}
                };
        int target = 19;
        System.out.println(searchMatrix(matrix, target));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*log(m))
        //
        //# Reference:
        //391. Perfect Rectangle
        //2304. Minimum Path Cost in a Grid
        //308. Range Sum Query 2D - Mutable
    }
}
