package interviews;

import java.util.HashSet;
import java.util.Set;

public class E89_1CountNumberOfDistinctSubstringInAString {

    public static int distinctSubstring(String str){
        Set<String> result=new HashSet<>();

        for(int i=0;i<str.length();i++){
            for(int j=i;j<str.length();j++){
                String s=str.substring(i, j+1);

                result.add(s);
            }
        }
        return result.size();
    }

    public static int distinctSubstringUsingHashSet(String str){
        HashSet<String> distinctString=new HashSet<>();

        for(int i=0;i<str.length();i++){
            String s="";
            for(int j=i;j<str.length();j++){
                s+=str.charAt(j);
                distinctString.add(s);
            }
        }
        return distinctString.size();
    }

    public static class TrieNode{
        TrieNode children[];
        boolean isEnd;

        public TrieNode(TrieNode[] children) {
            this.children = new TrieNode[26];
        }
    }

    public static int distinctSubstringCountTrie(String str){

        for(int i=0;i<str.length();i++){
            for(int j=i;j<str.length();j++){

            }
        }

        return 1;
    }

    public static int distinctSubstringCountRollingHash(String str){
        int n=str.length();
        int p=31;
        int m=1_000_000_009;
        int[] p_pow =new int[n];
        int rs=0;

        p_pow[0]=1;

        for(int i=1;i<n;i++){
            p_pow[i]=(p_pow[i-1]*p)%m;
        }
        int[] prefixHash =new int[n+1];

        for(int i=0;i<n;i++){
            prefixHash[i+1]=(prefixHash[i]+ (str.charAt(i)-'a'+1)*p_pow[i] ) %m;
        }
        for(int l=1;l<=n;l++){
            Set<Long> set=new HashSet<>();
            for(int j=0;j<=n-l;j++){
                int k=j+l-1;

                long hashCurrentStr=(prefixHash[k+1]+m-prefixHash[j])%m;
                hashCurrentStr=(hashCurrentStr*p_pow[n-j-1])%m;

                set.add(hashCurrentStr);
            }
            rs+=set.size();
        }

        return rs;
    }

    public static void main(String[] args) {
//        String s="aaaa";
        String s="aaaabc";
        //Bài này ta tư duy như sau:
        //+ Thực ra dạng bài kiểu này thường ta sẽ dùng tư duy kết hợp để làm:
        //(i) (0--> n-1) sẽ kết hợp với các (j tương ứng)
        //Cách 1:
        //Time Complexity: O(n3logn)
        //Auxiliary Space: O(n),  since n extra space has been taken.
        //+ Cách này đơn thuần xét range + sub String 1 cách bình thường
        //Push vào Set ---> Tính tổng các distinct string.
        System.out.println(distinctSubstring(s));
        //Cách 2:
        //Time Complexity: O(n2)
        //Auxiliary Space: O(n)
        //+ Cách này có thể dẫn đến MLE (Memory limit exceeded) trong trường hợp very large strings
        //+ Có thể có tổng cộng n(n+1)/2 strings <=> O(n/2)
        //---> Mỗi String có thể có (1 --> n length) <=> trung bình O(n/2)
        //---> Tổng độ phức tạp trung bình O(n^3)
        System.out.println(distinctSubstringUsingHashSet(s));
        //Cách 3:
        //Trie
        //
        //Cách 4: Dùng rolling hash
        //1,  Ở đây chú ý với những bài dùng prefix_sum[i]
        //--> Để tính toán dễ dàng hơn
        //VD:
        // prefix_sum[j] - prefix_sun[i] = Ra giá trị bắt đầu tại (i)
        //--> prefix_sum[n] ==> prefix_sum[n+1]
        //--> Ta bắt buộc phải tính lệch 1 đơn vị bên phải
        //VD: Phỏng vấn hôm nọ nếu tính lệch sang bên phải 1 đơn vị --> Mọi thứ sẽ dễ dàng hơn.
        //
        //2, Với bài toán bên trên:
        //Ta có công thức tính toán hash của substring giữa (i...j):
        //* hash(S[i...j]) = ( k:(i-->j) p^(k-i) ) mod m
        //CM: Công thức trên được suy ra từ 2 hiệu của hash_value:
        //
        //+ hash( s[i...j] ) * p^i (CHỖ NÀY SẼ BÙ VÀO p^k-i )
        //----> Code chỉ cần (nhớ công thức * p^i)
        //
        //= ( |(i...j) S[k] * p^k ) mod m
        //= hash ( S[0...j] ) - hash( S[0...i-1]) mod m.
        //
        //+ hash_value[n+1] cũng được tính lệch --> Việc tính bên dưới có sự thay đổi 1 chút
        //về giá trị (k =i+l-1) ==> Tính lệch nên lấy (k+1)
        //3, p_pow[n] --> Cần lưu để dùng lại
        System.out.println(distinctSubstringCountRollingHash(s));
    }
}
