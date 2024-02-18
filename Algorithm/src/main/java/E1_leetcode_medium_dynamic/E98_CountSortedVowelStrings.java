package E1_leetcode_medium_dynamic;

public class E98_CountSortedVowelStrings {

    public static int countVowelStrings(int n) {
//        System.out.println('u'-'a');
        int[][] dp=new int[n][5];
        //a, e, i, o, u
        dp[0][0]=1; //1
        dp[0][1]=1; //2
        dp[0][2]=1; //3
        dp[0][3]=1; //4
        dp[0][4]=1; //5
//        char[] arr=new char[]{'a','e','i','o','u'};
        //00
        //01
        //02
        //12

        for(int i=1;i<n;i++){

            for(int j=0;j<5;j++){
                int curNum=0;
                for(int h=0;h<=j;h++){
                    curNum+=dp[i-1][h];
                }
                dp[i][j]=curNum;
            }
        }
        int rs=0;
        for(int i=0;i<5;i++){
            rs+=dp[n-1][i];
        }
        return rs;
    }

    public int countVowelStringsOptimization(int n) {
        int[][] dp=new int[n][5];
        //a, e, i, o, u
        dp[0][0]=1; //1
        dp[0][1]=1; //2
        dp[0][2]=1; //3
        dp[0][3]=1; //4
        dp[0][4]=1; //5
//        char[] arr=new char[]{'a','e','i','o','u'};

        for(int i=1;i<n;i++){
            int curNum=0;

            for(int j=0;j<5;j++){
                curNum+=dp[i-1][j];
                dp[i][j]=curNum;
            }
        }
        int rs=0;
        for(int i=0;i<5;i++){
            rs+=dp[n-1][i];
        }
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Given an integer n,
        //* return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
        //Input: n = 2
        //Output: 15
        //Explanation: The 15 sorted strings that consist of vowels only are
        //["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
        //Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= n <= 50
        //
        //- Brainstorm
        //Sorted:
        //a, e, i, o, u
        //- dp['a'][j] là số lượng chuỗi kết thúc bởi 'a' + có (j+1) ký tự
        //<=> 0,1,2,3,4
        //- Ta sẽ làm cách tối ưu
        //=====
        //01 => + dp[len=0][end=0]
        //02 => + dp[len=0][end=0] ==> Ta có thể dùng lại từ dp[len=1][end=1] (Bên trên)
        //  + 1 vòng for bên trong là được
        //12 => + dp[len=0][end=1]
        //
        //1.1, Optimization
        //- Time : O(4*4*N) --> O(4*N)
        //
        //1.2, Complexity
        //- Space : O(4*N) = O(N)
        //- Time : O(4*N) = O(N)
        //
        //#Reference:
        //2927. Distribute Candies Among Children III
        //2172. Maximum AND Sum of Array
        //518. Coin Change II
        System.out.println(countVowelStrings(2));
    }
}
