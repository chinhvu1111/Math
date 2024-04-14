package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E126_InsertInterval {

    public static int binarySearch(int[][] intervals, int x, int n){
        int low=0;
        int high=n-1;
        int rs=0;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(intervals[mid][0]>x){
                high=mid-1;
            }else{
                rs=mid;
                low=mid+1;
            }
        }
        return rs;
    }

    public static int[][] insert(int[][] intervals, int[] newInterval) {
        int n=intervals.length;

        if(n==0){
            return new int[][]{{newInterval[0],newInterval[1]}};
        }
        //arr = [x,y,z,...]
        //newInterval = [minX,maxX]
        List<int[]> rsList=new ArrayList<>();
        int startIndex = binarySearch(intervals, newInterval[0], n);
        for(int i=0;i<startIndex;i++){
            rsList.add(new int[]{intervals[i][0], intervals[i][1]});
        }
        int min=newInterval[0];
        int max=newInterval[0];
        if(intervals[startIndex][1]>=newInterval[0]&&intervals[startIndex][1]<newInterval[1]){
            max=newInterval[1];
            min=Math.min(intervals[startIndex][0], newInterval[0]);
        }else if(intervals[startIndex][1]>=newInterval[1]&&intervals[startIndex][0]<=newInterval[0]){
            max=Math.max(intervals[startIndex][1], newInterval[1]);
            min=Math.min(intervals[startIndex][0], newInterval[0]);
        }else if(intervals[startIndex][1]<newInterval[0]){
            min=newInterval[0];
            max=newInterval[1];
            rsList.add(new int[]{intervals[startIndex][0], intervals[startIndex][1]});
        }else if(newInterval[1]>=intervals[0][0]&&newInterval[1]<=intervals[0][1]){
            max=intervals[0][1];
            min=Math.min(intervals[startIndex][0], newInterval[0]);
        }else if(newInterval[1]>=intervals[startIndex][1]&&newInterval[0]<=intervals[startIndex][0]){
            max=Math.max(intervals[startIndex][1], newInterval[1]);
            min=Math.min(intervals[startIndex][0], newInterval[0]);
        }else if(newInterval[1]<intervals[startIndex][0]){
            min=intervals[startIndex][0];
            max=intervals[startIndex][1];
            rsList.add(new int[]{newInterval[0], newInterval[1]});
        }
        for(int i=startIndex+1;i<n;i++){
            //(x,y) and (a,b)
            //+ x --- y
            //     a ---- b
            //+ x --- y
            //           a ---- b
            //
            if(max<intervals[i][0]){
                rsList.add(new int[]{min, max});
                min=intervals[i][0];
                max=intervals[i][1];
            }else if(max<intervals[i][1]){
                max=intervals[i][1];
            }
        }
        rsList.add(new int[]{min, max});

        for (int[] cur: rsList){
            System.out.printf("%s %s\n", cur[0], cur[1]);
        }
        int[][] ans=new int[rsList.size()][2];

        for(int i=0;i<rsList.size();i++){
            ans[i][0]=rsList.get(i)[0];
            ans[i][1]=rsList.get(i)[1];
        }
        return ans;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an array of non-overlapping intervals where intervals[i] = [start-i, end-i]
        //represent the start and the end of the ith interval and intervals is sorted in ascending order by starti.
        //- You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
        //- Insert (newInterval) into intervals such that intervals is still sorted in ascending order by start-i and intervals
        //still does not have any overlapping intervals (merge overlapping intervals if necessary).
        //* Return intervals after the insertion.
        //- Note that you don't need to modify intervals in-place. You can make a new array and return it.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //0 <= intervals.length <= 10^4
        //intervals[i].length == 2
        //0 <= start-i <= end-i <= 10^5
        //intervals is sorted by starti in ascending order.
        //newInterval.length == 2
        //0 <= start <= end <= 10^5
        //
        //- Brainstorm
        //Ex:
        //intervals = [[1,3],[6,9]], newInterval = [2,5]
        //[1,3],[2,5],[6,9]
        //- Khi sort + merge từng đôi thì ta sẽ thu được kết quả
        //[1,5],[6,9]
        //
//        int[][] intervals = {{1,3},{6,9}};
//        int[] newInterval = {2,5};

//        int[][] intervals = {{1,2},{3,5},{6,7},{8,10},{12,16}};
//        int[] newInterval = {4,8};
//        int[][] intervals = {};
//        int[] newInterval = {5,7};
//        int[][] intervals = {{1,5}};
//        int[] newInterval = {5,7};
//        int[][] intervals = {{1,5}};
//        int[] newInterval = {0,3};
//        int[][] intervals = {{1,5}};
//        int[] newInterval = {0,0};
//        int[][] intervals = {{1,5}};
//        int[] newInterval = {2,3};
        int[][] intervals = {{1,5},{6,8}};
        int[] newInterval = {0,9};
        insert(intervals, newInterval);
    }
}
