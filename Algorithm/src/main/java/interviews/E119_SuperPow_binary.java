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
        for(int i=0;i<n;i++){
            initPower=(initPower*10+b[i])%hashSet.size();
            System.out.printf("%s %s\n",b[i],initPower);
        }
        return map.get(initPower);
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
        System.out.println("=================");

//        System.out.println(64%14);
//        System.out.println(56%14);
//        System.out.println(Math.pow(8,14)%14);

        //- Kỹ năng phân tích bài toán --> chia ra nhiều kiểu tư duy + loại dần
        //- Kỹ năng chuyển 1 test case sai --> 1 test case có thể debug được local

        //Method:
        //https://leetcode.com/problems/super-pow/discuss/1873389/Java-2-Approaches%3A-BF-and-Binary-Exponentiation
        //https://leetcode.com/problems/super-pow/discuss/2412552/Java-or-Binary-Exponentiation-%2B-Euler-Totient-Function-(ETF)-with-Explanation
        //https://leetcode.com/problems/super-pow/discuss/84504/Java-4ms-short-solution
        //https://leetcode.com/problems/super-pow/discuss/1124654/Java-4ms-solution-use-HashMap

//        System.out.println(superPowWrong(a, arr));
        System.out.println(superPow(a, arr));
        //https://leetcode.com/problems/find-n-unique-integers-sum-up-to-zero/
        //https://leetcode.com/problems/find-the-kth-largest-integer-in-the-array/
    }
}
