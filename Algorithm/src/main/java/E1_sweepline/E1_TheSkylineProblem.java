package E1_sweepline;

import java.util.*;

public class E1_TheSkylineProblem {

    public static List<List<Integer>> getSkylineWrong(int[][] buildings) {
        int n= buildings.length;
        int[][] listX=new int[n*2][3];
        int index=0;

        for(int i=0;i<n;i++){
            listX[index][0]=buildings[i][0];
            listX[index][1]=buildings[i][2];
            listX[index++][2]=i;
            listX[index][0]=buildings[i][1];
            listX[index][1]=buildings[i][2];
            listX[index++][2]=i;
        }
        Arrays.sort(listX, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<List<Integer>> points=new ArrayList<>();
//        int m=listX.length;
        int i;

        for(i=0;i<n;i++){
            int maxHeight=listX[i][1];
            int prevHeight=-1;
            if(!points.isEmpty()){
                prevHeight=points.get(points.size()-1).get(1);
            }
//            int curIndex=listX[i][2];
            for(int j=0;j<n;j++){
                if(buildings[j][0]>listX[i][0]||prevHeight==buildings[j][2]){
                    break;
                }
                maxHeight=Math.max(maxHeight, buildings[j][2]);
            }
//            if(!points.isEmpty()&&maxHeight==points.get(points.size()-1).get(1)){
//                continue;
//            }
            List<Integer> curPoint=new ArrayList<>();
            curPoint.add(listX[i][0]);
            curPoint.add(maxHeight);
            points.add(curPoint);
        }
//        System.out.println(points);
        return points;
    }

    public static List<List<Integer>> getSkyline(int[][] buildings) {
        int n=buildings.length;
        List<int[]> listX=new ArrayList<>();

        for(int i=0;i<n;i++){
            listX.add(new int[]{buildings[i][0], buildings[i][2], 0});
            listX.add(new int[]{buildings[i][1], buildings[i][2], 1});
        }
        Collections.sort(listX, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        int m=listX.size();
        //
        //- Mỗi straight line:
        //  + Ta add được 2 points
        //  ==> Ta chỉ lấy 1 point
        //[....]
        //  + first/second(x) + max(height)
        //[....[
        //  + first/second(x) + first height
        //]....[
        //  + max(x) + height = 0
        //]....]
        //  + first/second(x) + second height
        List<List<Integer>> rs=new ArrayList<>();
        //[x,height,mark]
        PriorityQueue<int[]> maxHeap=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[0]-o1[0];
            }
        });

//        for(int i=0;i<m-1;i++){
//            int[] firstX=listX.get(i);
//            int[] secondX=listX.get(i+1);
//            int[] newPoints = new int[3];
//
//            while(!maxHeap.isEmpty()&&(maxHeap.peek()[1]<firstX[0]&&maxHeap.peek()[2]==1)){
//                maxHeap.poll();
//            }
//            if(!maxHeap.isEmpty()){
//                firstX[1]=Math.max(maxHeap.peek()[1],firstX[1]);
//            }
//            if(firstX[2]==0&&secondX[2]==1){
//                newPoints[0]=firstX[0];
////                newPoints[1]=secondX[0];
//                newPoints[2]=Math.max(firstX[1], secondX[1]);
//            }else if(firstX[2]==0&&secondX[2]==0){
//                newPoints[0]=firstX[0];
////                newPoints[1]=firstX[0];
//                newPoints[2]=firstX[1];
//            }else if(firstX[2]==1&&secondX[2]==0){
//                newPoints[0]=firstX[0];
////                newPoints[1]=secondX[0];
//            }else if(firstX[2]==1&&secondX[2]==1){
//                newPoints[0]=firstX[0];
////                newPoints[1]=secondX[0];
//                newPoints[2]=secondX[1];
//            }
//
//            if(rs.isEmpty() || rs.get(rs.size() - 1).get(1) != newPoints[2]){
//                List<Integer> newList=new ArrayList<>();
//                newList.add(newPoints[0]);
//                newList.add(newPoints[2]);
//                rs.add(newList);
//            }
//            maxHeap.add(firstX);
//        }
        maxHeap.add(listX.get(0));
        for(int i=1;i<m;i++){
            int[] curX=listX.get(i);
            int[] newPoint=new int[2];

            while(!maxHeap.isEmpty()&&(maxHeap.peek()[0]<curX[0]&&maxHeap.peek()[2]==1)){
                maxHeap.poll();
            }
            //- Mỗi straight line:
            //  + Ta add được 2 points
            //  ==> Ta chỉ lấy 1 point
            //[....]
            //  + first/second(x) + max(height)
            //[....[
            //  + first/second(x) + first height
            //]....[
            //  + max(x) + height = 0
            //]....]
            //  + first/second(x) + second height
            int maxHeight=0;
            if(!maxHeap.isEmpty()){
                maxHeight=maxHeap.peek()[1];
            }
            newPoint[0]=listX.get(i-1)[0];
            if(curX[2]==1){
                maxHeight=Math.max(maxHeight, curX[1]);
            }
            newPoint[1]=maxHeight;
            if(rs.isEmpty() || rs.get(rs.size() - 1).get(1) != newPoint[1]){
                List<Integer> newList=new ArrayList<>();
                newList.add(newPoint[0]);
                newList.add(newPoint[1]);
                rs.add(newList);
            }
            maxHeap.add(curX);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
        //- Given the locations and heights of all the buildings, return the skyline formed by these buildings collectively.
        //
        //The geometric information of each building is given in the array buildings where buildings[i] = [lefti, righti, heighti]:
        //  + left-i is the x coordinate of the left edge of the ith building.
        //  + right-i is the x coordinate of the right edge of the ith building.
        //  + height-i is the height of the ith building.
        //
        //- You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.
        //- The skyline should be represented as a list of "key points" sorted by their x-coordinate in the form [[x1,y1],[x2,y2],...].
        //- Each key point is the left endpoint of some horizontal segment in the skyline except the last point in the list,
        // which always has a y-coordinate 0 and is used to mark the skyline's termination where the rightmost building ends.
        // Any ground between the leftmost and rightmost buildings should be part of the skyline's contour.
        //* Note: There must be no consecutive horizontal lines of equal height in the output skyline.
        // For instance, [...,[2 3],[4 5],[7 5],[11 5],[12 7],...] is not acceptable;
        // the three lines of height 5 should be merged into one in the final output as such: [...,[2 3],[4 5],[12 7],...]
        //
        //- Cho các hình xếp chồng + giao nhau sẽ return lại các points ở vị trí even + điểm cuối kết thúc x (Cái nãy luôn có even index):
        //  + Vì chỉ cần (những points đó)
        //  ==> Ta có thể xác định được (hình dạng của đa giác)
        //
        // Idea
        //1. Binary search
        //1.0,
        //- Constraint:
        //1 <= buildings.length <= 10^4
        //0 <= left-i < right-i <= 2^31 - 1
        //1 <= height-i <= 2^31 - 1
        //buildings is sorted by left-i in (non-decreasing) order.
        //
        //** Brainstorm
        //- We use the (sweep line approach) to cut vertically base on all of (x axis)
        //- For each cut flat:
        //  + We choose the slice with max y value
        //      + That means that for each x value we will the max height for that.
        //  + For each x, we can find the range from (i to j) include x:
        //  Ex:
        //  1,2,6,[3,5,7,2],10
        //  ==> (index=3, index=6) has max height with (value=7)
        //  + The main point that we find max height of all of the areas
        //      + We can get max height between (min_x, max_x)
        //- At the same flat, we can get multiple points with the same height:
        //  + We need to deduplicate the points by getting the (first point) of this slice
        //
        //- "WRONG" Rules:
        //- For each vertical line, we cut the multiple buildings:
        //  + We will get the point with max height
        //  + We also need to deduplicate the points have the same height as the previous point
        //- How to find intersection?
        //  + The current x will be laied on multiple buildings:
        //      + Find the max height
        //
        //Ex:
        //buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
        //- We need to find (the real intersection) instead of only (max value) of (the specific x)
        //- For (each x):
        //  + We will add (multiple heights) with (the increase order)
        //- Nếu dùng tư duy points sẽ "Không đúng" vì:
        //  + 1 (x axis) có thể cut (multiple buildings) ==> m points thì cần phải (keep only 1 point) with (max height)
        //      + Nhưng sẽ bị case
        //      -----(ko lấy điểm này)
        //      |   |
        //      |   |
        //          ----
        //        (Chỉ lấy điểm trên này)
        //==> Việc lấy điểm max nhất không còn đúng nữa
        //- Idea ở đây của nó là tìm hình dạng:
        //==> Ta sẽ không focus vào point
        //- Ta sẽ cut thành cách rectangle độc lập thay vì chỉ dùng (x axes)
        //- Ta sẽ cần tìm giao theo (straight line):
        //  + Lấy (straight line) với (max height)
        //- Với các node cùng height:
        //  + Ta cũng deduplicate đi
        //Ex:
        //buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
        //
        //- Detach points skill:
        //buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
        //[2,10],[9,10],[3,15],[7,15],[5,12],[12,12],[15,10],[20,10],[19,8],[24,8]
        //  ==> sort by x
        //[2,10],[3,15],[5,12],[7,15],[9,10],[12,12],[15,10],[19,8],[20,10],[24,8]
        //- Nếu detach the buildings ntn:
        //  + Có thể [x][x1] ==> mình không thể xác định được [x1] là
        //      + start of new build
        //          + Start tức là ta không thể lấy height của nó
        //      + end of the previous building
        //          + End thì tức là ta có thể lấy height của nó
        //Ex:
        //[xxxxxxxx]
        //    [yyyyyyyyyyy]
        //                  [zzzzzz]
        //xxxx|max..........[zzzzzz]
        //Mỗi x sẽ cần được phân biệt là:
        //  + Start hay end
        //
        //- Refactor detach points skill:
        //buildings = [[2,9,10],[3,7,15],[5,12,12],[15,20,10],[19,24,8]]
        //[2,10,0],[9,10,1],[3,15,0],[7,15,1],[5,12,0],[12,12,1],[15,10,0],[20,10,1],[19,8,0],[24,8,1]
        //  ==> sort by x
        //[2,10],[3,15],[5,12],[7,15],[9,10],[12,12],[15,10],[19,8],[20,10],[24,8]
        //
        //- Mỗi straight line:
        //  + Ta add được 2 points
        //  ==> Ta chỉ lấy 1 point
        //[....]
        //  + first/second(x) + max(height)
        //[....[
        //  + first/second(x) + first height
        //]....[
        //  + max(x) + height = 0
        //]....]
        //  + first/second(x) + second height
        //
        //- Nếu làm như hiện tại vẫn chưa đủ:
        //==> Làm slide window mới valid
        //Ex:
        //[9,9,9,9,9]
        //  [7,7,7,7,7,7,7,7,7,7,7]
        //      [5,5,5,5,5,5,5,5,5,5,5,5,5,5]
        //          [       ] = 7
        //                        [         ] = 5 (ngoài khoảng =5)
        //+ 9 xuất hiện sau 7
        //  ==> 9 kết hợp với 5 ==> cần phải xét cả 7 nữa
        //
        //+ add(x1) ==> add line có (x>=x1):
        //  ===> pop những thằng có (x<x1)
        //- Dùng slide window + priority queue(sort by height)
        //  + Pop max with value is less than (current x)
        //
        //
        int[][] buildings = {{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}};
//        System.out.println(getSkylineWrong(buildings));
        System.out.println(getSkyline(buildings));
    }
}
