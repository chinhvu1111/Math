package contest;

import java.util.*;

public class E177_FindTheCountOfGoodIntegers {

    public static HashSet<String> distinctStr;
    public static int countGoodIntegers(int n, int k) {
        distinctStr=new HashSet<>();
        int start = (int) Math.pow(10, n - 1);
        int end = (int) Math.pow(10, n) - 1;
        int count = 0;

        for (int i = start; i <= end; i++) {
            if (isGoodInteger(i, k)) {
                count++;
            }
        }
        System.out.println(distinctStr.size());
        System.out.println(distinctStr);

        return count;
    }

    private static boolean isGoodInteger(int num, int k) {
        char[] digits = String.valueOf(num).toCharArray();
        Arrays.sort(digits);

        // Generate permutations and check for k-palindromic
        do {
            if (isKPalindromic(digits, k)) {
                String curStr = String.valueOf(digits);
//                System.out.println(String.valueOf(digits));
                distinctStr.add(curStr);
                return true;
            }
        } while (nextPermutation(digits));

        return false;
    }

    private static boolean isKPalindromic(char[] digits, int k) {
        if (digits[0] == '0') {
            return false; // No leading zeros
        }

        String str = new String(digits);
        long num = Long.parseLong(str);

        return isPalindrome(str) && num % k == 0;
    }

    private static boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    private static boolean nextPermutation(char[] array) {
        int i = array.length - 2;
        while (i >= 0 && array[i] >= array[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = array.length - 1;
        while (array[j] <= array[i]) {
            j--;
        }

        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        reverse(array, i + 1, array.length - 1);

        return true;
    }

    private static void reverse(char[] array, int start, int end) {
        while (start < end) {
            char temp = array[start];
            array[start] = array[end];
            array[end] = temp;
            start++;
            end--;
        }
    }

    public static String getValueInRange(int left, int right){
        StringBuilder rs=new StringBuilder();
        for(int i=left;i<=right;i++){
            rs.append(i);
        }
        return rs.toString();
    }

    public static int countGoodIntegers1(int n, int k) {
        if (n == 1) {
            return countSingleDigitPalindromes(k);
        }

        // Calculate the number of digits needed for half the palindrome
        int halfLength = n / 2;
        long start = (long) Math.pow(10, halfLength - 1);
        long end = (long) (Math.pow(10, halfLength) - 1);
        int count = 0;
        HashSet<String> checkedStr=new HashSet<>();
        HashSet<String> distinctStr1=new HashSet<>();
        HashSet<String> distinctStrValid=new HashSet<>();
        int countStr=0;
//        long halfNumber= (long) Math.pow(10, (n+1)/2);

        for (long i = start; i <= end; i++) {
//            String half = String.valueOf(i/halfNumber);
            String half = String.valueOf(i);
            String distinctVal =new String(half);
//            char[] halfChar = half.toCharArray();
            //Ignore the duplicated cases
//            Arrays.sort(halfChar);
//            String sortChar =new String(halfChar);

            //Nếu thêm ở đây thì sẽ bị sai ==> Hiểu lầm
            //==> Check ở đây sẽ bị thiếu case
//            if(checkedStr.contains(sortChar)){
//                continue;
//            }
//            checkedStr.add(sortChar);
            //getValueInRange(0, (len+1)/2-1, n);
//            String fullPalindrome = buildPalindrome(half, n % 2 == 1);
            List<String> curBuildStr=new ArrayList<>();
            if(n % 2 == 1){
                for(int d=0;d<=9;d++){
                    curBuildStr.add(buildPalindrome(half, n % 2 == 1, d+""));
                }
            }else{
                curBuildStr.add(buildPalindrome(half, n % 2 == 1, ""));
            }
//            boolean curHalfIsValid = false;
//            int midDigit=0;
            for(String fullPalindrome: curBuildStr){
                countStr++;
                distinctStr1.add(fullPalindrome);
                if (isDivisible(fullPalindrome, k)) {
//                    if(n%2==1){
//                        char[] halfChar = distinctVal.toCharArray();
//                        Arrays.sort(halfChar);
//                        String halfStr = String.valueOf(halfChar)+midDigit;
//                        if(checkedStr.contains(halfStr)){
//                            continue;
//                        }
//                        checkedStr.add(halfStr);
//                    }else{
//                        char[] halfChar = half.toCharArray();
//                        Arrays.sort(halfChar);
//                        String halfStr = String.valueOf(halfChar);
//                        if(checkedStr.contains(halfStr)){
//                            continue;
//                        }
//                        checkedStr.add(halfStr);
//                    }
//                    if(checkedStr.contains(fullPalindrome)){
//                        continue;
//                    }
//                    checkedStr.add(fullPalindrome);
                    char[] fullChars = fullPalindrome.toCharArray();
                    Arrays.sort(fullChars);
                    String fullStr = String.valueOf(fullChars);
                    if(checkedStr.contains(fullStr)){
                        continue;
                    }
                    checkedStr.add(fullStr);
//                    curHalfIsValid=true;
                    distinctStrValid.add(fullPalindrome);
                    long x = countDistinctPermutations(fullPalindrome);
//                    System.out.println(x);
                    count += x;
                }
//                midDigit++;
            }
//            if(!curHalfIsValid){
//                continue;
//            }
            //Ignore the duplicated cases
//            checkedStr.add(sortChar);
        }
//        System.out.println();
//        System.out.println(countStr);
//        System.out.println(checkedStr.size());
//        System.out.println("======================== In distinctStr and not in distinctStrValid");
//        for (String s: distinctStr){
//            if(!distinctStrValid.contains(s)){
//                System.out.println(s);
//            }
//        }
//        System.out.println("======================== In distinctStrValid and not in distinctStr");
//        for (String s: distinctStrValid){
//            if(!distinctStr.contains(s)){
//                System.out.println(s);
//            }
//        }
//        System.out.println("========================");

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

    private static String buildPalindrome(String half, boolean isOddLength, String mid) {
        StringBuilder sb = new StringBuilder(half);
        if (isOddLength) {
            sb.append(mid);  // Placeholder for middle digit
        }
        sb.append(new StringBuilder(half).reverse());
        return sb.toString();
    }

    private static boolean isDivisible(String number, int k) {
        long num = Long.parseLong(number);
        return num % k == 0;
    }

    private static long countPermutations(String half) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : half.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        long permCount = factorial(half.length());
        for (int freq : freqMap.values()) {
            permCount /= factorial(freq);
        }

        return permCount;
    }

    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

//    public static long countGoodIntegers(int n, int k) {
//        String leftPossibleStr="";
//        return 0L;
//    }

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
        for (int i = 0; i < length; i++)
            if (str.charAt(i) >= '0')
                freq[str.charAt(i) - '0']++;

        // finding factorial of number of appearances
        // and multiplying them since they are
        // repeating alphabets
        long fact = 1;
        long factFirstZero = 1;

        for (int i = 0; i < MAX_CHAR; i++)
            fact = fact * factorial1(freq[i]);
        for (int i = 0; i < MAX_CHAR; i++)
            if(i==0){
                factFirstZero = factFirstZero * factorial1(freq[i]-1);
            }else{
                factFirstZero = factFirstZero * factorial1(freq[i]);
            }
        int countZero = freq[0];
        long zeroCase = 0;
        if(countZero>=1){
            zeroCase = factorial1(length-1) / factFirstZero;
        }

        // finding factorial of size of string and
        // dividing it by factorial found after
        // multiplying
        return factorial1(length) / fact - zeroCase;
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
        //
        //** Solution:
        //- Sort toàn bộ dupplicated full string
        //  + Nhưng + sort
        //
        //1.1, Optimization
        //- Với case n lẻ
        //  + Ta có thể giảm thời gian sort của full string bằng cách sort từ trước
        //  + Sau đó đổi char ở middle là được.
        //1.2, Complexity
        //
        System.out.println(countGoodIntegers(n, k));
        System.out.println(countGoodIntegers1(n, k));
        //#Reference:
        //2717. Semi-Ordered Permutation
        //1662. Check If Two String Arrays are Equivalent
        //1032. Stream of Characters
    }
}
