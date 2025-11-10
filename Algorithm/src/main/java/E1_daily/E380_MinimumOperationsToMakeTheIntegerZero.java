package E1_daily;

public class E380_MinimumOperationsToMakeTheIntegerZero {

    public static int makeTheIntegerZero(int num1, int num2) {
        int k=1;

        while(true){
            long newNum1 = num1-(long)num2*k;
//            long newNum1 = num1-(long)(k*num2);
            //==> long newNum1 = num1-(long)num2*k;
            if(newNum1<k){
                return -1;
            }
            if(k>=Long.bitCount(newNum1)){
                return k;
            }
            k++;
        }
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two integers (num1 and num2).
        //- In one operation,
        // you can choose integer i in the range [0, 60] and subtract (2^i + num2) from num1.
        //* Return (the integer) denoting (the minimum number of operations) needed to make (num1) equal to (0).
        //- If it is impossible to make num1 equal to 0,
        //  + return -1.
        //
        //Example 1:
        //Input: num1 = 3, num2 = -2
        //Output: 3
        //Explanation: We can make 3 equal to 0 with the following operations:
        //- We choose i = 2 and subtract 22 + (-2) from 3, 3 - (4 + (-2)) = 1.
        //- We choose i = 2 and subtract 22 + (-2) from 1, 1 - (4 + (-2)) = -1.
        //- We choose i = 0 and subtract 20 + (-2) from -1, (-1) - (1 + (-2)) = 0.
        //It can be proven, that 3 is the minimum number of operations that we need to perform.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //-
        //
        //* Brainstorm:
        //- Formula:
        //- x = (2^x+2^y+...+2^z)+num2*k
        //- (2^x+2^y+...+2^z) + (2^z)
        //==> bit (111...11)
        //- 3 = 1*2^1+1*2^0 = 11
        //
        //Example 1:
        //Input: num1 = 3, num2 = -2
        //Output: 3
        //
        //==> num1 - k*num2 = 1...1
        //2^2 = 100
        //=> 2^2+2^2 = 1000
        //- Every number will be presented as (the binary format)
        //- 3-(-2)*k = 1...1
        //- 3+2 = 5 = 101 ==> Need to have at least (2 bits)
        //Ex:
        //1000 = 0100 + 0100
        //  + 1 bit lớn có thể bằng sum 2 bit bên right
        //==> 1000 số lần = 2^3
        //
        //3 = 11
        //-2
        //  + 2 = 10 ==> 01+1 = 10
        //- K là số nhiều nhất bit của vế right
        //  <> tức là add k lần bit 1 ==> nó sẽ chỉ triệt tiêu hoặc giữ nguyên thôi
        //  + For k: 1 -> khi tìm được k>= bit count(right)
        //  ==> return k
        //- Khi nào thì return -1?
        //  + When k>x, even k copies of (2^0=1) are not sufficient.
        //  ==> Tức (num1 - k*num2) không thể biến đổi thành (k*1) không thành binary được nữa
        //- Độ phức tạp nếu for là bao nhiêu
        //
        //1.1, Case
        //
        //1.2, Optimization
        //- (long)*(x*y) != (long)x*y
        //
        //1.3, Complexity
        //- Time: O(n)
        //Each iteration increases k by 1 while simultaneously decreasing
        //x = num1-k*num2
        // Since x decreases monotonically with k, the number of iterations is bounded
        // by how quickly x becomes smaller than k.
        // This transition occurs at most on the order of because representing x
        // in terms of powers of two relates directly to its binary length.
        // Therefore, the overall time
        //==> biểu diễn binary của (num1)
        //  + Time: O(Log(n))
        //
        //- Space: O(1)
        //
//        System.out.println(Math.pow(2, 60));
        System.out.println(makeTheIntegerZero(3,-2));
    }
}
