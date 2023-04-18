package mock;

public class Test_6_amazone {

    //**Đề bài:
    //- Tìm longest palindromic substring có trong s
    //
    //** Bài này tư duy như sau:
    //1.
    //1.0, Idea
    //- Dữ kiện length=1000
    //- Để check 1 string có là palindrome hay không:
    //+ Nếu length của nó lẻ : abc ==> a==c
    //+ Nếu length của nó chẵn : ab ==> a=b
    //VD:
    //babad
    //- Bài này có thể giải với time= O(n^2)
    //+ Loop1 : length của substring
    //+ Loop2: start(i)
    //+ Dp[i][j] : Là độ dài dài nhất của palindromic trong khoảng (i,j)
    //+ init [i][i]=1
    //+ if(s[i]==s[j] && (i+1, j-1) Tồn tại đối xứng) ==> dp[i][j]=dp[i-1][j-1]+1
    //+ dp[i][j]=0
    public static String longestPalindromeSlow(String s) {
        int n=s.length();
        int rs=0;
        int dp[][]=new int[n][n];
        int start=0;
        int end=0;

        for(int i=0;i<n;i++){
            dp[i][i]=1;
        }

        for(int i=1;i<=n;i++){
            for(int j=0;j+i<n;j++){
                int k=i+j;

                if(s.charAt(j)==s.charAt(k)
                        &&(i==1 || (j<n-1&&k>0&&dp[j+1][k-1]!=0) )){
                    dp[j][k]=k-j+1;
                    if(rs<dp[j][k]){
                        rs=dp[j][k];
                        start=j;
                        end=k;
                    }
                }
            }
        }
//        System.out.println(rs);
        String rsStr=s.charAt(0)+"";
        if(rs==0){
            return rsStr;
        }
        return s.substring(start, end+1);
    }

    public static String longestPalindrome(String s) {
        int n=s.length();

        return null;
    }

    public static void main(String[] args) {
        String s="babad";
//        String s="cbbd";
        System.out.println(longestPalindrome(s));
    }
}
