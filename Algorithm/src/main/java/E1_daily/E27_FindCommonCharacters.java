package E1_daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class E27_FindCommonCharacters {

    public static List<String> commonChars(String[] words) {
        int n=words.length;
        int[] count=new int[26];
        Arrays.fill(count, Integer.MAX_VALUE);

        for(int i=0;i<n;i++){
            String curWord=words[i];
            int m=curWord.length();
            int[] curCount=new int[26];

            for(int j=0;j<m;j++){
                curCount[curWord.charAt(j)-'a']++;
            }
            for(int j=0;j<26;j++){
                count[j]=Math.min(count[j], curCount[j]);
            }
        }
        List<String> list=new ArrayList<>();

        for(int i=0;i<26;i++){
            if(count[i]==Integer.MAX_VALUE){
                continue;
            }
            for(int j=0;j<count[i];j++){
                list.add(String.valueOf((char)(i+'a')));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given a string array words,
        //* return an array of all characters that show up in (all strings) within (the words) (including duplicates).
        // You may return the answer in any order.
        //* Return all character xuất hiện trong all strings.
        //
        //** Idea
        //1.
        //1.0,
        //* Method-1:
        //- Constraint:
        //1 <= words.length <= 100
        //1 <= words[i].length <= 100
        //words[i] consists of lowercase English letters.
        //
        //- Brainstorm
        //Example 1:
        //Input: words = ["bella","label","roller"]
        //Output: ["e","l","l"]
        //
        //- count số lần xuất hiện của character bên trong mỗi string.
        //- Ta có thể dùng count bằng index
        //  + (index+1!= current count) ==> Không phải.
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(1)
        //- Time : O(n*m)
        //
        //#Reference:
        //3048. Earliest Second to Mark Indices I
        //2682. Find the Losers of the Circular Game
        //2144. Minimum Cost of Buying Candies With Discount
        String[] words = {"bella","label","roller"};
        System.out.println(commonChars(words));
    }
}
