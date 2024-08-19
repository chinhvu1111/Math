package E1_leetcode_medium_dynamic;

public class E168_DecodeWaysII {

    public static int numDecodings(String s) {
        int n = s.length();
        long[] dp=new long[n+1];
        long mod = 1_000_000_007;

        dp[0]=1;
        if(s.charAt(0)=='*'){
            dp[1]=9;
        }else if(s.charAt(0)!='0'){
            dp[1]=1;
        }
        //- Xét đến ký tự thứ (i):
        //- s[i-1]!=*:
        //  + s[i] kết hợp với s[i-1] <= 2:
        //      + Thành 1 character :
        //      + s[i]=*:
        //          + s[i-1]==1
        //              dp[i]+=9*dp[i-2]
        //          + s[i-1]==2
        //              dp[i]+=6*dp[i-2]
        //  + s[i] đứng 1 mình + không kết hợp được với (i-1) (s[i-1]>=3)
        //      + s[i]=*:
        //          + s[i-1]>=3
        //              dp[i]+=9*dp[i-1]
        //- s[i-1]==*:
        //- Có 2 case:
        //  + *x
        //  + **
        //- *x
        //  + (s[i]!=*)
        //  - x<=6:
        //      + dùng 2 digit
        //          + dp[i]+=(10 -> 26)*dp[i-2]
        //  - Chỉ x>6 (Dùng 1 digit - Cộng thêm vào bên trên):
        //      + dp[i]+=dp[i-1]
        //- **:
        //  (s[i]==*)
        //  + 11 -> 99
        //      + dp[i]+=(10 -> 26)*dp[i-2]
        //      + dp[i]+=dp[i-1]
        for(int j=2;j<=n;j++){
            int i = j-1;
            if(s.charAt(i-1)!='*'){
                if(s.charAt(i-1)<='2'){
                    //2 digit
                    if(s.charAt(i)=='*'&&s.charAt(i-1)=='1'){
                        dp[j]=(dp[j]+(9*dp[j-2])%mod)%mod;
                    }else if(s.charAt(i)=='*'&&s.charAt(i-1)=='2'){
                        dp[j]=(dp[j]+(6*dp[j-2])%mod)%mod;
                    }
                    //1 digit
                    if(s.charAt(i)=='*'){
                        dp[j]=(dp[j]+9*dp[j-1]%mod)%mod;
                    }else{
                        //16
                        //26
                        //2 digits (fix)
                        if((s.charAt(i-1)=='1'&&s.charAt(i)<='9')||(s.charAt(i-1)=='2'&&s.charAt(i)<='6')){
                            dp[j]=(dp[j]+dp[j-2])%mod;
                        }
                        //1 digit + số !=0
                        //  + Có thể đứng single
                        if(s.charAt(i)!='0'){
                            dp[j]=(dp[j]+dp[j-1])%mod;
                        }
                    }
                }else{
                    //s.charAt(i-1)>=3
                    if(s.charAt(i)=='*'){
                        dp[j]=(dp[j]+9*dp[j-1]%mod)%mod;
                    }else if(s.charAt(i)!='0'){
                        //(x>=3)y
                        dp[j]=(dp[j]+dp[j-1]%mod)%mod;
                    }
                }
            }else{
                if(s.charAt(i)!='*'){
                    //*x:
                    //+ 1x -> 2x
                    //  + 17
                    //Use 2 digit
                    if(s.charAt(i)<='6'&&s.charAt(i)!='0'){
                        dp[j]=(dp[j]+2*dp[j-2]%mod)%mod;
                    }else if(s.charAt(i)>'6'&&s.charAt(i)!='0'){
                        //*(7->9)
                        //==> 1(7->9)
                        dp[j]=(dp[j]+dp[j-2]%mod)%mod;
                    }
                    //Use 1 digit
                    //*0
                    if(s.charAt(i)!='0'){
                        dp[j]=(dp[j]+dp[j-1])%mod;
                    }else{
                        dp[j]=(dp[j]+2*dp[j-2])%mod;
                    }
                }else{
                    //**:
                    //  + Vì là * nên bỏ 0
                    //+ 11 -> 99
                    //      + dp[i]+=(10 -> 26)*dp[i-2]
                    //      + dp[i]+=dp[i-1]
                    dp[j]=(dp[j]+15*dp[j-2]%mod)%mod;
                    //Case:
                    //** ==> 9*dp[j-1]
                    dp[j]=(dp[j]+9*dp[j-1])%mod;
                }
            }
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {
        //** Requirement
        //- A message containing letters from A-Z can be encoded into numbers using the following mapping:
        //  + 'A' -> "1"
        //  + 'B' -> "2"
        //...
        //  + 'Z' -> "26"
        //- To decode (an encoded message), (all the digits) must be grouped then mapped back into letters
        // using (the reverse of the mapping) above (there may be multiple ways).
        //  + For example, "11106" can be mapped into:
        //      "AAJF" with the grouping (1 1 10 6)
        //      "KJF" with the grouping (11 10 6)
        //* Note that the grouping (1 11 06) is invalid because
        // + "06" cannot be mapped into 'F' since "6" is different from "06".
        //- In addition to the mapping above, an encoded message may contain the '*' character,
        // which can represent any digit from '1' to '9' ('0' is excluded).
        // For example, the encoded message "1*" may represent any of the encoded messages
        // "11", "12", "13", "14", "15", "16", "17", "18", or "19".
        // Decoding "1*" is equivalent to decoding any of the encoded messages it can represent.
        //- Given a string s consisting of digits and '*' characters,
        //* Return (the number of ways) to decode it.
        //- Since the answer may be very large, return it modulo 10^9 + 7.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 10^5
        //s[i] is a digit or '*'.
        //
        //- Brainstorm
        //Ex
        //s = 11106
        //+ dp[i]:
        //  + Chỉ số cách decode cho đến vị trí thứ (i)
        //- Vì ta chỉ xét đến 2 chữ số:
        //  + Xét đến vị trí thứ (i):
        //      + i có thể tính 2 vị trí:
        //          + i+1
        //              + Ghép 1 ký tự
        //          + i+2
        //              + Ghép ký tự đôi
        //  + Sau đó đến (i+1) xét tương tự là được
        //- Ghép 1 ký tự khi:
        //  + x= 1 -> 9
        //====> WRONG
        //
        //- Ở đây là map digits --> characters
        //** NOTE:
        //- * character <=> 1 -> 9
        //- Xét đến ký tự thứ (i):
        //- s[i-1]!=*:
        //  + s[i] kết hợp với s[i-1] <= 2:
        //      + Thành 1 character :
        //      + s[i]=*:
        //          + s[i-1]==1
        //              dp[i]+=9*dp[i-2]
        //          + s[i-1]==2
        //              dp[i]+=6*dp[i-2]
        //  + s[i] đứng 1 mình + không kết hợp được với (i-1)
        //      + s[i]=*:
        //          + s[i-1]>=3
        //              dp[i]+=9*dp[i-1]
        //- s[i-1]==*:
        //- Có 2 case:
        //  + *x
        //  + **
        //- *x
        //  - x<=6:
        //      + dp[i]+=(10 -> 26)*dp[i-2]
        //  - x>6:
        //      + dp[i]+=dp[i-1]
        //- **:
        //  + 11 -> 99
        //      + dp[i]+=(10 -> 26)*dp[i-2]
        //      + dp[i]+=dp[i-1]
        //
        //* (**) Không có 10:
        //  + Vì 0 bị excluded
//        String s = "2*";
//        String s = "1*";
//        String s = "1106*";
        //11 --> 99
        //+ 11 -> 19 -> 21 -> 26 = 17 - 2 = 15
        //  +
        //+ 9*9
//        String s = "**";
//        String s = "106*1";
        //106*:
        //  + dp[3] = 9 cases
        //106*1:
        //  + dp[4] += dp[3]=9
        //  + *1:
        //      + 11,21:
        //          dp[4]+=2
//        String s = "*1*1*0";
//        String s = "0";
//        String s = "1*72*";
        //
        //- Rất nhiều case:
        //  ==> Nên chia ra (0, *, 1 digit, 2 digits...)
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String s = "1*72";
        //#Reference:
        //1977. Number of Ways to Separate Numbers
        //2147. Number of Ways to Divide a Long Corridor
        //
        System.out.println(numDecodings(s));
    }
}
