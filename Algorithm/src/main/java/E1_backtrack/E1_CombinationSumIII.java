package E1_backtrack;

import java.util.ArrayList;
import java.util.List;

public class E1_CombinationSumIII {

    public static List<List<Integer>> rs;
    public static void solution(int min, List<Integer> listNumbers, int curSum, int num, int k, int n){
//        System.out.printf("%s, sum: %s, num: %s\n", listNumbers, curSum, num);
        if(num==k){
            if(curSum==n){
                rs.add(new ArrayList<>(listNumbers));
            }
            return;
        }
        //1,2,3,4
        //1,3,4,5
        for(int i=min;i<=9;i++){
            listNumbers.add(i);
            solution(i+1, listNumbers, curSum+i, num+1, k, n);
            listNumbers.remove(listNumbers.get(listNumbers.size()-1));
        }
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        rs=new ArrayList<>();
        List<Integer> list=new ArrayList<>();
        if(k==1){
            if(n<=9){
                list.add(n);
                rs.add(list);
                return rs;
            }
            return rs;
        }
        solution(1, list, 0,0, k, n);
        return rs;
    }
    public static void main(String[] args) {
        //** Requirement:
        //- Tìm tất cả tập hợp k numbers such that sum up to n
        //+ Only numbers 1 through 9 are used.
        //+ Each number is used at most once.
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Idea:
        //- Constraint:
        //2 <= k <= 9
        //1 <= n <= 60
        //
        //- Brainstorm
        //
        //#Reference:
        //2708. Maximum Strength of a Group
        //2307. Check for Contradictions in Equations
        //247. Strobogrammatic Number II
        int k=3, n=7;
        combinationSum3(k, n);
        System.out.println(rs);
    }
}
