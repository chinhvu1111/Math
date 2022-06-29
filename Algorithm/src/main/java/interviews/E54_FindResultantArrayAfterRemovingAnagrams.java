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
        //Chú ý: Với những bài remove phần tử ntn thì tốt nhất là tận dụng tính của Stack and Queue:
        //--> Nhưng còn tùy vào order của các words.
        //Tư duy bài kiểu này khá dị:
        //1, Nếu muốn lấy kết quả trả về rs --> thì ta cần các operations :
        //+ add(value)
        //+ remove (value) ==> Wrong (Không cần remove values) --> Vì thực chất các words đã được (Removed sẵn từ lúc add)
        //==> Ta chọn add vào khi cần <=> Removed list.

        //Case 1: Case này khi sai cần check for(0 --> 26) return false;
        //Input:
        //["a","b","c","d","e"]
        //Output:
        //["a","b"]
        //Expected:
        //["a","b","c","d","e"]

        //Câu này do ta đọc sai đề bài:
        //** Để bài là:
        //words[i-1], words[i] --> remove words[i].
        //Case 2:
        //Input
        //["a","b","a"]
        //Output
        //["a","b"]
        //Expected
        //["a","b","a"]
//        String s[]=new String[]{"a","b","a"};

        //Bài này ta tư duy như sau:
        //*** Cách 1:
        //Bài này là ta tư duy như sau:
        //1, Không được phép nhầm giữa việc nhóm 1 cách tự do:
        //RULE:
        //word[i-1], word[i] --> Khi nhóm lại ta chỉ được phép bỏ word[i-1]
        //1.1, Tận dụng quy tắc thứ tự giữa các word:
        //Chú ý: Với những bài remove phần tử ntn thì tốt nhất là tận dụng tính của Stack and Queue:
        //--> Nhưng còn tùy vào order của các words.
        //Tư duy bài kiểu này khá dị:
        //1.2, Nếu muốn lấy kết quả trả về rs --> thì ta cần các operations :
        //+ add(value)
        //+ remove (value) ==> Wrong (Không cần remove values) --> Vì thực chất các words đã được (Removed sẵn từ lúc add)
        //==> Ta chọn add vào khi cần <=> Removed list.
        //2, Ta dùng stack xét theo thứ tự các phần tử:
        //2.1, Nếu phần tử sắp thêm vào kết hợp được với top --> Ta không thêm element đó nữa.
        //3, Bài toán con ở đây sẽ là : isAnagram(s, t)
        //isAnagram(s,t) : Check 2 chuỗi xem chúng có là Anagram của nhau hay không
        //VD: abc và bac là anagram
        //VD: a và a cũng là anagram
        //
        //*** Cách 2 : Tối ưu hơn
        //Tối ưu ở những điểm như sau:
        //1, Khi check : isAnagram(s, t)
        //--> Ta luôn khởi tạo arr[27] liên tục
        //VD: so sánh : s vs s1
        //--> khởi tạo array[27] của cả s và s1.
        //Ta tiếp tục so sánh s1 vs s2
        //--> Ta lại khởi tạo lại arr[27] của s1 và s2.
        //===> Gây duplicated phần khởi tạo array s1
        //1.1, Ở đây ta sẽ dùng mảng previous --> swap.
        //2, Tối ưu lưu trữ
        //Nếu ta dùng số nguyên nhỏ --> Ta có thể lưu trữ nó dưới dạng byte[]
        //2^8--> Có thể thay cho INT <-> BOOLEAN
        List<String> rs=removeAnagrams(s);
        List<String> rs1=removeAnagramsOptimized1(s);
        System.out.println(rs);
        System.out.println(rs1);
    }
}
