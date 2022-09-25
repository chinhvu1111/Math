package interviews;

public class E169_BitwiseANDOfNumbersRange_bit {

    public static int rangeBitwiseAnd(int left, int right) {
        int init=1;
        int numberBitStart=0;

        while (init<=left/2) {
            init<<=1;
            numberBitStart++;
        }
        if(1<<(numberBitStart)<=right/2){
            return 0;
        }
//        System.out.println(right-left);
        int rs=left;

        for(int i=left-1;i<=right-1;i++){
            rs&=i+1;
        }
        return rs;
    }

    public static int rangeBitwiseAndBitMethod(int left, int right) {
        while (right>left){
            right=right&(right-1);
        }
        return right&left;
    }

    public static int rangeBitwiseAndBitMethodOptimize(int left, int right) {
        int init=1;
        int numberBitStart=0;

        while (init<=left/2) {
            init<<=1;
            numberBitStart++;
        }
        if(1<<(numberBitStart)<=right/2){
            return 0;
        }
        while (right>left){
            right=right&(right-1);
            init<<=1;
        }
        return right&left;
    }

    public static int rangeBitwiseAndBitOtherImplement(int left, int right) {
        int numberBitUntilSame=0;

        while (left!=right){
            left>>=1;
            right>>=1;
            numberBitUntilSame++;
        }
        return left<<numberBitUntilSame;
    }

    public static void main(String[] args) {
//        int left=5, right=7;
//        int left=1, right=2147483647;
        int left=2147483646, right=2147483647;
        System.out.println(8&9&10&11&12&13&14&15);
        //
        //** Đề bài
        //- Bài này yêu cầu tìm AND giữa range(left, right)
        //
        //Cách 1:
        //0, Không nên tính pow(2, n) ==> Nên dùng dịch bit
        //** Bài này tư duy như sau:
        //1,
        //1.1, Ta có ví dụ sau đây
        //000 : 0
        //001
        //010
        //011 : 3
        //= 0
        //--------
        //100 : 4
        //101 : 5
        //110 : 6
        //111 : 7
        //= 100
        //
        //--------
        //
        //1000 : 8
        //1001 : 9
        //1010 : 10
        //1011 : 11
        //1100 : 12
        //1101 : 13
        //1110 : 14
        //1111 : 15
        //= 1000
        //==> Ta thấy 1000 and 100 = 0
        //- Quy lụât:
        //1, Nếu nếu (left, right) > 2^x (x có tồn tại)
        //--> return 0
        //1.1,
        //- Nếu kết quả !=0 ==> Ta tính bình thường.
        //
        //Cách 2:
        //1, Vì (n & (n-1)) sẽ loại dần tất cả các (chữ số 1 từ cuối lên)
        //VD:
        //+ Ta có số 111001
        //111001
        //&
        //111000 (111001 -1)
        //=
        //111000 ==> Ta đã loại được số 1
        //+
        //111000 --> Giả sử ta AND tiếp
        //111000
        //&
        //110111 ==> cuối cùng vẫn là (111000 khi bị loại thêm 1 chữ số 1 = 110000)
        //1.1, Cứ thể dần dần loại chữ số 1 từ right dần --> left ==> Đó chính là quy luật của bài này.
        //
        //Cách 3:
        //1,
        //1.1, Ta có các nhận xét như sau:
        //4 & 7 = 0b100 & 0b111 = 0b100 = 0b10 & 0b11
        //5 & 7 = 0b101 & 0b111 = 0b101 = 0b10 & 0b11
        //5 & 6 = 0b101 & 0b110 = 0b100 = 0b10 & 0b10
        //- Tức là giao của 2 số là số bit giống nhau của 2 số (left, right)
        //==> Số bit giống nhau của tất cả các số
        //---> ở đây nếu là bit =0 --> AND chắc chắn ra 0
        //** TƯ DUY KHOẢNG CÁCH --> Căn nếu left<right
        // ==> bit cuối của left =1 ==> Chắc chắn có tồn tại SỐ (last bit = 0) in range (lef, right)
        //VD:
        //101001 (left)
        //&
        //110111 (right)
        //---> Ta thấy vì left = 101001 ==> last bit = 1 (left< right) ==> Chắc chắn có Số có bit =1 in range (left, right)
        //1.1,
        //- Vì có tồn tại số last bit = 0 giữa (left, right)
        //==> Ta sẽ dịch phải (left, right) để xét bit kế tiếp
        //1.2, left, right >> dịch đến khi nào tìm được bit chung (của all số)
        //==> Lúc đó << (số lượng bits đã dịch) ==> Chính là kết quả.
        //
        //** COMPLEXITY??
        //Faster
        System.out.println(rangeBitwiseAnd(left, right));
        //Slower
        System.out.println(rangeBitwiseAndBitMethod(left, right));
        //Combination
        System.out.println(rangeBitwiseAndBitMethodOptimize(left, right));
        System.out.println(rangeBitwiseAndBitOtherImplement(left, right));
    }
}
