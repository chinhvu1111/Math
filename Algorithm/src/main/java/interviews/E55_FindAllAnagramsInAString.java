package interviews;

import java.util.*;

public class E55_FindAllAnagramsInAString {

    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> rs=new ArrayList<>();

        if(s.length()<p.length()){
            return rs;
        }
        int left[]=new int[27];
        int temp[]=new int[27];
        Deque<Character> characters=new LinkedList<>();

        for(int i=0;i<p.length();i++){
            left[p.charAt(i)-'a']++;
            temp[s.charAt(i)-'a']++;
            characters.addFirst(s.charAt(i));
        }
        if(Arrays.equals(temp, left)){
            rs.add(0);
        }
        for(int i=p.length();i<s.length();i++){
            temp[characters.pollLast()-'a']--;
            characters.addFirst(s.charAt(i));
            temp[characters.getFirst()-'a']++;
            if(Arrays.equals(left, temp)){
                rs.add(i-p.length()+1);
            }
        }
        return rs;
    }

    public static List<Integer> findAnagramsOptimized(String s, String p) {
        List<Integer> rs=new ArrayList<>();

        if(s.length()<p.length()){
            return rs;
        }
        int freq1[] = new int[26];
        int freq2[] = new int[26];
        List<Integer> list = new ArrayList<>();

        if(s.length()<p.length())
            return list;

        for(int i=0; i<p.length(); i++){
            freq1[s.charAt(i)-'a']++;
            freq2[p.charAt(i)-'a']++;
        }

        int start=0;
        int end=p.length();

        if(Arrays.equals(freq1,freq2))
            list.add(start);

        while(end<s.length()){

            freq1[s.charAt(start)-'a']--;
            freq1[s.charAt(end)-'a']++;

            if(Arrays.equals(freq1,freq2)) {
                list.add(start+1);
            }
            start++;
            end++;
        }
        return list;
    }

    public static void main(String[] args) {
//        String s="cbaebabacd";
//        String p="abc";
        //Case 1 : : Liên quan đến việc - index
//        String s="abab";
//        String p="ab";
        //Output: [0,6]
        //Case 2:
        String s="aaaaaaaaaa";
        String p="aaaaaaaaaaaaa";
        System.out.println(findAnagrams(s, p));
        //Bài này tư duy như sau:
        //Cách 1 : Dùng dequeue:
        //POP + ADD vào first and last.
        //1, Nếu ta chỉ dùng true, false thì không đủ vì có thể có cases các ký tự có thể duplicated:
        //VD:
        //left : abab
        //right : ab
        //---> Nên cần count số lượng các ký tự xuất hiện để kiểm tra rearrange của mỗi chuỗi String.

        //Reference:
        //76. Minimum Window Substring
        //340. Longest Substring with At Most K Distinct Characters
        //159. Longest Substring with At Most Two Distinct Characters
        //3. Longest Substring Without Repeating Characters
        //209. Minimum Size Subarray Sum
        //219. Contains Duplicate II
        //220. Contains Duplicate III
        //567. Permutation in String
        //239. Sliding Window Maximum
        //480. Sliding Window Median
        //487. Max Consecutive Ones II
        //1004. Max Consecutive Ones III

        //Cách 2:
        //1, Ở dạng dequeue như thế này ta có thể chọn sử dụng
        //1.1, (array + startIndex + endIndex)
        //1.2, dequeue --> Remove + add (các thao tác này sẽ nhanh hơn việc thao tác với register)
        //2, Bài naỳ tư duy theo dạng range index
        //start, end --> Sau đó thay vì remove đầu + add last, ta sẽ :
        //start++, end++ : Để move range.
    }
}
