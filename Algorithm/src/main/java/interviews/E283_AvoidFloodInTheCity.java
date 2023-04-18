package interviews;

import java.util.*;

public class E283_AvoidFloodInTheCity {

    //** Đề bài:
    //- cho n rivers
    //- rain[i] >0 --> Tức là rain sẽ trên rains[i] lake (Mưa sẽ đổ trên lake thứ rains[i])
    //- Trả lại kết qua sao cho dry hợp lý --> Không bị flood lake nào ==> 1 lake rain 2 lần thì sẽ bị flood
    //- ans:
    //+ 0: Không mưa --> dry được
    //+ >0 : Mưa trên rain[i] lake
    //- result:
    //+ -1 : Tức là mưa trên ith lake
    //+ i : tức là dry ith lake
    //- return[] nếu không tìm được solution
    //** Bài này tư duy như sau:
    //1.
    //1.1, Idea
    //- Dữ kiện:
    //+ 1 <= n <=10^5
    //+ 0 <= rains[i] <= 10^9
    //- Cần dry đúng chỗ để tránh flood:
    //VD:
    //1,2,0,2,0,1
    //--> 0 này cần dry 2 --> Vì sau đó mình gặp 2 luôn
    //+ Vì đế lúc gặp 2 thì mình mới biết nên chọn 0 : dry 1/2
    // ==> Mình sẽ lưu lại 0 --> Sử dụng sau tuy nhiên mỗi th 0 thì chỉ được clear những giá trị đã có trước đó --> Nhưng lake đã (dc mưa/xuất hiện) trước đó
    //- Lưu lại zero dạng queue
    //VD-1:
    //1,(2),0,(2),0,1
    //VD-2:
    //+ Mỗi 0 ta cần phải clear 1 trong những lakes đã xuất hiện trước đó.
    //0,0,5,0,0,1,(2),0,(2),0,1
    //VD-3: Nếu ta gặp 2 phần tử liên tiếp nhau
    //0,0,1,2,0,(2),(2),(2),0,1
    //VD-4: --> Ta chỉ cần dùng hashset
    //1,2,0,0,(2),(2),0,1
    public static int[] avoidFlood(int[] rains) {
        HashMap<Integer, Integer> mapCount=new HashMap<>();
        int n=rains.length;
        Deque<Integer> zeroDrys=new LinkedList<>();
        HashSet<Integer> hashExists=new HashSet<>();
        int[] rs=new int[n];

        for(int i=0;i<n;i++){

        }

        return null;
    }

    public static void main(String[] args) {

    }
}
