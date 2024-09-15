package E1_leetcode_medium_dynamic;

public class E176_NumberOfMusicPlaylists {

    public static int numMusicPlaylists(int n, int goal, int k) {
        long[][] dp=new long[goal+1][n+1];
        int mod=1_000_000_007;
        dp[0][0]=1;

        for(int i=1;i<=goal;i++){
            int min=Math.min(n, i);
            for(int j=1;j<=min;j++){
                dp[i][j]=dp[i-1][j-1]*(n-j+1)%mod;
                if(j>k){
                    dp[i][j]=(dp[i][j]+dp[i-1][j]*(j-k))%mod;
                }
            }
        }
        return (int) dp[goal][n];
    }

    public static void main(String[] args) {
        //** Requirement
        //- (Your music player) contains n (different songs).
        //  + n diff songs
        //  + You want to listen to goal songs (not necessarily different) during (your trip).
        //- To avoid (boredom), you will create a playlist so that:
        //  + Every song is played (at least once).
        //  + (A song) can only be (played again) only if (k other songs) have been played.
        //      + k song != current song
        //- Given n, goal, and k,
        //* Return (the number of possible playlists) that you can create.
        //- Since the answer can be very large, return it modulo 10^9 + 7.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1: Dynamic programming
        //- Constraint:
        //0 <= k < n <= goal <= 100
        //  + k, n không quá lớn : O(n*k) ==> O(n^3)
        //+ n song are distinctive.
        //
        //- Brainstorm
        //- (A song) can only be (played again) only if (k other songs) have been played.
        //  + The current song is x
        //  + (x1!=x) ==> bên trong tập hợp !=x
        //      + Cũng phải khác nhau --> vì chúng là (smaller collection) của (subset !=x)
        //  ==> k song trước cũng phải khác nhau luôn.
        //
        //- Mỗi bài hát phát ít nhất 1 lần
        //  + goal >= n ==> Luôn phát ít nhất 1 lần
        //Example 1:
        //Input: n = 3, goal = 3, k = 1
        //Output: 6
        //Explanation: There are 6 possible playlists: [1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 1, 2], and [3, 2, 1].
        //
        //- Phải chú ý case:
        //  + Với k nhỏ => Cần listen đủ các song
        //- Các song có vai trò như nhau:
        //  ==> Điền sao cũng được
        //
        //- Làm sao có thể biết được (k songs) trước current song
        //  + Xét current song = i (0->n-1):
        //      + Ghép với (k songs) != current song:
        //          + a,b,c,x
        //          a!=x,b!=x,c!=x
        //              + c có n-1 cách chọn
        //              + b có n-2 cách chọn
        //              + a có n-3 cách chọn
        //Input: n = 3, goal = 3, k = 1
        //a,b,c
        //- Xét a,b
        //current song = b:
        //  + b có 3 cách chọn
        //  + a có 2 cách chọn
        //  ==> dp[a]=6
        //  ==> dp[a]=2
        //- Xét a,b,c
        //current song = c:
        //  + c có 3 cách chọn
        //  + b có 2 cách chọn
        //  + a có 1 cách chọn
        //
        //- a,b
        //  + a và b có thể chọn (1->5)
        //  + a!=b
        //  + b có 5 cách chọn (1,2,3,4,5)
        //      + a!=b ==> (1-(2->5),...)
        //          + Mỗi phần tử gắn với n-1 phần tử còn lại
        //- a,b
        //  + b có thể chọn (1->3)
        //  + a có thể chọn (1->5)
        //  + a!=b
        //      + 1: (5-1) ( Các số còn lại trừ 1)
        //      + 2: (5-1)
        //      + 3: (5-1)
        //          ==> 3*(5-1)
        //- số lần luôn bằng khi xét đến i với song(i) != k lần trước = (n-k)
        //
        //Input: n = 3, goal = 5, k = 2
        //a,b,c,d,e
        //  + e có 3 cách chọn
        //  + d có 2 cách chọn
        //  + c có 1 cách chọn
        //  + b có 1 cách chọn
        //  + a có 1 cách chọn
        //
        //Input: n = 5, goal = 6, k = 2
        //- Xét a,b,c,d,e,f
        //  + f có 5 cách chọn
        //  + e có 4 cách chọn (e!=f)
        //  + d có 3 cách chọn (d!=e, d!=f)
        //  + c có 3 cách chọn (c!=d, c!=e)
        //  + b có 3 cách chọn
        //  + a có 3 cách chọn
        //
        //  + f có 5 cách chọn Ex: 1
        //  + e có 4 cách chọn (e!=f) Ex: 2
        //  + d có 3 cách chọn (d!=e, d!=f): Ex: 3
        //  + c có 3 cách chọn (c!=d, c!=e): Ex 1
        //  + b có 3 cách chọn Ex: 2
        //  + a có 3 cách chọn Ex: 3
        //  ==> Thiếu phần tử (4,5) nhưng vẫn thoả mãn
        //rs= 5*4*3*3*3*3 ==> WRONG
        //  + Bị các key mà không đủ n songs
        //
        //- Trừ đi những case thiếu có được không?
        //  + Khá khó
        //  + f có 4 cách chọn Ex: 1
        //  + e có 3 cách chọn (e!=f) Ex: 2
        //  + d có 2 cách chọn (d!=e, d!=f): Ex: 3
        //  + c có 2 cách chọn (c!=d, c!=e): Ex 1
        //  + b có 2 cách chọn Ex: 2
        //  + a có 2 cách chọn Ex: 3
        // = 4*3*2*2*2*2
        //* Kinh nghiệm:
        //- Khi mà mọi thứ quá khó ==> Để đi theo chiều chọn
        //  + Ta focus xử lý vấn đề unique được không
        //- Làm sao để check đủ n songs trong goal:
        //* Hint:
        //  + Liên quan đến (unique number) in (goal songs)
        //  ==> Nó liên quan gì đến k phần tử khác nhau liên tiếp
        //      + KHÔNG CẦN LIÊN QUAN
        //- Cái chính là ta phải xử lý vấn đề unique bằng 1 dimension thêm
        //  + Còn việc k elements liên tiếp khác nhau ==> Sẽ là cách chọn
        //- Xét index = i:
        //- 1 bài toán khác:
        //Input: n = 5, goal = 6, k = 2
        //==> Giảm goal = 5 cho dễ.
        //- Expected value = 120
        //- Xét a
        //  + dp[0][0] = 0
        //  + dp[0][1] = 5
        //- Xét a,b
        //  + dp[1][2] = 5*4
        //- Xét a,b,c
        //  + dp[2][2]: 0 (K<=unique ==> Mới có giá trị)
        //      + index=0,1,2 ==> 0 sẽ không có giá trị để chọn
        //      + nếu k>unique ==> Không biết chọn (index=0 ntn)
        //      + 2 unique values
        //  + dp[2][3]:
        //      + Unique 3 sẽ chọn từ (5-2) (3 số còn lại)
        //      + dp[1][2]*(5-2) = 5*4*3 = 60
        //      + Do k=2 ==> unique phải >=2 mới được
        //- Xét a,b,c,d
        //  + dp[3][3]:
        //      + dp[1][3-2]: do c và d chắc chắn unique
        //          + dp[1][1]=0: Nên không xảy ra case này
        //      + dp[2][3]:
        //          + Không cần thêm unique
        //          + Chỉ cần khác 2 số trước đó
        //          + Giống số trước (k số trước đó)
        //          + dp[3][3] = dp[2][3]*(3-k) = 60*1 = 60
        //              + 3 là số unique values có thể chọn
        //              + 3-k: là số lần có thể chọn
        //          + Ex:1,2,3,1
        //  + dp[3][4]:
        //      + Để thêm 1 phần tử ==> tăng được số lượng unique value lên 1 unit
        //          + dp[3][4] = dp[2][3]*(5-3) = 60*2 = 120 (Chọn 5-3 values còn lại)
        //- Xét a,b,c,d,e
        //      + dp[4][5] (add phần tử mới):
        //          + dp[3][4]*(5-4) = 120 ==> (RESULT)
        //      + dp[4][4]:
        //          + dp[4][4] = dp[3][3]*2 = 40*2 = 80
        //      + dp[4][3]:
        //          + dp[4][3] = dp[3][3]*(3-k) = 40*1 = 40
        //      + dp[4][2]
        //      + dp[3][2]
        //** KHÓ
        //** IDEA:
        //- Không xử lý được:
        //  ==> Đi xử lý (Hướng khó xử lý)
        //      + Base trên các case dễ.
        //- Init
        //- dp[0][0]=1;
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(goal*n)
        //- Time: O(goal*n)
        //
//        int n = 2, goal = 3, k = 0;
        int n = 5, goal = 5, k = 2;
        //rs=120
        System.out.println(numMusicPlaylists(n, goal, k));
    }
}
