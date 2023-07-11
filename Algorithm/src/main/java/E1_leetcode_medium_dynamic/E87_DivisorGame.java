package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E87_DivisorGame {
    //N= n-x
    public static int solution(int[] dp, int num){
        if(num==1){
            return 0;
        }
        if(dp[num]!=-1){
            return dp[num];
        }
        boolean isWin=false;

        //N/2
        for(int i=1;i<=num/2;i++){
            if(num%i==0){
                isWin= solution(dp, num - i) == 0;
//                System.out.printf("%s %s %s\n", num - i, isWin, num);
                if(isWin){
                    break;
                }
            }
        }
        return dp[num]=(isWin)?1:0;
    }
    public static boolean divisorGameSlow(int n) {
        int[] dp=new int[n+1];
        Arrays.fill(dp, -1);
        int result=solution(dp,n);

        return result==1;
    }
    public static boolean divisorGame(int n) {
        return n%2==0;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Having n number
        //- 2 people play game
        //- Each person chooses number x such that (n%x==0) then replace n with (n-x)
        //- If any person cannot make a move --> lose
        //- Each person play optimally
        //- When win?
        //+ n=1 : 0 < x < n
        //* return true if and only if Alice win the game.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint:
        //+ 1 <= n <= 1000
        //- Brainstorm
        //VD:
        //n=10
        //+ Choose the number x:
        // n%x==0 <=> n = k*x
        // (n --> n-x)
        //
        //- Cannot make a move when we can not find the x such that (n%x ==0)
        //Example:
        //n=10
        //- When don't we make a move?
        //+ n is prime number ==> the person play later who will to be lose.
        //
        //- How to play optimally?
        //- Only one of the sub-cases is False --> current result = True
        //
        //Example:
        //                                  10 --> expect TRUE
        //              (5,5)       (2,8)                      (1,9) --> also expect TRUE
        //          /               /   \                 /            \
        //       (1,4)          (2,6)   (4,4)          (3,6)T          (1,8)
        //      /    \                    /        /      \     \
        //   (1,3)F   (2,2)T            (2,2)T   (2,4)  (3,3)F  (1,5)F
        //                                       /     \            \
        //                                     (2,2)T  (1,3)F      (1,4)T
        //** NOTE:
        //- N%x==0
        //+ If N is odd : x is not even
        //+ If N is even : x is (even/ odd)
        //** Prove N<=n:
        //+ If N is even --> Can win
        //+ If N is odd --> will lose
        //
        //N=1 : True
        //N=2 : True
        //N=3 : False/ x=1 --> Decrease : 2 (TRUE)
        //N=4 ==> (1,3)F, (2,2) : True
        //N=5 : False : (1,4) True ==> False
        //N=6 : True : (1,5) (False),(2,4),(3,3)
        //N=7 : False: (1,6)
        //N=8 : True : (1,7)True, (2,6), (4,4)
        //N=9 : False : (1,8)False, (3,3) False
        //*
        //- Với trường hợp even --> Chắc chắn là nếu x=1 ==> Sẽ ra kết quả lẻ đã được tính trước = False ==> Đảo lại ra kết quả hiện tại = True
        //- Với trường hợp odd --> Ta sẽ chỉ chia được các case odd với (N1 < N) (Khi trừ 2 số lẻ n-x = even) --> Các cases đó đã được tính có thể True (Tối ưu) ==> current result=False
        //
        //** Key ở đây là ai get 1 trước
        //- Even : A get 1 trước --> B sẽ là Odd ==> B sẽ chạy dần dần --> 1 (sole) (F)
        //- Odd : thì A bị get odd --> Cũng chạy dần dần đến 1 (sole) (F)
        //
        //Case 1:
        //- N is even number:
        //+ even (T) --> even(T) --> odd(F) --> even(hiệu) --> odd (F)
        //+ or   --> even(T) --> even(T) --> even(4)T --> even (2) T
        //==> 2 cases bên trên đều có thể True được.
        //- N is odd number:
        //+ odd F --> (even) T --> (odd) F --> even(T) --> odd(3) F --> even(2) T
        //        --> odd(F)
        //VD:
        // n=3 (prime + odd) --> going first => lose
        //==> Wrong ==> We can decrease 1 unit each time ==> The number will to be (even number)
        //VD: 7 --> (1,6)
        // n=2 (prime + even) --> going first => win
        //
        //1 --> 1000 : Danh sách các prime number ==> Cũng khá nhiều (Không nên làm thế này)
        //
        //- n
        //A : x --> n-x
        //B : y --> n-x-y ==> 0
        //
        //n=10 : 1,5,2
        //
        //1.1,
        //* Complexity of slow way
        //- Time complexity :
        //                              n
        //                       /
        //                      1 ......n/2 : n/2
        //                   /                  \
        //                (i)<=n/4              (i)<=n/4
        //            1 ... i/2(<=n/4 elements)   1...i/2(<=n/4 elements) ==> n/4*n/2
        //               /         \
        //            (j)<=n/8     (j)<=n/8
        //             /
        //      1 ... j/2(<=n/8 elements) : n/8*n/4*n/2
        //time = n/2 + n/4 * n/2 + n/4 * n/2 * n/8
        //==> time = n*2^-1 + n^2 * 2^-3 + n^3 * 2^-6
        //
        //- Space complexity :
        //
        //
        //* Complexity of fast way
        //- Time complexity : O(1)
        //- Space complexity : O(1)
        int n=2;
        System.out.println(divisorGameSlow(n));
        System.out.println(divisorGame(n));
        //#Reference:
        //738. Monotone Increasing Digits
        //2420. Find All Good Indices
        //899. Orderly Queue
        //1551. Minimum Operations to Make Array Equal
        //361. Bomb Enemy
        //1916. Count Ways to Build Rooms in an Ant Colony
        //https://leetcode.com/tag/brainteaser/
    }
}
