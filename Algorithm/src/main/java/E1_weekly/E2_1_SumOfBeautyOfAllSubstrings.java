package E1_weekly;

public class E2_1_SumOfBeautyOfAllSubstrings {

    public static int beautySum(String s) {
        int n=s.length();
        int[][] prefix=new int[n+1][26];

        for(int i=1;i<=n;i++){
            for(int j=0;j<26;j++){
                prefix[i][j]=prefix[i-1][j];
            }
            prefix[i][s.charAt(i-1)-'a'] = prefix[i-1][s.charAt(i-1)-'a'] + 1;
        }
        int rs=0;

        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int maxFreq=0;
                int minFreq=Integer.MAX_VALUE;

                for(int h=0;h<26;h++){
                    if(prefix[j+1][h]==0){
                        continue;
                    }
                    maxFreq=Math.max(maxFreq, prefix[j+1][h]-prefix[i][h]);
                    if(prefix[j+1][h]-prefix[i][h]!=0){
                        minFreq=Math.min(minFreq, prefix[j+1][h]-prefix[i][h]);
                    }
                }
//                System.out.printf("i:%s, j:%s, val=%s\n", i, j, maxFreq-minFreq);
                rs+=maxFreq-minFreq;
            }
        }
        return rs;
    }

    public static int beautySumOptimization(String s) {
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            int[] freq=new int[26];

            for(int j=i;j<n;j++){
                freq[s.charAt(j)-'a']++;
                int minFreq=Integer.MAX_VALUE;
                int maxFreq=0;

                for(int h=0;h<26;h++){
                    if(freq[h]>0){
                        minFreq=Math.min(minFreq, freq[h]);
                        maxFreq=Math.max(maxFreq, freq[h]);
                    }
                }
                rs+=maxFreq-minFreq;
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- The beauty of a string is the (difference) in frequencies between (the most frequent) and (least frequent characters).
        //For example, the beauty of "abaacc" is 3 - 1 = 2.
        //Given a string s,
        //* Return (the sum of beauty) of (all of its substrings).
        //* Sum cái diff bên trên cho all of substrings.
        //
        //Example 1:
        //Input: s = "aabcb"
        //Output: 5
        //Explanation:
        // The substrings with non-zero beauty are ["aab","aabc","aabcb","abcb","bcb"], each with beauty equal to 1.
        //
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint
        //1 <= s.length <= 500
        //s consists of only lowercase English letters.
        //+ Length không quá lớn --> Có thể xử lý O(n^2)
        //
        //- Brainstorm
        //- Tính max/ min count giữa (i,j) ntn?
        //  + Tính all count của all of characters tại (i) - (j)
        //      ==> Tức là dùng prefix sum là được.
        //Ex:
        //s = "aabcb"
        //- i=3, j=2
        //  + aa(bc) : Nếu trừ đi thì với [a] ==0 : Cái này không nên được tính để xét (min/max)
        //* Note:
        //  + Case hiệu ==0 ==> Min/Max sẽ không được xét
        //      + 1 pair được gọi là valid khi có (subtraction!=0)
        //
        //1.1, Optimization
        //- Ta có thể tối ưu space từ O(n*26) -> O(n)
        //- Ta có thể thay đổi các loop đi:
        //  + Để tìm được subarray tư duy cơ bản là:
        //      + (i, j)
        //      + loop(i)
        //          + loop(j): loop luồng nhau
        //      ==> Lúc đấy để tính count thì ta phải trừ lẫn nhau
        //- Ta thấy rằng để có count:
        //  + Ta cần loop increase từ 0 --> n-1
        //-> Loop
        //- (i: 0->n-1)
        //  - (j: i-> n-1)
        //      + i lúc này sẽ ==0
        //      + j lúc này sẽ được cộng dần count lên
        //  ==> Lúc đó so sánh ta chỉ cần tạo freq temporary --> Sau đó compare là được chứ không cần trừ.
        //
        //1.2, Complexity
        //- Space: O(n*26)
        //- Time : O(n^2*26)
        //
        String s="aabcb";
        System.out.println(beautySum(s));
        System.out.println(beautySumOptimization(s));
        //#Reference:
        //594. Longest Harmonious Subsequence
        //1044. Longest Duplicate Substring
        //2029. Stone Game IX
        //2981. Find Longest Special Substring That Occurs Thrice I
        //831. Masking Personal Information
        //1110. Delete Nodes And Return Forest
    }
}
