package contest;

import java.util.*;

public class E177_FindTheCountOfGoodIntegers_optimizationc_division_classic {

    public static int countGoodIntegers(int n, int k) {
        if (n == 1) {
            return countSingleDigitPalindromes(k);
        }
        // Calculate the number of digits needed for half the palindrome
        int halfLength = n / 2;
        //Time: O(n)
        long start = (long) Math.pow(10, halfLength - 1);
        long end = (long) (Math.pow(10, halfLength) - 1);
        int count = 0;
        //Space: O(10^(n/2) - 10^(n/2-1))
        HashSet<String> checkedStr=new HashSet<>();

        //Time: O(10^(n/2) - 10^(n/2-1))
        for (long i = start; i <= end; i++) {
//            String half = String.valueOf(i/halfNumber);
            //Time: O(n)
            String half = String.valueOf(i);
            String curStr = "";

            //Time: O(n)
            curStr = buildPalindrome(half, n%2==1);
            char[] s=curStr.toCharArray();
            int numDigit = (n%2==1)?9:0;

            //Time: O(10)
            for(int j=0;j<=numDigit;j++){
                if(n%2==1){
                    s[s.length/2] = (char)(j+'0');
                }
                String fullPalindrome = String.valueOf(s);
                //Time: O(n)
                if (isDivisible(fullPalindrome, k)) {
                    char[] fullChars = fullPalindrome.toCharArray();
                    //Time: O(n*log(n))
                    Arrays.sort(fullChars);
                    String fullStr = String.valueOf(fullChars);
                    if(checkedStr.contains(fullStr)){
                        continue;
                    }
                    checkedStr.add(fullStr);
                    //Time: O(n*10)
                    long x = countDistinctPermutations(fullPalindrome);
                    count += x;
                }
            }
        }

        return count;
    }

    private static int countSingleDigitPalindromes(int k) {
        int count = 0;
        for (int i = 1; i <= 9; i++) {
            if (i % k == 0) {
                count++;
            }
        }
        return count;
    }

    private static String buildPalindrome(String half, boolean isOdd) {
        if(isOdd){
            return half + "0" +
                    new StringBuilder(half).reverse();
        }else{
            return half +
                    new StringBuilder(half).reverse();
        }
    }

    private static boolean isDivisible(String number, int k) {
        long num = Long.parseLong(number);
        return num % k == 0;
    }

    static final int MAX_CHAR = 10;

    // Utility function to find factorial of n.
    static long factorial1(int n)
    {
        long fact = 1;
        for (int i = 2; i <= n; i++)
            fact = fact * i;
        return fact;
    }

    // Returns count of distinct permutations
    // of str.
    static long countDistinctPermutations(String str)
    {
        int length = str.length();
        int[] freq = new int[MAX_CHAR];

        // finding frequency of all the lower case
        // alphabet and storing them in array of
        // integer
        //Time: O(n)
        for (int i = 0; i < length; i++){
            freq[str.charAt(i) - '0']++;
        }

        // finding factorial of number of appearances
        // and multiplying them since they are
        // repeating alphabets
        long fact = 1;
        long factFirstZero = 1;

        //Time: O(10)
        for (int i = 0; i < MAX_CHAR; i++){
            //Time: O(n)
            fact = fact * factorial1(freq[i]);
            if(i==0){
                factFirstZero = factFirstZero * factorial1(freq[i]-1);
            }else{
                factFirstZero = factFirstZero * factorial1(freq[i]);
            }
        }
        int countZero = freq[0];
        long zeroCase = 0;
        long factN = factorial1(length);
        if(countZero>=1){
            zeroCase = factN / length / factFirstZero;
        }

        // finding factorial of size of string and
        // dividing it by factorial found after
        // multiplying
        return factN / fact - zeroCase;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given two positive integers n and k.
        //- An integer x is called k-palindromic if:
        //  + x is a palindrome.
        //  + x is divisible by k.
        //- An integer is called (good) if (its digits) can be rearranged to form (a k-palindromic integer).
        //- For example, for k = 2, 2020 can be rearranged to form (the k-palindromic integer 2002),
        // whereas 1010 cannot be rearranged to form (a k-palindromic integer).
        //* Return the (count of good integers) containing (n digits).
        //- Note that any integer (must not) have leading zeros,
        // neither (before) nor (after) rearrangement.
        //+ For example, (1010) cannot be rearranged to form (101).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 10
        //1 <= k <= 9
        //  + 1_000_000_000
        //- max = 1_000_000_000 / k=1
        //  ==> 10^10 quá lớn.
        //  + Cái này return luôn all
        //- max = 1_000_000_000 / k=2
        //  = 500_000_000 ==> 10^9 vẫn quá lớn
        //
        //** Brainstorm
        //- Số chia hết cho k:
        //  + Cái này cần loop dùng kỹ thuật đếm
        //  (1*k => m*k)
        //- Check số xem có thể sắp xếp được thành palindrome hay không?
        //  + for từng số
        //      + Performance tệ
        //+ Chia hết cho k:
        //  1<=k<=9
        //+ k = [ 1,2,3,4,5,6,7,8,9 ]
        //  + 1
        //  + 2: Số cuổi là số chẵn
        //  + 3 : tổng số %3==0
        //  + 4:
        //- Liệu có thể tự build không?
        //
        //Ex:
        // num = 551
        //  + 515
        //
        //=====
        // Dấu hiệu chia hết cho 3
        //Một số chia hết cho 3, chỉ khi tổng của tất cả các chữ số của nó chia hết cho 3. Ta không cần biết nó có bao nhiêu chữ số, là số lẻ hay số chẵn, chỉ cần cộng tất cả các chữ số tạo thành số đó nếu chia hết cho 3 thì số đó chắn chắn chia hết cho 3.
        //
        //Ví dụ: Ví dụ: số 345 chia hết cho 3 vì tổng các chữ số của nó (3 + 4 + 5 = 12) chia hết cho 3.
        //
        //Số 123455 không chia hết cho 3 vì tổng 1 + 2 + 3 + 4 + 5 + 5 = 20 không chia hết cho 3.
        //
        //Dấu hiệu chia hết cho 4
        //Với trường hợp phép chia hết cho 4 ta phải xét 2 trường hợp gồm:
        //
        //Nếu số lớn hơn 99:
        //
        //Một số chia hết cho 4 khi 2 chữ số cuối của số đó là số 0 hoặc tổng 2 số cuối cùng chia hết cho 4.
        //Ví dụ: 14676 chia hết cho 4 vì 2 chữ số cuối cùng 76 tạo thành một số chia hết cho 4 (76/4 = 19). Số 345200 cũng chia hết cho 4 vì 2 chữ số cuối là số không.
        //Nếu số nhỏ hơn 99:
        //
        //Số chỉ chia hết cho 4 khi ta nhân đôi chữ số hàng chục và cộng thêm chữ số hàng đơn vị, nếu kết quả này chia hết cho 4 thì số ban đầu sẽ chia hết cho 4.
        //Ví dụ: số 64, số hàng chục ở đây là 6, chúng ta cần nhân đôi số này và cộng thêm chữ số cuối: 2 * 6 + 4 = 16, 16 chia hết cho 4 do đó 64 chia hết cho 4.
        //Hoặc số 96  = 9.2 + 6 = 24 /4 = 6 nên 96 chia hết cho 4.
        //Số 47 = 4.2 + 7 = 15 không chia hết cho 4 nên 47 không chia hết cho 4.
        //Dấu hiệu chia hết cho 5
        //Trường hợp chia hết cho 5 đơn giản hơn nhiều, điều kiện cần là chữ số cuối có giá trị bằng 0 hoặc 5 thì nó chia hết cho 5.
        //
        //Ví dụ: Số 2015 chia hết cho 5 vì chữ số cuối cùng bằng 5, hoặc số 2020 có số 0 cuối cùng nên thỏa điều kiện sẽ chia hết cho 5.
        //
        //Dấu hiệu chia hết cho 6
        //Có các quy tắc nhận biết một số có chia hết cho 6 gồm:
        //
        //Một số chia hết cho 6 khi nó chia hết cho 2 và chia hết cho 3. Ví dụ số 12 /2 = 6 và 12/3 = 4 nên 12 chia hết cho 6.
        //Nếu kết quả chữ số hàng chục nhân với 4 rồi cộng thêm chữ số hàng đơn vị của một số bất kỳ chia hết cho 6 thì số đó chia hết cho 6. Ví dụ: Số 72  = 7.4 + 2 = 28 + 2 = 30 / 6 = 5. Nên 72 chia hết cho 6.
        //Nếu tổng các chữ số là một số chẵn và tổng này chia hết cho 3 thì số đó đó chắc chắn sẽ chia hết cho 6. Ví dụ:  Số 132 có tổng các chữ số = 1 + 3 + 2 = 6 /3 = 2. Nên 132 chia hết cho 6.
        //Dấu hiệu chia hết cho 7
        //Có các dấu hiệu nhận biết một số bất kỳ có chia hết cho 7 không gồm:
        //
        //Nhân đôi chữ số cuối cùng rồi lấy các chữ số còn lại trừ cho phép nhân đó nếu kết quả chia hết cho 7 thì số đã cho sẽ chia hết cho 7. Ví dụ 784 ta thực hiện như sau: lấy số cuối cùng là 4.2  = 8, lấy 2 chữ số còn lại là 78 – 8 = 70 /7 = 10, suy ra được 784 sẽ chia hết cho 7.
        //Nếu một số có 2 chữ số và ta lấy chữ số hàng chục nhân với 3 rồi cộng với chữ số hàng đơn vị. Nếu kết quả này chia hết cho 7 thì số đó chia hết cho 7. Lưu ý rằng cách này chỉ áp dụng với số có 2 chữ số. Ví dụ số 98 ta lấy 9.3 + 8 = 27 + 8 = 35 /7 = 5. Nên 98 sẽ chia hết cho 7.
        //Dấu hiệu chia hết cho 8
        //Nếu ba chữ số cuối của một số chia hết cho 8, thì số đó chia hết cho 8. Ví dụ số 109816 có 816 /8 = 102 nên 109816 chia hết cho 8.
        //
        //Mẹo gợi ý làm nhanh: Ta lấy 3 số cuối cùng chia liên tiếp 3 lần cho 2, nếu kết quả là số nguyên thì số đó chia hết cho 8. Ví dụ số 109816 có  816/2 = 408, 408/2 = 204, 204/2 = 102.
        //
        //Dấu hiệu chia hết cho 9
        //Một số chỉ chia hết cho 9 khi tổng của tất cả các chữ số của nó chia hết cho 9, ví dụ số 12345678 chia hết cho 9 vì 1 + 2 + 3 + 4 + 5 + 6 + 7 + 8 = 36 chia hết cho 9.
        //
        //Dấu hiệu chia hết cho 10
        //Một số chỉ chia hết cho 10 khi chữ số cuối của số này là 0 (không).
        //
        //Ví dụ: Các số 100, 500, 2020, 5050 đều chia hết cho 10.
        //=========
        //- Đôi khi nếu muốn check chia hết hay không
        //  + (cast string to long) % k là được.
        //
        //- Check 1 số có thể rearrange ntn?
        //==> Check ntn thì phải scan all
        //- Build palindrome number:
        //+ N is even:
        //  + build string có length = x ==> Duplicate lên
        //+ N is odd:
        //  + build string có length = x ==> Duplicate lên + (Bất kỳ số nào ở giữa là được)
        //- len = 10/2 = 5
        //  + 9*9*9*9*9=59049 số thôi.
        //- Tìm được danh sách số palindrome:
        //  + Count số lượng number có thể rearrange thành palindrome ntn:
        //Ex:
        //515:
        //  + 155
        //  + 551
        //  + 515
        //- Số có length = n
        //  + Scan 10^(n-1)+1 ==> 10^n
        //
        //
//        int n = 3, k = 5;
//        int n = 7, k = 2;
//        int n = 5, k = 6;
        int n = 5, k = 7;
        //- Special cases:
        //- Nếu mà check duplicate của palindrome string:
        //  + Thì sẽ bị duplicated do 2 palindrome ==> Có thể overlap tập hợp permutations
        //Ex:
        //42924 ==> Có thể được sinh ra từ:
        //  + 22944...
        //24942 ==> Cũng có thể được sinh ra từ:
        //  + 22944...
        //- 2 cái kia nếu tính sum số permutation:
        //  + Sẽ thừa mất 1 lần (22944)
        //
        //- Distinct chúng đi ntn?
        //  + Nếu 2 thằng mà có chung (sorted half):
        //  ==> Ta sẽ bỏ qua không xét
        //- Thế này vẫn chưa đủ vì có những cases:
        //  + half= 23
        //      + 23432 %k == 0
        //      + 23532 %k != 0
        //      ==> Mà ta mark (23) visited
        //      + 32532 %k == 0 ==> Sẽ bị bỏ qua là sai
        //
        //* Bài toán con:
        //- Tính số distinct hoán vị của string a:
        //  + Tính frequency của mỗi char trong a
        //- rs = n! / (x!*y!*...)
        //  + x là số lần xuất hiện của char(i) trong a
        //- Giải thích công thức:
        //  + 1*2*3*4 : Số hoán vị của 4 phần tử khác nhau
        //      + index=0: Có 4 cách chọn
        //      + index=1: Có 3 cách chọn
        //      ...
        //Ex:
        // s = "aabaac
        // unique chars = ['a','b','c']
        //  + a có thể thay bằng b/c
        //  + index=0:
        //      + Có thể chọn a/b/c: 3 cách chọn
        //  + Sau khi chọn thì count của:
        //      + a/b/c sẽ giảm đi 1
        //      ==> Sau ta sẽ chỉ * (count(a)-1)/ (count(b)-1)/ (count(c)-1)
        //  + index=1:
        //      + Có thể chọn a/b/c: 3 cách chọn
        //  + rs = 3*3*3*2...*1
        //  + Có n lần chọn:
        //      + n = count(a)+ count(b) + count(c)
        //- Nếu count(a),count(b), count(c) giảm dần sau mỗi lần chọn
        //* Permute all elements, and remove permutations of elements that are identical, viz.
        //  - Tức là lấy
        //      + Tử số  = permutaion của all of elements
        //      + Mẫu số = permutation của các phần tử giống hện nhau
        //
        //========= Chứng minh
        //First consider that all the letters are distinct.
        //So 6!=720 possible permutations.
        //What's inside that 6! yo?
        //6!=6C2*2!*4C2*2!*2C2*2!
        //Let's explain it a little bit,
        //
        //6C2*2! this part counts in how many ways, 2 positions can be selected from 6 positions
        //and 2 F's can be placed there(You could place 2 E's there too).
        //Now we have 4 positions left.If we place those 2 E's there number of permutation is 4C2*2!.For T and O other 2 positions so ,2C2*2!.
        //
        //Now,Those 2! that we used for 2 F's and E's ain't right cause we have considered that all letters are distinct but they are not.
        //So out of 2! (FF and FF or EE and EE)combination only 1 combination(FF) is distinct and others are not.So instead of 2!, those counts should be 1
        //whice is,
        //
        //6C2*1*4C2*1*2C2*2!=180 and tada that is same as 6!/(2!2!). That's why we divide by factorial of repeatation. Don't wanna divide ?
        //6!-number of repeated calculations
        //=6!- 6C2(2!-1)4C2(2!-1)*2C2*2! =180 Any way you want
        //=========
        //https://math.stackexchange.com/questions/175621/distinct-permutations-of-the-word-toffee
        //
        //** Solution:
        //- Sort toàn bộ duplicated full string
        //  + Nhưng + sort
        //
        //1.1, Optimization
        //- Bỏ đoạn cộng string + mid digit mỗi lần loop:
        //  + Reduce: 660 ms => 379 ms
        //- Tối ưu cho lần tính loại bỏ startChar = '0':
        //  + Optimize fact function:
        //      + Tính factorial(n) => factorial(n-1)
        //      + factorial(n) = factorial(n-1) * n
        //
        //1.2, Complexity
        //- Space: O(10^(n/2) - 10^(n/2-1))
        //- Time: O(log(n)*n + n*10 + 10^(n/2) - 10^(n/2-1))
        //
        System.out.println(countGoodIntegers(n, k));
        //#Reference:
        //2717. Semi-Ordered Permutation
        //1662. Check If Two String Arrays are Equivalent
        //1032. Stream of Characters
        //2281. Sum of Total Strength of Wizards
        //949. Largest Time for Given Digits
        //793. Preimage Size of Factorial Zeroes Function
    }
}
