package contest;

import java.util.Arrays;

public class E189_CountSubstringsThatCanBeRearrangedToContainAStringI {

    public static long validSubstringCount(String word1, String word2) {
        int n=word1.length();
        int[] count2=new int[26];
        int[] count1=new int[26];
        for(char c: word2.toCharArray()){
            count2[c-'a']++;
        }
        long rs=0;
        int left=0;
        int right=0;
        //Time: O(n*26)
        while (left<n){
            boolean isInvalid=false;
            for(int j=0;j<26;j++){
                if(count1[j]<count2[j]){
                    isInvalid=true;
                    break;
                }
            }
            if(!isInvalid){
                rs+=n-right+1;
//                System.out.printf("left: %s, right:%s, val:%s\n", left, right, n-right);
                count1[word1.charAt(left)-'a']--;
                left++;
            }else if(right<n){
                count1[word1.charAt(right)-'a']++;
                right++;
            }else{
                left++;
            }
        }
//        boolean isInvalid=false;
//        for(int j=0;j<26;j++){
//            if(count1[j]<count2[j]){
//                isInvalid=true;
//                break;
//            }
//        }
//        if(!isInvalid){
//            rs+=n-right+1;
//        }
        return rs;
    }

    public static long validSubstringCountRefer(String source, String target) {
        int[] targetFreq = new int[26];
        for (int i = 0; i < target.length(); i++) {
            char c = target.charAt(i);
            targetFreq[c - 'a']++;
        }

        int start = 0;
        int[] currentFreq = new int[26];
        long validSubstrings = 0;
        for (int end = 0; end < source.length(); end++) {

            //Đoạn này là add new character
            //end++
            int charIndex = source.charAt(end) - 'a';
            currentFreq[charIndex]++;

            boolean isValid = true;
            for (int j = 0; j < 26; j++) {
                if (targetFreq[j] > currentFreq[j]) {
                    isValid = false;
                    break;
                }
            }

            //new start:
            //  + start++
            //  Với mỗi start ta có thể add thêm (n-end) string đằng sau
            //==> Nó y hệt idea việc mình cần tìm substring tiệm cận có (count == count_target)
            //      + Nếu vẫn thoả mãn: start++
            while (isValid) {
                validSubstrings += (long) (source.length() - end);
                charIndex = source.charAt(start) - 'a';
                currentFreq[charIndex]--;
                start++;

                isValid = true;
                for (int j = 0; j < 26; j++) {
                    if (targetFreq[j] > currentFreq[j]) {
                        isValid = false;
                        break;
                    }
                }
            }
        }
        return validSubstrings;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (two strings) word1 and word2.
        //- A string x is called valid if x can be rearranged to have (word2) as (a prefix)
        //* Return (the total number of ("valid substrings")) of word1.
        //- Tức là all substring của word1:
        //  + Có thể rearranged được mà có (prefix == word2)
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word1.length <= 10^5
        //1 <= word2.length <= 10^4
        //word1 and word2 consist only of lowercase English letters.
        //  + The length of Word1 is quite big ==> Time: O(n)
        //  + The length of Word1 is less than the length of Word2:
        //      + We should use the word2 instead of word1
        //
        //** Brainstorm
        //- Sắp xếp để thành prefix:
        //  + i in subtring of word1
        //  + j in subtring of word1
        //  + substring có all of indexes:
        //      + count[i]>=count[j] là được.
        //- Substring tức là:
        //  + Limit bởi (i,j)
        //  + Tức là tìm count của (1 list characters) trong khoảng (i,j) có khả thi không?
        //      + Nếu sort word1 --> Mất yếu tố vị trí
        //- Dùng slide window để count được không
        //Ex:
        //Input: word1 = "abcabc", word2 = "abc"
        //- Ta sẽ dùng prefix chia trường hợp để count:
        //* CT:
        //  + rs = prefix[0] + prefix[1] + ... + prefix[n-1]
        //(a,b,c)...
        //  + Nếu đi từ đây về sau ==> lấy được hết
        //  rs = 6-3+1 = 4
        //(b,c): không đủ
        //(b,c,a): đủ
        //  + rs+=3 = 7
        //- Remove b
        //(c,a): không đủ
        //- add b
        //+ (c,a,b)
        //  + rs+= 2 = 9
        //+ Nốt (a,b,c):
        //  + rs+= 1 = 10
        //
        //
//        String word1 = "abcabc", word2 = "abc";
//        String word1 = "abcabc", word2 = "aaabc";
//        String word1 = "bbbb", word2 = "b";
//        String word1 = "dcbdcdccb", word2 = "cdd";
        String word1 = "ddccdddccdddccccdddccdcdcd", word2 = "ddc";
        //(dcbd)cdccb
        //d(cbd)cdccb
        //  + Thiếu case: (left->i)
        //      + Lúc nào cũng add i thành ra có thể left++ ==> vẫn có case j<i thoả mãn
        //      ==> Thiều case
        //  + Nên ta chỉ được phép tăng i (i++)
        //      + Nếu thiếu mà thôi
        //* Bản chất là không được cộng right khi không cần thiết
        //  + Chỉ right++ cho "VỪA ĐỦ" ==> Nếu left++ sẽ false ngay?
        //
        //** Slice window:
        //  - Kinh nghiệm chỉ tăng right ==> Chạm biên
        //      + Đã xét xong the all of result corresponding to the current case.
        //
        //1.1, Optimization
        //- Idea của reference:
        //new start:
        //  + start++
        //  Với mỗi start ta có thể add thêm (n-end) string đằng sau
        //==> Nó y hệt idea việc mình cần tìm substring tiệm cận có (count == count_target)
        //      + Nếu vẫn thoả mãn: start++
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n*26)
        //
        //#Reference:
        //1153. String Transforms Into Another String
        //2713. Maximum Strictly Increasing Cells in a Matrix
        //2342. Max Sum of a Pair With Equal Sum of Digits
        //
        System.out.println(validSubstringCount(word1, word2));
        System.out.println(validSubstringCountRefer(word1, word2));
    }
}
