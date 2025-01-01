package E1_daily;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class E200_ConstructStringWithRepeatLimit {

    public static String repeatLimitedString(String s, int repeatLimit) {
        int n=s.length();
        int[] count=new int[26];
//        boolean[] visited=new boolean[26];
        //Update index at (i+2)
        int[] validIndex=new int[26];

        for(int i=0;i<n;i++){
            count[s.charAt(i)-'a']++;
        }
        int i;
        StringBuilder rs=new StringBuilder();
        int prevChar=-1;
        int consecutiveCount=0;

        //x(y)x
        //  + x<=y:
        //      + Traverse(y) before x:
        //      ==> No update visisted[x]
        for(i=0;i<n;i++){
            boolean isValid=false;
            for(int j=25;j>=0;j--){
                if(count[j]==0||validIndex[j]>i){
                    continue;
                }
                //p(o:cons=1)po
                //- Switch new char ==> update consecutiveCount
                if(j!=prevChar){
                    consecutiveCount=0;
                }
                prevChar=j;
                rs.append((char)(j+'a'));
                count[j]--;
                consecutiveCount++;
                if(consecutiveCount==repeatLimit||count[j]==0){
                    validIndex[j]=i+2;
                    consecutiveCount=0;
                }
                isValid=true;
                break;
            }
            if(!isValid){
                break;
            }
        }
        return rs.toString();
    }

    public static String repeatLimitedStringRefer(String s, int repeatLimit) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        StringBuilder rs=new StringBuilder();
        int currentCharIndex=25;

        while(currentCharIndex>=0){
            //if count[max]==0:
            //  + Decrease max char
            if(freq[currentCharIndex]==0){
                currentCharIndex--;
                continue;
            }
            int use= Math.min(freq[currentCharIndex], repeatLimit);
            for(int j=0;j<use;j++){
                rs.append((char) ('a'+currentCharIndex));
            }
            freq[currentCharIndex]-=use;
            if(freq[currentCharIndex]>0){
                //Choose the smaller char
                //  ==> Do everything in the same step
                //  ==> EASIER
                int smallerCharIndex = currentCharIndex - 1;
                while(smallerCharIndex>=0&&freq[smallerCharIndex]==0){
                    smallerCharIndex--;
                }
                if(smallerCharIndex<0){
                    break;
                }
                rs.append((char) (smallerCharIndex+'a'));
                freq[smallerCharIndex]--;
            }
        }
        return rs.toString();
    }

    public static String repeatLimitedStringPriorityQueue(String s, int repeatLimit) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char ch : s.toCharArray()) {
            freq.put(ch, freq.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> maxHeap = new PriorityQueue<>((a, b) ->
                Character.compare(b, a)
        );

        for (char ch : freq.keySet()) {
            maxHeap.offer(ch);
        }

        StringBuilder result = new StringBuilder();

        while (!maxHeap.isEmpty()) {
            char ch = maxHeap.poll();
            int count = freq.get(ch);

            int use = Math.min(count, repeatLimit);
            for (int i = 0; i < use; i++) {
                result.append(ch);
            }

            freq.put(ch, count - use);

            if (freq.get(ch) > 0 && !maxHeap.isEmpty()) {
                char nextCh = maxHeap.poll();
                result.append(nextCh);
                freq.put(nextCh, freq.get(nextCh) - 1);
                if (freq.get(nextCh) > 0) {
                    maxHeap.offer(nextCh);
                }
                maxHeap.offer(ch);
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a string s) and (an integer repeatLimit).
        //- Construct (a new string repeatLimitedString) using the characters of s such that
        // (no letter) appears (more than) (repeatLimit times) in a row.
        //- You do not have to use (all characters from s).
        //* Return (the lexicographically largest repeatLimitedString) possible.
        //- (A string a) is lexicographically (larger than) (a string b) if in the first position where a and b differ,
        // string a has a letter that appears later in the alphabet than the corresponding letter in b.
        //- If the first min(a.length, b.length) characters do (not differ), then (the longer string) is the lexicographically (larger one).
        //- In a row:
        //  ==> Liên tiếp
        //
        //Example 1:
        //
        //Input: s = "cczazcc", repeatLimit = 3
        //Output: "zzcccac"
        //Explanation: We use all of the characters from s to construct the repeatLimitedString "zzcccac".
        //The letter 'a' appears at most 1 time in a row.
        //The letter 'c' appears at most 3 times in a row.
        //The letter 'z' appears at most 2 times in a row.
        //Hence, no letter appears more than repeatLimit times in a row and the string is a valid repeatLimitedString.
        //The string is the lexicographically largest repeatLimitedString possible so we return "zzcccac".
        //Note that the string "zzcccca" is lexicographically larger but the letter 'c' appears more than 3 times in a row,
        // so it is not a valid repeatLimitedString.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= repeatLimit <= s.length <= 10^5
        //s consists of lowercase English letters.
        //  + length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //- Choose the largest character if:
        //  + count[c]>0
        //  + num_consecutive[c]<=k
        //- Consecutive values:
        //  + While loop ==> add(new c)
        //  ==> WRONG because we loop all of 'a' ==> It does not work because we need to add "c" after "a"
        //
        //- validIndex[i]:
        //  + We can append the i char if:
        //      + We reach to (index=i>=validIndex[i])
        //
        //* Issue:
        //- x(y)x
        //  + x<=y:
        //      + Traverse(y) before x:
        //      ==> No update visited[x] (Because we break before reaching the (x))
        //
        //- Update (consecutiveCount) if switch the (different character)
        //  + p(o:cons=1)po
        //  + Switch new char ==> update consecutiveCount
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n*26)
        //
//        String s = "cczazcc";
//        int repeatLimit = 3;
//        String s = "aababab";
//        int repeatLimit = 2;
//        String s = "robnsdvpuxbapuqgopqvxdrchivlifeepy";
//        int repeatLimit = 2;
        //Output:   yxxvvuvusrrqqppopopnliihgfeeddcbba
        //Rs:       yxxvvuvusrrqqppopponliihgfeeddcbba
//        String s = "ppappoo";
//        int repeatLimit = 2;
        String s = "xyutfpopdynbadwtvmxiemmusevduloxwvpkjioizvanetecnuqbqqdtrwrkgt";
        int repeatLimit = 1;
        System.out.println('p'-'o');
        System.out.println(repeatLimitedString(s, repeatLimit));
        System.out.println(repeatLimitedStringRefer(s, repeatLimit));
        System.out.println(repeatLimitedStringPriorityQueue(s, repeatLimit));
        //#Reference:
        //2782. Number of Unique Categories
        //2311. Longest Binary Subsequence Less Than or Equal to K
        //1439. Find the Kth Smallest Sum of a Matrix With Sorted Rows
    }
}
