package contest;

import java.util.*;

public class E118_LexicographicallyMinimumStringAfterRemovingStars {

    public static String clearStars(String s) {
        int n=s.length();
        //Min heap
        //- Sort character
        //- Sort by diff
        //
        PriorityQueue<int[]> queue=new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]!=o2[0]){
                    return o1[0]-o2[0];
                }
                return o2[1]-o1[1];
            }
        });
        Set<Integer> removedIndex= new HashSet<>();

//        for(int i=0;i<n;i++){
//            if(s.charAt(i)=='*'){
//                int[] deletedChar= queue.poll();
//                removedIndex.add(deletedChar[2]);
//            }
//            int curChar = s.charAt(i)-'a';
//            int j=i+1;
//            while (j<n&&(s.charAt(j)-'a'<curChar||s.charAt(j)=='*')){
//                j++;
//            }
//            int nextChar = j<n?s.charAt(j)-'a':-1;
//            queue.add(new int[]{curChar, nextChar-curChar, i});
//        }
        for(int i=0;i<n;i++){
            if(s.charAt(i)=='*'){
                int[] deletedChar= queue.poll();
                removedIndex.add(deletedChar[1]);
                continue;
            }
            int curChar = s.charAt(i)-'a';
            queue.add(new int[]{curChar, i});
        }
        StringBuilder rs= new StringBuilder();
        for(int i=0;i<n;i++){
            if(!removedIndex.contains(i)&&s.charAt(i)!='*'){
                rs.append(s.charAt(i));
            }
        }
        return rs.toString();
    }

    public static void main(String[] args) {
        //* Requirement
        //- You are given a string s.
        // It may contain (any number of '*' characters).
        // Your task is to (remove all '*' characters).
        //While there is a '*', do the following operation:
        //- Delete the leftmost '*' and
        // the smallest non-'*' character to its left.
        // + If there are several smallest characters, you can delete any of them.
        //* Return (the lexicographically smallest) resulting string after removing (all '*' characters).
        //
        //Example 1:
        //Input: s = "aaba*"
        //Output: "aab"
        //Explanation:
        //We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes (the lexicographically smallest).
        //
        // Idea
        //1.
        //1.0,
        //- Constraint:
        //1 <= s.length <= 10^5
        //s consists only of lowercase English letters and '*'.
        //The input is generated such that it is possible to delete all '*' characters.
        //+ n khá lớn ==> 10^5
        //
        //- Brainstorm
        //- Mỗi step follow operation:
        //  + Remove (leftmost * character)
        //  + Remove (smallest non-'*' character) to its (left).
        //
        //s = "aaba*"
        //- Remove smallest character
        //s = "aaba*bdca*"
        //
        //- Các lần delete có độc lập hay không?
        //- Xoá character tại index --> Chỉ ảnh hưởng đến character sau nó
        //- Vì smallest character:
        //  + Delete --> thì character sau đó có thể:
        //      + Không tồn tại
        //      + > smallest character
        //s = (a)aba
        //- aba
        //s = a(a)ba
        //- aba
        //s = aab(a)
        //- aab
        //Thứ tự ưu tiên:
        //- Không tồn tại character đằng sau
        //- Tồn tại character đằng sau ==
        //- Tồn tại character đằng sau > ==> Ít thôi
        //==> Priority Queue.
        //** ==> WRONG : Chỉ cần sort theo (char, index) là được
        //
        //- Vấn đề là khi delete character
        //==> Cần update lại next character?
        //==> Ta sẽ lấy character lớn hơn nó ==> Vì (smaller sẽ được xử lý trước yên tâm).
        //
//        String s="aaba*";
        String s="aaba*ab*";
        //s="aaba*ab*"
        //s="aabab*"
        //s="abab"
        //
        //aaba*a
        //==> nên xoá aab(a)*a thay vì a(a)ba*a / (a)aba*a
        //  + b sẽ bị đẩy lên phía trước
        //aaba*a
        //= aab(a)b*
        //= aabb
//        String s="abc";
        System.out.println(clearStars(s));
    }
}
