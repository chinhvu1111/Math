package E1_slide_window;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class E21_CountCompleteSubstrings_split_problem_classic {

    public static int countCompleteSubstringsSlow(String word, int k) {
        int n=word.length();
        List<Integer>[] prefixCount=new ArrayList[26];

        for (int i = 0; i < 26; i++) {
            prefixCount[i]=new ArrayList<>();
        }
        int prevIndex=0;
        int rs=0;

        for(int i=0;i<n;i++){
            int c = word.charAt(i)-'a';
            prefixCount[c].add(i);
            if(i!=0){
                if(Math.abs(word.charAt(i)-word.charAt(i-1))>2){
                    prevIndex=i;
                }
            }
            int minIndex=Integer.MAX_VALUE;

            for(int j=0;j<26;j++){
                if(prefixCount[j].size()<k){
                    minIndex=Integer.MAX_VALUE;
                    break;
                }
                //0,1,2
                minIndex=Math.min(prefixCount[i].get(prefixCount[j].size()-k), minIndex);
            }
            if(minIndex!=Integer.MAX_VALUE&&minIndex>=prevIndex){
//                System.out.println(i);
                rs++;
            }
//            System.out.printf("%s %s\n", prevIndex, i);
//            System.out.println(minIndex);
        }
//        for (int i = 0; i < 26; i++) {
//            System.out.println(prefixCount[i]);
//        }

        return rs;
    }

    public static boolean hasEqualFrequency(int[] count, int k){
        for(int i=0;i<26;i++){
            if(count[i]>0&&count[i]!=k){
                return false;
            }
        }
        return true;
    }

    public static boolean hasAdjacentDifferenceAtMostTwo(String word, int start, int end){
        for(int i=start+1;i<=end;i++){
            if(Math.abs(word.charAt(i)-word.charAt(i-1))>2){
                return false;
            }
        }
        return true;
    }

    public static int countCompleteSubstrings(String word, int k) {
        int n=word.length();
        HashSet<Character> uniqueChars=new HashSet<>();

        for(char c: word.toCharArray()){
            uniqueChars.add(c);
        }
        //
        int rs=0;

        for(int i=1;i<=uniqueChars.size();i++){
            int windowSize=i*k;
            int[] countChar=new int[26];
            int start=0;
            int end=start+windowSize-1;
//            System.out.println(windowSize);

            for(int j=0;j<=Math.min(end, word.length()-1);j++){
                countChar[word.charAt(j)-'a']++;
            }

            while(end<n){
                if(hasEqualFrequency(countChar, k)&&hasAdjacentDifferenceAtMostTwo(word, start, end)){
                    rs++;
                }
                countChar[word.charAt(start)-'a']--;
                start++;
                end++;
                if(end<n){
                    countChar[word.charAt(end)-'a']++;
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given (a string word) and (an integer k).
        //- A substring s of word is ("complete") if:
        //  + Each character in s occurs (exactly k times).
        //  + The difference between (two adjacent characters) is ("at most") 2.
        //  That is, for any two adjacent characters c1 and c2 in s,
        //  the absolute difference in their positions in (the alphabet) is at most 2.
        //* Return (the number of complete substrings) of word.
        //- A substring is a non-empty contiguous sequence of characters in a string.
        //
        //Example 1:
        //
        //Input: word = "igigee", k = 2
        //Output: 3
        //Explanation: The complete substrings where each character appears exactly twice and
        // the difference between adjacent characters is at most 2 are: igigee, igigee, igigee.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= word.length <= 10^5
        //word consists only of lowercase English letters.
        //1 <= k <= word.length
        //  + length<=10^5 => Time: O(n*k)
        //  + lowercase English letters ==> O(26)
        //
        //** Brainstorm
        //
        //Ex:
        //Input: word = "igigee", k = 2
        //- Constraint related to absolute differencet between 2 elements is at most 2
        //  + ==> We can find the substring
        //
        //- The number of (unique chars) in string is flexible
        //
        //- How to count the number of string having (exactly k times) occurrence for (each chars)?
        //Ex:
        //abbacc(index=i)
        //
        //* Main point:
        //  + We check min_exists_index(a,b,c) such that we can have substring with (exactly k appearances)
        //
        //- At (index=i):
        //  + For (char=c):
        //      + we need to find the position as (pos) such that between [pos,i] we have exactly (k characters).
        //
        //- We use (prefix sum) to solve that
        //- Nếu ta dùng list index:
        //  ==> Min thì sẽ không đúng vì không phải (each substring) cần đủ các characters.
        //  ==> Complexity sẽ thành O(n*26*26)
        //
        //* Hint:
        //- There are (at most) (26 different lengths) of the complete substrings:
        //  + k*1, k * 2, … k * 26.
        //- Since the string is composed of "only" (26 unique characters),
        //  the length of the substring should be (k times) (the number of unique) characters j.
        //
        //* NOTE:
        //- Cách phân tích thành 1->26 cases
        //==> Để có thể tách thành sub-solution để xử lý bằng slide window.
        //
        //1.2, Cases
        //
        //
        //1.3, Optimization
        //
        //1.4, Complexity
        //- Space: O(1)
        //- Time: O(26*n)
        //
        //#Reference:
        //2615. Sum of Distances
        //2767. Partition String Into Minimum Beautiful Substrings
        //1528. Shuffle String
        //
        String word = "igigee";
        int k = 2;
        //igi, ee, igigee.
//        System.out.println(countCompleteSubstringsSlow(word, k));
        System.out.println(countCompleteSubstrings(word, k));
    }
}
