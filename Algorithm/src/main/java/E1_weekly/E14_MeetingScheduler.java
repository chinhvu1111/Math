package E1_weekly;

import java.util.*;

public class E14_MeetingScheduler {

    public static List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int n=slots1.length;
        int m=slots2.length;
        int p=0, p1=0;
        List<Integer> rs=new ArrayList<>();
        Arrays.sort(slots1, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        Arrays.sort(slots2, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });

        while(p<n&&p1<m){
            int maxStart=Math.max(slots1[p][0], slots2[p1][0]);
            int minEnd=Math.min(slots1[p][1], slots2[p1][1]);
            //start--------end
            //      start1------end1
            //start--------end
            //                   start1----end1
            //                   start----end
            //start1--------end1
            //- Thiếu case:
            //start-------------------end
            //      start1-----end1

            if(minEnd-maxStart>=duration){
                rs.add(maxStart);
                rs.add(maxStart+duration);
                break;
            }
            if(slots1[p][0]==slots2[p1][0]&&slots1[p][1]==slots2[p1][1]){
                p++;
                p1++;
            }else if(minEnd==slots1[p][1]){
                p++;
            }else{
                p1++;
            }
        }
        return rs;
    }

    public static List<Integer> minAvailableDurationHeap(int[][] slots1, int[][] slots2, int duration) {
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        for(int[] slot: slots1){
            if(slot[1]-slot[0]>=duration){
                queue.add(slot);
            }
        }
        for(int[] slot: slots2){
            if(slot[1]-slot[0]>=duration){
                queue.add(slot);
            }
        }
        List<Integer> rs=new ArrayList<>();
        while (queue.size()>=2){
            int[] slot1 = queue.poll();
            int[] slot2 = queue.peek();
            if(slot1[1]-slot2[0]>=duration){
                rs.add(slot2[0]);
                rs.add(slot2[0]+duration);
                break;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the availability time slots arrays) (slots1 and slots2) of (two people) and (a meeting duration duration),
        //* return (the earliest time slot) that works for (both of them) and is of duration duration.
        //- If there is (no common time slot) that (satisfies the requirements), return an empty array.
        //- The (format of a time slot) is (an array of two elements [start, end]) representing (an inclusive time range) from (start to end).
        //- It is guaranteed that (no two availability slots) of (the same person) intersect with (each other).
        //  + Không có overlap
        //- That is, for (any two time slots) [start1, end1] and [start2, end2] of (the same person),
        //  + either (start1 > end2 or start2 > end1).
        //  + Cùng 1 người thì 2 slots không thể giao nhau.
        //- Các time slot của 2 người có thể giao nhau:
        //* Return (earliest time slot) có duration = duration mà 2 người đều có.
        //
        //**Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= slots1.length, slots2.length <= 10^4
        //slots1[i].length, slots2[i].length == 2
        //slots1[i][0] < slots1[i][1]
        //slots2[i][0] < slots2[i][1]
        //0 <= slots1[i][j], slots2[i][j] <= 10^9
        //1 <= duration <= 10^6
        //  + duration khá lớn ==> Dùng ct toán, k loop được
        //  + Slot value cũng khá lớn ==> Chú ý đoạn này
        //
        //- Brainstorm
        //Example 1:
        //Input:
        //slots1 = [[10,50],[60,120],[140,210]],
        //slots2 = [[0,15],[60,70]], duration = 8
        //Output: [60,68]
        //
        //- Sort trước theo (x)
        //  + Để dùng 2 pointers
        //- Ta sẽ tìm max duration giao nhau của 2 people
        //
        //- Vì là 2 people:
        //  + 1 slot của người này có thể giao ==> n slots của người kia
        //  + Same person thì các slots không overlap
        //- 2 slots giao nhau:
        //  + Lấy slot có (end) lớn hơn
        //      + Để check slot tiếp theo
        //  + Nếu duration giao nhau >= given duration
        //  ==> return
        //
//        int[][] slots1 = {{10,50},{60,120},{140,210}}, slots2 = {{0,15},{60,70}};
//        int duration = 8;
//        int[][] slots1 = {{0,1000000000}}, slots2 = {{0,1000000000}};
//        int duration = 1000000;
        //
        //- Slot overlap, các cases có thể xaỷ ra:
        //start--------end
        //      start1------end1
        //start--------end
        //                   start1----end1
        //                   start----end
        //start1--------end1
        //- Thiếu case:
        //start-------------------end
        //      start1-----end1
        //
        //** Chú ý:
        //for any two time slots [start1, end1] and [start2, end2] of the same person, either start1 > end2 or start2 > end1.
        //- Sẽ không có cases:
        //slots1 = [1,4],[4,6]
        //slots2 = [1,10]
        //  + 1 slots2 giao 2 slots1
        //  ==> Duration = 6
        //      + Không có case này.
        //
        //* Kinh ngiệm:
        //- Mấy bài overlap ntn:
        //  + Nên xét maxStart và minEnd để:
        //      + Xác định overlap size
        //  + Dùng minEnd còn có thể xác định được cái nào nên:
        //      + p++ or p1++
        //  + Với 2 slot giống hệt nhau:
        //      + p++,p1++ ==> At the same time.
        //
        //* Lỗi sai
        //  + Quên sort ==> Sắp xếp theo earilest
        //
        //1.1, Optimization
        //- Đoạn check minEnd có thể thay thế bằng cách so sánh:
        //  + End của slot1 và slot2
        //================
        // always move the one that ends earlier
        //if (slots1[pointer1][1] < slots2[pointer2][1]) {
        //    pointer1++;
        //} else {
        //    pointer2++;
        //}
        //================
        //
        //1.2, Complexity
        //- Space: O(log(n+m))
        //- Time: O(log(n+m)*(n+m))
        //
        //2. Heap (More optimal)
        //2.1,
        //- Ta sẽ filter đi những slot có duration < given duration
        //  + Vì < duration ==> Chắc chắn k đủ.
        //- Ta cũng có thể add vào cùng chung 1 heap
        //  + Sau đó pop ra để lấy từng pair một:
        //      + Sẽ được sắp xếp tăng dần theo (slot[0])
        //- Liệu có case xét nhầm 2 slot overlap cùng 1 person hay không?
        //  - Không ==> Vì chỉ có 2 slots từ (2 people) thì mới có thể (overlap)
        //* Main point:
        //  - Thì 2 slots có thể overlap ==> 2 slot có (x gần nhau nhất)
        //- Pop dần từ x (small -> big)
        //  + Nếu không giao nhau thì cứ pop ra là được:
        //      + Có thể cùng 1 person/ có thể k
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(log(n+m))
        //- Time: O(log(n+m)*log(n+m))
        //
        int[][] slots1 = {{10,50},{60,120},{140,210}};
        int[][] slots2 = {{0,15},{60,70}};
        int duration = 10;
        System.out.println(minAvailableDuration(slots1, slots2, duration));
        System.out.println(minAvailableDurationHeap(slots1, slots2, duration));
        //
        //#Reference:
        //2570. Merge Two 2D Arrays by Summing Values
        //
    }
}
