package E1_daily;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class E211_SentenceSimilarityII {

    public static Pair<String, Integer> findParent(String word, HashMap<String, String> parent){
        String initWord=word;
        int depth=1;

        while(!parent.get(word).equals(word)){
            word=parent.get(word);
            depth++;
        }
        parent.put(initWord, word);
        return new Pair<>(word, depth);
    }

    public static void addEdge(String word1, String word2, HashMap<String, String> parent){
        Pair<String, Integer> parent1=findParent(word1, parent);
        Pair<String, Integer> parent2=findParent(word2, parent);

        if(!parent1.getKey().equals(parent2.getKey())){
            if(parent1.getValue()>parent2.getValue()){
                parent.put(parent2.getKey(), parent1.getKey());
            }else{
                parent.put(parent1.getKey(), parent2.getKey());
            }
        }
    }

    public static boolean areSentencesSimilarTwo(String[] sentence1, String[] sentence2, List<List<String>> similarPairs) {
        HashMap<String, String> parent=new HashMap<>();
        int n=sentence1.length;
        int m=sentence2.length;

        if(n!=m){
            return false;
        }

        for(List<String> list: similarPairs){
            String word=list.get(0);
            String word1=list.get(1);

            if(!parent.containsKey(word)){
                parent.put(word, word);
            }
            if(!parent.containsKey(word1)){
                parent.put(word1, word1);
            }
            //Time: O(L)
            addEdge(word, word1, parent);
        }
        //Time: O(n)
        for(int i=0;i<n;i++){
            String word=sentence1[i];
            String word1=sentence2[i];

            if(word1.equals(word)){
                continue;
            }
            if(!parent.containsKey(word)){
                parent.put(word, word);
            }
            if(!parent.containsKey(word1)){
                parent.put(word1, word1);
            }
            Pair<String, Integer> parent1=findParent(word, parent);
            Pair<String, Integer> parent2=findParent(word1, parent);
            if(!parent1.getKey().equals(parent2.getKey())){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //** Requirement
        //- We can represent a sentence as an array of words, for example, the sentence "I am happy with leetcode" can be represented as
        // arr = ["I","am",happy","with","leetcode"].
        //- Given two sentences (sentence1 and sentence2) each represented as a string array and
        // given an array of string pairs similarPairs where similarPairs[i] = [xi, yi] indicates that (the two words xi and yi) are similar.
        //* Return true if (sentence1 and sentence2) are similar, or false if they are not similar.
        //
        //- Two sentences are similar if:
        //  + They have (the same length) (i.e., the same number of words)
        //  + sentence1[i] and sentence2[i] are similar.
        //- Notice that a word is always similar to itself, also notice that the similarity relation is transitive.
        // For example, if the words a and b are similar, and the words b and c are similar, then a and c are similar.
        //
        //Example 1:
        //
        //Input:
        // sentence1 = ["great","acting","skills"], sentence2 = ["fine","drama","talent"],
        // similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
        //Output: true
        //Explanation: The two sentences have the same length
        // and each word i of sentence1 is also similar to the corresponding word in sentence2.
        //
        // Idea
        //1.
        //1.0,
        //- Method-1:
        //+ Constraints:
        //1 <= sentence1.length, sentence2.length <= 1000
        //1 <= sentence1[i].length, sentence2[i].length <= 20
        //sentence1[i] and sentence2[i] consist of lower-case and upper-case English letters.
        //0 <= similarPairs.length <= 2000
        //similarPairs[i].length == 2
        //1 <= xi.length, yi.length <= 20
        //xi and yi consist of English letters.
        //  + length<=1000
        //
        //- Brainstorm
        //- Union find
        //- Build group using the similarPairs
        //
        //similarPairs = [["great","good"],["fine","good"],["drama","acting"],["skills","talent"]]
        //  + hash[x]=y
        //  + Pair<String, Integer> node
        //
        //
//        String[] sentence1 = {"great","acting","skills"}, sentence2 = {"fine","drama","talent"};
//        String[][] similarPairs = {{"great","good"},{"fine","good"},{"drama","acting"},{"skills","talent"}};
        String[] sentence1 = {"jrocadcojmybpxmuj","livgsrfvgtovcurzq","mnrdscqkycodx","wgcjlntupylayse","tglnshmqlmkqqfbpf","uzlxmaoro","narvuaqmmkqhd","xozoyaqxtbustrymo","jrocadcojmybpxmuj","ainlwrwabqcwq","qnjidlmwmxxjgntez","bbchthovla","vaufbmwdrupcxpg","zwwgloilddclufwze","tyxrlpmcy","wtjtdrlm","edurtetzseifez","yzxogkunvohdmro","livgsrfvgtovcurzq","wmpvjvzljhnaxvp","rqbswlkw","umlzibkkpsyvpdol","jkcmceinlyhi","wlvmfxbleuot","aeloeauxmc","ooyllkxg","wlvmfxbleuot","cuewcvuy","vaufbmwdrupcxpg","bbchthovla","arigdtezmyz","yzxogkunvohdmro","wrszraxxdum","dhmiuqhqlsprxy","xpmxtfyvjrnujyxjh","bfxbncez","cjjkmybleu","mnrdscqkycodx","mzfpofjn","livgsrfvgtovcurzq","shfzcyboj","xozoyaqxtbustrymo","xozoyaqxtbustrymo","orlzzpytpzazxr","filnwifbukdqijgr","fllqjtnxwmfoou","mkmawbogphdttd","rthpxoxyyiy","dkhfozltuckwog","wmpvjvzljhnaxvp","dhmiuqhqlsprxy","yltljjairlkrmdq","cuewcvuy","subzoyxjkfiwmfb","mzvbgcizeeth","narvuaqmmkqhd","tglnshmqlmkqqfbpf","rpesfkhfjucj","xrgfejybbkezgor","vaufbmwdrupcxpg","czlgbqzffodsoxng","suvvqdiceuogcmv","fllqjtnxwmfoou","yltljjairlkrmdq","bubwouozgs","mnrdscqkycodx","rqbswlkw","ooyllkxg","livgsrfvgtovcurzq","rthpxoxyyiy","pyzcbpjhntpefbq","wtjtdrlm","rztcppnmud","inuzvkgolupxelcal","pdxsxjop","wmpvjvzljhnaxvp","xydwvemqvtgvzl","hqpnoczciajvkbdy","rvihrzzkt","jzquemjzpvfbka","gkqrglav","qyaxqaqxiwr","mzvbgcizeeth","umlzibkkpsyvpdol","vaufbmwdrupcxpg","ooyllkxg","arigdtezmyz","bubwouozgs","wtjtdrlm","xozoyaqxtbustrymo","jrocadcojmybpxmuj","rnlryins","fllqjtnxwmfoou","livgsrfvgtovcurzq","czlgbqzffodsoxng","hlcsiukaroscfg","bfxbncez","ainlwrwabqcwq","vaufbmwdrupcxpg","vaufbmwdrupcxpg"};
        String[] sentence2 = {"jrocadcojmybpxmuj","livgsrfvgtovcurzq","mnrdscqkycodx","wgcjlntupylayse","bbchthovla","bfxbncez","ztisufueqzequ","yutahdply","suvvqdiceuogcmv","ainlwrwabqcwq","fquzrlhdsnuwhhu","tglnshmqlmkqqfbpf","vaufbmwdrupcxpg","zwwgloilddclufwze","livgsrfvgtovcurzq","wtjtdrlm","edurtetzseifez","ecqfdkebnamkfglk","livgsrfvgtovcurzq","wmpvjvzljhnaxvp","ryubcgbzmxc","pzlmeboecybxmetz","hqpnoczciajvkbdy","xpmxtfyvjrnujyxjh","zwwgloilddclufwze","khcyhttaaxp","wlvmfxbleuot","jzquemjzpvfbka","vaufbmwdrupcxpg","tglnshmqlmkqqfbpf","mzvbgcizeeth","cjjkmybleu","orlzzpytpzazxr","dhmiuqhqlsprxy","mzfpofjn","bfxbncez","inuzvkgolupxelcal","inhzsspqltvl","wlvmfxbleuot","livgsrfvgtovcurzq","orlzzpytpzazxr","yutahdply","yutahdply","orlzzpytpzazxr","gdziaihbagl","yltljjairlkrmdq","mkmawbogphdttd","aotjpvanljxe","aeloeauxmc","wmpvjvzljhnaxvp","dhmiuqhqlsprxy","yltljjairlkrmdq","dnaaehrekqms","khcyhttaaxp","mzvbgcizeeth","narvuaqmmkqhd","rvihrzzkt","bfufqsusp","xrgfejybbkezgor","vaufbmwdrupcxpg","czlgbqzffodsoxng","jrocadcojmybpxmuj","yltljjairlkrmdq","yltljjairlkrmdq","bubwouozgs","inhzsspqltvl","bsybvehdny","subzoyxjkfiwmfb","livgsrfvgtovcurzq","stkglpqdjzxmnlito","evepphnzuw","xrgfejybbkezgor","rztcppnmud","cjjkmybleu","qyaxqaqxiwr","ibwfxvxswjbecab","xydwvemqvtgvzl","hqpnoczciajvkbdy","tglnshmqlmkqqfbpf","dnaaehrekqms","gkqrglav","bfxbncez","qvwvgzxqihvk","umlzibkkpsyvpdol","vaufbmwdrupcxpg","khcyhttaaxp","arigdtezmyz","bubwouozgs","fllqjtnxwmfoou","xozoyaqxtbustrymo","jrocadcojmybpxmuj","rnlryins","wtjtdrlm","livgsrfvgtovcurzq","gkqrglav","orileazg","uzlxmaoro","ainlwrwabqcwq","vaufbmwdrupcxpg","vaufbmwdrupcxpg"};
        String[][] similarPairs = {{"yutahdply","yutahdply"},{"xozoyaqxtbustrymo","xozoyaqxtbustrymo"},{"xozoyaqxtbustrymo","xozoyaqxtbustrymo"},{"yutahdply","yutahdply"},{"bsybvehdny","bsybvehdny"},{"pzlmeboecybxmetz","pzlmeboecybxmetz"},{"rqbswlkw","rqbswlkw"},{"ryubcgbzmxc","ryubcgbzmxc"},{"umlzibkkpsyvpdol","umlzibkkpsyvpdol"},{"bsybvehdny","bsybvehdny"},{"rqbswlkw","bsybvehdny"},{"pzlmeboecybxmetz","bsybvehdny"},{"ryubcgbzmxc","ryubcgbzmxc"},{"umlzibkkpsyvpdol","ryubcgbzmxc"},{"hqpnoczciajvkbdy","hqpnoczciajvkbdy"},{"vdjccijgqk","vdjccijgqk"},{"rztcppnmud","rztcppnmud"},{"jkcmceinlyhi","hqpnoczciajvkbdy"},{"vdjccijgqk","vdjccijgqk"},{"rztcppnmud","vdjccijgqk"},{"hqpnoczciajvkbdy","hqpnoczciajvkbdy"},{"jkcmceinlyhi","hqpnoczciajvkbdy"},{"suvvqdiceuogcmv","llrzqdnoxbscnkqy"},{"jrocadcojmybpxmuj","jrocadcojmybpxmuj"},{"suvvqdiceuogcmv","suvvqdiceuogcmv"},{"llrzqdnoxbscnkqy","suvvqdiceuogcmv"},{"jrocadcojmybpxmuj","jrocadcojmybpxmuj"},{"dnaaehrekqms","dnaaehrekqms"},{"jzquemjzpvfbka","muaskefecskjghzn"},{"muaskefecskjghzn","iziepzqne"},{"cuewcvuy","dnaaehrekqms"},{"iziepzqne","iziepzqne"},{"muaskefecskjghzn","iziepzqne"},{"jzquemjzpvfbka","iziepzqne"},{"dnaaehrekqms","dnaaehrekqms"},{"cuewcvuy","dnaaehrekqms"},{"rpesfkhfjucj","xpmxtfyvjrnujyxjh"},{"wlvmfxbleuot","bfufqsusp"},{"xpmxtfyvjrnujyxjh","mzfpofjn"},{"rpesfkhfjucj","rpesfkhfjucj"},{"mzfpofjn","rpesfkhfjucj"},{"xpmxtfyvjrnujyxjh","rpesfkhfjucj"},{"bfufqsusp","bfufqsusp"},{"wlvmfxbleuot","bfufqsusp"},{"lkopigreodypvude","lkopigreodypvude"},{"hjogoueazw","hjogoueazw"},{"qvwvgzxqihvk","qvwvgzxqihvk"},{"mzvbgcizeeth","mzvbgcizeeth"},{"mzvbgcizeeth","arigdtezmyz"},{"arigdtezmyz","arigdtezmyz"},{"qvwvgzxqihvk","arigdtezmyz"},{"mzvbgcizeeth","arigdtezmyz"},{"lkopigreodypvude","lkopigreodypvude"},{"hjogoueazw","lkopigreodypvude"},{"tglnshmqlmkqqfbpf","tglnshmqlmkqqfbpf"},{"bbchthovla","bbchthovla"},{"rvihrzzkt","tglnshmqlmkqqfbpf"},{"tglnshmqlmkqqfbpf","tglnshmqlmkqqfbpf"},{"rvihrzzkt","tglnshmqlmkqqfbpf"},{"bbchthovla","bbchthovla"},{"filnwifbukdqijgr","pkirimjwvyxs"},{"gdziaihbagl","gdziaihbagl"},{"hlcsiukaroscfg","hlcsiukaroscfg"},{"gdziaihbagl","orileazg"},{"orileazg","orileazg"},{"gdziaihbagl","orileazg"},{"hlcsiukaroscfg","orileazg"},{"pkirimjwvyxs","pkirimjwvyxs"},{"filnwifbukdqijgr","pkirimjwvyxs"},{"xrgfejybbkezgor","wtjtdrlm"},{"yltljjairlkrmdq","fllqjtnxwmfoou"},{"wtjtdrlm","wtjtdrlm"},{"xrgfejybbkezgor","wtjtdrlm"},{"fllqjtnxwmfoou","fllqjtnxwmfoou"},{"yltljjairlkrmdq","fllqjtnxwmfoou"},{"ecqfdkebnamkfglk","gwkkpxuvgp"},{"inuzvkgolupxelcal","inuzvkgolupxelcal"},{"hgxrhkanzvzmsjpzl","gwkkpxuvgp"},{"cjjkmybleu","cjjkmybleu"},{"yzxogkunvohdmro","yzxogkunvohdmro"},{"inuzvkgolupxelcal","yzxogkunvohdmro"},{"yzxogkunvohdmro","yzxogkunvohdmro"},{"cjjkmybleu","yzxogkunvohdmro"},{"ecqfdkebnamkfglk","ecqfdkebnamkfglk"},{"gwkkpxuvgp","ecqfdkebnamkfglk"},{"hgxrhkanzvzmsjpzl","ecqfdkebnamkfglk"},{"qnjidlmwmxxjgntez","hhrllhedyy"},{"vyrvelteblnqaabc","qnjidlmwmxxjgntez"},{"wzflhbbgtc","wzflhbbgtc"},{"rnlryins","rnlryins"},{"fquzrlhdsnuwhhu","zzdvolqtndzfjvqqr"},{"zzdvolqtndzfjvqqr","bvxiilsnsarhsyl"},{"qnjidlmwmxxjgntez","vyrvelteblnqaabc"},{"vyrvelteblnqaabc","vyrvelteblnqaabc"},{"hhrllhedyy","vyrvelteblnqaabc"},{"rnlryins","vyrvelteblnqaabc"},{"fquzrlhdsnuwhhu","zzdvolqtndzfjvqqr"},{"zzdvolqtndzfjvqqr","zzdvolqtndzfjvqqr"},{"bvxiilsnsarhsyl","zzdvolqtndzfjvqqr"},{"wzflhbbgtc","zzdvolqtndzfjvqqr"},{"wgcjlntupylayse","wgcjlntupylayse"},{"hgtyntdmrgjh","hgtyntdmrgjh"},{"cemnayjhlnj","cemnayjhlnj"},{"wgcjlntupylayse","wgcjlntupylayse"},{"hgtyntdmrgjh","wgcjlntupylayse"},{"cemnayjhlnj","cemnayjhlnj"},{"aeloeauxmc","aeloeauxmc"},{"zwwgloilddclufwze","aeloeauxmc"},{"dkhfozltuckwog","dwojnswr"},{"dkhfozltuckwog","dkhfozltuckwog"},{"dwojnswr","dkhfozltuckwog"},{"aeloeauxmc","aeloeauxmc"},{"zwwgloilddclufwze","aeloeauxmc"},{"aotjpvanljxe","aotjpvanljxe"},{"stkglpqdjzxmnlito","aotjpvanljxe"},{"zfmpxgrevxp","aotjpvanljxe"},{"evepphnzuw","evepphnzuw"},{"rthpxoxyyiy","pyzcbpjhntpefbq"},{"aotjpvanljxe","stkglpqdjzxmnlito"},{"stkglpqdjzxmnlito","stkglpqdjzxmnlito"},{"zfmpxgrevxp","stkglpqdjzxmnlito"},{"rthpxoxyyiy","rthpxoxyyiy"},{"evepphnzuw","rthpxoxyyiy"},{"pyzcbpjhntpefbq","rthpxoxyyiy"},{"czlgbqzffodsoxng","czlgbqzffodsoxng"},{"gkqrglav","gkqrglav"},{"gkqrglav","gkqrglav"},{"czlgbqzffodsoxng","czlgbqzffodsoxng"},{"tyxrlpmcy","tyxrlpmcy"},{"livgsrfvgtovcurzq","livgsrfvgtovcurzq"},{"tyxrlpmcy","tyxrlpmcy"},{"livgsrfvgtovcurzq","livgsrfvgtovcurzq"},{"cufxsgbpjgqvk","cufxsgbpjgqvk"},{"cufxsgbpjgqvk","inhzsspqltvl"},{"shsgrqol","shsgrqol"},{"mnrdscqkycodx","mnrdscqkycodx"},{"inhzsspqltvl","inhzsspqltvl"},{"cufxsgbpjgqvk","inhzsspqltvl"},{"shsgrqol","shsgrqol"},{"mnrdscqkycodx","shsgrqol"},{"rphnhtvnihyfkrgv","rphnhtvnihyfkrgv"},{"edurtetzseifez","edurtetzseifez"},{"yykdqtkkdacpbwtbq","yykdqtkkdacpbwtbq"},{"rphnhtvnihyfkrgv","rphnhtvnihyfkrgv"},{"edurtetzseifez","rphnhtvnihyfkrgv"},{"yykdqtkkdacpbwtbq","yykdqtkkdacpbwtbq"},{"dhmiuqhqlsprxy","dhmiuqhqlsprxy"},{"ztisufueqzequ","ztisufueqzequ"},{"narvuaqmmkqhd","narvuaqmmkqhd"},{"narvuaqmmkqhd","narvuaqmmkqhd"},{"ztisufueqzequ","narvuaqmmkqhd"},{"dhmiuqhqlsprxy","dhmiuqhqlsprxy"},{"wmpvjvzljhnaxvp","wmpvjvzljhnaxvp"},{"ibwfxvxswjbecab","ibwfxvxswjbecab"},{"xydwvemqvtgvzl","wmpvjvzljhnaxvp"},{"wmpvjvzljhnaxvp","wmpvjvzljhnaxvp"},{"xydwvemqvtgvzl","wmpvjvzljhnaxvp"},{"ibwfxvxswjbecab","ibwfxvxswjbecab"},{"mkmawbogphdttd","mkmawbogphdttd"},{"bubwouozgs","mkmawbogphdttd"},{"ainlwrwabqcwq","ainlwrwabqcwq"},{"mkmawbogphdttd","mkmawbogphdttd"},{"bubwouozgs","mkmawbogphdttd"},{"ainlwrwabqcwq","ainlwrwabqcwq"},{"uzlxmaoro","bfxbncez"},{"qyaxqaqxiwr","qyaxqaqxiwr"},{"pdxsxjop","pdxsxjop"},{"pdxsxjop","pdxsxjop"},{"qyaxqaqxiwr","pdxsxjop"},{"bfxbncez","bfxbncez"},{"uzlxmaoro","bfxbncez"},{"subzoyxjkfiwmfb","subzoyxjkfiwmfb"},{"ooyllkxg","ooyllkxg"},{"subzoyxjkfiwmfb","khcyhttaaxp"},{"subzoyxjkfiwmfb","subzoyxjkfiwmfb"},{"khcyhttaaxp","subzoyxjkfiwmfb"},{"ooyllkxg","ooyllkxg"},{"orlzzpytpzazxr","orlzzpytpzazxr"},{"oufzmjgplt","oufzmjgplt"},{"wrszraxxdum","wrszraxxdum"},{"shfzcyboj","shfzcyboj"},{"oufzmjgplt","oufzmjgplt"},{"orlzzpytpzazxr","oufzmjgplt"},{"wrszraxxdum","wrszraxxdum"},{"shfzcyboj","wrszraxxdum"},{"yutahdply","xozoyaqxtbustrymo"},{"umlzibkkpsyvpdol","pzlmeboecybxmetz"},{"hqpnoczciajvkbdy","rztcppnmud"},{"llrzqdnoxbscnkqy","jrocadcojmybpxmuj"},{"cuewcvuy","jzquemjzpvfbka"},{"rpesfkhfjucj","wlvmfxbleuot"},{"lkopigreodypvude","mzvbgcizeeth"},{"tglnshmqlmkqqfbpf","bbchthovla"},{"orileazg","filnwifbukdqijgr"},{"yltljjairlkrmdq","xrgfejybbkezgor"},{"inuzvkgolupxelcal","hgxrhkanzvzmsjpzl"},{"hhrllhedyy","wzflhbbgtc"},{"cemnayjhlnj","hgtyntdmrgjh"},{"dkhfozltuckwog","zwwgloilddclufwze"},{"zfmpxgrevxp","pyzcbpjhntpefbq"},{"gkqrglav","czlgbqzffodsoxng"},{"tyxrlpmcy","livgsrfvgtovcurzq"},{"shsgrqol","cufxsgbpjgqvk"},{"rphnhtvnihyfkrgv","yykdqtkkdacpbwtbq"},{"dhmiuqhqlsprxy","ztisufueqzequ"},{"ibwfxvxswjbecab","xydwvemqvtgvzl"},{"mkmawbogphdttd","ainlwrwabqcwq"},{"pdxsxjop","uzlxmaoro"},{"ooyllkxg","khcyhttaaxp"},{"shfzcyboj","orlzzpytpzazxr"}};
        List<List<String>> similarPairsList=new ArrayList<>();

        for(String[] s: similarPairs){
            similarPairsList.add(Arrays.asList(s));
        }
        System.out.println(areSentencesSimilarTwo(sentence1, sentence2, similarPairsList));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- Space: O(E+N)
        //- Time: O(L+N)
        //
        //#Reference:
        //1681. Minimum Incompatibility
        //430. Flatten a Multilevel Doubly Linked List
        //1066. Campus Bikes II
    }
}
