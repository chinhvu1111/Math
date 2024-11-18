package E1_daily;

public class E148_MinimumNumberOfChangesToMakeBinaryStringBeautiful {

    public static int minChanges(String s) {
        //Space: O(1)
        int n = s.length();
        int count=1;
        char prevChar = s.charAt(0);
        int rs=0;

        //Time: O(n)
        for(int i=1;i<n;i++){
            if(count!=0){
                if(prevChar!=s.charAt(i)){
                    count=0;
                    rs++;
                }else{
                    count++;
                }
            }else{
                count++;
            }
            if(count%2==0){
                count=0;
            }
            prevChar=s.charAt(i);
        }
        return rs;
    }

    public static int minChangesRefactor(String s) {
        //Space: O(1)
        int n = s.length();
        int rs=0;

        //Time: O(n)
        for(int i=1;i<n;i+=2){
            if(s.charAt(i)!=s.charAt(i-1)){
                rs++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given (a 0-indexed binary string s) having (an even length).
        //- A string is (beautiful) if it's possible to (partition) it into (one or more substrings) such that:
        //  + (Each substring) has (an even length).
        //  + (Each substring) contains (only 1's or only 0's).
        //- You can change (any character in s) to (0 or 1).
        //* Return (the minimum number of changes) required to make (the string s) beautiful.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //2 <= s.length <= 10^5
        //s has an even length.
        //s[i] is either '0' or '1'.
        //  + n<=10^5: Time: O(n)
        //
        //- Brainstorm
        //- Change any bit to (0 or 1) such that we can split the string into (one or more substrings):
        //Ex:
        //s = 1001
        //- We don't need to know (how to split) the string into (one or more) substrings
        //  + We just need to find min (the number of characters) to flip
        //- The main problem is the number characters of each substring is (even):
        //- The number of character is even
        //Ex:
        //10101100
        //->
        //11111100: change number = 2
        //->
        //11111100
        //
        //- If we get the substring with even 1 or 0
        //  + We can (cut) this string (out of the main string)
        //Ex:
        //s = 10
        //  + s = 1[1]
        //  + s = [0]0
        //s = 10|10
        //  + s = 11|10
        //  + s = 00|10
        //  ==> We continue to flip bit behind in the same pattern
        //s = 1000
        //  + s = 11|00
        //  + s = 00|00
        //==> We can flip the string without caring about the bits behind.
        //- Because of that, we just need to flip the bit in sequence
        //- Count the number of the bit 1 or 0 in the previous turn:
        //Ex:
        //s = 1001
        //- count(1) = 1
        //  + We get 0:
        //      + If count(1)%2 ==1:
        //          flip the current bit 0 -> 1
        //  ==> reset count(1) = 0
        //- In the same pattern count(0) = x
        //==> We can use the same count for both of 1 and 0
        //
        //- If we got the count == 2:
        //  + We can restart count = 0 because we don't need to care the previous bits (The number of the previous bits is even number).
        //
        //1.1, Optimization
        //Ex:
        //s = 1001
        //  + 1!=0: changeNum++
        //      + 1001 -> 1101 => 01
        //  ==> Skip this substring
        //  + index+=2 because we only check the 3rd bit
        //
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
//        String s = "1001";
//        String s = "10";
//        String s = "000000";
        String s = "11000111";
        System.out.println(minChanges(s));
        System.out.println(minChangesRefactor(s));
        //
        //#Reference:
        //2842. Count K-Subsequences of a String With Maximum Beauty
        //2222. Number of Ways to Select Buildings
        //1985. Find the Kth Largest Integer in the Array
    }
}
