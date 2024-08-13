package contest;

public class E157_MaximumNumberOfOperationsToMoveOnesToTheEnd {

    public static int maxOperations(String s) {
        int n=s.length();
        int rs=0;
        int firstOne=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                firstOne=i;
                break;
            }
        }
        int countOne=0;
        int countZero=0;

        for (int i = firstOne; i < n; i++) {
            if(s.charAt(i)=='0'){
                if(i==n-1){
                    rs+=countOne;
                }
                countZero++;
            }else{
                if(countZero>0){
                    rs+=countOne;
                }
                countOne++;
                countZero=0;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (a binary string s).
        //You can perform the following operation on the string any number of times:
        //  - Choose any index i from the string where (i + 1) < s.length such that
        //      + s[i] == '1' and s[i + 1] == '0'.
        //  - Move the character s[i] to the right until it reaches
        //      + the end of the string or
        //      + another '1'.
        //  For example, for s = "0(1)0010", if we choose i = 1, the resulting string will be s = "000(1)10".
        //* Return the (maximum) number of operations that you can perform.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s[i] is either '0' or '1'.
        //==> Time: O(n*k)
        //
        //** Brainstorm
        //Example 1:
        //Input: s = "1001101"
        //Output: 4
        //Explanation:
        //
        //We can perform the following operations:
        //
        //Choose index i = 0. The resulting string is s = "0011101".
        //Choose index i = 4. The resulting string is s = "0011011".
        //Choose index i = 3. The resulting string is s = "0010111".
        //Choose index i = 2. The resulting string is s = "0001111".
        //
        //- Num steps:
        //  + 0011101
        //  ->0001111
        //
        //- Số lượng dịch là số lượng count để nó dịch thôi
        //- Có thể có case mà trailing 0 không?
        //Ex:
        // 1010111000
        //  ==> Không vì đều có thể dịch được.
        //- Traverse right -> left:
        //  + count zero
        //Ex:
        //1000011111
        //count+=4
        //==> SAI IDEA
        //
        //s = "1001101"
        //- Đi từ left
        //s = "00(1)101"
        //- count one
        //  ==> Khi gặp zero ==> rs+= countOne.
        //
        //Ex:
        //11000010001
        //->
        //10000110001: rs+=1
        //00001110001: rs+=1
        //00001110001: rs+=3
        //
        //s = 1001101
        //s = 0011101
        //==> rs=4
        //
        //11000010001000
        //rs= 5+3
        //
        //- Khi dịch 1 cái xong --> có 1 chỗ trống ==> Count Zero++;
//        String s = "1001101";
        String s = "11000010001000";
        //1001101
        //10010(1)1
        //  + countZero=1
        //1000(1)11
        //  + countZero=1
        //000(1)111
        //
        System.out.println(maxOperations(s));
    }
}
