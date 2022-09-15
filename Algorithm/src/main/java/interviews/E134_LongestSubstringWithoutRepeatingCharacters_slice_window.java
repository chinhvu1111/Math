package interviews;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class E134_LongestSubstringWithoutRepeatingCharacters_slice_window {

    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> queue=new LinkedList<>();
        int[] mark =new int[256];
        Arrays.fill(mark, -1);
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            if(mark[s.charAt(i)]!=-1){
                rs=Math.max(rs, queue.size());
//                System.out.printf("%s,", i);
                while (!queue.isEmpty()&&queue.peek()!=s.charAt(i)){
                    mark[queue.poll()]=-1;
                }
                if(!queue.isEmpty()){
                    mark[queue.poll()]=-1;
                }
            }
            mark[s.charAt(i)]=1;
            queue.add(s.charAt(i));
        }
//        queue.forEach(c-> System.out.printf("%s,", c));
        rs=Math.max(rs, queue.size());
        return rs;
    }

    public static int lengthOfLongestSubstringOptimize(String s) {
        char[] queue=new char[s.length()];
        int front=0,rear=0;
        int length=0;
        int[] mark =new int[256];
        Arrays.fill(mark, -1);
        int n=s.length();
        int rs=0;

        for(int i=0;i<n;i++){
            if(mark[s.charAt(i)]!=-1){
                rs=Math.max(rs, length);
//                System.out.printf("%s,", i);
//                System.out.println(String.valueOf(queue));
                while (length>0&&queue[front]!=s.charAt(i)){
                    mark[queue[front++]]=-1;
                    length--;
                }
                if(length>0){
//                    mark[queue[front++]]=-1;
                    front++;
                    length--;
                }
            }
            mark[s.charAt(i)]=1;
            queue[rear++]=s.charAt(i);
            length++;
        }
//        queue.forEach(c-> System.out.printf("%s,", c));
        rs=Math.max(rs, length);
        return rs;
    }

    public static int lengthOfLongestSubstringHashmap(String s) {
        if(s.length()==1){
            return 1;
        }
        HashMap<Character, Integer> hashIndex=new HashMap<>();
        int n=s.length();
        int rs=0;
        int firstIndex=0;

        for(int i=0;i<n;i++){
            Integer indexBefore=hashIndex.get(s.charAt(i));

            if(indexBefore!=null){
                rs=Math.max(i-firstIndex, rs);
//                System.out.printf("%s %s, ", firstIndex, i-firstIndex);
                if(firstIndex<=indexBefore){
                    firstIndex=indexBefore+1;
                }
            }
            hashIndex.put(s.charAt(i), i);
        }
        rs=Math.max(rs, n-firstIndex);
//        System.out.println(firstIndex);
//        System.out.println();
//        queue.forEach(c-> System.out.printf("%s,", c));
        return rs;
    }

    public static int lengthOfLongestSubstringArray(String s) {
        if(s.length()==1){
            return 1;
        }
        int[] mark =new int[256];
        Arrays.fill(mark, -1);
        int n=s.length();
        int rs=0;
        int firstIndex=0;

        for(int i=0;i<n;i++){
            int indexBefore=mark[s.charAt(i)];

            if(indexBefore!=-1){
                rs=Math.max(i-firstIndex, rs);
//                System.out.printf("%s %s, ", firstIndex, i-firstIndex);
                if(firstIndex<=indexBefore){
                    firstIndex=indexBefore+1;
                }
            }
            mark[s.charAt(i)]=i;
        }
        rs=Math.max(rs, n-firstIndex);
//        System.out.println(firstIndex);
//        System.out.println();
//        queue.forEach(c-> System.out.printf("%s,", c));
        return rs;
    }

    public static void main(String[] args) {
//        String s="abcabcbb";
//        String s="bbbbb";
//        String s="pwwkew";
//        String s="dvdf";
        String s="hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789hijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
//        String s="p";
//        String s=" ";
//        String s="au";
//        String s="abba";
//        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstringOptimize(s));
        System.out.println(lengthOfLongestSubstringHashmap(s));
        System.out.println(lengthOfLongestSubstringArray(s));
        //Bài này tư duy như sau:
        //Cách 1:
        //1, Ta sử dụng phương pháp queue giữ trong queue chỉ những phân từ không lặp lại:
        //1.1, ta sẽ khởi tạo 2 cấu trúc dữ liệu
        //- queue để lưu các phần tử không lặp lại VD: abcd
        //- array để lưu (sự tồn tại) của từng phần tự (a,b,c,e)
        //1.2, Tư duy như sau:
        //+ Do all characters --> 256 ký tự.
        //VD:
        //abcabcbb : abc(a) ==> Nếu gặp a
        //- Kiểm tra sự tồn tại của (a) --> Nếu a tồn tại
        //- queue pop + mark[character]=-1 (Đánh dấu là không tồn tại - do đã remove khỏi queue) --> cho đến khi gặp (a) thì thôi.
        //- add thêm element mới vào queue.
        //
        //Cách 2:
        //1,
        //1.1, Ở đây ta sẽ khởi tạo 1 cấu trúc dữ liệu duy nhất:
        //- Array[256] sẽ lưu index cuối cùng của ký tự thứ (i).
        //1.2, Tư duy như sau:
        //- Ta sẽ lưu index của các ký tự vào array
        //+ Sau mỗi (i)
        //VD: abc(a)bcbb
        //--> a đã có rồi --> firstIndex=(index của của a) + 1 ==> Và ta chỉ lấy chuỗi từ (a) trở đi.
        //+  arr[s[i]]=i;
        //+ rs= max(rs, i-firstIndex)
        //VD abb(a) --> a đã tồn tại ở vị trí 0, firstIndex = 2
        //abb(firstIndex)(a) --> Vì có thể xảy ra
        // ( index trước đó =0 (a) > firstIndex (b) )
        //--> Đoạn này ta phải check (firstIndex với curent index cũ)
        //CODE:
        //===========
        //if(firstIndex<=indexBefore){
        //    firstIndex=indexBefore+1;
        //}
        //===========
    }
}
