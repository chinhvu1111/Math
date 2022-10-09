package interviews;

public class E138_ArrangingCoins {

    public static int arrangeCoins(int n) {
        int left=1;
//        int right=n;
        //Vì khi ta nhân đôi n --> Sẽ ra hình vuông --> số row nhiều nhất sẽ chỉ được = right mà thôi
        //---> kế cả có không đủ hàng cuối ==> Nhiệm vụ chỉ return ra số lượng row filled đủ thôi
        //===> sqrt(n*2) : Sẽ là row nhiều nhất có thể trả lại.
        int right= (int) Math.sqrt(2*(double)n);
//        System.out.printf("%s %s, ", left, right);

        int rs=1;

        while (left<=right){
            long mid=left+(right-left)/2;
//            int numberCoint=mid*(mid+1)/2;
//            System.out.println(mid);

//            System.out.println(numberCoint);
            if(n < mid*(mid+1)/2 ){
                right= (int) (mid-1);
            }else{
                rs= (int) mid;
                left= (int) (mid+1);
            }
        }
        return rs;
    }

    public static void main(String[] args) {

        //Case này bị lỗi:
        //- n/mid < (mid+1)/2 ==> n/mid * 2<mid+1
        //+ Vì (mid+1)/2 ==> Có thể ra số thập phân
//        int n=5;
//        int n=8;
//        int n=3;
        int n=1804289383;
        System.out.println(arrangeCoins(n));

        //
        //** Đề bài:
        //- Tính số row nhiều nhất có thể để build ra được hình (coins) dạng tăng dần coin ở mỗi row:
        //- Input chính là số coins.
        //
        //VD:
        //1
        //1,2
        //1,2,3
        //1,2,3,4
        //==> Ở hàng cuối ta có thể build thiếu coins cũng được.
        //
        //1, Build nhiều row thì số coins càng tăng, build ít row thì số coins giảm.
        //1.1, --> Tính đồng biến
        //
        //#reference
        //- Elimination Game
        //- Fraction Addition and Subtraction
        //- Count Triplets That Can Form Two Arrays of Equal XOR
    }
}
