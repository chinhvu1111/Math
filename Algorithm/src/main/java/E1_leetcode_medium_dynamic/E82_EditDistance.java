package E1_leetcode_medium_dynamic;

public class E82_EditDistance {

    public static int minDistanceRedundant(String word1, String word2) {
        if("".equals(word1)){
            return word2.length();
        }
        if("".equals(word2)){
            return word1.length();
        }
        int n=word1.length();
        int m=word2.length();
        int[][]dp=new int[n+1][m+1];
        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                dp[i][j]=Integer.MAX_VALUE;
            }
        }
        for(int i=1;i<=m;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<n;i++){
            dp[i][0]=i;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
//                    dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;

                    //Deleting character of text1
                    if(dp[i-1][j]!=Integer.MAX_VALUE){
                        dp[i][j]=dp[i-1][j]+1;
                    }
                    //Inserting character into word1 ==> (i) vẫn giữ nguyên + không biết insert thêm word nào
                    //+ Chỉ biết là ký tự cần giống là word2[j]
                    if(dp[i][j-1]!=Integer.MAX_VALUE){
                        dp[i][j]=Math.min(dp[i][j], dp[i][j-1]+1);
                    }
                    //Replacing character of word1 to other word
                    if(dp[i-1][j-1]!=Integer.MAX_VALUE
//                            &&((j!=1&&i!=1)||(i==1&&j==1))
                    ){
                        dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]+1);
                    }
                }
//                System.out.println((i)+" "+(j)+" "+dp[i][j]);
//                System.out.println(dp[i][j]);
            }
        }
        return dp[n][m];
    }

    public static int minDistance(String word1, String word2) {
        if("".equals(word1)){
            return word2.length();
        }
        if("".equals(word2)){
            return word1.length();
        }
        int n=word1.length();
        int m=word2.length();
        int[][]dp=new int[n+1][m+1];
        for(int i=1;i<=m;i++){
            dp[0][i]=i;
        }
        for(int i=1;i<n;i++){
            dp[i][0]=i;
        }

        for(int i=1;i<=n;i++){
            for(int j=1;j<=m;j++){
                if(word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j]=dp[i-1][j-1];
                }else{
//                    dp[i][j]=Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]))+1;

                    //Deleting character of text1
                    dp[i][j]=dp[i-1][j]+1;
                    //Inserting character into word1 ==> (i) vẫn giữ nguyên + không biết insert thêm word nào
                    //+ Chỉ biết là ký tự cần giống là word2[j]
                    dp[i][j]=Math.min(dp[i][j], dp[i][j-1]+1);
                    //Replacing character of word1 to other word
                    dp[i][j]=Math.min(dp[i][j], dp[i-1][j-1]+1);
                }
//                System.out.println((i)+" "+(j)+" "+dp[i][j]);
//                System.out.println(dp[i][j]);
            }
        }
        return dp[n][m];
    }

    public static void main(String[] args) {
//        String word1 = "intention", word2 = "execution";
//        String word1 = "ac", word2 = "b";
        //ac
        //b
        //[c][b]=2 ==> Sẽ được tính theo remove = dp[i-1][j] (Trước là replace) +1
        // ==> [c][b] không được tính theo [c][0] ==> Không được tính theo insert (Trước đó) [ dp[i][j-1] ]
        // ==> [c][b] không được tính theo [a][0] ==> Không được tính theo replace (Trước đó) [ dp[i-1][j-1] ]
        //
//        String word1 = "ace", word2 = "be"; // result=2 = replace(a->b) + remove(c)
        //a
        //bbc
        //[a][b]=1
        //[a][b']=2
        //[a][c]=3
//        String word1 = "a", word2 = "bbc"; // result=3 == replace(a->b) + insert(b) + insert(c)
        String word1 = "ac", word2 = "bbc"; // result=2 == replace(a->b) + insert(b)
//        String word1 = "inten", word2 = "execu"; // result=2 == replace(a->b) + insert(b)
        System.out.println(minDistanceRedundant(word1, word2));
        //** Đề bài:
        //- Convert từ string1 --> string2
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1,
        //- Nếu length1 > length2 ==> insert 2 chả
        //
        //VD:
        //intention
        //execution
        //
        //dp[i][j] số operation đã thực hiện cho đến vị trí [i] của word1 và [j] của word2
        //để 2 chuỗi giống nhau.
        //
        //- Nếu a != b
        //+ replace a --> b
        //dp[i][j]=dp[i-1][j-1]+1
        //- Delete
        //VD: case này cần phải (replace + remove)
        //bab[d]e
        //gabe
        //+ ở đây ta sẽ remove 'd', mục đích để 2 string có length giống nhau
        //+ Nếu ở đây replace thì sẽ bị thừa 'e' đằng sau cần remove --> Không tối ưu.
        //
        //VD: case này cần phải remove 2 lần
        //bab[d]e
        //abe
        //
        //dp[i][j]=dp[i-1][j]+1
        //
        //- Insert
        //dp[i][j]=dp[i][j-1]+1
        //+ Viết như thế này nghĩa [i][j-1] đã gióng nhau rồi
        //tức là word2 lúc trước là chưa có word2[j]
        //
        //VD:
        //Ab
        //abc
        //
        //- Nếu a==b
        //dp[i][j]=dp[i-1][j-1]
        //
        //VD:
        //intention
        //execution
        //
        //ace
        //Be
        //[a][b]=1
        //[c][b]=2
        //
        //#Reference
        //73. Set Matrix Zeroes
        //161. One Edit Distance
        //1035. Uncrossed Lines
        //2209. Minimum White Tiles After Covering With Carpets
        //
    }
}
