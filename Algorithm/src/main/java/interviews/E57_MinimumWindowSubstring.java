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

        //Bài này tư duy như sau:
        //1,
        //Tư duy:
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
        //---> Ở đây không thể dùng cách lưu vết index --> Vì sẽ có case dạng:
        //s : A D A B E A C
        //t : ABC --> Không thể biết A nằm ở đâu mà trừ đi + Không thể bỏ qua được D.
        //2, Tư duy dạng dequeue, quy tắc như sau:
        //dequeue sẽ add thêm phần tử (i) (Index) vào chỉ khi:
        //+ s[i] không nằm trong t + dequeue not empty
        //+ s[i] mà nằm trong t --> left[s[i]-'A']++ --> Sau đó cần dequeue các phần tử ở đầy dequeue (dequeue.pollLast())
        //--> dequeue khi left[ s[dequeue.peekLast()] -'A'] > right[ s[dequeue.peekLast()] -'A'] (Tức là đang thừa số lần)
        // + right[ s[dequeue.peekLast()] -'A']==0 (Tức là không tồn tại trong t)
        //3, Chú ý liên quan đến phần test cases bên dưới:
        //3.1, Liên quan đến khi nào thì add[i] (i không có trong t)
        //3.2, Equal ở đây ta có thể viết function để customer compare().
        //left[i]>right[i] : Vẫn thỏa mãn.

        //Case 1 do:
        //Ta luôn add(i) vào dequeue khi loop
        //--> Nó sẽ bị case tìm thấy kêt quả rồi nhưng khi gặp những từ vô nghĩa không có tác dụng
        //Thì vẫn add vào index.
//        String s="ab";
//        String t="a";
        //Case 2 do:
        //--> Nó b có thể sẽ có trường hợp dequeue == empty --> Add mấy character vô dụng vào không để làm gì cả.
//        String s="ab";
//        String t="b";
        //Case 3: Case này bị khi chuỗi left có số chữ số (a) > số chữ số a của right
        //--> Arrays.equal() không còn đúng nữa --> Cần phải viết function tự check.
        String s="aaaaaaaaaaaabbbbbcdd";
        String t="abcdd";
        //Reference:
        //https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/
        //https://leetcode.com/problems/substring-with-concatenation-of-all-words/
        System.out.println(minWindow(s, t));
    }
}
