package E1_daily;

public class E234_MinimizeXOR {

    public static int minimizeXor(int num1, int num2) {
        int countBit = Integer.bitCount(num2);
        //3 = 011
        //
        int temp=num1;
        //num1 = 011, num2= 10101
        //x=111
        int len=0;
        while (temp!=0){
            temp=temp>>1;
            len++;
        }
        int x=0;
        temp=num1;
        int count1 = Integer.bitCount(num1);
        //10[1]011
        while(countBit>0&&count1>0){
            int curBit = (temp>>(len-1))&1;
            if(curBit==1){
                x=x|(1<<(len-1));
                countBit--;
                count1--;
            }
            len--;
        }
//        while (remainingCount>0||temp!=0){
//            x=x|1<<len;
//            remainingCount--;
//            len++;
//            temp=temp>>1;
//        }
        temp=x;
        int remainingCount=countBit-count1;
        int len1=0;
        while (remainingCount>0){
            int curBit = temp&1;
            if(curBit==0){
                remainingCount--;
                x = x|(1<<len1);
            }
            temp=temp>>1;
            len1++;
        }
        return x;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two positive integers num1 and num2, find the positive integer x such that:
        //  + x has the same number of set bits as num2, and
        //  + The value (x XOR num1) is (minimal).
        //* Note that XOR is (the bitwise XOR operation).
        //* Return the integer x. The test cases are generated such that x is uniquely determined.
        //The number of set bits of an integer is the number of 1's in its binary representation.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= num1, num2 <= 10^9
        //  + val<=10^9 ==> Time: O(log(n))
        //
        //- Brainstorm
        //- a xor b is minimal when:
        //  + the number of overlap set bit is (maximal)
        //  ==> Should be overlapped from (left to right)
        //Example 1:
        //Input: num1 = 3, num2 = 5
        //num1 = 011, num2 = 101
        //
        //
        System.out.println(1<0);
//        int num1 = 3, num2 = 5;
//        int num1 = 1, num2 = 12;
        //num2 = 1100
//        int num1 = 25, num2 = 72;
        //num1 = 11001, num2 = 1001000
        //rs=24
        //x=11000
        //x xor num1 = 00001
//        int num1 = 79, num2 = 74;
        //num1 = 1001111, num2 = 1001010
        //1001100
        int num1 = 65, num2 = 84;
        //num1 = 1000001, num2 = 1010100
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(1)
        //
        System.out.println(minimizeXor(num1, num2));
        //
        //#Rerference:
        //1707. Maximum XOR With an Element From Array
    }
}
