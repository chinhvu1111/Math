package E2_math;

public class E2_ArmstrongNumber {

    public static boolean isArmstrong(int n) {
        int val=n;
        int numChar=String.valueOf(n).length();

        while(n!=0){
            int curNum=n%10;
            val-=Math.pow(curNum, numChar);
            n=n/10;
        }
        return val==0;
    }

    public static void main(String[] args) {
        // Requirement
        //- Given an integer n,
        //* return true if and only if it is an Armstrong number.
        //The k-digit number n is an Armstrong number if and only if the (kth) (power of each digit sums) to n.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10^8
        //
        //- Brainstorm
        //- Làm bình thường thì sẽ tính sum pow tất cả các chữ số xem có bằng val hay không
        //1.1, Optimization
        //- Thay vì cast ra string + length của nó, ta có thể lấy length của 1 số bằng log:
        //  + int length = (int) Math.log10(n) + 1;
        //Ex:
        //log10(1)=0
        //log10(2)=0.301
        //log10(9)=0.954
        //log10(10)=1
        //log10(99)=1.995
        //log10(100)=2
        //log10(1000)=3
        //
        //1.2, Complexity
        //- N is the length of number
        //- Space : O(1)
        //- Time : O(N)
        //
        //#Reference:
        //1577. Number of Ways Where Square of Number Is Equal to Product of Two Numbers
        //282. Expression Add Operators
        //537. Complex Number Multiplication
    }
}
