package E1_leetcode_medium_dynamic;

public class E99_HandshakesThatDontCross {

    public static int numberOfWays(int numPeople) {
        int[] dp=new int[numPeople+1];
        int mod=1_000_000_007;
        dp[0]=1;
        dp[2]=1;
        for(int i=4;i<=numPeople;i+=2){
//            System.out.println(i);
            long curRs=dp[i-2];
            long remainingCases=0;

            for(int j=4;j<=i;j+=2){
                remainingCases=remainingCases+((long) dp[j - 2] *dp[i-j])%mod;
//                System.out.printf("%s, ", dp[j-2]*dp[i-j]);
            }
//            System.out.println(remainingCases);
            dp[i]= (int) ((curRs+remainingCases)%mod);
//            System.out.printf("Index= %s: %s %s, rs= %s\n", i, curRs, remainingCases, dp[i]);
//            System.out.printf("Index= %s, rs= %s\n",i, dp[i]);
        }
        return dp[numPeople];
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Có even số lượng people (numPeople) mà ngồi quanh 1 circle,
        // mỗi người sẽ bắt tay với 1 người khác để có : numPeople/2 lần
        //* Return số lượng cách handshake sao cho không có những cái bắt tay cross.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //2 <= numPeople <= 1000
        //numPeople is even.
        //--> Số lượng cũng nhỏ --> <=O(n^2) được
        //- Since the answer could be very large, return it modulo 109 + 7.
        //
        //- Brainstorm
        //Ex:
        //numPeople = 4
        //rs=2
        // 1 --- 2
        // 4 --- 3
        // 1     2
        // |     |
        // 4     3
        //- Các số sẽ có vai trò như nhau
        // --> Nên ta sẽ đánh số tăng dần xung quanh circle.
        //Ex:
        //numPeople = 6
        //  1  2
        //6     3
        // 5  4
        //1 điểm có thể:
        //+ Connect với node left hoặc right
        //+ Hoặc là các trường hợp song song
        //- Vì không thể phân tách các cases
        //==> Dynamic programming theo số node
        //Ex:
        //numPeople = 6
        //- Khi cố định 2 nodes (1), (2) thì 4 nodes còn lại sẽ có nhưng trường hợp nào:
        //+ 1 và 2 connect với nhau ==> current rs= dp[n-2]
        //+ 1 và 2 connect với 2 điểm cạnh nó ==> 1
        //+ 1 or 2 connect với node 2*k sao cho:
        //  + Khoảng cách connect <= n/2
        //Ex:
        //numPeople=8
        //    1  2  3
        //  8         4
        //    7  6  5
        //+ 1 và 2 --> dp[6]=5
        //+ 1 và 2 connect 2 điểm cạnh = 1
        //+ 1 (index=0) connect với:
        //  + index=3 : dp[2] và dp[4] = 2
        //  + index=5 : dp[4] và dp[2] = 2
        //+ 2 (index=0) connect với:
        //  + index=3 : dp[2] và dp[4] = 2
        //  + index=5 : dp[4] và dp[2] = 2
        //-> Do với n=10 bị sai
        //- Chia trường hợp lại:
        //+ 1 và 2 --> dp[6]=5
        //+ 1 và 4 --> dp[4]*dp[2] = 2
        //+ 1 và 6 --> dp[2]*dp[4] = 2
        //+ 1 và 8 --> dp[6]=5
        //
        //rs = 6+8 = 14
        //
        //- Init
        //+ dp[2]=1
        //+ dp[4]=2
        //+ dp[6]=5
        //  1  2
        //6     3
        // 5  4
        //- numPeople=10:
        //dp[8]=14
        //1
        //dp[6] + dp[4]*dp[4] + dp[6] = 5 + 2*2 + 5 = 14
        //==> 14*2+1 = 43 ==> Sai
        //
        //    1  2  3  4
        //  10           5
        //    9  8  7  6
        //- Nếu chỉ quy về 1 điểm connect đến các điểm khác:
        //Ex:
        // 2 -> 3
        // 2 -> 5
        // 2 -> 7
        // 2 -> 9
        // 2 -> 1
        //==> Mỗi bước sẽ chia tập hợp thành các nửa khác nhau ==> Tương ứng với số cases nhân đôi một với nhau.
        //1.1, Optimization
        //- Phần modulo thì cần khi multiply cần module tránh số lớn ngay lúc đó luôn
        //
        //1.2, Complexity:
        //- Time : O(n^2)
        //- Space: O(n)
        //
        //* Method 2 : Catalan Numbers
        //2,
        //2.1, Idea
        //-
        //
//        int numPeople=6;
//        int numPeople=8;
//        int numPeople=10;
        //#Reference:
        //1017. Convert to Base -2
        //469. Convex Polygon
        //2750. Ways to Split Array Into Good Subarrays
        int numPeople=140;
        System.out.println(numberOfWays(numPeople));
        System.out.println(Math.pow(10,9)+7-1000_000_007);
    }
}
