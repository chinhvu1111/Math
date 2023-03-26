package E1_trie_topic;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class E1_ShortEncodingOfWords {

    public static class TrieNode{
        HashMap<Character, TrieNode> child;

        public TrieNode(){
            child=new HashMap<>();
        }
    }

    public static boolean addWord(String word, TrieNode node){
        int n=word.length();
        boolean isExists=true;
        System.out.printf("%s, ",word);

        //node_1 -t-> node_2 -r-> node_3
        for(int i=n-1;i>=0;i--){
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

    public static int minimumLengthEncoding(String[] words) {
        Arrays.sort(words, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length()-o1.length();
            }
        });
        TrieNode root=new TrieNode();
        StringBuilder result=new StringBuilder();

        for(String word:words){
            if(word.length()==0){
                continue;
            }
            TrieNode node=root.child.get(word.charAt(word.length()-1));
//            System.out.printf("%s %s\n", word.charAt(0), mapNode);
            if(node==null){
                node=new TrieNode();
            }
            root.child.put(word.charAt(word.length()-1), node);

            boolean isExist=addWord(word, node);
            if(!isExist){
                if(result.length()!=0){
                    result.append("#");
                }
                result.append(word);
            }
        }
//        System.out.println(result);
        return result.length()+1;
    }

    public static void main(String[] args) {
//        String[] words=new String[]{"time", "me", "bell"};
//        String[] words=new String[]{"me", "time"};
//        String[] words=new String[]{"feipyxx","e"};
        String[] words=new String[]{"ctxdic","c"};
        System.out.println(minimumLengthEncoding(words));
        //** Đề bài:
        //-
        //Cho words = ["time", "me", "bell"]
        //+ Tìm chuỗi tham chiếu có độ dài ngắn nhất (shortest reference string)
        //+ Sao cho nó chứa đầy đủ các words
        //VD: "time#bell#"
        //index=[0,2,5]
        //+ Tương ứng với thứ tự xuất hiện của các word cho đến next '#'
        //
        //** Bài này tư duy như sau:
        //1.
        //1.0, Suy luận:
        //
        //["time", "me", "bell"]
        //- Nếu 1 từ mới 'time' + chuỗi đã lập = null, ta sẽ tạo string dạng : time#
        //- 1 từ trùng 'me' trong chuỗi đã lập : time#
        //- Nếu 1 từ mới 'bell' + chuỗi đã lập <> null, ta sẽ append vào:
        //+ Beginning of string
        //+ End of string
        //==> Add ở đâu cũng được vì nó ngăn cách bằng dấu # ==> Kết quả thu được (substring của chuỗi) về sau sẽ [như nhau].
        //
        //+ Nếu từ mới trùng 1 phần với từ cũ:
        //+ Từ mới timecd ==> Ta cần phải check xem có match được không
        //time#abc
        //
        //1.1, Implementation Trie bài này dạng prefix
        //time#abc build như sau:
        //- Để build được Trie có chút đặc biệt:
        //Format
        //Node-1 -t-->Node-2 -t-> Node3 -m-> Node4 -e-> Node 5
        //+ Mỗi node có dạng:
        //Hash<Char, Node> map;
        //
        //1.2 Kiểm tra từ mới có trùng trie đã có hay không?
        //- Có nhiều word khác nhau --> Chung prefix
        //- Nhưng điểm đến là word khác nhau.
        //--> Không vấn đề lắm --> Vẫn có thể build chung 1 root được.
        //
        //- Làm sao để check được key đứng giữa word.
        //VD: time#timeasb
        //==> Lần tiếp theo ta điền "im"
        //==> Làm sao để bắt đầu từ node chứ i
        //--> Solution: Lưu vào HashMap<Char, Node> là được.
        //
        //1.3, Case đặc biệt
        //+ "me", "na", "time"
        //+ me#na ==> Để trống đằng trước ==> Lúc này chọn đằng trước hay đằng sau mới có giá trị.
        //+ "ctxdic","c" ==> do trùng hashmap 'c' (hashtable)
        //+ Nếu ta prefix --> "ctxdic" thì sẽ bị case hashtable bị update lại ở 'c'
        //==> suffix string
        //- Tức là add character từ cuối lên (n-1 --> 0)
        //VD:
        //Word : add('d'), add('r'), add('o'), add('w')
        //--> Không cần check exist của child.
        //
        //1.4, Format implementation đặc biệt
        //VD:
        //"ctxdic", "me"
        //- Sẽ luôn có root node :
        //Word : ctxdic
        //+ node.put('c',node)
        //+ node.put('t',node)
        //+ node.put('x',node)
        //+ node.put('d',node)
        //Word : me
        //+ node.put('m',node)
        //+ node.put('e',node)
        //....................
        //--> Không tạo global hashmap.
        //
        //1.5, Complexity:
        //- Time complexity :
        //- Space complexity :
        //#Reference:
        //821. Shortest Distance to a Character
        //2108. Find First Palindromic String in the Array
        //447. Number of Boomerangs
        //2501. Longest Square Streak in an Array
    }
}
