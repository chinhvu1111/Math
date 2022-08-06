package leetcode_medium_dynamic;

public class E46_MinimumASCIIDeleteSumForTwoStrings {

    public static int minimumDeleteSum(String s1, String s2) {
        int n=s1.length();
        int m=s2.length();
        int dp[][]=new int[n+1][m+1];
        int sum=0;

        for(int i=0;i<n;i++){
            sum+=s1.charAt(i);
        }
        for(int i=0;i<m;i++){
            sum+=s2.charAt(i);
        }
        int rs=sum;

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(s1.charAt(i-1)==s2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1]+ s1.charAt(i-1);
                    rs=Math.min(rs, sum-dp[i][j]*2);
                }else{
                    dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return rs;
    }

    public static int minimumDeleteSum1(String s1, String s2) {
        int m = s1.length(), n = s2.length(), MAX = Integer.MAX_VALUE;
        char[] a = s1.toCharArray(), b = s2.toCharArray();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = m; i >= 0; i--) {
            for (int j = n; j >= 0; j--) {
                if (i < m || j < n)
                    dp[i][j] = i < m && j < n && a[i] == b[j] ?
                            dp[i + 1][j + 1] : Math.min((i < m ? a[i] + dp[i + 1][j] : MAX), (j < n ? b[j] + dp[i][j + 1] : MAX));
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
//        String s="delete";
//        String s1="leet";
        String s="kslcclpmfd";
        String s1="guvjxozrjvzhe";
        //Ở đây nhắc ta nhớ đến biểu thức nếu s(i) != s1(j) thì:
        //dp[i][j]=Math.max(dp[i-1][j], dp[i][j-1]);
        //Ở đây chậm hơn cách trên 2ms vì ta đang làm ngược
        System.out.println(minimumDeleteSum(s, s1));
        //Làm xuôi thì thì chỉ cần return dp[n][m] là xong (không cần rs)
        //Kinh nghiệm với những bài basic như thế này --> Không cần rs
        System.out.println(minimumDeleteSum1(s, s1));
    }
}
