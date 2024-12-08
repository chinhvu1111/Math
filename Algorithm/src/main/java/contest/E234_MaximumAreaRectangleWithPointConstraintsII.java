package contest;

public class E234_MaximumAreaRectangleWithPointConstraintsII {

    public static long maxRectangleArea(int[] xCoord, int[] yCoord) {
        return 0L;
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
        //1 <= xCoord.length == yCoord.length <= 2 * 10^5
        //0 <= xCoord[i], yCoord[i] <= 8 * 10^7
        //All the given points are unique.
        //
        //- Brainstorm
        //- Input of points give me the hint:
        //  + Binary search?
        //
        //Example 3:
        //Input: xCoord = [1,1,3,3,1,3], yCoord = [1,3,1,3,2,2]
        //Output: 2
        //- sort by x ==> find the point with (bigger x)
        //  + From point(x,y) ==> Find the point(x1,y1) with:
        //
    }
}
