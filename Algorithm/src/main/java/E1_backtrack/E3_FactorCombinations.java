package E1_backtrack;

import java.util.ArrayList;
import java.util.List;

public class E3_FactorCombinations {
    public static void solution(List<Integer> factors, int prevFactor, int curNum, List<Integer> prev, List<List<Integer>> rs){
//        System.out.println(curNum);
        if(curNum<=1){
            return;
        }
        for(int i=prevFactor;i<=Math.sqrt(curNum);i++){
            if(curNum%i!=0){
                continue;
            }
            if(prevFactor> i){
                continue;
            }
            int nextVal=curNum/ i;
            List<Integer> nextRs=new ArrayList<>(prev);
            List<Integer> curRs=new ArrayList<>(prev);

            curRs.add(i);
            nextRs.add(i);

            if(nextVal!=1){
                curRs.add(nextVal);
            }
            rs.add(curRs);
            solution(factors, i, curNum/ i, nextRs, rs);
        }
    }

    public static List<List<Integer>> getFactors(int n) {
        List<Integer> factors=new ArrayList<>();
        List<List<Integer>> rs=new ArrayList<>();

        for(int i=2;i<=Math.sqrt(n)+1;i++){
            if(n%i==0){
                factors.add(i);
            }
        }
//        System.out.println(factors);
        solution(factors, 2, n, new ArrayList<>(), rs);
        return rs;
    }

    public static void main(String[] args) {
        // Đề bài:
        //- Numbers can be regarded as the product of their factors.
        //For example, 8 = 2 x 2 x 2 = 2 x 4.
        //- Given an integer n
        //* Return all possible combinations of its factors. You may return the answer in any order.
        //- Note that the factors should be in the range [2, n - 1].
        //
        // Tư duy
        //1.
        //1.1, Idea
        //- Constraint
        //- 1 <= n <= 10^7
        //+ 10000000 : Khá lớn
        //
        //- Brainstorm
        //- Ở đây ta sẽ sắp xếp các số tăng dần ==> Dễ trace
        //Ex: n=12
        //Output: [[2,6],[3,4],[2,2,3]]
        //- Ta sẽ hướng đến việc thu được min-> max
        //Ex
        //n=12
        //- Ta cần tìm được các số mà (n%x==0)
        //==> list = [2 -> sqrt(n)]
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space:
        //- Time :
        //
//        int n=12;
//        int n=1;
        int n=37;
        //#Reference:
        //1989. Maximum Number of People That Can Be Caught in Tag
        //2515. Shortest Distance to Target String in a Circular Array
        //2012. Sum of Beauty in the Array
        System.out.println(getFactors(n));
    }
}
