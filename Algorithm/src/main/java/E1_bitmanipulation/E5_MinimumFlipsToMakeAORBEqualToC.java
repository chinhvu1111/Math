package E1_bitmanipulation;

public class E5_MinimumFlipsToMakeAORBEqualToC {

    public static int getBitAtPos(int pos, int val){
        //0(1)10000
        //==>
        //
        return val>>pos & 1;
    }

    public static int minFlips(int a, int b, int c) {
//        int lengthBit=Integer.bitCount(c);
        int aOrB=a | b;

        int pos=0;
        int rs=0;

        while(c >>pos!=0||aOrB>>pos!=0){
            int curDstBit=(c >> pos) & 1;
            int originalBit= (aOrB >> pos) & 1;
//            System.out.printf("CurDstBit: %s\n", curDstBit);
//            System.out.printf("OriginalBit: %s\n", originalBit);

            if(curDstBit!=originalBit){
                //1 -> 0
                if(originalBit==1){
                    if(((a>>pos)&1)==1){
                        rs++;
                    }
                    if(((b>>pos)&1)==1){
                        rs++;
                    }
                }else{
                    //0 -> 1
                    rs++;
                }
            }
//            tmpDst=tmpDst >> 1;
//            aOrB=aOrB >> 1;
            pos++;
        }
//        System.out.printf("%s\n", pos);
        return rs;
    }

    public static int minFlipsOptimization(int a, int b, int c) {
//        int lengthBit=Integer.bitCount(c);
        return Integer.bitCount((a|b)^c) + Integer.bitCount((a&b)&((a|b)^c) );
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given 3 positive numbers a,b,c
        //- Flip : 0 to 1 or 1 to 0
        //* Return minimum flips required in some bits of a and b to make (a or b ==c)
        //
        //** Idea
        //* Method-1:
        //1.
        //1.0,
        //- Constraint
        //1 <= a <= 10^9
        //1 <= b <= 10^9
        //1 <= c <= 10^9
        //
        //- Brainstorm
        //Ex:
        //Input: a = 2, b = 6, c = 5
        //Output: 3
        //Explain:
        //0010
        //or
        //0110
        //=
        //0110
        //+ Cần thay đổi từ
        //0110 -->
        //0101
        //=> Ta sẽ hướng đến việc flip change những bit không khớp
        //+ Ta sẽ flip bit thứ 3 từ 1 -> 0, bit thứ 4 từ 0 -> 1
        //+ Nếu change bit từ 1 -> 0 ta sẽ có 2 cases:
        //  + (1 or 0) = 1 --> 0 : Ta flip 1
        //  + (1 or 1) = 1 --> 0 : Ta flip 2
        //+ Nếu change bit từ 0 -> 1 ta sẽ có 1 case:
        //  + (0 or 0) = 0 --> 1 : Ta flip 2
        //==> Bài này ta sẽ tính toán trên từng bit lệch.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- N is the number of bit of the destination or the result of the formula (a or b)
        //
        //- Space: O(1)
        //- Time : O(n)
        //
        //2.
        //* Method-2:
        //2.1, Idea
        //- Ta dùng tính chất của XOR operation để tính
        //+ 0 xor 1 = 1
        //+ 1 xor 0 = 1
        //+ 1 xor 1 = 0
        //+ 0 xor 0 = 0
        //==> Số bit 1 của phép xor (a | b)^c chính là số bit ta cần flip
        //==> Tức là nó thể hiện cho sự khác nhau giữa (a|b) và c
        //
        //- Ngoài ra như trên ta cần phải xét các case đặc biệt:
        //+ bit(a)==1 or bit(b)==1 == 1
        //-> Ta cần flip cho cả a và b ==> rs+=2
        //--> Ở đây ta sẽ dùng phép & (AND) để tính số cặp bit như thế.
        //
        //+ Các cases khác nhau:
        //rs+= bitcount((a|b)^c) : Bao gồm all các cases
        //= A (Flip 0 -> 1/ 1 -> 0 - chỉ flip 1 bit) + B (1 -> 0 flip 2 bits ==> Nhưng ở đây chỉ cộng 1)
        //  + Ta thấy ở đây ta thiếu 1 lần flip của (1 -> 0 vì ở đây cần flip 2 bits)
        //+ Các cases đặc biệt cần flip 2 bit
        //  + countBit( a & b & ((a|b) ^ c) ) : Số case đặc biệt cần flip 2 bits.
        //rs+=countBit( a & b & ((a|b) ^ c) )
        //
        //rs= bitcount((a|b)^c) + countBit( a & b & ((a|b) ^ c) )
        //
        //2.1, Optimization
        //2.2, Complexity:
        //- Space : O(n) or O(1)
        //In C++ or Java, the space complexity is constant.
        //In Python, we use bin(a).count("1") to convert a into its binary representation, which is a string of length n.
        //- Time : O(n) or O(1)
        //In C++, __builtin_popcount() is a built-in function in GCC (and other compliers) which uses a precomputed lookup table to perform the operation in O(1) time.
        //In Java, Integer.bitCount() also uses a precomputed table of bit counts for every possible 16-bit integer value, and the bit count of a 32-bit integer can be computed by summing the bit counts of the two 16-bit halves of the integer, which takes O(1) time.
        //In Python (3.9 or earlier), we use bin(a).count("1") to count the number of set bits in the binary representation of a, which is equivalent to int.bit_count(a) in 3.10 or later. Both take O(n) time.
        //
        //#Reference:
        //2220. Minimum Bit Flips to Convert Number
        int a=2, b=6, c=5;
        System.out.println(minFlips(a, b, c));
        System.out.println(minFlipsOptimization(a, b, c));
    }
}
