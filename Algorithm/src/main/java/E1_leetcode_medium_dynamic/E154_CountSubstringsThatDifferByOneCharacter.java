package E1_leetcode_medium_dynamic;

import java.util.HashMap;

public class E154_CountSubstringsThatDifferByOneCharacter {

    public static int countSubstrings(String s, String t) {
        int n=s.length();
        int m=t.length();
        int rs=0;
        //
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int miss=0;
                int pos=0;
                while(i+pos<n&&j+pos<m){
                    if(s.charAt(i+pos)!=t.charAt(j+pos)){
                        miss++;
                        if(miss>1){
                            break;
                        }
                    }
                    rs+=miss;
                    pos++;
                }
            }
        }
        return rs;
    }

    public static int countSubstringsMethod2(String s, String t) {
        int n=s.length();
        int m=t.length();
        int rs=0;
        //
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(s.charAt(i)!=t.charAt(j)){
                    int l=i-1;
                    while(l>=0&&j-i+l>=0&&s.charAt(l)==t.charAt(j-i+l)){
                        l--;
                    }
                    int r=i+1;
                    while(r<n&&j-i+r<m&&s.charAt(r)==t.charAt(j-i+r)){
                        r++;
                    }
                    rs+=(i-l)*(r-i);
                }
            }
        }
        return rs;
    }

    public static int countSubstringsDynamicProgramming(String s, String t) {
        int n=s.length();
        int m=t.length();
        int rs=0;
        int[][] dpLeft=new int[n+1][m+1];
        int[][] dpRight=new int[n+1][m+1];

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dpLeft[i][j] = dpLeft[i-1][j-1]+1;
                }
            }
        }
        for(int i=n;i>0;i--){
            for(int j=m;j>0;j--){
                if(s.charAt(i-1)==t.charAt(j-1)){
                    dpRight[i-1][j-1] = dpRight[i][j]+1;
                }
            }
        }
        //
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(s.charAt(i)!=t.charAt(j)){
                    rs+=(dpLeft[i][j]+1)*(dpRight[i+1][j+1]+1);
                }
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given two strings s and t, find (the number of ways) you can choose (a non-empty substring of s) and (replace "a single" character)
        // by ("a" different character) such that (the resulting substring) is a (substring of t).
        //<=> In other words, find (the number of substrings) in s that differ from some substring in t by (exactly one character).
        //+ For example, the underlined substrings in "computer" and "computation"
        //  + compute <> computa : Khác nhau đúng 1 ký tự
        // only differ by the 'e'/'a', so this is a valid way.
        //* Return (the number of substrings) that satisfy (the condition above).
        //- A substring is a contiguous sequence of characters within a string.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length, t.length <= 100
        //s and t consist of lowercase English letters only.
        //==> Số không quá lớn O(n^2) được.
        //
        //- Brainstorm
        //Ex:
        //Input: s = "aba", t = "baba"
        //Output: 6
        //Explanation: The following are the pairs of substrings from s and t that differ by exactly 1 character:
        //("aba", "baba")
        //("aba", "baba")
        //("aba", "baba")
        //("aba", "baba")
        //("aba", "baba")
        //("aba", "baba")
        //The underlined portions are the substrings that are chosen from s and t.
        //
        //- Count số lượng substring khác nhau đúng 1 char
        //- Liệu có tính dựa trên common sub-array của 2 strings được không?
        //==> Cái này thì không lưu lại index ==> Tính sau
        //
        //- Tính toàn bộ substring của S
        //  ==> HashMap : count
        //  + Change character : ra string mới (Change khá dễ do toàn bộ là lower case (26 characters)
        //  ==> Vấn đề là nó có thể khác nhau ở bất cứ đâu
        //
        //* Solution:
        //  + Loop theo s và t
        //Ex:
        //s = "aba", t = "baba"
        //- Search theo prefix
        //  + substring của s có thể start at any index trong (t
        //- Nên ta có thể loop:
        //  + s: 0<=i<=n-1
        //  + t: 0<=j<=n-1
        //      + So sánh từng s[i]<>t[j]:
        //      + miss=0: Tức là chưa có string nào
        //      + miss=1: Tức là có khác biệt r ==> rs+=miss
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Time : O(n^3)
        //- Space : O(1)
        //
        //2. Xét left, right tại (index=i) của (s[i]!=t[j])
        //+ Đi ra left, right
        // ==> rs+=countLeft*countRight
        //- Vì pair chars khác nhau ==> Có thể map ra các pair of strings thoả mãn điều kiện trên.
        //2.1, Optimization
        //2.2, Complexity
        //- Space : O(1)
        //- Time : O(n^3)
        //
        //3. Dynamic programming
        //3.1.
        //- dpLeft[i][j] sẽ thể hiện là:
        //  + Chiều dài chuỗi matching với s[i]<>s[j] về left (Không tính (i) và (j))
        //- dpRight[i][j] sẽ thể hiện là:
        //  + Chiều dài chuỗi matching với s[i]<>s[j] về right (Không tính (i) và (j))
        //==> Sau đó xét tương tự:
        //  + s[i]!=t[j]: rs+= dpLeft*dpRight
        //
        //#Reference:
        //2135. Count Words Obtained After Adding a Letter
        //
//        String s = "aba", t = "baba";
        String s = "ab", t = "bb";
        System.out.println(countSubstrings(s, t));
        System.out.println(countSubstringsMethod2(s, t));
        System.out.println(countSubstringsDynamicProgramming(s, t));
    }
}
