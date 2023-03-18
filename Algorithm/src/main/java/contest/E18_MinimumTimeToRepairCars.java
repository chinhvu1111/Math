package contest;

import java.math.BigInteger;

public class E18_MinimumTimeToRepairCars {
    public static long repairCars(int[] ranks, int cars) {
        long low=0;
        long high=(long)1e14;
        while (low<high){
            long mid=low +(high-low)/2;
            long repairCars=0;

            for(int r:ranks){
                repairCars+=(int)Math.sqrt(mid / r);
            }
//            if(repairCars>cars){
//                high=mid-1;
//            }else{
//                low=mid;
//            }
            if(repairCars<cars){
                low=mid+1;
            }else{
                high=mid;
            }
//            System.out.printf("%s %s %s\n", low, high, repairCars);
        }
        return low;
    }

    public static void main(String[] args) {
        int[] ranks = new int[]{4,2,3,1};
        int cars = 10;
        System.out.println(repairCars(ranks, cars));
        //** Đề bài:
        //-
        //ranks = [4,2,3,1], cars = 10
        //Rank r có thể repair n cars in (r * n^2)
        //VD: 4 có thể repait 10 cars in (4*10^2)=104
        //- Tìm max nhất trong các time mà các rank có thể repair được.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Kết quả lớn nhất sẽ là 1--> 100 * 10^6*10^6
        //+ Nhiệm vụ của là tìm số sao cho số đó x khớp với
        //10 cars và arr=[4,2,3,1]
        //==> Ta sẽ tìm số trong khoảng ==> Để ra được kết quả
        //
        //1.2,
        //- Ý tưởng:
        //+ Các số sẽ balance nhất có thể nhận min nhất trong max nhất (arr)
        //VD:
        //Kết quả = rs
        //--> Khi chia rs/ cho từng phẩn tử + sqrt ==> sum = số cars có thể sửa.
        //+ Vì 1 dãy arr ==> Nhưng kết quả là chỉ phụ thuộc và số lớn nhất --> 1 số ==> dấu hiệu.
        //+ Kết quả có thể check được xem valid hay không bằng cách
        //check xem số car mà nó có thể sửa được mỗi lần
        //---> Phân low, high sang 2 bên nếu thu được < / >= car
        //
        //1.3
        //- Câu hỏi là nếu result=16 --> Liệu có trường hợp nào
        //==> 16 --> tính ra repaired trên all rank != với kết quả phân chia (2,2,2,4) hay không.
        //VD: 16 tính ra là (2,2,3,4) chẳng hạn.
        //[4,2,3,1]
        //+ 4*2*2
        //+ 2*2*2
        //+ 3*2*2
        //+ 1*4*4
        //
        //1.4,
        //- Kinh nghiệm :
        //+ while(low<high) ==> sẽ gắn với low=mid+1 ==> ta mới break được ra
        //+ Nếu không thì --> break.
        //
    }
}
