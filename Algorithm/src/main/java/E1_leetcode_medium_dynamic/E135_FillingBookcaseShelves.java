package E1_leetcode_medium_dynamic;

import java.util.ArrayList;
import java.util.Arrays;

public class E135_FillingBookcaseShelves {

    public static int minHeightShelves(int[][] books, int shelfWidth) {
        int n=books.length;
        int[][] dp=new int[n][n+1];
        int[] prefixWidth=new int[n+1];
        int rs=Integer.MAX_VALUE;

        for(int i=1;i<=n;i++){
            prefixWidth[i]=prefixWidth[i-1]+books[i-1][0];
//            System.out.printf("i=%s, %s\n", i, prefixWidth[i]);
        }
        for(int i=0;i<n;i++){
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
//        System.out.println();
        int c=1;
        int maxHeight=0;
        while (c<n+1&&prefixWidth[c]<=shelfWidth){
            maxHeight=Math.max(maxHeight, books[c-1][1]);
            dp[c-1][1]=maxHeight;
            dp[c-1][0]=0;
            c++;
        }
        //Index của current layer
        for(int i=1;i<n;i++){
            //Index của previous layer
            for(int l=i+1;l>=2;l--){
                int curMinHeight=Integer.MAX_VALUE;
                int curHeight=books[i][1];

                for(int j=i-1;j>=0&&j>=l-2;j--){
                    //Layer
                    //i=0 ==> layer(l) <= i+1s
                    if(dp[j][l-1]!=Integer.MAX_VALUE&&prefixWidth[i+1]-prefixWidth[j+1]<=shelfWidth){
                        curMinHeight=Math.min(dp[j][l-1]+curHeight, curMinHeight);
                    }
                    curHeight=Math.max(curHeight, books[j][1]);
                }
                dp[i][l]=curMinHeight;
//                System.out.printf("i=%s , l=%s, val=%s\n", i, l, dp[i][l]);
            }
        }
        for(int i=1;i<=n;i++){
            rs=Math.min(rs, dp[n-1][i]);
//            System.out.println(dp[n-1][i]);
        }
        return rs;
    }

    public static int minHeightShelvesOptimization(int[][] books, int shelfWidth) {
        int n=books.length;
        int[] dp=new int[n+1];

        //Xét book thứ index=i
        //- dp[i] : là min heigh tại index=i
        //  + dp[i] có thể tính theo dp[i-k] + height (của đoạn còn lại) nếu:
        //      + Đoạn còn lại đó <= limit weight
        //  ==> Ta lấy min là được.
        //
        for(int i=1;i<=n;i++){
            //Một mình nó 1 layer
            int min=dp[i-1]+books[i-1][1];
            int curHeight=books[i-1][1];
            int width=books[i-1][0];
            int j=i-2;
            while(j>=0&&width+books[j][0]<=shelfWidth){
                width+=books[j][0];
                curHeight=Math.max(curHeight, books[j][1]);
                min=Math.min(min, dp[j]+curHeight);
                j--;
            }
            dp[i]=min;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        //** Requirement:
        //- You are given an array books where books[i] = [thicknessi, heighti] indicates the (thickness) and (height) of the ith book.
        //- You are also given an integer (shelfWidth).
        //- We want to place these books in order onto bookcase shelves that have a total (width) (shelfWidth).
        //- We choose some of the books to place on this shelf such that the (sum of their thickness) is
        // (less than or equal) to (shelfWidth) then build (another level) of the shelf of the bookcase
        // so that the total (height of the bookcase) has increased by (the maximum height) of the books we just put down.
        //- We repeat this process until there are (no more books to place).
        //* Note that at each step of the above process, the order of the books we place is the same order as
        // the given sequence of books.
        //  - Order của book được đặt là same order as given sequence of books.
        //  ==> Dù có put books thì sẽ put lần lượt theo thứ tự đã cho.
        //- For example, if we have (an ordered list) of 5 books, we might place the (first) and (second) book onto the (first shelf),
        //the (third book) on the (second shelf), and the (fourth and fifth) book on (the last shelf).
        //* Return the (minimum possible height) that the total bookshelf can be after placing shelves in this manner.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //1 <= books.length <= 1000
        //1 <= thicknessi <= shelfWidth <= 1000
        //1 <= heighti <= 1000
        //==> Length không quá lớn
        //
        //- Brainstorm
        //Ex:
        //Input: books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]], shelfWidth = 4
        //Output: 6
        //Explanation:
        //The sum of the heights of the 3 shelves is 1 + 3 + 2 = 6.
        //Notice that book number 2 does not have to be on the first shelf.
        //
        //- Tìm min height
        //- Giả sử m là số lượng layers ta build.
        //  + m <= n (n là số lượng books)
        //- Thử prefixSum:
        //books = [[1,1],[2,3],[2,3],[1,1],[1,1],[1,1],[1,2]]
        //books = [1,3,5,6,7,8,9]
        //  + Không phải phân hết vừa đủ vào shelfWidth sẽ có lợi vì:
        //  Ex:
        //  layer 0 :[1,1],[2,3] (Height=3)
        //  layer 1: [2,3],[1,1] (Height=3)
        //  => total of height = 6 (Rõ ràng nếu để (2,3),(2,3) cùng chung có lợi hơn
        //  layer 0 :[1,1], (Height=1)
        //  layer 1: [2,3],[2,3] (Height=3)
        //  layer 2: [1,1] (Height = 1)
        //  => total of height = 5
        //- Mỗi book tại index=i có thể đặt vào layer:
        //  + layer=(0 -> i)
        //- Dp[i][j]: là total height:
        //  + Nếu layer (j) kết thúc tại book[i]
        //CT:
        //- k: 0 -> i-1:
        //  if(prefixSum...)
        //  + dp[i][j] = dp[k][j-1] + min_height(k+1,i)
//        int[][] books = {{1,1},{2,3},{2,3},{1,1},{1,1},{1,1},{1,2}};
        //{{1,1},
        // {2,3},{2,3},
        // {1,1},{1,1},{1,1},{1,2}};
        //
        //1.1, Optimization
        //- Ở đây ta không cần phải gắn i với layer:
        //  + Dùng dp[i][l] là thừa thãi
        //  ==> Ta chỉ quan tâm (min height) tại index=i là được.
        //  + Vì layer là bao nhiêu chẳng được.
        //
        //* Xét book thứ index=i
        //- dp[i] : là min heigh tại index=i
        //  + dp[i] có thể tính theo dp[i-k] + height (của đoạn còn lại) nếu:
        //      + Đoạn còn lại đó <= limit weight
        //  ==> Ta lấy min là được.
        //
        //- Với mỗi index=i
        //  + Ta sẽ loop đọc lại all (j: i-1 -> 0):
        //   Với mỗi j:
        //    + Ta cần xác định prefixSum <= selfWidth
        //    + Và min nhất của chúng sẽ gán cho dp[i]
        //1.2, Complexity
        //- Space : O(n)
        //- Time : O(n^2)
        //
        //#Reference:
        //2122. Recover the Original Array
        //883. Projection Area of 3D Shapes
        //2733. Neither Minimum nor Maximum
//        int shelfWidth = 4;
        int[][] books = {{1,3},{2,4},{3,2}};
        int shelfWidth = 6;
        System.out.println(minHeightShelves(books, shelfWidth));
        System.out.println(minHeightShelvesOptimization(books, shelfWidth));
    }
}
