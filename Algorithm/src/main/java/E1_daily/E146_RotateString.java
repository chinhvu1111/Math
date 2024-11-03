package E1_daily;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class E146_RotateString {

    public static boolean rotateStringWrong(String s, String goal) {
        int n= s.length();
        int m = goal.length();

        if(n!=m){
            return false;
        }
        HashMap<Character, Set<Character>> relation=new HashMap<>();

        for(int i=0;i<n;i++){
            Set<Character> parents = relation.getOrDefault(s.charAt(i), new HashSet<>());
            //(n-1)+1
            Character nextChar = s.charAt((i+1)%n);
            parents.add(nextChar);
            relation.put(s.charAt(i), parents);
        }
        //a,a,a,b
        //a,a,a
        for(int i=0;i<m;i++){
            Character nextChar = s.charAt((i+1)%n);
            if(!relation.containsKey(s.charAt(i))||!relation.get(s.charAt(i)).contains(nextChar)){
                return false;
            }
        }
        return true;
    }

    public static boolean rotateStringBruteForce(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        for(int i=0;i<n;i++){
            if(goal.charAt(i)==s.charAt(0)){
                String tailStr = goal.substring(i);
                String prefixStr = "";
                if(i>0){
                    prefixStr+= goal.substring(0, i);
                }
                String curStr = tailStr + prefixStr;
//                System.out.println(curStr);
                if(curStr.equals(s)){
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean rotateStringBuiltinFunction(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        String doubledString = s+s;
        return doubledString.contains(goal);
    }

    public static boolean rotateStringKMPSearch(String s, String goal) {
        int n=s.length();
        int m=goal.length();

        if(n!=m){
            return false;
        }
        String doubledString = s+s;
        return kmpSearch(s, goal);
    }

    public static boolean kmpSearch(String text, String pattern){
        //Space: O(m)
        int[] lps = computeLPS(pattern);
        int textIndex=0, patternIndex=0;
        int textLength = text.length();
        int patternLength = pattern.length();

        //Time: O(n)
        while(textIndex<textLength){
            if(text.charAt(textIndex) == pattern.charAt(patternIndex)){
                textIndex++;
                patternIndex++;
                if(patternIndex==patternLength){
                    return true;
                }
            }else if(patternIndex>0){
                patternIndex = lps[patternIndex-1];
            }else{
                textIndex++;
            }
        }
        return false;
    }

    public static int[] computeLPS(String pattern){
        int patternLength = pattern.length();
        int[] lps=new int[patternLength];
        int length=0, index=1;

        //Time: O(m)
        while (index<patternLength){
            if(pattern.charAt(index) == pattern.charAt(length)){
                length++;
                lps[index++] = length;
            }else if(length>0){
                length = lps[length-1];
            }else{
                lps[index++] =0;
            }
        }
        return lps;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given two strings (s and goal),
        //* Return true if and only if s can become goal after some number of shifts on s.
        //- A shift on s consists of moving the leftmost character of s to the rightmost position.
        //  + For example, if s = "abcde", then it will be "bcdea" (after one shift).
        //- Check xem có shift được từ s -> goal không
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length, goal.length <= 100
        //s and goal consist of lowercase English letters.
        //
        //- Brainstorm
        //- Check whether the array has a cycle or not
        //- HashMap<Character, Set<Char> parent> map
        //Ex:
        //a,a,a,b ==> Build the relation
        //a,a,a,a ==> Pass check
        //==> If we use the hashmap => WRONG
        //Ex
        //s =   "abcde"
        //goal ="cdeab"
        //or
        //goal ="eabcd"
        //
        //- We 2 two rings:
        //  + How to compare them
        //- Trie
        //- We don't use the character is node
        //  + We use the node as the non-value node
        //  ==> But we need to find the character (In goal) from the trie of the s
        //      + It is hard
        //
        //Ex
        //s =   "abcde"
        //goal ="cdeab"
        //or
        //goal ="eabcd"
        //abcde|eabcd
        //abcde|cdeab
        //
        //* Recognition:
        //- s = a|b
        //- goal = b|a
        //==> Return true
        //<> false
        //Ex
        //s =   "abcde" = "ab|cde-cde|ab"
        //goal ="cdeab"
        //
        //- Ta thấy rằng ta đi từ 2 đầu (low, high) vào:
        //"ab|cde-cde|ab"
        //  + Kết quả đối xứng ==> Nó hoàn toàn giống nhau?
        //  ==> Không đúng
        //Ex:
        //abcde|eabcd
        //abcd|e|e|abcd
        //
        //- Thay vì cố gắng so sánh bình thường giữa (goal và s) thì:
        //  + Ta sẽ tạo ra 1 chuỗi cycle lặp lại từ s
        //  Ex:
        //  s + s ==> Đủ để chứa goal
        //- Việc còn lại là ta chỉ cần check xem goal có chứa trong (s+s) không là được
        //
        //Ex
        //abcde|eabcd
        //abcde + abcde = abcdeabcde
        //- Còn lại sẽ là (search string) algorithm:
        //
        //* KMP (Search string in string):
        //(len)
        //  a, a,c,a,a,a,a,c
        //  0, 1,2,3,4,5,6,7
        //
        //* INIT:
        //+ len=0
        //+ index=1
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,
        //
        //+ len=0
        //+ index=1
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1
        //  + P[index] == P[len] , so len = len + 1 and P[index] = len and index = index + 1
        //  + len=1
        //  + index=2
        //
        //+ len=1
        //+ index=2
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0
        //  + P[index] != P[len] , so len = P[len-1] = 0
        //  + len=0
        //  + index=2
        //
        //+ len=0
        //+ index=2
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0,1
        //  + P[index] != P[len] and len = 0 so, P[index] = 0 and index = index + 1
        //  + len=0
        //  + index=3
        //
        //+ len=0
        //+ index=3
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0,1
        //  + P[index] == P[len] , so len = len + 1 and P[index] = len and index = index + 1
        //  + len=1
        //  + index=4
        //
        //+ len=1
        //+ index=4
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0,1,2
        //  + P[index] != P[len] , so len = P[len-1] = 0
        //  + len=1
        //  + index=4
        //
        //+ len=1
        //+ index=4
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0,1,2
        //  + P[index] == P[len] , so len = len + 1 and P[index] = len and index = index + 1
        //  + len=2
        //  + index=5
        //
        //+ len=2
        //+ index=5
        //a,a,c,a,a,a,a,c
        //0,1,2,3,4,5,6,7
        //0,1,0,1,2
        //  + P[index] != P[len] , so len = P[len-1] = 1
        //  + len=1
        //  + index=5
        //
        //....
        //a,a,c,a,a,a,a,c
        //0,1,0,1,2,2,2,3
        //- LPS[4] would store the length of (the longest proper prefix) which (same)
        // as (the proper suffix) of the string formed using the first 5 characters (“ a a c a a ”) of the pattern
        // i.e from index 0 to index 4.
        //- Tức LPS[4] chứa độ dài dài nhất mà (suffix string end at 4th) == (prefix string with same length)
        //- Mục đích của việc tìm ntn để việc:
        //  + Scan repeate cả 2 string khi compare sẽ không còn nữa
        //Ex:
        // test:    ---abcxxxab------
        // pattern:    abcxxxabcy
        //             ==========
        //              len=9
        //- When comparing (c<>-)
        //  + Although, abcxxxab is match
        //  ==> But we cannot continue to compare
        //- Restart:
        //  - Bình thường ta sẽ cần match (bcxxxab) (bỏ a) với pattern:
        //  Nhưng ở đây ta cần match (ab)+(-) vì ab ta đang match dở r:
        //  + ab có trong pattern [c]==3:
        //      + Shift: 6-3 ==> Ta sẽ match được (ab)
        //Ex:
        // test:    ---abcxxxab------
        // pattern:          abcxxxabcy
        //- Tại sao không match ntn:
        // test:    ---abcxxxab------
        // pattern:      abcxxxabcy
        //  ==> Đơn giản vì ta "match theo suffix":
        //  * SUFFIX của string sẽ nằm đâu đó trong pattern:
        //  ==> Vì ta đang đi dần dần:
        //      + Shift by : len - prefixLen[len]
        //  ==> nên đại loại nó sẽ cho ta kết quả (decrease index) của (ab)
        //
        //Ex:
        // test:    ---abcxxxab------
        // pattern:            abcxxxabcy
        //- Tiếp tục:
        //  + Shift by : len - prefixLen[len]
        //  = 3 - 0 = 3
        //* Idea:
        //- Text compare to pattern theo prefix
        //- Not match:
        //  + Shift by using len - (suffix precompute number)
        //- Match:
        //  + Continue to doing that
        //https://www.youtube.com/watch?v=EL4ZbRF587g
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //#Reference:
        //1830. Minimum Number of Operations to Make String Sorted
        //2864. Maximum Odd Binary Number
        //425. Word Squares
        //1156. Swap For Longest Repeated Character Substring
        //1796. Second Largest Digit in a String
        //3138. Minimum Length of Anagram Concatenation
        //
        String s = "abcde", goal = "cdeab";
        System.out.println(rotateStringBruteForce(s, goal));
        System.out.println(rotateStringBuiltinFunction(s, goal));
        System.out.println(rotateStringKMPSearch(s, goal));
    }
}
