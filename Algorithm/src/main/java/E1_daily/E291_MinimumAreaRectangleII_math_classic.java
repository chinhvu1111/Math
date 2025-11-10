package E1_daily;

import javafx.util.Pair;

import java.awt.Point;
import java.util.*;

public class E291_MinimumAreaRectangleII_math_classic {

    public static double minAreaFreeRect(int[][] points) {
        int n=points.length;
        HashSet<Pair<Integer, Integer>> pointsSet=new HashSet<>();

        for (int i = 0; i < n; i++) {
            pointsSet.add(new Pair<>(points[i][0], points[i][1]));
        }
        double rs=Double.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n; j++) {
                for (int k = j+1; k < n; k++) {
                    int[] a = points[i];
                    int[] b = points[j];
                    int[] c = points[k];
                    Pair<Integer, Integer> d=null;
                    double curArea=-1;

//                    if((a[0]-c[0])*(a[1]-c[1])+(b[0]-c[0])*(b[1]-c[1])==0){ //Check c
                    if((a[0]-c[0])*(b[0]-c[0])+(a[1]-c[1])*(b[1]-c[1])==0){ //Check c
                        d=new Pair<>(a[0]+b[0]-c[0], a[1]+b[1]-c[1]);
                        if(pointsSet.contains(d)){
                            curArea=Math.sqrt((a[0]-c[0])*(a[0]-c[0])+(a[1]-c[1])*(a[1]-c[1]))
                                    *Math.sqrt((b[0]-c[0])*(b[0]-c[0])+(b[1]-c[1])*(b[1]-c[1]));
                        }else{
                            continue;
                        }
                    }else if((a[0]-c[0])*(a[0]-b[0])+(a[1]-c[1])*(a[1]-b[1])==0){ //Check a
                        d=new Pair<>(b[0]+c[0]-a[0], b[1]+c[1]-a[1]);
                        if(pointsSet.contains(d)){
                            curArea=Math.sqrt((a[0]-c[0])*(a[0]-c[0])+(a[1]-c[1])*(a[1]-c[1]))
                                    *Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]));
                        }else{
                            continue;
                        }
                    }else if((a[0]-b[0])*(c[0]-b[0])+(a[1]-b[1])*(c[1]-b[1])==0){ //Check b
                        d=new Pair<>(a[0]+c[0]-b[0], a[1]+c[1]-b[1]);
                        if(pointsSet.contains(d)){
                            curArea=Math.sqrt((a[0]-b[0])*(a[0]-b[0])+(a[1]-b[1])*(a[1]-b[1]))
                                    *Math.sqrt((c[0]-b[0])*(c[0]-b[0])+(c[1]-b[1])*(c[1]-b[1]));
                        }else{
                            continue;
                        }
                    }else {
                        continue;
                    }
                    rs=Math.min(rs, curArea);
                }
            }
        }
        return rs==Double.MAX_VALUE?0:rs;
    }

    public static double minAreaFreeRectRefer(int[][] points) {
        int N = points.length;
        //List of points
        Point[] A = new Point[N];
        for (int i = 0; i < N; ++i)
            A[i] = new Point(points[i][0], points[i][1]);

        Map<Integer, Map<Point, List<Point>>> seen = new HashMap();
        //Two points:
        //- p(x,y), p1(x1,y1)
        for (int i = 0; i < N; ++i)
            for (int j = i+1; j < N; ++j) {
                // center is twice actual to keep integer precision
                Point center = new Point(A[i].x + A[j].x, A[i].y + A[j].y);

                //Radius = (x --- y)
                int r2 = (A[i].x - A[j].x) * (A[i].x - A[j].x);
                r2 += (A[i].y - A[j].y) * (A[i].y - A[j].y);
                if (!seen.containsKey(r2))
                    seen.put(r2, new HashMap<Point, List<Point>>());
                if (!seen.get(r2).containsKey(center))
                    seen.get(r2).put(center, new ArrayList<Point>());
                seen.get(r2).get(center).add(A[i]);
            }

        double ans = Double.MAX_VALUE;
        for (Map<Point, List<Point>> info: seen.values()) {
            for (Point center: info.keySet()) {  // center is twice actual
                List<Point> candidates = info.get(center);
                int clen = candidates.size();
                for (int i = 0; i < clen; ++i)
                    for (int j = i+1; j < clen; ++j) {
                        Point P = candidates.get(i);
                        Point Q = candidates.get(j);
                        Point Q2 = new Point(center);
                        Q2.translate(-Q.x, -Q.y);
                        double area = P.distance(Q) * P.distance(Q2);
                        if (area < ans)
                            ans = area;
                    }
            }
        }

        return ans < Double.MAX_VALUE ? ans : 0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array of points in (the X-Y plane points) where points[i] = [xi, yi].
        //* Return (the minimum area of any rectangle) formed from (these points),
        //with sides (not necessarily parallel) to (the X and Y axes).
        //- If there is (not any such rectangle), return 0.
        //- Answers within (10^-5) of the actual answer will be accepted.
        //
        //Input:
        //  points = [[1,2],[2,1],[1,0],[0,1]]
        //Output: 2.00000
        //Explanation:
        //- The minimum area rectangle occurs at [1,2],[2,1],[1,0],[0,1], with an area of 2.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= points.length <= 50
        //points[i].length == 2
        //0 <= xi, yi <= 4 * 10^4
        //All the given points are unique.
        //
        //* Brainstorm:
        //
        //a(x,y), b(x1,y1)
        //c(x2,y2),d(x3,y3)
        //==> a,b,c,d can make a rectangle:
        //Ex:
        //a(1,2), b(2,1)
        //c(1,0), d(0,1)
        //  + (x-x1)/(y-y1) = 1
        //  + (x2-x3)/(y2-y3) = 1
        //- Brute force:
        //  + Time: O(n^4) ==> TLE
        //
        //- How to identify (the rectangle):
        //  + length<=50 => Time: O(n^3)
        //=> We just need 3 points because from these points:
        //  ==> We can calculate (the remaining point)
        //
        //Ex:
        //1 ---- 1
        // \
        //   1
        //==> It is a rectangle so we need to determine what (angle point) is
        //
        //a(x,y), b(x1,y1)
        //c(x2,y2)
        //Ex:
        //a(1,2), b(2,1)
        //c(0,1)
        //f(a-b) = (-1,1)
        //f(d-c) = (-1,-1)
        //  ==> f(a-b)*f(d-c) = 1+(-1)*1 = 0
        //==> a is (the angle point)
        //==> d = (b+c)-a
        //  + d= (2,1) + (0,1) - (1,2)
        //  =>d =(1,0)
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //- For (each pair of points), classify them by (center) and (radius).
        //- We only need to record (one of the points P),
        //  since the other point is P' = 2 * center - P (using vector notation).
        //- For each (center) and (radius), look at (every possible rectangle) (two pairs of points P, P', Q, Q').
        //- The area of this rectangle dist(P, Q) * dist(P, Q') is a candidate answer.
        //
        //- We just need to care about (radius, center_point)
        //Ex:
        // A ---- center ---- B
        //  + From center, we can calculate (A and B)
        //- Cache Map(radius, Map<Center, List of points>>)
        //Map<Integer, Map<Point, List<Point>>> seen
        //- Loop all cache:
        //  + ==> For to find the result.
        //
        //1.3, Complexity
        //- Space: O(m)
        //- Time: O(n^3) ==> O(n^2*log(n))
        //
//        int[][] points = {{1,2},{2,1},{1,0},{0,1}};
//        int[][] points = {{0,1},{2,1},{1,1},{1,0},{2,0}};
        int[][] points = {{3,1},{1,1},{0,1},{2,1},{3,3},{3,2},{0,2},{2,3}};
        //[3,1](a),[1,1](b),[2,1](c)
        //
        //2
        //1 x x x
        //0 1 2 3
        //(a-b) = (2,0)
        //(b-c) = (-1,0)
        //
        //
        System.out.println(minAreaFreeRect(points));
        System.out.println(minAreaFreeRectRefer(points));
        //
        //#Reference:
        //956. Tallest Billboard
        //3134. Find the Median of the Uniqueness Array
        //1058. Minimize Rounding Error to Meet Target
        //
        //2921. Maximum Profitable Triplets With Increasing Prices II - HARD
        //668. Kth Smallest Number in Multiplication Table - HARD
        //2653. Sliding Subarray Beauty
    }
}
