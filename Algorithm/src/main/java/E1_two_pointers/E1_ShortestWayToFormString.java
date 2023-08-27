package E1_two_pointers;

import java.util.HashMap;
import java.util.TreeSet;

public class E1_ShortestWayToFormString {

    public static int shortestWay(String source, String target) {
        int n=source.length();
        int m=target.length();

        //Space : O(n)
        HashMap<Character, TreeSet<Integer>> valToIndex=new HashMap<>();

        //Time : O(n)
        for(int i=0;i<n;i++){
            TreeSet<Integer> indexes=valToIndex.get(source.charAt(i));
            if(indexes==null){
                indexes=new TreeSet<>();
            }
            indexes.add(i);
            //Time: O(log(n))
            valToIndex.put(source.charAt(i), indexes);
        }

        int rs=0;
        int prevIndex=0;

        //Time : O(m)
        for(int i=0;i<m;i++){
            TreeSet<Integer> listIndexes=valToIndex.get(target.charAt(i));

            if(listIndexes==null){
                return -1;
            }
            //Time : O(log(n))
            Integer index=listIndexes.ceiling(prevIndex);

            if(index==null){
                rs++;
                i--;
                prevIndex=0;
                // System.out.printf("%s %s %s\n", prevIndex, i, c);
            }else{
                prevIndex=index+1;
            }
        }
        rs++;
        return rs;
    }

    public static int shortestWayDuplicateSource(String source, String target) {
        int n=source.length();
        int m=target.length();

        boolean[] exists =new boolean[26];

        for(int i=0;i<n;i++){
            exists[source.charAt(i)-'a']=true;
        }
        //Time O(M)
        for(int i=0;i<m;i++){
            if(!exists[target.charAt(i)-'a']){
                return -1;
            }
        }
        StringBuilder temp=new StringBuilder(source);
        int rs=1;

        //Time : O(M)
        while(!isSubSequence(target, temp.toString())){
            //Time : O(n)
            temp.append(source);
            rs++;
        }
        return rs;
    }

    public static boolean isSubSequence(String s, String t){
        int i=0, j=0;
        int n=s.length();
        int m=t.length();

        while(i<n&&j<m){
            if(s.charAt(i)==t.charAt(j)){
                i++;
            }
            j++;
        }
        return i==n;
    }

    public static int shortestWayTwoPointers(String source, String target) {

        // Boolean array to mark all characters of source
        boolean[] sourceChars = new boolean[26];
        for (char c : source.toCharArray()) {
            sourceChars[c - 'a'] = true;
        }

        // Check if all characters of target are present in source
        // If any character is not present, return -1
        for (char c : target.toCharArray()) {
            if (!sourceChars[c - 'a']) {
                return -1;
            }
        }

        int count=0;
        int sourceIterator=0;
        int m=source.length();

        for(char c: target.toCharArray()){
            if(sourceIterator==0){
                count++;
            }
            while(source.charAt(sourceIterator)!=c){
                sourceIterator=(sourceIterator+1)%m;
                if(sourceIterator==0){
                    count++;
                }
            }
            sourceIterator=(sourceIterator+1)%m;
        }

        // Return count
        return count;
    }

    public static void main(String[] args) {
        //** Requirement
        //- Subsequence of target là các chuỗi con của chuỗi source + giữ nguyên thứ tự.
        //- Given source and target string
        //* return the minimum number of subsequences of "source" such that their concatenation equals "target"
        //Ex:
        //Input: source = "abc", target = "abcbc"
        //Output: 2
        //Explanation: The target "abcbc" can be formed by "abc" and "bc", which are subsequences of source "abc".
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //+ 1 <= source.length, target.length <= 1000
        //+ source and target consist of lowercase English letters.
        //
        //- Brainstorm
        //- Thay vì cố cộng string --> Ta sẽ xét các subsequence của chuỗi của target
        //Ex:
        //source="xyz", target = "xzyxz"
        //- Liên quan đến việc check xem có phải subsequence hay không?
        //xyz
        //xzyxz
        //- Nếu ta xét dần dần từng subsequence 1 thì sao:
        //VD:
        //xyz
        //xzyxz --> Đến y là ta cần phải 1 subsequence (xz)
        //remaining string = yxz --> Ta cần tiếp 1 subsequence (y)
        //remaining string = xz --> Ta cần tiếp 1 subsequence (xz)
        //==> Tức là quy luật quét dần dần cho đến khi không thể tìm được subsequence thoả mãn.
        //
        //1.1, Implementation
        //Ex:
        //source="xyz", target = "xzyxz"
        //x : xyz
        //y : yz
        //z : z
        //
        //Ex: xyzx
        //- traverse : xzyxz
        // [x:{0, 3}, y: {1}, z:{2}}
        // target = "xzyxz"
        // x : index=0, prevIndex=0
        // z : index=2, prevIndex=2
        // y : index=1 < prevIndex ==> rs++, prevIndex=0 ==> Remove
        //+ Giả sử xzyxz --> xzxxz
        // x : index= {0,3} , prevIndex=2 (2<3) ==> prevIndex ==> cần update index > old prevIndex nhưng vẫn cần thuộc index ={0, 3}
        //
        //
        //1.1, Optimization
        //1.2, Complexity:
        //- Space : O(1)
        //- Time : O((n+m)*log(n))
        //
        //* Method 2:
        //2.0,
        //- Vì target có thể tạo từ nhiều source (Trong trường hợp 1 source không được)
        //  + Ta sẽ duplicate source + concat vào để khi (target is a subsequence of source)
        //- Write method to check subsequence of 2 words.
        //
        //2.1, Optimization
        //2.2, Complexity:
        //- n is the length of s
        //- m is the length of t
        //- Space : O(n*m) (StringBuilder)
        //- Time : O(m + n*m)
        //+ Complexity of append method of string builder:
        //  + O(k) : n is the length of the string to be added.
        //
        //* Method 3:
        //3.0
        //- Vẫn là tư duy đấy thôi --> Nhưng ta sẽ dùng pointer dạng rotate
        //==> Tìm đến khi lấu được ký tự đó mới thôi ==> Nếu nó rotate về 0 <=> Ta cần new subsequence
        //--> count++
        //
        //3.1, Optimization
        //3.2, Complexity:
        //- n is the length of s
        //- m is the length of t
        //- Space : O(n*m) (StringBuilder)
        //- Time : O(m + n*m)
        //+ Complexity of append method of string builder:
        //  + O(k) : n is the length of the string to be added.
        //
        //#Reference:
        //870. Advantage Shuffle
        //1624. Largest Substring Between Two Equal Characters
        //881. Boats to Save People
        //2599. Make the Prefix Sum Non-negative
        //2131. Longest Palindrome by Concatenating Two Letter Words
        //2567. Minimum Score by Changing Two Elements
        //2571. Minimum Operations to Reduce an Integer to 0
        //1717. Maximum Score From Removing Substrings
        //1554. Strings Differ by One Character
        String source = "xyz", target = "xzyxz";
        System.out.println(shortestWay(source, target));
        System.out.println(shortestWayDuplicateSource(source, target));
        System.out.println(shortestWayTwoPointers(source, target));
    }
}
