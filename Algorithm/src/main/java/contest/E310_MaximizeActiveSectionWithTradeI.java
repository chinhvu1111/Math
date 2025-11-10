package contest;

import java.util.Deque;
import java.util.LinkedList;

public class E310_MaximizeActiveSectionWithTradeI {

    public static int maxActiveSectionsAfterTrade(String s) {
        int n=s.length();
        int countOne=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='1'){
                countOne++;
            }
        }
        s = "1"+s+"1";
        n=s.length();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if(str.length()>=1&&s.charAt(i)=='1'&&str.charAt(str.length()-1)=='1'){
                continue;
            }
            str.append(s.charAt(i));
        }
        n=str.length();
        Deque<Integer> indices=new LinkedList<>();
        int maxConvertedBit=Integer.MIN_VALUE;
//        System.out.println(str);

        for (int i = 0; i < n; i++) {
//            int index=i;
            if(str.charAt(i)=='1'){
                indices.addLast(i);
            }
//            System.out.println(i);
            //10101
            //c= 4-0-1
            if(indices.size()>=3){
                int first = indices.removeFirst();
                int last = indices.getLast();
                maxConvertedBit=Math.max(last-first-2, maxConvertedBit);
            }
        }
        if(maxConvertedBit==Integer.MIN_VALUE){
            return countOne;
        }
        return countOne+maxConvertedBit;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given (a binary string s of length n), where:
        //  + '1' represents an (active) section.
        //  + '0' represents an (inactive) section.
        //- You can perform (at most) (one trade) to maximize the number of active sections in s.
        //- In a trade, you:
        //  + Convert ("a" contiguous block of '1's) that is surrounded by '0's to all '0's.
        //  + Afterward, convert (a contiguous block of '0's) that is surrounded by '1's to all '1's.
        //* Return the (maximum number of ("active") sections) in s after making the optimal trade.
        //- Note: Treat s as if it is augmented with a '1' at both ends, forming t = '1' + s + '1'.
        //  + The augmented '1's do not contribute to the final count.
        //
        //Example 2:
        //
        //Input: s = "0100"
        //Output: 4
        //Explanation:
        //
        //String "0100" → Augmented to "101001".
        //Choose "0100", convert "101001" → "100001" → "111111".
        //The final string without augmentation is "1111". The maximum number of active sections is 4.
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= n == s.length <= 10^5
        //s[i] is either '0' or '1'
        //  + Length<=10^5 ==> Time: O(n)
        //
        //- Brainstorm
        //Ex:
        //1010001001
        //=>
        //1010000001
        //=>
        //1011111111
        //- Length subarray with (maximum) of (zero bit)(0)
        //- Max length of subarray:
        //  + It contains at most (3 bits) with 1 value
        //- Use dequeue to solve this problem
        //
        //Ex:
        //1010001001
        //[0],[2],[3]
        //==> pop[0]
        //
//        String s = "01";
        //==> 1011
        String s = "0100";
//        String s = "1000100";
//        String s = "11110010";
        //5 + 3
//        String s = "11110010111";
        //
        //* Main point:
        //- Combine 1111 ==> only one "1"
        //
        //s = 11110010
        System.out.println(maxActiveSectionsAfterTrade(s));
    }
}
