package interviews;

public class E163_NumberOf1Bits_bit {

    public static int hammingWeightWrong(int n) {
        int rs=0;

        if(n<0){
            if(n!=Integer.MIN_VALUE){
                rs++;
            }
            n=n>>>1;
        }
//        System.out.printf("%s, ",n);

        while(n!=0){
            rs+=n&1;
//            System.out.println(n);
            n=n>>1;
        }
        return rs;
    }

    public static int hammingWeight(int n) {
        int rs=0;

        while(n!=0){
            rs+=n&1;
            n=n>>>1;
        }
        return rs;
    }

    public static int hammingWeightOptimize(int n) {
        int rs=0;

        while(n!=0){
            n=n&(n-1);
            rs++;
        }
        return rs;
    }

    public static void main(String[] args) {
        int n=-9;
//        int n=-2147483648;
        //Danh sách cases sai:
        //Case 1: 11111111111111111111111111111101 : 2147483648
        //Case 2: 10101010101010101010101010101010 : 2863311530
        System.out.println(hammingWeightWrong(n));
        System.out.println(hammingWeight(n));
        //Đề bài:
        //- Đếm số bit 1 của số n.
        //Ta tư duy như sau:
        //0, Biểu diễn số âm trong hệ thập phân
        //0.1, Các bước biểu diễn số âm trong binary
        //Bước 1:  Xác định số nguyên 45 ở hệ thập phân được biểu diễn trong máy tính là : 0010 1101
        //
        //Bước 2: Đảo tất cả các bit nhận được ở bước 1. Kết quả bạn sẽ được: 1101 0010
        //
        //Bước 3: Cộng thêm 1 vào kết quả thu được ở bước 2. Kết quả sau khi cộng: 1101 0011
        //
        //0.2, Công thức ngược với số âm
        //+ -3                    → 11111111 11111111 11111111 11111101
        //+ -3 >>> 1 = 2147483646 → 01111111 11111111 11111111 11111110 ==> Với số âm (<0) thì ta sẽ dùng (bit shift unsigned)
        //--> Dịch như bình thường
        //+ -3 >> 1 = -2          → 111111111 11111111 11111111 1111110 ==> Nếu làm như bình thường thì sẽ bị lớn dần (Vô hạn)
        //--> Thêm số 1 vào đầu (Có thể chạy đến vô cực)
        //0.3,
        // So the input 2147483648 is represented in Java as -2147483648 (in java int type has a cyclic representation,
        // that means Integer.MAX_VALUE+1==Integer.MIN_VALUE).
        //
        //1, Bài này làm như sau:
        //1.1, Công thức check bit thứ (i) của dãy:
        //bit (i) = ( n >>(i) ) & 1
        //+ 1 : 0000...00001 ==> số 1 sẽ đứng ở cuối
        //+ n >> (i) : sẽ dịch bit sang phải i bit
        //VD: 0111001(1)1 --> shift 1 bits --> 00111001(1)
        //--> Check từng bit 1 ++ dần vào.
        //
        //Cách 2:
        //1, Cách này ta dùng phương pháp giản lược số bước đi
        //==> Cụ thể ta hướng đến việc đếm số bit (1) trong dãy là được.
        //1.1, Tư duy như sau:
        //VD:
        //1101010110110
        //- Ta sẽ bỏ qua việc check các bit =0
        //--> Check luôn bit =1
        //11010101101|1|0
        //1.2, Ta sẽ giảm số đó mỗi lần sao cho, mỗi lần ta sẽ tìm được bit (1) --> Loại bỏ tất cả các bit =0
        //VD:
        //1101010|1|1|0000
        //1101010110000
        //1101010101111
        //=
        //1101010100000 : Số đằng sau ta đã bỏ được bit 1 ra ==> rs++;
        //Loop ==> (n==0)
        //rs chính là số bit
        //
        //- Thao tác (n&(n-1)) --> Ta đã loại 1 bit 1 ra ngoài rồi.
        System.out.println(hammingWeightOptimize(n));
    }
}
