package interviews;

import com.google.common.collect.Lists;

import java.util.*;

public class E273_PrefixAndSuffixSearchTrie {

//    public static HashMap<String, List<Integer>> mapStrIndex;
    public HashMap<Character, TrieNode> rootPrefix;
    public HashMap<Character, TrieNode> rootSuffix;

    //TrieNode đại diện cho 1 node có các fields
    //- HashMap character của các node bên cạnh nó với value chính là Node đó, thể hiện:
    //+ (current node) có connect đến các (node nào) ==> Thay vì lưu list thì ta lưu hashMap để check connection giữa (current node) và (other node).
    //+ Truy cập được đến các node tiếp theo + Các child của nó
    //
    public static class TrieNode{
        HashMap<Character, TrieNode> child;
        boolean finish=false;
        HashMap<String, List<Integer>> valueIndexs;
        public TrieNode(){
            child=new HashMap<>();
            valueIndexs=new HashMap<>();
        }
    }

    public E273_PrefixAndSuffixSearchTrie(String[] words) {
        //Building Trie with prefix of string
        rootPrefix =new HashMap<>();
        HashMap<String, Integer> wordmap = new HashMap<>();

        for(int i=0;i<words.length;i++){
            wordmap.put(words[i], i);
        }

        for(Map.Entry<String, Integer> entry:wordmap.entrySet()){
            int k=entry.getValue();
            int n=words[k].length();
            HashMap<Character, TrieNode> temp= rootPrefix;
            StringBuilder currentVal=new StringBuilder();

            for(int i=0;i<n;i++){
                Character ch=words[k].charAt(i);
                currentVal.append(ch);
                TrieNode children=temp.get(ch);
                List<Integer> listIndex;

                if(children==null){
                    children=new TrieNode();
                    temp.put(ch, children);
                }
                if(!children.valueIndexs.containsKey(currentVal.toString())){
                    children.valueIndexs=new HashMap<>();
                    listIndex=new ArrayList<>();
                }else{
                    listIndex=children.valueIndexs.get(currentVal.toString());
                }
                listIndex.add(k);
                //Put giá trị index tương ứng vào
                children.valueIndexs.put(currentVal.toString(), listIndex);
                temp=children.child;
                if(i==n-1){
                    children.finish=true;
                }
            }
        }
        //Building Trie with prefix of string
        rootSuffix =new HashMap<>();

        for(Map.Entry<String, Integer> entry:wordmap.entrySet()){
            int k=entry.getValue();
            HashMap<Character, TrieNode> temp = rootSuffix;
            int n=words[k].length();
            StringBuilder currentVal=new StringBuilder();

            for(int i=n-1;i>=0;i--){
                Character ch=words[k].charAt(i);
                currentVal.append(ch);
                TrieNode children=temp.get(ch);
                List<Integer> listIndex;

                if(children==null){
                    children=new TrieNode();
                    temp.put(ch, children);
                }
                if(!children.valueIndexs.containsKey(currentVal.toString())){
                    children.valueIndexs=new HashMap<>();
                    listIndex=new ArrayList<>();
                }else{
                    listIndex=children.valueIndexs.get(currentVal.toString());
                }
                listIndex.add(k);
                //Put giá trị index tương ứng vào
                children.valueIndexs.put(currentVal.toString(), listIndex);
                temp=children.child;
                if(i==n-1){
                    children.finish=true;
                }
            }
        }
    }

    public List<Integer> findPrefix(String prefix){
        HashMap<Character, TrieNode> temp= rootPrefix;
        int n=prefix.length();
        List<Integer> rsIndexs=new ArrayList<>();

        for(int i=0;i<n;i++){
            char ch=prefix.charAt(i);
            TrieNode children = temp.get(ch);

            if(children==null){
                return rsIndexs;
            }else{
                temp=children.child;
            }
            if(i==n-1){
                return children.valueIndexs.get(prefix);
            }
        }
        return rsIndexs;
    }

    public List<Integer> findSuffix(String suffix){
        HashMap<Character, TrieNode> temp= rootSuffix;
        int n=suffix.length();
        List<Integer> rsIndexs=new ArrayList<>();

        for(int i=0;i<n;i++){
            char ch=suffix.charAt(i);
            TrieNode children = temp.get(ch);

            if(children==null){
                return rsIndexs;
            }else{
                temp=children.child;
            }
            if(i==n-1){
                return children.valueIndexs.get(suffix);
            }
        }
        return rsIndexs;
    }

    public int f(String pref, String suff) {
        List<Integer> listIndexPrefix=findPrefix(pref);
        List<Integer> listIndexSuffix=findSuffix(new StringBuilder(suff).reverse().toString());
        TreeSet<Integer> rs=new TreeSet<>();

        HashSet<Integer> indexSet = new HashSet<>(listIndexPrefix);

        for(Integer i: listIndexSuffix){
            if(indexSet.contains(i)){
                rs.add(i);
            }
        }
        return rs.isEmpty()?-1:rs.last();
    }
    public static void main(String[] args) {
//        String[] words={"apple","WordFilter"};
        String[] words={"abbba","abba"};
        E273_PrefixAndSuffixSearchTrie ep=new E273_PrefixAndSuffixSearchTrie(words);
//        System.out.println(ep.f("a","e"));
        System.out.println(ep.f("ab","ba"));
        //** Đề bài
        //- Yêu cầu bài này là tìm index của word Match với cả (prefix, suffix)
        //--> Nếu trùng word thì return mã(index)
        //- Số lượng queries rất lớn.
        //
        //** Bài này tư duy như
        //Cách 1:
        //1.
        //1.1,
        //["WordFilter", "f"]
        //
        //W -> o -> r -> d -> F
        //R -> d -> f
        //
        //find(W, r)
        //
        //			       W
        //                  \
        //                    o (Lưu danh sách index đi qua điểm wo này) [0,2,3] ==> Chú ý cần lưu string vì o --> Có thể thuộc rất nhiều các string khác nhau.
        //                      \
        // 				         r
        //					      \
        //					       g Lưu danh sách index của word có thể đi qua đến điểm g này
        //
        //
        //
        //- Làm sao để Match việc
        //Find_prefix(w, WordFilter)
        //Find_suffix(r, WordFilter)
        //
        //VD:
        //Giả sử
        //Find_prefix(worg, WordFilter) --> Có tồn tại.
        //Find_sufix(rg, WordFilter)
        //
        //Worg --> phải nối được đến rg
        //+ Có thể giao nhau (Thậm chí là bao nhau)
        //+ Có thể là không giao nhau
        //
        //1.2, Làm sao để có thể cùng 1 word + ra kết quả index của word đó?
        //- Traverse hết đến cuối --> Mới ra được danh sách từ.
        //
        //- Nếu tại o mà lưu nhiều index map kiểu:
        //('O', list_index) -->
        //word(Worg) --> bị trùng với word(org)
        //==> Lưu dạng map(wo, list(1,2,3)) với map(or,list(1,0,3,9))
        //+ Wor --> Index của nó bao gồm cả index của cả Wo
        //
        //1.3, Làm sao để tìm (prefix + suffix) cùng lúc
        //- Ta tìm index gắn với prefix + tìm index gắn với suffix
        //--> xong sau đó tìm sự trùng nhau
        //[[ ["apple"] ], ["a", "e"]]
        //
        //1.4, Để tìm được dựa trên sufix thì ta cần Build thêm 1 true dạng tạo
        //Wab
        //--> Search theo baW.
        //** Note:
        //- Cần truncate số words trùng nhau.
        //
        //1.5, Complexity
        //- Time complexity : O(N)
        //- Space complexity : O(N*L)
        //
        //- N : N is size of input word arrays.
        //- L : max size of all words.
        //Cách 2:
        //2.
        //2.1,
        //- Key tư duy ở đây là thay vì việc tìm một cách rời rạc prefix và suffix
        //==> Ta sẽ cộng string tìm cách build trie để có thể (prefix và suffix) cùng 1 lúc.
        //- Có 2 cách build trie:
        //+ Lưu dạng < (prefix + reverse(suffix)) > ==> Giống việc build 2 trie vậy
        //+ Lưu dạng < suffix (tất cả các cases) + prefix >
        //VD:
        //"apple{apple",
        //"pple{apple",
        //"ple{apple",
        //"le{apple",
        //"e{apple",
        //"{apple"
        //2.2, Code
        //- Ở đây có 1 cách code khá lạ:
        //word= "abc{"
        //==> duplicate lên
        //==>
        //- "abc{abc{" biến thành:
        //+ "bc{abc{"
        //+ "c{abc{"
        //- Thực chất ở đây ta có thể index++ dần dần nhưng:
        //+ Để duyệt hết chuỗi (word*2) mà không cần phải dùng cả (word*2)
        //==> Ta sẽ duyệt dạng (i < 2*n-1; i++)
        //+ j=(i%word.length())
        //
        //Cách 3: Brute force
        //3.
        //3.1,
        //- Với prefix + suffix --> Ta vét cạn các cases có thể xảy ra
        //==> Map<String, Integer>
        //3.2, Complextity
        //- Time complexity (query) : O(1)
        //- Space complexity : O(N*L)
        //
        //Cách 4: Xét lần lượt
        //startWith + endWith ==> Slow
        //3.1, Complextity
        //- Time complexity (query) : O(N*L)
        //- Space complexity : O(1)
        //
        //#Reference
        //746. Min Cost Climbing Stairs
        //211. Design Add and Search Words Data Structure
    }
}
