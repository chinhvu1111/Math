package E1_hashmap;

import java.util.*;

public class E10_GroupShiftedStrings {

    public static List<List<String>> groupStrings(String[] strings) {
        Arrays.sort(strings, (a, b) -> {
           if(a.length()<b.length()){
               return 1;
           }else if(a.length()>b.length()){
               return -1;
           }
           return a.compareTo(b);
        });
        for(String s: strings){
            System.out.printf("%s,", s);
        }
        System.out.println();
        List<List<String>> rs=new ArrayList<>();
        List<String> curList=new ArrayList<>();

        if(strings.length!=0){
            curList.add(strings[0]);
        }
        for (int i = 1; i < strings.length; i++) {
            if(isSameGroup(strings[i-1], strings[i])){
                curList.add(strings[i]);
            }else{
                rs.add(curList);
                curList=new ArrayList<>();
                curList.add(strings[i]);
            }
        }
        if(curList.size()!=0){
            rs.add(curList);
        }
        return rs;
    }

    //s < s1
    public static boolean isSameGroup(String s, String s1){
        if(s.length()!=s1.length()){
            return false;
        }
        if(s.length()==1){
            return true;
        }
        int range=s1.charAt(0)-s.charAt(0);

        //a-b-c-d-e-f
        //afc, ecf
        for(int i=1;i<s.length();i++){
            if((s.charAt(i)-'a'+range)%26!=s1.charAt(i)-'a'){
                return false;
            }
        }
        return true;
    }
    public static int getSub(char a, char b){
//        if(a=='z'){
//            a='a'-1;
//        }
//        if(b=='z'){
//            b='a'-1;
//        }
//        int temp=a-'a';
//        int aInt=Math.min(a-'a', b-'a');
//        int bInt=Math.max(temp, b-'a');
        int aInt=a-'a';
        int bInt=b-'a';
        //'a', 'z'
        if(aInt<bInt){
            return aInt+26-bInt;
        }
        return ((aInt-bInt)+26)%26;
    }

    public static String getHashWord(String word){
        StringBuilder hashKey=new StringBuilder();
        int n=word.length();

        for(int i=1;i<n;i++){
            char c=word.charAt(i);
            char prevC=word.charAt(i-1);
            hashKey.append((char)((c-prevC+26)%26));
        }
        return hashKey.toString();
    }

    public static List<List<String>> groupStringsHashMethod(String[] strings) {
        //Space : O(n*k)
        Map<String, List<String>> mapHashToList = new HashMap<>();

        //Time : O(n)
        for(String s:strings){
            //TIme : O(k)
            String hashKey=getHashWord(s);
            List<String> oldElements=mapHashToList.get(s);

            if(oldElements==null){
                oldElements=new ArrayList<>();
            }
            oldElements.add(s);
            mapHashToList.put(hashKey, oldElements);
        }
        List<List<String>> rs=new ArrayList<>();

        //Time : O(n)
        for(String key: mapHashToList.keySet()){
            rs.add(mapHashToList.get(key));
        }
        return rs;
    }

    public static void main(String[] args) {
        //** Requirement
        //- String s có thể shift với mỗi character sang thằng character sau nó 1 unit
        //Ex:
        //"abc" can be shifted to be "bcd".
        //- Ta có thể shift thành sequence:
        //Ex: "abc" -> "bcd" -> ... -> "xyz".
        //- Given string[] array
        //* Group tất cả các string cũng sequence vào thành cách groups
        //
        //** Idea
        //1.
        //1.0,
        //- Constraint
        //1 <= strings.length <= 200
        //1 <= strings[i].length <= 50
        //strings[i] consists of lowercase English letters.
        //- Length của mỗi string cũng không quá lớn
        //- Length của strings[] cũng không quá lớn
        //==> có thể giải O(n^2) --> O(n) được.
        //
        //- Brainstorm
        // a - b - c -...-z - a -...
        //- 2 string cùng group <=> ( abs(hiệu) các character của chúng == nhau)
        //Ex:
        //abc : xyz
        //+ x-a=y-b=z-c
        //
        //- Vì các string khác length --> Khác group
        //==> Ta có thể sort theo length + compare(string)
        //
        //ace --> bdf
        //- Nếu tư duy kiểu hashing thì length=50 thì có rất nhiều permuation đặc biệt ==> Lưu hết thì không được
        //acb ==> 021
        //bdc ==> 132 = 021 + (100 + 10 +1) = 021 + 111
        //==> %111
        //
        //- Có trường hợp 021 --> Có thể là số có length <3 không?
        //  + Ex: cb=21 ==> Chỗ này nó sẽ %11(ab) = (10)ba
        //- Vấn đề là max_length<=50 : 1111...111 số có 50 chữ số quá lớn
        //
        //- Special case:
        //- a-z=1 ==> chứ không phải theo ascii (z-a=2x)
        //
        //Ex:
        //a-z=-25 <> b-a=1
        //a-y=-24 <> c-a=2
        //b-y=-23 <> c-b=1
        //+ (+26)%26
        //
        //- Sort không giải quyết vấn đề:
        //Ex:
        //a,b,c
        //a và b không cùng group ==> break
        //But a and c are the same group ==> WRONG
        //
        //- Hint:
        //- Để s và s1 cùng sequence:
        //  + Các character của chúng phải cùng rule
        //** ==> Cố biến đổi mọi string cùng group ==> Có same hash_key ==> Generate hash_function()
        //Ex:
        //s[3]-s[2]+s[1]-s[0] = s1[3]-s1[2]+s1[1]-s1[0]
        //==> Thành ra là phải tính hashKey của string ==> Sau đó là phân group
        //
        //- Hash key function sẽ implement ntn?
        //- Hash key, ta sẽ không dùng append int vì : l-a=11
        // ==> Nó sẽ trùng với (abc): (b-a).append(c-b)=11
        //  + Ta sẽ +26 vì số có thể là số <0
        //  + %26 vì có thể hash>26 --> Không thể cast ra (lower case character)
        System.out.printf("hash(abc)= %s\n",getHashWord("abc"));
        System.out.printf("hash(al)= %s\n",getHashWord("al"));
        //1.1, Optimization
        //
        //1.2, Complexity
        //- n is the number of words
        //- k is the (average/ max) length of each word
        //- Space: O(n*k)
        //- Time: O(n*k)
        String[] strings = {"abc","bcd","acef","xyz","az","ba","a","z"};
        System.out.println(groupStrings(strings));
        System.out.println(getSub('a', 'z'));
        System.out.println(getSub('z', 'a'));
        System.out.println(getSub('b', 'a'));
        System.out.println(getSub('a', 'b'));
        System.out.println(('z'-'a'+1)%25-('a'-'a'));
        System.out.println("az"+" "+ "ba");
        System.out.println(isSameGroup("az", "ba"));
        System.out.println("ba"+" "+ "az");
        System.out.println(isSameGroup("ba", "az"));
        System.out.println("az"+" "+ "ba");
        System.out.println(getSub('b', 'a'));
        System.out.println(getSub('a', 'z'));
        System.out.println(isSameGroup("bcd", "xyz"));
        System.out.println("bcd"+" "+ "xyz");
        System.out.println(getSub('x', 'b'));
        System.out.println(getSub('y', 'c'));
        System.out.println(getSub('z', 'd'));
        //Ex:
        //abz, bca
        System.out.println(isSameGroup("abz", "bca"));
        System.out.println(isSameGroup( "bca", "abz"));
        System.out.println(isSameGroup( "aiplrzejplumda", "fnuqwejouqzrif"));
        System.out.println(isSameGroup( "fnuqwejouqzrif", "aiplrzejplumda"));
        String s="dgwlvcyubde";
        String s1="ilbqahdzgij";
        //a-b-c-d-e-f-g-h-i-j-k-l-m-n-o-p-q-r-s-t-u-v-w-x-y-z
        //w > b : b-a + (26-w)
        //a < b : b-a
        //c > a :
        System.out.println("lebzshcb"+", "+"ohecvkfe");
        System.out.println(isSameGroup( "lebzshcb", "ohecvkfe"));
        for(int i=0;i<s.length();i++){
            //lebzshcb
            //ohecvkfe
            //l -> o : 2
            //e -> h
            //b -> e
            //z -> c
            //s -> v
            //h -> k
            System.out.printf("s: '%s', s1: '%s' = %s; ", s.charAt(i), s1.charAt(i), s.charAt(i)-s1.charAt(i));
        }
        System.out.println();
        System.out.println(getSub('a','y'));
        //abc -> cda
        System.out.println("====================");
        String[] strings1= {"fpbnsbrkbcyzdmmmoisaa","cpjtwqcdwbldwwrryuclcngw","a","fnuqwejouqzrif","js","qcpr","zghmdiaqmfelr","iedda","l","dgwlvcyubde","lpt","qzq","zkddvitlk","xbogegswmad","mkndeyrh","llofdjckor","lebzshcb","firomjjlidqpsdeqyn","dclpiqbypjpfafukqmjnjg","lbpabjpcmkyivbtgdwhzlxa","wmalmuanxvjtgmerohskwil","yxgkdlwtkekavapflheieb","oraxvssurmzybmnzhw","ohecvkfe","kknecibjnq","wuxnoibr","gkxpnpbfvjm","lwpphufxw","sbs","txb","ilbqahdzgij","i","zvuur","yfglchzpledkq","eqdf","nw","aiplrzejplumda","d","huoybvhibgqibbwwdzhqhslb","rbnzendwnoklpyyyauemm"};
        System.out.println(groupStrings(strings1));
        System.out.println(groupStringsHashMethod(strings1));
        //
        //#Reference:
        //1691. Maximum Height by Stacking Cuboids
        //135. Candy
        //2332. The Latest Time to Catch a Bus
        //2330. Valid Palindrome IV
        //257. Binary Tree Paths
        //2546. Apply Bitwise Operations to Make Strings Equal
    }
}
