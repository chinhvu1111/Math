package E1_PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class E11_NumberOfSubmatricesThatSumToTarget_classic {

    public static int sumRec(int i, int j, int i1, int j1, int[][] dp){
        return dp[i1][j1] - dp[i-1][j1] - dp[i1][j-1] + dp[i-1][j-1];
    }

    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int n=matrix.length;
        int m = matrix[0].length;
        int[][] prefixSum=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                prefixSum[i][j] = prefixSum[i-1][j] + prefixSum[i][j-1] - prefixSum[i-1][j-1] + matrix[i-1][j-1];
            }
        }

        int rs=0;
        for(int i=1;i<=n;i++){
            for(int j=i;j<=n;j++){
                int[] prefixSumRec=new int[m+1];
                for(int h=1;h<=m;h++){
                    prefixSumRec[h]=sumRec(i, 1, j, h, prefixSum);
                }
                HashMap<Integer, Integer> mapCount=new HashMap<>();
                mapCount.put(0, 1);
                for(int h=1;h<=m;h++){
                    if(mapCount.containsKey(prefixSumRec[h]-target)){
                        rs+=mapCount.get(prefixSumRec[h]-target);
                    }
                    mapCount.put(prefixSumRec[h], mapCount.getOrDefault(prefixSumRec[h],0)+1);
                }
            }
        }
        return rs;
    }

    public static int numSubmatrixSumTargetRefer(int[][] matrix, int target) {
        int r = matrix.length, c = matrix[0].length;

        // compute 2D prefix sum
        int[][] ps = new int[r + 1][c + 1];
        for (int i = 1; i < r + 1; ++i) {
            for (int j = 1; j < c + 1; ++j) {
                ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + matrix[i - 1][j - 1];
            }
        }

        int count = 0, currSum;
        Map<Integer, Integer> h = new HashMap();
        // reduce 2D problem to 1D one
        // by fixing two columns c1 and c2 and
        // computing 1D prefix sum for all matrices using [c1..c2] columns
        for (int c1 = 1; c1 < c + 1; ++c1) {
            for (int c2 = c1; c2 < c + 1; ++c2) {
                h.clear();
                h.put(0, 1);
                for (int row = 1; row < r + 1; ++row) {
                    // current 1D prefix sum
                    currSum = ps[row][c2] - ps[row][c1 - 1];

                    // add subarrays which sum up to (currSum - target)
                    count += h.getOrDefault(currSum - target, 0);

                    // save current prefix sum
                    h.put(currSum, h.getOrDefault(currSum, 0) + 1);
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a matrix and a target,
        //* Return (the number of (non-empty submatrices)) that sum to target.
        //- A submatrix (x1, y1, x2, y2) is the set of all cells matrix[x][y] with (x1 <= x <= x2 and y1 <= y <= y2).
        //  - Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different
        //      + if they have some coordinate that is different:
        //      for example, if x1 != x1'.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= matrix.length <= 100
        //1 <= matrix[0].length <= 100
        //-1000 <= matrix[i][j] <= 1000
        //-10^8 <= target <= 10^8
        //  + Size của array không quá lớn
        //  => Time: O(n*m) or O(n^3)
        //
        //- Brainstorm
        //Dùng ct:
        //
        //1 1 1 1
        //1 1 1 1
        //1 1 1 1
        //
        //1 2 3 4
        //2 4 6 8
        //3 6 9 12
        //- dp[i][j]:
        //  + Là sum các số trong rectangle from (0,0) -> (i,j)
        //- Tính ntn?
        //- CT:
        //  + dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i][j]
        //
        //- Để tính sum trong (i,j) và (i1,j1):
        //Ex:
        //1 2 3 4 <-
        //2 4 6 8
        //3 6 9 12
        //  ^
        //  |
        //- grid(i1,j1) = 4, grid(i,j) = 12
        //- CT:
        //  + sum([i][j] -> [i1][j1]) = dp[i1][j1] - dp[i-1][j1] - dp[i1][j-1] + dp[i-1][j-1]
        //
        //- Nếu non optimization:
        //  + Time: O(n^4) do scan all of pairs
        //- Nếu ta scan all of rectangles:
        //  ==> Vẫn cần O(n^4)
        //- Nhưng nếu ta kết hợp prefix sum vào thì sao:
        //- Scan các cặp rows;
        //  + For each pair of row:
        //      + Ta có thể xét prefix sum dự trên 2 rows đó:
        //      Ex:
        //      row1 = u, row2 = v
        //      [ point[u,0], point1[v][i] ]
        //  + Sau dùng prefix sum + hashmap để tính ra kết quả.
        //
        //1.1, Optimization
        //- Ta có thể clear map để sử dụng lại tránh việc re-create liên tục.
        //
        //1.2, Complexity
        //- Space: O(n*m+m)
        //- Time: O(n^2*m)
        //
        //#Reference:
        //2556. Disconnect Path in a Binary Matrix by at Most One Flip
        //
//        int[][] matrix = {{0,1,0},{1,1,1},{0,1,0}};
//        int target = 0;
        int[][] matrix = {{1,-1},{-1,1}};
        int target = 0;
        System.out.println(numSubmatrixSumTarget(matrix, target));
        System.out.println(numSubmatrixSumTargetRefer(matrix, target));
    }
}
