package E1_leetcode_medium_dynamic;

public class E96_4Keyboard {

    public static int maxAWrong(int n) {
        int[][] dp=new int[n][4];
        dp[0][0]=1;

        for(int i=1;i<n;i++){
            //0 : A
            //1 : Ctrl+A
            //2 : Ctrl+C
            //3 : Ctrl+V
            //
            //
            System.out.printf("Print A's value= %s\n", dp[i][0]);
            System.out.printf("Ctrl+A value= %s\n", dp[i][1]);
            System.out.printf("Ctrl+C value= %s\n", dp[i][2]);
            System.out.printf("Ctrl+V value= %s\n", dp[i][3]);
            System.out.println("======================");
        }
        return Math.max(dp[n-1][0], Math.max(dp[n-1][1], Math.max(dp[n-1][2], dp[n-1][3])));
    }

    public static int maxA(int n) {
        int[] dp=new int[n];

        for(int i=0;i<n;i++){
            dp[i]=i+1;
        }
        for(int i=0;i<n;i++){
            int limit=Math.min(n-1, i+6);

            for(int j=i+3;j<=limit;j++){
                dp[j]=Math.max(dp[j], (j-i-1)*dp[i]);
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        // Đề bài:
        //Imagine you have a special keyboard with the following keys:
        //+ A: Print one 'A' on the screen.
        //+ Ctrl-A: Select the whole screen.
        //+ Ctrl-C: Copy selection to buffer.
        //+ Ctrl-V: Print buffer on screen appending it after what has already been printed.
        //- Given an integer n
        //* Return the maximum number of 'A' you can print on the screen with at most n presses on the keys.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //- 1 <= n <= 50
        //
        //- Brainstorm
        //- Ta có 4 lựa chọn
        //- Assume dp[i] : Maximum the number of characters we can get from (i) key
        //  + ith key
        //- Because We don't know about (the previous step) that We have used to perform
        //  + dp[4]: 0,1,2,3 which is the index of each types
        //  0 : print A
        //  1 : Control + A
        //  2 : Control + C
        //  3 : Control + V
        //Ex:
        //Input: n = 7
        //Output: 9
        //dp[0][0]=1
        //dp[0][1]=0
        //dp[0][2]=0
        //dp[0][3]=0
        //- 3rd (Control+V) : We can ("paste" multiple times) but We need to perform (control + A) before
        //  + At each time : The result will be added the previous value of:
        //   + The number of character that we have performed (control + A)
        //Ex:
        //A,Ctrl+A,Ctrl+A,[Ctrl+C] : [Ctrl+C] will get the value of [the nearest (Ctrl+A)]
        //- 1st (Control+A): We assign the value as before
        //- 2nd (Control+C): We assign the value as before
        //- print A: We will add the value to 1
        //  + and ("COPY" all value of (1,2,3) position))
        //
        //dp[i][j] là gì?
        //- i là số thứ tự action
        //- j là kiểu ta sẽ chọn action
        //dp[i][j] sẽ lưu giá trị buffer cho mỗi action:
        //  Cases:
        //  + Ctrl + A : Sẽ lấy value max nhất phía trước? (Xác định ntn)
        //  + Ctrl + C sẽ lấy giá trị của (Ctrl + A ở phía trước/ Ctrl+C ) <> thì không có (Ctrl + A/ Ctrl+C) phía trước thì nó vô nghĩa
        //  + Ctrl + V sẽ lấy giá trị của Ctrl + C ở phía trước <> thì không có (Ctrl + C) phía trước thì nó vô nghĩa
        //  + Print A ==> Sẽ lấy value gì?
        //  ==> Sẽ lấy value max nhất phía trước cho đến ith.
        //- Giá trị max nhất cho đến ith tính ntn?
        //  + Perform 0th action: Print A
        //  + Perform (Ctrl+V) action: Print A
        //- Vấn đề là muốn thực hiện (Ctrl+V), ta cần:
        //  + (Ctrl+A, Ctrl+C) đằng trước.
        //
        //- Nếu ta chia làm 3 loại 0,1,2,3
        //+ dp[i][0]: là max value cho đến ith chẳng hạn
        //  + dp[i][1/2/3] sẽ không thể kết hợp với 0
        //  Tức là dp[i+1][0] không thể tính bằng cách kết hợp các value trước đấy ==> Vì CHÚNG (KHÔNG THỂ XẢY RA CÙNG LÚC).
        //
        //- Bỏ idea bên trên, ta hướng đến:
        //+ dp[i]: là max letter We can get at ith position
        //
        //- Assume with ith position:
        //- We will execute (Ctrl + A -> Ctrl -> C + Ctrl + V) consecutively
        //  + dp[i]=max(dp[i-1]+1, dp[i-3]*2)
        //- If We do the (Ctrl + V) multiple times:
        //Ex:
        //n=7
        //A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V : rs = 9
        //A, A, A, A, Ctrl A, Ctrl C, Ctrl V : rs = 8
        //Ex:
        //n=9
        //A, A, A, Ctrl A, Ctrl C, Ctrl V [6], Ctrl A, Ctrl C, Ctrl V [12] : rs=12
        //A, A, A, Ctrl A, Ctrl C, Ctrl V [6], Ctrl V[9], Ctrl V[12], Ctrl V [15] : rs=12
        //n=12
        //A, A, A, Ctrl A, Ctrl C, Ctrl V [6], Ctrl V[9], Ctrl V[12], Ctrl V [15], Ctrl V [18], Ctrl V [21], Ctrl V [24] : rs=24
        //A, A, A, Ctrl A, Ctrl C, Ctrl V [6], ( Ctrl A, Ctrl C, Ctrl V [12] ), ( Ctrl A, Ctrl C, Ctrl V [24] ) : rs=24
        //A, A, A, Ctrl A, Ctrl C, ( Ctrl V [6], Ctrl V[9], Ctrl V[12], Ctrl V [15] ), ( Ctrl A, Ctrl C, Ctrl V [30] ) : rs=30
        //
        //- Press the Ctrl+V multiple times in freedome?
        //  + Whether we have the limitation
        //- Assume we have m presses and length=l
        //  + press Ctrl+ A, C, V ==> length=2*l, presses=m+3
        //  + press Ctrl + V ==> length=3*l, presses=m+4, buffer=l
        //  + press Ctrl + V ==> length=4*l, presses=m+5, buffer=l
        //  + press Ctrl + V ==> length=5*l, presses=(m+6), buffer=l
        //  + press Ctrl + V ==> length=6*l, presses=m+7, buffer=l
        //  + press Ctrl + V ==> length=7*l, presses=m+8, buffer=l
        //  + press Ctrl + V ==> length=8*l, presses=(m+9), buffer=l
        //  => We can have a length : (k*l) with (m+k+1) presses vs (k>=2)
        //
        //- If we do Ctrl+(A/C/V) instead:
        //- Assume we have m presses and length=l
        //  + press Ctrl+ A, C, V ==> length=2*l, presses=m+3, buffer=l
        //  + press Ctrl+ A, C, V ==> length=4*l, presses=m+6, buffer=2*l ==> (Cùng có 4*l và tốn m+6 presses) .
        //  + press Ctrl+ A, C, V ==> length=8*l, presses=m+9, buffer=4*l
        //  + press Ctrl+ A, C, V ==> length=16*l, presses=m+12, buffer=8*l
        //  + press Ctrl+ A, C, V ==> length=32*l, presses=m+15, buffer=16*l
        //  => We can have a length : (k*l) with (m+k+1) presses vs (k>=2)
        //
        //- Target:
        //- Chứng mình rằng việc press K times (Ctrl+V) ==> Tệ hơn so với việc press (K-3 times) (Ctrl+V) + (Ctrl A+C+V)
        //+ length=k*l, presses=m+k+1
        //+ Lùi 3 steps trước đó:
        //  + length=(k-3)*l, presses=m+k-2
        //  + Ta thực hiện (Ctrl A+C+V): length= 2*(k-3)*l, presses= m+k+1
        //
        //- Assume:
        //2*(k-3)*l > k*l
        //<=> k*l > 6*l
        //==> k > 6 ==> Thì sẽ xảy ra trường hợp này
        //==> Ta sẽ chỉ press (MAX= 6 lần) (Ctrl+V) trước khi thực hiện (Ctrl A + C + V)
        //- Nếu không đủ 3 ==> Ta sẽ thực hiện (Ctrl+V) đến cuối
        //dp[i] : max length ta có thể lấy
        // + i là số lần được press
        //- Phần Ctrl+V biểu diễn ntn:
        //  + Công thức : Cho (bottom-up) ==> i tính cho (i+k)
        //  (j=i+3;j<min(n,i+6);j++)
        //    + dp[j]= (j-i-1)*dp[i]
        //
        //- Init dp[i]=i=1 : Vì cùng lắm thì mỗi step ta sẽ add thêm 1 character
//        int n=3;
        //dp[0],dp[1],dp[2] ==> gen ra các số:
        //+ dp[3] : copy+ paste từ dp[0] hoặc là print key A 3 times
        //- Việc (J : i+3 => i+6) ==> Tính theo (i)
        //  -> để tính cái việc cho việc (Ctrl + V) liên tục tối ưu.
        //==> nếu nó tối ưu rồi ==> Ta sẽ sử dụng lại các value đó ==> Tính các value tiếp (i+3 -> i+6)
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n*6)
        //
        //#Reference:
        //1510. Stone Game IV
        //2466. Count Ways To Build Good Strings
        //115. Distinct Subsequences
        //
        int n=7;
        System.out.println(maxAWrong(n));
        System.out.println(maxA(n));
        //
    }
}
