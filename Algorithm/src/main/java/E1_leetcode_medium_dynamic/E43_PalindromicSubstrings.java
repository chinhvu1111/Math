package E1_leetcode_medium_dynamic;

public class E43_PalindromicSubstrings {

    public static int countSubstrings(String s) {
        int n=s.length();
        boolean[][] dp =new boolean[n][n];
        int rs=n;

        for(int i=0;i<n-1;i++){
            dp[i][i]=true;
            if(s.charAt(i)==s.charAt(i+1)){
                dp[i][i+1]=true;
                rs++;
            }
        }

        for(int i=2;i<=n;i++){
            for(int j=0;i+j-1<n;j++){
                int k=i+j-1;

                if(j+1<=k-1&&!dp[j+1][k-1]){
                    continue;
                }
                if(s.charAt(j)==s.charAt(k)&&j+1<=k-1){
                    if(j<n-1){
                        dp[j][k]=dp[j+1][k-1];
                    }
                    if(dp[j][k]){
                        rs++;
                    }
                }
            }
        }
        return rs;
    }

    public static int countSubstrings1(String s) {
        int res = 0, n = s.length();
        for(int i = 0; i<n ;i++ ){
            for(int j = 0; i-j >= 0 && i+j < n && s.charAt(i-j) == s.charAt(i+j); j++) res++; //substring s[i-j, ..., i+j]
            for(int j = 0; i-1-j >= 0 && i+j < n && s.charAt(i-1-j) == s.charAt(i+j); j++) res++; //substring s[i-1-j, ..., i+j]
        }
        return res;
    }

    public static void main(String[] args) {
//        String s="abc";
//        String s="aaa";
        String s="aaaaa";
        System.out.println(countSubstrings(s));
        //Tư duy ở đây nếu không dùng quy hoạch động như sau:
        //Ta sẽ chia ra thành 2 trường hợp:
        //1, Chiều dài chuỗi là số lẻ:
        //VD: abc, aba
        //--> For i: (0-->n-1), tính j ++ với mỗi (i) thỏa mãn:
        //i-j >= 0 && i+j < n --> Đối với chuỗi lẻ
        //2, Chiều dài chuỗi là số chẵn:
        //VD: aa, bbbb
        //i-1-j >= 0 && i+j < n --> Đối với chuỗi là số chẵn
        //(i) sẽ tính lệch 1 đơn vị nếu chẵn so với lẻ
        System.out.println(countSubstrings1(s));
    }
}
