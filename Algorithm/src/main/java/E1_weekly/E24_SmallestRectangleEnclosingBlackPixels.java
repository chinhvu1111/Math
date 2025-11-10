package E1_weekly;

import java.util.LinkedList;
import java.util.Queue;

public class E24_SmallestRectangleEnclosingBlackPixels {

    public static int[] dx={-1,0,1,0};
    public static int[] dy={0,1,0,-1};
    public static int minArea(char[][] image, int x, int y) {
        int n= image.length;
        int m=image[0].length;
        int minX=Integer.MAX_VALUE, minY=Integer.MAX_VALUE, maxX=0, maxY=0;

        Queue<int[]> currentNodes=new LinkedList<>();
        currentNodes.add(new int[]{x, y});

        while (!currentNodes.isEmpty()){
            int[] currentNode=currentNodes.poll();
//            System.out.printf("Node: %s %s\n", currentNode[0], currentNode[1]);
            image[currentNode[0]][currentNode[1]]='0';
            minX=Math.min(minX, currentNode[0]);
            minY=Math.min(minY, currentNode[1]);
            maxX=Math.max(maxX, currentNode[0]);
            maxY=Math.max(maxY, currentNode[1]);

            for(int i=0;i<dx.length;i++){
                int x1=currentNode[0]+dx[i];
                int y1=currentNode[1]+dy[i];

                if(x1>=0&&x1<n&&y1>=0&&y1<m&&image[x1][y1]=='1'){
                    currentNodes.add(new int[]{x1, y1});
                    image[x1][y1]='0';
                }
            }
        }
//        System.out.printf("%s %s %s %s\n", maxX, maxY, minX, minY);
        return (maxX-minX+1)*(maxY-minY+1);
    }

    //Given (x,y) == '1' ==> We can do this
    public static int searchColumn(int minX, int maxX, int minY, int maxY, char[][] image, boolean findMax){
        int low=minY, high=maxY;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            boolean isValid=false;
            int k=minX;

            while(k<=maxX){
                if(image[k][mid]=='1'){
                    isValid=true;
                    break;
                }
                k++;
            }
            if(isValid){
                rs=mid;
                if(!findMax){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else{
                if(!findMax){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        //
        return rs;
    }

    public static int searchRow(int minX, int maxX, int minY, int maxY, char[][] image, boolean findMax){
        int low=minX, high=maxX;
        int rs=-1;
        while(low<=high){
            int mid=low+(high-low)/2;
            boolean isValid=false;
            int k=minY;

            while(k<=maxY){
                if(image[mid][k]=='1'){
                    isValid=true;
                    break;
                }
                k++;
            }
            if(isValid){
                rs=mid;
                if(!findMax){
                    high=mid-1;
                }else{
                    low=mid+1;
                }
            }else{
                if(!findMax){
                    low=mid+1;
                }else{
                    high=mid-1;
                }
            }
        }
        //
        return rs;
    }

    public static int minAreaBinarySearch(char[][] image, int x, int y) {
        int n= image.length;
        int m=image[0].length;
        int minYBlackCol = searchColumn(0, n-1, 0, y, image, false);
        int maxYBlackCol = searchColumn(0, n-1, y, m-1, image, true);
        int minXBlackCol = searchRow(0, x, 0, m-1, image, false);
        int maxXBlackCol = searchRow(x, n-1, 0, maxYBlackCol, image, true);
//        System.out.printf("%s %s, %s %s\n", minYBlackCol, maxYBlackCol, minXBlackCol, maxXBlackCol);
        return (maxXBlackCol-minXBlackCol+1)*(maxYBlackCol-minYBlackCol+1);
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given an m x n binary matrix image where 0 represents a (white) pixel and 1 represents a (black) pixel.
        //- The black pixels are connected (i.e., there is only one black region).
        //  + Pixels are connected horizontally and vertically.
        //- Given (two integers x and y) that represents the location of (one of the black pixels),
        //* return (the area of the smallest) (axis-aligned) rectangle that encloses (all black pixels).
        //- You must write an algorithm with less than O(mn) runtime complexity
        //
        //Ex:
        //Input: image =
        // [
        //  ["0","0","1","0"],
        //  ["0","1","1","0"],
        //  ["0","1","0","0"]
        //],
        //x = 0, y = 2
        //Output: 6
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //m == image.length
        //n == image[i].length
        //1 <= m, n <= 100
        //image[i][j] is either '0' or '1'.
        //0 <= x < m
        //0 <= y < n
        //image[x][y] == '1'.
        //The black pixels in the image only form one component.
        //
        //* Brainstorm:
        //- The rectangle location determined by using:
        // a    b
        // c    d
        //  + a and d
        //- a(min-x, min-y)
        //- d(max-x, max-y)
        //- Using BFS
        // a(min-x, min-y)    b(max-x, min-y)
        // c(min-x, max-y)    d(max-x, max-y)
        //- area = (max-x - min-x)*(max-y - min-y)
        //
        //- You must write an algorithm with less than O(mn) runtime complexity?
        //  + Binary search?
        //- Find min, max ==> Just need to use binary search
        //
        //
        //* Explanation:
        //
        //
        //1.1, Case
        //
        //
        //1.2, Optimization
        //Given (x,y) == '1' ==> We can do the binary search
        //============================
//        int minYBlackCol = searchColumn(0, n-1, 0, y, image, false);
//        int maxYBlackCol = searchColumn(0, n-1, y, m-1, image, true);
//        int minXBlackCol = searchRow(0, x, 0, m-1, image, false);
//        int maxXBlackCol = searchRow(x, n-1, 0, maxYBlackCol, image, true);
        //
        //1.3, Complexity
        //- Space: O(1)
        //- Time: O(n*m) ==> O(m*log(n)+n*log(m))
        //
        char[][] image =
                {
                        {'0','0','1','0'},
                        {'0','1','1','0'},
                        {'0','1','0','0'}
                };
        int x = 0, y = 2;
//        System.out.println(minArea(image, x, y));
        System.out.println(minAreaBinarySearch(image, x, y));
    }
}
