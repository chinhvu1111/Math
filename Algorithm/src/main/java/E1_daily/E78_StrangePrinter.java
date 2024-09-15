package E1_daily;

import java.util.Arrays;

public class E78_StrangePrinter {

    public static int strangePrinterBottomUp(String s) {
        int n=s.length();
        //Time: O(n*n)
        //Space: O(n*n)
        int[][] dp=new int[n+1][n+1];

//        for(int i=0;i<n;i++){
//            Arrays.fill(dp[i], Integer.MAX_VALUE);
//        }

        //Space: O(n)
        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }
        //Space: O(n^3)
        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int k=i+j-1;
//                System.out.printf("%s %s\n", j, k);
                //Chọn ghi 1 character
                dp[j][k]=dp[j+1][k]+1;
                //Ex:
                //aba
                //+ ab = [a]+1 = 2
                //  ==> ab = 2
                //aba:
                //  + s[0] == s[2]:
                //      ==> (h phải đến được k) : h (j+1 => k)
                for(int h=j+1;h<=k;h++){
                    if(s.charAt(j)==s.charAt(h)){
                        //Chọn bằng cái bên trong
                        dp[j][k]=Math.min(dp[j][k], dp[j][h-1]+dp[h+1][k]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }

    public static int[][] memo;

    public static int solutionTopDown(int start, int end, String s){
        if(start>end){
            return 0;
        }
        if(memo[start][end]!=0){
            //Time: O(n^2)
            return memo[start][end];
        }
        //Nhận s[start] là cái ta sẽ print
        int minTurns=solutionTopDown(start+1, end, s)+1;

        //start+(start,end]
        //Time: O(n)
        for(int i=start+1;i<=end;i++){
            //(start,i),(i+1, end)
            //(aaaa)(bbbb)
            if(s.charAt(i)==s.charAt(start)){
                int curTurns = solutionTopDown(start, i-1, s)+solutionTopDown(i+1, end, s);
                minTurns=Math.min(curTurns, minTurns);
            }
        }
        return memo[start][end]=minTurns;
    }

    public static int strangePrinterTopDown(String s) {
        int n=s.length();
        memo=new int[n][n];

        solutionTopDown(0, n-1, s);
        return memo[0][n-1];
    }

    public static void main(String[] args) {
        //**Requirement
        //- There is (a strange printer) with the following (two special properties):
        //  + The printer can only print (a sequence of the same character) each time.
        //  + At each turn, the printer can print (new characters) (starting) from and (ending) at (any place)
        //  and will cover (the original existing characters).
        //Given a string s,
        //* Return (the minimum number of turns) the printer needed to (print it).
        //- Tóm tắt:
        //  + Mỗi step chọn 1 character để delete ==> Có thể cover cả các ký tự sẽ cover (all) ký tự khác
        //      + rs+=1
        //  + Ta có thể chọn ký tự nào + ending place nào cũng được.
        //Example - "t b g t g b"
        //Operation 1 - " t t t t t t "
        //Operation 2 - " t b b b b b"
        //Operation 3 - " t b g g g b"
        //Operation 4 - " t b g t g b"
        //==> Tức là cần ghi đè ra được kết quả:
        // + Chỉ có cách ghi đè thằng nào cover ngoài cùng ==> Tránh bên trong ghi xong thì bên ngoài ghi lại
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s.length <= 100
        //s consists of lowercase English letters.
        //==> Length khá nhỏ --> Có thể làm O(n^2)/ O(n^2*k)
        //
        //- Brainstorm
        //- Chỉ có cách ghi đè thằng nào cover ngoài cùng ==> Tránh bên trong ghi xong thì bên ngoài ghi lại
        //- If you have two pairs of characters.
        // For example "...[t....[t...t]..t]...."
        // sometimes (printing "t" first) and then (printing all the characters) between them uses (less moves) than (the greedy algorithm).
        //
        //- Để không cover lại các characters ở giữa t[...]t
        //  + Việc điền 1 phần các characters giống nhau ==> Không khác gì điền all cả
        //Ex:
        //at...tbbb
        //ttttttttt
        //<=>
        //.ttttt...
        //==> Cuối cùng cũng phải điền (a và bbb)
        //
        //- Các cases có thể xảy ra:
        //Ex:
        //(aa(bbaa)bbbb)
        //..(bbbbbbbb)
        //(aa)bbbbbbbb
        //aabb(aa)bbbb
        //+ Ở đây sẽ không tiền đoạn (aabaa) => (aaaaa) 2 lần vì:
        //  + Nó đã điền b đúng rồi ==> Ta sẽ (điền các vị trí còn lại thôi)
        //
        //- Xét đến vị trí (i):
        //Ex:
        //aaa(b)
        //aaa(bbb)
        //aaa(bcccbb)
        //  + aaabccc : (aaa)(b)(ccc)
        //  ==> rs = 3
        //  + Tính: aaabcccb
        //  + (aaa)(bbbbb)
        //  => (aaa)b(ccc)b: rs= 3
        //  + Ta có thể fill range(j,i) khi:
        //      + s[j]==s[i] ==> dp[i]=dp[j]+1
        //  ==> Xét 1 đầu không đúng được
        //- Ta cần phải xét 2 đầu: (i,j)
        //  + Với i và j cùng biến đổi
        //
        //Ex:
        //ccc : rs=1
        //+ s[i]==s[j] == b
        //bcccbb: rs = 2
        //  ==> Check thêm 1 layer nữa:
        //  s[i]!=s[i+1]
        //  + dp[0,5] = dp[1,4]+1
        //  s[i]==s[i+1]
        //  + dp[0,5] = dp[1,4]
        //+ s[i]!=s[j]:
        //bccccc:
        //  + dp[i,j]=Min(dp[i+1][j], dp[i][j-1])+1
        //==> WRONG
        //- Do case:
        //(aaabbb)
        //- Tính theo:
        //a(aabb)b
        //==> ntn thì sai
        //
        //- Làm top down trước:
        //CT:
        //  + (k=start+1, k<=end)
        //  + if(s[start]==s[k]):
        //      + dp[start][end] = min(dp[start][k-1],dp[k][end])
        //
        //- Dùng topdown:
        //  + Đơn giản là brute force
        //  + MEMO
        //
        //- Bottom up approach:
        //  + Ta chỉ có thể check nếu (s[start]== s[i-1])
        //      + Việc chọn (start -> i-1):
        //
        //- Công thức truy hồi là gì?
        //  + If s[start] == s[i]:
        //      + dp[start][end] = dp[start][i-1] + dp[i+1][end]
        //  Ex:
        //  (a)abbc(a)
        //  aaaaaa
        //  aabbba
        //  aabbca
        //  Expected rs = 3
        //* Bản chất là nếu 2 ký tự ở đầu giống nhau:
        //  Ex:
        //  (a)abbc(a)
        //  ==> dp[i][j] = dp[i][j-1] vì:
        //      + (a)abbc(a) = [aabbc] ==> Giảm đến cuối thì nó sẽ cộng vào (dp[j][j]==1) (s[j]=='a')
        //      + (a)abbc(a) = [abbca] ==> Giảm đến cuối thì nó sẽ cộng vào (dp[j][j]==1) (s[j]=='a')
        //      + (a)abbc(a) = [abbc] + 1
        //          + ==> Nếu apply công thức này thì nó sẽ có nhiều layer:
        //              + aaaaa(bbcdee)aaaaa
        //              ==> Cộng nhiều layer 'a' thì sẽ bị sai số
        //
        //- Đi lại các cases như trên:
        //Ex:
        //aba
        //[ab][a]
        //======
        //Chọn ghi 1 character
        //- dp[j][k]=dp[j+1][k]+1;
        //Ex:
        //aba
        //+ ab = [a]+1 = 2
        //  ==> ab = 2
        //aba:
        //  + s[0] == s[2]:
        //      ==> (h phải đến được k) : h (j+1 => k)
        //===
//        for(int h=j+1;h<=k;h++){
//            if(s.charAt(j)==s.charAt(h)){
//                //Chọn bằng cái bên trong
//                dp[j][k]=Math.min(dp[j][k], dp[j][h-1]+dp[h+1][k]);
//            }
//        }
        //===
        //======
        //
        //Ex:
        //ccc
        //+ expected rs=1
        //Ex:
        //bcccbb
        //+ expected rs=2
        //Ex:
        //bccccc
        //+ expected rs=2
        //Ex:
        //aaabbb
        //+ expected rs=2
        //
        //** KINH NGHIỆM:
        //- Làm mấy bài dynamic HARD thường:
        //  + Sẽ có 2 branches để chọn:
        //      + Worst cases
        //          + Kiểu gì cũng phải chọn
        //      + Optimal cases
        //          + Chọn sao cho tối ưu
        //
        //1.1, Optimization
        //- Để nhanh hơn ==> Ta có thể (remove duplicated characters) đi.
        //
        //1.2, Complexity
        //- Space: O(n^2)
        //- Time: O(n^3)
        //
//        String s = "aaabbb";
        String s = "aba";
        System.out.println(strangePrinterBottomUp(s));
        System.out.println(strangePrinterTopDown(s));
        //#Reference:
        //546. Remove Boxes
        //1591. Strange Printer II
    }
}
