package E1_daily;

import java.util.HashMap;
import java.util.Map;

public class E301_RabbitsInForest {

    public static int numRabbits(int[] answers) {
        int n=answers.length;
        HashMap<Integer, Integer> mapCount=new HashMap<>();

        for (int i = 0; i < n; i++) {
            mapCount.put(answers[i], mapCount.getOrDefault(answers[i], 0)+1);
        }
        int rs=0;

        for(Map.Entry<Integer, Integer> e: mapCount.entrySet()){
            int count=e.getValue();
            rs+=(e.getKey()+1)*Math.ceil((double) count/(e.getKey()+1));
        }

        return rs;
    }

    public static int numRabbitsRefactor(int[] answers) {
        int n=answers.length;
        int[] count=new int[1000];

        for (int i = 0; i < n; i++) {
            count[answers[i]]++;
        }
        int rs=0;

        for(int i=0;i<1000;i++){
            if(count[i]==0){
                continue;
            }
            rs+=(i+1)*Math.ceil((double) count[i]/(i+1));
        }

        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- There is a forest with (an unknown number of rabbits).
        //- We asked (n rabbits) "How many rabbits have the same color as you?"
        //  + The number of rabbits with (the same color) (except for the current one)
        // and collected the answers in an integer array answers where answers[i] is (the answer of the ith rabbit).
        //- Given the array answers,
        //* Return (the minimum number of rabbits) that could be in the forest.
        //
        //Example 1:
        //
        //Input: answers = [1,1,2]
        //Output: 5
        //Explanation:
        //The two rabbits that answered "1" could both be the same color, say red.
        //The rabbit that answered ("2" can't be red) or the answers would be inconsistent.
        //Say the rabbit that answered ("2" was blue).
        //Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
        //(The smallest possible number) of rabbits in the forest is therefore 5:
        //  + 3 that answered plus 2 that didn't.
        //
        //** Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= answers.length <= 1000
        //0 <= answers[i] < 1000
        //  + Length <= 1000 ==> Time: O(n^2)
        //
        //* Brainstorm:
        //
        //Example 2:
        //
        //Input: answers = [10,10,10]
        //Output: 11
        //- answers[i] = x
        //  + Group the same answer altogether
        //
        //1.1, Case
        //- 2,2,2,2,2
        //==> 2 map to group with 3 rabits
        //- [2,2,2],2,2
        //- 2,2 ==> Invalid
        //
        //- Formula:
        //  + (val+1)/ceil(count/val+1)
        //
//        int[] answers = {1,1,2};
        //2*(2/2)
        int[] answers = {10,10,10};
        //(11)*ceil(3/11)
        //
        //1.2, Optimization
        //
        //
        //1.3, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        System.out.println(numRabbits(answers));
        System.out.println(numRabbitsRefactor(answers));
    }
}
