package E1_daily;

public class E21_NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

    public static int numSteps(String s) {
        char[] binary = s.toCharArray();
        char[] binaryShift = s.toCharArray();
        int countBit=0;

        for(char c: binary){
            if(c=='1'){
                countBit++;
            }
        }
        int n=s.length();
        int rs=0;

        //Time : O(k)
        while (countBit != 1 || binary[n - 1] == '0'){
            //10 -> 11
            //111 -> 1000 -> 100
            int i=n-1;
            if(binary[i]=='0'){
                //1100 -> 0110
                //0110
                //bi[i] = bi[
                i=0;
                //Time : O(n)
                while (i<n){
                    if(i!=0){
                        binaryShift[i]=binary[i-1];
                    }else{
                        binaryShift[i]='0';
                    }
                    i++;
                }
                for(int j=0;j<n;j++){
                    binary[j]=binaryShift[j];
                }
                rs++;
                continue;
            }

            while (i>=0&&binary[i]!='0'){
                binary[i]='0';
                countBit--;
                i--;
            }
            if(i<0){
                binary[0]='1';
                rs+=2;
            }else{
                binary[i]='1';
                rs++;
            }
            countBit++;
        }
        return rs;
    }
    public static void addOne(StringBuilder s){
        int i =s.length()-1;

        while (i>=0&&s.charAt(i)!='0'){
            s.setCharAt(i,'0');
            i--;
        }
        if(i<0){
            //Thêm vào đằng trước thay vì set như mình
            //1111 ==> 10000
            //==> Đoạn này thì như code kia thì mình sẽ làm 2 operations cùng lúc luôn
            //1111 ==> 10000 ==> 10000 : Để length luôn ==n
            s.insert(0, '1');
        }else{
            s.setCharAt(i, '1');
        }
    }

    public static void divideTwo(StringBuilder s){
        s.deleteCharAt(s.length()-1);
    }

    public static int numStepsReference(String s) {
        StringBuilder s1=new StringBuilder(s);
        int n=s.length();
        int rs=0;

        while (s.length()>1){
            if(s.charAt(n-1)=='0'){
                divideTwo(s1);
            }else{
                addOne(s1);
            }
            rs++;
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (the binary representation of an integer) as a string s,
        //* Return (the number of steps) to reduce (it to 1) under the following rules:
        //  + If the current number is even, you have to (divide it by 2).
        //  + If the current number is odd, you have to (add 1 to it).
        //It is guaranteed that you can always reach one for (all test cases).
        //- Return số step để reach được 1
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 500
        //s consists of characters '0' or '1'
        //s[0] == '1'
        //==> Length khá lớn nên không thể đổi ra hexa format được.
        //
        //- Brainstorm
        //Ex:
        //Input: s = "1101"
        //Output: 6
        //Explanation: "1101" corressponds to number 13 in their decimal representation.
        //Step 1) 13 is odd, add 1 and obtain 14. : 1101 -> 1110
        //Step 2) 14 is even, divide by 2 and obtain 7. 1110 -> 0111
        //Step 3) 7 is odd, add 1 and obtain 8. 0111 -> 1000
        //Step 4) 8 is even, divide by 2 and obtain 4. 1000 -> 100
        //Step 5) 4 is even, divide by 2 and obtain 2. 100 -> 10
        //Step 6) 2 is even, divide by 2 and obtain 1. 10 -> 1
        //
        //1.1, Optimization
        //- Dùng StringBuilder --> Delete character dễ hơn.
        //- Chia thành 2 function:
        //  + Add one : Lý thuyết add one thì i hệt
        //      ==> Tách ra nên dễ hơn thôi
        //  + Divide 2
        //      + Đoạn này delete(n-1) sẽ nhanh hơn là shift toàn bộ string.
        //===============
        //if(i<0){
            //Thêm vào đằng trước thay vì set như mình
            //1111 ==> 10000
            //==> Đoạn này thì như code kia thì mình sẽ làm 2 operations cùng lúc luôn
            //1111 ==> 10000 ==> 10000 : Để length luôn ==n
        //  s.insert(i, '1');
        //}else{
        //  s.setCharAt(i, '1');
        //}
        //===============
        //
        //1.2, Complexity
        //- K is the number of 1 bit
        //- Time : O(k*n) => O(n^2)
        //- Space : O(n)
        //
        //2. Greedy
        //2.0,
        //- Brainstorm
        //- Ta thấy:
        //  + last char = '1':
        //      + rs+=2
        //      carry = 1 ==> Nợ
        //  + last char = '0'
        //      + rs+=1
        //- Carry sẽ được cộng vào character sau
        //  + Mặc kệ có có change các ký tự sau k
        //
        //2.1, Optimization
        //
        //2.2, Complexity
        //- K is the number of 1 bit
        //- Time : O(n)
        //- Space : O(1)
        //
        //#Reference:
        //2139. Minimum Moves to Reach Target Score
        //
        String s = "1101";
        System.out.println(numSteps(s));
        System.out.println(numStepsReference(s));
    }
}
