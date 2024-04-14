package contest;

import javafx.util.Pair;

import java.util.Objects;
import java.util.TreeSet;

public class E71_MinimizeManhattanDistances {

    public static int minimumDistance(int[][] points) {
        TreeSet<Pair<Integer, Integer>> maxSumTree=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return a.getKey()-b.getKey();
            }
            return a.getValue()-b.getValue();
        });
//        TreeSet<Pair<Integer, Integer>> minSumTree=new TreeSet<>((a, b) -> {
//            if(!Objects.equals(a.getKey(), b.getKey())){
//                return b.getKey()-a.getKey();
//            }
//            return a.getValue()-b.getValue();
//        });
        TreeSet<Pair<Integer, Integer>> maxDiffTree=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return a.getKey()-b.getKey();
            }
            return a.getValue()-b.getValue();
        });
//        TreeSet<Pair<Integer, Integer>> minDiffTree=new TreeSet<>((a, b) -> {
//            if(!Objects.equals(a.getKey(), b.getKey())){
//                return b.getKey()-a.getKey();
//            }
//            return a.getValue()-b.getValue();
//        });
        int n=points.length;
        int minSum = points[0][0] + points[0][1];
        int maxSum = points[0][0] + points[0][1];
        int maxDiff = points[0][0] - points[0][1];
        int minDiff = points[0][0] - points[0][1];
        maxSumTree.add(new Pair<>(maxSum, 0));
//        minSumTree.add(new Pair<>(minSum, 0));
        maxDiffTree.add(new Pair<>(maxDiff, 0));
//        minDiffTree.add(new Pair<>(minDiff, 0));
        for(int i=1;i<n;i++){
            int x = points[i][0];
            int y = points[i][1];
            int sum = points[i][0] + points[i][1];
            int diff = points[i][0] - points[i][1];
            Pair<Integer, Integer> sumNode=new Pair<>(sum, i);
            Pair<Integer, Integer> diffNode=new Pair<>(diff, i);
            maxSumTree.add(sumNode);
            maxDiffTree.add(diffNode);
        }
//        System.out.println(maxSumTree);
//        System.out.println(minSumTree);
//        System.out.println(maxDiffTree);
//        System.out.println(minDiffTree);
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int x = points[i][0];
            int y = points[i][1];
            int sum = points[i][0] + points[i][1];
            int diff = points[i][0] - points[i][1];
            Pair<Integer, Integer> sumNode=new Pair<>(sum, i);
            Pair<Integer, Integer> diffNode=new Pair<>(diff, i);
            maxSumTree.remove(sumNode);
            maxDiffTree.remove(diffNode);
//            System.out.printf("index = %s, %s %s\n", i, x, y);
//            System.out.println(maxX);
//            System.out.println(minX);
//            System.out.println(maxY);
//            System.out.println(minY);
            int maxLastX = maxSumTree.last().getKey();
            int minLastX = maxSumTree.first().getKey();
            int maxLastY = maxDiffTree.last().getKey();
            int minLastY = maxDiffTree.first().getKey();
            maxSumTree.add(sumNode);
            maxDiffTree.add(diffNode);
//            System.out.printf("%s %s %s %s\n", maxLastX, minLastX, maxLastY, minLastY);
            rs=Math.min(rs, Math.max(Math.abs(maxLastY-minLastY), Math.abs(maxLastX-minLastX)));
        }
        return rs;
    }

    public static int minimumDistanceWrong(int[][] points) {
        TreeSet<Pair<Integer, Integer>> maxX=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return a.getKey()-b.getKey();
            }
            return a.getValue()-b.getValue();
        });
        TreeSet<Pair<Integer, Integer>> minX=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return b.getKey()-a.getKey();
            }
            return a.getValue()-b.getValue();
        });
        TreeSet<Pair<Integer, Integer>> maxY=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return a.getKey()-b.getKey();
            }
            return a.getValue()-b.getValue();
        });
        TreeSet<Pair<Integer, Integer>> minY=new TreeSet<>((a, b) -> {
            if(!Objects.equals(a.getKey(), b.getKey())){
                return b.getKey()-a.getKey();
            }
            return a.getValue()-b.getValue();
        });
        int n=points.length;
        for(int i=0;i<n;i++){
            int x = points[i][0];
            int y = points[i][1];
            Pair<Integer, Integer> xNode=new Pair<>(x, i);
            Pair<Integer, Integer> yNode=new Pair<>(y, i);
            maxX.add(xNode);
            minX.add(xNode);
            minY.add(yNode);
            maxY.add(yNode);
        }
        System.out.println(maxX);
        System.out.println(minX);
        System.out.println(maxY);
        System.out.println(minY);
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<n;i++){
            int x = points[i][0];
            int y = points[i][1];
            Pair<Integer, Integer> xNode=new Pair<>(x, i);
            Pair<Integer, Integer> yNode=new Pair<>(y, i);
            maxX.remove(xNode);
            minX.remove(xNode);
            minY.remove(yNode);
            maxY.remove(yNode);
//            System.out.printf("index = %s, %s %s\n", i, x, y);
//            System.out.println(maxX);
//            System.out.println(minX);
//            System.out.println(maxY);
//            System.out.println(minY);
            int maxLastX = maxX.last().getKey();
            int minLastX = minX.last().getKey();
            int maxLastY = maxY.last().getKey();
            int minLastY = minY.last().getKey();
            maxX.add(xNode);
            minX.add(xNode);
            minY.add(yNode);
            maxY.add(yNode);
            System.out.printf("%s %s %s %s\n", maxLastX, minLastX, maxLastY, minLastY);
            rs=Math.min(rs, Math.abs(maxLastY-minLastY)+ Math.abs(maxLastX-minLastX));
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 0-indexed array points representing integer coordinates of some points on a 2D plane, where points[i] = [xi, yi].
        //The distance between two points is defined as their Manhattan distance.
        //* Return the (minimum possible value) for (maximum distance) between (any two points) by (removing exactly one point).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //3 <= points.length <= 10^5
        //points[i].length == 2
        //1 <= points[i][0], points[i][1] <= 10^8
        // => Positive value
        //==> Cần solve in O(n), k*O(log(n))
        //
        //- Brainstorm
        //- Không thể tìm khoảng cách từng pair of points được ==> Quá O(N)
        //+ CT: val =  |x-x1| + |y-y1|
        //val^2 = (x+x1)^2 - 4*x*x1 + (u+y1)^2 - 4*y*y1
        //Ex:
        //points = [[3,10],[5,15],[10,2],[4,4]]
        //+ Sort
        //  [3,10],[4,4],[5,15],[10,2]
        //- Ta cần tìm max nhất giữa 2 node trong array trước
        //+ CT: val =  |x-x1| + |y-y1|
        //
        int[][] points = {{3,10},{5,15},{10,2},{4,4}};
        System.out.println(minimumDistance(points));
    }
}
