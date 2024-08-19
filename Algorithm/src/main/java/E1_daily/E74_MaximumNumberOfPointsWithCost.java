package E1_daily;

import java.util.Collections;
import java.util.PriorityQueue;

public class E74_MaximumNumberOfPointsWithCost {

    public static long maxPoints(int[][] points) {
        int n=points.length;
        if(points.length==0||n==0){
            return 0;
        }
        int m = points[0].length;
        long rs=0;
        long[] prevMaxScore=new long[points[0].length];

        for(int i=0;i<m;i++){
            prevMaxScore[i]=points[0][i];
//            System.out.printf("%s, ", prevMaxScore[i]);
        }
//        System.out.println();
        for(int i=1;i<n;i++){
            PriorityQueue<Long> prevScoreLessThan = new PriorityQueue<>(Collections.reverseOrder());
            PriorityQueue<Long> prevScoreGreaterThan = new PriorityQueue<>(Collections.reverseOrder());
//            PriorityQueue<Integer> currentScore = new PriorityQueue<>();
            long[] currentMaxScore=new long[points[0].length];
//            System.out.println(i);

            for(int j=0;j<m;j++){
                prevScoreLessThan.add(prevMaxScore[j]+j);
//                prevScoreGreaterThan.add(prevMaxScore[j]+j);
                currentMaxScore[j]=Math.max(currentMaxScore[j], prevScoreLessThan.peek()+points[i][j]-j);
//                currentMaxScore[j]=Math.max(currentMaxScore[j], prevScoreGreaterThan.peek()+points[i][j]-j);
//                System.out.printf("%s, ", currentMaxScore[j]);
//                currentScore.add(points[i][j]+j);
            }
            prevScoreLessThan.clear();
            prevScoreGreaterThan.clear();
            for(int j=m-1;j>=0;j--){
                prevScoreLessThan.add(prevMaxScore[j]-j);
//                prevScoreGreaterThan.add(prevMaxScore[j]+j);
                currentMaxScore[j]=Math.max(currentMaxScore[j], prevScoreLessThan.peek()+points[i][j]+j);
//                currentMaxScore[j]=Math.max(currentMaxScore[j], prevScoreGreaterThan.peek()+points[i][j]-j);
//                System.out.printf("%s, ", currentMaxScore[j]);
//                currentScore.add(points[i][j]+j);
            }
//            for(int j=0;j<n;j++){
//                System.out.printf("%s, ", currentMaxScore[j]);
////                currentScore.add(points[i][j]+j);
//            }
//            System.out.println();
            prevMaxScore=currentMaxScore;
        }
        for(int i=0;i<points[0].length;i++){
            rs=Math.max(rs, prevMaxScore[i]);
        }
        return rs;
    }

    public static long maxPointsOptimization(int[][] points) {
        int n=points.length;

        if(points.length == 0){
            return 0;
        }
        int m = points[0].length;
        long rs=0;
        long[] prevMaxScore=new long[m];

        for(int i=0;i<m;i++){
            prevMaxScore[i]=points[0][i];
//            System.out.printf("%s, ", prevMaxScore[i]);
        }
//        System.out.println();
        for(int i=1;i<n;i++){
//            PriorityQueue<Integer> currentScore = new PriorityQueue<>();
            long[] currentMaxScore=new long[m];
//            System.out.println(i);
            long max=Integer.MIN_VALUE;

            for(int j=0;j<m;j++){
                max=Math.max(max, prevMaxScore[j]+j);
                currentMaxScore[j]=Math.max(currentMaxScore[j], max+points[i][j]-j);
            }
            max=Integer.MIN_VALUE;
            for(int j=m-1;j>=0;j--){
                max=Math.max(max, prevMaxScore[j]-j);
                currentMaxScore[j]=Math.max(currentMaxScore[j], max+points[i][j]+j);
            }
//            for(int j=0;j<n;j++){
//                System.out.printf("%s, ", currentMaxScore[j]);
////                currentScore.add(points[i][j]+j);
//            }
//            System.out.println();
            prevMaxScore=currentMaxScore;
        }
        for(int i=0;i<points[0].length;i++){
            rs=Math.max(rs, prevMaxScore[i]);
        }
        return rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- You are given an m x n integer matrix points (0-indexed).
        //- Starting with 0 points, you want to (maximize) (the number of points) you can get from the matrix.
        //- To gain points, you must pick (one cell) in (each row).
        //- Picking the cell at coordinates (r, c) will add points[r][c] to your score.
        //- However, you will (lose points) if you pick (a cell) too far from the cell that you picked in the (previous row).
        //- For every two adjacent rows r and r + 1 (where 0 <= r < m - 1), picking cells at coordinates (r, c1) and (r + 1, c2) will
        //  + subtract abs(c1 - c2) from your score.
        //* Return (the maximum number of points) you can achieve.
        //- abs(x) is defined as:
        //  + x for x >= 0.
        //  + -x for x < 0.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //m == points.length
        //n == points[r].length
        //1 <= m, n <= 10^5
        //1 <= m * n <= 10^5
        //0 <= points[r][c] <= 10^5
        //  + m*n<= 10^5 --> Time: O(n) / O(n*k)
        //
        //- Brainstorm
        //- Ta sẽ đi từ row = 0
        //- Khoảng trừ đi sẽ là:
        //  + Diff giữa các col trong array grid
        //- Có cases nào mà các row cách nhau xa hơn 1 => Được ưu tiên hơn không
        //Ex:
        //col_indexes = [1,4,5]
        //+ (5-1) + (5-4) = 4+1
        //+ (4-1) + (5-4) = 4
        //==> 1->5 là quá xa ==> Sẽ cộng thêm phần thừa
        //  + Các score vẫn keep do các score của cells không đổi
        //  + Nên ta sẽ ưu tiên di dần dần
        //
        //- Nếu xét mỗi row:
        //  + Time = O(n)
        //- Ta cần phải loop mỗi col:
        //  + Time = O(m) đề lấy được score
        //- Vì đằng sau luôn trừ cho đằng trước:
        //  + current col - previous col
        //- Ta chỉ cần lưu lại:
        //  + dạng priority queue:
        //      + (score - col index là được)
        //- Vì ta sẽ trừ đi:
        //  + abs|col2 - col1| nên:
        //      + col2> col1
        //      + col1> col2
        //- Với col2 > col1:
        //  + Ta chỉ cần loop cả 2 row cùng lúc:
        //      + Chúng sẽ cùng index và dùng priorityQueue cùng lúc:
        //          + col2 >= col1
        //- Làm sao để chỉ xét các col1 mà thoả mãn:
        //  + col1 <= col2
        //  ==> Loop theo index tăng dần
        //- Xét các cặp như sau:
        //- PriorityQueue1 = (prev_score - index)
        //- PriorityQueue2 = (current + index)
        //
        //- PriorityQueue1 = (prev_score + index)
        //- PriorityQueue2 = (current - index)
        //
        //- Bài này có vẻ là dynamic programming:
        //  + Vì mỗi cell trong row sẽ có thể nhận 1 giá trị max duy nhất, tính theo:
        //      + Các rows trước đó.
        //
        //1.1, Optimization
        //- Đoạn này không cần phải dùng priority queue do chỉ cần lấy giá trị max cuối cùng
        //  + Đoạn này xét max là được.
        //
        //1.2, Complexity
        //- Space: O(m)
        //- Time: O(n*m)
        //
//        int[][] points = {
//                {1,2,3},
//                {1,5,1},
//                {3,1,1}
//        };
        int[][] points = {
                {2,2,2},
                {2,2,2},
                {2,2,2}
        };
        System.out.println(maxPoints(points));
        System.out.println(maxPointsOptimization(points));
        //#Reference:
        //1981. Minimize the Difference Between Target and Chosen Elements
    }
}
