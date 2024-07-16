package contest;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.HashMap;

public class E148_ConstructStringWithMinimumCost {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finished=false;
        int cost=Integer.MAX_VALUE;

        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static boolean addWord(String word, TrieNode node){
        int n=word.length();
        boolean isExists=true;

        //node_1 -t-> node_2 -r-> node_3
        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                children=new TrieNode();
                isExists=false;
            }
//            System.out.println(isExists);
            node.child.put(word.charAt(i), children);
            node=children;
        }
        return isExists;
    }

    public static boolean searchWord(StringBuilder word, TrieNode node){
        int n=word.length();

        //node_1 -t-> node_2 -r-> node_3
        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                return false;
            }
            node=children;
        }
        return true;
    }

    public static int[] memo;
    public static int rs;

    public static void findAllCombinationString(
            int index, int[] costs, String[] words, StringBuilder curRs,
            TrieNode node, int cost, int n){
        if (index==n) {
            rs=Math.min(rs, cost);
            System.out.println(rs);
            return;
        }
//        if(index<n&&memo[index]!=Integer.MAX_VALUE){
//            return;
//        }
        for(int i=0;i<words.length;i++){
            int curIndex=index+words[i].length()-1;
            if(curIndex>=n){
                continue;
            }
            StringBuilder nextString = curRs.append(words[i]);
            int curLen=nextString.length();
//            boolean exists=memo[curIndex]!=Integer.MAX_VALUE;
//            if(!exists){
//                exists=searchWord(nextString, node);
//            }
            boolean exists=searchWord(nextString, node);
//            if(memo[curIndex]!=Integer.MAX_VALUE){
//                continue;
//            }
//            System.out.printf("String: %s, exists: %s\n", nextString, exists);
            int curCost=memo[curIndex];
            int nextCost=cost+costs[i];
            //&&curCost>nextCost
            if(exists&&curCost>nextCost){
                memo[curIndex]=nextCost;
                findAllCombinationString(curIndex+1, costs, words, nextString, node, cost+costs[i], n);
            }
            nextString.delete(curLen-words[i].length(), curLen);
        }
    }

    public static Pair<Boolean, TrieNode> searchWordOptimization(String word, TrieNode node){
        int n=word.length();

        //node_1 -t-> node_2 -r-> node_3
        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                return new Pair<>(false, node);
            }
            node=children;
        }
        return new Pair<>(true, node);
    }
    public static HashMap<Pair<Integer, String>, TrieNode> memoWord;

    public static void findAllCombinationStringOptimization(
            int index, int[] costs, String[] words,
            TrieNode node, int cost, int n){
        if (index==n) {
            rs=Math.min(rs, cost);
            System.out.println(rs);
            return;
        }
//        if(index<n&&memo[index]!=Integer.MAX_VALUE){
//            return;
//        }
        for(int i=0;i<words.length;i++){
            int curIndex=index+words[i].length()-1;
            if(curIndex>=n){
                continue;
            }
//            boolean exists=memo[curIndex]!=Integer.MAX_VALUE;
//            if(!exists){
//                exists=searchWord(nextString, node);
//            }
//            Pair<Integer, String> curPair=new Pair<>(i, words[i]);
            TrieNode nextNode;
            boolean exists;
//            if(memoWord.containsKey(curPair)){
//                nextNode=memoWord.get(curPair);
//                exists=true;
//            }else{
//                Pair<Boolean, TrieNode> existNodes=searchWordOptimization(words[i], node);
//                nextNode=existNodes.getValue();
//                exists=existNodes.getKey();
//                memoWord.put(curPair, existNodes.getValue());
//            }
            Pair<Boolean, TrieNode> existNodes=searchWordOptimization(words[i], node);
            nextNode=existNodes.getValue();
            exists=existNodes.getKey();
//            if(memo[curIndex]!=Integer.MAX_VALUE){
//                continue;
//            }
//            System.out.printf("String: %s, exists: %s\n", nextString, exists);
            int curCost=memo[curIndex];
            int nextCost=cost+costs[i];
            //&&curCost>nextCost
            if(exists&&curCost>nextCost){
                memo[curIndex]=nextCost;
                findAllCombinationStringOptimization(curIndex+1, costs, words, nextNode, cost+costs[i], n);
            }
        }
    }

    public static int minimumCostTLE(String target, String[] words, int[] costs) {
        int n=target.length();
        memoWord=new HashMap<>();
        TrieNode root=new TrieNode();
        addWord(target, root);
//        System.out.println(searchWord("abcdef", root));
        memo=new int[n];
        rs=Integer.MAX_VALUE;
        Arrays.fill(memo, Integer.MAX_VALUE);
        findAllCombinationString(0, costs, words, new StringBuilder(), root, 0, n);
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static int minimumCostTLE1(String target, String[] words, int[] costs) {
        int n=target.length();
        memoWord=new HashMap<>();
        TrieNode root=new TrieNode();
        addWord(target, root);
//        System.out.println(searchWord("abcdef", root));
        memo=new int[n];
        rs=Integer.MAX_VALUE;
        Arrays.fill(memo, Integer.MAX_VALUE);
        findAllCombinationStringOptimization(0, costs, words, root, 0, n);
        return rs==Integer.MAX_VALUE?-1:rs;
    }

    public static boolean addWordRefer(String word, int cost, TrieNode node){
        int n=word.length();
        boolean isExists=true;

        for(int i=0;i<n;i++){
            TrieNode children=node.child.get(word.charAt(i));

            if(children==null){
                children=new TrieNode();
                isExists=false;
            }
//            System.out.println(isExists);
            node.child.put(word.charAt(i), children);
            node=children;
        }
        if(node.finished){
            node.cost=Math.min(node.cost, cost);
        }else{
            node.finished=true;
            node.cost=cost;
        }
        return isExists;
    }

    public static int[] memoRefer;
    //Space: O(n)
    public static int solution(int index, String target, int n, TrieNode root){
        if(index>=n){
            return 0;
        }
        //Time: O(n)
        if(memoRefer[index]!=Integer.MAX_VALUE){
            return memoRefer[index];
        }
        int curRs=Integer.MAX_VALUE;
        TrieNode curNode = root;

        //Time:
        //i: 0 --> n-1 ==> O(n)
        for(int i=index;i<n;i++){
            char nextChar=target.charAt(i);
            if(!curNode.child.containsKey(nextChar)){
                break;
            }
            curNode=curNode.child.get(nextChar);
            if(curNode.finished){
                int subRs=solution(i+1, target, n, root);
                if(subRs!=Integer.MAX_VALUE){
                    curRs=Math.min(curRs, subRs+curNode.cost);
                }
            }
        }
        return memoRefer[index]=curRs;
    }

    public static int minimumCost(String target, String[] words, int[] costs) {
        int n=target.length();
        int m=words.length;
        memoWord=new HashMap<>();
        TrieNode root=new TrieNode();
        //Time: O(m*k)
        //Space: O(m*k)
        for(int i=0;i<m;i++){
            addWordRefer(words[i], costs[i], root);
        }
        //Space: O(n)
        memoRefer=new int[n];
        rs=Integer.MAX_VALUE;
        Arrays.fill(memoRefer, Integer.MAX_VALUE);
        int rs = solution(0, target, n, root);
        return rs==Integer.MAX_VALUE?-1: rs;
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string target, an array of strings words, and an integer array costs, both arrays of the same length.
        //- Imagine an empty string s.
        //- You can perform the following operation any number of times (including zero):
        //  + Choose an index i in the range [0, words.length - 1].
        //  + Append words[i] to s.
        //  + The cost of operation is costs[i].
        //* Return the (minimum cost) to make (s) equal to (target). If it's not possible, return -1.
        //
        //Example 1:
        //
        //Input: target = "abcdef", words = ["abdef","abc","d","def","ef"], costs = [100,1,1,10,5]
        //Output: 7
        //Explanation:
        //The minimum cost can be achieved by performing the following operations:
        //Select index 1 and append "abc" to s at a cost of 1, resulting in s = "abc".
        //Select index 2 and append "d" to s at a cost of 1, resulting in s = "abcd".
        //Select index 4 and append "ef" to s at a cost of 5, resulting in s = "abcdef".
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= target.length <= 5 * 10^4
        //1 <= words.length == costs.length <= 5 * 10^4
        //1 <= words[i].length <= target.length
        //+ The total sum of words[i].length is less than or equal to 5 * 10^4.
        //target and words[i] consist only of lowercase English letters.
        //1 <= costs[i] <= 10^4
        //+ Target --> lower case: 0 -> 25
        //+ length(target) = 10^4 ==> Time: O(n*k)
        //+ sum(length(wordS) = 10^4
        //
        //- Brainstorm
        //- Trie ==> Check prefix
        //- For loop ==> To get new word
        //
        //- Tối ưu memo
        //- Tối ưu search:
        //  + Tránh searc duplicated
        //  Ex:
        //  val = abcdef
        //  word[0] = abcd
        //  word[1] = abcdef ==> Nếu lại phải search tiếp "abcd" lại thì quá tệ
        //- Truyền trie node tiếp ==> Để search tiếp
        //
        //==> Làm kiểu trên vẫn bị TLE thôi
        //
        //- Build từ tree từ target string ==> Loop sẽ vẫn bị timeout
        //  + Build để check từng substring trong target có match với tree từ word đó không.
        //  + Vì build ntn ==> Cần finished variable để xác định
        //Ex:
        //target = "abcdef"
        //+ i=2
        // + "(abc)def"
        // + i=2 có thể chọn next char:
        //  + j= (3 => n-1)
        //  + Nếu bất kỳ character nào không match ==> Ignore case (i==2) hoàn toàn
        //  + Loop đến khi:
        //      + root.get(nextChar).finish == true: thì ta có thể lấy price được rồi.
        //
//        String target = "abcdef";
//        String [] words = {"abdef","abc","d","def","ef"};
//        int[] costs = {100,1,1,10,5};
//        String target = "ztggqggdhzzkykzqrhbrfkj";
//        String [] words = {"r","k","gqggd","j","g","f","zzky","z","k","k","hb","t","y","q","ggdhzzkykzqr","r","h","z","z","z"};
//        int[] costs = {42,26,17,23,33,30,25,2,13,15,19,29,42,32,15,25,23,32,36,40};
        String target = "choknckutugcjkaezgaupbvloqcxmruwvmkfxzihgqesjlctqhykofqbhbnxxerbxujyacuxduvbgansmgtfcsvokugikuifgbabkiwbnksrihamrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjrygnjsmqljcfhbrpslhwezm";
        String [] words = {"c","h","o","k","n","c","k","u","t","u","gcjkaez","g","a","upb","vl","o","q","c","x","m","r","u","w","v","m","k","f","xzihgqesjlctqhykofqbhbnxxerbxujyacuxduvbgansm","g","t","f","c","s","vokugikuifgbabk","i","w","b","n","k","s","r","i","h","a","m","r","w","o","h","f","q","t","q","t","i","j","kuw","u","i","z","v","k","y","w","f","d","uixezqbz","m","r","e","c","r","b","c","i","d","z","g","d","b","y","k","x","c","h","e","o","j","c","u","r","w","b","p","d","n","r","e","q","d","w","b","d","tmsnrwocfvwqtlgjioaqi","b","u","i","a","l","nvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbd","j","n","a","r","k","l","r","o","g","w","q","e","r","t","s","yrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvl","a","z","s","g","x","q","t","w","a","w","j","q","j","j","q","i","d","n","p","n","w","utpyeiidcpqmyqljqwmodfpemfoapeaknfqc","grmvllzfh","b","u","r","n","p","r","c","x","g","i","n","i","f","g","k","g","f","u","w","n","m","d","j","r","y","g","n","j","s","m","q","l","j","c","f","h","b","r","p","s","l","h","w","e","z","m","c","h","o","k","nckutugcjkaezgaupb","v","l","o","q","cxmruwvmk","f","x","z","i","h","g","q","e","s","j","l","c","t","q","h","y","k","o","f","q","b","h","b","n","x","x","e","r","b","x","u","j","y","a","c","u","x","d","u","v","b","g","a","n","s","m","g","t","f","c","s","v","o","k","u","g","i","k","u","i","f","g","b","abk","i","w","b","n","k","s","r","i","h","a","mrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqi","b","u","ialnvekbduycsk","x","b","d","j","dshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllkt","d","y","v","t","w","y","z","x","ufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnp","n","w","u","t","p","y","e","i","i","d","c","p","q","m","yqljqwmodfpemf","o","a","p","e","a","k","n","f","q","c","g","r","m","v","l","l","z","f","h","b","u","r","n","p","r","c","x","g","i","n","i","f","g","k","g","f","u","w","n","m","d","j","r","y","g","n","j","s","m","q","l","j","c","f","h","b","r","p","s","l","h","w","e","z","m","ayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpezt","m","eowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxgin","bapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdin","fwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregw","ctkeqt","fbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqm","zm","nhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpy","vydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtw","sbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzk","sxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnoso","diomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfll","profebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskz","oylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlso","fnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowa","eqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikq","zwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflf","qdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiy","uskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzf","frllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaali","gvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpy","no","arklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdio","kbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazs","mpyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprc","npmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrm","ygnjsmqljcfhb","servudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprj","jqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjryg","ajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhb","rcxginifgkgfuwnmdjrygnjsmqljcfh","norqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdva","fgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftq","bgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpye","ympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjh","dnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfo","mvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljq","qjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwn","lzfhburnprcxginifgkgfuwnmdjrygnjsmqljcfhbrpsl","ixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmh","akhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzr","wmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjrygnjsmqljc","apsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjem","dshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivik","zidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmod","redklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfy","xkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzls","yvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhbur","ukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxgi","xhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnza","nffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzg","ibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifg","kbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvy","jlctqhykofqbhbnxxerbxujyacuxduvbgansmgtfcsvokugikuifgbabkiwbnksrihamrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdw","nugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffgl","vxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshk","gnj","wodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjrygnjsmqljcfhbrpslhw","yjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqx","vedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbo","eunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpe","wsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvnin","zmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvf","zrsmnspbibtxydeosdpnsysabpe","soukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdva","cbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwu","opjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjff","oqcxmruwvmkfxzihgqesjlctqhykofqbhbnxxerbxujyacuxduvbgansmgtfcsvokugikuifgbabkiwbnksrihamrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaa","kjwhoye","slrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwg","omyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfu","pnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjaf","iikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxob","bajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffgl","frexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhc","jivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjr","wbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkp","yhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayym","svcvklprofebpkqunjgorjfulgfobfre","gpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqb","jnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqj","rxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayym","wqkmgsbiikqincqxrcjsuyqizsgwopjyfeqie","nhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdy","iqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqx","xmruwvmk","cbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovi","zzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvl","pvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkj","wawjqjjqidnpnwutpye","regwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuz","edklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpezt","lazsg","nfqcgrmvllzfhburnprcxginifgkgf","kwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccw","ddyghownfvdzdfbodlccweoyuzhvnnorqxe","ykofqbhbnxxerbxujyacuxduvbgansmgtfcsvokugikuifgbabkiwbnksrihamrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxu","wszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguah","mebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzid","zfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpez","wqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxginifgkgfuwnmdjrygnjsmqljcfhbrpslhwezm","oqcxmruwvmkfxzihgqesjlctqhykofqbhbnxxerbxujyacuxduvbgansmgtfcsvokugikuifgbabkiwbnksrihamrwohfqtqtijkuwuizvkywfduixezqbzmrecrbcidzgdbykxcheojcurwbpdnreqdwbdtmsnrwocfvwqtlgjioaqibuialnvekbduycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstalj","nfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzf","yqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnp","jjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrm","ffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyz","ycskxbdjdshqibgeekjhjlvjukcxfukuypgvumebxkokcbdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanba","oyktdljzxcekfympcenpdfjpyhsakhzbsqzgwgmzedjmpntmjzlsqbjpfqhlxjjklgvrxnsyxwyhmasmapxyozdkbeomunmjkevxobapsoukztuddyghownfvdzdfbodlccweoyuzhvnnorqxeqbgczxzhmhzcrflpfwkwqmnfgauohmxhuskzsbmxwhclfuayymgjacbeuzsxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmp","bdjnarklrogwqertsyrslrayhxtfgckbgnffgyjnwejjkpzwuyzfnctxkozxbkkcmisixhbukiqntfgjivbideauqbculvxhgdnassdiwzsvedhgycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiw","tqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldw","xamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcxgini","uyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugj","vninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoq","sxyapsmqnwnrpvzfdbzluqrypjhtuyefrllktdyvtwyzxufgyaksrnpmxnosoarjjemnkfiluprjgypcbzzkbushszwbgwcfwsdvaaliqweanbajnhyzrsmnspbibtxydeosdpnsysabpeztrmkleglrgruetfernnnzawjvninkcqogmpnngrzfnedpshkjwhoyetwnyvfdinugjlsoliongkntorvjafmdxhkeowasgfkuwcixwruuoeqnrqnoqjffglhvzidqxavbcxamoqawtvlazsgxqtwawjqjjqidnpnwutpyeiidcpqmyqljqwmodfpemfoapeaknfqcgrmvllzfhburnprcx","ycykiyqtepsgoovinjqxonskfxecrfvndnlsfwszaykojlfvupblbzxzkafrexvuhnftwpxtiyvzzlqguvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimq","uvjnmckqqgmthhppzwceoegkuhdncvhyivikwymhpqtvrmsxhglxjtgkrprmkjouregwjlrmvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpsl","mvjuyxaiojplehyfvbpqqmpnolthtymvclnvnwidlyjyctkeqtssoknpnrwfzjkppkldepbjxvtqjyswpslcewmhasoypheddivujfnqkhtakbbsnbcrialggctlaavmbtgyjhagpsvcvklprofebpkqunjgorjfulgfobfredklzydyykbgsrkuruisggrnrsngbyoqdiomyesyjwxqtanwodmdservudhotmgkpompyhniiusrkpuftqvydgsiotqcipffreozimkojkweaynlbfiwjnmidguahffhbfvvjpjmgyaqkdtbowqkmgsbiikqincqxrcjsuyqizsgwopjyfeqieunhftugfyxrnadvgnsrgleqisdbghhkgmflfcsjrxearoxajufirrrhnjgormpgplcpezkcimqpkvryjhcdzqfegpufqrsmkggdvjfldwesoylwsaryuyjfllajwgjvlstaljbvlvwwysgooyktdljzxcek"};
        int[] costs = {5048,5233,2469,5721,4153,1397,3163,994,1172,2162,1923,793,1577,2719,258,1002,1102,4818,1405,935,3054,5397,1207,5055,3483,1050,3438,389,5316,1504,3197,2787,4719,1122,1548,5004,4600,4332,3932,5531,3845,5611,1760,5151,927,5754,3561,2573,3304,5801,5798,4760,1375,3401,3948,4316,1118,2275,1746,1445,649,5137,1543,4759,1862,2072,641,3762,4795,1220,3019,786,2398,3710,2758,977,824,3706,1880,605,4548,1,1339,3057,5492,2788,5833,1300,3842,1372,4665,2753,1513,2442,2525,1485,1560,2166,1875,2428,3931,4362,4362,641,28,514,492,1942,3575,3033,5071,1414,1443,3257,1561,1001,320,4381,5311,1020,1271,4717,2964,2327,5114,366,2745,4769,528,1934,2179,1376,3197,3065,3953,599,2103,5656,2717,5743,1412,3787,3272,2613,1507,5464,1863,141,5154,2105,4023,5740,3991,1764,4212,5639,5387,1380,4726,5181,1203,1782,3881,3722,5868,5784,4883,3720,1487,4169,582,4012,3631,2220,3084,4705,1185,1978,5238,4043,3097,2220,197,5273,2780,5843,108,3546,4481,4136,5136,5226,3257,2396,1405,999,5273,707,4398,1644,450,1210,58,5763,3809,3310,402,3457,5719,4705,1947,667,3625,3325,4206,2479,1154,2609,44,1325,3343,1574,2730,1447,5547,1806,3317,70,4374,2746,2988,5259,4787,865,2954,5223,1861,4937,4892,4881,2732,4234,5356,4816,4570,883,3320,390,551,1104,1104,2795,2322,1060,451,2027,3254,1905,5788,4091,434,365,3880,4547,5051,4659,240,5270,2875,3834,5439,722,3573,1645,5154,5096,4155,1248,3344,2040,5264,5242,2149,2798,3124,4071,546,4065,2722,3582,964,2025,2643,5119,953,1474,3248,5399,2627,3209,3835,4606,1644,308,2904,668,3759,3641,3654,1640,4049,357,4157,1815,986,3823,3124,1459,3433,395,2350,1357,990,1220,923,3316,2064,5058,570,4251,1906,2948,754,5741,4898,33,1556,3597,1428,5614,4190,2500,2569,2198,5321,1085,4373,4927,4702,1505,2284,5771,3915,209,604,3007,857,5830,4704,3324,253,4958,290,4239,622,2547,1130,4338,4659,792,2012,3646,5801,3485,3753,711,4485,1332,4391,395,5618,4091,333,3078,834,4113,4381,3086,4658,3620,1399,5808,205,191,2745,1349,1515,5201,1783,2226,1824,1668,4147,489,3620,473,1781,4478,2322,3500,4385,4351,4286,660,5401,2131,1176,3286,2190,4141,5376,2796,972,1032,3295,136,4537,5042,1322,3230,5554,2606,5452,116,3223,2108,4226,5456,4532,3706,2059,2967,1662,1695,5763,3412,5794,5700,5306,1446,3710,4502,4351,1112,4631,2473,557,13,51,3321,3284,1582,2003,561,3214,3952,1259,5572,876,2605,2850,2991,32,1694,3302,1300,234,4827,3711,2152,2901,5784,2085,1650,1613,1963,4690,3335,3062,1017,1269,3856,4512,4322,4210};
        //Expected result: 84
        //
//        System.out.println(minimumCostTLE(target, words, costs));
//        System.out.println(minimumCostTLE1(target, words, costs));
        System.out.println(minimumCost(target, words, costs));
        //
        //1.1, Optimization
        //1.2, Complexity
        //- K is average length of the words
        //- m is the number of the words
        //- Space: O(k*m + n)
        //- Time: O(k*m + n)
        //
        //- Time complexity:
        //+ O(target.length()*words.length) ==> ???
        //- Space complexity:
        //+ O(target.length()^2) ==> ???
        //
        //#Reference:
        //555. Split Concatenated Strings
        //2368. Reachable Nodes With Restrictions
        //2898. Maximum Linear Stock Score
    }
}
