package E1_daily;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeSet;

public class E383_FindTheNumberOfWaysToPlacePeopleI {

    public static int numberOfPairsWrong(int[][] points) {
        int n=points.length;
        int m=points[0].length;
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        TreeSet<int[]> sortListX=new TreeSet<>((o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[2] - o1[2];
        });
        TreeSet<int[]> sortListY=new TreeSet<>((o1, o2) -> {
            if (o1[1] != o2[1]) {
                return o1[1] - o2[1];
            }
            return o2[2] - o1[2];
        });
        int rs=0;

        for(int i=0;i<n;i++){
            sortListX.add(new int[]{points[i][0], points[i][1], i});
            sortListY.add(new int[]{points[i][0], points[i][1], i});
        }

        for(int i=0;i<n;i++){
            for(int j=i+1;j<m;j++){
                if(points[i][0]==points[j][0]){
                    continue;
                }
                if(points[i][1]<=points[j][1]){
                    continue;
                }
                //Check the left upper point
                int[] findLessThanMinX = sortListX.floor(new int[]{points[i][0]-1, -1, -1});
                if(findLessThanMinX!=null){
                    continue;
                }
                int[] findGreaterThanMinY = sortListY.ceiling(new int[]{-1, points[i][1]+1, -1});
                if(findGreaterThanMinY!=null){
                    continue;
                }
                //Check the right down point
                int[] findGreaterThanMinX = sortListX.ceiling(new int[]{points[j][0]+1, -1, -1});
                if(findGreaterThanMinX!=null){
                    continue;
                }
                int[] findLessThanMinY = sortListX.floor(new int[]{-1, points[j][1]-1, -1});
                if(findLessThanMinY!=null){
                    continue;
                }
                rs++;
            }
        }
        return rs;
    }

    public static int numberOfPairs(int[][] points) {
        int n=points.length;
        int rs=0;
        Arrays.sort(points, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return o1[0] - o2[0];
            }
            return o2[1] - o1[1];
        });
        if(n==2){
            if(points[0][1]<points[1][1]){
                return 0;
            }
            return 1;
        }
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                boolean isValid=true;
                for (int k = 0; k < n; k++) {
//                    if(points[i][0]==points[j][0]){
//                        continue;
//                    }
                    if(points[i][1]<points[j][1]){
                        isValid=false;
                        break;
                    }
                    if(i==k||j==k){
                        continue;
                    }
                    if(points[i][0]<=points[k][0]&&points[j][0]>=points[k][0]
                    &&points[j][1]<=points[k][1]&&points[i][1]>=points[k][1]){
                        isValid=false;
                        break;
                    }
                }
                if(isValid){
                    rs++;
                }
            }
        }
        return rs;
    }

    public static int numberOfPairsOptimization(int[][] points) {
        // Sort by x ascending, and y descending
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) return b[1] - a[1];
            return a[0] - b[0];
        });

        int n = points.length;
        int ans = 0;

        //(x,y),(x1,y1)
        //x<=x1
        //y>=y1
        //- Find:
        //x<=x2<=x1
        //y>=y2>=y1
        //
        //- x<=x2<=x1 (By default it is always true)
        //- Follow (y>=y2>=y1)
        //- Found y2
        //  +
        for (int i = 0; i < n - 1; i++) {
            //y
            int pi2 = points[i][1];
            int minh = Integer.MIN_VALUE;

            for (int j = i + 1; j < n; j++) {
                //- x luôn tăng dần:
                //  + Nếu x -> x1 mà không tồn tại (y>=y2>=y1) thì coi như pair này valid
                //if (y1<=y) & (y1>y2)
                //- y2 tăng dần lên:
                //  + Nếu y1 càng tăng --> độ rộng càng nới
                //** Main point:
                //- points[j][1] > minh
                //==> Point này nằm dưới points[j]
                //  + Mà minh là max giữa [x,x1]
                //  Rule: y>=y2>=y1
                //  + Tìm y2 nhưng y1 đã lớn hơn Y của tất cả các points nằm giữa có X từ [x,x1]
                //  ==> Không còn points nào nữa từ points[i]-points[j] thoả mãn
                if (points[j][1] > minh && points[j][1] <= pi2) {
                    ans++;
                    minh = points[j][1];// extend y2
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D array points of size n x 2 representing integer coordinates of some points on a 2D plane,
        // where points[i] = [xi, yi].
        //- Count the number of pairs of points (A, B), where
        //  + A is on the upper left side of B, and
        //  + there are no other points in the rectangle (or line) they make (including the border),
        //  except for the points A and B.
        //* Return the count.
        //
        //Example 1:
        //Input: points = [[1,1],[2,2],[3,3]]
        //Output: 0
        //
        //Explanation:
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //2 <= n <= 50
        //points[i].length == 2
        //0 <= points[i][0], points[i][1] <= 50
        //All points[i] are distinct.
        //  + Time: O(n^3)
        //
        //* Brainstorm:
        //- Upper left:
        //  + Bigger y + smaller x
        //
        //1.1, Case
        //
        //int[][] points = {{1,1},{2,2},{3,3}};
//        int[][] points = {{6,2},{4,4},{2,6}};
//        int[][] points = {{3,1},{1,3},{1,1}};
//        int[][] points = {{0,0},{0,3}};
        int[][] points = {{0,0},{0,6},{0,3},{1,0}};
        //- n=2 ==> only 2 points so we don't need to scan other points
        //  + No scan ==> return 0 (WRONG)
        //  ==> Add if else condition
        //1.2, Optimization
        //- Monotonic method:
        //- x luôn tăng dần:
        //  + Nếu x -> x1 mà không tồn tại (y>=y2>=y1) thì coi như pair này valid
        //if (y1<=y) & (y1>y2)
        //- y2 tăng dần lên:
        //  + Nếu y1 càng tăng --> độ rộng càng nới
        //** Main point:
        //- points[j][1] > minh
        //==> Point này nằm dưới points[j]
        //  + Mà minh là max giữa [x,x1]
        //  Rule: y>=y2>=y1
        //  + Tìm y2 nhưng y1 đã lớn hơn Y của tất cả các points nằm giữa có X từ [x,x1]
        //  ==> Không còn points nào nữa từ points[i]-points[j] thoả mãn
        //
        //1.3, Complexity
        //- Time: O(n^3) => O(n^2)
        //
        //- Space: O(1)
        //
        System.out.println(numberOfPairs(points));
        System.out.println(numberOfPairsOptimization(points));
    }
}
