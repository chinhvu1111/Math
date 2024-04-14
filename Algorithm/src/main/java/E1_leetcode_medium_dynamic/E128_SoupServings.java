package E1_leetcode_medium_dynamic;

import java.util.HashMap;
import java.util.Map;

public class E128_SoupServings {
    //    public static double rateEmptyA;
//    public static double rateEmptyAB;
    public static HashMap<Pair, Double> memo;

    public static double solution(int a, int b, double curRate) {
        Pair curKey = new Pair(a, b);
        if (memo.containsKey(curKey)) {
            return memo.get(curKey);
        }
        double rsCurRate = 0;
        if (a > 0 && b <= 0) {
            return 0;
        }
        if (a <= 0 && b <= 0) {
            rsCurRate += curRate / 2;
            return rsCurRate;
        }
        if (a <= 0) {
            rsCurRate += curRate;
            return rsCurRate;
        }
//        System.out.printf("%s %s %s\n", a, b, curRate);
        //-50,50
        rsCurRate += solution(a - 100, b, curRate / 4);
        //-25,25
        rsCurRate += solution(a - 75, b - 25, curRate / 4);
        //0,0
        rsCurRate += solution(a - 50, b - 50, curRate / 4);
        //25,-25
        rsCurRate += solution(a - 25, b - 75, curRate / 4);
        memo.put(new Pair(a, b), rsCurRate);
        return rsCurRate;
    }

    public static double soupServingsRecursion(int n) {
        memo = new HashMap<>();
        return solution(n, n, 1);
    }

    public static HashMap<Integer, Map<Integer, Double>> memoOptimization;

    public static double solutionOptimizationWrong(int a, int b, double curRate) {
        double rsCurRate = 0;
        if (a <= 0 && b <= 0) {
            rsCurRate += curRate / 2;
            return rsCurRate;
        }
        if (a <= 0) {
            rsCurRate += curRate;
            return rsCurRate;
        }
        if (b <= 0) {
            return 0;
        }
        if (memoOptimization.containsKey(a)&&memoOptimization.get(a).containsKey(b)) {
            return memoOptimization.get(a).get(b);
        }
//        System.out.printf("%s %s %s\n", a, b, curRate);
        //-50,50
        rsCurRate += solutionOptimizationWrong(a - 4, b, curRate / 4);
        //-25,25
        rsCurRate += solutionOptimizationWrong(a - 3, b - 1, curRate / 4);
        //0,0
        rsCurRate += solutionOptimizationWrong(a - 2, b - 2, curRate / 4);
        //25,-25
        rsCurRate += solutionOptimizationWrong(a - 1, b - 3, curRate / 4);
        memoOptimization.computeIfAbsent(a, k -> new HashMap<>()).put(b, rsCurRate);
        return rsCurRate;
    }

    public static double solutionOptimization(int a, int b) {
        double rsCurRate = 0;
        if (a <= 0 && b <= 0) {
            return 0.5;
        }
        if (a <= 0) {
            return 1;
        }
        if (b <= 0) {
            return 0;
        }
        if (memoOptimization.containsKey(a)&&memoOptimization.get(a).containsKey(b)) {
            return memoOptimization.get(a).get(b);
        }
//        System.out.printf("%s %s %s\n", a, b, curRate);
        //-50,50
        rsCurRate += solutionOptimization(a - 4, b)/4;
        //-25,25
        rsCurRate += solutionOptimization(a - 3, b - 1)/4;
        //0,0
        rsCurRate += solutionOptimization(a - 2, b - 2)/4;
        //25,-25
        rsCurRate += solutionOptimization(a - 1, b - 3)/4;
        memoOptimization.computeIfAbsent(a, k -> new HashMap<>()).put(b, rsCurRate);
        return rsCurRate;
    }

    public static double soupServingsRecursionOptimization(int n) {
        memoOptimization = new HashMap<>();
        for(int i=1;i<=n;i++){
            if(solutionOptimization(i, i)>1-1e-5){
                return 1;
            }
        }
        return solutionOptimization(n/25, n/25);
    }

    public static double soupServings(int n) {
        return 0;
    }

    public static void main(String[] args) {
        //** Requirement:
        //There are two types of soup: type A and type B. Initially, we have n ml of each type of soup. There are four kinds of operations:
        //  - Serve 100 ml of soup A and 0 ml of soup B,
        //  - Serve 75 ml of soup A and 25 ml of soup B,
        //  - Serve 50 ml of soup A and 50 ml of soup B, and
        //  - Serve 25 ml of soup A and 75 ml of soup B.
        //- Mỗi lần ta sẽ chon 1 trong 4 operations với tỉ lệ 0.25.
        //- Ta sẽ serve nhiều nhất có thể và stop khi không còn quantity của cả 2 loại soup.
        //* Không có operation mà 100ml của soup B được sử dụng first.
        //* Return the probability that soup A will be (empty first),
        // plus (half the probability) that A and B become (empty) at the same time.
        //* Answers within 10^-5 of the actual answer will be accepted.
        //
        //** Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //0 <= n <= 10^9
        //
        //- Brainstorm
        //Ex:
        //Input: n = 50
        //Output: 0.62500
        //Explanation: If we choose the first two operations, A will become empty first.
        //- For the third operation, A and B will become empty at the same time.
        //- For the fourth operation, B will become empty first.
        //- So the total probability of A becoming empty first plus half the probability
        // that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
        //
        //- Given n(ml) A và n(ml) B
        //- We have 4 options:
        //  + (n-100, n) : 0.25
        //  + (n-75, n-25) : 0.25
        //  + (n-50, n-50) : 0.25
        //  + (n-25, n-75) : 0.25
        //==> Qua layer 1 ==> Tỉ lệ giảm xuống 0.25/4
        //- Bài này có thể làm với memoziation
        //- Không nên chia ra 2 loại rate ==> Khó có thể memoziation được.
        //
        //          50,50
        //         /    \       \      \
        //     -50,50  -25,25   0,0   25,-25
        //                               ==> B empty first ==> 0
        //
        //- Nếu big n ==> StackOverFlow nếu làm recursion.
        //
        //- Interative method:
        //- (n,n)
        //  + (n-100, n) : 1/4
        //  + (n-75, n-25) : 1/4
        //  + (n-50, n-50) : 1/4
        //  + (n-25, n-75) : 1/4
        //
        //- Ta có thể giảm n => n/25
        //
        //==> Bfs được không
        //* Noted:
        //- Độ chính xác của số chỉ đến 10^-5
        //==> N sẽ tỉ lệ thuận với rate
        //  + N càng lớn thì rate càng gần 1
        //  + N/25 --> để giảm số lượng loop
        //      + Loop 1 -> n/25 : nếu ta được (value > 1-10^-5)
        //      ==> Thì ta sẽ return 1 luôn
        //      + Nếu không có đoạn trên --> StackOverFlow luôn.
        //
        //#Reference:
        //976. Largest Perimeter Triangle
        //834. Sum of Distances in Tree
        //1175. Prime Arrangements
        //
//        System.out.println(soupServingsRecursion(100));
//        System.out.println(soupServingsRecursion(660295675));
        System.out.println(soupServingsRecursionOptimization(660295675));
//        System.out.println(soupServingsRecursionOptimizatin(100));
//        System.out.println(soupServings(50));
    }
}
