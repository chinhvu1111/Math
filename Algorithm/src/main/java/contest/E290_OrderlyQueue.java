package contest;

import javafx.util.Pair;

import java.util.*;

public class E290_OrderlyQueue {

    public static String orderlyQueue(String s, int k) {
        char[] chars=s.toCharArray();
        if(k>1){
            Arrays.sort(chars);
            return String.valueOf(chars);
        }
        //cba
        //Rotate string
        //
        int n=s.length();
        String curStr="";

//        for(int i=0;i<n;i++){
//            StringBuilder rs=new StringBuilder();
//            int temp=i;
//            boolean isValid=false;
//            while(temp!=i||(!isValid)){
//                rs.append(s.charAt(temp));
//                isValid=true;
//                temp=(temp+1)%n;
//            }
//            prefix.add(rs.toString());
//        }
//        Collections.sort(prefix);
        for(int i=0;i<n;i++){
            StringBuilder rs=new StringBuilder();
            int temp=i;
            boolean isValid=false;

            while(temp!=i||(!isValid)){
                rs.append(s.charAt(temp));
                isValid=true;
                temp=(temp+1)%n;
            }
//            System.out.printf("%s %s\n", rs, curStr);
//            System.out.println(curStr.compareTo(rs.toString()));
            if("".equals(curStr)||curStr.compareTo(rs.toString())>0){
                curStr=rs.toString();
            }
        }
//
        return curStr;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s and an integer k.
        //- You can choose (one of the "first") (k letters) of s and append it at the end of the string.
        //* Return (the lexicographically smallest string) you could have after applying the mentioned step (any number of moves).
        //
        //Example 2:
        //
        //Input: s = "baaca", k = 3
        //Output: "aaabc"
        //Explanation:
        //In the first move, we move the 1st character 'b' to the end, obtaining the string "aacab".
        //In the second move, we move the 3rd character 'c' to the end, obtaining the final result "aaabc".
        //
        // Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= k <= s.length <= 1000
        //s consist of lowercase English letters.
        //  + length<=1000 ==> Time: O(n^2)
        //
        //- Brainstorm
        //the lexicographically smallest string
        //  + a,a,a==> better
        //- We can do any times
        //  ==> We should focus on finding the rule.
        //- Smallest string is (the sorted string).
        //
        //Ex:
        //s = bccda, k=3
        // ==>
        //  + ccdab ==> cdabc ==> dabcc => abccd
        //
        //- bccda, k=2
        //  + ccdab ==> cdabc ==> dabcc ==> abccd
        //- Order is present the char before char
        //Ex:
        //- bccda, k=2
        //bccda => ccdab
        //
        //---x----x1
        //  + x => x1
        //
        //- Each time, after we do (a new movement):
        //  + We can move the (next element) such that this element is in (the prefix with k size)
        //
        //1.1, Cases
        //1.2, Optimization
        //- We don't need to loop by using rotation method
        //  + ab(c)d ==> cd+ab ==> substring(i) + substring(0,i)
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n^2+n*log(n))
        //
//        String s = "cba";
//        int k = 1;
        String s = "cbacaaa";
        int k = 1;
        //Output:   acaaacb
        //Expected: aaacbac
        //aaab
        //aaaaab
        //aaaaaaaac ==> Choose this
        //
        System.out.println(orderlyQueue(s, k));
        //
        //#Reference:
        //380. Insert Delete GetRandom O(1)
        //917. Reverse Only Letters
        //1904. The Number of Full Rounds You Have Played
    }
}
