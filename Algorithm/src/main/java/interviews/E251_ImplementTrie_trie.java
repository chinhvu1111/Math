package interviews;

import java.util.HashMap;

public class E251_ImplementTrie_trie {


    public static class TrieNode{
        public boolean finished;
        //VD:
        // abt
        // abe
        //==> Nếu nối ở a thì ta có thể đi đến 2 nodes như (t,e) ta sẽ lưu ở hashmap là được.

        public HashMap<Character, TrieNode> children;

        public TrieNode() {
            children=new HashMap<>();
        }

        public boolean isFinished() {
            return finished;
        }

        public HashMap<Character, TrieNode> getChildren() {
            return children;
        }
    }

    public static class Trie{
        TrieNode root;
        public Trie() {
            root=new TrieNode();
        }

        public void insert(String word) {
            int n=word.length();
            TrieNode node=root;

            //Đoạn này lúc nào cũng đi từ root của tree
            //VD:
            //abds
            //- Nếu a không có trong child của root --> add thêm a vào
            //- Sau đó sẽ gán node=child (Node hiện tạt mà sau a) / a chính là cạnh giữa (root node và child)
            //VD:
            //+ root --a--> child-1
            //+ root --b--> child-2
            //Tức là tất cả mọi điểm đều xuất phát từ root node vì ta đang xét prefix tức là abds thì từ left --> right
            //<=> Tương ứng với từ root ---> sang các node khác.
            for(int i=0;i<n;i++){
                char ch=word.charAt(i);
                TrieNode child=node.getChildren().get(ch);

                if(child==null){
                    child=new TrieNode();
                    node.children.put(ch, child);
                }
                node=child;
            }
            node.finished=true;
        }

        public boolean search(String word) {
            TrieNode node=root;
            int n=word.length();

            //Tìm abds có tồn tại hay không
            for(int i=0;i<n;i++){
                char ch= word.charAt(i);
                TrieNode child=node.getChildren().get(ch);

                if(child!=null){
                    node=child;
                }else return false;
            }
            //Nó có phải kết thúc của 1 word hay không
            return node.finished;
        }

        public boolean startsWith(String prefix){
            TrieNode node=root;
            int n=prefix.length();

            //Tìm abds có tồn tại hay không
            for(int i=0;i<n;i++){
                char ch= prefix.charAt(i);
                TrieNode child=node.getChildren().get(ch);

                if(child!=null){
                    node=child;
                }else return false;
            }
            //Nó có phải kết thúc của 1 word hay không
            return true ;
        }

    }
    public static void main(String[] args) {
        //
        //** Đề bài:
        //- Implement tree thoả mãn các operation như:
        //+ insert
        //+ search whole word
        //+ search prefix word
        //
        //** Bài này tư duy như sau:
        //1.
        //1.1, Cấu tạo Trie
        //- Các cạnh chính là các từ (a,b,c):
        //+ Thế nên mỗi node --> Có thể có nhiều cạnh khác nhau <=> Hash<char, child>
        //+ Thoả mãn nhiều cạnh + nhiều children
        //- Sẽ bao gồm flag để đánh dấu là từ đó có là cạnh sát leaf node hay không.
        //==> Nếu node là cạnh sát leaf node --> Nó là nguyên 1 word (Có thể search nguyên 1 word được)
        //- Có thể tưởng tượng từ root --> leaf node chứa : các edges biểu diễn cho các từ hoàn chỉnh
        //==> Nếu từ đó được insert lại thì sẽ không tốn công insert thêm nữa.
        //1.2,
        //- Insert operation:
        //Đoạn này lúc nào cũng đi từ root của tree
        //VD:
        //abds
        //- Nếu a không có trong child của root --> add thêm a vào
        //- Sau đó sẽ gán node=child (Node hiện tại mà sau a) / a chính là cạnh giữa (root node và child)
        //VD:
        //+ root --a--> child-1
        //+ root --b--> child-2
        //Tức là tất cả mọi điểm đều xuất phát từ root node vì ta đang xét prefix tức là abds thì từ left --> right
        //<=> Tương ứng với từ root ---> sang các node khác.
        //
        //- Thao tác tìm kiếm:
        //+ Tìm whold word : Cần dùng finished check
        //+ Tìm prefix : Thì chỉ cần return xem có tìm được hay không thôi.
        //
        //1.3,
        //- Time complexity:
        //+ Insert O(m) : m là chiều dài word
        //+ Search O(m) : m là chiều   dài word
        //- Space complexity: O(m*n) :m là chiều dài word, n là số string.
        //+ Trong thực tế số string sẽ giảm đi rất nhiều vì số string trùng nhau
        //+ String càng dài thì trùng nhau càng nhiều.
        //
        //1.4, Dấu hiệu nhận biết dùng TrieNode
        //- Search theo prefix.
        //
        //1.5, Format chính của Trie:
        //+ HashMap<Char, TrieNode> child (Danh sách cạnh con của node đó)
        //+ finised : boolean : Để kiểm tra cả word
        //+ root : Là node gốc : Thường để check prefix
        //
        //1.5,
        //#Reference:
        //211. Design Add and Search Words Data Structure
        //642. Design Search Autocomplete System
        //648. Replace Words
    }
}
