package E1_daily;

import java.util.Arrays;
import java.util.HashSet;

public class E230_ConstructKPalindromeStrings_divide_and_conquer_classic {

    public static boolean canConstruct(String s, int k) {
        HashSet<Character> sWithEvenExisting=new HashSet<>();
        int n=s.length();

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            if(sWithEvenExisting.contains(c)){
                sWithEvenExisting.remove(c);
            }else{
                sWithEvenExisting.add(c);
            }
        }
//        System.out.println(sWithEvenExisting);

        return k >= sWithEvenExisting.size()&&k<=n;
    }
    public static int[] count=new int[26];

    public static boolean canConstructOptimization(String s, int k) {
        int n=s.length();
        Arrays.fill(count, 0);

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            count[c-'a']++;
        }
//        System.out.println(sWithEvenExisting);
        int singleCharNum=0;

        for(int i=0;i<26;i++){
            if(count[i]%2==1){
                singleCharNum++;
            }
        }
        return k >= singleCharNum&&k<=n;
    }

    public static boolean canConstructBitManipulation(String s, int k) {
        int n=s.length();
        int odd=0;

        if(k>n){
            return false;
        }

        for(int i=0;i<n;i++){
            char c=s.charAt(i);
            odd^=1<<(c-'a');
        }
//        System.out.println(sWithEvenExisting);
        return Integer.bitCount(odd)<=k;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given (a string s) and (an integer k),
        //* return true if you can use (all the characters in s) to (construct k palindrome strings or false otherwise).
        //- Tức tách s thành các strings sao cho (chúng palindrome)
        //
        //Example 1:
        //
        //Input: s = "annabelle", k = 2
        //Output: true
        //Explanation: You can construct two palindromes using all characters in s.
        //Some possible constructions "anna" + "elble", "anbna" + "elle", "anellena" + "b"
        //
        //Example 3:
        //
        //Input: s = "true", k = 4
        //Output: true
        //Explanation: The only possible solution is to put each character in a separate string.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= s.length <= 10^5
        //s consists of lowercase English letters.
        //1 <= k <= 10^5
        //
        //- Brainstorm
        //Ex:
        //s = "trueu", k = 3
        //t+r+ueu
        //1+1+3
        //- String s is palindrome, the number of chars is :
        //  + even:
        //      + All of the count of chars need to even
        //  + odd:
        //      + All of the count of chars need to even except (only 1 char)
        //      Ex:
        //      dabc[d]cbad
        //      count[d]=3
        //      count[...] = 2
        //s = "trueu", k = 3
        //count[t] = 1
        //count[r] = 1
        //count[u] = 2
        //count[e] = 1
        //- Rule is related to the palindrome:
        //- From 1 palindrome
        // ==> we can create the new palindrme from that
        //Ex:
        //aba
        //==> b + aa from that
        //leetcode
        //
        //Input: s = "annabelle", k = 2
        //anna, elble
        //
        //- We can use stack to reduce the number of characters
        //- We try to reduce them:
        //  + The number of character need to stand alone
        //  + Ex:
        //      elble ==> remaining char: b
        //- For each reduction time:
        //  + a + a = 0
        //  ==> We can create new palindrome
        //
        //* Characteristic of the palindrome string:
        //- The number of palindrome >= the number of single character
        //- The number of palindrome <= the number of character in string
        //==> We use both of conditions to return result
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        //2. Bit manipulation
        //2.0,
        //- Nếu nói đến chính chất (chẵn/ lẻ)
        //  + Count the number of characters
        //==> Dùng bit manipulation
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        String s = "annabelle";
        int k = 2;
        System.out.println(canConstruct(s, k));
        System.out.println(canConstructOptimization(s, k));
        System.out.println(canConstructBitManipulation(s, k));
        //
        //#Reference:
        //2551. Put Marbles in Bags
        //2351. First Letter to Appear Twice
        //2263. Make Array Non-decreasing or Non-increasing
        //2590. Design a Todo List
        //2732. Find a Good Subset of the Matrix
        //1172. Dinner Plate Stacks
        //
        //2038. Remove Colored Pieces if Both Neighbors are the Same Color
        //244. Shortest Word Distance II
        //1674. Minimum Moves to Make Array Complementary
    }
}
