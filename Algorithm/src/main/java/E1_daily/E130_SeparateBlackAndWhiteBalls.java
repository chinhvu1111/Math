package E1_daily;

public class E130_SeparateBlackAndWhiteBalls {

    public static long minimumSteps(String s) {
        long rs=0;
        int n=s.length();
        int j=n-1;

        for(int i=n-1;i>=0;i--){
            if(s.charAt(i)=='1'){
                rs+=j-i;
                j--;
            }
        }
        return rs;
    }

    public static long minimumSteps1(String s) {
        long rs=0;
        int n=s.length();
        int blackBallCount=0;

        for(int i=0;i<n;i++){
            if(s.charAt(i)=='0'){
                rs+=blackBallCount;
            }else{
                blackBallCount++;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There are (n balls) on a table, (each ball) has a (color black or white).
        //- You are given (a 0-indexed binary string s) of length n, where
        //  + (1 and 0) represent (black and white) balls, respectively.
        //- In each step, you can choose (two adjacent balls) and (swap them).
        //* Return (the minimum number of steps) to group
        //  + (all the black balls) to the right
        //  + (all the white balls) to the left.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= n == s.length <= 10^5
        //s[i] is either '0' or '1'.
        //  + n khá lớn: Time: O(n)
        //
        //- Brainstorm
        //
        //Example 2:
        //Input: s = "100"
        //Output: 2
        //Explanation: We can group all the black balls to the right in the following way:
        //- Swap s[0] and s[1], s = "010".
        //- Swap s[1] and s[2], s = "001".
        //It can be proven that the minimum number of steps needed is 2.
        //
        //- Cần swap sao cho thành format 000...111
        //Ex:
        //s = "00110011000"
        //- Nếu 2 số 1 cạnh nhau => Swap thì không đổi gì
        //  + Hạn chế để 2 số 1 cạnh nhau
        //  ==> Tức là 1 lúc ta chỉ có thể swap 1 chữ số 1 thôi
        //  ==> Ta sẽ swap dần dần số 1 sang right là được.
        //- swap <=> shift
        //s = "00110011000"
        //=> rs+=3
        //s = "00110010001"
        //=> rs+=3
        //s = "00110000011"
        //=> rs+=5
        //s = "00100000111"
        //=> rs+=5
        //s = "00000001111"
        //
        //- Do n khá lớn ==> Không thể thành số được
        //
        //s = "00110(0)11000"
        //- Ta thấy rằng:
        //  + Khi gặp 0 bên trên thì nhiệm vụ của ta sẽ là swap tất cả (11) qua left
        //  s = "00110(0)11000"
        //  =>
        //  s = "00110000011"
        //  + Ta không đi từ (right->left) mà đi ngược lại (left->right)
        //  ==> Idea bên trên WRONG
        //
        //  s = "00[11](00)11000"
        //  + Ta thấy rằng ta sẽ move [11] => [0] sau đó move tiếp đến [0]
        //  => Khi gặp 0 thì ta sẽ move cùng 1 lúc (11) ==> rs+=count_black
        //  + Khi gặp 1 thì ta sẽ combine các số 1 vào với nhau:
        //      + Để gặp 0 thì cộng tiếp là được
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time: O(n)
        //
        String s="00110011000";
        System.out.println(minimumSteps(s));
        System.out.println(minimumSteps1(s));
        //
        //#Reference:
        //1217. Minimum Cost to Move Chips to The Same Position
        //2825. Make String a Subsequence Using Cyclic Increments
        //1402. Reducing Dishes (hard)
        //2512. Reward Top K Students
        //1028. Recover a Tree From Preorder Traversal (Hard)
        //1433. Check If a String Can Break Another String
    }
}
