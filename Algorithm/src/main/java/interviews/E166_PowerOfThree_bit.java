package interviews;

public class E166_PowerOfThree_bit {

    public static boolean isPowerOfThree(int n) {
        if(n==1){
            return true;
        }
        if(n==0){
            return false;
        }
        if(n%3!=0){
            return false;
        }
        return isPowerOfThree(n/3);
    }

    public static boolean isPowerOfThreeLoop(int n) {
        if(n<=0){
            return false;
        }
        while (n%3==0){
            n/=3;
        }
        return n==1;
    }

    public static boolean isPowerOfThreeMathWrong(int n) {
        System.out.println(Math.log(n)/Math.log(3));
//        return Math.log(n)/Math.log(3)%1==0;
        double rs=Math.log(n)/Math.log(3);
        return Math.abs(rs-Math.rint(rs))%1<=0.00000000000001;
    }

    public static boolean isPowerOfThreeMath(int n) {
        System.out.println(Math.log10(n)/Math.log10(3));
        return Math.log10(n)/Math.log10(3)%1==0;
    }

    public static boolean isPowerOfThreeMathOptimize(int n) {
        System.out.println(Math.log10(n)/Math.log10(3));
        return Math.log10(n)/Math.log10(3)%1==0;
    }

    public static boolean isPowerOfThreeOptimize(int n) {
        while(n>0&&n%3==0){
            n=n/3;
        }
        return n == 1;
    }

    public static void main(String[] args) {
//        int n=9;
//        int n=45;
        int n=243;
        System.out.println(isPowerOfThree(n));
        //** Đề bài
        //- Kiểm tra xem số x có dạng 3^n hay không?
        //
        //** Bài này tư duy như sau:
        //
        //Cách 1: Recursion
        //- Bài này đều có tư duy tương tự 2 bài format 2^k
        //1, Chỉ chú ý: (n%3!=0) return false;
        //
        //Cách 2: Dùng math
        //1,
        //1.1, CT:
        //+ log3(n) = log10(n)/ log10(3)
        //1.2, Cách kiểm tra số nào đó có phải (SỐ THẬP PHÂN HAY KHÔNG)
        //
        //- return (n%1)
        //
        //1.3, Nếu dùng log(n) --> độ chính xác sẽ bị lệch khi dùng log10(n)
        //VD: nó sẽ trả về 4.999999999999999 ~ 5
        //Nhưng 4.999999999999999 (return false) <=> thực tế nó là 5 (return true)
        //
        //--> Ta cần so sánh abs với (5-4.999999999999999) = 0.00000000000001
        //==> Khi thao tác với double (ĐỘ chính xác không cao)
        //+ rint() function : Để làm tròn số đó.
        //
        //2, Tối ưu
        //- Thêm đoạn while(n>0) --> Để tăng tốc độ với các trường hợp <0 thôi.
        //
        //3, Reference
        //- Random Point in Non-overlapping Rectangles
        //- Find the Shortest Superstring
        //- Largest Combination With Bitwise AND Greater Than Zero
        System.out.println(Math.rint(3));
        System.out.println(isPowerOfThreeLoop(n));
        System.out.println(isPowerOfThreeMathWrong(n));
        System.out.println(isPowerOfThreeMath(n));
        System.out.println(isPowerOfThreeMathOptimize(n));
        System.out.println(isPowerOfThreeOptimize(n));
    }
}
