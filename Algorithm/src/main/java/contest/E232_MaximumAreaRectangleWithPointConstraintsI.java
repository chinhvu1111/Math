package contest;

import javafx.util.Pair;

import java.util.HashSet;

public class E232_MaximumAreaRectangleWithPointConstraintsI {

    public static int maxRectangleArea(int[][] points) {
        int n=points.length;
        HashSet<Pair<Integer, Integer>> setPoints=new HashSet<>();

        for(int[] p:points){
            setPoints.add(new Pair<>(p[0], p[1]));
        }
        int rs=-1;

        //Top-left
        for(int i=0;i<n;i++){
            int x=points[i][0];
            int y=points[i][1];
            //Bottom-right
            for(int j=0;j<n;j++){
                if(i==j){
                    continue;
                }
                int x1=points[j][0];
                int y1=points[j][1];
                if(x>=x1||y<=y1){
                    continue;
                }
                Pair<Integer, Integer> bottomLeft = new Pair<>(x,y1);
                Pair<Integer, Integer> topRight = new Pair<>(x1,y);
                if(!setPoints.contains(bottomLeft)||!setPoints.contains(topRight)){
                    continue;
                }
                boolean isValid=true;
                for(int h=0;h<n;h++){
                    if(x==points[h][0]&&y==points[h][1]){
                        continue;
                    }
                    if(x1==points[h][0]&&y1==points[h][1]){
                        continue;
                    }
                    if(x1==points[h][0]&&y==points[h][1]){
                        continue;
                    }
                    if(x==points[h][0]&&y1==points[h][1]){
                        continue;
                    }
                    int x2=points[h][0];
                    int y2=points[h][1];

                    if(x2>=x&&x2<=x1&&y2<=y&&y2>=y1){
//                        System.out.printf("%s %s, %s %s, %s %s\n", x, y, x1, y1, x2, y2);
                        isValid=false;
                    }
                }
                if(isValid){
//                    System.out.printf("%s %s, %s %s\n", x, y, x1, y1);
                    rs=Math.max(rs, (x1-x)*(y-y1));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an array points where points[i] = [xi, yi] represents (the coordinates of a point) on (an infinite plane).
        //- Your task is to find (the maximum area of a rectangle) that:
        //  + Can be formed using (four of these points) as its corners.
        //  + Does not contain any (other point) inside or on (its border).
        //  + Has (its edges) parallel to the (axes).
        //* Return (the maximum area) that you can obtain or -1 if no such rectangle is possible.
        //- It is allowed to get the rectangle including the (4 points) at (the corners).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= points.length <= 10
        //points[i].length == 2
        //0 <= xi, yi <= 100
        //All the given points are unique.
        //  + 10 points : Not too much
        //
        //- Brainstorm
        //- 2 points ==> 2 points
        //  + (Top left, bottom right)
        //
        //- Visited for x and y
        //
        //- Top-left(x,y), bottom-right(x1,y1)
        //- Point (x2,y2) in rectangle:
        //  + x2 >= x and x2<=x1
        //  + y2<=y and y2>=y1
        //
        //- Area = (x1-x+1)*(y-y1+1)
        //
//        int[][] points = {{1,1},{1,3},{3,1},{3,3}};
//        int[][] points = {{1,1},{1,3},{3,1},{3,3},{2,2}};
        int[][] points = {{1,1},{1,3},{3,1},{3,3},{1,2},{3,2}};
        //
        System.out.println(maxRectangleArea(points));
    }
}
