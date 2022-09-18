package interviews;

import java.util.HashSet;

public class E13_HappyNumber_linkedList {

    public static boolean isHappy(int n) {
        HashSet<Integer> sums=new HashSet<>();

        while(n!=1){
            n=nextSum(n);

            if(sums.contains(n)){
                return false;
            }
            sums.add(n);
        }
        return true;
    }

    public static int nextSum(int n){
        int sum=0;

        while(n!=0){
            sum+=(n%10)*(n%10);
            n=n/10;
        }
        return sum;
    }

    public static boolean isHappyOptimize(int n){
        int slow=n;
        int fast=n;
        while(slow!=1){
            slow=nextSum(slow);
            fast=nextSum(fast);
            fast=nextSum(fast);

            if(slow==fast&&slow!=1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int n=19;
        System.out.println(isHappy(19));
        System.out.println(isHappyOptimize(19));
        //Đề bài:
        //- Cho 1 số n --> Lấy số ^2 của tất cả các số trong number
        //- Cho đến khi tổng cho ra ==1 : return true <>< return false.
        //VD:
        //Input: n = 19
        //Output: true
        //Explanation:
        //1^2 + 9^2 = 82
        //8^2 + 2^2 = 68
        //6^2 + 8^2 = 100
        //1^2 + 0^2 + 0^2 = 1
        //
        //Bài này tư duy như sau:
        //1,
        //1.1, Ta cần xác định dãy này dừng lại khi nào?
        // Cộng liên tục sẽ có lúc nó lặp lại --> Có cycle
        //1.2, Có 2 cách tư duy với dạng bài cycle kiểu như thế này!
        //- Dùng hashSet để check cycle
        //- Dùng slow, fast để xác định cycle : Trong trường hợp sử dụng linkedList để traverse.
        //1.3, 19 có 2 cách duyệt số này
        //- 119 = 9 + 1*1 1 *100 : Xuôi (Với số lượng chữ số nhỏ)
        //- 119 = ( 1*10 + 1 ) * 10 + 9 : Ngươc (Với số lượng chữ số lớn)
        //---> Ta sẽ dùng cách suy luận xuôi để làm bài này
        //+ n = n/10;
        //+ sum += sum%10;
        //1.4, nextSum() là method tính sum bình phương: a^2 + b^2 của all digits.
        //- Code:
        //+ while(n!=1) --> break ra được while return true (Có 1)
        //+ Còn nếu trong while gặp cycle --> return false.
        //
        //1.5, Ở đây ta dùng thuật toán rùa và thỏ để tìm --> cycle
        //+ fast= nextSum() 2 lần.
    }

}
