package E1_leetcode_medium_dynamic;

import java.util.Stack;

public class E155_MinimumDeletionsToMakeStringBalanced {

    public static int minimumDeletionsWrong(String s) {
        int n=s.length();
        int rs=0;
        int countA=0;

        for(int i=n-1;i>=0;i--){
            int index=i;

            while(index>=0&&s.charAt(index)!='b'){
                countA++;
                index--;
            }
            int startIndex=index;
            while(startIndex>=0&&s.charAt(startIndex)=='b'){
                startIndex--;
            }
            if(countA!=0&&index!=startIndex){
                System.out.printf("val:%s, index=%s, startIndex=%s, countA: %s, i=%s\n", Math.min(countA, index-startIndex), index, startIndex, countA, i);
                if(countA<=index-startIndex){
                    rs+=countA;
                    countA=0;
                }else{
                    rs+=index-startIndex;
                }
//                rs+=Math.min(countA, index-startIndex);
            }
            i=startIndex+1;
        }
        return rs;
    }

    public static int minimumDeletions(String s) {
        int n=s.length();
        int rs=0;
        Stack<Character> stack=new Stack<>();

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='a'&&!stack.isEmpty()&&stack.peek()=='b'){
                stack.pop();
                rs++;
            }else{
                stack.add(s.charAt(i));
            }
        }
        return rs;
    }

    public static int minimumDeletionsDynamicProgramming(String s) {
        int n=s.length();
        int countB=0;
        int[] dp=new int[n+1];

        for(int i=1;i<=n;i++){
            if(s.charAt(i-1)=='a'){
                dp[i]=Math.min(dp[i-1]+1, countB);
            }else{
                dp[i]=dp[i-1];
                countB++;
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a string s consisting ("only" of characters 'a' and 'b').
        //You can (delete any number of characters) in s to make (s balanced).
        //- s is balanced
        //  + if there is no pair of indices (i,j) such that (i < j and s[i] = 'b' and s[j]= 'a').
        //* Return (the minimum number of deletions) needed to make (s balanced).
        //- Tìm cách xoá (i) sao cho không có (b trước a)
        //
        //Ex:
        //Input: s = "aababbab"
        //Output: 2
        //Explanation: You can either:
        //Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
        //Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s[i] is 'a' or 'b'.
        //==> O(n)
        //
        //- Brainstorm
        //Example 1:
        //Input: s = "aababbab"
        //Output: 2
        //Explanation: You can either:
        //Delete the characters at 0-indexed positions 2 and 6 ("aababbab" -> "aaabbb"), or
        //Delete the characters at 0-indexed positions 3 and 6 ("aababbab" -> "aabbbb").
        //
        //- Nếu có b trước a thì:
        //  + Xoá b
        //  + Xoá a
        //
        //Ex:
        //s= aababbab
        //+ Trong case này thì xoá 2 a đi thì sẽ có lợi
        //Ex:
        //s= aabaaaaaab
        //+ Trong case này thì xoá b đi sẽ có lợi
        //- Đi từ cuối
        //  + Chọn xem nên xoá b hay a với từng trường hợp
        //Ex:
        //s= abaa(bbb|aaa)
        //(bbb|aaa) : Bằng nhau thì xoá cái nào cũng dc
        //
//        String s ="bbaaaaabb";
//        String s ="baababbaabbaaabaabbabbbabaaaaaabaabababaaababbb";
//        String s ="aaabaabbabbbabaa";
        //aaabaabbabbbabaa => aaabaabbabbba(b)aa = aaabaabba[bbb][aaa]
        //aaabaabbabbbaaa
        // + => aaabaabba(bbb)aaa / aaabaabbabbb(aaa)
        //   => aaabaabba|aaa / aaabaabba|bbb
        //aaabaabba =>
        //Output: 4
        //Expect: 5
        String s ="bbabaa";
        //+ Chọn xoá bbab(aa) ==> Tốt hơn là xoá bba(b)aa
        //  + Mặc dù từ right -> left thì count(b) ít hơn.
        //  ==> Idea bên trên sai
        //Output: 4
        //Expected : 3
        //* Bài này giống bài ngoặc ==> STACK
        //  + Với mỗi b thì xoá mỗi a bên phải của b là được.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n)
        //
        //2. Dynamic programming
        //2.0, Brainstorm
        //- Point ở đây là lấy 'a' làm tham chiếu thay vì 'b'
        //  + Dùng 'b' thì sẽ bị case bên trên
        //- dp[i] là min character cần xoá để string valid
        //+ CT:
        //  + S[i]=='a':
        //      + Keep current a ==> remove prev b
        //          + b...ba==> Cần remove all prev b
        //          + countB
        ///     + Mỗi a tương ứng với xoá 1 b
        //          + aaaa : dp[a(i=2)] = min(dp[a(i=1)]+1, countB)
        //      + Remove a ==> Keep b
        //          + dp[i]+1
        //  + S[i]=='b':
        //      + Sau chưa biết ntn: countB++
        //      + dp[i+1]=dp[i]
        //
        //2.1, Optimization
        //2.2, Complexity
        //- Space: O(n)
        //- Time : O(n)
        //
        //#Reference:
        //2124. Check if All A's Appears Before All B's
        System.out.println(minimumDeletions(s));
        System.out.println(minimumDeletionsWrong(s));
        System.out.println(minimumDeletionsDynamicProgramming(s));
    }
}
