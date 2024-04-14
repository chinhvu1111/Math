package contest;

import java.util.Arrays;

public class E78_MinimumRectanglesToCoverPoints {

    public static int minRectanglesToCoverPoints(int[][] points, int w) {
        int n = points.length;
        if(n==0){
            return 0;
        }
        int prevX=0;
//        int prevY=0;
        int rs=0;
        Arrays.sort(points, (a, b) ->{
            return a[0]-b[0];
        });

        //w=2
        // (0,0) -> (1,1): rs++
        // (1,1) -> (2,2)
        for(int i=0;i<n;){
            int x=points[i][0];
            //Chỗ này cần break mới rs++
//            if(x-prevX>w){
//                rs++;
//                prevX=x;
////                System.out.printf("%s %s\n", x, y);
////                prevY=y;
//            }
            int curIndex=i;
            while (curIndex<n&&points[curIndex][0]-x<=w){
                curIndex++;
            }
            i=curIndex;
            rs++;
        }
//        if(prevX!=points[n-1][0]){
//            rs++;
//        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D integer array points, where points[i] = [xi, yi]. You are also given an integer w.
        // Your task is to cover all the given points with rectangles.
        //Each rectangle has its lower end at some point (x1, 0) and its upper end at some point (x2, y2),
        // where x1 <= x2, y2 >= 0, and
        // + The condition x2 - x1 <= w must be satisfied for each rectangle.
        //A point is considered covered by a rectangle if it lies within or on the boundary of the rectangle.
        //* Return an integer denoting the minimum number of rectangles needed so that each point is covered by at least one rectangle.
        //Note: A point may be covered by more than one rectangle.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= points.length <= 105
        //points[i].length == 2
        //0 <= xi == points[i][0] <= 109
        //0 <= yi == points[i][1] <= 109
        //0 <= w <= 10^9
        //All pairs (xi, yi) are distinct.
        //
        //- Brainstorm
        //- Bắt buộc phải rectangle mới khi nào?
        //  + x2 - x1 > w
        //  + new points y2 > prev_y
        //      + Nếu y2 <= prev_y ==> Check condition (x2 - x1 <=w)
        //
        int[][] points = {{2,1},{1,0},{1,4},{1,8},{3,5},{4,6}};
        int w = 1;
//        int[][] points = {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6}};
//        int w = 2;
//        int[][] points = {{2,3},{1,2}};
//        int w = 0;
//        int[][] points = {{2,3}};
//        int w = 2;
//        int[][] points = {{0,0},{1,1},{2,2},{3,3},{4,4},{5,5},{6,6}};
//        int w = 2;
        //Expected: 3
        //
        //
        System.out.println(minRectanglesToCoverPoints(points, w));
    }
}
