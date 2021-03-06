package interviews;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class E54_FindResultantArrayAfterRemovingAnagrams {

//    public static List<String> removeAnagrams(String[] words) {
//        int n=words.length;
//        List<String> rs=new ArrayList<>();
//
//        for(int i=0;i<n;i++){
//            boolean isAnagram=false;
//            String currentWord=words[i];
//
//            for(int j=0;j<rs.size();j++){
//                if(isAnagram(currentWord, rs.get(j))){
//                    isAnagram=true;
//                    break;
//                }
//            }
//            if(!isAnagram){
//                rs.add(currentWord);
//            }
//        }
//        return rs;
//    }

    public static List<String> removeAnagrams(String[] words) {
        int n=words.length;
        Stack<String> stack=new Stack<>();
//        List<String> rs=new ArrayList<>();

        for(int i=0;i<n;i++){
            boolean isAnagram=false;
            String currentWord=words[i];

            while (!stack.isEmpty()){
                if(isAnagram(stack.peek(), currentWord)){
                    isAnagram=true;
//                    stack.pop();
                }
                break;
            }
            if(!isAnagram){
                stack.push(currentWord);
//                rs.add(currentWord);
            }
        }
        return stack;
    }

    public static boolean isAnagram(String t, String s){
        if(t.length()!=s.length()){
            return false;
        }
        int arr[]=new int[27];

        for(int i=0;i<s.length();i++){
            arr[s.charAt(i)-'a']++;
            arr[t.charAt(i)-'a']--;
        }
        for(int i=0;i<27;i++){
            if(arr[i]!=0){
                return false;
            }
        }
        return true;
    }

    public List<String> removeAnagramsOptimized(String[] words) {
        ArrayList<String> ans = new ArrayList<>(words.length);
        byte[] sign = new byte[26], tmp, buf = new byte[26];
        for (String s : words) {
            Arrays.fill(buf, (byte) 0);
            for (int i=0, slen=s.length(); i < slen; i++) {
                buf[s.charAt(i) - 'a']++;
            }
            if (Arrays.equals(sign, buf)) continue;
            tmp = sign; sign = buf; buf = tmp; // swap
            ans.add(s);
        }
        return ans;
    }

    public static List<String> removeAnagramsOptimized1(String[] words){
        ArrayList<String> rs = new ArrayList<>(words.length);
        byte[] sign=new byte[26];
        byte[] temp;
        byte[] prevSign=new byte[26];

        for(int i=0;i<words.length;i++){
            Arrays.fill(sign, (byte) 0);
            String currentWord=words[i];

            for(int j=0;j<currentWord.length();j++){
                sign[currentWord.charAt(j)-'a']++;
            }
            if(Arrays.equals(sign, prevSign)){
                continue;
            }
            temp=sign;
            sign=prevSign;
            prevSign=temp;
            rs.add(currentWord);
        }
        return rs;
    }

    public static void main(String[] args) {
        String s[]=new String[]{"abba","baba","bbaa","cd","cd"};
//        String s[]=new String[]{"abba","baba"};
//        String s[]=new String[]{"b","c","d","e"};
        //Ch?? ??: V???i nh???ng b??i remove ph???n t??? ntn th?? t???t nh???t l?? t???n d???ng t??nh c???a Stack and Queue:
        //--> Nh??ng c??n t??y v??o order c???a c??c words.
        //T?? duy b??i ki???u n??y kh?? d???:
        //1, N???u mu???n l???y k???t qu??? tr??? v??? rs --> th?? ta c???n c??c operations :
        //+ add(value)
        //+ remove (value) ==> Wrong (Kh??ng c???n remove values) --> V?? th???c ch???t c??c words ???? ???????c (Removed s???n t??? l??c add)
        //==> Ta ch???n add v??o khi c???n <=> Removed list.

        //Case 1: Case n??y khi sai c???n check for(0 --> 26) return false;
        //Input:
        //["a","b","c","d","e"]
        //Output:
        //["a","b"]
        //Expected:
        //["a","b","c","d","e"]

        //C??u n??y do ta ?????c sai ????? b??i:
        //** ????? b??i l??:
        //words[i-1], words[i] --> remove words[i].
        //Case 2:
        //Input
        //["a","b","a"]
        //Output
        //["a","b"]
        //Expected
        //["a","b","a"]
//        String s[]=new String[]{"a","b","a"};

        //B??i n??y ta t?? duy nh?? sau:
        //*** C??ch 1:
        //B??i n??y l?? ta t?? duy nh?? sau:
        //1, Kh??ng ???????c ph??p nh???m gi???a vi???c nh??m 1 c??ch t??? do:
        //RULE:
        //word[i-1], word[i] --> Khi nh??m l???i ta ch??? ???????c ph??p b??? word[i-1]
        //1.1, T???n d???ng quy t???c th??? t??? gi???a c??c word:
        //Ch?? ??: V???i nh???ng b??i remove ph???n t??? ntn th?? t???t nh???t l?? t???n d???ng t??nh c???a Stack and Queue:
        //--> Nh??ng c??n t??y v??o order c???a c??c words.
        //T?? duy b??i ki???u n??y kh?? d???:
        //1.2, N???u mu???n l???y k???t qu??? tr??? v??? rs --> th?? ta c???n c??c operations :
        //+ add(value)
        //+ remove (value) ==> Wrong (Kh??ng c???n remove values) --> V?? th???c ch???t c??c words ???? ???????c (Removed s???n t??? l??c add)
        //==> Ta ch???n add v??o khi c???n <=> Removed list.
        //2, Ta d??ng stack x??t theo th??? t??? c??c ph???n t???:
        //2.1, N???u ph???n t??? s???p th??m v??o k???t h???p ???????c v???i top --> Ta kh??ng th??m element ???? n???a.
        //3, B??i to??n con ??? ????y s??? l?? : isAnagram(s, t)
        //isAnagram(s,t) : Check 2 chu???i xem ch??ng c?? l?? Anagram c???a nhau hay kh??ng
        //VD: abc v?? bac l?? anagram
        //VD: a v?? a c??ng l?? anagram
        //
        //*** C??ch 2 : T???i ??u h??n
        //T???i ??u ??? nh???ng ??i???m nh?? sau:
        //1, Khi check : isAnagram(s, t)
        //--> Ta lu??n kh???i t???o arr[27] li??n t???c
        //VD: so s??nh : s vs s1
        //--> kh???i t???o array[27] c???a c??? s v?? s1.
        //Ta ti???p t???c so s??nh s1 vs s2
        //--> Ta l???i kh???i t???o l???i arr[27] c???a s1 v?? s2.
        //===> G??y duplicated ph???n kh???i t???o array s1
        //1.1, ??? ????y ta s??? d??ng m???ng previous --> swap.
        //2, T???i ??u l??u tr???
        //N???u ta d??ng s??? nguy??n nh??? --> Ta c?? th??? l??u tr??? n?? d?????i d???ng byte[]
        //2^8--> C?? th??? thay cho INT <-> BOOLEAN
        List<String> rs=removeAnagrams(s);
        List<String> rs1=removeAnagramsOptimized1(s);
        System.out.println(rs);
        System.out.println(rs1);
    }
}
