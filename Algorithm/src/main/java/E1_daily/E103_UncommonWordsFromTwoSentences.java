package E1_daily;

import java.util.*;

public class E103_UncommonWordsFromTwoSentences {

    public static String[] uncommonFromSentences(String s1, String s2) {
        String[] s1Arr=s1.split(" ");
        String[] s2Arr=s2.split(" ");

        HashMap<String, Integer> mapCountS1 = new HashMap<>();
        HashMap<String, Integer> mapCountS2 = new HashMap<>();
        HashSet<String> rs=new HashSet<>();

        for(String s: s1Arr){
            mapCountS1.put(s, mapCountS1.getOrDefault(s, 0)+1);
        }
        for(String s: s2Arr){
            mapCountS2.put(s, mapCountS2.getOrDefault(s, 0)+1);
        }
        for(String s: s1Arr){
            if(!mapCountS2.containsKey(s)&&mapCountS1.get(s)==1){
                rs.add(s);
            }
        }
        for(String s: s2Arr){
            if(!mapCountS1.containsKey(s)&&mapCountS2.get(s)==1){
                rs.add(s);
            }
        }
        return rs.toArray(new String[0]);
    }

    public static String[] uncommonFromSentencesOptimization(String s1, String s2) {
        String[] s1Arr=s1.split(" ");
        String[] s2Arr=s2.split(" ");

        HashMap<String, Integer> mapCountS = new HashMap<>();

        for(String s: s1Arr){
            mapCountS.put(s, mapCountS.getOrDefault(s, 0)+1);
        }
        for(String s: s2Arr){
            mapCountS.put(s, mapCountS.getOrDefault(s, 0)+1);
        }
        List<String> list=new ArrayList<>();
        for(String word: mapCountS.keySet()){
            if(mapCountS.get(word)==1){
                list.add(word);
            }
        }

        return list.toArray(new String[list.size()]);
    }

    public static void main(String[] args) {
        //** Requirement
        //- A sentence is (a string of ("single-space")) separated words where (each word) consists only of ("lowercase" letters).
        //- A word is (uncommon) if it appears (exactly once) in one of the sentences, and does (not appear) in the other sentence.
        //- Given two sentences s1 and s2,
        //* return a list of all the (uncommon words). You may return the answer in any order.
        //
        // Idea
        //1.
        //1.0,
        // Method-1:
        //- Constraint
        //1 <= s1.length, s2.length <= 200
        //s1 and s2 consist of lowercase English letters and spaces.
        //s1 and s2 do not have leading or trailing spaces.
        //All the words in s1 and s2 are separated by a single space.
        //
        //- Brainstorm
        //- HashSet
        //1.1, Optimization
        //- Thay vì dùng 2 map cho 2 arrays ==> Ta dùng 1 map thôi
        //  + Sau đó for các key trong đó ==> lấy count(key)==1
        //      + Có nghĩa là nó xuất hiện 1 lần trong 1 trong 2 arrays
        //      + Nó xuất hiện duy nhất 1 lần ==> (Cả trong 1 trong 2 arrays)
        //
        //1.2, Complexity
        //- Space: O(n)
        //- Time: O(n)
        //
        String s1 = "this apple is sweet", s2 = "this apple is sour";
//        String[] rs = uncommonFromSentences(s1, s2);
        String[] rs = uncommonFromSentencesOptimization(s1, s2);
        for(String s: rs){
            System.out.println(s);
        }
        //#Reference:
        //2085. Count Common Words With One Occurrence
    }
}
