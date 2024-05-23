package E1_leetcode_medium_dynamic;

import java.util.HashSet;

public class E151_MinimumAdditionsToMakeValidString {

    public static int addMinimum(String word) {
        HashSet<Character> setChar=new HashSet<>();
        setChar.add('a');
        setChar.add('b');
        setChar.add('c');
        int n=word.length();
        int rs=0;

        for(int i=0;i<n;){
            int curRemainingChar=0;
            char curChar=word.charAt(i);

            if(!setChar.contains(curChar)){
                rs+=3;
                i++;
                continue;
            }
            while(i<n&&setChar.contains(curChar)){
                if(word.charAt(i)==curChar){
                    i++;
                    curRemainingChar++;
                }
                curChar++;
            }
            curRemainingChar=3-curRemainingChar;
            rs+=curRemainingChar;
        }
        return rs;
    }

    public static int addMinimumOptimization(String word) {
        int index = 0, count = 0;
        char nextChar = 'a';
        while(index < word.length()) {
            if(word.charAt(index) != nextChar)  count++;
            else index++;
            nextChar = (char) ('a' + ((nextChar + 1)  - 'a')%3);
        }
        if(nextChar == 'b')   count += 2;
        else if(nextChar == 'c')    count += 1;
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string word to which you can insert letters ("a", "b" or "c") anywhere and (any number of times),
        //* Return (the minimum number of letters) that (must be inserted) so that word becomes (valid).
        //- A string is called (valid) if it can be formed by concatenating (the string "abc" several times).
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= word.length <= 50
        //word consists of letters "a", "b" and "c" only.
        //
        //- Brainstorm
        //Example 2:
        //
        //Input: word = "aaa"
        //Output: 6
        //Explanation: Insert letters "b" and "c" next to each "a" to obtain the valid string "abcabcabc".
        //- Nó có thể có cases:
        //  + abba
        //
//        String word = "aaa";
//        String word = "abc";
//        String word = "b";
//        String word = "bc";
//        String word = "c";
        String word = "aaaaac";
        //rs=12
        //Expected rs = 9
        //- Bị case các chữ số nó không liên tiếp nhau
        //- aa => 4
        //- ab => 1
        //- ac => 1
        //  + Cái này loop 2 pointers/ có thể loop bằng set (char++ lên)
        //
        //1.1, Optimization
        //- Ta có thể dùng công thức math để đỡ loop hơn:
        //  - aa => 4
        //  - ab => 1
        //  - ac => 1
        //* CT:
        //- nextChar = (char) ('a' + ((nextChar + 1)  - 'a')%3);
        //  + Vẫn là loop hết các character ('a','b','c') ==> Tăng i nếu == thôi.
        //- Đến cuối --> Xét 1 case riêng.
        //
        //1.2, Complexity
        //- Space : O(3)
        //- Time : O(n*3)
        //
        //#Reference:
        //1698. Number of Distinct Substrings in a String
        //2767. Partition String Into Minimum Beautiful Substrings
        //3119. Maximum Number of Potholes That Can Be Fixed
        //
        System.out.println(addMinimum(word));
        System.out.println(addMinimumOptimization(word));
        //
    }
}
