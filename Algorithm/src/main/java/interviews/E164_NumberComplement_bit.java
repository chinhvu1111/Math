package interviews;

public class E164_NumberComplement_bit {

    public static int findComplement(int num) {
        int countBit=0;
        int n=num;

        while (n!=0){
            n=n>>1;
            num = num ^ (1<<countBit);
            countBit++;
//            System.out.printf("%s, ", num);
        }
//        System.out.println(countBit);
        return num;
    }

    public static int findComplementMethod2(int num) {
        int countBit=0;
        int n=num;

        while (n!=0){
            n=n>>1;
            num = num ^ (1<<countBit);
            countBit++;
//            System.out.printf("%s, ", num);
        }
        int mask=(1<<countBit)-1;
//        System.out.println(countBit);
        return num&mask;
    }

    public static void main(String[] args) {
        //101
        int n=5;
        System.out.println(findComplement(n));
        //
        //** Đề bài:
        //- Tìm flip của số n (binary)
        //VD: 101 -> flit(101) = 010 (2)
        //
        //** Bài này tư duy như sau:
        //Cách 1:
        //1,
        //1.1, Đầu tiên ta phải tìm số bit của số n
        //- VD: 5 (101) count of bits = 3
        //
        //1.2, Từng bit ta sẽ flit dần
        //- CT: n= n xor(^) (1 << countBits)
        //# Reference
        //- Minimum Number of K Consecutive Bit Flips
        //- Maximum Score Words Formed by Letters
        //- Minimum Time to Kill All Monsters
        //- Find Minimum Time to Finish All Jobs
        //- Find Kth Largest XOR Coordinate Value
        //- Minimum Score After Removals on a Tree
        //
        //Cách 2:
        //1,
        //1.1,
        //- Với tư duy này ta sẽ tạo ra 1 mask để XOR:
        //- Mục đích của việc flip bits là chuyển từ :
        //+ 0 --> 1
        //+ 1 --> 0
        //- Nếu dùng phép XOR thì ta sẽ suy MASK như thế nào:
        //+ 0 --> 1 ==> mask = 1
        //+ 1 --> 0 ==> mask = 1
        //===> mask toàn là 1111... ==> Sẽ ổn, số lượng bits = length(bits)
        //1.2, 1111 = 10000 - 1 = 1<<(length+1) - 1
        //VD:
        //101011
        //Mask = 111111
        //-->
        //010100
        //
        System.out.println("=============");
        System.out.println(1^1);
        System.out.println(0^0);
        System.out.println("=============");
        //https://leetcode.com/problems/number-complement/discuss/96026/Python-4-ways
        System.out.println(findComplementMethod2(n));
    }

}
