package E1_daily;

public class E75_UglyNumberIII {

    public static long UCLN(long a, long b){
        while(a<b&&a!=0){
            long temp = b%a;
            b=a;
            a=temp;
//            System.out.printf("a: %s, b: %s\n", a, b);
        }
        return b;
    }

    public static long USCLN(long a, long b) {
        if (b == 0) return a;
        return USCLN(b, a % b);
    }

    public static long BCNN(long a, long b){
        return (a*b)/USCLN(a, b);
    }

    public static int nthUglyNumber(int n, int a, int b, int c) {
        long low=1, high=1_000_000_000_000L;
        long bclnAB = BCNN(a, b);
        long bclnBC = BCNN(b, c);
        long bclnAC = BCNN(a, c);
        long bclnABC = BCNN(BCNN(a, c), b);
        long rs=-1;

        //Time: O(log(max))
        while (low<=high){
            long mid=low+(high-low)/2;
            long countA = mid/a;
            long countB = mid/b;
            long countC = mid/c;
            long countUclnAB = mid / bclnAB;
            long countUclnBC = mid / bclnBC;
            long countUclnAC = mid / bclnAC;
            long countUclnABC = mid / bclnABC;
            long curVal = countA+countB+countC-countUclnAB-countUclnBC-countUclnAC + countUclnABC;
//            System.out.println(mid);

            if(curVal>=n) {
                if(curVal==n
                        &&(mid%a==0||mid%b==0||mid%c==0)){
                    rs=mid;
//                    System.out.println(rs);
                }
                high = mid-1;
            }else{
                low=mid+1;
            }
        }
        return (int) rs;
    }

    public static void main(String[] args) {
        // Requirement
        //- (An ugly number) is (a positive integer) that is divisible by (a, b, or c).
        //Given four integers n, a, b, and c,
        //* Return (the nth ugly number).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n, a, b, c <= 10^9
        //1 <= a * b * c <= 10^18
        //It is guaranteed that the result will be in range [1, 2 * 10^9].
        //  + (n khá lớn 10^9) ==> Không thể loop full được
        //
        //- Brainstorm
        //- Khi x % (a,b,c)
        //  + x % (a*b*c) == 0
        //  ==> Có thể a%b==0/ a%c==0
        //  ==> Nên không cần phải %(a*b*c) == 0
        //==> WRONG do đọc sai đề
        //
        //- Tìm số chia hết cho a or b or c
        //  + Chứ không phải cả (a,b,c)
        //
        //- Bài này số lớn 10^9, chỉ có thể:
        //  + Phương pháp đặc biệt: Math
        //  + Dùng binary search thôi
        //
        //- Làm sao đế biết được value đang đứng thứ bao nhiêu trong chuỗi?
        //Ex:
        //a = 2, b = 3, c = 5
        //Giả sử có x:
        //x = i*a
        //x = i1*b
        //x = i2*c
        //Output: 4
        //Explanation: The ugly numbers are 2, 3, 4, 5, 6, 8, 9, 10... The 3rd is 4.
        //4 = 2*2
        //4 >= 3*1
        //4 = 4*1
        //==> ith = 2+1+1 = 4
        //
        //- Count là chưa đủ vì:
        //  + a,b,c có thể chia hết cho nhau
        //Ex:
        //a = 2, b = 3, c = 5
        //2,3,4,5,6
        //
        //- count(UCL) của 3 thằng cho đến x là bao nhiêu?
        //- Tính UCLN:
        //  + a = 4, b = 6
        //      + a=b%a, b= old_a
        //- Tính BCNN:
        //  + (a*b)/USCLN(a, b)
        //
//        int n = 3, a = 2, b = 3, c = 5;
//        int n = 4, a = 2, b = 3, c = 4;
//        int n = 1000000000, a = 2, b = 217983653, c = 336916467;
        //CT:
        //countA + countB + countC - countBCNN(A,B) - countBCNN(B,C) - countBCNN(A,C) + countBCNN(A,B,C)
        //- Chứng minh:
        //+ BCNN(A,B) trùng với BCNN(B,C) 1 phần
        //+ BCNN(B,C) trùng với BCNN(A,C) 1 phần
        //+ BCNN(A,B) trùng với BCNN(A,C) 1 phần
        //+ Nếu ta trừ ntn thì sẽ thiếu BCNN(A,B,C):
        //  + Thế nên ta cần + thêm countBCNN(A,B,C)
        //
        //----- Đoạn này không cần thiết
        //+ countA + countB và countBCNN(A,B) sẽ trùng 1 phân BCNN(A,B) : Ta sẽ trừ đi
        //+ countB + countC và countBCNN(B,C) sẽ trùng 1 phân BCNN(B,C) : Ta sẽ trừ đi
        //+ countA + countC và countBCNN(A,C) sẽ trùng 1 phân BCNN(A,C) : Ta sẽ trừ đi
        //-----
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(Log min(x, y))
        //  + https://www.geeksforgeeks.org/time-complexity-of-euclidean-algorithm/
        //  + The time complexity of this optimized Euclidean algorithm is O(log(max(a, b)))
        //  since the algorithm reduces the values quickly (by taking the modulus).
        //- Phương pháp quy nạp toán học:
        //  + https://www.geeksforgeeks.org/principle-of-mathematical-induction/
        //  + https://stackoverflow.com/questions/3980416/time-complexity-of-euclids-algorithm
        //
        //- Best explainion:
        //- PROOF:
        //There are two cases.
        // If N <= M/2, then since the remainder is smaller than N, the theorem is true for this case.
        // The other case is N > M/2.
        // But then N goes into M once with a remainder M - N < M/2, proving the theorem.
        //
        //So, we can make the following inference:
        //Variables    M      N      Rem
        //initial      M      N      M%N
        //1 iteration  N     M%N    N%(M%N)
        //2 iterations M%N  N%(M%N) (M%N)%(N%(M%N)) < (M%N)/2
        //- So, after two iterations, (the remainder) is ("at most") (half of its original value).
        //==> This would show that the number of iterations is at most 2logN = O(logN).
        //- X/2, X/4, X/8 ==> N/(2^n) = 1
        //  + N=log2(X)
        //# Reference:
        //- https://stackoverflow.com/questions/3980416/time-complexity-of-euclids-algorithm
        //
        //- Note that, the algorithm computes Gcd(M,N), assuming M >= N.(If N > M, the first iteration of the loop swaps them.)
        //
        //** Đọc mấy cái này khá khó hiểu:
        //  + Nên đọc cái bên trên:
        //  - Time = (log(max(a,b)) là được.
        //
        //a', b'
        //b%a, a
        //a%(b%a), b%a
        //Nếu ta đổi role a cho b
        //<=>
        //a % b, b % (a % b)
        //
        //- N steps:
        //- Mất N steps để biến (b == 0)
        //  GCD(a,b) ==> N steps
        //  + GCD(a,b) : 0
        //  + GCD(f1,a) : 1
        //  + GCD(f2,f1) : 2
        //
        //
        //- Phân tích UCLN algorithm:
        //- Time: O(Log min(x, y))
        //a = 1000, b = 300
        //a = 1000%300=, b= 1000
        //a = 1000%300, b= 1000
        //
        //a,b
        //b%a,a
        //a%(b%a),b%a
        //b%a%(a%(b%a)),a%(b%a)
        //a%(b%a)%(b%a%(a%(b%a))), b%a%(a%(b%a))
        //- Giả sử:
        //
        //Cải Tiến Thuật Toán Euclid
        //
        //Trong mục 2 khi a và b cách xa nhau thì thuật toán Euclid hoạt động không hiệu quả,
        // ví dụ bạn tìm ULCN(1000000000, 1) thì thuật toán cần lặp 999999999 lần.
        //- Ta cải tiến thuật toán Euclid bằng nhận xét sau :
        //  + UCLN của hai số nguyên không thay đổi khi thay 1 trong 2 số thành số dư của nó với số còn lại.
        //  + Có nghĩa là UCLN(a, b) = UCLN(b, a % b)
        //
        int n = 5, a = 2, b = 3, c = 3;
        //2,3,4,6,8,10
        //9/2 = 4
        //9/3 = 3
        //9/3 = 3
        //+ countBNNN(3,3) = 3
        //+ countBNNN(3,3) = 3
        //+ countBNNN(2,3) = 6
        //+ count
        //
        //#Reference:
        //800. Similar RGB Color
        //1782. Count Pairs Of Nodes
        //1304. Find N Unique Integers Sum up to Zero
        System.out.println(nthUglyNumber(n, a, b, c));
//        System.out.println(BCLN(2,4));
//        System.out.println(BCLN(3,3));
//        System.out.println(BCLN(2,3));
//        System.out.println(BCLN(BCLN(2,3), 4));
        //- UCLN cần phân biệt số lớn với số nhỏ hơn
//        System.out.println(UCLN(6,4));
        //- Với mỗi step thì nó sẽ giảm đi
        System.out.println(UCLN(300, 1000));
    }
}
