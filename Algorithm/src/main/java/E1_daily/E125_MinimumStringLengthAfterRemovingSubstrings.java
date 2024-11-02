package E1_daily;

import java.util.Stack;

public class E125_MinimumStringLengthAfterRemovingSubstrings {

    public static int minLength(String s) {
        int len= s.length();
        int prevLen=-1;

        while (len!=prevLen){
//            s=s.replace("AB", "").replace("CD","");
            s=s.replaceFirst("AB|CD", "");
            prevLen=len;
            len=s.length();
        }
        return s.length();
    }

    public static int minLength1(String s) {
        int n=s.length();
        Stack<Character> chars=new Stack<>();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(!chars.isEmpty()
                    &&((chars.peek()=='A'&&c=='B')||(chars.peek()=='C'&&c=='D'))){
                chars.pop();
            }else{
                chars.add(c);
            }
        }
//        System.out.println(chars);
        return chars.size();
    }

    public static int minLength2(String s) {
        int writePtr = 0;
        char[] charArray = s.toCharArray();

        // Iterate over each character in the string
        for (int readPtr = 0; readPtr < s.length(); readPtr++) {
            // Write the current character to the current write position
            charArray[writePtr] = charArray[readPtr];

            // Check if we have a valid pattern to remove
            if (
                    writePtr > 0 &&
                            (charArray[writePtr - 1] == 'A' ||
                                    charArray[writePtr - 1] == 'C') &&
                            charArray[writePtr] == charArray[writePtr - 1] + 1
            ) {
                writePtr--;
            } else {
                writePtr++; // No match, so move the write pointer forward
            }
        }

        // The writePtr now represents the length of the remaining string
        return writePtr;
    }

    public static int minLengthRefer(String s) {
        int writePtr = 0;
        char[] charArray = s.toCharArray();

        // Iterate over each character in the string
        for (int readPtr = 0; readPtr < s.length(); readPtr++) {
            // Write the current character to the current write position
            charArray[writePtr] = charArray[readPtr];

            // Check if we have a valid pattern to remove
            if (
                    writePtr > 0 &&
                            (charArray[writePtr - 1] == 'A' ||
                                    charArray[writePtr - 1] == 'C') &&
                            charArray[writePtr] == charArray[writePtr - 1] + 1
            ) {
                writePtr--;
            } else {
                writePtr++; // No match, so move the write pointer forward
            }
        }

        // The writePtr now represents the length of the remaining string
        return writePtr;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting only of (uppercase) English letters.
        //- You can apply (some operations) to this string where, in (one operation),
        //  + you can remove (any occurrence) of (one of the substrings) "AB" or "CD" from s.
        //* Return (the minimum possible length) of (the resulting string) that you can obtain.
        //- Note that (the string) concatenates after (removing the substring) and could produce new ("AB" or "CD") substrings.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 100
        //s consists only of uppercase English letters.
        //
        //- Brainstorm
        //Example 1:
        //Input: s = "ABFCACDB"
        //Output: 2
        //Explanation: We can do the following operations:
        //- Remove the substring "ABFCACDB", so s = "FCACDB".
        //- Remove the substring "FCACDB", so s = "FCAB".
        //- Remove the substring "FCAB", so s = "FC".
        //So the resulting length of the string is 2.
        //It can be shown that it is the minimum length that we can obtain.
        //- Có trưởng hợp nào remove substring:
        //  + Cho kết quả khác so với việc remove lần lượt không?
        //Ex:
        //AXBCDE
        //==> Ta thấy việc remove AB or CD không liên quan đến nhau
        //
        //- Đoạn này đơn giản là replace đến khi len nó không thay đổi là được
        //
        //1.1, Optimization
        //- Chỉ có thể optimize đoạn replace thôi
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //2. Stack
        //2.0,
        //- Brainstorm
        //Ex:
        //AAABBB
        //- Ở đây replace AB đi thì vẫn còn cover AB ở ngoài
        //  ==> Ta chỉ cần làm stack A gặp B là pop là được.
        //* KINH NGHIỆM:
        //- Pattern kiểu nối ABBA
        //  + Sẽ dùng stack
        //
        //2.1, Optimization
        //
        //3. Ta có thể dùng array là được
        //3.0,
        //- Brainstorm
        //- Cùng idea với stack nhưng k dùng stack:
        //  + Modify chính input array là được.
        //
        //3.1, Optimization
        //
        String s = "ABFCACDB";
//        String s = "ACBBD";
        System.out.println(minLength(s));
        System.out.println(minLength1(s));
        System.out.println(minLength2(s));
        System.out.println(minLengthRefer(s));
        //
        //
        //#Reference:
        //3042. Count Prefix and Suffix Pairs I
        //2409. Count Days Spent Together
        //2468. Split Message Based on Limit
        //3125. Maximum Number That Makes Result of Bitwise AND Zero
        //2802. Find The K-th Lucky Number
        //1260. Shift 2D Grid
    }
}
