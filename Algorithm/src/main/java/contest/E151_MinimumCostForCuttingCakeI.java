package contest;

import java.util.Arrays;
import java.util.Collections;

public class E151_MinimumCostForCuttingCakeI {

    public static int[][][][] memo;

//    public static int solutionWrong(int x, int y, int x1, int y1, int[] horizontalCut, int[] verticalCut, int m, int n){
//        //(x,y)
//        //      (x1,y1)
//        //x==1&&y==1&&x1==2&&y1==1
//        if(x==x1-1&&y==y1-1){
//            return horizontalCut[x]+verticalCut[y];
//        }
//        if(x1>=m||y1>=n||x>=m||y>=n){
//            return 0;
//        }
//        if(x==x1&&y==y1){
//            return 0;
//        }
//        if(memo[x][y][x1][y1]!=Integer.MAX_VALUE){
//            return memo[x][y][x1][y1];
//        }
//        int curRs=Integer.MAX_VALUE;
//
//        //(x,y)
//        //x=0 --> cut 0
//        //0
//        //--
//        //1
//        //--
//        //2
//        //==
//        for(int i=x;i<x1;i++){
//            int temp=horizontalCut[i];
//            int verMin=Integer.MAX_VALUE;
//
//            for(int j=y;j<y1;j++){
//                //
//                //(x,y)(x,j)|(x, j+1)(x,y1)
//                //(i,y)(i,j)|(i, j+1)(i,y1)
//                //-----(1,0)-------- (i,j)
//                //(i+1,y)(i+1,j)|(i+1,j+1) (i+1,y1)
//                //(x1,y) (x1,j) |(x1, j+1)  (x1,y1)
//                int t;
//                int a1=solutionWrong(x, y, i, j, horizontalCut, verticalCut, m, n);
//                int a2=solutionWrong(i+1, j+1, x1, y1, horizontalCut, verticalCut, m, n);
//                int a3=solutionWrong(x, j+1, i, y1, horizontalCut, verticalCut, m, n);
//                int a4=solutionWrong(i+1, y, x1, j, horizontalCut, verticalCut, m, n);
//                t=a1;
//                t+=a2;
//                t+=a3;
//                t+=a4;
//                verMin=Math.min(verMin, t+verticalCut[j]*2);
//                System.out.printf("a1: x: %s, y: %s, x1: %s, y1: %s, val: %s\n", x, y, i, j, a1);
//                System.out.printf("a2: x: %s, y: %s, x1: %s, y1: %s, val: %s\n", i+1, j+1, x1, y1, a2);
//                System.out.printf("a3: x: %s, y: %s, x1: %s, y1: %s, val: %s\n", x, j+1, i, y1, a3);
//                System.out.printf("a4: x: %s, y: %s, x1: %s, y1: %s, val: %s\n", i+1, y, x1, j, a4);
//                System.out.printf("i: %s, j: %s, x:%s, y:%s, x1:%s, y1:%s, curRs: %s\n",
//                        i, j, x, y, x1, y1, t+verticalCut[j]*2);
//            }
//            if(verMin==Integer.MAX_VALUE){
//                verMin=0;
//            }
//            curRs=Math.min(temp+verMin, curRs);
//            System.out.printf("i: %s, x:%s, y:%s, x1:%s, y1:%s, curRs: %s\n", i, x, y, x1, y1, curRs);
//            System.out.println(temp);
//        }
////        System.out.println(curRs);
////        System.out.printf("x: %s, y: %s, x1: %s, y1: %s, rs: %s\n", x, y, x1, y1, curRs);
//        return memo[x][y][x1][y1]=curRs;
//    }

    public static int solution(int x, int y, int x1, int y1, int[] horizontalCut, int[] verticalCut, int m, int n){
        //(x,y)
        //      (x1,y1)
        //x==1&&y==1&&x1==2&&y1==1
//        if(x==x1-1&&y==y1-1){
//            return horizontalCut[x]+verticalCut[y];
//        }
        if(x1>=m||y1>=n||x>=m||y>=n){
            return 0;
        }
        if(x==x1&&y==y1){
            return 0;
        }
        if(memo[x][y][x1][y1]!=Integer.MAX_VALUE){
            return memo[x][y][x1][y1];
        }

        //(x,y)
        //x=0 --> cut 0
        //0
        //--
        //1
        //--
        //2
        //==
        int curRs=Integer.MAX_VALUE;

        //Horizontal cut
        for(int i=x;i<x1;i++){
            //(x,y)(x,j)(x, j+1)(x,y1)
            //(i,y)(i,j)(i, j+1)(i,y1)
            //-----(1,0)-------- (i,j)
            //(i+1,y)(i+1,j)(i+1,j+1) (i+1,y1)
            //(x1,y) (x1,j) (x1, j+1)  (x1,y1)
            int temp=horizontalCut[i];
            temp+= solution(x, y, i, y1, horizontalCut, verticalCut, m, n);
            temp+= solution(i+1, y, x1, y1, horizontalCut, verticalCut, m, n);
            curRs=Math.min(temp, curRs);
        }
        //Vertical cut
        for(int i=y;i<y1;i++){
            //(x,y)(x,j)|(x, j+1)(x,y1)
            //(x1,y)(x1,j)|(x1, j+1)(x1,y1)
            int temp=verticalCut[i];
            temp+= solution(x, y, x1, i, horizontalCut, verticalCut, m, n);
            temp+= solution(x, i+1, x1, y1, horizontalCut, verticalCut, m, n);
            curRs=Math.min(temp, curRs);
        }
//        System.out.println(curRs);
//        System.out.printf("x: %s, y: %s, x1: %s, y1: %s, rs: %s\n", x, y, x1, y1, curRs);
        return memo[x][y][x1][y1]=curRs;
    }

    public static int minimumCost(int m, int n, int[] horizontalCut, int[] verticalCut) {
        memo=new int[m][n][m][n];

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                for(int h=0;h<m;h++){
                    for(int k=0;k<n;k++){
                        memo[i][j][h][k]=Integer.MAX_VALUE;
                    }
                }
            }
        }
        return solution(0, 0, m-1, n-1, horizontalCut, verticalCut, m, n);
    }

    public static int minimumCostGreedy(int m, int n, int[] horizontalCut, int[] verticalCut) {
        int numH=horizontalCut.length;
        int numV= verticalCut.length;
        Arrays.sort(horizontalCut);
//        Arrays.sort(verticalCut, Collections.reverseOrder());
        Arrays.sort(verticalCut);
        int countH=0;
        int countV=0;
        int rs=0;

        while (countH<numH||countV<numV){
            if((countV>=numV)
                    ||(countH<numH&&horizontalCut[numH-countH-1]>verticalCut[numV-countV-1])){
                rs+=(countV+1)*horizontalCut[numH-countH-1];
                countH++;
            }else{
                rs+=(countH+1)*verticalCut[numV-countV-1];
                countV++;
            }
//            System.out.printf("rs: %s\n", rs);
        }
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- There is an m x n cake that needs to be cut into 1 x 1 pieces.
        //You are given integers m, n, and two arrays:
        //- horizontalCut of size m - 1, where horizontalCut[i] represents the cost to cut along the horizontal line i.
        //- verticalCut of size n - 1, where verticalCut[j] represents the cost to cut along the vertical line j.
        //In one operation, you can choose any piece of cake that is not yet a 1 x 1 square and perform one of the following cuts:
        //  - Cut along a horizontal line i at a cost of horizontalCut[i].
        //  - Cut along a vertical line j at a cost of verticalCut[j].
        //- After the cut, the piece of cake is divided into two distinct pieces.
        //- The cost of a cut depends only on the initial cost of the line and does not change.
        //* Return the minimum total cost to cut the entire cake into 1 x 1 pieces.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= m, n <= 20
        //horizontalCut.length == m - 1
        //verticalCut.length == n - 1
        //1 <= horizontalCut[i], verticalCut[i] <= 10^3
        //==> Số cũng không lớn lắm ==> O(n)
        //
        //** Brainstorm
        //- Cut theo:
        //  + Horizontal
        //  + Vertical
        //(m,n)=(m-1,n) + hor[m-1]
        //==> Dùng recursion được
        //
        //- Thứ tự cut (horizontal) or (vertical) cũng có thể ảnh hưởng kết quả
        //Ex:
        //  + Cut horizontal trước --> vertical sau thì:
        //      + Vertical sẽ x2 lên vì lúc đó nó sẽ có 2 element (Cut đôi) thay vì là 1 như trước.
        //- Vậy thì tức 1 bước sẽ cần:
        //  + Chọn horizontal/ vertical
        //==> For O(n^2) sẽ không work.
        //
        //* Kinh nghiệm:
        //- Những bài nào ("thứ tự chọn") ảnh hưởng đến kết quả:
        //  + solution(a,b,c)
        //  + solution(a,b,c)
        //==> Ta sẽ coi như trong 1 method recursion:
        //  + Là 1 unit + nối nhau tạo thành thứ tự.
        //- Bài trên vertical và horizontal order thứ tự ảnh hưởng đến kết quả:
        //  ==> Ta sẽ cần tách riêng ra.
        //- Bài dạng cut ntn:
        //  - (i,j) là cut ở (row i) và (col j)
        //      + thì (i,j) sẽ match với index của point trên (x,y)
        //      ==> Khoảng thì nó sẽ chạy đến point(x1-1,y1-1) thôi
        //- Với những dạng bài ntn:
        //  + Vẽ ra thành hình xử lý là tốt nhất
        //
        //- Horizontal cut:
        //Example:
        //(x,y)(x,j)(x, j+1)(x,y1)
        //(i,y)(i,j)(i, j+1)(i,y1)
        //-----(1,0)-------- (i,j)
        //(i+1,y)(i+1,j)(i+1,j+1) (i+1,y1)
        //(x1,y) (x1,j) (x1, j+1)  (x1,y1)
        //
        //- Vertical cut:
        //(x,y)(x,j)|(x, j+1)(x,y1)
        //(x1,y)(x1,j)|(x1, j+1)(x1,y1)
        //
        //1.1, Optimization
        //
        //1.2, Complexity:
        //- Space: O(n+m)
        //  + Stack:
        //      + We have n way to split (0 -> n-1)
        //      + We have m way to split (0 -> m-1)
        //
        //- Time: O(n*m*n*m)
        //
        //2. Greedy
        //2.0,
        //- Make the (most expensive cut) (as early as possible), which leads to "a greedy" solution.
        //- Prove:
        //  + Mỗi direction ta sẽ phải split nhiều lần (n/m lần)
        //  + Thứ tự cut cùng 1 loại liên tiếp -> Không làm thay đổi cost
        //  + Nếu cut (horizontal/ vertical) trước:
        //      + Ta có thể 1 cost cho (horizontal/ vertical)
        //Ex:
        //Take an example of 2 * 2.
        //Cut h first, total cost is h + v + v
        //Cut v first, total cost is v + h + h
        //* Main point:
        //- Nếu đằng trước cut được k * h
        // ==> thêm 1 v ==> (k+1)*v
        //==> Ta có thể suy ra công thức để tính toán.
        //
        //==> Vì số bước sẽ bảo toàn:
        //- Ta ưu tiên thực hiện cái có cost lớn hơn trước.
        //Ex:
        //1|1 1
        //1|1 1
        //1|1 1
        //1|1 1
        //[5,3,1]
        //
        int m = 3, n = 2;
        int[] horizontalCut = {1,3}, verticalCut = {5};
//        int m = 2, n = 2;
        //1|1
        //1|1
        //+ current rs=4
        //1|1
        //---
        //1|1
        //+ current rs=4+7*2=18
//        int[] horizontalCut = {7}, verticalCut = {4};
        //
        //2.1, Complexity
        //- Space: O(log(n))
        //- Time : O(n*log(n) + n+m )
        //
        System.out.println(minimumCost(m, n, horizontalCut, verticalCut));
        System.out.println(minimumCostGreedy(m, n, horizontalCut, verticalCut));
        //#Reference:
        //1332. Remove Palindromic Subsequences
        //2928. Distribute Candies Among Children I
        //2845. Count of Interesting Subarrays
        //1171. Remove Zero Sum Consecutive Nodes from Linked List
        //2949. Count Beautiful Substrings II
        //1728. Cat and Mouse II
        //
        //1413. Minimum Value to Get Positive Step by Step Sum
        //3013. Divide an Array Into Subarrays With Minimum Cost II
        //1644. Lowest Common Ancestor of a Binary Tree II
        //
        //471. Encode String with Shortest Length
        //736. Parse Lisp Expression
    }
}
