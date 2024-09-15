package E1_daily;

import java.util.ArrayList;
import java.util.List;

public class E80_FindTheClosestPalindrome_hard_classic_palindrome {

    public static long getValueInRange(int left, int right, String n){
        long rs=0L;
        for(int i=left;i<=right;i++){
            rs=rs*10+n.charAt(i)-'0';
        }
        return rs;
    }

    public static long getPalindromeNumber(long x, boolean isOdd){
        //Ex:
        //1234|
        //1234[5]
        long rs=0L;
        long t=x;
        long temp=isOdd?x/10:x;
        //123|321
        while (temp!=0){
            rs=rs*10+temp%10;
            temp=temp/10;
            t=t*10;
        }
//        System.out.printf("t: %s, rs: %s\n", t, rs);
        rs=t+rs;
        return rs;
    }

    public static String nearestPalindromic(String n) {
        int len=n.length();
        long x=Long.parseLong(n);
        //Time: O(n)
        long leftHalf=getValueInRange(0, (len+1)/2-1, n);
//        if(leftHalf==1){
//            return "9";
//        }
//        System.out.println(leftHalf);
        List<Long> cases=new ArrayList<>();
        cases.add(getPalindromeNumber(leftHalf, len%2==1));
        cases.add(getPalindromeNumber(leftHalf+1, len%2==1));
        cases.add(getPalindromeNumber(leftHalf-1, len%2==1));
        //Cover case:
        //- n==100
        //  + rs = 99
        //Time: O(n)
        cases.add((long) (Math.pow(10, len-1)-1));
        //Cover case:
        //- n==99
        //  + rs = 101
        cases.add((long) (Math.pow(10, len)+1));
//        System.out.println(cases);

        //Ex:
        //aabb
        long rs=Long.MAX_VALUE;
        long diff=Long.MAX_VALUE;

        for(long e: cases){
            if(Math.abs(e-x)<=diff&&e!=x){
                if(e<rs&&diff==Math.abs(e-x)){
                    rs=e;
                }else if(Math.abs(e-x)<diff){
                    rs=e;
                    diff=Math.abs(e-x);
                }
            }
        }

        return String.valueOf(rs);
    }

    public static String nearestPalindromicRefer(String n) {
        int len = n.length();
        int i = len % 2 == 0 ? len / 2 - 1 : len / 2;
        long firstHalf = Long.parseLong(n.substring(0, i + 1));
        /*
        Generate possible palindromic candidates:
        1. Create a palindrome by mirroring the first half.
        2. Create a palindrome by mirroring the first half incremented by 1.
        3. Create a palindrome by mirroring the first half decremented by 1.
        4. Handle edge cases by considering palindromes of the form 999...
           and 100...001 (smallest and largest n-digit palindromes).
        */
        List<Long> possibilities = new ArrayList<>();

        possibilities.add(halfToPalindrome(firstHalf, len % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf + 1, len % 2 == 0));
        possibilities.add(halfToPalindrome(firstHalf - 1, len % 2 == 0));
        possibilities.add((long) Math.pow(10, len - 1) - 1);
        possibilities.add((long) Math.pow(10, len) + 1);

        // Find the palindrome with minimum difference, and minimum value.
        long diff = Long.MAX_VALUE, res = 0, nl = Long.parseLong(n);
        for (long cand : possibilities) {
            if (cand == nl) continue;
            if (Math.abs(cand - nl) < diff) {
                diff = Math.abs(cand - nl);
                res = cand;
            } else if (Math.abs(cand - nl) == diff) {
                res = Math.min(res, cand);
            }
        }

        return String.valueOf(res);
    }

    private static long halfToPalindrome(long left, boolean even) {
        // Convert the given half to palindrome.
        long res = left;
        if (!even) left = left / 10;
        while (left > 0) {
            res = res * 10 + (left % 10);
            left /= 10;
        }
        return res;
    }

    public static void main(String[] args) {
        //**Requirement
        //- Given a string n representing an integer,
        //* Return (the closest integer) (not including itself), (which is a palindrome).
        //- If there is (a tie), return (the smaller one).
        //- The closest is defined as (the absolute difference minimized) between (two integers).
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n.length <= 18
        //n consists of (only digits).
        //n does not have leading zeros.
        //n is representing an integer in the range [1, 10^18 - 1].
        //  + 10^18 số khá lớn ==> Không thể check từng số được
        //  + n > 0
        //- 10^18 ==> dùng Long type được.
        //
        //- Brainstorm
        //- Phần difference giữa 2 số ==> Tính được O(18 times)
        //- Gần nhất:
        //  + |a-b| min
        //
        //Ex:
        //3412|3123
        //3412|1243
        //3400|0043
        //Ex:
        //- số nhở hơn ít step hơn
        //3999|9998 (n)
        //3999|9993 (n-5)
        //4000|0004 (n+6)
        //
        //Ex:
        //- số lớn hơn ít step hơn
        //3999|9999 (n)
        //3999|9993 (n-6)
        //4000|0004 (n+5)
        //
        //- Có cases nào mà số x<n ==> Closest không
        //Ex:
        //  1200 - 1199 = 1 (Mặc dù số đằng trước 2 -> 1 nhưng vẫn rất nhỏ)
        //  12
        //  -
        //  11
        //  ==> 100 - số còn lại (99) == 1
        //- Case bên trên ta có thể check (diff) giữa:
        //  +  x|00000
        //  -
        //   x-1|.....
        //==> Nếu check sâu thêm nữa thì diff sẽ càng lớn ==> Càng tệ.
        //
        //3221|9999 (n)
        //3221|1223 (n-8776)
        //3219|9123 (n)
        //3220|0223 (n)
        //
        //* Ta thấy rằng nếu lấy reverse của left --> right:
        //  + Số x mới có thể:
        //      + x>n
        //          + Nếu reverse(left)>right
        //      + x<n
        //          + Nếu reverse(left)<right
        //==> Sẽ (không phải) là chia ra 2 cases x>n/x<n ==> WRONG.
        //
        //* Ta sẽ chia ra 2 case:
        //- reverse(left) > right
        //- reverse(left) < right
        //
        //- Xét reverse(left) > right:
        //3999|8888 (n)
        //3999|9993 (n+1105)
        //4000|0004 (n+1116) ==> Rõ ràng là lớn hơn vì (4000 0004 > 3999 9993 > 3999 8888)
        //  + Do tăng dần rồi ==> Không cần xét case này
        //
        //* Công thức:
        //Ex:
        //1546 (x) => reverse(x) = 6541
        //1734 (x1) => reverse(x1) = 4371
        //==> Số cuối hàng đơn vị thay đổi ntn cũng được
        //==> x<x1 (Vẫn thế)
        //==> x<x1 hay x>x1 không liên quan đến (reverse(x) > reverse(x1))
        //
        //- Chọn reverse(left) sao cho (closer) right hơn
        //3009|8888 (n)
        //3009|9003 (n+115)
        //* Chọn left(x) < left(n) (3008<3009):
        //  ==> Không liên quan đến reverse(left(x)) nhé vì:
        //  + Nếu ta chọn:
        //      2999|9992 (9992 > 9003)
        //3008|8003 ==> Worst
        //- Nếu chọn left(x) < left(n):
        //  ==> Ta cần phải giảm đi 1 khoảng ít nhất = right(n):
        //      Ex:
        //      9|9003
        //      8|9999 ==> 9004
        //--> Giảm 1 khoảng ít nhất bằng = right(n) +k sẽ tệ hơn là keep left ==> Buil right = reverse(left)
        //  + Ta có thể tăng or giảm < right(n)
        //
        //- Nếu chọn left(x) > left(n):
        //3009|8888 (n)
        //3009|9003 (n+115)
        //3010|0103 (n+10000 - 8888 + 103) = (n+1215)
        //  + Tăng 1 đơn vị thôi ==> Check lớn hơn thì stop.
        //  + Nếu tăng 1 đơn vị ở đầu ==> Số còn lớn hơn nữa (Vì nó nằm sát đầu)
        //
        //- Xét reverse(left) < right:
        //Ex:
        //7856|8312 (n)
        //7856|6587 (n-2724)
        //  + Phần diff < right
        //8849|9488 (n+10000+ right ==> Tệ)
        //+ Ta lấy x> left(n)
        //+ Ta chỉ tăng 1 đơn vị thôi:
        //7857|7587 (n+10000+8312-7587)
        //  + Giả sử ta tăng left càng nhiều ==> x*10000 càng nhiều ==> Càng tệ
        //      + Do số càng lớn.
        //+ Ta lấy x< left(n)
        //  + Ta cũng chỉ giảm 1 đơn vị thôi ==> Giảm nhiều diff cũng bị case bên trên.
        //
        //- Ta cần xét điều kiện số chẵn nữa:
        //Ex:
        //12345
        //12321 (n-24) ==> Chọn cái này
        //12421 (n+76)
        //
        //Ex:
        //12399
        //12321 (n-78)
        //12421 (n+22) ==> Chọn cái này
        //+ Ta cần xét cả điều kiện:
        //  + Số ở giữa increase/ decrease 1 unit
        //Ex:
        //9899
        //9999 (n+100)
        //9999 (n-2)
        //
        //
        //* Pattern:
        //- Increase or decrease left half 1 unit ==> Check xem là được
        //
        //- n%2==0:
        //Ex:
        //12[3]45
        //Cases:
        //12[2]21
        //12[4]21
        //- n%2==1:
        //Ex:
        //1[2]45
        //1[1]11
        //1[3]31
        //
        //- Special cases:
        //+ n=10
        //  + rs = 9 <=> 11 tương đương
        //  ==> Chọn (9 vì nhỏ hơn)
        //+ Nếu n = 11
        //  + rs= 22 or 9
        //
        //+ n = 100
        //  + n=99 / 101
        //
        //- Với pattern này ta có thể quay ra:
        //  + left half = subString(0, (n+1)/2)
        //
        //- Special cases
        //Cover case:
        //- n==100
        //  + rs = 99
        //===== CODE =====
        //cases.add((long) (Math.pow(10, len-1)-1));
        //Cover case:
        //- n==99
        //  + rs = 101
        //===== CODE =====
        //cases.add((long) (Math.pow(10, len)+1));
        //===== CODE =====
        //
        //- Giải thích:
        //- n=100
        //  + Half left = 1 ==> nếu giảm đi 1 về 0
        //      + Thực ra là cần giảm về 9 vì có số 99
        //  + Nhưng thực tế những thằng mà có (left half == 1)
        //      + Luôn là 99
        //  + Hoặc là những thằng có (left half == 1000)
        //      + Trừ đi sẽ giảm số 0 ==> result == 99..999 gì đó
        //  ==> Ta sẽ luôn lấy (10^(len-1) + 1) làm kết quả cần check
        //  + Giảm len nên sẽ lấy (len)
        //
        //- n = 88
        //  + expected rs=101
        //  + Tức là tăng số digit lên 1
        //      + Tất cả những thằng dạng này nếu có giao thì:
        //      Result luôn có thể là 100....001
        //  ==> Ta sẽ luôn lấy (10^(len) + 1) làm kết quả cần check
        //  + Tăng len nên sẽ lấy (len)
        //
        //1.1, Optimization
        //1.2, Complexity
        //+ N is length of the number
        //- Space: O(1)
        //- Time: O(n)
        //
        System.out.println(getPalindromeNumber(123, false));
        System.out.println(getPalindromeNumber(123, true));
//        String n="12345";
//        String n="1";
//        String n="10";
//        String n="11";
//        String n="100";
        String n="99";
        System.out.println(nearestPalindromic(n));
        System.out.println(nearestPalindromicRefer(n));
        //#Reference:
        //2217. Find Palindrome With Fixed Length
        //1842. Next Palindrome Using Same Digits
        //3260. Find the Largest Palindrome Divisible by K
    }
}
