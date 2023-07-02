package contest;

import java.util.Arrays;

public class E29_DecrementalStringConcatenation {

    public static int[][][] dp;

    public static int solution(String[] words){
        int n=words.length;
        if(n==0){
            return 0;
        }
        if(n==1){
            return words[0].length();
        }
        int[][][] dp =new int[n][26][26];
        for(int i=0;i<n;i++){
            for(int j=0;j<=25;j++){
                for(int h=0;h<=25;h++){
                    dp[i][j][h]=Integer.MAX_VALUE;
                }
            }
        }
        String word=words[0];
        char first=word.charAt(0);
        char last=word.charAt(word.length()-1);
        dp[0][first-'a'][last-'a']=word.length();

        for(int i=1;i<n;i++){
            String currentStr=words[i];
            int firstChar=currentStr.charAt(0)-'a';
            int lastChar=currentStr.charAt(currentStr.length()-1)-'a';
            int currentLength=currentStr.length();

            for(int j=0;j<=25;j++){
                for(int h=0;h<=25;h++){
                    if(dp[i-1][j][h]!=Integer.MAX_VALUE){
                        if(h==firstChar){
                            dp[i][j][lastChar]=Math.min(dp[i][j][lastChar], dp[i-1][j][h]+currentLength-1);
                        }else{
                            dp[i][j][lastChar]=Math.min(dp[i][j][lastChar], dp[i-1][j][h]+currentLength);
                        }
                        // (j,h)(first,last)
                        //(first, last)(j,h)
                        if(j==lastChar){
                            dp[i][firstChar][h]=Math.min(dp[i][firstChar][h], dp[i-1][j][h]+currentLength-1);
                        }else{
                            dp[i][firstChar][h]=Math.min(dp[i][firstChar][h], dp[i-1][j][h]+currentLength);
                        }
//                        System.out.printf("Before %s %s %s %s\n", i-1, j, h, dp[i-1][j][h]);
//                        System.out.printf("%s %s %s %s %s\n", i, j, lastChar, dp[i][j][lastChar], currentStr);
//                        System.out.printf("%s %s %s %s %s\n", i, firstChar, h, dp[i][firstChar][h], currentStr);
                    }
                }
            }
        }
        int rs=Integer.MAX_VALUE;

        for(int i=0;i<=25;i++){
            for(int j=0;j<=25;j++){
                rs=Math.min(rs, dp[n-1][i][j]);
            }
        }
        return rs;
    }

    public static int solutionRecursion(int index, char start, char end, String[] words){
        if(index>=words.length){
            return 0;
        }
        if(dp[index][start-'a'][end-'a']!=-1){
            return dp[index][start-'a'][end-'a'];
        }
        String currentWord=words[index];
        int currentResult;
        //(start, end) currentWord
        //currentWord, (start, end)
        if(end==currentWord.charAt(0)){
            currentResult=1+solutionRecursion(index+1, start, currentWord.charAt(currentWord.length()-1), words);
        }else{
            currentResult=solutionRecursion(index+1, start, currentWord.charAt(currentWord.length()-1), words);
        }
        if(currentWord.charAt(currentWord.length()-1)==start){
            currentResult=Math.max(currentResult, 1+solutionRecursion(index+1, currentWord.charAt(0), end, words));
        }else{
            currentResult=Math.max(currentResult, solutionRecursion(index+1, currentWord.charAt(0), end, words));
        }
        return dp[index][start-'a'][end-'a']=currentResult;
    }

    public static int minimizeConcatenatedLengthBottomUp(String[] words) {
        int n=words.length;
        dp=new int[n+1][26][26];
        int total=0;

        for(int i=0;i<n;i++){
            total+=words[i].length();
        }

        for(int[][] arr:dp){
            for(int[] subArr:arr){
                Arrays.fill(subArr, -1);
            }
        }
        return total-solutionRecursion(1, words[0].charAt(0), words[0].charAt(words[0].length()-1), words);
    }

    public static int minimizeConcatenatedLength(String[] words) {
        return solution(words);
    }

    public static void main(String[] args) {
        //** Requirement:
        //- Ta có ["aaa","c","aba"]
        //- Join là phép biến đổi:
        //  + join("ab", "ba") = "aba" (char ở last sẽ bỏ đi) nếu last character giống nhau
        //- Mỗi operation ta có thể chọn:
        //  + s(trước đó) với str(i)
        //  + str(i) với s(trước đó)
        //- Tìm cách tối ưu cho str(n-1) --> Chọn so cho min length
        //
        //** Idea
        //1.
        //1.1,
        //- Dữ kiện:
        //+ Constraints:
        //+ 1 <= words.length <= 1000
        //+ 1 <= words[i].length <= 50
        //- Length(Words) <=1000 --> nếu làm theo binary, complexity 2^1000 ==> timeout
        //- Word length <=50 --> O(N^2) vẫn work
        //
        //- Brainstorm
        //VD:
        //aba, ba, ca
        //step 1:
        //+ join(aba, ba) = abab (length=3)
        //+ join(ba, aba) = baaba (length=4)
        //step 2:
        //+ join(abab, ca) = ababc (length=5)
        //+ join(ca, abab) = caabab (length=6)
        //--> Nếu đằng trước (i) chọn (deleted 0) --> có thể bù cho đằng (i+k) đằng sau chọn (deleted 1) không???
        //
        //- Với case 1 kết quả chọn không deleted --> Sẽ có lợi hơn về sau:
        //VD:
        //[abc, ca, ce, de]
        //+ Optimal way:
        //+ join(abc, ca) = bcc --> chọn deleted (a)
        //+ join(ce, bcc) = ebc
        //+ join(ebc, de) = bcd (length=5)
        //
        //+ join(ca, abc) = aab --> Chọn deleted (c)
        //+ join(ce, aab) = ceaab
        //+ join(ceaab, de) = ceaabde (length=5)
        //==> deleted nhiều nhất có thể
        //
        //- String : (first char) + (second char) + (s) + (previous last char) (last char)
        //[ (first char) + (second char) + (s) + (previous last char) (last char) ] + new string ==> Để dùng second char
        //[ new string + [ (first char) + (second char) + (s) + (previous last char) ] (last char) ==> Để dùng previous last char
        //VD:
        //[abc, ca, ce, de]
        // a, b, c
        // c, a
        // c, e
        // d, e
        //
        //a, b, a --> về đầu ==> aab
        // c, a, a, b, c --> c, a ==> về đầu bcc
        //
        //
        //- Nếu có trường hơp nào (s,s1,s2,s3)
        //+ Join(s, s1) = j1 - deleted 1
        //+ Join(s1, s)= j11 - deleted 1
        //+ Join(s2, j1)= j21
        //+ Join(j1, s2)= j12 ==> Có cùng chiều dài nhưng j2 và j22 khi kết hợp với s3 thì 1 trong 2 thẳng (không bị deleted)
        //+ Join(s2, j11)= j211
        //+ Join(j11, s2)= j112
        //* Nếu step 2 kết hợp với (s2) --> Ta sẽ chọn cases có thể deleted được nhưng sẽ có lợi nhất khi kết hợp với s3
        //
        // s: firstS, firstSS, lastSS, lastS
        // s1: firstS1, firstS11, lastS11, lastS1
        // s2: firstS2, firstS22, lastS22, lastS2
        // s3: firstS3, firstS33, lastS33, lastS3
        //
        //+ Join(s, s1) = j1 - deleted 1 ==> (firstS==lastS1) ==> (firstSS, lastSS, lastS, firstS1, firstS11, lastS11) : [firstSS, lastS11]
        //+ Join(s1, s)= j11 - deleted 0 ==> (lastS!=firstS1) ==> (firstS1, firstS11, lastS11, lastS1, firstS, firstSS, lastSS, lastS) : [firstS1, lastS]
        //+ Join(s2, j1)= j21 - deleted 0 ==> (firstS2!=lastS11) [(firstS2, firstS22, lastS22, lastS2), (firstSS, lastSS, lastS, firstS1, firstS11, lastS11) ] : [firstS2, lastS11]
        //+ Join(j1, s2)= j22 - deleted 0
        //
        //+ Join(j11, s2)= j112 - deleted 1 ==> (firstS1==lastS2) ==> (firstS11, lastS11, lastS1, firstS, firstSS, lastSS, lastS),(firstS2, firstS22, lastS22) : [firstS11, lastS22]
        //+ Join(s2, j11)= j211 - deleted 1
        //--> Đến đây length của 2 thằng vẫn như nhau trước khi kết hợp với s3
        //+ Join(j21, s3)= j112 - deleted 0
        //+ Join(s3, j21)= j211 - deleted 0
        //+ Join(j112, s3)= j1123 - deleted 1
        //+ Join(s3, j112)= j3112 - deleted 1
        //--> Lúc này thì ban đầu chọn không deleted thì sẽ tốt hơn.
        //--> Making testcase dựa trên idea này
        //
        //- Tư duy rộng ra (Thử tách nhỏ ra để dùng queue thử):
        //[abc, ca, ce, de]
        //+ Optimal way:
        //+ join(abc, ca) = bcc --> chọn deleted (a)
        //+ join(ca, abc) = aab --> chọn deleted (c) -->  Không có c để xoá
        //+ join(ce, bcc) = ebc
        //+ join(ebc, de) = bcd (length=5)
        //a,b,c,c,a
        //
        //[ca, ce, de]
        //+ (ca, ce)= cace
        //+ (ce, ca)= ceca
        //+ (cace, de) = cacede
        //+ (de, ceca) = decace
        //--> Tách ra có vẻ không được
        //
        //- Dùng dynamic programming:
        //- Bài toán là tìm min length()
        //- Thêm 1 string s vào 1 tập hợp trước đó s1 thì có thể:
        //+ join(s,s1) : compare(first(s) : last(s1))
        //+ join(s1,s) : compare(first(s1) : last(s))
        //- Tìm cách biểu diễn biểu thức trên:
        //+ Ta có min length cho đến (i-1)
        //
        //- Cần: ==> Với tư duy này thì ta sẽ gần như cover hết cases ???
        //+ Check để cut first, last
        //+ Update lại first, last mới
        // + Try: dp[i][first][last] : (first: char, last:char)
        //+ Lưu lại length mới ==> với string s --> Ta có (first, last) ==> Ta có thể check all các (i-1) tìm ra thằng có first=last hoặc sao đó
        //
        //** Nhắc lại phần đề bài (HIỂU SAI ĐỀ BÀI) : join('ab', 'ba') = ab + ba = aba ==> nếu last = first --> Bỏ đi 1 ký tự
        //==> Nếu tư duy như trên ab+ba = ab ==> Sai (khi bỏ last character của string 2)
        //
        //- dp[i][firstChar][lastChar] : Là chiều dài min string khi join đến vị trí i với ký tự first là (firstChar) và last (lastChar)
        //VD:
        //Ta có new string : s
        //current_first và current_last
        //+ join(s, s1)
        //dp[i][current_first] [1 --> 26] = dp[i-1][1 --> 26][1 --> 26] + length (Nếu current_first != last)
        //<> dp[i][current_second] [1 --> 26] = dp[i-1][1 --> 26][1 --> 26] + length (Nếu current_first == last)
        //+ join(s, s1)
        //dp[i][ 1--> 26 ] [current_first] = dp[i-1][1 --> 26][1 --> 26] + length --> Tuỳ
        //==> Với dạng dynamic programming kiểu này ta có thể dùng recursion
        //VD: [abc, ca, ce, de]
        //'abc', 'ca', 'ce', 'de'
        //+ i=0 : str='abc'
        //dp[0]['a']['c']=2
        //+ i=1 : str='ca'
        //'abc'+ 'ca' = abca : dp[1]['a']['a'] ==> dp[1]['b']['c']=4 (bcc)
        //'ca' + 'abc' = cabc : dp[1]['c']['c'] ==> dp[1]['a']['b']=4 (aab)
        //+ i=2 : str='ce'
        //'abca'+ 'ce' = abcace : dp[2]['a']['e'] ==> dp[2]['a']['e']=6
        //'ce' + 'abca' = ceabca : dp[2]['c']['a'] ==> dp[2]['c']['a']=5
        //'cabc'+ 'ce' = cabce : dp[2]['c']['e'] ==> dp[2]['c']['e']=5
        //'ce' + 'aab' = ceaab : dp[2]['c']['b'] ==> dp[2]['c']['b']=5
        //Các cases:
        //- (j,h)(first,last)
        //- (first, last)(j,h)
        //
        //- Special cases
        //+ {a,b,b,cb} :
        //
        //1.2, Complexity:
        //- Time complexity : O(n*26*26)
        //- Space complexity : O(n*26*26)
        //1.3, How to implement by using recursion method
        //- Dùng dạng để quy có nhớ (memoziation)
        //+ dp[n][j][h] : Lưu thông tin số ký tự có thể xoá đi được tương tự như trên:
        //- (j,h)(first,last)
        //- (first, last)(j,h)
        //
//        String[] words={"aa","ab","bc"};
//        String[] words={"a","b","b","b"};
//        String[] words={"aa","a","b","b"};
//        String[] words={"a","c","b","d"};
        String[] words={"ab","acb"};
        //str1 = join(str0, "ab") = "aab"
        //str2 = join(str1, "bc") = "aabc"
        System.out.println(minimizeConcatenatedLength(words));
        System.out.println(minimizeConcatenatedLengthBottomUp(words));
        //#Reference:
        //1754. Largest Merge Of Two Strings
    }
}
