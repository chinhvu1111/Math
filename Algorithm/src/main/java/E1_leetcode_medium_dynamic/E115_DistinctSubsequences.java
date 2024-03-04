package E1_leetcode_medium_dynamic;

public class E115_DistinctSubsequences {

    public static int numDistinct(String s, String t) {
        int n=s.length();
        int m=t.length();
        //s:n
        //t:m
        int[][] dp=new int[m][n];

        for(int i=0;i<m;i++){
            char c=t.charAt(i);
            Character prevC=null;
            int curCount=0;
            int prevCount=0;

            if(i-1>=0){
                prevC=t.charAt(i-1);
            }else{
                prevCount=1;
            }

            for(int j=0;j<n;j++){
                if(s.charAt(j)==c){
                    dp[i][j]=prevCount+curCount;
                    curCount+=prevCount;
//                    System.out.printf("char: %s, prevC: %s, i:%s, j:%s, dp[i][j]: %s\n", c, prevC, i, j, dp[i][j]);
                }
                if(prevC!=null&&prevC==s.charAt(j)){
                    prevCount=dp[i-1][j];
                }
            }
        }
        int rs=0;
        for(int i=0;i<n;i++){
            rs=Math.max(rs, dp[m-1][i]);
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Given two strings s and t
        //* Return the number of distinct subsequences of s which equals t.
        //The test cases are generated so that the answer fits on a 32-bit signed integer.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= s.length, t.length <= 1000
        //s and t consist of English letters.
        //
        //- Brainstorm
        //Ex:
        //rabbbit
        //||| |||
        //rab bit
        //- Tức là tìm số lượng subsequence trong (s) mà trong (t)
        //Ex
        //ra(bbb)it
        //||| |||
        //rab
        //ra(bb(a)b)it
        //||| |||
        //rab
        //Ta thấy:
        //+ a(bb) ==> (bb) được tính theo all (a) đằng trước
        //+ (a)b ==> b sẽ được tính theo all a đằng trước nó == sum all cases(a).
        //-> Ta cần cache lại character trước đó để compare.
        //- Kết quả cuối sẽ bằng max nhất dp[m-1][0->n-1]
        //
        //- Loop
        //Ex:
        //s= rra(a)bbbit
        //t= r(a)bbit
        //+ Có nhiều (r) đằng trước và có nhiều (a)
        //+ dp[1][3] : Tính như thế nào
        //- Mỗi a sẽ cộng tổng all (rr)
        //=>
        //dp[1][2]=2
        //dp[1][3]=dp[1][2]+2(all r)
        //Ex:
        //+ r đứng giữa các (a)
        //s= rrar(a)bbbit
        //t= r(a)bbit
        //==> a nào thì tính bằng all prev count (r) trước nó thôi.
        //- Cache char của (t) ==> Không cần tính sum (Chỉ cần lấy latest dp[i][j] là được)
        //
        //- Init
        //+ Xử lý khi ta đừng ở index=0
        //  + prevChar=null, prevCount=1
        //  => Lúc đó ta luôn (curCount=+ prevCount) khi exists (c==t[i])
        //+ Còn nếu không thì ta lấy latest:
        //  + prevCount=dp[i-1][j] : Tức là khi kết quả của việc compare (t[i-1]==s[j])
        //      + Không phải dp[i-1][j-1] vì ta đang assign sau comparison.
        //  + Latest vì việc cùng rrr --> dp[i][j] tại (r) cũng tăng dần theo sum(các char trước rồi)
        //
        //Ex:
        //rabbbit
        //rabbit
        //dp[0][0]=1
        //dp[1][1]=1
//        String s = "rabbbit";
//        String t = "rabbit";
//        String s = "babgbag";
//        String t = "bag";
//        String s = "r";
//        String t = "r";
//        String s = "rr";
//        String t = "r";
//        String s = "r";
//        String t = "rr";
        String s = "";
        String t = "";
        //- i=0:
        //  + dp[0][0]=1
        //  + dp[0][2]=2
        //  + dp[0][4]=4
        //dp[1][1]=1
        //
        System.out.println(numDistinct(s, t));
        //#Reference:
        //1987. Number of Unique Good Subsequences
    }
}
