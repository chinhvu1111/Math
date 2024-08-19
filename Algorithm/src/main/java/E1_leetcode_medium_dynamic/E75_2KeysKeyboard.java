package E1_leetcode_medium_dynamic;

import java.util.Arrays;

public class E75_2KeysKeyboard {

    public static int minStepsWrong(int n) {

        int dp[]=new int[n+1];
        int copy[]=new int[n+1];
        copy[1]=1;
        dp[1]=1;

        for(int i=2;i<=n;i++){
            int beforeValue=-1;
            int secondBefore=-1;

            if(i>=1){
                beforeValue=dp[i-1]+copy[i-1];
            }
            if(i>=2){
                secondBefore=dp[i-2]*2;
            }
            if(beforeValue>secondBefore){
                dp[i]=beforeValue;
                copy[i]=copy[i-1];
            }else{
                dp[i]=secondBefore;
                copy[i]=dp[i-2];
            }
        }
        return dp[n];
    }

    public static int minSteps(int n) {
        //1, dp [i][j] : Thông tin copy
        //+ i : Chí số lượng ký tự 'A'
        //+ j : Chỉ số số lượng ký có thể copy
        //Nếu dp[i][j]!=0 : Tức là ở 'A' ký tự (i) có thể copy (j) ký tự 'A'.
        //
        //2, step[i][j] : Chí số lượng step khi có (i) ký tự 'A' + copy = (j) tức là copy lúc đó sẽ = j.
        if(n==1){
            return 0;
        }
        int[][] dp =new int[n+1][n+1];
        int[][] steps =new int[n+1][n+1];

        dp[1][1]=1;
//        steps[1]=1;

        for(int i=2;i<=n;i++){
            dp[i][i]=1;
            //All giá trị trước (i)
            int min=Integer.MAX_VALUE;

            for(int j=i-1;j>=1;j--){
                //All copy của (j)
//                for(int h=1;h<=j;h++){
//                    if(dp[j][h]==1&&j+h==i){
//                        dp[i][h]=1;
//                        min=Math.min(min, steps[j]+1);
//                        System.out.printf("%s %s %s\n", j, h ,i);
//                    }
//                }
                if(dp[j][i-j]!=0){
                    dp[i][i-j]=1;
                    int expStep=1;

                    if(i==j*2){
                        expStep=2;
                        steps[i][j]=steps[j][i-j]+expStep;
                        min=Math.min(min, steps[i][j]);
                    }else{
                        steps[i][i-j]=steps[j][i-j]+expStep;
                        min=Math.min(min, steps[i][i-j]);
                    }
//                    System.out.printf("%s %s %s\n", j, i-j ,i);
                }
            }
            if(min!=Integer.MAX_VALUE){
                steps[i][i]=min;
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){
            if(steps[n][i]!=0){
                rs=Math.min(steps[n][i], rs);
            }
        }
        return rs;
    }

    public static int minStepsOptimization(int n) {
        //dp [i][j] : Thông tin copy
        //+ i : Chí số lượng ký tự 'A'
        //+ j : Chỉ số số lượng ký có thể copy
        //Nếu dp[i][j]!=0 : Tức là ở 'A' ký tự (i) có thể copy (j) ký tự 'A'.
        if(n==1){
            return 0;
        }
        int[][] steps =new int[n+1][n+1];

        for(int i=0;i<=n;i++){
            Arrays.fill(steps[i], -1);
        }
//        int[][] steps =new int[n+1][n+1];

        steps[1][1]=0;

        for(int i=2;i<=n;i++){
            steps[i][i]=0;
            //All giá trị trước (i)
            int min=Integer.MAX_VALUE;

            for(int j=i-1;j>=1;j--){
                //All copy của (j)
//                for(int h=1;h<=j;h++){
//                    if(dp[j][h]==1&&j+h==i){
//                        dp[i][h]=1;
//                        min=Math.min(min, steps[j]+1);
//                        System.out.printf("%s %s %s\n", j, h ,i);
//                    }
//                }
                if(steps[j][i-j]!=-1){
                    steps[i][i-j]=0;
                    int expStep=1;

                    if(i==j*2){
                        expStep=2;
                        steps[i][j]=steps[j][i-j]+expStep;
                        min=Math.min(min, steps[i][j]);
                    }else{
                        steps[i][i-j]=steps[j][i-j]+expStep;
                        min=Math.min(min, steps[i][i-j]);
                    }
//                    System.out.printf("%s %s %s\n", j, i-j ,i);
                }
            }
            if(min!=Integer.MAX_VALUE){
                steps[i][i]=min;
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){
            if(steps[n][i]!=0&&steps[n][i]!=-1){
                rs=Math.min(steps[n][i], rs);
            }
        }
        return rs;
    }

    public static int minStepsOptimize1(int n) {
        int ans=0;

        for(int i=2;i*i<=n;){
            if(n%i==0){
                ans+=i;
                n=n/i;
            }else{
                i++;
            }
        }

        if(n!=1){
            ans+=n;
        }
        return ans;
    }

    public int minStepsRecursion(int n) {
        if(n==1) return 0;

        for(int i=n/2;i>=2;i--){
            if(n%i==0){
                return minStepsRecursion(i)+minStepsRecursion(n/i); //bigger the factor, fewer will be the number of operations.
            }
        }

        return n; // for prime numbers there won't be any factors so default case of copying 'A' and pasting 'A' (n-1) times, a total of n operations.
    }

    public static int minStepsOptimizeDynamic(int n) {
        int[] dp = new int[n+1];

        for(int i=2;i<=n;i++){
            dp[i]=i;
            for(int j=i/2;j>=1;j--){
                if(i%j==0){
                    dp[i]=dp[j]+(i/j);
                }
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        int n=3;
//        int n=1;
//        int n=4;
//        int n=6;
//        int n=7;
        System.out.println(minStepsWrong(n));
        System.out.println(minSteps(n));
        System.out.println(minStepsOptimization(n));
        //
        //* Requirement:
        //- There is only one character 'A' on the screen of a notepad.
        // You can perform one of two operations on this notepad for each step:
        //  + Copy All:
        //      + You can copy all the characters present on the screen (a partial copy is not allowed).
        //  + Paste:
        //      + You can paste the characters which are copied last time.
        //- Given an integer n,
        //* return (the minimum number of operations) to get the character 'A' (exactly n times) on the screen.
        //
        //
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Ta sẽ quy hoạch động theo 2 giá trị :
        //- dp [i][j] : Thông tin copy
        //+ i : Chí số lượng ký tự 'A'
        //+ j : Chỉ số số lượng ký có thể copy
        //Nếu dp[i][j]!=0 : Tức là ở 'A' ký tự (i) có thể copy (j) ký tự 'A'.
        //
        //- step[i][j] : Chí số lượng step khi có (i) ký tự 'A' + ( số ký tự copy = (j) ) tức là copy lúc đó sẽ = (j) .
        //2, Với mỗi (i) ký tự A ta có thể tạo từ rất nhiều các ký tự đằng trước:
        //VD:
        //0 : A
        //1: Copy
        //2: AA
        //3: AAA
        //4: Copy
        //5: AAAAA
        //step[6][3]=5 (Số lượng step)
        //dp[6][3]=1 (Thể hiện việc có thể copy (3) tại vị trí (i==6))
        //2.1, Ở (i==6) ta có thể copy rất nhiều các value khác nhau:
        //VD:
        //0 : A
        //1: Copy
        //2: AA
        //3: AAA
        //4: AAAA
        //5: AAAAA
        //dp[6][1]=1, step[6][1]=6
        //2.2, Lúc đầu tư duy --> O(n^3)
        //- Khi ta for với mỗi ( i : n ) --> (j cái trước : n ) + check (all các copy của j : n)
        //==> Ta có thể giảm số vòng lặp đi (n) lần khi check:
        //dp[i][i-j]!=0 --> Tức là từ (j) có thể nhảy sang (i)
        //+ j là số ký tự 'A' (Trước đó)
        //+ i là số ký tự 'A' (Hiện tại)
        //--> copy lúc này = i-j ==> Nên ta chỉ càn check dp[i][i-j]
        //3, Trường hợp đặc biệt:
        //
        //3.1, Với case: i==6
        //+ Nếu ta chỉ khởi tạo dp[3][1] : Tức là chỉ tính theo các giá trị trước đó.
        //---> Sẽ bị sai case (i==6)
        //Khi dp[6][3] --> Được tính theo dp[3][3]
        //+ Ta cần gán giá trị cho dp[3][3]
        //+ Giá trị của dp[3][3] là bao nhiêu?
        //--> dp[3][3] là lúc ta sẽ đứng từ (j==3) --> move đến (i==3)
        //** Việc ( copy ==3 ) đó xảy ra sau khi ta nhận được chuỗi có số ký tự ('A'==3)
        //==> Lúc này dp[3][3] --> Không phụ thuộc vào các giá trị trước đó
        //dp[3][3]=min(dp[3][i])=3;
        //VD
        //step 3: AAA
        //step 4: Copy (Đoạn này sau)
        //step 5: AAAAAA
        //
        //3.2, Ngoài case:
        //- dp[i][i-j]=dp[j][i-j]+1;
        //+ Case copy all:
        //- dp[i][i-j] = dp[j][i-j] + 2
        //---> Tức là
        //VD:
        //0 : A
        //1: Copy
        //2: AA
        //3: AAA
        //4: Copy
        //5: AAAAA
        //steps[6][3]=steps[3][3] + 2 (Vì nó bao gồm 2 bước copy và paste)
        //
        //3.3, Chú ú: Ở bài toán này sẽ xuất hiện step đầu tiên : step 0 = A.
        //
        //4, Tối ưu:
        //4.1, Ở đây ta có thể bỏ 1 array đi (dp)
        //--> Vì nó đóng vài trò tương đương với step.
        //4.2, Ta có thể bỏ dimension copy[i] nếu dựa trên tư duy như sau:
        //+ f(i) = f(j) + f(i/j)    //  if (i%j == 0) for the largest j between 2 to i/2
        //+ f(i) = i                // if(i%j != 0) for any j between 2 to i/2
        //4.3, Ta sẽ phân bài này thành 2 trường hợp:
        //- Các số có thể chia hết cho 1 số (j) nào đó
        //- Các số không chia hết cho số (j) (Số đó có thể là số lẻ vì số lẻ có thể vẫn sẽ chia hết)
        //--> Khởi tạo dp[i]=i.
        //Giải thích:
        //- Các số có thể chia hết cho (j)
        //VD:
        //+ (i==3) --> (i==6) --> (i==9) --> (i==12)
        //+ (i=12) --> Nếu tính theo (i==9) thì chỉ có thể tính theo được (i==3) (COPY trong trường hợp này ==3)
        //--> Lúc đó dp[12] --> Sẽ phải tính theo dp[3] với (COPY==3) ---> Chạy dần dần.
        //===> Việc tính theo (9) lúc đó sẽ được quy về việc tính theo (j==3)
        //+ (i==12) --> Nếu tính theo (i==6) thì không phải tính theo (i==3 && copy==3) nữa
        //---> Ta có thể quy về copy toàn bộ (6) + paste value ==12.
        //---> Lúc này copy==6
        //===> dp[12]=d[6]+2 (2 bước cppy + paste)
        //** Nhưng không phải lúc nào cũng gấp đôi
        //===> Có thể gấp N lần
        //**, CT : dp[24] = dp[6] + (24/6) (Số lần copy 6)
        //
        //4.4, Ta chỉ cần tìm điểm gần (i) nhất là được:
        //if(i%j==0){
        //break;
        //}
        //===> Vì điểm đó sẽ là điểm cho kết quả tốt nhất.
        //
        //Cách 2:
        //

        System.out.println(minStepsOptimizeDynamic(n));
        System.out.println(minStepsOptimize1(n));

    }
}
