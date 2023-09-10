package E1_String;

import java.util.PriorityQueue;

public class E4_DetermineIfTwoStringsAreClose {

    public boolean closeStrings(String word1, String word2) {
        int n=word1.length();
        int m=word2.length();

        if(n!=m){
            return false;
        }
        int[] countS=new int[26];
        int[] countT=new int[26];
        //Space: O(26)
        PriorityQueue<Integer> countSortS=new PriorityQueue<Integer>();
        //Space: O(26)
        PriorityQueue<Integer> countSortT=new PriorityQueue<Integer>();

        //Time : O(n)
        for(int i=0;i<n;i++){
            countS[word1.charAt(i)-'a']++;
        }
        //Time : O(m)
        for(int i=0;i<m;i++){
            if(countS[word2.charAt(i)-'a']!=0){
                countT[word2.charAt(i)-'a']++;
            }else{
                return false;
            }
        }
        for(int i=0;i<26;i++){
            if(countS[i]!=0){
                countSortS.add(countS[i]);
            }
        }
        for(int i=0;i<26;i++){
            if(countT[i]!=0){
                countSortT.add(countT[i]);
            }
        }
        if(countSortS.size()!=countSortT.size()){
            return false;
        }
        while(!countSortS.isEmpty()){
            int curCountS=countSortS.poll();
            int curCountT=countSortT.poll();
            if(curCountS!=curCountT){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        //* Requirement
        //- Given s and t
        //- s and t called close if we can attain one from the other by using the following operations:
        //+ Operation 1: Swap any two existing characters : Swap 2 character bất kỳ
        //For example, abcde -> aecdb
        //+ Operation 2: Transform every occurrence of one (existing) character into another (existing) character, and do the same with the other character.
        //--> Chuyển toàn bộ ký tự từ a -> b và b -> a
        //For example, aacabb -> bbcbaa (all a's turn into b's, and all b's turn into a's)
        //
        //** Idea
        //1.
        //1.0, Idea
        //- Constraint
        //1 <= word1.length, word2.length <= 105
        //word1 and word2 contain only lowercase English letters.
        //
        //- Brainstorm
        //Ex:
        //word1 = "cabbba", word2 = "abbccc"
        //Apply Operation 1: "ca(b)bb(a)" -> "caabbb" (swap i=2 and i=5)
        //+ Mục đích của swap chính là để có được (order) như mong muốn
        //Apply Operation 2: "caabbb" -> "baaccc" ( c <-> b )
        //Apply Operation 2: "baaccc" -> "abbccc" ( a <-> b )
        //- Bài này chỉ cần return true/false (Được hay không)
        //cabbba
        //caabbb
        //baaccc
        //abbccc
        //
        //- Ở đây ta có thể (swap/ replace) lúc nào cũng được
        //- s và t có thể (swap/ replace) cùng lúc nếu cùng ra 1 result --> return true.
        //- Replace thì ta sẽ replace sao cho số lượng count các số của 2 words là như nhau
        //==> Ý tưởng là nếu chúng giống nhau --> Ta có thể swap được ==> Swap là 1 operation rất tự do
        //--> Lúc swap thì làm gì cũng được
        //
        //Ex:
        //word1 = "cabbba", word2 = "abbccc"
        //+ word1
        //count['c']=1
        //count['a']=2
        //count['b']=3
        //
        //+ word2
        //count['a']=1
        //count['b']=2
        //count['c']=3
        // c, a, b
        // a, b, c
        //==> nhìn như thế này thì làm thế nào để xác định là có thể replace được hay không?
        // c <-> b
        // b, a, c
        // a, b, c
        //
        // a <-> b
        // a, b, c
        // a, b, c
        //==> Swap linh tinh cũng được ==> Miễn là count bằng nhau
        //
        //- Idea
        //- Tính count từng character của từng string
        //- Add vào priority queue ==> Sort
        //- Sau đó so sánh count 2 bên nếu chúng giống nhau hoàn toàn : return true
        //
        //1.1, Optimization
        //
        //1.2, Complexity
        //- Time complexity : O(N+M)
        //- Space complexity : O(1)
        //
        //#Reference:
        //859. Buddy Strings
        //1247. Minimum Swaps to Make Strings Equal
    }
}
