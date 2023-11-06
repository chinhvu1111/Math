package E1_hashmap;

import java.util.*;

public class E8_SentenceSimilarity {

    public static boolean areSentencesSimilar(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        int n=sentence1.length;
        int m=sentence2.length;
        if(n!=m){
            return false;
        }
        HashMap<String, HashSet<String>> similarRelWord=new HashMap<>();

        for(List<String> rel:similarPairs){
            String word1=rel.get(0);
            String word2=rel.get(1);
            HashSet<String> oldSimilar1=similarRelWord.getOrDefault(word1, new HashSet<>());
            oldSimilar1.add(word2);
            similarRelWord.put(word1, oldSimilar1);

//            HashSet<String> oldSimilar2=similarRelWord.getOrDefault(word2, new HashSet<>());
//            oldSimilar1.add(word1);
//            similarRelWord.put(word2, oldSimilar2);
        }
//        for(String key: similarRelWord.keySet()){
//            similarRelWord.get(key).add(key);
//        }
        //    System.out.println(similarRelWord);
        for(int i=0;i<n;i++){
            String word1=sentence1[i];
            String word2=sentence2[i];
            boolean isValid1=similarRelWord.containsKey(word1)&&similarRelWord.get(word1).contains(word2);
            boolean isValid2=similarRelWord.containsKey(word2)&&similarRelWord.get(word2).contains(word1);

            if(!isValid1 && !isValid2 && !Objects.equals(word1, word2)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Given 2 sentences:
        //  + sentence1 : a array of word
        //  + sentence2 : a array of word
        //- Given the similar pairs:
        //  + (word1, word2) : Indicate word1 and word2 are similar
        //- Note:
        //- word1=word2, word2=word3 ==> word1 chưa chắc = word3 (Not transitive)
        //- Sentence1 is similar to sentence2:
        //+ same length
        //+ sentence1[i] is similar to sentence2[i] (IN RELATIONSHIPS)
        //+ Giống y hệt nhau (exact the same)
        //
        //* return true if two sentences are similar <> return false.
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= sentence1.length, sentence2.length <= 1000
        //1 <= sentence1[i].length, sentence2[i].length <= 20
        //sentence1[i] and sentence2[i] consist of English letters.
        //0 <= similarPairs.length <= 1000
        //similarPairs[i].length == 2
        //1 <= xi.length, yi.length <= 20
        //xi and yi consist of lower-case and upper-case English letters.
        //All the pairs (xi, yi) are distinct.
        //
        //- Brainstorm
        //Ex:
        //sentence1 = ["great","acting","skills"],
        //sentence2 = ["fine","drama","talent"],
        // similarPairs = [["great","fine"],["drama","acting"],["skills","talent"]]
        //+ Return true
        //Ex:
        //sentence1 = [a, b, c]
        //sentence2 = [e, f, g]
        // similarPairs =[(a,f), (b,g), (e,c), (c,g)]
        //+ Return false
        //- Idea là lưu kết quả pair similar giữa sentence1[i] và sentence[i]
        //  + Chung index mà (khác nhau 1 character) && (relation similar không có trong list) ==> return false
        //  + return true
        //- 1 index => 1 string
        //- 1 string => Nhiều index
        //
        //- Không có điều kiện distinct ==> ta cần phải care cases duplicated word
        //
        //1.1, Implementation
        //
        //- Để check (sentence2[i] == sentence2[i]) ==> 2 word này có trong list similar không
        //--> traverse listSimilar + create hashMap 2 chiều giữa 2 word
        //- 1 word == n words ==> HashMap<String, Set<String>>
        //
        //- Ta có 2 cách để caching:
        //+ pair(word1, word2) :
        //  + Ta có thể add hashmap cả 2 chiều (word1, word2)
        //  ==> Sau đó chỉ cần check contains(word1) == fale ==> return false
        //  + Ta có tểh add 1 chiều hashmap của word include word2:
        //     + boolean isValid2=similarRelWord.containsKey(word2)&&similarRelWord.get(word2).contains(word1);
        //  ==> Lúc validate ta cần check 2 chiều:
        //     + boolean isValid2=similarRelWord.containsKey(word2)&&similarRelWord.get(word2).contains(word1);
        //       boolean isValid1=similarRelWord.containsKey(word1)&&similarRelWord.get(word1).contains(word2);
        //1.2, Optimization
        //- Có 2 cách caching:
        //+ Cách 1 chiều ==> có vẻ faster
        //  + Tốt cho mem hơn.
        //  + String pool nếu 2 string == nhau (So sánh vùng nhớ thay vì for loop) ==> Faster.
        //+ Cache nhiều (2 chiều)
        //  + Tốt cho time : Large M (The average length of each word)
        //
        //1.3, Complexity:
        //- N is the length of sentence
        //- k is the length of similarity list : Pair <String, string> ==> distinct
        //** Khái niệm length trung bình của mỗi word
        //- M is the average length of each word
        //
        //  + hash<word1, set<words>>
        //  Ex: Trong trường hợp add 1 chiều
        //  + {word1, (word2, word3)}
        //  + {word3, (word1, word2)}
        //  ==> 4 edges {word1, word2}, {word1, word3}, {word3, word1}, {word3, word2}
        //  ==> O(k)
        //  + M is the average length of each word ==> Space = O(k*M)
        //- Space : O(k*M)
        //- Time : O((n+k)*M)
        //  + Do ta tốn M time to compare(word1, word2) are equal.

        String[] sentence1 = {"great","acting","skills"};
        String[] sentence2 = {"fine","drama","talent"};
        String[][] similarPairs = {{"great","fine"},{"drama","acting"},{"skills","talent"}};
        List<List<String>> similarPairsList=new ArrayList<>();
        for(String[] s:similarPairs){
            List<String> list=new ArrayList<>();
            list.add(s[0]);
            list.add(s[1]);
            similarPairsList.add(list);
        }
        System.out.println(areSentencesSimilar(sentence1, sentence2, similarPairsList));
        //#Reference:
        //- Bài này chắc là bài dạng transitive
        //737. Sentence Similarity II
    }
}
