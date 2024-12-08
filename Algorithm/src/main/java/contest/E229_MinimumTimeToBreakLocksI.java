package contest;

import java.util.*;

public class E229_MinimumTimeToBreakLocksI {

    public static int rs;
    public static void permutation(int index, List<Integer> strength, List<Integer> output, HashSet<Integer> visited, int n, int k){
        if(index==n){
//            System.out.println(output);
            rs=Math.min(rs, getResult(output, k));
//            System.out.printf("%s: %s\n", output, rs);
            return;
        }
        for(int i=0;i<n;i++){
            if(visited.contains(i)){
                continue;
            }
            visited.add(i);
            output.add(strength.get(i));
            permutation(index+1, strength, output, visited, n, k);
            visited.remove(i);
            output.remove(output.size()-1);
        }
    }

    public static int getResult(List<Integer> strength, int k){
        int n= strength.size();
        //x+=k
        int x=1;
//        int prevVal = strength.get(0);
        //time = 1:
        //energy = 1
        //
        int curRs=0;
        for(int i=0;i<n;i++){
            curRs+=Math.ceil((float)strength.get(i)/x);
            if(curRs>=rs){
                return rs;
            }
            x+=k;
        }
        return curRs;
    }

    public static int findMinimumTime(List<Integer> strength, int k) {
        int n= strength.size();
        HashSet<Integer> visited=new HashSet<>();
        List<Integer> output=new ArrayList<>();
        //x+=k
        rs=Integer.MAX_VALUE;
        permutation(0, strength, output, visited, n, k);
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Bob is stuck in a dungeon and (must break n locks), each requiring some (amount of energy) to break.
        //- The required energy for (each lock) is stored in an array called strength where strength[i] indicates (the energy needed to break (the ith lock)).
        //- To break a lock, Bob uses (a sword) with the following characteristics:
        //  + (The initial energy) of the sword is 0.
        //  + (The initial factor X) by which the energy of the sword increases is 1.
        //  + Every minute, the energy of the sword increases by (the current factor X).
        //  + To break the ith lock, the energy of the sword must reach at least strength[i].
        //  + After breaking a lock, the energy of the sword (resets to 0), and (the factor X) increases by (a given value K).
        //- Your task is to determine (the minimum time) in minutes required for Bob to break all n locks and escape the dungeon.
        //* Return the minimum time required for Bob to break all n locks.
        //
        //Example 1:
        //Input: strength = [3,4,1], K = 1
        //Output: 4
        //Explanation:
        //- Init: X=1
        //  + Break new lock --> X=X+k
        //
        //Time	Energy	X	Action	Updated X
        //0	    0	    1	Nothing	1
        //1	    1	    1	Break 3rd Lock	2
        //2	    2	    2	Nothing	2
        //3	    4	    2	Break 2nd Lock	3
        //4	    3	    3	Break 1st Lock	3
        //The locks cannot be broken in less than 4 minutes; thus, the answer is 4.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //n == strength.length
        //1 <= n <= 8
        //1 <= K <= 10
        //1 <= strength[i] <= 10^6
        //  n <=8,k<=10
        //  + strength <= 10^6 (big) ==> Time: O(n)
        //
        //- Brainstorm
        //- Gap between (i),(i+1)
        //  + Fast calculation
        //Input: strength = [3,4,1], K = 1
        //sort(strength) = [1,3,4]
        //- 4/updated_x = time
        //
        //- [1,1,3,4]
        //=>
        //- Thứ tự chọn quan trọng
        //  + 1,1,3,4
        //- Permutation của 8 elements
        //
//        Integer[] strength = {3,4,1};
//        int k = 1;
//        Integer[] strength = {1,3,4,1};
//        int k = 1;
        Integer[] strength = {1,3,4,1,100013,12312,12312,22};
        int k = 2;
        //1,1,3,4
        //Time	Energy	X	Action	Updated X
        //0	    0	1	Nothing	1
        //1	    1	1	Break 1 Lock	2
        //2	    2	2	Break 2 Lock	3
        //3	    3	3	Break 3 Lock	4
        //4	    4	4	Break 4 Lock	3
        System.out.println(findMinimumTime(Arrays.asList(strength), k));
    }
}
