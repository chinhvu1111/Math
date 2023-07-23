package E1_stack;

import java.util.*;

public class E1_CarFleet {

    public static class Node{
        int pos;
        double time;

        public Node(int pos, double time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "pos=" + pos +
                    ", time=" + time +
                    '}';
        }
    }

    public static int carFleetOwnWay(int target, int[] position, int[] speed) {
//        int n=position.length;
//        Node[] nodes=new Node[n];
//        for(int i=0;i<n;i++){
//            nodes[i]=new Node(position[i], speed[i]);
//        }
//        Arrays.sort(nodes, new Comparator<Node>() {
//            @Override
//            public int compare(Node o1, Node o2) {
//                return o1.pos-o2.pos;
//            }
//        });
//        System.out.println(Arrays.asList(nodes));
//        int low=0;
//        int high=n-1;
//        int rs=0;
//
//        while (low<n-1&&nodes[low].speed<nodes[low+1].speed){
//            low++;
//            rs++;
//        }
//        while (high>=low+1&&nodes[high].speed>nodes[high-1].speed){
//            high--;
//            rs++;
//        }
//        System.out.printf("%s %s\n", low, high);
//        int last=high;
//        while (last>low){
//            Node currentNode=nodes[last];
//            System.out.println(currentNode);
//
//            while (last>low&&nodes[last].speed<nodes[last-1].speed){
//                int steps= (int) Math.ceil((nodes[last].pos-nodes[last-1].pos)/((double)(nodes[last-1].speed-nodes[last].speed)));
//                int nextPos= nodes[last].pos+nodes[last].speed*steps;
//                System.out.printf("Length: %s\n", nextPos);
//
//                // 5, 3 --> Không đến được nhau
//                if(nextPos>target){
//                    int value=nodes[last-1].speed;
//                    last-=2;
//                    while (last>=low&&nodes[last].speed>=value){
//                        if(nodes[last].speed==value){
//                            rs++;
//                        }
//                        last--;
//                    }
//                }else{
//                    last--;
//                }
//            }
//        }
//
//        return rs;
        return 1;
    }

    public static int carFleet(int target, int[] position, int[] speed) {
        int n=position.length;
        Node[] nodes=new Node[n];
        for(int i=0;i<n;i++){
            nodes[i]=new Node(position[i], (double) (target-position[i])/speed[i]);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.pos-o2.pos;
            }
        });
//        System.out.println(Arrays.asList(nodes));
        int rs=0;
        double currentTime=0;

        for(int i=n-1;i>=0;i--){
            if(currentTime<nodes[i].time){
                currentTime=nodes[i].time;
                rs++;
            }
        }

        return rs;
    }

    public static int carFleetTreeMap(int target, int[] pos, int[] speed) {
        Map<Integer, Double> m = new TreeMap<>(Collections.reverseOrder());
        for (int i = 0; i < pos.length; ++i)
            m.put(pos[i], (double)(target - pos[i]) / speed[i]);
        int res = 0; double cur = 0;
        for (double time : m.values()) {
            if (time > cur) {
                cur = time;
                res++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Cho position và speed
        //+ Position[i] : Vị trí của car thứ ith ( Tính bằng miles)
        //+ Speed[i] : Is speed of car ith (Tính dựa trên miles)
        //- The car can catch up without passing each other.
        //- If they met each other, the faster car will be slow down to match the slower car's speed ( ==> Tức xe nhanh hơn sẽ làm chậm đi cho bằng xe chậm hơn)
        //- If a car catch up another, this group called (a car fleet)
        //* Count the number of car fleet
        //- 1 car --> 1 car fleet
        //- Nếu a car catch up a car fleet at the destination point --> A car fleet.
        //
        //** Idea
        //* Method 1:
        //1.
        //1.0,
        //- Constraint
        //n == position.length == speed.length
        //1 <= n <= 10^5
        //0 < target <= 10^6
        //0 <= position[i] < target
        //All the values of position are unique ==> Vị trí khác nhau
        //0 < speed[i] <= 10^6
        //--> Số n khá lớn
        //
        //- Brainstorm
        //- 2 car gặp nhau khi nào --> Khi car1 đuổi kịp car2 (Chỗ đuổi kịp < destination point)
        //+ Liệu điểm gặp nhau có lẻ được không
        //VD: 1(4), 6(2)
        // 1+4*x = 6 + 2*x
        //==> x = 2.5 units.
        //
        //+ Khi đuổi kịp sớm --> a car fleet sẽ chạy với vận tốc của (slower car)
        //VD:
        //1(4), 6(2), 11(1)
        //==>
        //10(2), 13.5(1)
        //10+2*x = 13.5+ 1*x
        //==> x= 3.5 units
        //==>
        //Gặp nhau all ở 17 --> return 1
        //
        //- Xe nhanh hơn chắc chắn sẽ đuổi kịp xe chậm hơn.
        //==> Và coi như group đó sẽ là group của xe chậm hơn
        //- Làm sao để có thể tính toán được việc 2 xe gặp nhau?
        //+ 2 xe gặp nhau khi có 1 xe chậm hơn --> Công thức bên trên work
        //+ Ta sẽ tiếp tục lấy đại diện của group là xe chậm hơn đi so sánh tiếp ==> Add thêm nodes vào group
        //
        //+ Điều kiện để xe i và j cùng 1 group:
        //+ Cứ pos[i] <= pos[j], speed[i] > speed[j]
        //
        //VD:
        //1(6), 5(4), 6(1)
        //+ Ở đây i=0 sẽ gặp i=2 trước ở :7
        // 7(1), 9(4)
        //+ Sau đó không gặp được nhau nữa:
        //<> nếu i==0 và i==1 gặp nhau thì lại khác.
        //** Tư duy như trên không thể xảy ra vì i==2 đứng sau (i==0 và i==1) ==> Sẽ không thể có case (i==0 gặp i==2 trước)
        //
        //Test case 1:
        //target = 12,
        //+ position = [10,8,0,5,3]
        //+ speed =    [2,4,1,1,3]
        //[10,8,0,5,3]
        // -->
        //pos :  [0,3,5,8,10]
        //speed: [1,3,1,4,2]
        //- i==0 không thể đuổi kịp i==1 và 2
        //- i==1 có thể đuổi kịp i==2
        //- Nếu a < b, b<c ==> về cơ bản thì c sẽ không thuộc group của (a, b) vì group mới với speed a<c sẽ không bao giờ đuổi kịp được c.
        //
        //Test case 2:
        //pos :    4, 6, 7
        //speed :  4, 1, 3
        //==> i==0 và i==1 sẽ luôn gặp ở đâu đó sau i==2
        //Test case 2.1:
        //pos :    4, 6, 6
        //speed :  4, 1, 3
        //==> position khác nhau --> Sẽ không có trường hợp trùng position.
        //
        //Test case 3:
        //pos :    4, 6, 7
        //speed :  4, 3, 1
        //- Ở đây thì có thể:
        //+ i==0 và i==1 có thể gặp nhau trước gặp i==2
        //+ i==1 và i==2 có thể gặp trước gặp i==1
        //---> Tức là không thể biết sẽ kết hợp ntn
        //
        //Test case 4:
        //pos :    4, 5, 12, 13
        //speed :  4, 3, 5, 1
        //+ i==0 và i==1 gặp nhau ở: pos=8, speed=3
        //-->
        //pos :    8, 14
        //speed :  3, 1
        //==> i==0 đuổi kịp i==14 ở pos=17
        //- Có thể có các cases:
        //+ (a,b),c
        //+ a,(b,c)
        //
        //Test case 5:
        //pos :    4, 5, 12, 13
        //speed :  4, 5, 6, 1
        //
        //- Ý tưởng xử lý:
        //- Sort theo position
        //pos :  [0,3,5,8,10]
        //speed: [1,3,1,4,2]
        //- Bỏ đi elements only increase (at first) + decrease (at last)
        //- Ta sẽ traverse từ last to first ==> Thu gọn dần:
        //+ Nếu speed sau < speed trước --> pop tất cả ra ==> Tính dần dần điểm đến
        //- Nếu điểm đến kết hợp tiếp > limit:
        //  + rs++ : Sau đó pop các node sau đó ra coi như là node đang xét là node cần xét tiếp
        //  ==> (speed =3 gặp speed =2 ở đâu đó out of limit) ==> 4 gặp 3 ở đâu đó chẳng hạn thì cuối cùng vẫn gặp 2 thôi
        //  + pop dần theo 3 là giá trị làm mốc mới
        //VD:
        //pos =   8,10,12
        //speed = 3,4,2
        //
        //stack : (0,1)
        //+ 1<3 ==> pop() + (rs++) + add(3,3)
        //stack : (3,3), (5,1)
        //+ 1<4 ==> pop() all + (rs++)
        //
        int[] position=new int[]{4, 5, 12, 13};
        int[] speed=new int[]{4, 3, 5, 1};
        //time = {13/4(3.25), 4, 1, 4}
        //
        //+ Ta sẽ chọn những time > current time ==> Cùng 1 group
        //+ < current time ==> Luôn slower vì:
        //- Sẽ không có case:
        //VD:
        //pos : a, b
        //time :5, 3
        //--> Gặp nhau sớm được không? mà thời gian (a) --> target > thời gian (b) --> target
        //==> Gặp nhau sớm --> là (a) giảm tốc --> (b) --> time sẽ càng tăng lên
        //- Dùng phương pháp quy nạp chứng minh:
        //* Mà đã gặp được b trong khoảng (b --> target) rồi + (b) đến đích đúng hạn ==> Giả sử sai.
        //==> Không có cases gặp nhau mà đến đích đúng hạn được.
        //
        //1.1, Complexity:
        //- Time complexity : O(NLog(N))
        //+ N is number of elements
        //- Space complexity : O(N)
        //
        //* Method 2:
        //2.
        //2.0, Dùng TreeMap --> Sorted value theo giá trị của key.
        //
        //
        int target=16;
//        System.out.println(carFleetOwnWay(target, position, speed));
        System.out.println(carFleet(target, position, speed));
        System.out.println(carFleetTreeMap(target, position, speed));
        //** Khó
        //#Reference:
        //1776. Car Fleet II
        //2211. Count Collisions on a Road
    }
}
