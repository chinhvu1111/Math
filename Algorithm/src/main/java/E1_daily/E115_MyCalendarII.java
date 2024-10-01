package E1_daily;

import java.util.Comparator;
import java.util.TreeSet;

public class E115_MyCalendarII {


    public TreeSet<int[]> events;

    public E115_MyCalendarII() {
        events=new TreeSet<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o1[1]-o2[1];
            }
        });
    }

//    public boolean book(int start, int end) {
//        int[] newNode=new int[]{start, end, 1};
//        int[] ceilNode = events.ceiling(newNode);
//        //+          (start1 -------- end1)
//        //(start --------- end)
//        //+          (start1 -------- end1)
//        //(start ----------------------------- end) ==> Điều kiện vẫn như trên (start1<end)
//        //ceilNode[0]<end ==> kph ceilNode[0]<=end
//        //  + start1 == end k sao
//        if(ceilNode!=null
//                &&((ceilNode[0]<end))){
//            if(ceilNode[2]<=1){
//                newNode = new int[]{start, Math.max(end, ceilNode[1]), ceilNode[2]+1};
//                events.remove(ceilNode);
//                events.add(newNode);
//                return true;
//            }
//            return false;
//        }
//        int[] floorNode = events.floor(newNode);
//        //+   (start1 --------- end1)
//        //             (start ---------- end)
//        if(floorNode!=null&&((floorNode[1]>start))){
//            if(floorNode[2]<=1){
//                newNode = new int[]{floorNode[0], Math.max(end, floorNode[1]), floorNode[2]+1};
//                events.remove(floorNode);
//                events.add(newNode);
//                return true;
//            }
//            return false;
//        }
//        events.add(newNode);
//        return true;
//    }

    public boolean book(int start, int end) {
        int[] newNode=new int[]{start, end, 1};
        int[] ceilNode = events.ceiling(newNode);
        if(ceilNode!=null&&ceilNode[0]==start&&ceilNode[1]==end){
            return true;
        }
        int[] initNode = ceilNode;
        int count=0;
        //
        while (count<2&&initNode!=null&&initNode[0]<end){
            initNode=events.ceiling(new int[]{initNode[0], ceilNode[1], 1});
            count++;
        }
        if(count==2){
            return false;
        }
        //+          (start1 -------- end1)
        //(start --------- end)
        //+          (start1 -------- end1)
        //(start ----------------------------- end) ==> Điều kiện vẫn như trên (start1<end)
        //ceilNode[0]<end ==> kph ceilNode[0]<=end
        //  + start1 == end k sao
        if(ceilNode!=null
                &&((ceilNode[0]<end))){
            if(ceilNode[2]<=1){
//                newNode = new int[]{start, Math.max(end, ceilNode[1]), ceilNode[2]+1};
//                events.remove(ceilNode);
                events.add(newNode);
                return true;
            }
            return false;
        }
        int[] floorNode = events.floor(newNode);
        initNode = floorNode;
        count=0;
        while (count<2&&initNode!=null&&initNode[1]>start){
            initNode=events.floor(new int[]{initNode[0], initNode[1], 1});
            count++;
        }
        if(count==2){
            return false;
        }
        //+   (start1 --------- end1)
        //             (start ---------- end)
        if(floorNode!=null&&((floorNode[1]>start))){
            if(floorNode[2]<=1){
//                newNode = new int[]{floorNode[0], Math.max(end, floorNode[1]), floorNode[2]+1};
//                events.remove(floorNode);
                events.add(newNode);
                return true;
            }
            return false;
        }
        events.add(newNode);
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are implementing a program to use as your calendar.
        //- We can add a new event if adding the event will not cause (a triple booking).
        //- A triple booking happens when
        //  + three events have (some non-empty intersection) (i.e., some moment is common to all the three events.).
        //- The event can be represented as a pair of integers start and end that represents a booking on the half-open interval [start, end),
        // the range of real numbers x such that start <= x < end.
        //- Implement the MyCalendarTwo class:
        //  + MyCalendarTwo() Initializes the calendar object.
        //  + boolean book(int start, int end) Returns true if the event can be added to the calendar successfully without causing a triple booking.
        // Otherwise, return (false) and do not add the event to the calendar.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //0 <= start < end <= 10^9
        //At most 1000 calls will be made to book.
        //  + Số lần call ít ==> loop bình thường cũng được.
        //
        //- Brainstorm
        //
        //- 1 event khi được add có thể:
        //  + Không kết hợp với event nào
        //      + (start, end), (x,y), (start1, end1)
        //  + Kết hợp với 1 event
        //  (start,end), (star1,end1)
        //- Có những cases sau:
        //  + start  ----------- end
        //             start1 -------- end
        //
        //  +          start  ----------- end
        //    start1 -------- end
        //
        //  + start ------------------------ end
        //           start1 -------- end
        //
        //  +        start1 -------- end
        //    start ------------------------ end
        //  + Kết hợp với 2 event
        //- Cases:
        //  + (star1 ----- end1), (start2 ---- end2)
        //         (start ------------- end)
        //  +     (star1 ----- end1), (start2 ---- end2)
        //   start ----------------------- end)
        //  - Cứ:
        //      + end1> start
        //      + end > start2
        //
        //  + Kết hợp với n>2 event
        //
        //- Không cần phải xét 3 intervals ==> Phức tạp:
        //  + Mình chỉ cần lưu lại count số lần trùng của mỗi interval là được
        //  + Sau đó nếu trùng nhau ==> Tạo interval mới có count = old interval.count++
        //  ==> Nếu == 3:
        //      + Return false <>
        //      + Return true
        //- Tuy nhiên vẫn sẽ có cases:
        //- ceil:
        //          (star1 ----- end1), (start2 ---- end2)
        //(start) ------------------------------------------------ (end) ==> False vì giao 2 cái
        //  ==> Ta sẽ phải search ceil tiếp với (start1)
        //- floor:
        //          (star1 ------------ end1),
        //  (start2 ---------------------------------- end2)
        //                  (start) ----------- (end)
        //  ==> Ta sẽ phải search floor tiếp với (start1)
        //- Ta sẽ phải search ceil và floor liên tục:
        //  + Cho đến khi không tìm được nữa.
        //
        E115_MyCalendarII e=new E115_MyCalendarII();
        //          10 ------- 20
        //                                   50 ------ 60
        //          10 ---------------- 40
        //5 ------------- 15
        //5 ------- 10
        //                         25 ----------- 55
        //- Nếu ta sort theo end:
        //  + Bị case add(10,40) + search ceil không ra được (10,20) được.
        //
        System.out.println(e.book(10, 20));//True
        System.out.println(e.book(50, 60));//True
        System.out.println(e.book(10, 40));//True
        System.out.println(e.book(5, 15));//False
        System.out.println(e.book(5, 10));//True
        System.out.println(e.book(25, 55));//True
        //
        //                                          47 ---- 50
        // 1 --- 10
        //            15 ------ 23
        //                   20 ------ 27
        //                   20 ------ 27
        //                              27 --- 36
        //       10 ----- 18
        //==> (15,23) kết hợp với (20,27) ==> Thành ra nếu kết hợp với (10,18) ==> Invalid
        //  + Thực tế thì (15,23) không nên kết hợp với (20,27)
//        System.out.println(e.book(47, 50));//True
//        System.out.println(e.book(1, 10));//True
//        System.out.println(e.book(27, 36));//True
//        System.out.println(e.book(40, 47));//True
//        System.out.println(e.book(20, 27));//True
//        System.out.println(e.book(20, 27));//True
//        System.out.println(e.book(15, 23));//True
//        System.out.println(e.book(10,18));//True
        //
    }
}
