package contest;

import java.util.*;

public class E324_FindTheMostCommonResponse {

    public static String findCommonResponse(List<List<String>> responses) {
        HashMap<String, Integer> mapCount=new HashMap<>();

        for(List<String> res: responses){
            HashSet<String> curSet = new HashSet<>(res);
            for (String s: curSet){
                mapCount.put(s, mapCount.getOrDefault(s, 0)+1);
            }
        }
        int maxCount=0;
        String rs="";
        for (Map.Entry<String, Integer> e: mapCount.entrySet()){
            if(maxCount==e.getValue()){
                if(e.getKey().compareTo(rs)<0||"".equals(rs)){
                    rs=e.getKey();
                }
            }else if(maxCount<e.getValue()){
                rs=e.getKey();
                maxCount=e.getValue();
            }
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- You are given a 2D string array responses where each responses[i] is an array of strings
        // representing (survey responses) from (the ith day).
        //* Return (the most common response) across (all days) after removing (duplicate responses) within each responses[i].
        // If there is a tie, return the lexicographically (smallest) response.
        //
        //Example 1:
        //
        //Input: responses = [["good","ok","good","ok"],["ok","bad","good","ok","ok"],["good"],["bad"]]
        //Output: "good"
        //
        //Explanation:
        //
        //After removing duplicates within each list, responses = [["good", "ok"], ["ok", "bad", "good"], ["good"], ["bad"]].
        //"good" appears 3 times, "ok" appears 2 times, and "bad" appears 2 times.
        //Return "good" because it has the highest frequency.
        //
        //* Idea
        //1
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= responses.length <= 1000
        //1 <= responses[i].length <= 1000
        //1 <= responses[i][j].length <= 10
        //responses[i][j] consists of only lowercase English letters
        //
        //- Brainstorm
        //
        //
        //
        String[][] responses = {{"good","ok","good"},{"ok","bad"},{"bad","notsure"},{"great","good"}};
        List<List<String>> input=new ArrayList<>();
        for (String[] s: responses){
            input.add(Arrays.asList(s));
        }
        System.out.println(findCommonResponse(input));
    }
}
