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
        //B??i n??y ta t?? duy nh?? sau:
        //+ Th???c ra d???ng b??i ki???u n??y th?????ng ta s??? d??ng t?? duy k???t h???p ????? l??m:
        //(i) (0--> n-1) s??? k???t h???p v???i c??c (j t????ng ???ng)
        //C??ch 1:
        //Time Complexity: O(n3logn)
        //Auxiliary Space: O(n),  since n extra space has been taken.
        //+ C??ch n??y ????n thu???n x??t range + sub String 1 c??ch b??nh th?????ng
        //Push v??o Set ---> T??nh t???ng c??c distinct string.
        System.out.println(distinctSubstring(s));
        //C??ch 2:
        //Time Complexity: O(n2)
        //Auxiliary Space: O(n)
        //+ C??ch n??y c?? th??? d???n ?????n MLE (Memory limit exceeded) trong tr?????ng h???p very large strings
        //+ C?? th??? c?? t???ng c???ng n(n+1)/2 strings <=> O(n/2)
        //---> M???i String c?? th??? c?? (1 --> n length) <=> trung b??nh O(n/2)
        //---> T???ng ????? ph???c t???p trung b??nh O(n^3)
        System.out.println(distinctSubstringUsingHashSet(s));
        //C??ch 3:
        //Trie
        //
        //C??ch 4: D??ng rolling hash
        //1,  ??? ????y ch?? ?? v???i nh???ng b??i d??ng prefix_sum[i]
        //--> ????? t??nh to??n d??? d??ng h??n
        //VD:
        // prefix_sum[j] - prefix_sun[i] = Ra gi?? tr??? b???t ?????u t???i (i)
        //--> prefix_sum[n] ==> prefix_sum[n+1]
        //--> Ta b???t bu???c ph???i t??nh l???ch 1 ????n v??? b??n ph???i
        //VD: Ph???ng v???n h??m n??? n???u t??nh l???ch sang b??n ph???i 1 ????n v??? --> M???i th??? s??? d??? d??ng h??n.
        //
        //2, V???i b??i to??n b??n tr??n:
        //Ta c?? c??ng th???c t??nh to??n hash c???a substring gi???a (i...j):
        //* hash(S[i...j]) = ( k:(i-->j) p^(k-i) ) mod m
        //CM: C??ng th???c tr??n ???????c suy ra t??? 2 hi???u c???a hash_value:
        //
        //+ hash( s[i...j] ) * p^i (CH??? N??Y S??? B?? V??O p^k-i )
        //----> Code ch??? c???n (nh??? c??ng th???c * p^i)
        //
        //= ( |(i...j) S[k] * p^k ) mod m
        //= hash ( S[0...j] ) - hash( S[0...i-1]) mod m.
        //
        //+ hash_value[n+1] c??ng ???????c t??nh l???ch --> Vi???c t??nh b??n d?????i c?? s??? thay ?????i 1 ch??t
        //v??? gi?? tr??? (k =i+l-1) ==> T??nh l???ch n??n l???y (k+1)
        //3, p_pow[n] --> C???n l??u ????? d??ng l???i
        System.out.println(distinctSubstringCountRollingHash(s));
    }
}
