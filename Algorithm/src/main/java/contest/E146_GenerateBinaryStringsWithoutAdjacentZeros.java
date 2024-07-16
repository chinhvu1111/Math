package contest;

import java.util.ArrayList;
import java.util.List;

public class E146_GenerateBinaryStringsWithoutAdjacentZeros {

    public static List<String> rs;

    public static void solution(int index, String curRs, int n){
        if(index==n){
            rs.add(curRs);
            return;
        }
        if(curRs.charAt(index-1)=='0'){
            solution(index+1, curRs+"1", n);
        }else if(curRs.charAt(index-1)=='1'){
            solution(index+1, curRs+"0", n);
            solution(index+1, curRs+"1", n);
        }
    }

    public static List<String> validStrings(int n) {
        rs=new ArrayList<>();
        if(n==0){
            return rs;
        }
        solution(1, "0", n);
        solution(1, "1", n);
        return rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a positive integer n.
        //- A binary string x is valid if all substrings of x of length 2 contain (at least one) "1".
        //  + <=1
        //* Return all valid strings with length n, in any order.
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= n <= 18
        //
        //- Brainstorm
        //- Length == 2 has at least one 1:
        //  + >=1
        //  + 11, 01, 10
        //
        //- Length = 1 :
        //  + Kệ
        //
//        int n=3;
//        int n=1;
        int n=0;
        System.out.println(validStrings(n));
    }
}
