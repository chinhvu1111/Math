package interviews;

import java.util.*;

public class E254_StringMatchingInAnArray {

    public static class TrieNode{
        public boolean isAdaptSubstring;
        public HashMap<Character, TrieNode> children;

        public TrieNode() {
            children=new HashMap<>();
        }

        public boolean isAdaptSubstring() {
            return isAdaptSubstring;
        }

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }
    }

    public static List<String> stringMatchingSlow(String[] words) {
        TrieNode root=new TrieNode();
        TrieNode root1=new TrieNode();

        for(String s:words){
            int len=s.length();
            StringBuilder subString=new StringBuilder();

            for(int j=len-1;j>=0;j--){
                subString.append(s.charAt(j));
                insertingWord(root, subString.toString(), j==0);
                insertingWord1(root1, subString.toString(), j==0);
//                System.out.println(subString);
            }
        }
        HashSet<String> result=new HashSet<>();
        for(String s:words){
            if(searchSubString(root, s)){
                result.add(s);
            }
        }
        for(String s:words){
            StringBuilder s1=new StringBuilder(s);
            s1.reverse();
            if(searchSubString(root1, s1.toString())){
                result.add(s);
            }
        }
        return new ArrayList<>(result);
    }

    public static void insertingWord1(TrieNode root,String word, boolean isOriginal){
        TrieNode node=root;
        int n=word.length();

        for(int i=0;i<n;i++){
            Character ch=word.charAt(i);
            TrieNode children=node.getChildren().get(ch);

            if(children==null){
                children=new TrieNode();
                node.getChildren().put(ch, children);
            }
            node=children;
//            System.out.printf("%s", ch);
        }
//        System.out.println();
        if(isOriginal){
            node.isAdaptSubstring =true;
        }
    }

    public static void insertingWord(TrieNode root,String word, boolean isOriginal){
        TrieNode node=root;
        int n=word.length();

        for(int i=n-1;i>=0;i--){
            Character ch=word.charAt(i);
            TrieNode children=node.getChildren().get(ch);

            if(children==null){
                children=new TrieNode();
                node.getChildren().put(ch, children);
            }
            node=children;
//            System.out.printf("%s", ch);
            node.isAdaptSubstring = node.isAdaptSubstring || !isOriginal || i!=0;
        }
//        System.out.println();
        //Old idea
//        if(isOriginal){
//            node.isAdaptSubstring =true;
//        }
    }

    public static boolean searchSubString(TrieNode root, String s){
        TrieNode node=root;
        int n=s.length();

        for(int i=0;i<n;i++){
            char ch=s.charAt(i);
            TrieNode children=node.getChildren().get(ch);

            if(children==null){
                break;
            }
            node=children;
        }
        if(node.isAdaptSubstring){
            return true;
        }
        return false;
    }

    public static List<String> stringMatching(String[] words) {
        TrieNode root=new TrieNode();

        for(String s:words){
            int len=s.length();
            StringBuilder subString=new StringBuilder();

            for(int j=len-1;j>=0;j--){
                subString.append(s.charAt(j));
                insertingWord(root, subString.toString(), j==0);
//                System.out.println(subString);
            }
        }
        HashSet<String> result=new HashSet<>();
        for(String s:words){
            if(searchSubString(root, s)){
                result.add(s);
            }
        }
        return new ArrayList<>(result);
    }

    public static void main(String[] args) {
//        String[] s= new String[]{"mass","as","hero","superhero"};
        String[] s= new String[]{"leetcode","et","code"};
//        String[] s= new String[]{"leetcoder","leetcode","od","hamlet","am"};
//        System.out.println(stringMatchingSlow(s));
        System.out.println(stringMatching(s));
        //
        //** Đề bài:
        //- Tìm các chuỗi s1 là substring của chuỗi bất kỳ s(i) nằm trong tập hợp
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Tư duy build Trie khá cồng kềnh
        //- Vì cần tìm s1 có là substring của bất cứ chuỗi s2 nào có trong words nên:
        //--> Ta nghĩ đến việc build Trie cho cả words
        //VD: mass, as --> Thì nếu build 2 caí này thì branch lúc này sẽ là 2 branch riêng rẽ nhau
        //+ (x) --m--> (x) --a--> (x) --s--> (x) --s--> (x)
        //+ (x) --a--> (x) --s--> (x)
        //==> Nó thuộc 2 child khác nhau nên muốn kiểm tra có là substring của nhau hay ko.
        //1.2, Chia mass (s2) thành nhiều chuỗi con để có thể nối nhánh với as
        //VD: mass
        //ass ==> Lúc này là có thể nối nhánh với as
        //ss
        //s
        //==> Khi nối nhánh xong thì sau as(s) sẽ được nối thêm ký tự s
        //** Ta có thể thêm điều kiện ( node.children.size() != 0 )
        //- Lúc đó thì ta sẽ lấy.
        //
        //1.3, Nếu chỉ xét như bên trên thì kết quả trả về chưa đủ
        //VD: hero, superhero
        //- Với ví dụ bên trên thì sau hero không thể có ký tự nào nữa dù chia.
        //==> Ta sẽ chia ngược build Trie suffix --> Từ cuối lên để check tương tự như trên thì trả được kết quả đúng.
        //- Chỉ đánh finished <=> Cái ta đanh xét chính là 1 string (j==0)
        //- Để có thể giảm thời gian --> Không cần substring --> Ta có thể cộng StringBuilder từ cuối lên
        //--> Sau đó build cây ngược là đưọc ==> Lúc xét sẽ không cần check ngược nữa (0 --> n-1) 1 cách bình thường.
        //** Thực ra tư duy + StringBuilder hơi cồng kềnh ==> Ta có thể code trực tiếp để giảm độ trễ đi.
        //
        //#Reference
        //1409. Queries on a Permutation With Key
        //1605. Find Valid Matrix Given Row and Column Sums
        //691. Stickers to Spell Word
        //1449. Form Largest Integer With Digits That Add up to Target

    }
}
