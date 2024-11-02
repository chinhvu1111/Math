package E1_daily;

public class E135_FindKthBitInNthBinaryString {

    public static long reverseBit(long curVal){
        //1011001
        //=>
        //1001101
        //110
        //=>
        //011
        long newVal=0;
        while (curVal!=0){
            long curBit=curVal&1;
            newVal=newVal|curBit;
            curVal=curVal>>1;
            newVal=(newVal<<1);
        }
        return newVal>>1;
    }

    public static char findKthBit1(int n, int k) {
        //1
        //1+1+1
        //3+1+3
        //7+1+7
        //...
        long len=1;
        long curVal=0;
        for(int i=1;i<n;i++){
            //len=2
            //100
            long left = curVal<<(len+1);
            long midVal = 1L <<(len);
            curVal = left | midVal | reverseBit(~curVal&(midVal-1));
            len=2*len+1;
            System.out.println(curVal);
        }
//        len=(len-1)/2;
        int temp = 1<<(len-k);
        curVal=curVal&temp;
        if(curVal==0){
            return '0';
        }
        return '1';
    }

    public static char findKthBit(int n, int k) {
        if(n==1){
            return '0';
        }
        int len=1<<n;
        if(k<len/2){
            return findKthBit(n-1, k);
        }else if(k==len/2){
            return '1';
        }else{
            char bit = findKthBit(n-1, len-k);
            return bit=='1'?'0':'1';
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //-
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n <= 20
        //1 <= k <= 2^n - 1
        //  + N cũng không lớn: Time: O(n^3)
        //  + 1*2*2*...*2 (n lần) = 2^20 không quá lớn
        //      ==> Dùng bit để biểu diễn vẫn được.
        //
        //- Brainstorm
        //- Nó yêu cầu return bit thứ kth
        //  + Có thể không cần tính hết
        //S1 = "0"
        //Si = Si - 1 + "1" + reverse(invert(Si - 1)) for i > 1
        //
        //
        int a = 178;
        //10110010
        //=> 01001101 (77)
        //~178 = -179
        //-10110011 => 1111111101001101
        System.out.println(0xff);
        System.out.println(~a);
        System.out.println(~a&0xff);
        //10
        //=>
        //01
//        int b=2;
//        int b=3;
        //110 -> 011
        int b=6;
        System.out.println(reverseBit(b));
        //Flip all of bits:
        //101 => 010
        int num=5;
        int mask = (1 << 3) - 1;
        System.out.println(~num&mask);
        //
        //- Invert bit
        System.out.println("================");
//        int n=3,k=1;
//        int n=4,k=11;
        int n=16,k=32944;
//        System.out.println(findKthBit1(n, k));
        System.out.println(findKthBit(n, k));
        //int flipBits2(int n, int k) {
        //    int mask = (1 << k) - 1;
        //    return ~n & mask;
        //}
        //
        //- Nếu làm theo số bình thường ==> Bị âm số (do int/ long không đủ lưu)
        //+ Do length của số tăng theo cấp số nhân ==> 10000 (Số lớn)
        //
        //- Ta thấy rằng ta không cần tính toàn bộ bit
        //- Dựa trên k ta có thể tính được bit này được gen từ đâu
        //S1 = "0"
        //S2 = "011"
        //S3 = "0111001"
        //S4 = "0111001[1]01(1)0001"
        //- Bit thứ k
        //- len= 1<<n (số bit cho đến lần nth operation)
        //- k<len/2
        //  + ==> Có thể tính theo (n-1)th operation
        //  + return finKthBit(n-1, k)
        //- k==len/2: return '1'
        //Ex:
        //S4 = "0111001[1]01(1)0001"
        //  (1) ==> Có thể tính theo 01(1)0[0]01
        //  + Bởi vì reverse(bit)'
        //  + Thế nếu flip thì sao:
        //      + Ở đây ta sẽ lấy ~findKthBit(n-1,len-k)
        //  ==> len-k
        //- k>len/2
        //  ==> Có thể tính theo (n-1,len-k) + ~findKthBit(n-1,len-k)
        //
        //https://stackoverflow.com/questions/6351374/bitwise-operator-for-simply-flipping-all-bits-in-an-integer
        //
        //#Reference:
        //1103. Distribute Candies to People
        //2515. Shortest Distance to Target String in a Circular Array
        //916. Word Subsets
        //https://leetcode.com/problems/word-subsets/description/
    }
}
