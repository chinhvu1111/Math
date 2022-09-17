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
//        System.out.println(countBit);
        return num;
    }

    public static void main(String[] args) {
        //101
        int n=5;
        System.out.println(findComplement(n));
        //** Đề bài:
        //- Tìm flip của số n (binary)
        //VD: 101 -> flit(101) = 010 (2)
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
        //
        //Cách 2:
        //https://leetcode.com/problems/number-complement/discuss/96026/Python-4-ways
        System.out.println(findComplement(n));
    }

}
