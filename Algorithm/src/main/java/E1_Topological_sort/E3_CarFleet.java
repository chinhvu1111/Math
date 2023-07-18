package E1_Topological_sort;

public class E3_CarFleet {

    public static int carFleet(int target, int[] position, int[] speed) {
        return 0;
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
    }
}
