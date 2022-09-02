package interviews;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class E119_SuperPow_binary {

    public static int superPowWrong(int a, int[] b) {
//        int m=1;
//        int currentValue=a;
//
//        while(currentValue<=1337){
//            currentValue*=a;
//            m++;
//        }
//        int residual=currentValue%1337;
//        System.out.println(m);
//
//        int exponentNeeded=m;
        int n=b.length;
        double mod=1;
        int length=0;

//        for(int i=n-1;i>=0;i--){
//            if(b[i]<=exponentNeeded){
//                exponentNeeded-=b[i];
//                b[i]=0;
//                mod*=residual;
//            }else{
//                b[i]-=exponentNeeded;
//                mod*=residual;
//                exponentNeeded=m;
//                i++;
//            }
//            length++;
//        }

        for(int i=n-1;i>=0;i--){
            for(int j=0;j<length&&b[i]!=0;j++){
                mod=(mod*Math.pow(a, 10))%1337;
            }
            mod=mod%1337;
//            System.out.println(Math.pow(a, 10)+" "+mod);
            System.out.println(mod);
            if(b[i]!=1&&b[i]!=0){
                mod=(mod*Math.pow(mod, b[i]))%1337;
            }
//            System.out.printf("%s\n\n",mod);
            length++;
        }

        return (int) (mod % 1337);
    }

    public static int superPow(int a, int[] b) {
        int n=b.length;
        Set<Integer> hashSet=new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();
        if(a<=1){
            return a;
        }
        a=a%1337;
        int init =a;

        //hashset lưu danh sách số dư có thể (Nếu dùng a)
        //hashset.size :  là số lượng số dư có thể khi dùng (a * x %1337)
        //==> Khi % 1337 thì kết quả chỉ có thể nằm trong (1--> 1337)
        //1, Đây là 1 cách tư duy mới --> Thay vì cố gắng tìm kết quả
        //1.1, Hướng đến việc limit kết quả ==> Scan từng trường hợp ==> Tìm <> có khả thi hay không
        //2, Số dư sẽ tăng dần khi ta tăng dần init lên : (init = init * a)
        int power=1;

        while (!hashSet.contains(init)){
            map.put(power, init);
            hashSet.add(init);
            init=(init*a)%1337;
            power++;
        }
        //If a = 1337 * x + y, then a^b % 1337 = y^b % 1337
        //--> a %1337=y ==> a^b % 1337 = y^b % 1337.
        //map: lưu dạng (só exponent, value (0 --> 1337) (số dư))
        //2, Nhiệm vụ đi tìm số exponent --> tạo lên (large number)
        //
        int initPower=0;

        //3, Nếu bỏ hashSet đi thi 1 số có thể biểu diễn theo 2 cách:
        //+ right --> left (Thường nghĩ)
        //+ left --> right
        //VD: (1*10 + 2) * 10 + 3
        //123
        //+ 0 * 10 + 1
        //+ 1 * 10 + 2
        //+ 12 * 10 + 3
        //==> Ở dưới ta tính dư theo (số lượng số mũ có thể % 1337)
        //4, Với những dạng theo kiểu toán như thế này ==> Cố dùng toán để mà giải quyết
        //==> nếu chỉ dùng phép thử như bình thường thì rất khó có thể đánh giá được hết khả năng.
        //VD: 2^i % 1337 = k ---> Cần biến đổi ngược lại (2^i = 1337 * x + k)
        //Cái ta cần tính là 2^n theo 2^i
        // ==> 2^n = 1337 * x * 2^(n-i) + k * 2^(n-i)
        //==> Thay vì cố thử các case 2^i --> theo 2^n bằng cách thử
        //VD: (2^3)^15 %k có giống (2^3%k)^15 hay không.
        //--> Thử như thế này có thể rất mất thời gian + sai sót.
        //
        //5, Với bài kiểu này ---> Để phát hiện ra quy luật ta sẽ chỉ cần thử 2 --> 3 steps loop gần nhất.
        //VD:
        //- ở đây hashset.size() thể hiện (số mũ lớn nhất) k : (a^k) <=> (a^k % 1337 = m)
        //+ m không xuất hiện trước đó : [Không tồn tại] (a^i % 1337) = m
        //
        System.out.println(hashSet.size());
        for(int i=0;i<n;i++){
            initPower=(initPower*10+b[i])%hashSet.size();
            System.out.printf("%s %s\n",b[i],initPower);
        }
        return map.get(initPower);
    }

    public static int superPowEulerBinary(int a, int[] b) {
        int num=0;

        for(int i=0;i<b.length;i++){
            num=(num*10 + b[i])%1140;
        }
        return modPow(a, num, 1337);
    }

    public static int modPow(int a, int b, int modNumber){
        int rs=1;
        a=a%modNumber;

        while (b>0){
            if(b%2==1){
                rs=(rs*a)%modNumber;
            }
            a=(a*a)%modNumber;
            b/=2;
        }
        return rs;
    }

    public static void main(String[] args) {
//        int[] arr=new int[]{4,3};
//        int a=8;
//        int[] arr=new int[]{1,0};
//        int a=2;
//        int[] arr=new int[]{3};
//        int a=2;
//        int[] arr=new int[]{2,0,0};
//        int a=2;
        int[] arr=new int[]{4,3,3,8,5,2};
        int a=4;

//        System.out.println(262144%14);
//        System.out.println(64%14);
//        System.out.println(512%14);
//        System.out.println(4096%14);
//        System.out.println();
//        System.out.println(262144%11);
//        System.out.println(64%11);
//        System.out.println(512%11);
//        System.out.println(4096%11);
//        System.out.println((4096*9)%11);
//        System.out.println();
//        System.out.println(32768 %11);
//        System.out.println(80%11);
//
        System.out.println("=================");
//        System.out.println(Math.pow(2, 15)%3);
//        System.out.println(Math.pow(2, 15)%4);
//        System.out.println(Math.pow(2, 15)%5);
//        System.out.println(Math.pow(3, 5)%5);
//        System.out.println(Math.pow(5, 4)%3);
//        System.out.println(25%3);
//        System.out.println(Math.pow(2, 20)%3);
//        System.out.println(Math.pow(2, 10)%3);
//        System.out.println(Math.pow(2, 123)%3);
//        //1.0
//        System.out.println(Math.pow(2, 100)%3);
//        System.out.println(Math.pow(2, 10)%3);
        System.out.println(Math.pow(2, 11));
        System.out.println(Math.pow(2, 11)%1337);
        System.out.println(Math.pow(2, 200)%1337);
        System.out.println(Math.pow(4, 100)%1337);
        System.out.println(Math.pow(711,18)%1337);
        System.out.println(Math.pow(2,11)%1337);
        //Case 2 : Case này bị sai khi lấy số ^ (Số dư) nhân liên tiếp với nhau
        System.out.println((Math.pow(711,18)%1337*(4%1337))%1337);
        //Thử tính chất số mũ
        System.out.println("=================Test tính chất số mũ=================");
        System.out.println(Math.pow(3,23)%11);
        System.out.println((9*Math.pow(3,21))%11);
        System.out.println(Math.pow(2,23)%3);
        System.out.println((Math.pow(2,3))%3);
        System.out.println("=================Test tính chất số mũ=================");
        System.out.println("=================");

//        System.out.println(64%14);
//        System.out.println(56%14);
//        System.out.println(Math.pow(8,14)%14);
        //
        //1, Bài này học được khá nhiều skill liên quan đến:
        //1.1, Kỹ năng phân tích bài toán --> chia ra nhiều kiểu tư duy + loại dần
        //+ Gạch các ý tưởng đầu dòng --> Sau đó loại dần (Thực ra cái này từ trước mình vẫn làm)
        //Chỉ là mình làm trên giấy thôi.
        //VD: Ở bài này ta có rất nhiều tư duy bị loại như:
        //1.1.1, Trừ dần dần (số mũ) --> Tránh quan tâm đến độ lớn của số arr
        //==> Vì chia theo hàng trăm, hàng chục ==> Tính dần dần vẫn phải tính
        //--> Sai
        //==> a range khá lớn --> việc tính ^ cho a là không thể
        //1.1.2, Chuyển đổi dãy đó --> 1337
        //--> Quy luật % * % ---> % tiếp sẽ ra kết quả chung
        //1.1.2.1,
        //+ 2000 chữ số quá lớn ==> chia ra thì thành nhiều số nhỏ hơn ---> Rất nhiều
        //VD: Nếu làm theo phương pháp phân tách dần dần chia dư (1337%8==0) >1337
        //4096
        //VD: 15 : 5 + 10 --> 15 lần loops
        //==> 2000 số ==> Có ít nhất 2000 lần loops
        //+ Chia đôi ra để đi binary --> Cũng rất tệ.
        //log2(n) : 2000 số (Khá tệ)
        //** ===> Cần 1 tư duy toán học hơn.
        //
        //1.2, Kỹ năng chuyển (1 test case sai/quá lớn --> 1 test case) có thể debug được local
        //VD: số quá lớn a=26214412312312, b=[2.0.0] ==>Chuyển (26214412312312 --> 2)
        // Để có thể test được với (b)
        //1.3, Cách đọc loop (for) để hiểu dễ dàng hơn:
        //Để phát hiện ra quy luật ta sẽ chỉ cần thử 2 --> 3 steps loop gần nhất.
        //---> Có thể hiện thị dưới dạng print ra.
        //1.4,
//        Với những dạng theo kiểu toán như thế này ==> Cố dùng toán để mà giải quyết
        //==> nếu chỉ dùng phép thử như bình thường thì rất khó có thể đánh giá được hết khả năng.
        //VD: 2^i % 1337 = k ---> Cần biến đổi ngược lại (2^i = 1337 * x + k)
        //Cái ta cần tính là 2^n theo 2^i
        // ==> 2^n = 1337 * x * 2^(n-i) + k * 2^(n-i)
        //==> Thay vì cố thử các case 2^i --> theo 2^n bằng cách thử
        //VD: (2^3)^15 %k có giống (2^3%k)^15 hay không.
        //--> Thử như thế này có thể (rất mất thời gian) + sai sót.
        //Bài này tư duy như sau:
        //
        //Bài này ta có 4 cách làm:
        //Cách 1:
        //https://leetcode.com/problems/super-pow/discuss/1124654/Java-4ms-solution-use-HashMap
        //1, Tính chất nhân số dư
        // x= a*b
        //+ a%1337=x <=> 1337*k + x = a
        //+ b%1337=y <=> 1337*k1 + y = b
        //--> Nhân 2 số a*b = 1337*(k*k1 + 1337 * y + 1337 * x) + (x*y)
        //==> a*b= (x*y) %1337
        //
        //2,
        //2.1,Ta sẽ áp dụng tính chất cycle của phép %
        //VD: ( a*x ) %1337 với (x++)
        //=> Cho có thể cho kết quả nằm trong (0,1337)
        //- Tính chất cycle back lại vị trí first (Nếu số đủ lớn)
        //VD:
        //2%5
        //pow=1: 2%5=(2)
        //pow=2 :(2^2)%5=(4)
        //pow=3 :(2^3)%5=3
        //pow=4 :(2^4)%5=1
        //pow=5 :(2^5)%5=(2)
        //pow=6 :(2^6)%5=(4)
        //---> Số quá lớn --> quay lại vị trí (first) (Xoay vòng)
        //- Vì tính chất xoay vòng
        //==> a^k --> Luôn luôn thuộc (0 --> 1337)
        //+ Giả sử pow(max) = power (Là số mũ lớn nhất để SỐ DƯ ROLLBACK)
        //a^(power+1) % N = a (Vì là số đầu tiên)
        //<=> (a^power * a ) % N = a
        //<=> (a^power * a ) = N * k + a
        //<=> a^power = N*(K/a) + 1 ( Vì a^power là số tự nhiên --> N*(k/a) sẽ là (Integer)
        //==> [ a^power % N = 1 ]
        //** a^k % N = ( a^power * a ^ (k-power) )%N
        // a^k % N = ( a^power % N) * (a ^ (k-power) %N )
        //a^k % N = ( 1 * a (k-power) ) %N
        //VD
        //a=2, b=23, số chia =5
        //+ power_max = 4 (Vì 5 ra dư trùng 1)
        //2^23 ==> Chỉ quan tâm đến số mũ
        //23 = (4)*5 + 3
        //23 % 4 = 3
        //==> (2^23 % N) = (2^3 % N)
        //
        //2.2, Vì ta tư duy thành số MŨ (exponent)
        //--> Mọi thứ đã trở nên dễ dàng hơn
        //- 1 số có 2 cách biểu diễn sau đây:
        //+ right --> left (1 cách tư duy bình thường)
        //+ left --> right (cách tư duy khác biệt hơn 1 chút)
        //VD:
        //123
        //+ right --> left :
        //123 = 3 + 2 * 10 + 1* 100 (Số tăng dần quá lớn)
        //==> Vì 100 được tính trực tiếp tại mỗi loop ==> Cách này không thể được
        //==> Ta cần dùng phép biến đổi làm sao (result_2) --> Tính theo (result_1)
        //+ left --> right
        //VD: (1*10 + 2) * 10 + 3
        //123
        //+ 0 * 10 + 1
        //+ 1 * 10 + 2
        //+ 12 * 10 + 3
        //3, Vì dùng cách tính trùng hay không phân theo power
        //- Ta cần 1 haset để check trùng
        //- 1 hasmap để lưu thông tin (power(số mũ), value) ==> Value chính là (số dư)
        //==> khi xác định số mũ cần tính ---> Ta chỉ cần lây (số dư) dựa trên power là xong.
        //
        //Cách 2: Áp dụng tính chất euler
        //1, Ở đây hashSet.size() sẽ được thay cho (số lượng các số nguyên tố với 1337)
        //Theo ct Euler thì:
        //- (a^(b%1140))%m
        //
        //2, Và phần tính kết quả là pow(n, m) % 1337 ==> Ta có thể kết hợp 1337 vào để tính (%) dần dần ra kết quả.
        //3, Tốc độ:
        //- b&1 nhanh hơn so với > (b%2) (Vì nó chỉ (and bit 1)))
        //- b>>=1 nhanh hơn so với > b/2 (Vì nó chỉ là phép dịch bit))
        //Method:
        //https://leetcode.com/problems/super-pow/discuss/1873389/Java-2-Approaches%3A-BF-and-Binary-Exponentiation
        //https://leetcode.com/problems/super-pow/discuss/2412552/Java-or-Binary-Exponentiation-%2B-Euler-Totient-Function-(ETF)-with-Explanation
        //https://leetcode.com/problems/super-pow/discuss/84504/Java-4ms-short-solution
        //https://leetcode.com/problems/super-pow/discuss/1124654/Java-4ms-solution-use-HashMap

//        System.out.println(superPowWrong(a, arr));
        System.out.println(superPow(a, arr));
        System.out.println(superPowEulerBinary(a, arr));
        //https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
        //https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
    }
}
