package E1_heap_priority_queue;

import java.util.Arrays;
import java.util.Comparator;

public class E3_MinimumNumberOfArrowsToBurstBalloons {

    public static int findMinArrowShots(int[][] points) {
        int n=points.length;
        if(n==0){
            return 0;
        }
        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));
        int rs=1;
        // int x=points[0][0];
        int y=points[0][1];
//        System.out.println(y);

        for(int i=1;i<n;i++){
            // (a, b), (c, d)
            //(2, 5), (2, 9)
            if(points[i][0]>y){
                rs++;
//                x=points[i][0];
                y=points[i][1];
            }else{
//                x=points[i][0];
                y=Math.min(points[i][1], y);
            }
        }
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement
        //- Có n quả bóng bay được stretch from [x-start, y-end]
        //- Shoot a baloon successfully when (x-start <= x <= y-end)
        //* Return minnimum number of arrows that must be shot to burst all balloons.
        //
        //** Idea
        //- Constraint
        //1 <= points.length <= 10^5
        //points[i].length == 2
        //-2^31 <= xstart < xend <= 2^31 - 1
        //
        //- Brainstorm
        //Ex:
        //Input: points = [[1,2],[2,3],[3,4],[4,5]]
        //Output: 2
        //Explanation: The balloons can be burst by 2 arrows:
        //- Shoot an arrow at x = 2, bursting the balloons [1,2] and [2,3].
        //- Shoot an arrow at x = 4, bursting the balloons [3,4] and [4,5].
        //
        //- Sort theo x-start trước
        //Case 1:
        //Ex:
        // [1,5],[2,5],[3,4] ==> Ở đây ta chỉ cần shoot only one time at 4 ==> 3 baloons
        // [1,5] and [2,5] = [2,5]
        // [2,5] and [3,4] = [3,4]
        //=> Idea là sort trước sau đó ta thu gọn dần các phần tử giao nhau ==> Nếu phần tử mới không giao với phần tử cũ ==> rs++
        //
        //Case 2:
        //Ex:
        // [1,5],[2,9],[6,7] ==> Ở đây ta chỉ cần shoot only one time at 4 ==> 3 baloons
        //+ Cách 1:
        // [1,5] and [2,9] = [2,5]
        // [2,5] and [6,7] = không giao nhau
        //+ rs=2
        //
        //+ Cách 2:
        // [2,9] and [6,7] = [6,7]
        // [6,7] and [1,5] = = không giao nhau
        //+ rs=2
        //==> Kết quả có vẻ không thay đổi.
        //
        //1.1, Optimization
        //1.2, Complexity
        //+ n is the number of baloons
        //- Time : O(n*log(n))
        //- Space : O(n)/ O(log(n)) (Sort algorithm)
        //+ The space complexity of the sorting algorithm depends on the implementation of each program language.
        //+ For instance, the list.sort() function in Python is implemented with the Timsort algorithm whose space complexity is O(N).
        //+ In Java, the Arrays.sort() is implemented as a variant of quicksort algorithm whose space complexity is O(logN)
        //#Reference:
        //253. Meeting Rooms II
        int[][] arr={{-2147483646,-2147483645},{2147483646,2147483647}};
        System.out.println(findMinArrowShots(arr));
    }

}
