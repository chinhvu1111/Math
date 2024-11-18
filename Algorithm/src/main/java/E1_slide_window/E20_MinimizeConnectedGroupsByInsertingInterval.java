package E1_slide_window;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class E20_MinimizeConnectedGroupsByInsertingInterval {

    public static int searchFirstOutOfRangeInterval(List<int[]> mergeIntervals, int value){
        int low=0, high=mergeIntervals.size()-1;
        int rs=-1;

        while(low<=high){
            int mid=low+(high-low)/2;
            if(mergeIntervals.get(mid)[0]>value){
                rs=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return rs;
    }

    public static int minConnectedGroups(int[][] intervals, int k) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
        List<int[]> mergeIntervals = new ArrayList<>();
        int n=intervals.length;
        int i=0;

        while(i<n){
            // (1 --- 3)
            //     (2 ---- 4)
//            int[] curInterval = intervals[i];
//            int x = curInterval[0];
//            int y = curInterval[1];
//            int j=i+1;
//            //a,b,c
//            while(j<n&&y>=intervals[j][0]){
//                curInterval=intervals[j];
//                x=Math.min(curInterval[0], x);
//                y=Math.max(curInterval[1], y);
//                j++;
//            }
//            i=j-1;
            int[] curInterval = intervals[i];
            int x = curInterval[0];
            int y = curInterval[1];
            while(i+1<n&&y>=intervals[i+1][0]){
                curInterval=intervals[i+1];
                x=Math.min(curInterval[0], x);
                y=Math.max(curInterval[1], y);
                i++;
            }
            mergeIntervals.add(new int[]{x, y});
            i++;
        }
        int m = mergeIntervals.size();
        int rs=mergeIntervals.size();
        for(i=0;i<m;i++){
            int lastY = mergeIntervals.get(i)[1]+k;
            int firstOutofGroupInterval = searchFirstOutOfRangeInterval(mergeIntervals, lastY);
            int countOutOfGroup = firstOutofGroupInterval!=-1?m-firstOutofGroupInterval:0;
            rs=Math.min(rs, countOutOfGroup+i+1);
        }
        return rs;
    }

    public static int minConnectedGroupsSideWindow(int[][] intervals, int k) {
        Arrays.sort(intervals, (a, b) -> a[0] == b[0] ? Integer.compare(b[1], a[1]) : Integer.compare(a[0], b[0]));
        List<int[]> gaps = new ArrayList<>();
        int end = intervals[0][1];
        int i = 1;

        while (i < intervals.length) {
            if (intervals[i][0] > end) {
                gaps.add(new int[]{end, intervals[i][0]});
            }
            end = Math.max(end, intervals[i][1]);
            i++;
        }
        int count = 0;
        int covered = 0;
        int l = 0, r = 0;
        while (r < gaps.size()) {
            covered = gaps.get(r)[1] - gaps.get(l)[0];
            while (l < r && covered > k) {
                l++;
                covered = gaps.get(r)[1] - gaps.get(l)[0];
            }
            if (covered <= k) {
                count = Math.max(count, r - l + 1);
            }
            r++;
        }
        return gaps.size() - count + 1;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a 2D array intervals, where intervals[i] = [start-i, end-i] represents the start and the end of interval i.
        //- You are also given an integer k.
        //- You must add (exactly one) (new interval [startnew, endnew]) to the array such that:
        //  + The length of the new interval, endnew - startnew, is (at most k).
        //  + After adding, (the number of connected groups) in intervals is (minimized).
        //- A connected group of intervals is (a maximal collection of intervals) that, when considered together,
        // cover (a continuous range) from (the smallest point to the largest point) with (no gaps) between them.
        //- Here are some examples:
        //  + A group of intervals [[1, 2], [2, 5], [3, 3]] is connected because together they cover the range from 1 to 5 without any gaps.
        //- However, a group of intervals [[1, 2], [3, 4]] is not connected because the segment (2, 3) is not covered.
        //* Return (the minimum number of connected groups) after adding (exactly one) (new interval) to the array.
        //
        //Example 1:
        //
        //Input: intervals = [[1,3],[5,6],[8,10]], k = 3
        //Output: 2
        //Explanation:
        //After adding the interval [3, 5], we have (two connected groups): [[1, 3], [3, 5], [5, 6]] and [[8, 10]].
        //
        // Idea
        //1. Binary search
        //1.0,
        //- Constraint:
        //1 <= intervals.length <= 10^5
        //intervals[i] == [starti, endi]
        //1 <= starti <= endi <= 10^9
        //1 <= k <= 10^9
        //  Length<=10^5 => Time: O(n)
        //
        //** Brainstorm
        //- We need to add new group such that the number of group need to be:
        //  + Smaller
        //  + Equal
        //
        //- If we add new interval, do we have any case after adding we can decrease the number of connected group by (2)?
        //  + The number of connected group = x ==> After adding = x-2 ???
        //  => Yes
        //Ex:
        //[1,4],[5,8],[10,20], k = 7, x=3
        //We add [4,10]
        //  => The number of connected groups x = 1
        //
        //- How to calculate the number of connected group?
        //- We can merge all of the interval to the final intervals
        //
        //- Could we try putting the k after every interval?
        //* Solution:
        //- Sort interval by x
        //- Merging the intervals
        //- Trying to put the k after the last_interval:
        //  + For each putting time, try to search the position of the remaining interval such that:
        //      + last_interval[1]+k < remaining_interval[0]
        //  + current index + (n-remaining) ==> The number of connected groups
        //
        //1.1, Optimization
        //- Check add element(i) + combine với (các next elements):
        //  + Sau đó xét tiếp với (i+1) invalid điều kiện bên trên.
        //- Thay vì viết:
        //====
        //int j=i+1;
        //while(j&&check_valid) j++
        //i=j-1
        //====
        //- Vì j là invalid element ==> i=j-1
        //  + Để loop tiếp nó sẽ i++ tiếp
        //===
        //while(i+1<n&&check_valid) i++
        //  + Nó sẽ chỉ tăng (i) khi valid:
        //  ==> Đoạn này sẽ không cần assign(i) = j-1 như bên trên
        //  + Không j++ với cả invalid element
        //      + Control (i) ==> assign khó hơn
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*log(n))
        //
        //2. Slide window
        //2.0.
        //-
        //
//        int[][] intervals = {{1,3},{5,6},{8,10}};
//        int k = 3;
//        int[][] intervals = {{6,10},{16,17},{18,20},{19,20},{15,20},{4,8},{8,9}};
//        int k = 4;
        int[][] intervals = {{12,17},{20,20},{7,7},{6,11},{17,19},{12,17},{4,5},{3,7},{8,10},{15,15}};
        int k = 5;
        //#Reference:
        //1632. Rank Transform of a Matrix
        //1406. Stone Game III
        //2105. Watering Plants II
        //
        System.out.println(minConnectedGroups(intervals, k));
        System.out.println(minConnectedGroupsSideWindow(intervals, k));
    }
}
