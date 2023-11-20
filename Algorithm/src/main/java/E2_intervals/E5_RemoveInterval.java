package E2_intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class E5_RemoveInterval {

    public static int findIndexLeft(int[][] intervals, int value, int low, int high){

        //- Ta sẽ có removed interval có thể là :
        //[a, b)
        //+ Với a ta sẽ tìm interval ở rìa về phía left có:
        //  + (x, y) (a, b): y<=a, ta tìm y để xác định biên
        //  --> (y<=a) ==> (y max nhất) + (y<=a)
        //+ Với b ta sẽ tìm interval ở rìa về phía right có:
        //  + (a, b), (x, y) : b<=x
        //  ==> Ta cần tìm (x min nhất) + (x>=b)
        //
        //intervals=(0,2], (4,6], (9,15]
        //removed_interval= [3,7]
        //+ So sánh 3 --> y
        //+ mid=1
        //+ 3 > intervals[mid][0] && 3 <= intervals[mid][1]: phù hợp return
        //+ 3 < intervals[mid][1] : high=mid-1
        //  Ex: (0,2][mid], (3), (4,6]
        //+ mid=0
        //+ 3 >= intervals[mid][1] : rs=mid, low=mid+1
        //  Ex: (0,2], (3), (4,6][mid]
        //
        //+ So sánh 7
        //Ex:
        //Search 7 trong [5,7)
        //[2,7],[7,10]
        //==> Lấy [7,10], 7>=7 (Vẫn lấy [7,10))
        //Ex:
        //Search 5 trong [5,7)
        //[2,5],[5,10]
        //==> Lấy [2,5], 5<=5 (Vẫn lấy [2,5))
        //
        //** NOTE:
        //==> Trường hợp == ta sẽ luôn lấy
        //
        //+ mid=1
        //+ 3 > intervals[mid][0] && 3 <= intervals[mid][1]: phù hợp return
        //+ 3 > intervals[mid][0] : low=mid+1
        //   Ex: 3>2, 2.1>2
        //   3>=3 --> Ta sẽ không thu kết quả =3 (Vì y được định nghĩa là index right remove)
        //+ 3 <= intervals[mid][0] : rs=mid, high=mid-1
        //   [3,5], search 4:
        //  Ex: (0,2][mid], (3), (4,6]
        //+ mid=0
        //+ 3 > intervals[mid] : rs=mid, low=mid+1
        //  Ex: (0,2], (3), (4,6][mid]
        //
        //
        int mid=low+(high-low)/2;

        if(low>high){
            return -1;
        }
        int curRs=-1;

        if(intervals[mid][0]<value&&value<=intervals[mid][1]){
            return mid;
        }else if(value<intervals[mid][1]){
            high=mid-1;
        }else{
            //(3,5), (6,10)
            //Search 7
            //7>=5
            low=mid+1;
        }
        curRs=findIndexLeft(intervals, value, low, high);
        if(curRs==-1){
            return mid;
        }
        return curRs;
    }

    public static int findIndexRight(int[][] intervals, int value, int low, int high){

        if(low>high){
            return -1;
        }
        int mid=low+(high-low)/2;
        int curRs=-1;

        if(intervals[mid][0]<value&&value<=intervals[mid][1]){
            return mid;
        }else if(value>intervals[mid][0]){
            low=mid+1;
        }else{
            //(3,5), (6,10)
            //Search 7
            //7>=5
            high=mid-1;
        }
        curRs=findIndexLeft(intervals, value, low, high);
        if(curRs==-1){
            return mid;
        }
        return curRs;
    }

    public static List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        int n=intervals.length;
        //Time : O(log(N))
        //Space : O(log(N))
        int leftIndex=findIndexLeft(intervals, toBeRemoved[0], 0, n-1);
        //Time : O(log(N))
        int rightIndex=findIndexRight(intervals, toBeRemoved[1], 0, n-1);
//        System.out.printf("%s %s\n", leftIndex, rightIndex);
        List<List<Integer>> rs=new ArrayList<>();

        //Space : O(N)
        //Time : O(N)
        //value>=intervals[mid][1]
        //(1,4) search 6
        if(intervals[leftIndex][1]>toBeRemoved[0]&&intervals[leftIndex][0]<toBeRemoved[0]){
            List<Integer> curList=new ArrayList<>();
            curList.add(intervals[leftIndex][0]);
            curList.add(toBeRemoved[0]);
            rs.add(curList);
            leftIndex--;
        }else while(leftIndex>=0&&toBeRemoved[0]<=intervals[leftIndex][0]){
            leftIndex--;
        }
        //4 (2,3)
        if(leftIndex>=0&&intervals[leftIndex][1]<=toBeRemoved[0]){
            for(int i=leftIndex;i>=0;i--){
                List<Integer> curList=new ArrayList<>();
                curList.add(intervals[i][0]);
                curList.add(intervals[i][1]);
                rs.add(curList);
            }
        }
        //Time : O(N)
        Collections.reverse(rs);
        //rightIndex<=intervals[mid][0]
        // [1, 5) search 4
        if(intervals[rightIndex][1]>toBeRemoved[1]&&intervals[rightIndex][0]<toBeRemoved[1]){
            List<Integer> curList=new ArrayList<>();
            curList.add(toBeRemoved[1]);
            curList.add(intervals[rightIndex][1]);
            rs.add(curList);
            rightIndex++;
        }else while(rightIndex<n&&intervals[rightIndex][1]<=toBeRemoved[1]){
            rightIndex++;
        }
        if(rightIndex<n&&intervals[rightIndex][0]>=toBeRemoved[1]){
            for(int i=rightIndex;i<n;i++){
                List<Integer> curList=new ArrayList<>();
                curList.add(intervals[i][0]);
                curList.add(intervals[i][1]);
                rs.add(curList);
            }
        }
        return rs;
    }

    public static List<List<Integer>> removeIntervalLinearSearch(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> rs=new ArrayList<>();

        for(int[] interval: intervals){
            if(interval[0]>toBeRemoved[1]||interval[1]<toBeRemoved[0]){
                rs.add(Arrays.asList(interval[0], interval[1]));
            }else{
                if(interval[0]<toBeRemoved[0]){
                    rs.add(Arrays.asList(interval[0], toBeRemoved[0]));
                }
                if(toBeRemoved[1]<interval[1]){
                    rs.add(Arrays.asList(toBeRemoved[1], interval[1]));
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given intervals contains (the sorted disjoint intervals)
        //  + where intervals[i] = [ai, bi] represents the interval [ai, bi) (ai <=x <bi)
        //- Given toBeRemoved contains an interval to remove (only one interval)
        //* return the set of real numbers such that every x in the set is in intervals but not in toBeRemoved.
        //  + Your answer should be a sorted list of disjoint intervals as described above.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= intervals.length <= 10^4
        //-10^9 <= ai < bi <= 10^9
        //
        //- Brainstorm
        //- Ở đây sẽ dùng binary search để xác đinh đầu cuối --> vì nó chỉ có duy nhất 1 interval
        //  + Vì intervals cũng được sort + disjoint set nữa thế nên ta có thể dùng binary search
        //==> Sau đó ta cut giữa là xong.
        //Ex:
        //  0 -- 1 -- 2 -- 3 -- 4 -- 5
        //            2 -- 3
        //  0 -- 1 -- 2    3 -- 4 -- 5
        // [1,2): 2 chỉ thuộc toBeRemoved
        // [3,4): 3 thuộc intervals + không thuộc toBeRemoved
        //- Ta có các trường hợp sau đây:
        //- Case 1:
        //+ Khoảng cách trừ nằm ở giữa
        //  0 -- 1 -- 2 -- 3 -- 4 -- 5
        //Trừ        [2 -- 3]
        //            2 -- 3
        //Trừ        [2 -- 3)
        //=
        //  0 -- 1 -- 2    3 -- 4 -- 5
        //=           2)  [3
        //Case 2:
        //+ Khoảng cách trừ nằm ở 1 trong 2 đầu
        //+ Đầu bên phải
        //  0 -- 1 -- 2 -- 3 -- 4 -- 5
        //                     [4 -- 5)
        //Trừ
        //                           5 -- 6
        //                          [5 -- 6)
        //=
        //  0 -- 1 -- 2 -- 3 -- 4 -- 5
        //==> Giữ nguyên
        //+ Đầu bên trái
        //       1 -- 2 -- 3 -- 4 -- 5
        //Trừ
        //  0 -- 1
        // [0 -- 1)
        //=
        //       1 -- 2 -- 3 -- 4 -- 5
        //==> Giữ nguyên
        //
        // 1 -- 2 -- 3
        //-
        //     [2 -- 3)
        //=
        // 1 -- 2
        //
        //
//        int[][] intervals = {{0,5}};
//        int[] toBeRemoved = {2,3};
//        int[][] intervals = {{0,2},{3,4},{5,7}};
//        int[] toBeRemoved = {1,6};
//        int[][] intervals = {{0,5}};
//        int[] toBeRemoved = {2,3};
//        int[][] intervals = {{-5,-4},{-3,-2},{1,2},{3,5},{8,9}};
//        int[] toBeRemoved = {-1,4};
//        int[][] intervals = {{0,2},{3,4},{5,7}};
//        int[] toBeRemoved = {7,10};
        //- Test case này liên quan đến cases:
        // (a,b)(x, y)
        //+ Yêu cầu b>=x nhưng :
        //  + a -------- b
        //       x -- y
        //  Cho dù x<b ==> Nhưng ta vẫn lấy index của nó thế nên:
        //  + Ta sẽ while cho đến khi:
        //  + a -------- b
        //           x ----- y
        //  + while(rightIndex++ && tobeRemoved[1]>=intervals[rightIndex][1]
        //  ta cần while(a>x) rightIndex++
        //
        //+ (x, y), (a, b)
        //  + yêu cầu: y<=a nhưng:
        //       a ---------- b
        //          x --- y
        //==> Run cho đến khi a<=x
        //  +    a ---------- b
        //    x ----- y
        //  ==> Ta sẽ while cho đến khi gặp cái này
        //  while(leftIndex-- && toBeRemoved[0]>=intervals[leftIndex][0])
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space: O(N+log(N))
        //- Time : O(N+log(N))
        //
        //2.
        //2.0,
        //- Liên quan đến linear traverse
        //--> So sánh để chọn range thôi
        //Ex:
        // a ---------------- b
        //       x ----- y
        //=> [a,x), [y,b)
        //
        //Ex:
        //     a -------- b
        //x ----- y
        //=> [x, a), [y,b)
        //
        //Ex:
        // a -------- b
        //        x ----- y
        //=> [a,x), [b,y)
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //+ N is the number of intervals array
        //- Space : O(1)
        //- Time : O(n)
        //
        int[][] intervals = {{-698194477,-688012389},{-532235046,-432247214},{-242061248,-51761308},{-8634180,-3871523},{21293476,209050858},{288656927,301985473},{477097117,509345926},{556786949,682971769},{753599205,775733165},{793037104,853284467}};
        int[] toBeRemoved = { -193104797,872740618};
        System.out.println(Integer.MAX_VALUE-Math.pow(10, 9));
        System.out.println(removeInterval(intervals, toBeRemoved));
        System.out.println(removeIntervalLinearSearch(intervals, toBeRemoved));
        //#Reference:
        //51. N-Queens
        //1626. Best Team With No Conflicts
        //317. Shortest Distance from All Buildings
        //2303. Calculate Amount Paid in Taxes
        //2919. Minimum Increment Operations to Make Array Beautiful
        //2660. Determine the Winner of a Bowling Game
        //2098. Subsequence of Size K With the Largest Even Sum
        //2111. Minimum Operations to Make the Array K-Increasing
        //2658. Maximum Number of Fish in a Grid
    }
}
