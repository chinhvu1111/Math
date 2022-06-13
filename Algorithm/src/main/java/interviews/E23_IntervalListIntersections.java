package interviews;

import java.util.ArrayList;
import java.util.List;

public class E23_IntervalListIntersections {

    public static int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        int n=firstList.length;
        int m=secondList.length;
        int iLeft=0;
        int iRight=0;
        int currentX = 0;
        int currentY = 0;

        if(firstList.length==1&&secondList.length==0){
            return firstList;
        }
        if(firstList.length==0&&secondList.length==1){
            return secondList;
        }

        if(firstList.length>=1){
            currentX=firstList[0][0];
            currentY=firstList[0][1];
            iLeft++;
        }else if(secondList.length>=1){
            currentX=secondList[0][0];
            currentY=secondList[0][1];
            iRight++;
        }
        List<int[]> list=new ArrayList<>();

        while (iLeft<n||iRight<m){
            int secondRightX=Integer.MAX_VALUE;
            int secondRightY=Integer.MAX_VALUE;
            int firstRightX=Integer.MAX_VALUE;
            int firstRightY=Integer.MAX_VALUE;
            int arr[][]=null;

            if(iRight<m){
                secondRightX=secondList[iRight][0];
                secondRightY=secondList[iRight][1];
            }
            if(iLeft<n){
                firstRightX=firstList[iLeft][0];
                firstRightY=firstList[iLeft][1];
            }
            if(firstRightX>secondRightX){
                arr=merge(currentX, currentY, secondRightX, secondRightY);
                iRight++;
            }else {
                arr=merge(currentX, currentY, firstRightX, firstRightY);
                iLeft++;
            }
            currentX=arr[1][0];
            currentY=arr[1][1];
            System.out.println(currentX+" "+currentY);
            if(arr[0][1]!=-1){
                list.add(arr[0]);
            }
        }
        int rs[][]=new int[list.size()][2];

        for(int i=0;i<list.size();i++){
            rs[i][0]=list.get(i)[0];
            rs[i][1]=list.get(i)[1];
        }
        return rs;
    }

    public int[][] intervalIntersectionOptimized(int[][] firstList, int[][] secondList) {

        return 0;
    }

    public static int[][] merge(int x, int y, int x1, int y1){
        int arr[][]=new int[2][2];

        if(y<x1){
            arr[0][0]=-1;
            arr[0][1]=-1;
            arr[1][0]=x1;
            arr[1][1]=y1;
            return arr;
        }
        int interX=Math.max(x, x1);
        int interY=Math.min(y, y1);
        arr[0][0]=interX;
        arr[0][1]=interY;
        int remainX=-1;
        int remainY=-1;

        if(y>y1){
            remainX=y1;
            remainY=y;
        }else{
            remainX=y;
            remainY=y1;
        }
        arr[1][0]=remainX;
        arr[1][1]=remainY;
        return arr;
    }

    public static void main(String[] args) {
//        int firstList[][]=new int[][]{{0,2},{5,10},{13,23},{24,25}};
//        int secondList[][]=new int[][]{{1,5},{8,12},{15,24},{25,26}};

//        int firstList[][]=new int[][]{};
//        int secondList[][]=new int[][]{{1,5}};
//        int firstList[][]=new int[][]{{1,5}};
//        int secondList[][]=new int[][]{};
        //Case 1:
        int firstList[][]=new int[][]{{14,16}};
        int secondList[][]=new int[][]{{7,13},{16,20}};
        System.out.println(intervalIntersection(firstList, secondList));
    }
}
