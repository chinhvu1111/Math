package E2_intervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class E3_MeetingRoomsII {

    public static int minMeetingRooms(int[][] intervals) {
        //Time : O(n*log(n))
        Arrays.sort(intervals, (a, b) -> (a[0]-b[0]));
        int rs=0;
        int n=intervals.length;
        //Space : O(n)
        PriorityQueue<int[]> meetings=new PriorityQueue<>((a, b) -> (a[1]-b[1]));
        int usedRooms=0;

        //Time : O(n)
        for(int i=0;i<n;i++){
            int start=intervals[i][0];
            int end=intervals[i][1];

            //Time : O(1)
            while(!meetings.isEmpty()&&meetings.peek()[1]<=start){
                meetings.poll();
                usedRooms--;
            }
            usedRooms++;
            //Time : O(log(n))
            meetings.add(new int[]{start, end});
            rs=Math.max(rs, usedRooms);
//            System.out.printf("start: %s, end: %s, rooms: %s\n", start, end, usedRooms);
        }
        return rs;
    }

    public static int minMeetingRoomsOptimization(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> (a[0]-b[0]));
        PriorityQueue<int[]> meetings=new PriorityQueue<>((a, b) -> (a[1]-b[1]));

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            if (!meetings.isEmpty() && meetings.peek()[1] <= start) {
                meetings.poll();
            }
            meetings.add(new int[]{start, end});
//            System.out.printf("start: %s, end: %s, rooms: %s\n", start, end, usedRooms);
        }
        return meetings.size();
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given an array of meeting time intervals where intervals[i] = [starti, endi]
        //* Return số phòng yêu cầu để có thể cover được hết meetings
        //- Nếu 2 tập hợp riêng rẽ --> Có thể dùng chung room được vì nó sẽ diễn ra ở 2 thời điểm khác nhau.
        //- Nếu 2 tập hợp giao nhau --> Ta sẽ cần có 2 phòng để 2 meeting k xảy ra conflict
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= intervals.length <= 10^4
        //0 <= start-i < end-i <= 10^6
        //- Length k lớn lắm --> Time= O(n*k) --> O(n)
        //
        //- Brainstorm
        //Ex:
        //intervals = [[0,30],[5,10],[15,20]]
        // 0 ------------------------- 30
        //      5 --- 10
        //                15 ---- 20
        //
        //- Ta sẽ sort ==> Chứ bài ntn mà không sort thì khó xử lý
        //Ex:
        //intervals = [ [5,10],[15,20],[0,3],[5,20] ]
        //          5 ---- 10
        //                     15 ---- 20
        //0 --- 3   5 ---------------- 20
        //+ Ta thấy rằng, ta có thể dựng được các tổ hợp như sau:
        //  + 0 - 3 : ta có 1
        //  + 5 - 10 : ta có 2
        //  + 15 - 20 : ta có 3
        //  + 5 - 20 : ta có 2 (Merge được)
        //result=2
        //- Ở đây mục đích của mình có thể chia thành các tập hợp:
        //+ Riêng rẽ nhau (Không giao nhau) ==> return 1
        //
        //- Khi thêm 1 edge vào thì có 2 khả năng:
        //+ Số lượng tập hợp không đổi --> Do merge được
        //+ Số lượng tập hợp giảm --> Do merge được
        //+ Số lượng tập hợp tăng --> Tăng lên 1
        //
        //- Sort mục đích là để start point sẽ tăng dần ==> Ta chỉ quan tâm đến (end point)
        //
        //- Do starting point luôn tăng dần, thể nên nếu 1 edge được add vào thì:
        //+ Giữ nguyên số tập hợp
        //+ Tăng số tập hợp lên 1
        //===> Sẽ không thể xảy ra trường hợp giảm số tập hợp vì (starting point later) không thể cover được (previous starting point)
        //
        //- Cases:
        // (1,5),(2,4)
        //  1 --------- 5
        //     2 ----4
        //low=max(1,2)
        //high=max(4,5)
        //
        //(2, 4), (7, 10)
        // 2 --- 4
        //          7 ---- 10
        //result=1 vì có thể dùng chung room
        //
        //Ex
        //[[0,30],[5,10],[15,20]]
        // 0 ------------------------- 30
        //     5 --- 10
        //              15 --- 20
        // (5, 10) và (15, 20) có thể chia nhau dùng 1 room
        //+ (15,20) sẽ dùng chung room với (5, 10)
        //+ (15,20) sẽ dùng tách room với (0, 30)
        //
        //--> Chứ không phải 3 th 3 rooms
        //
        //Ex
        //[[0,1],[5,17],[15,20]]
        // 0 - 1
        //        5 ----------- 17
        //                 15 ------- 20
        //rs=2 ==> (0, 1), (15, 20) chung room
        //
        //- Idea
        //- Ta sẽ gồm các phần tử mà không giao nhau nhiều nhất vào 1 tập hợp
        //==> Sau đó sẽ tính ra kết quả
        //
        //- Tư duy lại, khi thêm 1 meeting vào thì:
        //+ Nó sẽ giữ nguyên số room nếu nó tách biệt với bất kỳ 1 meetings nào trước kia ==> Vì nó có thể share room với meeting đó?
        //Ex
        //[[0,3], [4, 8],[5,17],[6,20]]
        // 0 ---- 3
        //          4 ------ 8
        //            5 ----------- 17
        //               7 ------------- 20
        //rs= [ (0,3) chung [7,20] ], (4,8), (5,17)
        //rs = 3
        //- Câu hỏi là (0,3) nên chọn edges nào trong (4,8), (5,16), (7,20) làm cái để chung room?
        //  + Ở đây thì chọn edge nào cũng được nhưng:
        //Ex
        //[[0,3], [4, 8],[5,17],[6,20], [9,12] ]
        // 0 ---- 3
        //          4 ------ 8
        //            5 --------------------- 17
        //               7 ------------------------ 20
        //                      9 ---- 12
        //+ Ở đây sẽ chọn (0,3), (4,8), (9,12) chung 1 room, rs=3
        //+ Ngoài ra thì ta có thể chọn (0,3),(5,17) and (4,8),(9,12) là 2 tập hợp , rs=3
        //- Nếu tăng ending point (4,8) ra
        //
        //Ex
        //[[0,3], [4, 18],[5,7.5],[6,20], [9,12] ]
        // 0 ---- 3
        //          4 -------------------------- 18
        //            5 ---- 7.5
        //               7 -------------------------- 20
        //                        9 ---- 12
        //rs= 3
        //+ Gộp (0,3), (5,7.5), (9,12) 1 rooms, (4,18), (7,20) ra 2 rooms
        //+ Nếu ta merge (0,3) và (4,18), rs= 4 ==> tệ hơn phía trên.
        //
        //- Hint:
        //- Tư duy đơn giản như là việc:
        //+ room là số room đang sử dụng:
        //+ Allocate thêm room nếu thiếu : room++
        //+ Free up room nếu sử dụng xong : room--
        //==> Rs sẽ max room trong quá trình sử dụng.
        //
        //- Idea traverse:
        //Ex
        //[[0,3], [4, 18],[5,7.5],[6,20], [9,12] ]
        //Ex
        //[[0,3], [4, 18],[5,7.5],[6,20], [9,12] ]
        // 0 ---- 3 : Dùng 1 room
        //        3 : free room
        //          4 -------------------------- 18 : Dùng 1 room
        //            5 ---- 7.5 : Dùng 2 rooms
        //               7 -------------------------- 20: Dùng 3 rooms
        //                        9 ---- 12 : Free 1 room từ (5,7.5) và dùng thêm 1 room => số room = 3
        //- Làm thể nào đến step (9,12) --> Ta free được room thì được.
        //
        //- Idea là sort trước theo starting point + priority queue để add các element theo ending point (Giảm dần)
        //==> Ta có thể pop nó ra ngoài (Free room khi cần)
        //+ Sort là để sắp xếp thứ tự traverse.
        //+ Priority Queue là để sắp xếp các phần tử đã traverse --> So sánh với phần tử later.
        //
        //1.1, Optimization
        //- Thay vì pop các element ra + trừ số lượng room đi khi free up
        //--> Ta sẽ lấy size của meetings sau khi traverse là được.
        //==> Vì phải đi qua hết + free up mấy cái trước --> Thì mới đến được các edges đằng sau nên:
        //  + Ta chỉ cần if là được rồi không cần while
        //==> Số edges còn lại trong queue chính là số rooms mà không thể free up được.
        //
        //1.2, Complexity:
        //+ N is the number of meetings
        //- Time: O(n*log(n))
        //- Space: O(n)
        //
        //2. Chronological Ordering
        //2.1, Xem thêm thôi.
        //
        int[][] arr={{0,3}, {4, 18},{5,8},{6,20}, {9,12} };
        System.out.println(minMeetingRooms(arr));
        System.out.println(minMeetingRoomsOptimization(arr));
        //#Reference:
        //+ Merge Intervals
        //+ Meeting Rooms
        //+ Minimum Number of Arrows to Burst Balloons
        //+ Car Pooling
        //+ Number of Flowers in Full Bloom
        //+ Meeting Rooms III
        //+ Total Cost to Hire K Workers
        //+ Points That Intersect With Cars
    }
}
