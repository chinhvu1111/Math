package contest;

import java.util.*;

public class E99_MaximumPointsInsideTheSquare {

    public static int getSize(int x, int y){
        return Math.max(Math.abs(x), Math.abs(y));
    }

    public static int maxPointsInsideSquare(int[][] points, String s) {
        List<int[]> pointsList=new ArrayList<>();
        int n=points.length;

        for(int i=0;i<n;i++){
            pointsList.add(new int[]{points[i][0], points[i][1], s.charAt(i)-'a'});
        }
        Collections.sort(pointsList, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int maxO1 = Math.max(Math.abs(o1[0]), Math.abs(o1[1]));
                int maxO2 = Math.max(Math.abs(o2[0]), Math.abs(o2[1]));
                return maxO1-maxO2;
            }
        });
        boolean[] visited=new boolean[26];
        int curLen=0;
        int curRs=0;
        int prevRs=0;

        for(int i=0;i<n;i++){
            int newSize = getSize(pointsList.get(i)[0], pointsList.get(i)[1]);

            if(newSize<=curLen){
                if(visited[pointsList.get(i)[2]]){
                    return prevRs;
                }
                curRs++;
            }else{
                if(visited[pointsList.get(i)[2]]){
                    return curRs;
                }
                prevRs=curRs;
                curRs++;
                curLen= newSize;
            }
            visited[pointsList.get(i)[2]]=true;
        }
        return curRs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D array points and a string s where, points[i] represents the coordinates of point i, and s[i] represents the tag of point i.
        //A valid square is a square centered at the origin (0, 0), has edges parallel to the axes, and does not contain two points with the same tag.
        //* Return the maximum number of points contained in a valid square.
        //Note:
        //- A point is considered to be inside the square if it lies on or within the square's boundaries.
        //- The side (length of the square) can be (zero).
        //* Bài này muốn tìm max points có thể trong 1 valid square + không có points nào trùng tag
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length, points.length <= 10^5
        //points[i].length == 2
        //-10^9 <= points[i][0], points[i][1] <= 10^9
        //s.length == points.length
        //points consists of distinct coordinates.
        //s consists only of lowercase English letters.
        //+ Length khá lớn
        //+ point có value cũng khá lơn
        //
        //- Brainstorm
        //- Length của square : 0 ==> max(x,y)
        //- Xác định các điểm trong square có (length = l) ntn?
        //  + Các điểm có min(abs(x), abs(y)) <= l
        //==> Sort trước ==> Mở rộng length lên là được.
        //
//        int[][] points = {{2,2},{-1,-2},{-4,4},{-3,1},{3,-3}};
//        String s = "abdca";
        int[][] points = {{16,32},{27,3},{23,-14},{-32,-16},{-3,26},{-14,33}};
        String s = "aaabfc";
        System.out.println(maxPointsInsideSquare(points, s));
    }
}
