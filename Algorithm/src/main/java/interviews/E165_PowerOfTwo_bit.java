package interviews;

public class E165_PowerOfTwo_bit {

    public static boolean isPowerOfTwoOptimize(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    public static boolean isPowerOfTwoRecursion(int n) {
        if(n==1){
            return true;
        }
        if(n==0){
            return false;
        }
        if(n%2==1){
            return false;
        }
        return isPowerOfTwoOptimize(n/2);
    }

    public static void main(String[] args) {
        int n=1;
        System.out.println(isPowerOfTwoOptimize(n));
        //
        //** Đề bài:
        //- Tìm xem 1 số bất kỳ có ở dạng là 2^n hay không
        //
        //** Bài này tư duy như sau
        //
        //Cách 1:
        //
        //1, Có các cách tư duy như sau:
        //1.1, Tư duy tập trung vào việc chỉ có (1 bit) dạng:
        //- 10000 --> Tức là chỉ có thể (and) (xor) kiểu gì với (chính nó / ~n)
        //- Vì ta (không thể đêm số bit) --> Tư duy đứng nguyên dịch (left/ right) : Thất bại.
        //VD: 0001000 --> (left/ right) bao nhiêu cho đủ
        //- Tư duy đảo lại + and với nhau ==> Cũng không có tác dụng vì ~(đảo) số
        // --> Sẽ có thể áp dụng với mọi số chứ không chỉ là các số biểu diễn bằng 2^n.
        //
        //1.2, Thử với các số (n-k) (Binary thường sẽ hướng theo kiểu như thế)
        //VD: n-1
        //n=16 --> n=15
        //16
        //1000
        //15
        //0111
        //- Quy luật tất cả các số right (Bên phải số 1 == 0) --> and 1 ==0 hết.
        //VD:
        //1xxx1000 ==> nếu ở dạng này thì (n-1) với (n) sẽ vẫn còn số 1 left --> Khi & (!=0)
        //--> Case này cover được hết các cases.
        //
        //Cách 2:
        //1, Ở đây ta dùng phương pháp đệ quy:
        //16 % dần dần cho 2 để ra được số binary tương ứng:
        //VD: 1000
        //+ 16%2==0 : 0 --> 16/2
        //+ 8%2==0 : 0 --> 8/2
        //+4%2==0 : 0 --> 4/2
        //2%2==0 : 0 --> 2/2
        //1%2==1 : 1 --> 1/2
        //Số : 10000
        //1.1, Giá sử đang đi 1 số (x%2==1) && (x!=1)
        //VD: 3,5,7 --> Chắc chắn x/2 --> Ra 1 số chắn ==> sẽ còn reduce về (1) (Tức là còn 1 số 1 nữa)
        //==> Có ít nhất 2 số 1 ==> Với case này loại
        //- Điều kiện quan trọng nhất : ( x%2==1 && x!=1 ) return false;
        //1.2, Nếu đã chạy đến cuối --> n==1
        //==> Tức là ta chỉ có thể tìm thấy 1 số 1 duy nhất --> Return true
        //<> n==0 : return false.
        System.out.println(isPowerOfTwoRecursion(n));
    }
}
