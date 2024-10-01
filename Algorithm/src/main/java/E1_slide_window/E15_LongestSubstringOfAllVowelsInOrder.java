package E1_slide_window;

import java.util.Arrays;

public class E15_LongestSubstringOfAllVowelsInOrder {

    public static int longestBeautifulSubstring(String word) {
        String vowels = "aeiou";
        int n=word.length();
        int left=0,right=0;
        int[] countVowels=new int[5];
        char prevChar= (char) -1;
        int rs=0;

        while (right<n){
            char c=word.charAt(right);
            int index=vowels.indexOf(c);
            if(prevChar>c){
                while (left<right){
                    int tempIndex = vowels.indexOf(word.charAt(left));
                    if(tempIndex!=-1){
                        countVowels[tempIndex]--;
                    }
                    left++;
                }
                if(index!=-1){
                    countVowels[index]++;
                }
                right++;
                prevChar=c;
                continue;
            }
            prevChar=c;
            if(index==-1){
                right++;
                continue;
            }
            countVowels[index]++;
            boolean isValid = true;
            for(int i=0;i<5;i++){
                if(countVowels[i]==0){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                rs=Math.max(rs, right-left+1);
            }
            right++;
        }
        return rs;
    }

    public static int longestBeautifulSubstringOptimization1(String word) {
        String vowels = "aeiou";
        int n=word.length();
        int left=0,right=0;
        int[] countVowels=new int[5];
        char prevChar = (char) -1;
        int rs=0;

        while (right<n){
            char c=word.charAt(right);
            int index=vowels.indexOf(c);
            if(prevChar>c){
                while (left<right){
                    int tempIndex = vowels.indexOf(word.charAt(left));
                    if(tempIndex!=-1){
                        countVowels[tempIndex]--;
                    }
                    left++;
                }
            }
            countVowels[index]++;
            boolean isValid = true;
            for(int i=0;i<5;i++){
                if(countVowels[i]==0){
                    isValid=false;
                    break;
                }
            }
            if(isValid){
                rs=Math.max(rs, right-left+1);
            }
            prevChar=c;
            right++;
        }
        return rs;
    }

    public static int longestBeautifulSubstringOptimization(String word) {
        int n=word.length();
        int rs=1;
        int count=1;
        int len=0;

        for(int i=1;i<n;i++){
            if(word.charAt(i)==word.charAt(i-1)){
                len++;
            }else if(word.charAt(i)<word.charAt(i-1)){
                count=1;
                len=1;
            }else{
                count++;
                len++;
            }
            if(count==5){
                rs=Math.max(rs, len);
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- A string is considered beautiful if it satisfies the following conditions:
        //  + Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear (at least once) in it.
        //  + The letters must be sorted in (alphabetical order) (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
        //- For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.
        //- Given a string word consisting of English vowels,
        //* return (the length of the longest beautiful substring) of word.
        //  + If no such substring exists, return 0.
        //- A substring is a contiguous sequence of characters in a string.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word.length <= 5 * 10^5
        //word consists of characters 'a', 'e', 'i', 'o', and 'u'.
        //  + Length khá lớn: Time: O(n)
        //
        //- Brainstorm
        //Example 1:
        //Input: word = "aeiaaioaaaaeiiiiouuuooaauuaeiu"
        //Output: 13
        //Explanation: The longest beautiful substring in word is "aaaaeiiiiouuu" of length 13.
        //
        //- Các vowels xuất hiện ít nhất 1 lần:
        //- Count thôi
        //- Nếu không đủ vowels:
        //  + right++
        //- Nếu gặp 1 character giảm dần:
        //  Ex:
        //  ab(ca)
        //      + left = right luôn
        //      + right++
        //- Đủ rồi:
        //  + right++ đển hết.
        //
        //1.1, Optimization
        //- Code chưa optimization
        //  + Structure của code bad:
        //      + Duplication code nhiều.
        //- Ta thấy rằng chuỗi tăng dần:
        //  + a<e<i<o<u: cũng tăng dần
        //* Ta thiếu mất condition:
        //  ==> word consists of characters 'a', 'e', 'i', 'o', and 'u'.
        //- Không cần care consonants
        //  ==> Chỉ cần (size tăng dần) == 5 là được
        //- Tạo 2 vars:
        //  + count: lưu số char distinct
        //      + c[i-1]<c[i]: count++, len++
        //  + len: lưu size window
        //      + c[i-1]==c[i]: len++
        //  + Cần init (count, len) =1:
        //      + Vì kiểu gì cũng có 1 vowel
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        //#Reference:
        //2062. Count Vowel Substrings of a String
        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        System.out.println(longestBeautifulSubstring(word));
        System.out.println(longestBeautifulSubstringOptimization(word));
    }
}
