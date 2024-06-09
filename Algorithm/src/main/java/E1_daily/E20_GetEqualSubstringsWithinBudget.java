package E1_daily;

public class E20_GetEqualSubstringsWithinBudget {

    public static int equalSubstring(String s, String t, int maxCost) {
        int n=s.length();
        int start=0, i;
        int maxLen=0;
        int curCost=0;

        for(i=0;i<n;i++){
            curCost+=Math.abs(s.charAt(i)-t.charAt(i));
            while (curCost>maxCost){
                curCost-=Math.abs(s.charAt(start)-t.charAt(start));
                start++;
            }
            maxLen=Math.max(i-start+1, maxLen);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given two (strings s and t) of (the same length) and an integer (maxCost).
        //- You want to change (s to t). Changing (the ith character of s) to (ith character of t)
        // costs |s[i] - t[i]|
        // (i.e., the absolute difference between the ASCII values of the characters).
        //* Return (the maximum length of a substring of s) that can be changed to be the same as
        // (the corresponding substring of t) with (a cost) less than or equal to (maxCost).
        // If there is (no substring) from s that can be changed to (its corresponding substring from t), return 0.
        //* Tìm max length của string s mà có thể changed đế giống substring của t
        //  + Nhưng mà (cost <= maxCost)
        //<> return 0 nếu không có string nào.
        //* Cần clear lại đề bài:
        //- cost to change s[i] to t[i]
        //==> Cùng index
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //t.length == s.length
        //0 <= maxCost <= 10^6
        //s and t consist of only (lowercase English letters).
        //+ MaxCost khá lớn ==> Không thể loop mà sử dụng maxCost
        //+ Length khá lớn nên ==> O(n*k)
        //
        //- Brainstorm
        //- String dài nhất mã change mất cost <= maxCost
        //- Có thể brute force để check diff được không?
        //  ==> Không thể vì length quá lớn
        //- Binary search có được không
        //- Given length = x
        //  + Check xem có substring của t và s có cost <=max cost hay không?
        //abcd = 0123
        //bcdf = 1235
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space : O(1)
        //- Time : O(n)
        //
        String s="abcd", t="bcdf";
        int maxCost=3;
        System.out.println(equalSubstring(s, t, maxCost));
        //
        //#Reference:
        //2401. Longest Nice Subarray
    }
}
