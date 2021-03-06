package interviews;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Locale;

public class E57_MinimumWindowSubstring {

    public static String minWindow(String s, String t) {
//        s=s.toLowerCase();
//        t=t.toLowerCase();
        int[] right =new int[58];
        int[] left =new int[58];
        Deque<Integer> deque=new LinkedList<>();
        Deque<Integer> rsDeque=null;
        int minLength=Integer.MAX_VALUE;

        for(int i=0;i<t.length();i++){
            right[t.charAt(i)-'A']++;
        }
        for(int i=0;i<s.length();i++){
            if(right[s.charAt(i)-'A']==0){
//                deque.addFirst(s.charAt(i));
                if(!deque.isEmpty()){
                    deque.addFirst(i);
                }
                continue;
            }
            left[s.charAt(i)-'A']++;

            if(left[s.charAt(i)-'A']>right[s.charAt(i)-'A']){
                while (!deque.isEmpty()&&(right[s.charAt(deque.getLast())-'A']==0
                        ||left[s.charAt(deque.getLast())-'A']>right[s.charAt(deque.getLast())-'A'])){
                    Integer removeElement=deque.pollLast();

                    if(right[s.charAt(removeElement)-'A']!=0){
                        left[s.charAt(removeElement)-'A']--;
                    }
                }
            }
            deque.addFirst(i);
            if(checkValidate(left, right)&&minLength>deque.size()){
                rsDeque= new LinkedList<>(deque);
                minLength=deque.size();
            }
        }
        StringBuilder rs=new StringBuilder();

        while (rsDeque!=null&&!rsDeque.isEmpty()){
            rs.append(s.charAt(rsDeque.pollLast()));
        }
        return rs.toString();
    }

    public static String minWindowOptimized(String s, String t) {
        int []m = new int[256];
        int ans = Integer.MAX_VALUE;
        int start = 0;
        int count = 0 ;
        for(int i = 0; i < t.length() ; i++){
            if(m[t.charAt(i)] == 0){
                count++;
            }
            m[t.charAt(i)]++;
        }

        int i = 0 ;
        int j = 0 ;
        while(j < s.length()){
            m[s.charAt(j)]--;

            if(m[s.charAt(j)] == 0){
                count--;
            }

            if(count == 0){
                while(count == 0){
                    if(ans > j - i + 1){
                        ans = Math.min(ans , j - i + 1);
                        start = i;
                    }
                    m[s.charAt(i)]++;
                    if(m[s.charAt(i)] > 0){
                        count++;
                    }
                    i++;
                }
            }
            j++;
        }

        if(ans != Integer.MAX_VALUE){
            return s.substring(start , ans + start);
        }
        else{
            return "";
        }
    }

    public static boolean checkValidate(int left[], int right[]){
        int length=left.length;

        for(int i=0;i<length;i++){
            if(left[i]<right[i]){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        String s="ADOBECODEBANC";
//        String t="ABC";
//        String s="a";
//        String t="aa";
//        String s="a";
//        String t="a";

        //B??i n??y t?? duy nh?? sau:
        //1,
        //T?? duy:
        //ADOBECODEBANC
        //ABC
        //
        //A B C
        //1 1 1
        //
        //A A B A C
        //0 1 2 3 4
        //1 2 1 3 1
        //
        //A A B A C
        //---> ??? ????y kh??ng th??? d??ng c??ch l??u v???t index --> V?? s??? c?? case d???ng:
        //s : A D A B E A C
        //t : ABC --> Kh??ng th??? bi???t A n???m ??? ????u m?? tr??? ??i + Kh??ng th??? b??? qua ???????c D.
        //2, T?? duy d???ng dequeue, quy t???c nh?? sau:
        //dequeue s??? add th??m ph???n t??? (i) (Index) v??o ch??? khi:
        //+ s[i] kh??ng n???m trong t + dequeue not empty
        //+ s[i] m?? n???m trong t --> left[s[i]-'A']++ --> Sau ???? c???n dequeue c??c ph???n t??? ??? ?????y dequeue (dequeue.pollLast())
        //--> dequeue khi left[ s[dequeue.peekLast()] -'A'] > right[ s[dequeue.peekLast()] -'A'] (T???c l?? ??ang th???a s??? l???n)
        // + right[ s[dequeue.peekLast()] -'A']==0 (T???c l?? kh??ng t???n t???i trong t)
        //3, Ch?? ?? li??n quan ?????n ph???n test cases b??n d?????i:
        //3.1, Li??n quan ?????n khi n??o th?? add[i] (i kh??ng c?? trong t)
        //3.2, Equal ??? ????y ta c?? th??? vi???t function ????? customer compare().
        //left[i]>right[i] : V???n th???a m??n.

        //Case 1 do:
        //Ta lu??n add(i) v??o dequeue khi loop
        //--> N?? s??? b??? case t??m th???y k??t qu??? r???i nh??ng khi g???p nh???ng t??? v?? ngh??a kh??ng c?? t??c d???ng
        //Th?? v???n add v??o index.
//        String s="ab";
//        String t="a";
        //Case 2 do:
        //--> N?? b c?? th??? s??? c?? tr?????ng h???p dequeue == empty --> Add m???y character v?? d???ng v??o kh??ng ????? l??m g?? c???.
//        String s="ab";
//        String t="b";
        //Case 3: Case n??y b??? khi chu???i left c?? s??? ch??? s??? (a) > s??? ch??? s??? a c???a right
        //--> Arrays.equal() kh??ng c??n ????ng n???a --> C???n ph???i vi???t function t??? check.
        String s="aaaaaaaaaaaabbbbbcdd";
        String t="abcdd";
        //Reference:
        //https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
        //https://leetcode.com/problems/substring-with-concatenation-of-all-words/
        System.out.println(minWindow(s, t));
    }
}
